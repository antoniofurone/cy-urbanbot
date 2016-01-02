package org.cysoft.urbanbot.core.task;


import java.io.File;
import java.util.List;

import org.cysoft.bss.core.model.CyBssFile;
import org.cysoft.bss.core.model.Location;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.Task;
import org.cysoft.urbanbot.core.TaskAdapter;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TellShowDelTask extends TaskAdapter implements Task{

	private static final Logger logger = LoggerFactory.getLogger(TellShowDelTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		
		String selection=update.getMessage().getText()==null?"":update.getMessage().getText();
		if (selection.equals("")){
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.TELL_LIST_OP_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			return;
		}
		
		String[] aSelection=selection.split(" ");
		
		if (aSelection[0].equalsIgnoreCase("/b")){
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.TELL_SHOW_OP_ID, session.getLanguage());
			
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			session.getSessionStatus().setId(SessionStatus.TELL_SELOP_STATUS_ID);
			return;
		}
		
		
		if (aSelection.length!=2 || (!aSelection[0].equalsIgnoreCase("/v") && !aSelection[0].equalsIgnoreCase("/d"))){
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.TELL_LIST_OP_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			return;
		}
		
		
		if (aSelection[0].equalsIgnoreCase("/v")){
			long storyId=0;
			try {
				storyId=Long.parseLong(aSelection[1]);
			}
			catch (NumberFormatException ne){
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.TELL_LIST_OP_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				return;
			}
			
			Location loc=CyBssCoreAPI.getInstance().getStory(storyId,session.getLanguage());
			if (loc.getPersonId()==0 || loc.getPersonId()!=session.getPersonId() || 
				loc.getLocationType()==null || !loc.getLocationType().equals("Story Location"))
				{
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_INVALID_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				return;
				}
			
			if (loc.getDescription()!=null && !loc.getDescription().equals(""))
				TelegramAPI.getInstance().sendMessage(loc.getDescription(), session.getId(), update.getMessage().getMessage_id());
			// Invia Location
			TelegramAPI.getInstance().sendLocation(loc.getLatitude(), loc.getLongitude(), 
						session.getId(), update.getMessage().getMessage_id());
			
			
			List<CyBssFile> files=CyBssCoreAPI.getInstance().getStoryFiles(storyId);
			if (files!=null && !files.isEmpty()){
				for(CyBssFile file:files){
					CyBssCoreAPI.getInstance().downloadFile(file.getId(), session.getId()+"_"+file.getName());
					if (file.getFileType().equals("Photo"))
						TelegramAPI.getInstance().sendPhoto(session.getId()+"_"+file.getName(), 
								session.getId(),update.getMessage().getMessage_id(),null);
					
					if (file.getFileType().equals("Audio")){
						if (file.getName().endsWith(".mp3")||file.getName().endsWith(".ogg"))
						TelegramAPI.getInstance().sendAudio(session.getId()+"_"+file.getName(), 
								session.getId(),update.getMessage().getMessage_id(),null);
					}
					
					if (file.getFileType().equals("Video"))
						TelegramAPI.getInstance().sendVideo(session.getId()+"_"+file.getName(), 
								session.getId(),update.getMessage().getMessage_id(),null);
					
					if (file.getFileType().equals("Voice")){
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
			}
		} // end /v
		
		/*
		if (aSelection[0].equalsIgnoreCase("/d")){
			long warnId=0;
			try {
				warnId=Long.parseLong(aSelection[1]);
			}
			catch (NumberFormatException ne){
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_LIST_OP_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				return;
			}
			
			Ticket warn=CyBssCoreAPI.getInstance().getWarn(warnId,session.getLanguage());
			if (warn.getPersonId()==0 || warn.getPersonId()!=session.getPersonId())
				{
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_INVALID_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				return;
				}
			
			CyBssCoreAPI.getInstance().removeWarn(warnId);
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_DEL_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
						
		} // end /d
		*/
	}

}
