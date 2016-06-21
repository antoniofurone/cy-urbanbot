package org.cysoft.urbanbot.core.task;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.cysoft.bss.core.model.Location;
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

public class EventSelOpTask extends EventTaskAdapter {

	private static final Logger logger = LoggerFactory.getLogger(EventSelOpTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		
		if (session.getSessionStatus().getId()==SessionStatus.MAIN_MENU_STATUS_ID){
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.EVENT_GET_SEARCH_ID, session.getLanguage());
			
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),Keyboard.getB(session.getLanguage()));
			session.getSessionStatus().setId(SessionStatus.EVENT_SEARCH_STATUS_ID);
			return;
		}
		
		if (session.getSessionStatus().getId()==SessionStatus.EVENT_SEARCH_STATUS_ID){
			if (update.getMessage().getText()==null)
				return;
			String text=update.getMessage().getText().trim();
			
			if (
				text.equalsIgnoreCase(Keyboard.SELECTION_B)||
				text.equalsIgnoreCase(Keyboard.BUTTON_BACK)||
				text.equalsIgnoreCase(Keyboard.BUTTON_BACK_EN)
				){
			
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.WELCOME_MENU_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),Keyboard.getWelcome(session.getLanguage()));
				session.getSessionStatus().setId(SessionStatus.MAIN_MENU_STATUS_ID);
				
				return;
			}
			
			if (text.equalsIgnoreCase("!"))
				text="";
			else
				text="%"+text+"%";
			
			List<Location> locs=null;
			if (!text.equals(""))
				locs=CyBssCoreAPI.getInstance().findEvents(text,"",session.getLanguage(),1,EVENT_CACHE_SIZE);
			else
				locs=CyBssCoreAPI.getInstance().findEvents("","",session.getLanguage(),1,EVENT_CACHE_SIZE);
				
			if (!text.equals("")){
				// find using description
				Map <Long,String> hMap=new LinkedHashMap<Long,String>();
				for(Location loc:locs)
					hMap.put(loc.getId(), loc.getName());
				
				List<Location> locsDesc=CyBssCoreAPI.getInstance().findEvents("",text,session.getLanguage(),1,EVENT_CACHE_SIZE);
				for(Location loc:locsDesc){
					if (!hMap.containsKey(loc.getId()))
					locs.add(loc);
					if (locs.size()>=EVENT_CACHE_SIZE)
						break;
				}
			}
			
			logger.info("locs.size()="+locs.size());
			
			String messageList="";
			if (locs.isEmpty()){
				messageList+=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.EVENT_NO_EVENT_ID, session.getLanguage());
				
				TelegramAPI.getInstance().sendMessage(messageList, session.getId(), 
						update.getMessage().getMessage_id(),Keyboard.getB(session.getLanguage()));
				
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.EVENT_GET_SEARCH_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),Keyboard.getB(session.getLanguage()));
				session.getSessionStatus().setId(SessionStatus.EVENT_SEARCH_STATUS_ID);
				return;
			
			}
			else
			{
				session.setCachedItems(locs);
				session.setCachedItemsOffset(EVENT_NUM_SHOW*-1);
				session.setIsSearch(true);
				
				ListOptionMsgKb msgKb=this.getListMsgKb(session, true);
				TelegramAPI.getInstance().sendMessage(msgKb.getMessage(), session.getId(), 
						update.getMessage().getMessage_id(),msgKb.getKeyboard());
				
				session.getSessionStatus().setId(SessionStatus.EVENT_SHOWLIST_STATUS_ID);
				return;
			}
			
		}

	}

}
