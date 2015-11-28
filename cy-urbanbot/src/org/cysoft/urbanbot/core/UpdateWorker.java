package org.cysoft.urbanbot.core;

import java.util.ArrayList;
import java.util.List;

import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.model.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateWorker implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(UpdateWorker.class);
	private Update update=null;
	private Session session=null;
	
	private List<UpdateWorkerListener> listeners=new ArrayList<UpdateWorkerListener>();
	public void addUpdateWorkerListener(UpdateWorkerListener l){
		listeners.add(l);
	}
	public void removeUpdateWorkerListener(UpdateWorkerListener l){
		listeners.remove(l);
	}
	
	
	public UpdateWorker(Session session,Update update){
		this.update=update;
		this.session=session;
	}
	
	private synchronized void doError(){
		session.setLocked(false);
		if (!listeners.isEmpty())
			for(UpdateWorkerListener l:listeners)
				l.onUpdateWorkerError(session, update);
		
		try {
			TelegramAPI.getInstance().sendMessage("Sorry. Errror is verified :-(", 
					session.getId(), update.getMessage().getMessage_id());
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info(">>> Start UpdateWorker Thread...");
		
		WorkflowManager wf=new WorkflowManager(session);
		try {
			wf.transition(update);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			doError();
		}
		
		session.setLocked(false);
		logger.info("<<< Start UpdateWorker Thread...");
	}

}
