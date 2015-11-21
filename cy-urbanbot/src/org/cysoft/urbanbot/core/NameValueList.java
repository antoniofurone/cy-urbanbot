package org.cysoft.urbanbot.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class NameValueList {
	
	private List<NameValuePair> nvList=new ArrayList<NameValuePair>();
	
	public void add(String name,String value){
		nvList.add(new BasicNameValuePair(name,value));
	}
	
	public String toJSon(){
		String ret="{";
		boolean first=true;
		for(NameValuePair nv:nvList){
			ret+=(!first?",":"")+"\""+nv.getName()+"\":\""+nv.getValue()+"\"";
			first=false;
		}
		
		ret+="}";
		return ret;
	}
	
	public List<NameValuePair> toList(){
		return nvList;
	}
}
