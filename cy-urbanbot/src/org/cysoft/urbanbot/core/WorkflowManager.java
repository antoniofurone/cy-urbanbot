package org.cysoft.urbanbot.core;


import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.cysoft.urbanbot.core.task.ChangeLanguageTask;
import org.cysoft.urbanbot.core.task.GetWarnImgLocTask;
import org.cysoft.urbanbot.core.task.GetWarnTask;
import org.cysoft.urbanbot.core.task.InvalidCommandTask;
import org.cysoft.urbanbot.core.task.InvalidStatusTask;
import org.cysoft.urbanbot.core.task.SendWarnTask;
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
			
			if (update.getMessage().getText().trim().equalsIgnoreCase("/a") ||
				update.getMessage().getText().trim().equalsIgnoreCase("/t") ||
				update.getMessage().getText().trim().equalsIgnoreCase("/e") ||
				update.getMessage().getText().trim().equalsIgnoreCase("/i") ||
				update.getMessage().getText().trim().equalsIgnoreCase("/p")
					){
					try {
						TelegramAPI.getInstance().sendMessage("Working in Progress...", session.getId(), update.getMessage().getMessage_id());
					} catch (CyUrbanbotException e) {
						// TODO Auto-generated catch block
						logger.error(e.toString());
					}
					return;
				} // working progress 
				
			
			if (update.getMessage().getText().trim().equalsIgnoreCase("/l"))
				task=new ChangeLanguageTask();
			
			if (update.getMessage().getText().trim().equalsIgnoreCase("/s"))
				task=new SendWarnTask();
			
			
			if (task==null)
				task=new InvalidCommandTask();
				
		}
		// end Menu Status
	
		// Warning Status
		if (session.getSessionStatus().getId()==SessionStatus.WARNING_STATUS_ID)
			task=new GetWarnTask();
		// end Warning Status
		
		// Warn Img or Loc Status
		if (session.getSessionStatus().getId()==SessionStatus.WARNING_IMGORLOC_STATUS_ID ||
			session.getSessionStatus().getId()==SessionStatus.WARNING_IMG_STATUS_ID)
			task=new GetWarnImgLocTask();
		// end Warn Img or Loc Status
		
		
		if (task==null)
			task=new InvalidStatusTask();
		
		task.exec(update, session);
		
		
	}
	
	
}
