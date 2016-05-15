package org.cysoft.urbanbot.core.model;

public class SessionStatus {
	public static final int INIT_STATUS_ID=0;
	public static final int MAIN_MENU_STATUS_ID=1;
	
	public static final int WARN_TEXT_STATUS_ID=2;
	public static final int WARN_IMGLOC_STATUS_ID=3;
	public static final int WARN_IMG_STATUS_ID=4;
	public static final int WARN_SELOP_STATUS_ID=5;
	public static final int WARN_SHOWOP_STATUS_ID=6;
	public static final int WARN_SHOWLIST_STATUS_ID=7;
	public static final int WARN_CATEGORY_STATUS_ID=8;
	public static final int WARN_SEARCH_STATUS_ID = 9;
	

	public static final int STORY_SHOWOP_STATUS_ID=20;
	public static final int STORY_SELOP_STATUS_ID=21;
	public static final int STORY_GETLOC_STATUS_ID=22;
	public static final int STORY_GETTEXT_STATUS_ID=23;
	public static final int STORY_GETMEDIA_STATUS_ID=24;
	public static final int STORY_SHOWLIST_STATUS_ID=25;
	public static final int STORY_SEARCH_STATUS_ID = 26;
	
	
	public static final int TOURIST_GETLOC_STATUS_ID=30;
	public static final int TOURIST_SELLOC_STATUS_ID=31;
	
	
	//public static final int EVENT_SHOWOP_STATUS_ID=32;
	//public static final int EVENT_SELOP_STATUS_ID=33;
	public static final int EVENT_SHOWLIST_STATUS_ID=34;
	public static final int EVENT_SEARCH_STATUS_ID = 35;
	
	
	
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
