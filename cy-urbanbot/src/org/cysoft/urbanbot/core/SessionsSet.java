package org.cysoft.urbanbot.core;

import java.util.LinkedHashMap;
import java.util.Map;

import org.cysoft.urbanbot.core.model.Session;

public abstract class SessionsSet {
	
	protected Map <Long,Session> sessionsMap=null;

	public SessionsSet(){
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
	
}
