package org.cysoft.urbanbot.core.task;

import java.util.List;

import org.cysoft.bss.core.model.Location;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StorySelOpTask extends StoryTaskAdapter {

	private static final Logger logger = LoggerFactory.getLogger(StorySelOpTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		
		if (session.getSessionStatus().getId()==SessionStatus.STORY_SHOWOP_STATUS_ID || 
			session.getSessionStatus().getId()==SessionStatus.MAIN_MENU_STATUS_ID){
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.STORY_SHOW_OP_ID, session.getLanguage());
			
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),BotMessage.NRVB_KEYB);
			session.getSessionStatus().setId(SessionStatus.STORY_SELOP_STATUS_ID);
			return;
		}
		
		if (session.getSessionStatus().getId()==SessionStatus.STORY_SEARCH_STATUS_ID){
			String text=update.getMessage().getText().trim();
			if (text.equalsIgnoreCase("/b")){
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.STORY_SHOW_OP_ID, session.getLanguage());
				
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.NRVB_KEYB);
				
				session.getSessionStatus().setId(SessionStatus.STORY_SELOP_STATUS_ID);
				return;
			}
			
			if (text.equalsIgnoreCase("!"))
				text="";
			else
				text="%"+text+"%";
			List<Location> locs=CyBssCoreAPI.getInstance().findStories(0,text,session.getLanguage(),1,STORY_CACHE_SIZE);
			logger.info("locs.size()="+locs.size());
			
			String messageList="";
			if (locs.isEmpty()){
				messageList+=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.STORY_NO_STORY_ID, session.getLanguage());
				
				TelegramAPI.getInstance().sendMessage(messageList, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.B_KEYB);
			}
			else
			{
				session.setCachedItems(locs);
				session.setCachedItemsOffset(STORY_NUM_SHOW*-1);
				session.setIsSearch(true);
				
				ListOptionMsgKb msgKb=this.getListMsgKb(session, true);
				TelegramAPI.getInstance().sendMessage(msgKb.getMessage(), session.getId(), 
						update.getMessage().getMessage_id(),msgKb.getKeyboard());
				
				}
			
			session.getSessionStatus().setId(SessionStatus.STORY_SHOWLIST_STATUS_ID);
			return;
		}

		
		
		if (session.getSessionStatus().getId()==SessionStatus.STORY_SELOP_STATUS_ID){
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null &&
					update.getMessage().getText().trim().equalsIgnoreCase("/r")){
					
					String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.STORY_GET_SEARCH_ID, session.getLanguage());
					
					TelegramAPI.getInstance().sendMessage(message, session.getId(), 
							update.getMessage().getMessage_id(),BotMessage.B_KEYB);
					session.getSessionStatus().setId(SessionStatus.STORY_SEARCH_STATUS_ID);
					return;
				}
			
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null &&
				update.getMessage().getText().trim().equalsIgnoreCase("/v")){
					
				List<Location> locs=CyBssCoreAPI.getInstance().findStories(session.getPersonId(),"",
						session.getLanguage(),1,STORY_CACHE_SIZE);
				logger.info("stories.size()="+locs.size());
				
				String messageList="";
				if (locs.isEmpty()){
					messageList+=CyBssCoreAPI.getInstance().
							getMessage(BotMessage.STORY_NO_STORY_ID, session.getLanguage());
					
					TelegramAPI.getInstance().sendMessage(messageList, session.getId(), 
							update.getMessage().getMessage_id(),BotMessage.B_KEYB);
				}
				else
				{
					session.setCachedItems(locs);
					session.setCachedItemsOffset(STORY_NUM_SHOW*-1);
					session.setIsSearch(false);
					
					ListOptionMsgKb msgKb=this.getListMsgKb(session, true);
					TelegramAPI.getInstance().sendMessage(msgKb.getMessage(), session.getId(), 
							update.getMessage().getMessage_id(),msgKb.getKeyboard());
					
					}
				
				session.getSessionStatus().setId(SessionStatus.STORY_SHOWLIST_STATUS_ID);
				return;
			} 
				
			if (update.getMessage()!=null && update.getMessage().getText()!=null &&
					update.getMessage().getText().trim().equalsIgnoreCase("/n")){
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.STORY_LOC_ID, session.getLanguage());
				
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.B_KEYB);
				session.getSessionStatus().setId(SessionStatus.STORY_GETLOC_STATUS_ID);
				return;
			}
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null &&
					update.getMessage().getText().trim().equalsIgnoreCase("/b")){
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.WELCOME_MENU_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.WELCOME_MENU_KEYB);
				session.getSessionStatus().setId(SessionStatus.MAIN_MENU_STATUS_ID);
				return;
			}
			
		}
	}

}
