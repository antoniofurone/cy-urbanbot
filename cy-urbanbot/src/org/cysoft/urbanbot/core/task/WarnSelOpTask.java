package org.cysoft.urbanbot.core.task;


import java.util.List;

import org.cysoft.bss.core.model.Ticket;
import org.cysoft.bss.core.model.TicketCategory;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WarnSelOpTask extends WarnTaskAdapter {

	private static final Logger logger = LoggerFactory.getLogger(WarnSelOpTask.class);
	
	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		
		if (session.getSessionStatus().getId()==SessionStatus.WARN_SHOWOP_STATUS_ID || 
			session.getSessionStatus().getId()==SessionStatus.MAIN_MENU_STATUS_ID){
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.WARN_SHOW_OP_ID, session.getLanguage());
			
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),BotMessage.NRVB_KEYB);
			
			session.getSessionStatus().setId(SessionStatus.WARN_SELOP_STATUS_ID);
			return;
		}
		
		if (session.getSessionStatus().getId()==SessionStatus.WARN_SEARCH_STATUS_ID){
			String text=update.getMessage().getText().trim();
			if (text.equalsIgnoreCase("/b")){
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.WARN_SHOW_OP_ID, session.getLanguage());
				
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.NRVB_KEYB);
				
				session.getSessionStatus().setId(SessionStatus.WARN_SELOP_STATUS_ID);
				return;
			}
			
			if (text.equalsIgnoreCase("!"))
				text="";
			else
				text="%"+text+"%";
			List<Ticket> warns=CyBssCoreAPI.getInstance().findWarns(0,text,
					session.getLanguage(),1,WARN_CACHE_SIZE);
			logger.info("warns.size()="+warns.size());
			
			String messageList="";
			if (warns.isEmpty()){
				messageList+=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.WARN_NO_WARN_ID, session.getLanguage());
				
				TelegramAPI.getInstance().sendMessage(messageList, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.B_KEYB);
			}
			else
			{
				session.setCachedItems(warns);
				session.setCachedItemsOffset(WARN_NUM_SHOW*-1);
				session.setIsSearch(true);
				
				ListOptionMsgKb msgKb=this.getListMsgKb(session, true);
				TelegramAPI.getInstance().sendMessage(msgKb.getMessage(), session.getId(), 
						update.getMessage().getMessage_id(),msgKb.getKeyboard());
				
				}
			
			session.getSessionStatus().setId(SessionStatus.WARN_SHOWLIST_STATUS_ID);
			return;
		}
			
		
		if (session.getSessionStatus().getId()==SessionStatus.WARN_SELOP_STATUS_ID){
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null &&
				update.getMessage().getText().trim().equalsIgnoreCase("/r")){
				
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_GET_SEARCH_ID, session.getLanguage());
				
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.B_KEYB);
				session.getSessionStatus().setId(SessionStatus.WARN_SEARCH_STATUS_ID);
				return;
			}
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null &&
				update.getMessage().getText().trim().equalsIgnoreCase("/v")){
						
					List<Ticket> warns=CyBssCoreAPI.getInstance().findWarns(session.getPersonId(),"",
							session.getLanguage(),1,WARN_CACHE_SIZE);
					logger.info("warns.size()="+warns.size());
					
					String messageList="";
					if (warns.isEmpty()){
						messageList+=CyBssCoreAPI.getInstance().
								getMessage(BotMessage.WARN_NO_WARN_ID, session.getLanguage());
						
						TelegramAPI.getInstance().sendMessage(messageList, session.getId(), 
								update.getMessage().getMessage_id(),BotMessage.B_KEYB);
					}
					else
					{
						session.setCachedItems(warns);
						session.setCachedItemsOffset(WARN_NUM_SHOW*-1);
						session.setIsSearch(false);
						
						ListOptionMsgKb msgKb=this.getListMsgKb(session, true);
						TelegramAPI.getInstance().sendMessage(msgKb.getMessage(), session.getId(), 
								update.getMessage().getMessage_id(),msgKb.getKeyboard());
						
						}
					
					session.getSessionStatus().setId(SessionStatus.WARN_SHOWLIST_STATUS_ID);
					return;
				} 
				
			if (update.getMessage()!=null && update.getMessage().getText()!=null &&
					update.getMessage().getText().trim().equalsIgnoreCase("/n")){
				List<TicketCategory> categories=CyBssCoreAPI.getInstance().getWarnCategories(session.getLanguage());
				String categoriesMessage="";
				for (TicketCategory category:categories)
					categoriesMessage+=category.getId()+" "+category.getName()+"\n";
				categoriesMessage+=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.WARN_SEL_CATEGORY_ID, session.getLanguage());
				
				session.putVariable("WarnCategories", categories);
				
				TelegramAPI.getInstance().sendMessage(categoriesMessage, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.B14_KEYB);
				session.getSessionStatus().setId(SessionStatus.WARN_CATEGORY_STATUS_ID);
				return;
			}
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null &&
					update.getMessage().getText().trim().equalsIgnoreCase("/b")){
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.WELCOME_MENU_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),
						BotMessage.WELCOME_MENU_KEYB);
				session.getSessionStatus().setId(SessionStatus.MAIN_MENU_STATUS_ID);
				return;
			}
			
		}
	}

}
