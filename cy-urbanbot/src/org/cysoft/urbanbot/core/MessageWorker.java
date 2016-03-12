package org.cysoft.urbanbot.core;

import java.util.ArrayList;
import java.util.List;

import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.model.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageWorker implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(MessageWorker.class);
	private Update update=null;
	private Session session=null;
	
	private List<MessageWorkerListener> listeners=new ArrayList<MessageWorkerListener>();
	public void addUpdateWorkerListener(MessageWorkerListener l){
		listeners.add(l);
	}
	public void removeUpdateWorkerListener(MessageWorkerListener l){
		listeners.remove(l);
	}
	
	
	public MessageWorker(Session session,Update update){
		this.update=update;
		this.session=session;
	}
	
	private synchronized void doError(){
		logger.info("doError() >>>");
		session.setLocked(false);
		if (!listeners.isEmpty())
			for(MessageWorkerListener l:listeners)
				l.onUpdateWorkerError(session, update);
		
		try {
			TelegramAPI.getInstance().sendMessage("Sorry. Errror is verified \uD83D\uDE15", 
					session.getId(), update.getMessage().getMessage_id());
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("doError() <<<");
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info(">>> UpdateWorker Thread...");
		
		MessageWorkflowManager wf=new MessageWorkflowManager(session);
		try {
			wf.transition(update);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			doError();
		}
		
		session.setLocked(false);
		logger.info("<<< UpdateWorker Thread...");
	}

}
