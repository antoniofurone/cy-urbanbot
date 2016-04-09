package org.cysoft.urbanbot.core.task;

import java.util.List;

import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.cysoft.bss.core.model.TicketCategory;

public class WarnCategoryTask extends WarnTaskAdapter {

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		String sel=update.getMessage().getText()==null?"":update.getMessage().getText();
		
		if (sel.equalsIgnoreCase("/b")){
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.WARN_SHOW_OP_ID, session.getLanguage());
			
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),BotMessage.B_KEYB);
			session.getSessionStatus().setId(SessionStatus.WARN_SELOP_STATUS_ID);
			return;
		}
		
		long categoryId;
		try {
			categoryId=Long.parseLong(sel);
		}
		catch (NumberFormatException ne){
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_SEL_CATEGORY_ID, 
					session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),BotMessage.B14_KEYB);
			return;
		}
		
		List<TicketCategory> categories=(List<TicketCategory>)session.getVariable("WarnCategories");
		boolean found=false;
		for(TicketCategory category:categories){
			if (category.getId()==categoryId){
				found=true;
				break;
			}
		}
			
		if (!found){
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_SEL_CATEGORY_ID, 
					session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),BotMessage.B14_KEYB);
			return;
		}

		session.putVariable("categoryId", categoryId);
		String message=CyBssCoreAPI.getInstance().
				getMessage(BotMessage.WARN_TEXT_ID, session.getLanguage());
		
		TelegramAPI.getInstance().sendMessage(message, session.getId(), 
				update.getMessage().getMessage_id(),BotMessage.B_KEYB);
		session.getSessionStatus().setId(SessionStatus.WARN_TEXT_STATUS_ID);
	}

}
