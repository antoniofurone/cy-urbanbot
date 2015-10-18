package org.cysoft.urbanbot.api.telegram;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.api.telegram.response.GetUpdatesResponse;
import org.cysoft.urbanbot.api.telegram.response.SendMessageResponse;
import org.cysoft.urbanbot.common.CyUrbanBotUtility;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class TelegramAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(TelegramAPI.class);
	
	
	private static final String GETUPDATES_METHOD="getUpdates";
	private static final String SENDMESSAGE_METHOD="sendMessage";
	
	private TelegramAPI(){};
	
	static private TelegramAPI instance=null;
	public static TelegramAPI getInstance(){
		if (instance==null)
			instance=new TelegramAPI();
		return instance;
	}
	
	
	private String botUrl="";
	public String getBotUrl() {
		return botUrl;
	}
	public void setBotUrl(String botUrl) {
		this.botUrl = botUrl;
	}
	
	
	public List<Update> getUpdates(long offSet) throws CyUrbanbotException{
		
		String response=null;
		try {
			String url=botUrl+GETUPDATES_METHOD+"?offset="+offSet;
			//logger.info("url="+url);
			response=CyUrbanBotUtility.httpGet(url,null);
		} catch (CyUrbanbotException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw e1;
		}
		
		GetUpdatesResponse updResponse = new Gson().fromJson(response, GetUpdatesResponse.class);
		if (!updResponse.isOk()){
			throw new CyUrbanbotException(GETUPDATES_METHOD+" nok !");
		}
		
		return updResponse.getResult();
	
	}
	
	public void sendMessage(String text,long chatId) throws CyUrbanbotException{
		sendMessage(text,chatId,0);
	}
	
	public void sendMessage(String text,long chatId,long replyToMessageId) throws CyUrbanbotException{
		String response=null;
		try {
			  
			response=CyUrbanBotUtility.httpGet(
					botUrl+SENDMESSAGE_METHOD+"?chat_id="+chatId+"&text="+
					URLEncoder.encode(text,java.nio.charset.StandardCharsets.UTF_8.toString())+
					"&parse_mode=Markdown"+
					(replyToMessageId!=0?"&reply_to_message_id="+replyToMessageId:""),
					null
					);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			throw e;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		//logger.info("response received="+response);
		SendMessageResponse sendResponse = new Gson().fromJson(response, SendMessageResponse.class);
		if (!sendResponse.isOk()){
			throw new CyUrbanbotException(SENDMESSAGE_METHOD+" nok !");
		}
	}
	
	
	@Override
	public String toString() {
		return "TelegramAPI [botUrl=" + botUrl + "]";
	}
	
	

}
