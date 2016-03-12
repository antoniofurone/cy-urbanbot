package org.cysoft.urbanbot.core;


public class LiveSessions  extends SessionsSet{
private static LiveSessions instance=null;
	
	private LiveSessions(){
		super();
	}
	
	public static LiveSessions getInstance(){
		if (instance==null)
			instance=new LiveSessions();
		return instance;
	}
	
}
