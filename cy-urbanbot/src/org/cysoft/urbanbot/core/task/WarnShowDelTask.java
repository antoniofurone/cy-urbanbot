package org.cysoft.urbanbot.core.task;


import java.io.File;
import java.util.List;

import org.cysoft.bss.core.model.Ticket;
import org.cysoft.bss.core.model.CyBssFile;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.common.ICyUrbanbotConst;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.cysoft.urbanbot.core.model.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WarnShowDelTask extends WarnTaskAdapter{

	private static final Logger logger = LoggerFactory.getLogger(WarnShowDelTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		
		String selection=update.getMessage().getText()==null?"":update.getMessage().getText();
		if (selection.equals("") || (selection.length()<3 
				&& !selection.equalsIgnoreCase("/b")
				&& !selection.equalsIgnoreCase("/s")
				&& !selection.equalsIgnoreCase("/p")
				)){
			ListOptionMsgKb msgKb=getListOptionMsgKb(session);
			TelegramAPI.getInstance().sendMessage(msgKb.getMessage(), session.getId(), 
					update.getMessage().getMessage_id(),
					msgKb.getKeyboard());
			return;
		}
		
		if (selection.equalsIgnoreCase("/b")){
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.WARN_SHOW_OP_ID, session.getLanguage());
			
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),BotMessage.NRVB_KEYB);
			
			session.getSessionStatus().setId(SessionStatus.WARN_SELOP_STATUS_ID);
			return;
		}
		
		if (selection.equalsIgnoreCase("/s")){
			ListOptionMsgKb msgKb=this.getListMsgKb(session, true);
			TelegramAPI.getInstance().sendMessage(msgKb.getMessage(), session.getId(), 
					update.getMessage().getMessage_id(),msgKb.getKeyboard());
			return;
		}
		
		if (selection.equalsIgnoreCase("/p")){
			ListOptionMsgKb msgKb=this.getListMsgKb(session, false);
			TelegramAPI.getInstance().sendMessage(msgKb.getMessage(), session.getId(), 
					update.getMessage().getMessage_id(),msgKb.getKeyboard());
			return;
		}
		
		String command=selection.substring(0,2);
		String sWarnId=selection.substring(2);
		logger.info("command="+command+";warnId="+sWarnId);
		
		long warnId=0;
		try {
			warnId=Long.parseLong(sWarnId);
		}
		catch (NumberFormatException ne){
			ListOptionMsgKb msgKb=getListOptionMsgKb(session);
			TelegramAPI.getInstance().sendMessage(msgKb.getMessage(), session.getId(), 
					update.getMessage().getMessage_id(),
					msgKb.getKeyboard());
			return;
		}
		
		if (!command.equalsIgnoreCase("/v") && !command.equalsIgnoreCase("/d")){
			ListOptionMsgKb msgKb=getListOptionMsgKb(session);
			TelegramAPI.getInstance().sendMessage(msgKb.getMessage(), session.getId(), 
					update.getMessage().getMessage_id(),
					msgKb.getKeyboard());
			return;
		}
		
		if (command.equalsIgnoreCase("/v")){
			Ticket warn=CyBssCoreAPI.getInstance().getWarn(warnId,session.getLanguage());
			if (!session.isSearch() && (warn.getPersonId()==0 || warn.getPersonId()!=session.getPersonId()))
				{
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_INVALID_ID, session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),
						this.getListOptionMsgKb(session).getKeyboard());
				return;
				}
			
			TelegramAPI.getInstance().sendMessage(warn.getText()+" ["+(warn.getCategoryName()==null?"":warn.getCategoryName())+","+warn.getStatusName()+"]", session.getId(), update.getMessage().getMessage_id());
			if (warn.getLocation()!=null){
				// Invia Location
				TelegramAPI.getInstance().sendLocation(warn.getLocation().getLatitude(), warn.getLocation().getLongitude(), 
						session.getId(), update.getMessage().getMessage_id());
			}
			
			List<CyBssFile> files=CyBssCoreAPI.getInstance().getWarnFiles(warnId);
			if (files!=null && !files.isEmpty()){
				for(CyBssFile file:files){
					if (file.getVisibility().equals(CyBssFile.VISIBILITY_PUBLIC)){
						CyBssCoreAPI.getInstance().downloadFile(file.getId(), session.getId()+"_"+file.getName());
						
						if (file.getFileType().equals(ICyUrbanbotConst.MEDIA_PHOTO_TYPE))
							TelegramAPI.getInstance().sendPhoto(session.getId()+"_"+file.getName(), 
									session.getId(),update.getMessage().getMessage_id(),null);
						
						if (file.getFileType().equals(ICyUrbanbotConst.MEDIA_VIDEO_TYPE))
							TelegramAPI.getInstance().sendVideo(session.getId()+"_"+file.getName(), 
									session.getId(),update.getMessage().getMessage_id(),null);
						
						
						File f=new File(TelegramAPI.getInstance().getDownloadPath()+session.getId()+"_"+file.getName());
						f.delete();
					}
					else
						logger.warn(file.toString()+": not visible !");
				}
			}
				
		} // end /v

		if (command.equalsIgnoreCase("/d")){
			Ticket warn=CyBssCoreAPI.getInstance().getWarn(warnId,session.getLanguage());
			if (warn.getPersonId()==0 || warn.getPersonId()!=session.getPersonId())
				{
				String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_INVALID_ID, 
						session.getLanguage());
				TelegramAPI.getInstance().sendMessage(message, session.getId(), 
						update.getMessage().getMessage_id(),
						getListOptionMsgKb(session).getKeyboard());
				return;
				}
			
			CyBssCoreAPI.getInstance().removeWarn(warnId);
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_DEL_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), 
					update.getMessage().getMessage_id(),
					getListOptionMsgKb(session).getKeyboard());
						
		} // end /d
	
	}
	
}
