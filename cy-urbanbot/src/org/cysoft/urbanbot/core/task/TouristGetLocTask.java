package org.cysoft.urbanbot.core.task;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.cysoft.bss.core.model.Location;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.google.model.GeoLocation;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanBotUtility;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.LocDistance;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.cysoft.urbanbot.core.task.StoryTaskAdapter.ListOptionMsgKb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class TouristGetLocTask extends TouristTaskAdapter{

	private static final Logger logger = LoggerFactory.getLogger(TouristGetLocTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
	
		if (update.getMessage()!=null && update.getMessage().getText()!=null &&
				update.getMessage().getText().trim().equalsIgnoreCase("/b")){
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.WELCOME_MENU_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),BotMessage.WELCOME_MENU_KEYB);
			
			session.getSessionStatus().setId(SessionStatus.MAIN_MENU_STATUS_ID);
			return;
		}
		
		if (session.getSessionStatus().getId()==SessionStatus.MAIN_MENU_STATUS_ID){
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.TOURIST_LOC_ID, session.getLanguage());
			
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),BotMessage.B_KEYB);
			session.getSessionStatus().setId(SessionStatus.TOURIST_GETLOC_STATUS_ID);
			return;
		}
		
		if (session.getSessionStatus().getId()==SessionStatus.TOURIST_GETLOC_STATUS_ID){
			org.cysoft.urbanbot.api.telegram.model.Location rifLoc=null;
			
			if (update.getMessage()!=null && update.getMessage().getText()!=null){
				String textLoc=update.getMessage().getText();
				String response=null;
				try {
					response=CyUrbanBotUtility.httpGet("http://maps.googleapis.com/maps/api/geocode/json?address="
							+URLEncoder.encode(textLoc, "UTF-8"), 
							null);
				} catch (CyUrbanbotException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					logger.error(e.toString());
					logger.error("response="+response);
					throw new CyUrbanbotException(e);
				}
				
				GeoLocation geoLocation =null; 
				try{
					geoLocation=	new Gson().fromJson(response, GeoLocation.class);
				}
				catch(Exception e){
					logger.error(e.toString());
					logger.error("response="+response);
					throw new CyUrbanbotException(e);
				}
				
				//logger.info("GeoLocation="+geoLocation);
				if (geoLocation!=null && geoLocation.getStatus()!=null 
					&& geoLocation.getStatus().equals("OK") && geoLocation.getResults()!=null 
					&& !geoLocation.getResults().isEmpty()
					)
					if (geoLocation.getResults().get(0).getGeometry()!=null
						&& geoLocation.getResults().get(0).getGeometry().getLocation()!=null){
						rifLoc=new org.cysoft.urbanbot.api.telegram.model.Location();
						rifLoc.setLatitude(geoLocation.getResults().get(0).
								getGeometry().getLocation().getLat());
						rifLoc.setLongitude(geoLocation.getResults().get(0).
								getGeometry().getLocation().getLng());
						
					}
				
			} // text location
			
			org.cysoft.urbanbot.api.telegram.model.Location tlLoc=update.getMessage().getLocation();
			if (tlLoc!=null)
				rifLoc=tlLoc;
			
			logger.info("location="+rifLoc);
			
			if (rifLoc!=null){
				List<Location> locs=CyBssCoreAPI.getInstance().findTouristSites("","",session.getLanguage());
				if (locs.isEmpty()){
					String messageList=CyBssCoreAPI.getInstance().
							getMessage(BotMessage.TOURIST_NO_SITE_ID, session.getLanguage());
					
					TelegramAPI.getInstance().sendMessage(messageList, session.getId(), 
							update.getMessage().getMessage_id(),BotMessage.B_KEYB);
				}
				else
				{
					
					List<LocDistance> locDists=new ArrayList<LocDistance>();
					for(Location loc:locs)
						locDists.add(new LocDistance(loc,rifLoc.getLatitude(),rifLoc.getLongitude()));
					
					Collections.sort(locDists);
					
					session.setCachedItems(locDists);
					session.setCachedItemsOffset(TOURIST_NUM_SHOW*-1);
					session.setIsSearch(false);
					
					ListOptionMsgKb msgKb=this.getListMsgKb(session, true);
					TelegramAPI.getInstance().sendMessage(msgKb.getMessage(), session.getId(), 
							update.getMessage().getMessage_id(),msgKb.getKeyboard());
					
					
					/*
					messageList+=CyBssCoreAPI.getInstance().
							getMessage(BotMessage.TOURIST_LIST_ID, session.getLanguage())+"\n";
					
					
					for(LocDistance locDist:locDists)
						messageList+="/v"+locDist.getLocation().getId()+" -> "+locDist.getLocation().getName()+" @ "+
					(session.getLanguage().equals("it")?
						NumberFormat.getNumberInstance(Locale.ITALIAN).format((int)locDist.getDistance()):
						NumberFormat.getNumberInstance(Locale.US).format((int)locDist.getDistance())	
					)
					+
					" mt;\n";
					
					messageList+=CyBssCoreAPI.getInstance().
							getMessage(BotMessage.TOURIST_LIST_OP_ID, session.getLanguage());
					*/
				}
					
				session.getSessionStatus().setId(SessionStatus.TOURIST_SELLOC_STATUS_ID);
			}
			else
			{
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.TOURIST_LOC_ID, session.getLanguage());
				
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.B_KEYB);
				session.getSessionStatus().setId(SessionStatus.TOURIST_GETLOC_STATUS_ID);
			}
			
			return;
		
		
		}
		
		
		
	}

}
