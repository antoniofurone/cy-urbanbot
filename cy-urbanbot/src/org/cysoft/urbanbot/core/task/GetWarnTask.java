package org.cysoft.urbanbot.core.task;

import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.Task;
import org.cysoft.urbanbot.core.TaskAdapter;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetWarnTask extends TaskAdapter implements Task {
	
	private static final Logger logger = LoggerFactory.getLogger(GetWarnTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		String warn=update.getMessage().getText()==null?"":update.getMessage().getText();
		
		if (warn.equalsIgnoreCase("/b")){
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.WELCOME_MENU, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			session.getSessionStatus().setId(SessionStatus.MAIN_MENU_STATUS_ID);
			return;
		}
		
		if (!warn.equals("")){
			logger.info("warning text="+warn);
			
			long warnId=CyBssCoreAPI.getInstance().addWarn(warn, session.getPersonId());
			session.putVariable("warnId", warnId);
			
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.SEND_WARNIMGORLOC, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
		}
		
		session.getSessionStatus().setId(SessionStatus.WARNING_IMGORLOC_STATUS_ID);
	}

}
