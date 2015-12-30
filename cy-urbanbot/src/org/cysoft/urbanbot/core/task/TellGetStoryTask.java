package org.cysoft.urbanbot.core.task;

import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Location;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.Task;
import org.cysoft.urbanbot.core.TaskAdapter;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TellGetStoryTask extends TaskAdapter implements Task{

	private static final Logger logger = LoggerFactory.getLogger(TellGetStoryTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		
		String text=update.getMessage().getText()==null?"":update.getMessage().getText();
		if (text.equalsIgnoreCase("/b")){
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.TELL_SHOW_OP_ID, session.getLanguage());
			
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			session.getSessionStatus().setId(SessionStatus.TELL_SELOP_STATUS_ID);
			return;
		}
		
		
		if (session.getSessionStatus().getId()==SessionStatus.TELL_GETLOC_STATUS_ID){
			
			Location loc=update.getMessage().getLocation();
			if (loc!=null)
			{
				logger.info("location="+loc);
				session.putVariable("storyLoc", loc);
				
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.TELL_TEXT_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				session.getSessionStatus().setId(SessionStatus.TELL_GETTEXT_STATUS_ID);
			}
			else
			{
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.TELL_LOC_ID, session.getLanguage());
				
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				session.getSessionStatus().setId(SessionStatus.TELL_GETLOC_STATUS_ID);
			}
			
			return;
		}
		
		if (session.getSessionStatus().getId()==SessionStatus.TELL_GETTEXT_STATUS_ID){
		
			Location loc=(Location)session.getVariable("storyLoc");
			long storyId=CyBssCoreAPI.getInstance().addStory(loc.getLatitude(), loc.getLongitude(), text, session.getPersonId()); 
			session.putVariable("storyId", storyId);
			
			
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.TELL_TEXT_OK_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			
			message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.TELL_MEDIA_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			
			
			session.getSessionStatus().setId(SessionStatus.TELL_GETMEDIA_STATUS_ID);
		}
		
		if (session.getSessionStatus().getId()==SessionStatus.TELL_GETMEDIA_STATUS_ID){
			//...
			
		}
			
		
	}

}
