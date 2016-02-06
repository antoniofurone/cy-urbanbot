package org.cysoft.urbanbot.api.telegram;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.api.telegram.response.GetFileResponse;
import org.cysoft.urbanbot.api.telegram.response.GetUpdatesResponse;
import org.cysoft.urbanbot.api.telegram.response.SendMessageResponse;
import org.cysoft.urbanbot.common.CyUrbanBotUtility;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.common.NameValueList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

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
	private static final String SENDDOCUMENT_METHOD="sendDocument";
	
	public static final String MESSAGE_PARSEMODE_MARKDOWN="Markdown";
	public static final String MESSAGE_PARSEMODE_HTML="HTML";
	
	
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
		
		GetUpdatesResponse updResponse =null;
		try{
			updResponse=new Gson().fromJson(response, GetUpdatesResponse.class);
			if (!updResponse.isOk()){
				throw new CyUrbanbotException(GETUPDATES_METHOD+" nok !");
			}
		}
		catch(Exception e){
			logger.error(e.toString());
			logger.error("response="+response);
			throw new CyUrbanbotException(e);
		}
		return updResponse.getResult();
	
	}
	
	public void sendMessage(String text,long chatId) throws CyUrbanbotException{
		sendMessage(text,chatId,0,null,null);
	}
	
	public void sendMessage(String text,long chatId,long replyToMessageId) throws CyUrbanbotException{
		sendMessage(text,chatId,replyToMessageId,null,null);
	}
	
	public void sendMessage(String text,long chatId,long replyToMessageId,List<List<String>> keyboard) throws CyUrbanbotException{
		sendMessage(text,chatId,replyToMessageId,null,keyboard);
	}
	
	public void sendMessage(String text,long chatId,long replyToMessageId,String parseMode) throws CyUrbanbotException{
		sendMessage(text,chatId,replyToMessageId,parseMode,null);
	}
	
	public void sendMessage(String text,long chatId,long replyToMessageId,String parseMode,List<List<String>> keyboard) throws CyUrbanbotException{
		String response=null;
		try {
			
			List<NameValuePair> parms = new ArrayList<NameValuePair>();
			parms.add(new BasicNameValuePair("chat_id", chatId + ""));
			parms.add(new BasicNameValuePair("text", text));
			if (parseMode!=null && !parseMode.equals(""))
				parms.add(new BasicNameValuePair("parse_mode", parseMode));
			if (replyToMessageId!=0)
				parms.add(new BasicNameValuePair("reply_to_message_id", replyToMessageId+""));
			if (keyboard!=null && !keyboard.isEmpty()){
				JsonObject jsonObject = new JsonObject();

		        /// Convert the List<List<String>> to JSONArray
		        JsonArray jsonkeyboard = new JsonArray();
		        for (List<String> innerRow : keyboard) {
		            JsonArray innerJSONKeyboard = new JsonArray();
		            for (String element: innerRow) {
		                innerJSONKeyboard.add(new JsonPrimitive(element));
		            }
		            jsonkeyboard.add(innerJSONKeyboard);
		        }
		        jsonObject.add("keyboard", jsonkeyboard);
		        jsonObject.add("one_time_keyboard", new JsonPrimitive(true));
		        jsonObject.add("resize_keyboard",new JsonPrimitive(true));
		       
		        parms.add(new BasicNameValuePair("reply_markup", jsonObject.toString()));
		        
		        //logger.info("reply_markup="+jsonObject.toString());
			        
			}// end if keyboard
			
			
			response=CyUrbanBotUtility.httpPost(botUrl+SENDMESSAGE_METHOD, null, parms);
			
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			logger.error("response="+response);
			throw e;
		} 
		
		//logger.info("response received="+response);
		SendMessageResponse sendResponse =null;
		try{
			sendResponse=new Gson().fromJson(response, SendMessageResponse.class);
			if (!sendResponse.isOk()){
				throw new CyUrbanbotException(SENDMESSAGE_METHOD+" nok !");
			}
		}
		catch(Exception e){
			logger.error(e.toString());
			logger.error("response="+response);
			throw new CyUrbanbotException(e);
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
			logger.error("response="+response);
			throw e;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			logger.error("response="+response);
			throw new CyUrbanbotException(e);
		}
		
		//logger.info("response received="+response);
		SendMessageResponse sendResponse =null;
		try{
			sendResponse=new Gson().fromJson(response, SendMessageResponse.class);
			if (!sendResponse.isOk()){
				throw new CyUrbanbotException(SENDLOCATION_METHOD+" nok !");
			}
		}
		catch(Exception e){
			logger.error(e.toString());
			logger.error("response="+response);
			throw new CyUrbanbotException(e);
		}
	}
	
	public void sendPhoto(String filePath,long chatId,long replyToMessageId,String caption) throws CyUrbanbotException{
		
		NameValueList nvl=new NameValueList(); 
		nvl.add("chat_id", (new Long(chatId)).toString());
		if (caption!=null && !caption.equals(""))
			nvl.add("caption", caption);
		if (replyToMessageId!=0)
			nvl.add("reply_to_message_id", (new Long(replyToMessageId)).toString());
		
		
		String response=null;
		try {
			response = CyUrbanBotUtility.httpUpload(
					botUrl+SENDPHOTO_METHOD,
					downloadPath+filePath,
					null,
					nvl.toList(),"photo");
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			logger.error("response="+response);
			throw new CyUrbanbotException(e);
		}
		
		//logger.info("response received="+response);
		SendMessageResponse sendResponse =null;
		try{
			sendResponse=new Gson().fromJson(response, SendMessageResponse.class);
			if (!sendResponse.isOk()){
				throw new CyUrbanbotException(SENDPHOTO_METHOD+" nok !");
			}
		}
		catch(Exception e){
			logger.error(e.toString());
			logger.error("response="+response);
			throw new CyUrbanbotException(e);
		}
		
	}
	
	public void sendVideo(String filePath,long chatId,long replyToMessageId,String caption) throws CyUrbanbotException{
		
		NameValueList nvl=new NameValueList(); 
		nvl.add("chat_id", (new Long(chatId)).toString());
		if (caption!=null && !caption.equals(""))
			nvl.add("caption", caption);
		if (replyToMessageId!=0)
			nvl.add("reply_to_message_id", (new Long(replyToMessageId)).toString());
		
		
		String response=null;
		try {
			response = CyUrbanBotUtility.httpUpload(
					botUrl+SENDVIDEO_METHOD,
					downloadPath+filePath,
					null,
					nvl.toList(),"video");
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			logger.error("response="+response);
			throw new CyUrbanbotException(e);
		}
		
		//logger.info("response received="+response);
		SendMessageResponse sendResponse =null;
		try{
			sendResponse=new Gson().fromJson(response, SendMessageResponse.class);
			if (!sendResponse.isOk()){
				throw new CyUrbanbotException(SENDVIDEO_METHOD+" nok !");
			}
		}
		catch(Exception e){
			logger.error(e.toString());
			logger.error("response="+response);
			throw new CyUrbanbotException(e);
		}
		
	}
	
	
	public void sendAudio(String filePath,long chatId,long replyToMessageId, String mimeType) throws CyUrbanbotException{
		
		NameValueList nvl=new NameValueList(); 
		nvl.add("chat_id", (new Long(chatId)).toString());
		if (replyToMessageId!=0)
			nvl.add("reply_to_message_id", (new Long(replyToMessageId)).toString());
		if (mimeType!=null && !mimeType.equals(""))
			nvl.add("mime_type", mimeType);
		
		String response=null;
		try {
			response = CyUrbanBotUtility.httpUpload(
					botUrl+SENDAUDIO_METHOD,
					downloadPath+filePath,
					null,
					nvl.toList(),"audio");
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			logger.error("response="+response);
			throw new CyUrbanbotException(e);
		}
		
		logger.info("response received="+response);
		SendMessageResponse sendResponse =null;
		try{
			sendResponse=new Gson().fromJson(response, SendMessageResponse.class);
			if (!sendResponse.isOk()){
				throw new CyUrbanbotException(SENDAUDIO_METHOD+" nok !");
			}
		}
		catch(Exception e){
			logger.error(e.toString());
			logger.error("response="+response);
			throw new CyUrbanbotException(e);
		}
		
	}
	
	public void sendVoice(String filePath,long chatId,long replyToMessageId) throws CyUrbanbotException{
		
		NameValueList nvl=new NameValueList(); 
		nvl.add("chat_id", (new Long(chatId)).toString());
		if (replyToMessageId!=0)
			nvl.add("reply_to_message_id", (new Long(replyToMessageId)).toString());
		
		
		String response=null;
		try {
			response = CyUrbanBotUtility.httpUpload(
					botUrl+SENDVOICE_METHOD,
					downloadPath+filePath,
					null,
					nvl.toList(),"voice");
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			logger.error("response="+response);
			throw new CyUrbanbotException(e);
		}
		
		//logger.info("response received="+response);
		SendMessageResponse sendResponse =null;
		try{
			sendResponse=new Gson().fromJson(response, SendMessageResponse.class);
			if (!sendResponse.isOk()){
				throw new CyUrbanbotException(SENDVOICE_METHOD+" nok !");
			}
		}
		catch(Exception e){
			logger.error(e.toString());
			logger.error("response="+response);
			throw new CyUrbanbotException(e);
		}
	}
	
	public void sendDocument(String filePath,long chatId,long replyToMessageId) throws CyUrbanbotException{
		
		NameValueList nvl=new NameValueList(); 
		nvl.add("chat_id", (new Long(chatId)).toString());
		if (replyToMessageId!=0)
			nvl.add("reply_to_message_id", (new Long(replyToMessageId)).toString());
		
		
		String response=null;
		try {
			response = CyUrbanBotUtility.httpUpload(
					botUrl+SENDDOCUMENT_METHOD,
					downloadPath+filePath,
					null,
					nvl.toList(),"document");
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			logger.error("response="+response);
			throw new CyUrbanbotException(e);
		}
		
		logger.info("response received="+response);
		SendMessageResponse sendResponse =null;
		try{
			sendResponse=new Gson().fromJson(response, SendMessageResponse.class);
			if (!sendResponse.isOk()){
				throw new CyUrbanbotException(SENDDOCUMENT_METHOD+" nok !");
			}
		}
		catch(Exception e){
			logger.error(e.toString());
			logger.error("response="+response);
			throw new CyUrbanbotException(e);
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
			logger.error(e1.toString());
			logger.error("response="+response);
			throw e1;
		}
		
		GetFileResponse fileResponse =null;
		try{
			fileResponse=new Gson().fromJson(response, GetFileResponse.class);
			if (!fileResponse.isOk()){
				throw new CyUrbanbotException(GETFILE_METHOD+" nok !");
			}
		}
		catch(Exception e){
			logger.error(e.toString());
			logger.error("response="+response);
			throw new CyUrbanbotException(e);
		}
		
		return fileResponse.getResult().getFile_path();
	
	}
	
	
	@Override
	public String toString() {
		return "TelegramAPI [botUrl=" + botUrl + "]";
	}
	
	

}
