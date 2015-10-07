package org.cysoft.urbanbot.main;


import org.cysoft.urbanbot.common.CyUrbanBotUtility;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.common.ICyUrbanbotConst;
import org.cysoft.urbanbot.response.GetUpdatesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class UrbanbotMain {
	
	private static final Logger logger = LoggerFactory.getLogger(UrbanbotMain.class);
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.info(">>> Start...");
		
		//System.setProperty("http.proxyHost", "lelapo.telecomitalia.local");
    	//System.setProperty("http.proxyPort", "8080");
    	
		String response=null;
		try {
			//System.out.println(">>> "+ICyUrbanbotConst.BOT_URL+ICyUrbanbotConst.GETUPDATES_METHOD+"?offset=0");
			
			response=CyUrbanBotUtility.httpCall(ICyUrbanbotConst.BOT_URL+
					ICyUrbanbotConst.GETUPDATES_METHOD+"?offset=0");
		} catch (CyUrbanbotException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("response="+response);

		GetUpdatesResponse updReponse = new Gson().fromJson(response, GetUpdatesResponse.class);
		
		if (updReponse.isOk()){
			System.out.println("ok !");

		}
		
		System.out.println("updReponse="+updReponse);

	}

}
