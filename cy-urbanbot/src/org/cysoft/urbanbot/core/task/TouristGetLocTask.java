package org.cysoft.urbanbot.core.task;


import java.util.ArrayList;
import java.util.List;

import org.cysoft.bss.core.model.Location;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.Task;
import org.cysoft.urbanbot.core.TaskAdapter;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.LocDistance;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TouristGetLocTask extends TaskAdapter implements Task{

	private static final Logger logger = LoggerFactory.getLogger(TouristGetLocTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
	
		if (session.getSessionStatus().getId()==SessionStatus.MAIN_MENU_STATUS_ID){
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.TOURIST_LOC_ID, session.getLanguage());
			
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			session.getSessionStatus().setId(SessionStatus.TOURIST_GETLOC_STATUS_ID);
			return;
		}
		
		if (session.getSessionStatus().getId()==SessionStatus.TOURIST_GETLOC_STATUS_ID){
			org.cysoft.urbanbot.api.telegram.model.Location tlLoc=update.getMessage().getLocation();
			if (tlLoc!=null)
			{
				logger.info("location="+tlLoc);
				
				List<Location> locs=CyBssCoreAPI.getInstance().findTouristSites(session.getLanguage());
				String messageList="";
				if (locs.isEmpty())
					messageList+=CyBssCoreAPI.getInstance().
							getMessage(BotMessage.TOURIST_NO_SITE_ID, session.getLanguage());
				else
				{
					
					List<LocDistance> locDists=new ArrayList<LocDistance>();
					for(Location loc:locs)
						locDists.add(new LocDistance(loc,tlLoc.getLatitude(),tlLoc.getLongitude()));
					
					messageList+=CyBssCoreAPI.getInstance().
							getMessage(BotMessage.TOURIST_LIST_ID, session.getLanguage())+"\n";
					for(LocDistance locDist:locDists)
						messageList+=locDist.getLocation().getId()+" -> "+locDist.getLocation().getName()+" @ "+(int)locDist.getDistance()+" mt;\n";
					
					messageList+=CyBssCoreAPI.getInstance().
							getMessage(BotMessage.TOURIST_LIST_OP_ID, session.getLanguage());
				}
					
				TelegramAPI.getInstance().sendMessage(messageList, session.getId(), update.getMessage().getMessage_id());
				session.getSessionStatus().setId(SessionStatus.TOURIST_SELLOC_STATUS_ID);
			}
			else
			{
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.TOURIST_LOC_ID, session.getLanguage());
				
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				session.getSessionStatus().setId(SessionStatus.TOURIST_GETLOC_STATUS_ID);
			}
			
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
