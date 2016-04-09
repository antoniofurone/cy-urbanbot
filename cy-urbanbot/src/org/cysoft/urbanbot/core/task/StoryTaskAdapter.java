package org.cysoft.urbanbot.core.task;

import java.nio.charset.Charset;
import java.util.List;

import org.cysoft.bss.core.model.Location;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.TaskAdapter;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class StoryTaskAdapter extends TaskAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(StoryTaskAdapter.class);
	private static final String itemEmoj=new String(new byte[]{(byte)0xE2, (byte)0x9C, (byte)0x94}, Charset.forName("UTF-8"));
	
	protected static final int STORY_CACHE_SIZE=100;
	protected static final int STORY_NUM_SHOW=5;
	
		
	protected ListOptionMsgKb getListMsgKb(Session session,boolean next)throws CyUrbanbotException{
		ListOptionMsgKb ret=new ListOptionMsgKb();
		
		logger.info("getCachedItemsOffset="+session.getCachedItemsOffset()+";cachedItemsSize="+session.getCachedItems().size());
		
		int offSet,endIndex;
		if (next){
			offSet=session.getCachedItemsOffset()+STORY_NUM_SHOW;
			endIndex=offSet+STORY_NUM_SHOW;
			if (endIndex>session.getCachedItems().size())
				endIndex=session.getCachedItems().size();
		}
		else
		{
			endIndex=session.getCachedItemsOffset();
			offSet=session.getCachedItemsOffset()-STORY_NUM_SHOW;
			if (offSet<0) offSet=0;
		}
		List<Location> locs=(List<Location>) session.getCachedItems();
		String messageList="["+(offSet+1)+".."+endIndex+"/"+locs.size()+"] -->\n";
		for(int i=offSet;i<endIndex;i++){
			Location loc=locs.get(i);
			
			messageList+=itemEmoj+" /v"+loc.getId()+" "+(loc.getDescription().length()>80?loc.getDescription().substring(0,80)+" [...]":loc.getDescription())
				+" ["+(loc.getPersonFirstName()!=null?loc.getPersonFirstName():"")+"]"
				+" @ "+loc.getCreationDate().substring(0, 10)+(session.getPersonId()!=loc.getPersonId()?"":" /d"+loc.getId())+";\n";
		}
		
		session.setCachedItemsOffset(offSet);
		
		ListOptionMsgKb msgKb=getListOptionMsgKb(session);
		messageList+="\n"+msgKb.getMessage();
		
		ret.setMessage(messageList);
		ret.setKeyboard(msgKb.getKeyboard());
		
		
		return ret;
	}
	
	protected ListOptionMsgKb getListOptionMsgKb(Session session) throws CyUrbanbotException{ 
		ListOptionMsgKb ret=new ListOptionMsgKb();
		
		logger.info("getCachedItemsOffset="+session.getCachedItemsOffset()+";cachedItemsSize="+session.getCachedItems().size());
		
		if (session.getCachedItems().size()<=STORY_NUM_SHOW){
			ret.setMessage(CyBssCoreAPI.getInstance().getMessage(BotMessage.STORY_LIST_OP_ID, 
					session.getLanguage()));
			ret.setKeyboard(BotMessage.B_KEYB);
		}
		else
		{
			if (session.getCachedItemsOffset()+STORY_NUM_SHOW>=session.getCachedItems().size()){
				ret.setMessage(CyBssCoreAPI.getInstance().getMessage(BotMessage.STORY_LIST_OP_ID_P, 
						session.getLanguage()));
				ret.setKeyboard(BotMessage.BP_KEYB);
			}
			else 
			{
				if (session.getCachedItemsOffset()==0){
					ret.setMessage(CyBssCoreAPI.getInstance().getMessage(BotMessage.STORY_LIST_OP_ID_N, 
							session.getLanguage()));
					ret.setKeyboard(BotMessage.BN_KEYB);
				}
				else
				{
					ret.setMessage(CyBssCoreAPI.getInstance().getMessage(BotMessage.STORY_LIST_OP_ID_NP, 
							session.getLanguage()));
					ret.setKeyboard(BotMessage.BNP_KEYB);
				}
				
			}
		}
		
		return ret;
	}

	protected class ListOptionMsgKb{
		private String message="";
		List<List<String>> keyboard=null;
		
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public List<List<String>> getKeyboard() {
			return keyboard;
		}
		public void setKeyboard(List<List<String>> keyboard) {
			this.keyboard = keyboard;
		}
		@Override
		public String toString() {
			return "ListOptionMsgKb [message=" + message + ", keyboard=" + keyboard + "]";
		}
		
		
	}
	
}
