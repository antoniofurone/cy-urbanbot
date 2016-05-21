package org.cysoft.urbanbot.core.task;

import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Keyboard;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WarnGetTextTask extends WarnTaskAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(WarnGetTextTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		String warn=update.getMessage().getText()==null?"":update.getMessage().getText();
		
		if (warn.equalsIgnoreCase(Keyboard.SELECTION_B)||
			warn.equalsIgnoreCase(Keyboard.BUTTON_BACK)||
			warn.equalsIgnoreCase(Keyboard.BUTTON_BACK_EN)
			){
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.WARN_SHOW_OP_ID, session.getLanguage());
			
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),Keyboard.getNrvb(session.getLanguage()));
			session.getSessionStatus().setId(SessionStatus.WARN_SELOP_STATUS_ID);
			return;
		}
		
		if (!warn.equals("")){
			logger.info("warning text="+warn);
			long categoryId=(long)session.getVariable("categoryId");
			long warnId=CyBssCoreAPI.getInstance().addWarn(warn,categoryId,session.getPersonId());
			session.putVariable("warnId", warnId);
			
			double default_latitude=Double.parseDouble(CyBssCoreAPI.getInstance().getParam("default_latitude").getValue());
			double default_longitude=Double.parseDouble(CyBssCoreAPI.getInstance().getParam("default_longitude").getValue());
			
			CyBssCoreAPI.getInstance().addWarnLoc(warnId, default_latitude, default_longitude);
			
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.WARN_MEDIALOC_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),Keyboard.getB(session.getLanguage()));
			session.getSessionStatus().setId(SessionStatus.WARN_IMGLOC_STATUS_ID);
		}
		else
		{
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.WARN_TEXT_ID, session.getLanguage());
			
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),Keyboard.getB(session.getLanguage()));
			session.getSessionStatus().setId(SessionStatus.WARN_TEXT_STATUS_ID);
		}
		
	}

}
