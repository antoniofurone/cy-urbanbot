package org.cysoft.urbanbot.core.model;

public class BotMessage {
	
	public static String WAIT_LOCK_SESSION_ID="WAIT_LOCK_SESSION";
	public static String WELCOME_MENU_ID="WELCOME_MENU";
	public static String COMMAND_NOT_ID="COMMAND_NOT";
	public static String ERROR_ID="ERROR";
	public static String INVALID_SESSION_ID="INVALID_SESSION";
	
	public static String WARN_SHOW_OP_ID="WARN_SHOW_OP";
	public static String WARN_TEXT_ID="WARN_TEXT";
	public static String WARN_IMGLOC_ID="WARN_IMGLOC";
	public static String WARN_LOCOK_ID="WARN_LOCOK";
	public static String WARN_IMG_ID="WARN_IMG";
	public static String WARN_IMGOK_ID="WARN_IMGOK";
	public static String WARN_LIST_ID="WARN_LIST";
	public static String WARN_LIST_OP_ID="WARN_LIST_OP";
	public static String WARN_INVALID_ID="WARN_INVALID_ID";
	public static String WARN_NO_WARN_ID="WARN_NO_WARN";
	public static String WARN_DEL_ID="WARN_DEL";
	public static String WARN_SEL_CATEGORY_ID="WARN_SEL_CATEGORY";
	
	
	private String id="";
	private String text="";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "BotMessage [id=" + id + ", text=" + text + "]";
	}
}
