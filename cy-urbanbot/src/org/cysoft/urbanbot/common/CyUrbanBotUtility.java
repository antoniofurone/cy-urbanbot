package org.cysoft.urbanbot.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CyUrbanBotUtility {
	
	private static final Logger logger = LoggerFactory.getLogger(CyUrbanBotUtility.class);
	
	
	
	public static String httpPostBodyRequest(String url,
					List<NameValuePair> headerAttrs,
					String params) 
				throws CyUrbanbotException{
		
		String ret="";
		
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		
		if (headerAttrs!=null)
			for(NameValuePair attr:headerAttrs)
				httppost.setHeader(attr.getName(), attr.getValue());
		
		StringEntity sEntity = new StringEntity(params,"UTF-8");
		httppost.setEntity(sEntity);
		
		HttpResponse response;
		try {
			response = httpclient.execute(httppost);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new CyUrbanbotException(e);
		}
		
		HttpEntity entity = response.getEntity();

		if (entity != null) {
		    InputStream instream;
			
			try {
				instream = entity.getContent();
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new CyUrbanbotException(e);
			}
			
		    try {
		        // do something useful
		    	ret = getStringFromInputStream(instream);
		 
		    } finally {
		        try {
					instream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.getMessage());
					throw new CyUrbanbotException(e);
				}
		    }
		}
		
		return ret;
	}
	
	public static String httpPost(String url,
			List<NameValuePair> headerAttrs,
			List<NameValuePair> params) throws CyUrbanbotException{
		
		String ret="";
		
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		
		if (headerAttrs!=null)
			for(NameValuePair attr:headerAttrs)
				httppost.setHeader(attr.getName(), attr.getValue());
		
		
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		
		HttpResponse response;
		try {
			response = httpclient.execute(httppost);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		HttpEntity entity = response.getEntity();

		if (entity != null) {
		    InputStream instream;
			
			try {
				instream = entity.getContent();
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.toString());
				throw new CyUrbanbotException(e);
			}
			
		    try {
		        // do something useful
		    	ret = getStringFromInputStream(instream);
		 
		    } finally {
		        try {
					instream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.toString());
					throw new CyUrbanbotException(e);
				}
		    }
		}
		
		return ret;
	}
	
	
	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}
	
	public static String httpGet(String url,List<NameValuePair> headerAttrs) 
			throws CyUrbanbotException{
		String ret="";
		
		HttpClient httpclient = HttpClients.createDefault();
		URI uri=null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		
		HttpGet getRequest = new HttpGet(uri);
		
		if (headerAttrs!=null)
			for(NameValuePair attr:headerAttrs)
				getRequest.setHeader(attr.getName(), attr.getValue());
		
		
		HttpResponse response=null;
		try {
			response = httpclient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}

		if (response.getStatusLine().getStatusCode() != 200) {
			logger.error(response.toString());
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}
		
		HttpEntity entity = response.getEntity();

		if (entity != null) {
		    InputStream instream;
			
			try {
				instream = entity.getContent();
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.toString());
				throw new CyUrbanbotException(e);
			}
			
		    try {
		        // do something useful
		    	ret = getStringFromInputStream(instream);
		 
		    } finally {
		        try {
					instream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.toString());
					throw new CyUrbanbotException(e);
				}
		    }
		}
		
		return ret;
	}
	
	public static void httpDownload(String url,String downloadPath,String filePath) 
			throws CyUrbanbotException{
	
		logger.info(">>> httpDownload:"+url+";"+downloadPath+";"+filePath);
		
		File file=new File(downloadPath+filePath);
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		
		URL website=null;
		try {
			website = new URL(url);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile());
	        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
	        fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CyUrbanbotException(e);
		}
        
		logger.info("<<< httpDownload:"+url+";"+downloadPath+";"+filePath);
			
	}	
	
	public static String httpUpload(String url,String filePath,List<NameValuePair> headerAttrs,
			List<NameValuePair> params) throws CyUrbanbotException{
		
		return httpUpload(url,filePath,headerAttrs,params,"file");
	}
	
	public static String httpUpload(String url,String filePath,List<NameValuePair> headerAttrs,
			List<NameValuePair> params,String fileParamName) throws CyUrbanbotException{
		
		logger.info(">>> httpUpload:"+url+";"+filePath);
		String ret="";
		
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		   
		if (headerAttrs!=null)
			for(NameValuePair attr:headerAttrs)
				httppost.setHeader(attr.getName(), attr.getValue());
		
		
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addPart(fileParamName,new FileBody(new File(filePath)));
		for(NameValuePair param:params){
			builder.addPart(param.getName(),new StringBody(param.getValue(),ContentType.TEXT_PLAIN));
		}
        HttpEntity reqEntity = builder.build();
		  
        httppost.setEntity(reqEntity);
        
        HttpResponse response;
		try {
			response = httpclient.execute(httppost);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			throw new CyUrbanbotException(e);
		}
		
		HttpEntity entity = response.getEntity();

		if (entity != null) {
		    InputStream instream;
			
			try {
				instream = entity.getContent();
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.toString());
				throw new CyUrbanbotException(e);
			}
			
		    try {
		        // do something useful
		    	ret = getStringFromInputStream(instream);
		    } finally {
		        try {
					instream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.toString());
					throw new CyUrbanbotException(e);
				}
		    }
		}
		
		logger.info("<<< httpUpload:"+url+";"+filePath);
		return ret;

	}
	
	public static Date getCurrentTime(){
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		return now;
	}
	
	
	
}
