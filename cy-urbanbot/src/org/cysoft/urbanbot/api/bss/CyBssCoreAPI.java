package org.cysoft.urbanbot.api.bss;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.cysoft.bss.core.model.AppParam;
import org.cysoft.bss.core.web.response.ICyBssResultConst;
import org.cysoft.bss.core.web.response.rest.AppResponse;
import org.cysoft.bss.core.web.response.rest.CyBssAuthLogOn;
import org.cysoft.urbanbot.common.CyUrbanBotUtility;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class CyBssCoreAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(CyBssCoreAPI.class);
	
	private String securityToken="";
	
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
		String response=null;
		try {
			response=CyUrbanBotUtility.httpGet(coreUrl+"/rest/app/"+appId+"/getMessage/"+languageCode+"/"+id, 
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
	
	
	@Override
	public String toString() {
		return "CyBssCoreAPI [appId=" + appId + ", coreUrl=" + coreUrl + "]";
	}
	
}
