package org.cysoft.urbanbot.core.task;

import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.cysoft.bss.core.model.ICyBssConst;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.TaskAdapter;
import org.cysoft.urbanbot.core.model.BotMessage;
import org.cysoft.urbanbot.core.model.Keyboard;
import org.cysoft.urbanbot.core.model.LocDistance;
import org.cysoft.urbanbot.core.model.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TouristTaskAdapter extends TaskAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(TouristTaskAdapter.class);
	private static final String itemEmoj=new String(new byte[]{(byte)0xE2, (byte)0x9C, (byte)0x94}, Charset.forName("UTF-8"));
	
	protected static final int TOURIST_NUM_SHOW=15;
		
	protected ListOptionMsgKb getListMsgKb(Session session,boolean next)throws CyUrbanbotException{
		ListOptionMsgKb ret=new ListOptionMsgKb();
		
		logger.info("getCachedItemsOffset="+session.getCachedItemsOffset()+";cachedItemsSize="+session.getCachedItems().size());
		
		int offSet,endIndex;
		if (next){
			offSet=session.getCachedItemsOffset()+TOURIST_NUM_SHOW;
			endIndex=offSet+TOURIST_NUM_SHOW;
			if (endIndex>session.getCachedItems().size())
				endIndex=session.getCachedItems().size();
		}
		else
		{
			endIndex=session.getCachedItemsOffset();
			offSet=session.getCachedItemsOffset()-TOURIST_NUM_SHOW;
			if (offSet<0) offSet=0;
		}
		
		
		List<LocDistance> locDists=(List<LocDistance>) session.getCachedItems();
		String messageList="["+(offSet+1)+".."+endIndex+"/"+locDists.size()+"] -->\n";
		for(int i=offSet;i<endIndex;i++){
			LocDistance locDist=locDists.get(i);
			
			messageList+=itemEmoj+" "+Keyboard.SELECTION_V+locDist.getLocation().getId()+" "+locDist.getLocation().getName()+" @ "+
					(session.getLanguage().equalsIgnoreCase(ICyBssConst.LOCALE_IT)?
							NumberFormat.getNumberInstance(Locale.ITALIAN).format((int)locDist.getDistance()):
							NumberFormat.getNumberInstance(Locale.US).format((int)locDist.getDistance())	
						)
						+
						" mt;\n";
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
		
		if (session.getCachedItems().size()<=TOURIST_NUM_SHOW){
			ret.setMessage(CyBssCoreAPI.getInstance().getMessage(BotMessage.TOURIST_LIST_OP_ID, 
					session.getLanguage()));
			ret.setKeyboard(Keyboard.getB(session.getLanguage()));
		}
		else
		{
			if (session.getCachedItemsOffset()+TOURIST_NUM_SHOW>=session.getCachedItems().size()){
				ret.setMessage(CyBssCoreAPI.getInstance().getMessage(BotMessage.TOURIST_LIST_OP_ID_P, 
						session.getLanguage()));
				ret.setKeyboard(Keyboard.getPb(session.getLanguage()));
			}
			else 
			{
				if (session.getCachedItemsOffset()==0){
					ret.setMessage(CyBssCoreAPI.getInstance().getMessage(BotMessage.TOURIST_LIST_OP_ID_N, 
							session.getLanguage()));
					ret.setKeyboard(Keyboard.getNb(session.getLanguage()));
				}
				else
				{
					ret.setMessage(CyBssCoreAPI.getInstance().getMessage(BotMessage.TOURIST_LIST_OP_ID_NP, 
							session.getLanguage()));
					ret.setKeyboard(Keyboard.getNpb(session.getLanguage()));
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
