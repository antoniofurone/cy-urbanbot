package org.cysoft.urbanbot.core.model;

public class SessionStatus {
	public static int INIT_STATUS_ID=0;
	public static int MAIN_MENU_STATUS_ID=1;
	
	public static int WARN_TEXT_STATUS_ID=2;
	public static int WARN_IMGLOC_STATUS_ID=3;
	public static int WARN_IMG_STATUS_ID=4;
	public static int WARN_SELOP_STATUS_ID=5;
	public static int WARN_SHOWOP_STATUS_ID=6;
	public static int WARN_SHOWLIST_STATUS_ID=7;
	public static int WARN_CATEGORY_STATUS_ID=8;
	

	public static int TELL_SHOWOP_STATUS_ID=9;
	public static int TELL_SELOP_STATUS_ID=10;
	public static int TELL_GETLOC_STATUS_ID=11;
	public static int TELL_GETTEXT_STATUS_ID=12;
	public static int TELL_GETMEDIA_STATUS_ID=13;
	
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
