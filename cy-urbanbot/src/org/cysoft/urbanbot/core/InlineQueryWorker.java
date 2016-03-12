package org.cysoft.urbanbot.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.cysoft.bss.core.model.CyBssFile;
import org.cysoft.bss.core.model.ICyBssConst;
import org.cysoft.bss.core.model.Location;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.InlineQuery;
import org.cysoft.urbanbot.api.telegram.model.InlineQueryResult;
import org.cysoft.urbanbot.api.telegram.model.InlineQueryResultArticle;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InlineQueryWorker implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(InlineQueryWorker.class);

	private InlineQuery inLineQuery=null;
	
	public InlineQueryWorker(InlineQuery inLineQuery){
		this.inLineQuery=inLineQuery;
	}
	
	
	private List<InlineQueryResult> getTouristSite(String query, String langCode) throws CyUrbanbotException{
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
			message+=loc.getDescription()+"\n";
			
			List<CyBssFile> files=FilesCache.getInstance().getLocationFiles(loc.getId());
			if (files!=null){
				for(CyBssFile file:files){
					message+="\n";
					if (file.getFileType()!=null && !file.getFileType().equals(""))
						message+=file.getFileType()+" - ";
					if (file.getNote()!=null && !file.getNote().equals(""))
						message+=file.getNote()+" - ";
					message+=CyBssCoreAPI.getInstance().getExternalCoreUrl()+"/fileservice/file/"+file.getId()+"/download";
				}
			}
			
			message+="\n\n";
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info(">>> InlineQueryWorker Thread...");
		
		
		String queryforSearch="";
		String queryReceived=inLineQuery.getQuery();
		String langCode="";
		
		if (queryReceived!=null && !queryReceived.equals("")){
			StringTokenizer st = new StringTokenizer(queryReceived);
		     while (st.hasMoreTokens()) {
		    	 String token=st.nextToken();
		    	 String token_lc=token.toLowerCase();
		    	 if (token_lc.startsWith("lang=")){
		    		 langCode=token_lc.substring(5);
		    		 logger.info("langCode found <"+langCode+"> !");
		    	 }
		    	 else
		    		 queryforSearch+=queryforSearch.equals("")?token:" "+token;
		     }
		}

		if (langCode.equals("") || langCode.equalsIgnoreCase(ICyBssConst.LOCALE_IT))
			langCode=ICyBssConst.LOCALE_IT;
		else
			langCode=ICyBssConst.LOCALE_EN;
		
		List<InlineQueryResult> inLineResults=null;
		try {
			inLineResults=getTouristSite(queryforSearch, langCode);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			return;
		}
		try {
			TelegramAPI.getInstance().answerInlineQuery(inLineQuery.getId(), inLineResults,"");
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			return;
		}
		
		logger.info("<<< InlineQueryWorker Thread...");
		
	}
	
	
}
