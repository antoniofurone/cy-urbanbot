package org.cysoft.urbanbot.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class CyUrbanBotUtility {
	
	public static String httpCall(String pUrl) 
		throws CyUrbanbotException
	
	{
		
		String response="";

		URL url=null;
		try {
			url = new URL(ICyUrbanbotConst.BOT_URL+ICyUrbanbotConst.GETUPDATES_METHOD);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CyUrbanbotException(e);
		}
		
		HttpURLConnection conn=null;
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CyUrbanbotException(e);
		}
		try {
			conn.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CyUrbanbotException(e);
		}
		conn.setRequestProperty("Accept", "application/json");
		
		try {
			if (conn.getResponseCode() != 200) {
				throw new CyUrbanbotException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CyUrbanbotException(e);
		}

		BufferedReader br=null;
		try {
			br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CyUrbanbotException(e);
		}

		String output;
		System.out.println("Output from Server .... \n");
		try {
			while ((output = br.readLine()) != null) {
				response+=output;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CyUrbanbotException(e);
		}

		conn.disconnect();
	
		return response;
	}

}
