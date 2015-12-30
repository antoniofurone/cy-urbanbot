package org.cysoft.urbanbot.core.task;

import java.util.List;

import org.cysoft.bss.core.model.Ticket;
import org.cysoft.bss.core.model.TicketCategory;
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

public class TellSelOpTask extends TaskAdapter implements Task {

	private static final Logger logger = LoggerFactory.getLogger(TellSelOpTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		
		if (session.getSessionStatus().getId()==SessionStatus.TELL_SHOWOP_STATUS_ID || 
			session.getSessionStatus().getId()==SessionStatus.MAIN_MENU_STATUS_ID){
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.TELL_SHOW_OP_ID, session.getLanguage());
			
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			session.getSessionStatus().setId(SessionStatus.TELL_SELOP_STATUS_ID);
			return;
		}
		
		if (session.getSessionStatus().getId()==SessionStatus.TELL_SELOP_STATUS_ID){
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null &&
				update.getMessage().getText().trim().equalsIgnoreCase("/v")){
						/*
						List<Ticket> warns=CyBssCoreAPI.getInstance().findWarns(session.getPersonId(),session.getLanguage());
						String messageList="";
						if (warns.isEmpty())
							messageList+=CyBssCoreAPI.getInstance().
									getMessage(BotMessage.WARN_NO_WARN_ID, session.getLanguage());
						else
						{
							messageList+=CyBssCoreAPI.getInstance().
									getMessage(BotMessage.WARN_LIST_ID, session.getLanguage())+"\n";
							for(Ticket warn:warns)
								messageList+=warn.getId()+" @ "+warn.getCreationDate()+";\n";
							messageList+=CyBssCoreAPI.getInstance().
									getMessage(BotMessage.WARN_LIST_OP_ID, session.getLanguage());
						}
						
						
						TelegramAPI.getInstance().sendMessage(messageList, session.getId(), update.getMessage().getMessage_id());
						session.getSessionStatus().setId(SessionStatus.WARN_SHOWLIST_STATUS_ID);
						*/
						return;
					} 
				
			if (update.getMessage()!=null && update.getMessage().getText()!=null &&
					update.getMessage().getText().trim().equalsIgnoreCase("/n")){
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.TELL_LOC_ID, session.getLanguage());
				
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				session.getSessionStatus().setId(SessionStatus.TELL_GETLOC_STATUS_ID);
				return;
			}
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null &&
					update.getMessage().getText().trim().equalsIgnoreCase("/b")){
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.WELCOME_MENU_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				session.getSessionStatus().setId(SessionStatus.MAIN_MENU_STATUS_ID);
				return;
			}
			
		}
	}

}
