package org.cysoft.urbanbot.main;


import java.util.List;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.UpdateDispatcher;
import org.cysoft.urbanbot.core.UpdateDispatcherListener;
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
			telegramAPI.setBotUrl(bssCoreAPI.getParam("bot_url").getValue());
			telegramAPI.setDownloadPath(bssCoreAPI.getParam("download_path").getValue());
			telegramAPI.setBotFileUrl(bssCoreAPI.getParam("bot_file_url").getValue());
			
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			System.exit(1);
		}
		
		TransferQueue<Update> queue =new LinkedTransferQueue<Update>();
		UpdateDispatcher updateDispatcher=new UpdateDispatcher(queue); 
		Thread t=new Thread(updateDispatcher);
		t.start();
		
		
		List<Update> telegramUpdates=null;
		
		while(true){
			
			try {
				if (stopNow) break;
				telegramUpdates = telegramAPI.getUpdates(updatesOffSet);
				for(Update update:telegramUpdates){
					logger.info("Received:"+update);
					queue.transfer(update);
				}
				
				if (stopNow) break;
				int updatesSize=telegramUpdates.size();
				if (updatesSize>0){
					updatesOffSet=telegramUpdates.get(updatesSize-1).getUpdate_id()+1;
					bssCoreAPI.setUpdatesOffSet(updatesOffSet);
				}
				
			} catch (CyUrbanbotException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.warn(e.toString());
			}
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.toString());
				break;
			}
		} // end while
		
		
		bssCoreAPI.logOff();
		logger.info(">>> End Main Thread...");
		
	}
	@Override
	public void onUpdateDispatcherStop() {
		// TODO Auto-generated method stub
		logger.info("Stopping...");
		stopNow=true;
	}
	
}
