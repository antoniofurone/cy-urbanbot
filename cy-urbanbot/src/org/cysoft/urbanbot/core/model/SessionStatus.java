package org.cysoft.urbanbot.core.model;

public class SessionStatus {
	public static int INIT_STATUS_ID=0;
	public static int MAIN_MENU_STATUS_ID=1;
	
	private int id=INIT_STATUS_ID;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	@Override
	public String toString() {
		return "SessionStatus [id=" + id + "]";
	}
	
}
