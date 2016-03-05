package org.cysoft.urbanbot.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.cysoft.bss.core.model.Location;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.InlineQueryResult;
import org.cysoft.urbanbot.api.telegram.model.InlineQueryResultArticle;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InlineQueryServer {
	
	private static final Logger logger = LoggerFactory.getLogger(InlineQueryServer.class);

	List<InlineQueryResult> getTouristSite(String query, String langCode) throws CyUrbanbotException{
		logger.info("query="+query+";langCode="+langCode);
		
		List<InlineQueryResult> inLineResults=new ArrayList<InlineQueryResult>();
		
		List<Location> locs=CyBssCoreAPI.getInstance().findTouristSites(query.equals("")?"":"%"+query+"%","",langCode);
		if (query!=null && !query.equals("")){
			// find using description
			Map <Long,String> hMap=new LinkedHashMap<Long,String>();
			for(Location loc:locs)
				hMap.put(loc.getId(), loc.getName());
			
			List<Location> locsDesc=CyBssCoreAPI.getInstance().findTouristSites("",query.equals("")?"":"%"+query+"%",langCode);
			for(Location loc:locsDesc){
				if (!hMap.containsKey(loc.getId()))
				locs.add(loc);	
			}
		}
		
		for(Location loc:locs){
			InlineQueryResultArticle inRes=new InlineQueryResultArticle();
			inRes.setId(""+loc.getId());
			inRes.setTitle(loc.getName());
			String message="<strong>"+loc.getName()+"</strong>\n";
			message+=loc.getDescription()+"\n\n";
			message+="Google Maps: http://maps.google.com/?q="+loc.getLatitude()+","+loc.getLongitude()+"\n";
			message+="OpenStreetMap: http://www.openstreetmap.org/?mlat="+loc.getLatitude()+"&mlon="+loc.getLongitude()+"&zoom=12"+"\n";
			message+="\n";
			inRes.setMessage_text(message);
			inRes.setParse_mode(TelegramAPI.MESSAGE_PARSEMODE_HTML);
			inRes.setDescription(loc.getDescription().length()>50?loc.getDescription().substring(0,50)+" [...]":
				loc.getDescription()); 
			inLineResults.add(inRes);
			
			if (inLineResults.size()>=50)
				break;
		}
		
		return inLineResults;
	}

	
	
}
