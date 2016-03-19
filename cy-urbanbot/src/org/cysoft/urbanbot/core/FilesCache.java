package org.cysoft.urbanbot.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.cysoft.bss.core.model.CyBssFile;
import org.cysoft.bss.core.model.Location;
import org.cysoft.bss.core.model.Ticket;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilesCache implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(FilesCache.class);
	
	private final static long SLEEP=1800000;
	private final static long SLEEP_ERROR=60000;
	
	private final static int ERROR_MAX=50;
	
	private static FilesCache instance=null;
	
	private Map <Long,List<CyBssFile>> locFilesMap=new LinkedHashMap<Long,List<CyBssFile>>();
	private Map <Long,List<CyBssFile>> ticketFilesMap=new LinkedHashMap<Long,List<CyBssFile>>();
	
	
	private FilesCache(){
		
		Thread t=new Thread(this);
		t.start();
	}
	
	public static FilesCache getInstance(){
		if (instance==null)
			instance=new FilesCache();
		return instance;
	}
	
	public List<CyBssFile> getLocationFiles(long entityId){
		if (locFilesMap==null)
			return null;
		return locFilesMap.get(entityId);
	}
	
	public List<CyBssFile> getTicketFiles(long entityId){
		if (ticketFilesMap==null)
			return null;
		return ticketFilesMap.get(entityId);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info(">>> FilesCache - Loader Task >>>");
		
		long sleep=SLEEP;
		int errorCount=0;
		
		while(true){
			logger.info("Loading Location Files....");
			
			List<CyBssFile> files=null;
			boolean error=false;
			
			locFilesMap.clear();
			
			try {
				files=CyBssCoreAPI.getInstance().getFiles(Location.ENTITY_NAME);
				
				logger.info("location files.size="+files.size());
				
				for (CyBssFile file:files){
					if (file.getVisibility().equals(CyBssFile.VISIBILITY_PUBLIC) && 
						file.getEntityId()!=0){
						if (!locFilesMap.containsKey(file.getEntityId())){
							//logger.info("add1 file="+file);
							List<CyBssFile> fileList=new ArrayList<CyBssFile>();
							fileList.add(file);
							locFilesMap.put(file.getEntityId(), fileList);
						}
						else
						{
							//logger.info("add2 file="+file);
							List<CyBssFile> fileList=locFilesMap.get(file.getEntityId());
							fileList.add(file);	
						}
					
					}
				}
				
				
			} catch (CyUrbanbotException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.toString());
				sleep=SLEEP_ERROR;
				errorCount++;
				error=true;
				
				if (errorCount>=ERROR_MAX)
					break;
			}
			logger.info("<<< Loading Location Files - locFilesMap.size = "+locFilesMap.size());
			
			
			logger.info("Loading Ticket Files....");
			ticketFilesMap.clear();
			
			try {
				files=CyBssCoreAPI.getInstance().getFiles(Ticket.ENTITY_NAME);
				
				logger.info("ticket files.size="+files.size());
				
				for (CyBssFile file:files){
					if (file.getVisibility().equals(CyBssFile.VISIBILITY_PUBLIC) && 
						file.getEntityId()!=0){
						if (!ticketFilesMap.containsKey(file.getEntityId())){
							//logger.info("add1 file="+file);
							List<CyBssFile> fileList=new ArrayList<CyBssFile>();
							fileList.add(file);
							ticketFilesMap.put(file.getEntityId(), fileList);
						}
						else
						{
							List<CyBssFile> fileList=ticketFilesMap.get(file.getEntityId());
							fileList.add(file);	
						}
					
					}
				}
				
				
			} catch (CyUrbanbotException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.toString());
				sleep=SLEEP_ERROR;
				errorCount++;
				error=true;
				
				if (errorCount>=ERROR_MAX)
					break;
			}
			logger.info("<<< Loading Ticket Files - ticketfilesMap.size = "+ticketFilesMap.size());
			
			
			try {
				if (!error)
					errorCount=0;
				
				Thread.sleep(sleep);
				sleep=SLEEP;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.toString());
				break;
			}
		}
		
		logger.info(">>> FilesCache - Loader Task <<<");
	}
	
}
