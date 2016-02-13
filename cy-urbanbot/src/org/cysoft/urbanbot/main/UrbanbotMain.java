package org.cysoft.urbanbot.main;


import java.util.List;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

import org.cysoft.bss.core.model.AppParam;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanBotUtility;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.common.ICyUrbanbotConst;
import org.cysoft.urbanbot.core.LiveSessions;
import org.cysoft.urbanbot.core.UpdateDispatcher;
import org.cysoft.urbanbot.core.UpdateDispatcherListener;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UrbanbotMain  implements Runnable,UpdateDispatcherListener	
{
	
	private static final Logger logger = LoggerFactory.getLogger(UrbanbotMain.class);
	
	private boolean stopNow=false;
	
	private String bssAppName="";
		public String getBssAppName() {
		return bssAppName;
	}
	public void setBssAppName(String bssAppName) {
		this.bssAppName = bssAppName;
	}
	
	private String bssCoreUrl="";
	public String getBssCoreUrl() {
		return bssCoreUrl;
	}
	public void setBssCoreUrl(String bssCoreUrl) {
		this.bssCoreUrl = bssCoreUrl;
	}

	private String bssUserId="";
	public String getBssUserId() {
		return bssUserId;
	}
	public void setBssUserId(String bssUserId) {
		this.bssUserId = bssUserId;
	}
	
	private String bssPwd="";
	public String getBssPwd() {
		return bssPwd;
	}
	public void setBssPwd(String bssPwd) {
		this.bssPwd = bssPwd;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.info(">>> Start Urbanbot...");
		
		//System.setProperty("http.proxyHost", "lelapo.telecomitalia.local");
    	//System.setProperty("http.proxyPort", "8080");
		
		if (args.length!=4){
			logger.error("Number of parameters wrong > actual:"+args.length+";aspected:4 [BssCoreAppId, BssCoreUrl, userId, pwd]");
			System.exit(1);
		}

		UrbanbotMain urbanBot=new UrbanbotMain();
		urbanBot.setBssAppName(args[0]);
		logger.info("appName="+urbanBot.getBssAppName());
		
		urbanBot.setBssCoreUrl(args[1]);
		logger.info("coreUrl="+urbanBot.getBssCoreUrl());
		
		urbanBot.setBssUserId(args[2]);
		logger.info("userId="+urbanBot.getBssUserId());

		urbanBot.setBssPwd(args[3]);
		logger.info("userId="+urbanBot.getBssPwd());
		
		
		Thread t=new Thread(urbanBot);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.warn(e.toString());
		}
		
		
		logger.info("<<< End Urbanbot...");
		System.exit(1);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info(">>> Start Main Thread...");
		
		CyBssCoreAPI bssCoreAPI=CyBssCoreAPI.getInstance();
		bssCoreAPI.setAppName(bssAppName);		
		bssCoreAPI.setCoreUrl(bssCoreUrl);
		
		try {
			bssCoreAPI.logOn(bssUserId, bssPwd);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			return;
		}
		
		long updatesOffSet=0;
		try {
			updatesOffSet = bssCoreAPI.getUpdatesOffSet();
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			System.exit(1);
		}
		
		TelegramAPI telegramAPI=TelegramAPI.getInstance();
		try {
			AppParam param=bssCoreAPI.getParam("bot_url");
			if (param==null){
				logger.error("Param bot_url not configurated");
				throw new CyUrbanbotException("Param bot_url not configurated");
			}
			telegramAPI.setBotUrl(param.getValue());
			
			param=bssCoreAPI.getParam("download_path");
			if (param==null){
				logger.error("Param download_path not configurated");
				throw new CyUrbanbotException("Param download_path not configurated");
			}
			telegramAPI.setDownloadPath(param.getValue());
			
			param=bssCoreAPI.getParam("bot_file_url");
			if (param==null){
				logger.error("Param bot_file_url not configurated");
				throw new CyUrbanbotException("Param bot_file_url not configurated");
			}
			telegramAPI.setBotFileUrl(param.getValue());
			
			bssCoreAPI.setDownloadPath(telegramAPI.getDownloadPath());
			
			param=bssCoreAPI.getParam("media_mediation");
			if (param!=null && !param.equals("")){
				bssCoreAPI.setMediaMediation(param.getValue());
			}
				
			
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			System.exit(1);
		}
		
		TransferQueue<Update> queue =new LinkedTransferQueue<Update>();
		UpdateDispatcher updateDispatcher=new UpdateDispatcher(queue);
		updateDispatcher.addUpdateDispatcherListener(this);
		Thread t=new Thread(updateDispatcher);
		t.start();
		
		List<Update> telegramUpdates=null;
		
		int loop=0;
		short updErrorCount=0;
		
		while(true){
			boolean updError=false;
			
			try {
				if (stopNow) break;
				
				try{
					telegramUpdates = telegramAPI.getUpdates(updatesOffSet);
					for(Update update:telegramUpdates){
						logger.info("Received:"+update);
						queue.transfer(update);
					}
			
				} catch (CyUrbanbotException e){
					logger.error("getUpdates error:"+e.getMessage()+ "... sleeping for 1 min.");
					Thread.sleep(60000);
					loop=50; // force updateOffset
					updErrorCount++;
					updError=true;
					if (updErrorCount>=60){
						logger.error("updErrorCount:"+updErrorCount);
						break;
					}
				}
				if (!updError)
					updErrorCount=0;
				
				if (telegramUpdates!=null){
					int updatesSize=telegramUpdates.size();
					if (updatesSize>0){
						updatesOffSet=telegramUpdates.get(updatesSize-1).getUpdate_id()+1;
						bssCoreAPI.setUpdatesOffSet(updatesOffSet);
					}
				}	
				
				loop++;
				if (loop>=50){
					logger.info("loop="+loop+"; call setUpdatesOffSet("+updatesOffSet+")");
					bssCoreAPI.setUpdatesOffSet(updatesOffSet); //no bss-core session timeout
					loop=0;
				}
				
				//Discard session
				long currentTime=CyUrbanBotUtility.getCurrentTime().getTime();
				for(Session session:LiveSessions.getInstance().getSessionsList()){
					//logger.info("session="+session.toString());
					if ((currentTime-session.getLastUseTime())>=ICyUrbanbotConst.SESSION_TIME_OUT){
						String message=CyBssCoreAPI.getInstance().
								getMessage(BotMessage.SESSION_TIMEOUT_ID, session.getLanguage());
						TelegramAPI.getInstance().sendMessage(message, session.getId(), 0);
						LiveSessions.getInstance().remove(session.getId());
					}
				}
				
				
				Thread.sleep(3000);
				
			} catch (CyUrbanbotException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.warn(e.toString());
			}
			
		} // end while
		
		t.interrupt();
		
		logger.info(">>> End Main Thread...");
	}
	@Override
	public void onUpdateDispatcherStop() {
		// TODO Auto-generated method stub
		logger.info("Stopping...");
		stopNow=true;
	}
	
}
