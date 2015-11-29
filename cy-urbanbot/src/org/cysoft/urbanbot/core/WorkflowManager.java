package org.cysoft.urbanbot.core;


import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.cysoft.urbanbot.core.task.ChangeLanguageTask;
import org.cysoft.urbanbot.core.task.WarnImgLocTask;
import org.cysoft.urbanbot.core.task.WarnGetTextTask;
import org.cysoft.urbanbot.core.task.InvalidCommandTask;
import org.cysoft.urbanbot.core.task.InvalidStatusTask;
import org.cysoft.urbanbot.core.task.WarnSelOpTask;
import org.cysoft.urbanbot.core.task.WarnShowDelTask;
import org.cysoft.urbanbot.core.task.WarnTextTask;
import org.cysoft.urbanbot.core.task.WelcomeTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkflowManager {
	
	private static final Logger logger = LoggerFactory.getLogger(WorkflowManager.class);
	
	private Session session=null;
	public WorkflowManager(Session s){
		this.session=s;
	}

	public void transition(Update update) throws CyUrbanbotException{
		Task task=null;
		
		// Init Status
		if (session.getSessionStatus().getId()==SessionStatus.INIT_STATUS_ID){
			if (update.getMessage().getText().trim().equalsIgnoreCase("/start"))
				task=new WelcomeTask();
			
			if (task==null)
				task=new InvalidCommandTask();
		} 
		// end Init Status
		
		// Menu Status
		if (session.getSessionStatus().getId()==SessionStatus.MAIN_MENU_STATUS_ID){
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null &&
				(update.getMessage().getText().trim().equalsIgnoreCase("/a") ||
				 update.getMessage().getText().trim().equalsIgnoreCase("/t") ||
				 update.getMessage().getText().trim().equalsIgnoreCase("/e") ||
				 update.getMessage().getText().trim().equalsIgnoreCase("/i") ||
				 update.getMessage().getText().trim().equalsIgnoreCase("/m"))
					){
					try {
						TelegramAPI.getInstance().sendMessage("Working in Progress...", session.getId(), update.getMessage().getMessage_id());
					} catch (CyUrbanbotException e) {
						// TODO Auto-generated catch block
						logger.error(e.toString());
					}
					return;
				} // working progress 
				
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null && 
				update.getMessage().getText().trim().equalsIgnoreCase("/l"))
				task=new ChangeLanguageTask();
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null && 
				update.getMessage().getText().trim().equalsIgnoreCase("/s"))
				task=new WarnSelOpTask();
			
			
			if (task==null)
				task=new InvalidCommandTask();
				
		}
		// end Menu Status
	
		if (session.getSessionStatus().getId()==SessionStatus.WARN_SHOWOP_STATUS_ID ||
			session.getSessionStatus().getId()==SessionStatus.WARN_SELOP_STATUS_ID)
			task=new WarnSelOpTask();
		
		if (session.getSessionStatus().getId()==SessionStatus.WARN_TEXT_STATUS_ID)
			task=new WarnGetTextTask();
		
		if (session.getSessionStatus().getId()==SessionStatus.WARN_IMGLOC_STATUS_ID ||
			session.getSessionStatus().getId()==SessionStatus.WARN_IMG_STATUS_ID)
			task=new WarnImgLocTask();
		
		if (session.getSessionStatus().getId()==SessionStatus.WARN_SHOWLIST_STATUS_ID)
			task=new WarnShowDelTask();
		
		if (task==null)
			task=new InvalidStatusTask();
		
		task.exec(update, session);
		
	}
	
	
}
