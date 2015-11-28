package org.cysoft.urbanbot.core.model;

public class SessionStatus {
	public static int INIT_STATUS_ID=0;
	public static int MAIN_MENU_STATUS_ID=1;
	public static int WARNING_STATUS_ID=2;
	public static int WARNING_IMGORLOC_STATUS_ID=3;
	public static int WARNING_IMG_STATUS_ID=4;
	public static int WARNING_SEL_OP_ID=5;
	public static int WARNING_SHOW_OP_ID=6;
	
	
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
