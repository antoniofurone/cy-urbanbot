package org.cysoft.urbanbot.core;


import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.model.Keyboard;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.cysoft.urbanbot.core.task.ChangeLanguageTask;
import org.cysoft.urbanbot.core.task.EventSelOpTask;
import org.cysoft.urbanbot.core.task.EventShowTask;
import org.cysoft.urbanbot.core.task.MapsTask;
import org.cysoft.urbanbot.core.task.StoryGetTask;
import org.cysoft.urbanbot.core.task.StorySelOpTask;
import org.cysoft.urbanbot.core.task.StoryShowDelTask;
import org.cysoft.urbanbot.core.task.TouristGetLocTask;
import org.cysoft.urbanbot.core.task.TouristShowTask;
import org.cysoft.urbanbot.core.task.WarnCategoryTask;
import org.cysoft.urbanbot.core.task.WarnMediaLocTask;
import org.cysoft.urbanbot.core.task.WarnGetTextTask;
import org.cysoft.urbanbot.core.task.InvalidCommandTask;
import org.cysoft.urbanbot.core.task.InvalidStatusTask;
import org.cysoft.urbanbot.core.task.WarnSelOpTask;
import org.cysoft.urbanbot.core.task.WarnShowDelTask;
import org.cysoft.urbanbot.core.task.WelcomeTask;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class MessageWorkflowManager {
	
	//private static final Logger logger = LoggerFactory.getLogger(WorkflowManager.class);
	
	private Session session=null;
	public MessageWorkflowManager(Session s){
		this.session=s;
	}

	public void transition(Update update) throws CyUrbanbotException{
		Task task=null;
	
		String textofMsg=update.getMessage().getText()==null?"":update.getMessage().getText();
		if (textofMsg.trim().equalsIgnoreCase(Keyboard.SELECTION_START)){
			task=new WelcomeTask();

			task.exec(update, session);
			return;
		}
			
		
		// Init Status
		if (session.getSessionStatus().getId()==SessionStatus.INIT_STATUS_ID){
			if (textofMsg.trim().trim().equalsIgnoreCase(Keyboard.SELECTION_START))
				task=new WelcomeTask();
			
			if (task==null)
				task=new InvalidCommandTask();
		} 
		// end Init Status
		
		// Menu Status
		if (session.getSessionStatus().getId()==SessionStatus.MAIN_MENU_STATUS_ID){
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null && 
					(
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.SELECTION_L) ||
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.BUTTON_CHANGE_LANGUAGE) ||
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.BUTTON_CHANGE_LANGUAGE_EN)
					)
				)
				task=new ChangeLanguageTask();
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null && 
					(
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.SELECTION_S) ||
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.BUTTON_WARNING) ||
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.BUTTON_WARNING_EN)
					)
				)
				task=new WarnSelOpTask();
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null && 
					(
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.SELECTION_N) ||
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.BUTTON_STORY) ||
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.BUTTON_STORY_EN)
					)
				)
				task=new StorySelOpTask();
	
			if (update.getMessage()!=null && update.getMessage().getText()!=null && 
					(
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.SELECTION_E) ||
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.BUTTON_EVENT) ||
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.BUTTON_EVENT_EN)
					)
				)
				task=new EventSelOpTask();
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null && 
					(
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.SELECTION_T) ||
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.BUTTON_SITE) ||
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.BUTTON_SITE_EN)
					)
				)
				task=new TouristGetLocTask();
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null && 
				(
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.SELECTION_M) ||
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.BUTTON_MAP) ||
						update.getMessage().getText().trim().equalsIgnoreCase(Keyboard.BUTTON_MAP_EN)
					)
				)
				task=new MapsTask();
				
			
			if (task==null)
				task=new InvalidCommandTask();
				
		}
		// end Menu Status
	
		// warn transition
		if (session.getSessionStatus().getId()==SessionStatus.WARN_SHOWOP_STATUS_ID ||
			session.getSessionStatus().getId()==SessionStatus.WARN_SELOP_STATUS_ID ||
			session.getSessionStatus().getId()==SessionStatus.WARN_SEARCH_STATUS_ID)
			task=new WarnSelOpTask();
		
		if (session.getSessionStatus().getId()==SessionStatus.WARN_CATEGORY_STATUS_ID)
			task=new WarnCategoryTask();
		
		if (session.getSessionStatus().getId()==SessionStatus.WARN_TEXT_STATUS_ID)
			task=new WarnGetTextTask();
		
		if (session.getSessionStatus().getId()==SessionStatus.WARN_IMGLOC_STATUS_ID ||
			session.getSessionStatus().getId()==SessionStatus.WARN_IMG_STATUS_ID)
			task=new WarnMediaLocTask();
		
		if (session.getSessionStatus().getId()==SessionStatus.WARN_SHOWLIST_STATUS_ID)
			task=new WarnShowDelTask();
		// end warn transition
		
		// STORY transition
		if (session.getSessionStatus().getId()==SessionStatus.STORY_SHOWOP_STATUS_ID ||
			session.getSessionStatus().getId()==SessionStatus.STORY_SELOP_STATUS_ID ||
			session.getSessionStatus().getId()==SessionStatus.STORY_SEARCH_STATUS_ID)
			task=new StorySelOpTask();
		
		if (session.getSessionStatus().getId()==SessionStatus.STORY_GETLOC_STATUS_ID ||
			session.getSessionStatus().getId()==SessionStatus.STORY_GETTEXT_STATUS_ID ||
			session.getSessionStatus().getId()==SessionStatus.STORY_GETMEDIA_STATUS_ID)
			task=new StoryGetTask();
		
		if (session.getSessionStatus().getId()==SessionStatus.STORY_SHOWLIST_STATUS_ID)
			task=new StoryShowDelTask();
		// end STORY transitionTouristSiteLocation
		
		// EVENT transition
		if (session.getSessionStatus().getId()==SessionStatus.EVENT_SEARCH_STATUS_ID)
			task=new EventSelOpTask();
		
		if (session.getSessionStatus().getId()==SessionStatus.EVENT_SHOWLIST_STATUS_ID)
			task=new EventShowTask();
		// end EVENT 
		
		// tourist site transition
		if (session.getSessionStatus().getId()==SessionStatus.TOURIST_GETLOC_STATUS_ID)
			task=new TouristGetLocTask();
		
		if (session.getSessionStatus().getId()==SessionStatus.TOURIST_SELLOC_STATUS_ID)
			task=new TouristShowTask();
		// end tourist site transition
		
		
		if (task==null)
			task=new InvalidStatusTask();
		
		task.exec(update, session);
		
	}
	
	
}
