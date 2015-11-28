package org.cysoft.urbanbot.core.task;

import java.io.File;
import java.util.List;

import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Location;
import org.cysoft.urbanbot.api.telegram.model.PhotoSize;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.Task;
import org.cysoft.urbanbot.core.TaskAdapter;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetWarnImgLocTask extends TaskAdapter implements Task {
	
	private static final Logger logger = LoggerFactory.getLogger(GetWarnImgLocTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		
		if (session.getSessionStatus().getId()==SessionStatus.WARNING_IMGORLOC_STATUS_ID||
			session.getSessionStatus().getId()==SessionStatus.WARNING_IMG_STATUS_ID	){
			String text=update.getMessage().getText()==null?"":update.getMessage().getText();
			
			if (text.equalsIgnoreCase("/b")){
				String message=CyBssCoreAPI.getInstance().
						getMessage(BotMessage.SHOW_WARNOP, session.getLanguage());
				
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				session.getSessionStatus().setId(SessionStatus.WARNING_SEL_OP_ID);
				return;
			}
			
			Location loc=update.getMessage().getLocation();
			if (loc!=null && session.getSessionStatus().getId()==SessionStatus.WARNING_IMGORLOC_STATUS_ID)
			{
				logger.info("location="+loc);
				
				long warnId=(long)session.getVariable("warnId");
				CyBssCoreAPI.getInstance().addWarnLoc(warnId, loc.getLatitude(),loc.getLongitude()); 
				
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.SEND_WARNLOCOK, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				message=CyBssCoreAPI.getInstance().getMessage(BotMessage.SEND_WARNIMG, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				
				session.getSessionStatus().setId(SessionStatus.WARNING_IMG_STATUS_ID);
				
				return;
			}
			
			List<PhotoSize> phs=update.getMessage().getPhoto();
			if (phs!=null && phs.size()!=0) {
				logger.info("photo="+phs);
				
				String fileId="";
				for (PhotoSize ph:phs){
					if (ph.getFile_size()<=PhotoSize.MAX_DOWNLOADABLE_SIZE)
						fileId=ph.getFile_id();
				}
				if (fileId.equals(""))
					throw new CyUrbanbotException("No file_Id found in images!");
				
				String filePath=TelegramAPI.getInstance().getFilePath(fileId);
				
				TelegramAPI.getInstance().downloadFile(filePath);
				long warnId=(long)session.getVariable("warnId");
				CyBssCoreAPI.getInstance().addWarnImg(warnId, TelegramAPI.getInstance().getDownloadPath()+filePath); 
				
				File file=new File(TelegramAPI.getInstance().getDownloadPath()+filePath);
				file.delete();
				
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.SEND_WARNIMGOK, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				
				if (session.getSessionStatus().getId()==SessionStatus.WARNING_IMGORLOC_STATUS_ID)
					message=CyBssCoreAPI.getInstance().getMessage(BotMessage.SEND_WARNIMGORLOC, session.getLanguage());
				else
					message=CyBssCoreAPI.getInstance().getMessage(BotMessage.SEND_WARNIMG, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
				
				if (session.getSessionStatus().getId()==SessionStatus.WARNING_IMGORLOC_STATUS_ID)
					session.getSessionStatus().setId(SessionStatus.WARNING_IMGORLOC_STATUS_ID);
				else
					session.getSessionStatus().setId(SessionStatus.WARNING_IMG_STATUS_ID);
				return;
			}
			
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.SEND_WARNIMGORLOC, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			
		} 
				
	}

}
