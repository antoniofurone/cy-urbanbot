package org.cysoft.urbanbot.api.bss;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.cysoft.bss.core.model.AppParam;
import org.cysoft.bss.core.model.CyBssFile;
import org.cysoft.bss.core.model.Location;
import org.cysoft.bss.core.model.Ticket;
import org.cysoft.bss.core.model.TicketCategory;
import org.cysoft.bss.core.web.response.ICyBssResultConst;
import org.cysoft.bss.core.web.response.file.FileListResponse;
import org.cysoft.bss.core.web.response.file.FileResponse;
import org.cysoft.bss.core.web.response.rest.AppResponse;
import org.cysoft.bss.core.web.response.rest.CyBssAuthLogOn;
import org.cysoft.bss.core.web.response.rest.LocationListResponse;
import org.cysoft.bss.core.web.response.rest.LocationResponse;
import org.cysoft.bss.core.web.response.rest.PersonResponse;
import org.cysoft.bss.core.web.response.rest.TicketCategoryListResponse;
import org.cysoft.bss.core.web.response.rest.TicketListResponse;
import org.cysoft.bss.core.web.response.rest.TicketResponse;
import org.cysoft.urbanbot.common.CyUrbanBotUtility;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.NameValueList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class CyBssCoreAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(CyBssCoreAPI.class);
	
	private String securityToken="";
	
	private final static String STORY_LOC_TYPE="Story Location";
	
	private static CyBssCoreAPI instance=null;
	public static CyBssCoreAPI getInstance(){
		if (instance==null)
			instance=new CyBssCoreAPI();
		return instance;
	}
	
	private CyBssCoreAPI() {
	}
	
	
	
	private long appId;
	public long getAppId() {
		return appId;
	}
	
	private String appName="";
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}



	private String coreUrl="";
	public String getCoreUrl() {
		return coreUrl;
	}
	public void setCoreUrl(String coreUrl) {
		this.coreUrl = coreUrl;
	}
	
	private String downloadPath="";
	public String getDownloadPath() {
		return downloadPath;
	}
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}
	
	
	private long updatesOffSet=0;
	public long getUpdatesOffSet() throws CyUrbanbotException {
		
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		
		String response=null;
		try {
			response=CyUrbanBotUtility.httpGet(coreUrl+"/rest/app/"+appId+"/getVariable/updatesOffSet", 
					headerAttrs);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		
		AppResponse appResponse = new Gson().fromJson(response, AppResponse.class);
		if (!appResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(appResponse.getResultCode()+":"+appResponse.getResultDesc());
			throw new CyUrbanbotException(appResponse.getResultCode()+":"+appResponse.getResultDesc());
		}
		
		if (appResponse.getAppVariable()!=null)
			updatesOffSet=Long.parseLong(appResponse.getAppVariable().getValue());
		
		return updatesOffSet;
	}
	
	
	public void setUpdatesOffSet(long updatesOffSet) throws CyUrbanbotException {
		
		this.updatesOffSet = updatesOffSet;
		
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		
		String jsonString="{"
				+ "\"appId\":\""+appId
				+"\",\"name\":\"updatesOffSet\",\"value\":\""+updatesOffSet+"\""
				+",\"type\":\"N\""
				+ "}";
		
		String response;
		try {
			response = CyUrbanBotUtility.httpPostBodyRequest(
					coreUrl+"/rest/app/"+appId+"/putVariable",
					headerAttrs,
					jsonString);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		AppResponse appResponse = new Gson().fromJson(response, AppResponse.class);
		if (!appResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(appResponse.getResultCode()+":"+appResponse.getResultDesc());
			throw new CyUrbanbotException(appResponse.getResultCode()+":"+appResponse.getResultDesc());
		}
		
	}
	
	
	public void logOn(String userId,String pwd) throws CyUrbanbotException{
		String response=null;
		
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		
		String jsonString="{\"userId\":\""+userId+"\",\"pwd\":\""+pwd+"\"}";
		
		response=CyUrbanBotUtility.httpPostBodyRequest(
				coreUrl+"/rest/cybss-auth/logOn",
				headerAttrs,
				jsonString);
		
		CyBssAuthLogOn authLogOn = new Gson().fromJson(response, CyBssAuthLogOn.class);
		if (!authLogOn.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(authLogOn.getResultCode()+":"+authLogOn.getResultDesc());
			throw new CyUrbanbotException(authLogOn.getResultCode()+":"+authLogOn.getResultDesc());
		}
		
		securityToken=authLogOn.getSecurityToken();
		
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		try {
			response=CyUrbanBotUtility.httpGet(coreUrl+"/rest/app/getByName?name="+appName, 
					headerAttrs);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		AppResponse appResponse = new Gson().fromJson(response, AppResponse.class);
		if (!appResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(appResponse.getResultCode()+":"+appResponse.getResultDesc());
			throw new CyUrbanbotException(appResponse.getResultCode()+":"+appResponse.getResultDesc());
		}
		logger.info(appResponse.getApp().toString());
		appId=appResponse.getApp().getId();
		
		
	}
	
	public void logOff(){
		
	}
	
	
	public AppParam getParam(String name) throws CyUrbanbotException{
		
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		String response=null;
		try {
			response=CyUrbanBotUtility.httpGet(coreUrl+"/rest/app/"+appId+"/getParam/"+name, 
					headerAttrs);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		
		AppResponse appResponse = new Gson().fromJson(response, AppResponse.class);
		if (!appResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(appResponse.getResultCode()+":"+appResponse.getResultDesc());
			throw new CyUrbanbotException(appResponse.getResultCode()+":"+appResponse.getResultDesc());
		}
		
		if (appResponse.getAppParam()!=null)
			return appResponse.getAppParam();
		else
			return null;
	}
	
	public String getMessage(String id,String languageCode) throws CyUrbanbotException{
		String ret="Sorry ! I don't know reply."; 
	
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		headerAttrs.add(new BasicNameValuePair("Language",languageCode));
		
		String response=null;
		try {
			response=CyUrbanBotUtility.httpGet(coreUrl+"/rest/app/"+appId+"/getMessage/"+id, 
					headerAttrs);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		

		AppResponse appResponse = new Gson().fromJson(response, AppResponse.class);
		if (!appResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(appResponse.getResultCode()+":"+appResponse.getResultDesc());
			throw new CyUrbanbotException(appResponse.getResultCode()+":"+appResponse.getResultDesc());
		}
		
		if (appResponse.getAppMessage()!=null)
			ret=appResponse.getAppMessage().getText();
		
		return ret;
	}
	
	
	public long updatePerson(String code,String firstName, String secondName)
		throws CyUrbanbotException
	{
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		String response=null;
		try {
			response=CyUrbanBotUtility.httpGet(coreUrl+"/rest/person/getByCode?code="+code, 
					headerAttrs);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		PersonResponse personResponse = new Gson().fromJson(response, PersonResponse.class);
		if (personResponse.getResultCode().equals(ICyBssResultConst.RESULT_NOT_FOUND)){
			// add
				
			NameValueList nvl=new NameValueList(); 
			nvl.add("code", code);
			nvl.add("firstName", firstName);
			nvl.add("secondName", secondName);
			
			response=CyUrbanBotUtility.httpPostBodyRequest(
					coreUrl+"/rest/person/add",
					headerAttrs,
					nvl.toJSon());
			
			personResponse = new Gson().fromJson(response, PersonResponse.class);
			if (!personResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
				logger.error(personResponse.getResultCode()+":"+personResponse.getResultDesc());
				throw new CyUrbanbotException(personResponse.getResultCode()+":"+personResponse.getResultDesc());
			}
			
			return personResponse.getPerson().getId();
			
		}
		else {
			if (!personResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
				logger.error(personResponse.getResultCode()+":"+personResponse.getResultDesc());
				throw new CyUrbanbotException(personResponse.getResultCode()+":"+personResponse.getResultDesc());
			}
			// update
			long ret=personResponse.getPerson().getId();
			
			NameValueList nvl=new NameValueList(); 
			nvl.add("code", code);
			nvl.add("firstName", firstName);
			nvl.add("secondName", secondName);
			
			response=CyUrbanBotUtility.httpPostBodyRequest(
					coreUrl+"/rest/person/"+ret+"/update",
					headerAttrs,
					nvl.toJSon());
			
			personResponse = new Gson().fromJson(response, PersonResponse.class);
			if (!personResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
				logger.error(personResponse.getResultCode()+":"+personResponse.getResultDesc());
				throw new CyUrbanbotException(personResponse.getResultCode()+":"+personResponse.getResultDesc());
			}
			
			return ret;
		}
		
	}
	
	
	public long addStory(double latitude, double longitude,String description,long personId) 
			throws CyUrbanbotException{
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		
		NameValueList nvl=new NameValueList(); 
		nvl.add("name", personId+","+CyUrbanBotUtility.getCurrentTime().getTime());
		nvl.add("personId", (new Long(personId)).toString());
		nvl.add("latitude", (new Double(latitude)).toString());
		nvl.add("longitude", (new Double(longitude)).toString());
		nvl.add("description", description);
		nvl.add("locationType", STORY_LOC_TYPE);
	
		String response;
		try {
			response = CyUrbanBotUtility.httpPostBodyRequest(
					coreUrl+"/rest/location/add",
					headerAttrs,
					nvl.toJSon());
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		//System.out.println("response="+response);
		LocationResponse locationResponse = new Gson().fromJson(response, LocationResponse.class);
		if (!locationResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(locationResponse.getResultCode()+":"+locationResponse.getResultDesc());
			throw new CyUrbanbotException(locationResponse.getResultCode()+":"+locationResponse.getResultDesc());
		}
		
		return locationResponse.getLocation().getId();
	}
	
	
	public void addStoryFile(long storyId,String filePath,String fileType) throws CyUrbanbotException{
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		
		NameValueList nvl=new NameValueList(); 
		nvl.add("entityId", (new Long(storyId)).toString());
		nvl.add("entityName", Location.ENTITY_NAME);
		nvl.add("fileType", fileType);
		String response;
		try {
			response = CyUrbanBotUtility.httpUpload(
					coreUrl+"/fileservice/file/upload",
					filePath,
					headerAttrs,
					nvl.toList());
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		FileResponse fileResponse = new Gson().fromJson(response, FileResponse.class);
		if (!fileResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(fileResponse.getResultCode()+":"+fileResponse.getResultDesc());
			throw new CyUrbanbotException(fileResponse.getResultCode()+":"+fileResponse.getResultDesc());
		}
		
	}
	
	public List<Location> findStories(long personId, String languageCode) throws CyUrbanbotException{
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		headerAttrs.add(new BasicNameValuePair("Language",languageCode));

		String response=null;
		try {
			response=CyUrbanBotUtility.httpGet(coreUrl+"/rest/location/find?personId="+personId+"&locationType=Story%20Location", 
					headerAttrs);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		

		LocationListResponse locationListResponse = new Gson().fromJson(response, LocationListResponse.class);
		if (!locationListResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(locationListResponse.getResultCode()+":"+locationListResponse.getResultDesc());
			throw new CyUrbanbotException(locationListResponse.getResultCode()+":"+locationListResponse.getResultDesc());
		}
		
		return locationListResponse.getLocations();
	}

	public Location getStory(long storyId, String languageCode) throws CyUrbanbotException{
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		headerAttrs.add(new BasicNameValuePair("Language",languageCode));

		String response=null;
		try {
			response=CyUrbanBotUtility.httpGet(coreUrl+"/rest/location/"+storyId+"/get", 
					headerAttrs);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		

		LocationResponse locationResponse = new Gson().fromJson(response, LocationResponse.class);
		if (!locationResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(locationResponse.getResultCode()+":"+locationResponse.getResultDesc());
			throw new CyUrbanbotException(locationResponse.getResultCode()+":"+locationResponse.getResultDesc());
		}
		
		return locationResponse.getLocation();
	}

	public List<CyBssFile> getStoryFiles(long storyId)throws CyUrbanbotException{
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		
		String response=null;
		try {
			response=CyUrbanBotUtility.httpGet(coreUrl+"/rest/location/"+storyId+"/getFiles", 
					headerAttrs);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		FileListResponse filesResponse = new Gson().fromJson(response, FileListResponse.class);
		if (!filesResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(filesResponse.getResultCode()+":"+filesResponse.getResultDesc());
			throw new CyUrbanbotException(filesResponse.getResultCode()+":"+filesResponse.getResultDesc());
		}
		
		return filesResponse.getFiles();
	}
	
	
	public long addWarn(String text,long categoryId,long personId) throws CyUrbanbotException{
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		
		NameValueList nvl=new NameValueList(); 
		nvl.add("text", text);
		nvl.add("personId", (new Long(personId)).toString());
		nvl.add("categoryId", (new Long(categoryId)).toString());
		
		String response;
		try {
			response = CyUrbanBotUtility.httpPostBodyRequest(
					coreUrl+"/rest/ticket/add",
					headerAttrs,
					nvl.toJSon());
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		
		//System.out.println("response="+response);
		TicketResponse ticketResponse = new Gson().fromJson(response, TicketResponse.class);
		if (!ticketResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(ticketResponse.getResultCode()+":"+ticketResponse.getResultDesc());
			throw new CyUrbanbotException(ticketResponse.getResultCode()+":"+ticketResponse.getResultDesc());
		}
		
		return ticketResponse.getTicket().getId();
	}
	
	public void removeWarn(long id) throws CyUrbanbotException{
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		
		String response=null;
		try {
			response=CyUrbanBotUtility.httpGet(coreUrl+"/rest/ticket/"+id+"/remove", 
					headerAttrs);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
	
		TicketListResponse ticketListResponse = new Gson().fromJson(response, TicketListResponse.class);
		if (!ticketListResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(ticketListResponse.getResultCode()+":"+ticketListResponse.getResultDesc());
			throw new CyUrbanbotException(ticketListResponse.getResultCode()+":"+ticketListResponse.getResultDesc());
		}
		
	}
	
	public List<Ticket> findWarns(long personId, String languageCode) throws CyUrbanbotException{
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		headerAttrs.add(new BasicNameValuePair("Language",languageCode));

		String response=null;
		try {
			response=CyUrbanBotUtility.httpGet(coreUrl+"/rest/ticket/find?personId="+personId, 
					headerAttrs);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		

		TicketListResponse ticketListResponse = new Gson().fromJson(response, TicketListResponse.class);
		if (!ticketListResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(ticketListResponse.getResultCode()+":"+ticketListResponse.getResultDesc());
			throw new CyUrbanbotException(ticketListResponse.getResultCode()+":"+ticketListResponse.getResultDesc());
		}
		
		return ticketListResponse.getTickets();
	}

	public Ticket getWarn(long warnId, String languageCode) throws CyUrbanbotException{
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		headerAttrs.add(new BasicNameValuePair("Language",languageCode));

		String response=null;
		try {
			response=CyUrbanBotUtility.httpGet(coreUrl+"/rest/ticket/"+warnId+"/get", 
					headerAttrs);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		

		TicketResponse ticketResponse = new Gson().fromJson(response, TicketResponse.class);
		if (!ticketResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(ticketResponse.getResultCode()+":"+ticketResponse.getResultDesc());
			throw new CyUrbanbotException(ticketResponse.getResultCode()+":"+ticketResponse.getResultDesc());
		}
		
		return ticketResponse.getTicket();
	}

	public List<TicketCategory> getWarnCategories(String languageCode)throws CyUrbanbotException{
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		headerAttrs.add(new BasicNameValuePair("Language",languageCode));
		
		String response=null;
		try {
			response=CyUrbanBotUtility.httpGet(coreUrl+"/rest/ticket/getCategoryAll", 
					headerAttrs);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		

		TicketCategoryListResponse ticketCategoryListResponse = new Gson().fromJson(response, TicketCategoryListResponse.class);
		if (!ticketCategoryListResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(ticketCategoryListResponse.getResultCode()+":"+ticketCategoryListResponse.getResultDesc());
			throw new CyUrbanbotException(ticketCategoryListResponse.getResultCode()+":"+ticketCategoryListResponse.getResultDesc());
		}
		
		return ticketCategoryListResponse.getTicketCategories();
		
	}
	
	public List<CyBssFile> getWarnFiles(long warnId)throws CyUrbanbotException{
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		
		String response=null;
		try {
			response=CyUrbanBotUtility.httpGet(coreUrl+"/rest/ticket/"+warnId+"/getFiles", 
					headerAttrs);
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		FileListResponse filesResponse = new Gson().fromJson(response, FileListResponse.class);
		if (!filesResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(filesResponse.getResultCode()+":"+filesResponse.getResultDesc());
			throw new CyUrbanbotException(filesResponse.getResultCode()+":"+filesResponse.getResultDesc());
		}
		
		return filesResponse.getFiles();
	}
	
	public void downloadFile(long fileId,String fileName) throws CyUrbanbotException{
		CyUrbanBotUtility.httpDownload(coreUrl+"/fileservice/file/"+fileId+"/download", downloadPath, fileName);
	}
	
	
	public void addWarnLoc(long warnId, double latitude, double longitude) throws CyUrbanbotException{
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		
		
		NameValueList nvl=new NameValueList(); 
		nvl.add("id", (new Long(warnId)).toString());
		nvl.add("latitude", (new Double(latitude)).toString());
		nvl.add("longitude", (new Double(longitude)).toString());
		
		String response;
		try {
			response = CyUrbanBotUtility.httpPostBodyRequest(
					coreUrl+"/rest/ticket/"+warnId+"/setLocation",
					headerAttrs,
					nvl.toJSon());
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		TicketResponse ticketResponse = new Gson().fromJson(response, TicketResponse.class);
		if (!ticketResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(ticketResponse.getResultCode()+":"+ticketResponse.getResultDesc());
			throw new CyUrbanbotException(ticketResponse.getResultCode()+":"+ticketResponse.getResultDesc());
		}
		
	
	}
	
	public void addWarnFile(long warnId,String filePath,String fileType) throws CyUrbanbotException{
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Security-Token",securityToken));
		
		NameValueList nvl=new NameValueList(); 
		nvl.add("entityId", (new Long(warnId)).toString());
		nvl.add("entityName", Ticket.ENTITY_NAME);
		nvl.add("fileType", fileType);
		String response;
		try {
			response = CyUrbanBotUtility.httpUpload(
					coreUrl+"/fileservice/file/upload",
					filePath,
					headerAttrs,
					nvl.toList());
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		FileResponse fileResponse = new Gson().fromJson(response, FileResponse.class);
		if (!fileResponse.getResultCode().equals(ICyBssResultConst.RESULT_OK)){
			logger.error(fileResponse.getResultCode()+":"+fileResponse.getResultDesc());
			throw new CyUrbanbotException(fileResponse.getResultCode()+":"+fileResponse.getResultDesc());
		}
	
		
	}
	
	@Override
	public String toString() {
		return "CyBssCoreAPI [appId=" + appId + ", coreUrl=" + coreUrl + "]";
	}
	
}
