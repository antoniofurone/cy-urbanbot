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
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Keyboard;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventShowTask extends EventTaskAdapter{

	private static final Logger logger = LoggerFactory.getLogger(EventShowTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		
		String selection=update.getMessage().getText()==null?"":update.getMessage().getText();
		if (selection.equals("") || (selection.length()<3 
				&& !selection.equalsIgnoreCase(Keyboard.SELECTION_B)
				&& !selection.equalsIgnoreCase(Keyboard.BUTTON_BACK)
				&& !selection.equalsIgnoreCase(Keyboard.BUTTON_BACK_EN)
				&& !selection.equalsIgnoreCase(Keyboard.SELECTION_S)
				&& !selection.equalsIgnoreCase(Keyboard.BUTTON_NEXT)
				&& !selection.equalsIgnoreCase(Keyboard.BUTTON_NEXT_EN)
				&& !selection.equalsIgnoreCase(Keyboard.SELECTION_P)
				&& !selection.equalsIgnoreCase(Keyboard.BUTTON_PREV)
				&& !selection.equalsIgnoreCase(Keyboard.BUTTON_PREV_EN)
				)){
			ListOptionMsgKb msgKb=getListOptionMsgKb(session); 
			TelegramAPI.getInstance().sendMessage(msgKb.getMessage(), session.getId(), 
					update.getMessage().getMessage_id(),
					msgKb.getKeyboard());
			return;
		}
		
		if (
			selection.equalsIgnoreCase(Keyboard.SELECTION_B) ||
			selection.equalsIgnoreCase(Keyboard.BUTTON_BACK) ||
			selection.equalsIgnoreCase(Keyboard.BUTTON_BACK_EN)
			){
			
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.EVENT_GET_SEARCH_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),Keyboard.getB(session.getLanguage()));
			session.getSessionStatus().setId(SessionStatus.EVENT_SEARCH_STATUS_ID);
			return;
		}
		
		if (selection.equalsIgnoreCase(Keyboard.SELECTION_S) ||
			selection.equalsIgnoreCase(Keyboard.BUTTON_NEXT) ||
			selection.equalsIgnoreCase(Keyboard.BUTTON_NEXT_EN)
			){
			ListOptionMsgKb msgKb=this.getListMsgKb(session, true);
			TelegramAPI.getInstance().sendMessage(msgKb.getMessage(), session.getId(), 
					update.getMessage().getMessage_id(),msgKb.getKeyboard());
			return;
		}
		
		if (selection.equalsIgnoreCase(Keyboard.SELECTION_P) ||
			selection.equalsIgnoreCase(Keyboard.BUTTON_PREV) ||
			selection.equalsIgnoreCase(Keyboard.BUTTON_PREV_EN)){
			ListOptionMsgKb msgKb=this.getListMsgKb(session, false);
			TelegramAPI.getInstance().sendMessage(msgKb.getMessage(), session.getId(), 
					update.getMessage().getMessage_id(),msgKb.getKeyboard());
			return;
		}
		
		String command=selection.substring(0,2);
		String sEventId=selection.substring(2);
		logger.info("command="+command+";EventId="+sEventId);
		
		long eventId=0;
		try {
			eventId=Long.parseLong(sEventId);
		}
		catch (NumberFormatException ne){
			ListOptionMsgKb msgKb=getListOptionMsgKb(session);
			TelegramAPI.getInstance().sendMessage(msgKb.getMessage(), session.getId(), 
					update.getMessage().getMessage_id(),msgKb.getKeyboard());
			return;
		}
		
		if (!command.equalsIgnoreCase(Keyboard.SELECTION_V)){
			ListOptionMsgKb msgKb=getListOptionMsgKb(session);
			TelegramAPI.getInstance().sendMessage(msgKb.getMessage(), session.getId(), 
					update.getMessage().getMessage_id(),msgKb.getKeyboard());
			return;
		}
		
		
		if (command.equalsIgnoreCase(Keyboard.SELECTION_V)){
			
			Location loc=CyBssCoreAPI.getInstance().getEvent(eventId,session.getLanguage());
			if (!session.isSearch() && (loc.getPersonId()==0 || loc.getPersonId()!=session.getPersonId() || 
				loc.getLocationType()==null || !loc.getLocationType().equals(ICyUrbanbotConst.LOCATION_TYPE_EVENT)))
				{
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.EVENT_INVALID_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),getListOptionMsgKb(session).getKeyboard());
				return;
				}
			
			if (loc.getDescription()!=null && !loc.getDescription().equals(""))
				TelegramAPI.getInstance().sendMessage("<strong>"+loc.getName()+"</strong>\n"+loc.getDescription(), session.getId(), 
						update.getMessage().getMessage_id(),TelegramAPI.MESSAGE_PARSEMODE_HTML);
			
			// Invia Location
			TelegramAPI.getInstance().sendLocation(loc.getLatitude(), loc.getLongitude(), 
						session.getId(), update.getMessage().getMessage_id());
			
			
			List<CyBssFile> files=CyBssCoreAPI.getInstance().getEventFiles(eventId);
			if (files!=null && !files.isEmpty()){
				for(CyBssFile file:files){
					
					logger.info(file.toString());
					if (file.getVisibility().equals(CyBssFile.VISIBILITY_PUBLIC)){
						
						String caption=file.getNote();
						
						CyBssCoreAPI.getInstance().downloadFile(file.getId(), session.getId()+"_"+file.getName());
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
