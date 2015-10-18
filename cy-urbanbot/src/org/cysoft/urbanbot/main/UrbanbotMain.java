package org.cysoft.urbanbot.main;


import java.util.List;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.api.telegram.response.GetUpdatesResponse;
import org.cysoft.urbanbot.common.CyUrbanBotUtility;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.common.ICyUrbanbotConst;
import org.cysoft.urbanbot.core.UpdateDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class UrbanbotMain  implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(UrbanbotMain.class);
	
	private String bssAppId="";
		public String getBssAppId() {
		return bssAppId;
	}
	public void setBssAppId(String bssAppId) {
		this.bssAppId = bssAppId;
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
		urbanBot.setBssAppId(args[0]);
		logger.info("appId="+urbanBot.getBssAppId());
		
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
		
		
		/*
		
		List<Update> telegramUpdates=null;
		try {
			telegramUpdates = telegramAPI.getUpdates(0);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
		for(Update update:telegramUpdates){
			logger.info("Received:"+update);
			
			try {
				
				telegramAPI.sendMessage("Hi "+update.getMessage().getFrom().getFirst_name()+"!", 
						update.getMessage().getChat().getId(), 
						update.getMessage().getMessage_id());
				
			} catch (CyUrbanbotException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		*/
		
		
		logger.info("<<< End Urbanbot...");
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info(">>> Start Main Thread...");
		
		CyBssCoreAPI bssCoreAPI=CyBssCoreAPI.getInstance();
		bssCoreAPI.setAppId(bssAppId);		
		bssCoreAPI.setCoreUrl(bssCoreUrl);
		
		try {
			bssCoreAPI.logOn(bssUserId, bssPwd);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			return;
		}
		
		
		long updatesOffSet=bssCoreAPI.getUpdatesOffSet();
		
		TelegramAPI telegramAPI=TelegramAPI.getInstance();
		telegramAPI.setBotUrl(bssCoreAPI.getParam("bot.url").getValue());
		
		TransferQueue<Update> queue =new LinkedTransferQueue<Update>();
		Thread t=new Thread(new UpdateDispatcher(queue));
		t.start();
		
		
		List<Update> telegramUpdates=null;
		
		while(true){
			
			try {
				telegramUpdates = telegramAPI.getUpdates(updatesOffSet);
				for(Update update:telegramUpdates){
					logger.info("Received:"+update);
					queue.transfer(update);
				}
				
				int updatesSize=telegramUpdates.size();
				if (updatesSize>0){
					bssCoreAPI.setUpdatesOffSet(telegramUpdates.get(updatesSize-1).getUpdate_id()+1);
					updatesOffSet=bssCoreAPI.getUpdatesOffSet();
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
		
		
		/*
					
				telegramAPI.sendMessage("Hi "+update.getMessage().getFrom().getFirst_name()+"!", 
						update.getMessage().getChat().getId(), 
						update.getMessage().getMessage_id());
				
		*/
		
		
		
		
		logger.info(">>> End Main Thread...");
		
	}
	
}
