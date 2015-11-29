package org.cysoft.urbanbot.core.task;

import org.cysoft.bss.core.model.ICyBssConst;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.Task;
import org.cysoft.urbanbot.core.TaskAdapter;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;

public class ChangeLanguageTask extends TaskAdapter implements Task {

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		if (session.getLanguage().equals(ICyBssConst.LOCALE_IT))
			session.setLanguage(ICyBssConst.LOCALE_EN);
		else
			session.setLanguage(ICyBssConst.LOCALE_IT);
		String message=CyBssCoreAPI.getInstance().
				getMessage(BotMessage.WELCOME_MENU_ID, session.getLanguage());
		
		TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
	}

}
