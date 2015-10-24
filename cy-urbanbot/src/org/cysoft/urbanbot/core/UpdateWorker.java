package org.cysoft.urbanbot.core;

import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.core.model.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateWorker implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(UpdateWorker.class);
	private Update update=null;
	private Session session=null;
	
	public UpdateWorker(Session session,Update update){
		this.update=update;
		this.session=session;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info(">>> Start UpdateWorket Thread...");
		
		WorkflowManager wf=new WorkflowManager(session);
		wf.transition(update);
		
		session.setLocked(false);
		logger.info("<<< Start UpdateWorket Thread...");
	}

}
