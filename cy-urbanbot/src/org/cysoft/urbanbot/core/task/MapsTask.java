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

public class MapsTask extends TaskAdapter implements Task {

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		String message=CyBssCoreAPI.getInstance().
				getMessage(BotMessage.MAP_URLS_ID, session.getLanguage());
		TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id(),TelegramAPI.MESSAGE_PARSEMODE_HTML);
		session.getSessionStatus().setId(SessionStatus.MAIN_MENU_STATUS_ID);
	}

}
