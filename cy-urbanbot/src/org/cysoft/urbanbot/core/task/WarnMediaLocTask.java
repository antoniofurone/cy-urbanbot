package org.cysoft.urbanbot.core.task;

import java.io.File;
import java.util.List;

import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Location;
import org.cysoft.urbanbot.api.telegram.model.PhotoSize;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.api.telegram.model.Video;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.common.ICyUrbanbotConst;
import org.cysoft.urbanbot.core.Task;
import org.cysoft.urbanbot.core.TaskAdapter;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WarnMediaLocTask extends TaskAdapter implements Task {
	
	private static final Logger logger = LoggerFactory.getLogger(WarnMediaLocTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		
		if (session.getSessionStatus().getId()==SessionStatus.WARN_IMGLOC_STATUS_ID||
			session.getSessionStatus().getId()==SessionStatus.WARN_IMG_STATUS_ID	){
			String text=update.getMessage().getText()==null?"":update.getMessage().getText();
			
			if (text.equalsIgnoreCase("/b")){
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.WARN_SHOW_OP_ID, session.getLanguage());
				
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.NVB_KEYB);
				session.getSessionStatus().setId(SessionStatus.WARN_SELOP_STATUS_ID);
				return;
			}
			
			Location loc=update.getMessage().getLocation();
			if (loc!=null && session.getSessionStatus().getId()==SessionStatus.WARN_IMGLOC_STATUS_ID)
			{
				logger.info("location="+loc);
				
				long warnId=(long)session.getVariable("warnId");
				CyBssCoreAPI.getInstance().addWarnLoc(warnId, loc.getLatitude(),loc.getLongitude()); 
				
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_LOCOK_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id());
				message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_MEDIA_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.B_KEYB);
				
				session.getSessionStatus().setId(SessionStatus.WARN_IMG_STATUS_ID);
				
				return;
			}
			
			Video video=update.getMessage().getVideo();
			if (video!=null){
				logger.info("video="+video);
				String filePath=TelegramAPI.getInstance().getFilePath(video.getFile_id());
				TelegramAPI.getInstance().downloadFile(filePath);
				if (video.getFile_size()>ICyUrbanbotConst.MAX_DOWNLOADABLE_FILE_SIZE)
					throw new CyUrbanbotException("File size is greater than "+ICyUrbanbotConst.MAX_DOWNLOADABLE_FILE_SIZE+" bytes !");
				long warnId=(long)session.getVariable("warnId");
				CyBssCoreAPI.getInstance().addWarnFile(warnId, TelegramAPI.getInstance().getDownloadPath()+filePath,ICyUrbanbotConst.MEDIA_VIDEO_TYPE); 
				
				File file=new File(TelegramAPI.getInstance().getDownloadPath()+filePath);
				file.delete();
				
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_VIDEOOK_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.B_KEYB);
			}
			
			List<PhotoSize> phs=update.getMessage().getPhoto();
			if (phs!=null && phs.size()!=0) {
				logger.info("photo="+phs);
				
				String fileId="";
				for (PhotoSize ph:phs){
					if (ph.getFile_size()<=ICyUrbanbotConst.MAX_DOWNLOADABLE_FILE_SIZE)
						fileId=ph.getFile_id();
				}
				if (fileId.equals(""))
					throw new CyUrbanbotException("No file_Id found in images!");
				
				String filePath=TelegramAPI.getInstance().getFilePath(fileId);
				
				TelegramAPI.getInstance().downloadFile(filePath);
				long warnId=(long)session.getVariable("warnId");
				CyBssCoreAPI.getInstance().addWarnFile(warnId, TelegramAPI.getInstance().getDownloadPath()+filePath,ICyUrbanbotConst.MEDIA_PHOTO_TYPE); 
				
				File file=new File(TelegramAPI.getInstance().getDownloadPath()+filePath);
				file.delete();
				
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_IMGOK_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				
				if (session.getSessionStatus().getId()==SessionStatus.WARN_IMGLOC_STATUS_ID)
					message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_MEDIALOC_ID, session.getLanguage());
				else
					message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_MEDIA_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.B_KEYB);
				
				return;
			}
			
			String message=null;
			if (session.getSessionStatus().getId()==SessionStatus.WARN_IMGLOC_STATUS_ID)
				message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_MEDIALOC_ID, session.getLanguage());
			else
				message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_MEDIA_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),BotMessage.B_KEYB);
			
		} 
				
	}

}
