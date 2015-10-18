package org.cysoft.urbanbot.core;

import java.util.Calendar;
import java.util.HashSet;
import java.util.concurrent.TransferQueue;

import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.main.UrbanbotMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateDispatcher implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(UpdateDispatcher.class);
	
	
	TransferQueue<Update> updateQueue = null;
	public UpdateDispatcher(TransferQueue<Update> updateQueue){
		this.updateQueue=updateQueue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info(">>> Start Dispatcher Thread...");
		
		while (true){
			try {
				Update update=updateQueue.take();
				Session session=null;
				
				long chatId=update.getMessage().getChat().getId();
				
				if (!LiveSessions.getInstance().containsKey(chatId)){
					session=new Session(chatId);
				    logger.info("new session ="+session);
				    session.setLocked(true);
					LiveSessions.getInstance().add(session);		
				}
				else
				{
					session=LiveSessions.getInstance().get(chatId);
					if (session.isLocked()){
						logger.info("locked:"+session);
						continue;
					}
					else
					{
						logger.info("live ="+session);
					    session.setLocked(true);
					    session.setLastUseTime(Calendar.getInstance().getTimeInMillis());
					}
						
				}
				// Instance and start Worker
				UpdateWorker worker=new UpdateWorker(session,update);
				Thread t=new Thread(worker);
				t.start();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				break;
			}
				
		}
		logger.info(">>> Stop Dispatcher Thread...");
	}

}
