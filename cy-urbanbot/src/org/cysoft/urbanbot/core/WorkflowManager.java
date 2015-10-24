package org.cysoft.urbanbot.core;

import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkflowManager {
	
	private static final Logger logger = LoggerFactory.getLogger(WorkflowManager.class);

	
	private Session session=null;
	public WorkflowManager(Session s){
		this.session=s;
	}

	public void transition(Update update){
		if (session.getSessionStatus().getId()==SessionStatus.INIT_STATUS_ID){
			if (update.getMessage().getText().trim().equals("/start")){
				
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.WELCOME_MENU, session.getLanguage());
				try {
					TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				} catch (CyUrbanbotException e) {
					// TODO Auto-generated catch block
					logger.error(e.toString());
				}
				
				session.getSessionStatus().setId(SessionStatus.MAIN_MENU_STATUS_ID);
				return;
			}
			else
			{
				// comando non riconosciuto
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.COMMAND_NOT_RECOGNIZED, session.getLanguage());
				try {
					TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				} catch (CyUrbanbotException e) {
					// TODO Auto-generated catch block
					logger.error(e.toString());
				}
				
				session.getSessionStatus().setId(SessionStatus.INIT_STATUS_ID);
				return;
			}
			
		} // End Init Status
				
		
		
		// Invalid Status
		String message=CyBssCoreAPI.getInstance().
				getMessage(BotMessage.INVALID_SESSION, session.getLanguage());
		
		try {
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
		session.getSessionStatus().setId(SessionStatus.INIT_STATUS_ID);
		
	}
	
	
}
