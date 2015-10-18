package org.cysoft.urbanbot.core;


public class LiveSessions  extends SessionsSet{
private static LiveSessions instance=null;
	
	public static LiveSessions getInstance(){
		if (instance==null)
			instance=new LiveSessions();
		return instance;
	}
	
}
