package org.cysoft.urbanbot.core.task;

import java.io.File;
import java.util.List;

import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Audio;
import org.cysoft.urbanbot.api.telegram.model.Location;
import org.cysoft.urbanbot.api.telegram.model.PhotoSize;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.api.telegram.model.Video;
import org.cysoft.urbanbot.api.telegram.model.Voice;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.common.ICyUrbanbotConst;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoryGetTask extends StoryTaskAdapter{

	private static final Logger logger = LoggerFactory.getLogger(StoryGetTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		
		String text=update.getMessage().getText()==null?"":update.getMessage().getText();
		if (text.equalsIgnoreCase("/b")){
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.STORY_SHOW_OP_ID, session.getLanguage());
			
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),BotMessage.NVB_KEYB);
			session.getSessionStatus().setId(SessionStatus.STORY_SELOP_STATUS_ID);
			return;
		}
		
		
		if (session.getSessionStatus().getId()==SessionStatus.STORY_GETLOC_STATUS_ID){
			
			Location loc=update.getMessage().getLocation();
			if (loc!=null)
			{
				logger.info("location="+loc);
				session.putVariable("storyLoc", loc);
				
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.STORY_TEXT_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.B_KEYB);
				session.getSessionStatus().setId(SessionStatus.STORY_GETTEXT_STATUS_ID);
			}
			else
			{
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.STORY_LOC_ID, session.getLanguage());
				
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.B_KEYB);
				session.getSessionStatus().setId(SessionStatus.STORY_GETLOC_STATUS_ID);
			}
			
			return;
		}
		
		if (session.getSessionStatus().getId()==SessionStatus.STORY_GETTEXT_STATUS_ID){
		
			if (text!=null && !text.equals("")){
				
				Location loc=(Location)session.getVariable("storyLoc");
				long storyId=CyBssCoreAPI.getInstance().addStory(loc.getLatitude(), loc.getLongitude(), text, session.getPersonId()); 
				session.putVariable("storyId", storyId);
			
			
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.STORY_TEXT_OK_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.B_KEYB);
				
				session.getSessionStatus().setId(SessionStatus.STORY_GETMEDIA_STATUS_ID);
			}
			else
			{
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.STORY_TEXT_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),BotMessage.B_KEYB);
				session.getSessionStatus().setId(SessionStatus.STORY_GETTEXT_STATUS_ID);
				
			}
			
			
		}
		
		if (session.getSessionStatus().getId()==SessionStatus.STORY_GETMEDIA_STATUS_ID){
			
			Audio audio=update.getMessage().getAudio();
			if (audio!=null){
				logger.info("audio="+audio);
				
				String filePath=TelegramAPI.getInstance().getFilePath(audio.getFile_id());
				TelegramAPI.getInstance().downloadFile(filePath);
				if (audio.getFile_size()>ICyUrbanbotConst.MAX_DOWNLOADABLE_FILE_SIZE)
					throw new CyUrbanbotException("File size is greater than "+ICyUrbanbotConst.MAX_DOWNLOADABLE_FILE_SIZE+" bytes !");
				long storyId=(long)session.getVariable("storyId");
				CyBssCoreAPI.getInstance().addStoryFile(storyId, TelegramAPI.getInstance().getDownloadPath()+filePath,ICyUrbanbotConst.MEDIA_AUDIO_TYPE); 
				
				File file=new File(TelegramAPI.getInstance().getDownloadPath()+filePath);
				file.delete();
				
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.STORY_AUDIOOK_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				
			}
			
			Video video=update.getMessage().getVideo();
			if (video!=null){
				logger.info("video="+video);
				String filePath=TelegramAPI.getInstance().getFilePath(video.getFile_id());
				TelegramAPI.getInstance().downloadFile(filePath);
				if (video.getFile_size()>ICyUrbanbotConst.MAX_DOWNLOADABLE_FILE_SIZE)
					throw new CyUrbanbotException("File size is greater than "+ICyUrbanbotConst.MAX_DOWNLOADABLE_FILE_SIZE+" bytes !");
				long storyId=(long)session.getVariable("storyId");
				CyBssCoreAPI.getInstance().addStoryFile(storyId, TelegramAPI.getInstance().getDownloadPath()+filePath,ICyUrbanbotConst.MEDIA_VIDEO_TYPE); 
				
				File file=new File(TelegramAPI.getInstance().getDownloadPath()+filePath);
				file.delete();
				
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.STORY_VIDEOOK_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			}
			
			Voice voice=update.getMessage().getVoice();
			if (voice!=null){
				logger.info("voice="+voice);
				
				String filePath=TelegramAPI.getInstance().getFilePath(voice.getFile_id());
				TelegramAPI.getInstance().downloadFile(filePath);
				if (voice.getFile_size()>ICyUrbanbotConst.MAX_DOWNLOADABLE_FILE_SIZE)
					throw new CyUrbanbotException("File size is greater than "+ICyUrbanbotConst.MAX_DOWNLOADABLE_FILE_SIZE+" bytes !");
				long storyId=(long)session.getVariable("storyId");
				CyBssCoreAPI.getInstance().addStoryFile(storyId, TelegramAPI.getInstance().getDownloadPath()+filePath,ICyUrbanbotConst.MEDIA_VOICE_TYPE); 
				
				File file=new File(TelegramAPI.getInstance().getDownloadPath()+filePath);
				file.delete();
				
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.STORY_VOICEOK_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
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
				long storyId=(long)session.getVariable("storyId");
				CyBssCoreAPI.getInstance().addStoryFile(storyId, TelegramAPI.getInstance().getDownloadPath()+filePath,ICyUrbanbotConst.MEDIA_PHOTO_TYPE); 
				
				File file=new File(TelegramAPI.getInstance().getDownloadPath()+filePath);
				file.delete();
				
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.STORY_IMGOK_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			}
			
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.STORY_MEDIA_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			
			
			session.getSessionStatus().setId(SessionStatus.STORY_GETMEDIA_STATUS_ID);
			
		}
			
		
	}

}
