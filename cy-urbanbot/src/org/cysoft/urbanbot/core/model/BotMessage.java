package org.cysoft.urbanbot.core.model;

public class BotMessage {
	
	public static String WAIT_LOCK_SESSION_ID="WAIT_LOCK_SESSION";
	public static String WELCOME_MENU_ID="WELCOME_MENU";
	public static String COMMAND_NOT_ID="COMMAND_NOT";
	public static String ERROR_ID="ERROR";
	public static String INVALID_SESSION_ID="INVALID_SESSION";
	
	public static String WARN_SHOW_OP_ID="WARN_SHOW_OP";
	public static String WARN_TEXT_ID="WARN_TEXT";
	public static String WARN_MEDIALOC_ID="WARN_MEDIALOC";
	public static String WARN_LOCOK_ID="WARN_LOCOK";
	public static String WARN_MEDIA_ID="WARN_MEDIA";
	public static String WARN_IMGOK_ID="WARN_IMGOK";
	public static String WARN_VIDEOOK_ID="WARN_VIDEOOK";
	public static String WARN_LIST_ID="WARN_LIST";
	public static String WARN_LIST_OP_ID="WARN_LIST_OP";
	public static String WARN_INVALID_ID="WARN_INVALID_ID";
	public static String WARN_NO_WARN_ID="WARN_NO_WARN";
	public static String WARN_DEL_ID="WARN_DEL";
	public static String WARN_SEL_CATEGORY_ID="WARN_SEL_CATEGORY";
	
	public static String TELL_LOC_ID="TELL_LOC";
	public static String TELL_SHOW_OP_ID="TELL_SHOW_OP";
	public static String TELL_TEXT_ID="TELL_TEXT";
	public static String TELL_TEXT_OK_ID="TELL_TEXT_OK";
	public static String TELL_MEDIA_ID="TELL_MEDIA";
	public static String TELL_VOICEOK_ID="TELL_VOICEOK";
	public static String TELL_IMGOK_ID="TELL_IMGOK";
	public static String TELL_VIDEOOK_ID="TELL_VIDEOOK";
	public static String TELL_AUDIOOK_ID="TELL_AUDIOOK";
	public static String TELL_NO_TELL_ID="TELL_NO_TELL";
	public static String TELL_LIST_ID="TELL_LIST";
	public static String TELL_LIST_OP_ID="TELL_LIST_OP";
	public static String TELL_INVALID_ID="TELL_INVALID_ID";
	public static String TELL_DEL_ID="TELL_DEL";
	
	public static String TOURIST_LOC_ID="TOURIST_LOC";
	public static String TOURIST_NO_SITE_ID="TOURIST_NO_SITE";
	public static String TOURIST_LIST_ID="TOURIST_LIST";
	public static String TOURIST_LIST_OP_ID="TOURIST_LIST_OP";
	public static String TOURIST_INVALID_ID="TOURIST_INVALID_ID";
	
	public static String MAP_URLS_ID="MAP_URLS";
	
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
