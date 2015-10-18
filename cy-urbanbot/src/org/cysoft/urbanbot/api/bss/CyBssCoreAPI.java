package org.cysoft.urbanbot.api.bss;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.cysoft.bss.core.model.AppParam;
import org.cysoft.bss.core.rest.response.cybssauth.CyBssAuthLogOn;
import org.cysoft.urbanbot.common.CyUrbanBotUtility;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class CyBssCoreAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(CyBssCoreAPI.class);
	
	private final String AUTH_SERVICE="/cybss-auth";
	
	private final String LOGON_METHOD="/logOn";
	
	private String securityToken="";
	
	private static CyBssCoreAPI instance=null;
	public static CyBssCoreAPI getInstance(){
		if (instance==null)
			instance=new CyBssCoreAPI();
		return instance;
	}
	
	private CyBssCoreAPI() {
	}
	
	private String appId="";
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	private String coreUrl="";
	public String getCoreUrl() {
		return coreUrl;
	}
	public void setCoreUrl(String coreUrl) {
		this.coreUrl = coreUrl;
	}
	
	
	private long updatesOffSet=0;
	public long getUpdatesOffSet() {
		return updatesOffSet;
	}
	public void setUpdatesOffSet(long updatesOffSet) {
		this.updatesOffSet = updatesOffSet;
	}
	
	
	public void logOn(String userId,String pwd) throws CyUrbanbotException{
		String response=null;
		
		List<NameValuePair> headerAttrs=new ArrayList<NameValuePair>();
		headerAttrs.add(new BasicNameValuePair("Content-Type","application/json"));
		
		String jsonString="{\"userId\":\""+userId+"\",\"pwd\":\""+pwd+"\"}";
		
		response=CyUrbanBotUtility.httpPostBodyRequest(
				coreUrl+"/rest"+AUTH_SERVICE+LOGON_METHOD,
				headerAttrs,
				jsonString);
		
		CyBssAuthLogOn authLogOn = new Gson().fromJson(response, CyBssAuthLogOn.class);
		securityToken=authLogOn.getSecurityToken();
	}
	
	public void logOff(){
		
	}
	
	
	public AppParam getParam(String name){
		AppParam ret=null;
		
		if (name.equalsIgnoreCase("bot.url")){
			ret=new AppParam();
			ret.setName(name);
			ret.setValue("https://api.telegram.org/bot130643009:AAGW77QBGQV4A7G494zA_w2DfzCR0zqULCM/");
			ret.setType("S");
		}
		
		return ret;
	}
	
	@Override
	public String toString() {
		return "CyBssCoreAPI [appId=" + appId + ", coreUrl=" + coreUrl + "]";
	}
	
}
