package org.cysoft.urbanbot.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.cysoft.bss.core.model.CyBssFile;
import org.cysoft.bss.core.model.ICyBssConst;
import org.cysoft.bss.core.model.Location;
import org.cysoft.bss.core.model.Ticket;
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
	
	private static final short QUERY_SITES=0;
	private static final short QUERY_WARN=1;
	private static final short QUERY_STORY=2;
	private static final short QUERY_EVENT=3;
	
	private static final String TOKEN_STORY1="story";
	private static final String TOKEN_STORY2="storie";
	
	private static final String TOKEN_WARN1="warn";
	private static final String TOKEN_WARN2="segn";
	
	private static final String TOKEN_EVENT1="event";
	private static final String TOKEN_EVENT2="eventi";
	
	
	private static final int QUERY_SIZE=20;
	
	
	public InlineQueryWorker(InlineQuery inLineQuery){
		this.inLineQuery=inLineQuery;
	}
	
	
	private List<InlineQueryResult> getStories(String query,String langCode,String offSet) throws CyUrbanbotException{
		List<InlineQueryResult> inLineResults=new ArrayList<InlineQueryResult>();
		
		List<Location> locations=CyBssCoreAPI.getInstance().findStories(0, query.equals("")?"":"%"+query+"%", 
				langCode,(offSet!=null && !offSet.equals(""))?Integer.parseInt(offSet):1,QUERY_SIZE);
		
		for(Location loc:locations){
			InlineQueryResultArticle inRes=new InlineQueryResultArticle();
			inRes.setId(""+loc.getId());
			String personFirstName=(loc.getPersonFirstName()!=null && !loc.getPersonFirstName().equals(""))?loc.getPersonFirstName()+",":"";
			inRes.setTitle(loc.getId()+". ["+personFirstName+loc.getCreationDate()+"]");
			String message="<strong>"+inRes.getTitle()+"</strong>\n";
			message+=loc.getDescription()+"\n";
			
			List<CyBssFile> files=FilesCache.getInstance().getLocationFiles(loc.getId());
			if (files!=null){
				for(CyBssFile file:files){
					message+="\n";
					if (file.getFileType()!=null && !file.getFileType().equals(""))
						message+=file.getFileType()+" - ";
					message+=CyBssCoreAPI.getInstance().getExternalCoreUrl()+"/fileservice/file/"+file.getId()+"/download";
				}
			}
			
			message+="\n\n";
			message+="Google Maps: http://maps.google.com/?q="+loc.getLatitude()+","+loc.getLongitude()+"\n";
			message+="OpenStreetMap: http://www.openstreetmap.org/?mlat="+loc.getLatitude()+"&mlon="+loc.getLongitude()+"&zoom=12"+"\n";
			message+="\n";
			
			inRes.setMessage_text(message);
			inRes.setParse_mode(TelegramAPI.MESSAGE_PARSEMODE_HTML);
			inRes.setDescription(loc.getDescription().length()>60?loc.getDescription().substring(0,60)+" [...]":
				loc.getDescription()); 
			inLineResults.add(inRes);
		}
		
		return inLineResults;
	}
	
	private List<InlineQueryResult> getWarns(String query,String langCode,String offSet) throws CyUrbanbotException{
		List<InlineQueryResult> inLineResults=new ArrayList<InlineQueryResult>();
		
		List<Ticket> tickets=CyBssCoreAPI.getInstance().findWarns(0, query.equals("")?"":"%"+query+"%", 
				langCode,(offSet!=null && !offSet.equals(""))?Integer.parseInt(offSet):1,QUERY_SIZE);
		
		for(Ticket ticket:tickets){
			InlineQueryResultArticle inRes=new InlineQueryResultArticle();
			inRes.setId(""+ticket.getId());
			inRes.setTitle(ticket.getCategoryName().substring(1,1)+
					ticket.getId()+". ["+ticket.getCategoryName()+","+ticket.getStatusName()+"]");
		
			String message="<strong>"+inRes.getTitle()+"</strong>\n";
			message+="<strong>"+ticket.getCreationDate()+"</strong>: "+ ticket.getText();
			
			List<CyBssFile> files=FilesCache.getInstance().getTicketFiles(ticket.getId());
			if (files!=null){
				for(CyBssFile file:files){
					message+="\n";
					if (file.getFileType()!=null && !file.getFileType().equals(""))
						message+=file.getFileType()+" - ";
					message+=CyBssCoreAPI.getInstance().getExternalCoreUrl()+"/fileservice/file/"+file.getId()+"/download";
				}
			}
			
			message+="\n\n";
			message+="Google Maps: http://maps.google.com/?q="+ticket.getLocation().getLatitude()+","+ticket.getLocation().getLongitude()+"\n";
			message+="OpenStreetMap: http://www.openstreetmap.org/?mlat="+ticket.getLocation().getLatitude()+"&mlon="+ticket.getLocation().getLongitude()+"&zoom=12"+"\n";
			message+="\n";
			
			inRes.setMessage_text(message);
			inRes.setParse_mode(TelegramAPI.MESSAGE_PARSEMODE_HTML);
			inRes.setDescription(ticket.getCreationDate()+": "+(ticket.getText().length()>80?ticket.getText().substring(0,80)+" [...] ":
				ticket.getText()));
			
			inLineResults.add(inRes);
		}
		
		return inLineResults;
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

	private List<InlineQueryResult> getEvents(String query, String langCode) throws CyUrbanbotException{
		logger.info("query="+query+";langCode="+langCode);
		
		List<InlineQueryResult> inLineResults=new ArrayList<InlineQueryResult>();
		
		List<Location> locs=CyBssCoreAPI.getInstance().findEvents(query.equals("")?"":"%"+query+"%","",langCode);
		if (query!=null && !query.equals("")){
			// find using description
			Map <Long,String> hMap=new LinkedHashMap<Long,String>();
			for(Location loc:locs)
				hMap.put(loc.getId(), loc.getName());
			
			List<Location> locsDesc=CyBssCoreAPI.getInstance().findEvents("",query.equals("")?"":"%"+query+"%",langCode);
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
	
		short queryType=QUERY_SITES;
		
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
		    		 if (token_lc.equalsIgnoreCase(TOKEN_STORY1)||token_lc.equalsIgnoreCase(TOKEN_STORY2)){
		    			 queryType=QUERY_STORY;
		    			 logger.info("Is stories query !");
		    		 }
		    		 else
		    			 if (token_lc.equalsIgnoreCase(TOKEN_WARN1)||token_lc.equalsIgnoreCase(TOKEN_WARN2)){
			    			 queryType=QUERY_WARN;
			    			 logger.info("Is warns query !");
			    		 } 
		    			 else
		    				 if (token_lc.equalsIgnoreCase(TOKEN_EVENT1)||token_lc.equalsIgnoreCase(TOKEN_EVENT2)){
				    			 queryType=QUERY_EVENT;
				    			 logger.info("Is event query !");
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
		
			if (queryType==QUERY_STORY)
				inLineResults=getStories(queryforSearch,langCode,inLineQuery.getOffset());
			else
				if (queryType==QUERY_WARN)
					inLineResults=getWarns(queryforSearch,langCode,inLineQuery.getOffset());
				else
					if (queryType==QUERY_EVENT)
						inLineResults=getEvents(queryforSearch,langCode);
					else
						inLineResults=getTouristSite(queryforSearch, langCode);
		
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			return;
		}
		try {
			int offSet=(inLineQuery.getOffset()!=null&&!inLineQuery.getOffset().equals(""))?
					Integer.parseInt(inLineQuery.getOffset()):1;
			offSet+=QUERY_SIZE;		
					
			TelegramAPI.getInstance().answerInlineQuery(inLineQuery.getId(), inLineResults,queryType!=QUERY_SITES?""+offSet:"");
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			return;
		}
		
		logger.info("<<< InlineQueryWorker Thread...");
	}
	
	
}
