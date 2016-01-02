package org.cysoft.urbanbot.api.telegram;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.api.telegram.response.GetFileResponse;
import org.cysoft.urbanbot.api.telegram.response.GetUpdatesResponse;
import org.cysoft.urbanbot.api.telegram.response.SendMessageResponse;
import org.cysoft.urbanbot.common.CyUrbanBotUtility;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.NameValueList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class TelegramAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(TelegramAPI.class);
	
	
	private static final String GETUPDATES_METHOD="getUpdates";
	private static final String GETFILE_METHOD="getFile";
	
	private static final String SENDMESSAGE_METHOD="sendMessage";
	private static final String SENDLOCATION_METHOD="sendLocation";
	private static final String SENDPHOTO_METHOD="sendPhoto";
	private static final String SENDVIDEO_METHOD="sendVideo";
	private static final String SENDAUDIO_METHOD="sendAudio";
	private static final String SENDVOICE_METHOD="sendVoice";
	
	
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
	
	private String botFileUrl="";
	public String getBotFileUrl() {
		return botFileUrl;
	}
	public void setBotFileUrl(String botFileUrl) {
		this.botFileUrl = botFileUrl;
	}
	
	
	private String downloadPath="";
	public String getDownloadPath() {
		return downloadPath;
	}
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
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

	public void sendLocation(double latitude,double longitude,long chatId,long replyToMessageId) throws CyUrbanbotException{
		String response=null;
		try {
			  
			response=CyUrbanBotUtility.httpGet(
					botUrl+SENDLOCATION_METHOD+"?chat_id="+chatId+"&latitude="+
					URLEncoder.encode(new Double(latitude).toString(),java.nio.charset.StandardCharsets.UTF_8.toString())+
					"&longitude="+
					URLEncoder.encode(new Double(longitude).toString(),java.nio.charset.StandardCharsets.UTF_8.toString())+
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
	
	public void sendPhoto(String filePath,long chatId,long replyToMessageId,String caption) throws CyUrbanbotException{
		
		NameValueList nvl=new NameValueList(); 
		nvl.add("chat_id", (new Long(chatId)).toString());
		if (caption!=null && !caption.equals(""))
			nvl.add("caption", caption);
		if (replyToMessageId!=0)
			nvl.add("reply_to_message_id", (new Long(replyToMessageId)).toString());
		
		
		String response;
		try {
			response = CyUrbanBotUtility.httpUpload(
					botUrl+SENDPHOTO_METHOD,
					downloadPath+filePath,
					null,
					nvl.toList(),"photo");
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		//logger.info("response received="+response);
		SendMessageResponse sendResponse = new Gson().fromJson(response, SendMessageResponse.class);
		if (!sendResponse.isOk()){
			throw new CyUrbanbotException(SENDMESSAGE_METHOD+" nok !");
		}
		
	}
	
	public void sendVideo(String filePath,long chatId,long replyToMessageId,String caption) throws CyUrbanbotException{
		
		NameValueList nvl=new NameValueList(); 
		nvl.add("chat_id", (new Long(chatId)).toString());
		if (caption!=null && !caption.equals(""))
			nvl.add("caption", caption);
		if (replyToMessageId!=0)
			nvl.add("reply_to_message_id", (new Long(replyToMessageId)).toString());
		
		
		String response;
		try {
			response = CyUrbanBotUtility.httpUpload(
					botUrl+SENDVIDEO_METHOD,
					downloadPath+filePath,
					null,
					nvl.toList(),"video");
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		//logger.info("response received="+response);
		SendMessageResponse sendResponse = new Gson().fromJson(response, SendMessageResponse.class);
		if (!sendResponse.isOk()){
			throw new CyUrbanbotException(SENDMESSAGE_METHOD+" nok !");
		}
		
	}
	
	
	public void sendAudio(String filePath,long chatId,long replyToMessageId, String mimeType) throws CyUrbanbotException{
		
		NameValueList nvl=new NameValueList(); 
		nvl.add("chat_id", (new Long(chatId)).toString());
		if (replyToMessageId!=0)
			nvl.add("reply_to_message_id", (new Long(replyToMessageId)).toString());
		if (mimeType!=null && !mimeType.equals(""))
			nvl.add("mime_type", mimeType);

		
		
		String response;
		try {
			response = CyUrbanBotUtility.httpUpload(
					botUrl+SENDAUDIO_METHOD,
					downloadPath+filePath,
					null,
					nvl.toList(),"audio");
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		logger.info("response received="+response);
		SendMessageResponse sendResponse = new Gson().fromJson(response, SendMessageResponse.class);
		if (!sendResponse.isOk()){
			throw new CyUrbanbotException(SENDMESSAGE_METHOD+" nok !");
		}
		
	}
	
	public void sendVoice(String filePath,long chatId,long replyToMessageId) throws CyUrbanbotException{
		
		NameValueList nvl=new NameValueList(); 
		nvl.add("chat_id", (new Long(chatId)).toString());
		if (replyToMessageId!=0)
			nvl.add("reply_to_message_id", (new Long(replyToMessageId)).toString());
		
		
		String response;
		try {
			response = CyUrbanBotUtility.httpUpload(
					botUrl+SENDVOICE_METHOD,
					downloadPath+filePath,
					null,
					nvl.toList(),"voice");
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		logger.info("response received="+response);
		SendMessageResponse sendResponse = new Gson().fromJson(response, SendMessageResponse.class);
		if (!sendResponse.isOk()){
			throw new CyUrbanbotException(SENDMESSAGE_METHOD+" nok !");
		}
		
	}
	
	public void downloadFile(String telegramFilePath) throws CyUrbanbotException{
		CyUrbanBotUtility.httpDownload(botFileUrl+telegramFilePath, downloadPath, telegramFilePath);
	}
	
	public String getFilePath(String fileId) throws CyUrbanbotException{
		String response=null;
		try {
			String url=botUrl+GETFILE_METHOD+"?file_id="+fileId;
			//logger.info("url="+url);
			response=CyUrbanBotUtility.httpGet(url,null);
		} catch (CyUrbanbotException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw e1;
		}
		
		GetFileResponse fileResponse = new Gson().fromJson(response, GetFileResponse.class);
		if (!fileResponse.isOk()){
			throw new CyUrbanbotException(GETFILE_METHOD+" nok !");
		}
		
		return fileResponse.getResult().getFile_path();
	
	}
	
	
	@Override
	public String toString() {
		return "TelegramAPI [botUrl=" + botUrl + "]";
	}
	
	

}
