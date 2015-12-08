package org.cysoft.urbanbot.core.task;


import java.io.File;
import java.util.List;

import org.cysoft.bss.core.model.Ticket;
import org.cysoft.bss.core.model.CyBssFile;
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

public class WarnShowDelTask extends TaskAdapter implements Task{

	private static final Logger logger = LoggerFactory.getLogger(WarnShowDelTask.class);

	@Override
	public void exec(Update update, Session session) throws CyUrbanbotException {
		// TODO Auto-generated method stub
		
		String selection=update.getMessage().getText()==null?"":update.getMessage().getText();
		if (selection.equals("")){
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_LIST_OP_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			return;
		}
		
		String[] aSelection=selection.split(" ");
		
		if (aSelection[0].equalsIgnoreCase("/b")){
			String message=CyBssCoreAPI.getInstance().
					getMessage(BotMessage.WARN_SHOW_OP_ID, session.getLanguage());
			
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			session.getSessionStatus().setId(SessionStatus.WARN_SELOP_STATUS_ID);
			return;
		}
		
		if (aSelection.length!=2 || (!aSelection[0].equalsIgnoreCase("/v") && !aSelection[0].equalsIgnoreCase("/d"))){
			String message=CyBssCoreAPI.getInstance().getMessage(BotMessage.WARN_LIST_OP_ID, session.getLanguage());
			TelegramAPI.getInstance().sendMessage(message, session.getId(), update.getMessage().getMessage_id());
			return;
		}
		
		if (aSelection[0].equalsIgnoreCase("/v")){
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
				
				TelegramAPI.getInstance().sendMessage(warn.getText()+" {"+(warn.getCategoryName()==null?"":warn.getCategoryName())+";"+warn.getStatusName()+"}", session.getId(), update.getMessage().getMessage_id());
				if (warn.getLocation()!=null){
					// Invia Location
					TelegramAPI.getInstance().sendLocation(warn.getLocation().getLatitude(), warn.getLocation().getLongitude(), 
							session.getId(), update.getMessage().getMessage_id());
				}
				
				List<CyBssFile> files=CyBssCoreAPI.getInstance().getWarnFiles(warnId);
				if (files!=null && !files.isEmpty()){
					int nPhoto=1;
					for(CyBssFile file:files){
						CyBssCoreAPI.getInstance().downloadFile(file.getId(), session.getId()+"_"+file.getName());
						String caption=warn.getId()+" ["+nPhoto+"/"+files.size()+"] ";
						logger.info("caption="+caption);
						TelegramAPI.getInstance().sendPhoto(session.getId()+"_"+file.getName(), 
								session.getId(),update.getMessage().getMessage_id(),caption);
						
						File f=new File(TelegramAPI.getInstance().getDownloadPath()+session.getId()+"_"+file.getName());
						f.delete();
						nPhoto++;
					}
				}
				
		} // end /v

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
	
	}

}
