package org.cysoft.urbanbot.core.task;

import java.util.List;

import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Location;
import org.cysoft.urbanbot.api.telegram.model.PhotoSize;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.ITask;
import org.cysoft.urbanbot.core.TaskAdapter;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetWarnImgLocTask extends TaskAdapter implements ITask {
	
	private static final Logger logger = LoggerFactory.getLogger(GetWarnImgLocTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		
		if (session.getSessionStatus().getId()==SessionStatus.WARNING_IMGORLOC_STATUS_ID){
			String text=update.getMessage().getText()==null?"":update.getMessage().getText();
			
			if (text.equalsIgnoreCase("/b")){
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WELCOME_MENU, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				session.getSessionStatus().setId(SessionStatus.MAIN_MENU_STATUS_ID);
				return;
			}
			
			Location loc=update.getMessage().getLocation();
			if (loc!=null)
			{
				logger.info("location="+loc);
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.SEND_WARNLOCOK, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				message=CyBssCoreAPI.getInstance().getMessage(BotMessage.SEND_WARNIMG, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				
				session.getSessionStatus().setId(SessionStatus.WARNING_IMG_STATUS_ID);
				
				return;
			}
			
			List<PhotoSize> phs=update.getMessage().getPhoto();
			if (phs!=null && phs.size()!=0) {
				logger.info("photo="+phs);
				
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.SEND_WARNIMGOK, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				
				message=CyBssCoreAPI.getInstance().getMessage(BotMessage.SEND_WARNIMG, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				
				session.getSessionStatus().setId(SessionStatus.WARNING_IMG_STATUS_ID);
				return;
			}
			
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.SEND_WARNIMGORLOC, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			
		} // End WARNING_IMGORLOC_STATUS_ID
		
		
		if (session.getSessionStatus().getId()==SessionStatus.WARNING_IMG_STATUS_ID){
			String text=update.getMessage().getText()==null?"":update.getMessage().getText();
			
			if (text.equalsIgnoreCase("/b")){
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WELCOME_MENU, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				session.getSessionStatus().setId(SessionStatus.MAIN_MENU_STATUS_ID);
				return;
			}
		
			List<PhotoSize> phs=update.getMessage().getPhoto();
			if (phs!=null && phs.size()!=0) {
				logger.info("photo="+phs);
				
				String message=null;
				try {
				message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.SEND_WARNIMGOK, session.getLanguage());
				
					TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				} catch (CyUrbanbotException e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				}
				
				try {
				message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.SEND_WARNIMG, session.getLanguage());
				
					TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				} catch (CyUrbanbotException e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				}
				
				session.getSessionStatus().setId(SessionStatus.WARNING_IMG_STATUS_ID);
				return;
			}
			
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.SEND_WARNIMG, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			
			return;
		
		} // End WARNING_IMG_STATUS_ID
		
		
	}

}
