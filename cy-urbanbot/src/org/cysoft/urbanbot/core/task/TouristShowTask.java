package org.cysoft.urbanbot.core.task;

import java.io.File;
import java.util.List;

import org.cysoft.bss.core.model.CyBssFile;
import org.cysoft.bss.core.model.Location;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.common.ICyUrbanbotConst;
import org.cysoft.urbanbot.core.Task;
import org.cysoft.urbanbot.core.TaskAdapter;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TouristShowTask extends TaskAdapter implements Task{

	private static final Logger logger = LoggerFactory.getLogger(TouristShowTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		
		String selection=update.getMessage().getText()==null?"":update.getMessage().getText();
		if (selection.equals("")){
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.TOURIST_LIST_OP_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),BotMessage.B_KEYB);
			return;
		}
		
		if (selection.equalsIgnoreCase("/b")){
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.WELCOME_MENU_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),BotMessage.WELCOME_MENU_KEYB);
			session.getSessionStatus().setId(SessionStatus.MAIN_MENU_STATUS_ID);
			return;
		}
		
		if (selection.length()<3){
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.TOURIST_LIST_OP_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),BotMessage.B_KEYB);
			return;
		}
		
		String command=selection.substring(0,2);
		String sLocId=selection.substring(2);
		logger.info("command="+command+";locId="+sLocId);
		
		long locId=0;
		try {
			locId=Long.parseLong(sLocId);
		}
		catch (NumberFormatException ne){
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.TOURIST_LIST_OP_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),BotMessage.B_KEYB);
			return;
		}
		
		
		if (!command.equalsIgnoreCase("/v")){
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.TOURIST_LIST_OP_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),BotMessage.B_KEYB);
			return;
		}
		
		if (command.equalsIgnoreCase("/v")){
			Location loc=CyBssCoreAPI.getInstance().getTouristSite(locId,session.getLanguage());
			if (loc.getLocationType()==null || !loc.getLocationType().equals(ICyUrbanbotConst.LOCATION_TYPE_TOURIST_SITE))
				{
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.TOURIST_INVALID_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.B_KEYB);
				return;
				}
			
			if (loc.getDescription()!=null && !loc.getDescription().equals(""))
				TelegramAPI.getInstance().sendMessage("<strong>"+loc.getName()+"</strong>\n"+loc.getDescription(), session.getId(), 
						update.getMessage().getMessage_id(),TelegramAPI.MESSAGE_PARSEMODE_HTML);
			// Invia Location
			TelegramAPI.getInstance().sendLocation(loc.getLatitude(), loc.getLongitude(), 
						session.getId(), update.getMessage().getMessage_id());
			
			List<CyBssFile> files=CyBssCoreAPI.getInstance().getTouristSiteFiles(locId);
			if (files!=null && !files.isEmpty()){
				for(CyBssFile file:files){
					
					logger.info(file.toString());
					
					if (file.getVisibility().equals(CyBssFile.VISIBILITY_PUBLIC)){
					
						String caption=file.getNote();
						
						//if (file.getNote()!=null && !file.getNote().equals(""))
						//	TelegramAPI.getInstance().sendMessage(file.getNote(), session.getId(), update.getMessage().getMessage_id());
						
						CyBssCoreAPI.getInstance().downloadFile(file.getId(), session.getId()+"_"+file.getName());
						
						if (file.getFileType()==null || file.getFileType().equals("") || (
							!file.getFileType().equals(ICyUrbanbotConst.MEDIA_PHOTO_TYPE) &&
							!file.getFileType().equals(ICyUrbanbotConst.MEDIA_AUDIO_TYPE) &&
							!file.getFileType().equals(ICyUrbanbotConst.MEDIA_VIDEO_TYPE) &&
							!file.getFileType().equals(ICyUrbanbotConst.MEDIA_VOICE_TYPE))
							)
							TelegramAPI.getInstance().sendDocument(session.getId()+"_"+file.getName(), 
									session.getId(),update.getMessage().getMessage_id());
						
						if (file.getFileType().equals(ICyUrbanbotConst.MEDIA_PHOTO_TYPE))
							TelegramAPI.getInstance().sendPhoto(session.getId()+"_"+file.getName(), 
									session.getId(),update.getMessage().getMessage_id(),caption);
						
						if (file.getFileType().equals(ICyUrbanbotConst.MEDIA_AUDIO_TYPE)){
							if (file.getName().endsWith(".mp3")||file.getName().endsWith(".ogg"))
							TelegramAPI.getInstance().sendAudio(session.getId()+"_"+file.getName(), 
									session.getId(),update.getMessage().getMessage_id(),null);
						}
						
						if (file.getFileType().equals(ICyUrbanbotConst.MEDIA_VIDEO_TYPE))
							TelegramAPI.getInstance().sendVideo(session.getId()+"_"+file.getName(), 
									session.getId(),update.getMessage().getMessage_id(),caption);
						
						if (file.getFileType().equals(ICyUrbanbotConst.MEDIA_VOICE_TYPE)){
							File f=new File(TelegramAPI.getInstance().getDownloadPath()+session.getId()+"_"+file.getName());
							
							File fr=new File(TelegramAPI.getInstance().getDownloadPath()+session.getId()+"_"+file.getName()+".ogg");
							f.renameTo(fr);
							
							TelegramAPI.getInstance().sendVoice(session.getId()+"_"+file.getName()+".ogg", 
									session.getId(),update.getMessage().getMessage_id());
							
							fr.delete();
						}
					
						File f=new File(TelegramAPI.getInstance().getDownloadPath()+session.getId()+"_"+file.getName());
						f.delete();
					}
					else
						logger.warn(file.toString()+": not visible !");
					
				}
			}
						
		} // end /v
		
	}

}
