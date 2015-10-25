package org.cysoft.urbanbot.core;

import java.util.List;
import java.util.Locale;

import org.cysoft.bss.core.model.ICyBssConst;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Location;
import org.cysoft.urbanbot.api.telegram.model.PhotoSize;
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
				
		if (session.getSessionStatus().getId()==SessionStatus.MAIN_MENU_STATUS_ID){
			if (update.getMessage().getText().trim().equals("/l")){
				if (session.getLanguage().equals(ICyBssConst.LOCALE_IT))
					session.setLanguage(ICyBssConst.LOCALE_EN);
				else
					session.setLanguage(ICyBssConst.LOCALE_IT);
				
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.WELCOME_MENU, session.getLanguage());
				try {
					TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				} catch (CyUrbanbotException e) {
					// TODO Auto-generated catch block
					logger.error(e.toString());
				}
				return;
			} // /l
			
			if (update.getMessage().getText().trim().equals("/a") ||
				update.getMessage().getText().trim().equals("/t") ||
				update.getMessage().getText().trim().equals("/e") ||
				update.getMessage().getText().trim().equals("/i") ||
				update.getMessage().getText().trim().equals("/p")
				){
				try {
					TelegramAPI.getInstance().sendMessage("Working in Progress...", session.getId(), update.getMessage().getMessage_id());
				} catch (CyUrbanbotException e) {
					// TODO Auto-generated catch block
					logger.error(e.toString());
				}
				return;
			} // working progress 
			
			if (update.getMessage().getText().trim().equals("/s")){
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.SEND_WARNING, session.getLanguage());
				
				try {
					TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				} catch (CyUrbanbotException e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				}
				
				session.getSessionStatus().setId(SessionStatus.WARNING_STATUS_ID);
				return;
			} // end /s
			
			
			// comando non riconosciuto
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.COMMAND_NOT_RECOGNIZED, session.getLanguage());
			try {
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			} catch (CyUrbanbotException e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
			}
			
			return;
			
		} // End Menu Status
		
		
		if (session.getSessionStatus().getId()==SessionStatus.WARNING_STATUS_ID){
			
			String warn=update.getMessage().getText()==null?"":update.getMessage().getText();
			
			if (warn.equals("/b")){
				
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
			
			if (!warn.equals("")){
				
				logger.info("warning text="+warn);
				
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.SEND_WARNIMGORLOC, session.getLanguage());
				
				try {
					TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				} catch (CyUrbanbotException e) {
					// TODO Auto-generated catch block
					logger.error(e.toString());
				}
				
				session.getSessionStatus().setId(SessionStatus.WARNING_IMGORLOC_STATUS_ID);
				return;
				
			} // end warning stutus id
			
			
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.SEND_WARNING, session.getLanguage());
			
			try {
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			}
			
			return;
			
		} // End Warning Status Id
			
		
		if (session.getSessionStatus().getId()==SessionStatus.WARNING_IMGORLOC_STATUS_ID){
			
			String text=update.getMessage().getText()==null?"":update.getMessage().getText();
			
			if (text.equals("/b")){
				
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
			
			Location loc=update.getMessage().getLocation();
			if (loc!=null)
			{
				logger.info("location="+loc);
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.SEND_WARNLOCOK, session.getLanguage());
				
				try {
					TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				} catch (CyUrbanbotException e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				}
				
				message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.SEND_WARNIMG, session.getLanguage());
				
				try {
					TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				} catch (CyUrbanbotException e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				}
				
				session.getSessionStatus().setId(SessionStatus.WARNING_IMG_STATUS_ID);
				
				return;
			}
			
			List<PhotoSize> phs=update.getMessage().getPhoto();
			if (phs!=null && phs.size()!=0) {
				logger.info("photo="+phs);
				
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.SEND_WARNIMGOK, session.getLanguage());
				
				try {
					TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				} catch (CyUrbanbotException e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				}
				
				message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.SEND_WARNIMG, session.getLanguage());
				
				try {
					TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				} catch (CyUrbanbotException e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				}
				
				
				session.getSessionStatus().setId(SessionStatus.WARNING_IMG_STATUS_ID);
				return;
			}
			
			
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.SEND_WARNIMGORLOC, session.getLanguage());
			
			try {
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			} catch (CyUrbanbotException e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
			}
			
			return;
			
		} // End ImgLoc Staus
	
		
		if (session.getSessionStatus().getId()==SessionStatus.WARNING_IMG_STATUS_ID){
			String text=update.getMessage().getText()==null?"":update.getMessage().getText();
			
			if (text.equals("/b")){
				
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
		
			
			List<PhotoSize> phs=update.getMessage().getPhoto();
			if (phs!=null && phs.size()!=0) {
				logger.info("photo="+phs);
				
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.SEND_WARNIMGOK, session.getLanguage());
				
				try {
					TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				} catch (CyUrbanbotException e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				}
				
				message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.SEND_WARNIMG, session.getLanguage());
				
				try {
					TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				} catch (CyUrbanbotException e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				}
				
				
				session.getSessionStatus().setId(SessionStatus.WARNING_IMG_STATUS_ID);
				return;
			}
			
			
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.SEND_WARNIMG, session.getLanguage());
			
			try {
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			} catch (CyUrbanbotException e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
			}
			
			return;
		
		} // End Img Status
		
		
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
