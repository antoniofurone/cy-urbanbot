package org.cysoft.urbanbot.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TransferQueue;

import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.api.telegram.model.User;
import org.cysoft.urbanbot.common.CyUrbanBotUtility;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.common.ICyUrbanbotConst;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateDispatcher implements Runnable, UpdateWorkerListener{
	
	private static final Logger logger = LoggerFactory.getLogger(UpdateDispatcher.class);
	
	
	private TransferQueue<Update> updateQueue = null;
	public UpdateDispatcher(TransferQueue<Update> updateQueue){
		this.updateQueue=updateQueue;
	}

	private List<UpdateDispatcherListener> listeners=new ArrayList<UpdateDispatcherListener>();
	public void addUpdateDispatcherListener(UpdateDispatcherListener l){
		listeners.add(l);
	}
	public void removeUpdateDispatcherListener(UpdateDispatcherListener l){
		listeners.remove(l);
	}
	
	private synchronized void doStop(){
		if (!listeners.isEmpty())
			for(UpdateDispatcherListener l:listeners)
				l.onUpdateDispatcherStop();
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
					
					User user=update.getMessage().getFrom();
					long personId=0;
					
					try {
						personId=CyBssCoreAPI.getInstance().updatePerson("tlg:"+user.getUsername(), 
								user.getFirst_name(), user.getLast_name());
					} catch (CyUrbanbotException e) {
						// TODO Auto-generated catch block
						logger.error(e.toString());
						doStop();
						break;
					}
					session=new Session(chatId);
					session.setPersonId(personId);
					session.setFirstName(user.getFirst_name());
					session.setSecondName(user.getLast_name());
					session.setLocked(true);
					logger.info("new session ="+session);
				    LiveSessions.getInstance().add(session);		
				}
				else
				{
					session=LiveSessions.getInstance().get(chatId);
					if (session.isLocked()){
						logger.info("locked:"+session);
						try {
						String waitMessage=CyBssCoreAPI.getInstance().
								getMessage(BotMessage.WAIT_LOCK_SESSION_ID, session.getLanguage());
							TelegramAPI.getInstance().sendMessage(waitMessage, session.getId(), update.getMessage().getMessage_id());
						} catch (CyUrbanbotException e) {
							// TODO Auto-generated catch block
							logger.error(e.toString());
							doStop();
							break;
						}
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
				doStop();
				break;
			}
				
		}
		logger.info(">>> Stop Dispatcher Thread...");
	}
	@Override
	public void onUpdateWorkerError(Session session,Update update) {
		// TODO Auto-generated method stub
		logger.error("UpdateWorkerError on "+session.toString()+ " at "+update.toString());
	}

}
