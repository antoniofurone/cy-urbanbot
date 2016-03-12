package org.cysoft.urbanbot.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.cysoft.urbanbot.core.model.Session;

public abstract class SessionsSet {
	
	protected Map <Long,Session> sessionsMap=null;

	protected SessionsSet(){
		sessionsMap =  new LinkedHashMap<Long,Session>();
	}
	
	
	public void add(Session s){
		sessionsMap.put(s.getId(), s);
	}
	
	public void remove(long id){
		sessionsMap.remove(id);	}
	
	public boolean containsKey(long id){
		return sessionsMap.containsKey(id);
	}
	
	public Session get(long id){
		return sessionsMap.get(id);
	}
	
	public List<Session> getSessionsList(){
		List<Session> ret=new ArrayList<Session>();
		for (Map.Entry<Long,Session> entry : sessionsMap.entrySet()) {
			ret.add(entry.getValue());
		}
		return ret;
	}
}
