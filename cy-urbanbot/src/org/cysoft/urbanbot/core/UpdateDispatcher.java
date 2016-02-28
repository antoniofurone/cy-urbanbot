package org.cysoft.urbanbot.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TransferQueue;

import org.cysoft.bss.core.model.ICyBssConst;
import org.cysoft.bss.core.model.Location;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.InlineQueryResult;
import org.cysoft.urbanbot.api.telegram.model.InlineQueryResultArticle;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.api.telegram.model.User;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateDispatcher implements Runnable, MessageWorkerListener{
	
	private static final Logger logger = LoggerFactory.getLogger(UpdateDispatcher.class);
	private static final short ERROR_SESSION_MAX=3;
	
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
		
		short sessionErrorCont=0;
		while (true){
			boolean sessionError=false;
			
			try {
				Update update=updateQueue.take();
				
				if (update.getMessage()!=null){
					// manage message
					Session session=null;
					long chatId=update.getMessage().getChat().getId();
					
					if (!LiveSessions.getInstance().containsKey(chatId)){
						
						User user=update.getMessage().getFrom();
						long personId=0;
						
						try {
							String code=null;
							if (user.getId()!=0)
								code="tlgid:"+user.getId();
							else
								if (user.getUsername()==null || user.getUsername().equals(""))
									code="tlgn:"+(user.getFirst_name()==null?"":user.getFirst_name())+
											(user.getLast_name()==null?"":user.getLast_name());
								else
									code="tlg:"+user.getUsername();
									
							personId=CyBssCoreAPI.getInstance().updatePerson(code, 
									user.getFirst_name(), user.getLast_name());
							
							TelegramAPI.getInstance().sendMessage("Ciao "+user.getFirst_name()+"!"+" \uD83D\uDE00", 
									chatId, update.getMessage().getMessage_id());
							
						} catch (CyUrbanbotException e) {
							// TODO Auto-generated catch block
							logger.error(e.toString());
							sessionError=true;
							sessionErrorCont++;
							if (sessionErrorCont>=ERROR_SESSION_MAX)
								doStop();
							continue;
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
								sessionError=true;
								sessionErrorCont++;
								if (sessionErrorCont>=ERROR_SESSION_MAX)
									doStop();
								
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
					MessageWorker worker=new MessageWorker(session,update);
					Thread t=new Thread(worker);
					t.start();
				} // end manage message
				
				if (update.getInline_query()!=null){
					// manage Inline query
					
					String query=update.getInline_query().getQuery();
					logger.info("Inline_query:"+update.getInline_query().toString());
					if (query==null || query.equals("")){
						// getAll Location
						List<Location> locs=null;
						try {
							locs=CyBssCoreAPI.getInstance().findTouristSites(ICyBssConst.LOCALE_IT);
						} catch (CyUrbanbotException e) {
							// TODO Auto-generated catch block
							logger.error(e.toString());
							sessionError=true;
							sessionErrorCont++;
							if (sessionErrorCont>=ERROR_SESSION_MAX)
								doStop();
							continue;
						}
						List<InlineQueryResult> inLineResults=new ArrayList<InlineQueryResult>();
						for(Location loc:locs){
							InlineQueryResultArticle inRes=new InlineQueryResultArticle();
							inRes.setId(""+loc.getId());
							inRes.setTitle(loc.getName());
							String message="<strong>"+loc.getName()+"</strong>\n";
							message+=loc.getDescription();
							inRes.setMessage_text(message);
							inRes.setParse_mode(TelegramAPI.MESSAGE_PARSEMODE_HTML);
							
							inLineResults.add(inRes);
						}
						
						try {
							TelegramAPI.getInstance().answerInlineQuery(update.getInline_query().getId(), inLineResults);
						} catch (CyUrbanbotException e) {
							// TODO Auto-generated catch block
							logger.error(e.toString());
							sessionError=true;
							sessionErrorCont++;
							if (sessionErrorCont>=ERROR_SESSION_MAX)
								doStop();
							continue;
						}
						
					}
					
				}
				
				if (update.getChosen_inline_result()!=null){
					
					logger.info("Chosen_inline_result:"+update.getChosen_inline_result());
					
					
				}
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				doStop();
				break;
			}
				
		if (!sessionError)
			sessionErrorCont=0;
		} // end while
		
		logger.info(">>> Stop Dispatcher Thread...");
	}
	@Override
	public void onUpdateWorkerError(Session session,Update update) {
		// TODO Auto-generated method stub
		logger.error("UpdateWorkerError on "+session.toString()+ " at "+update.toString());
	}

}
