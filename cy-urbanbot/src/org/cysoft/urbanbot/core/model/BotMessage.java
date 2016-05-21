package org.cysoft.urbanbot.core.model;

public class BotMessage {
	
	public static final String WAIT_LOCK_SESSION_ID="WAIT_LOCK_SESSION";
	
	public static final String WELCOME_MENU_ID="WELCOME_MENU";
	
	public static final String COMMAND_NOT_ID="COMMAND_NOT";
	public static final String ERROR_ID="ERROR";
	public static final String INVALID_SESSION_ID="INVALID_SESSION";
	public static final String SESSION_TIMEOUT_ID="SESSION_TIMEOUT";
	
	public static final String WARN_SHOW_OP_ID="WARN_SHOW_OP";
	public static final String WARN_TEXT_ID="WARN_TEXT";
	public static final String WARN_MEDIALOC_ID="WARN_MEDIALOC";
	public static final String WARN_LOCOK_ID="WARN_LOCOK";
	public static final String WARN_MEDIA_ID="WARN_MEDIA";
	public static final String WARN_IMGOK_ID="WARN_IMGOK";
	public static final String WARN_VIDEOOK_ID="WARN_VIDEOOK";
	public static final String WARN_LIST_OP_ID="WARN_LIST_OP";
	public static final String WARN_LIST_OP_ID_N="WARN_LIST_OP_N";
	public static final String WARN_LIST_OP_ID_P="WARN_LIST_OP_P";
	public static final String WARN_LIST_OP_ID_NP="WARN_LIST_OP_NP";
	public static final String WARN_INVALID_ID="WARN_INVALID_ID";
	public static final String WARN_NO_WARN_ID="WARN_NO_WARN";
	public static final String WARN_DEL_ID="WARN_DEL";
	public static final String WARN_SEL_CATEGORY_ID="WARN_SEL_CATEGORY";
	public static final String WARN_GET_SEARCH_ID = "WARN_GET_SEARCH";

	public static final String EVENT_LIST_OP_ID = "EVENT_LIST_OP";
	public static final String EVENT_LIST_OP_ID_N="EVENT_LIST_OP_N";
	public static final String EVENT_LIST_OP_ID_P = "EVENT_LIST_OP_P";
	public static final String EVENT_LIST_OP_ID_NP="EVENT_LIST_OP_NP";
	public static final String EVENT_INVALID_ID="EVENT_INVALID_ID";
	public static final String EVENT_NO_EVENT_ID="EVENT_NO_STORY";
	public static final String EVENT_GET_SEARCH_ID = "EVENT_GET_SEARCH";
	
	
	
	public static final String STORY_LOC_ID="STORY_LOC";
	public static final String STORY_SHOW_OP_ID="STORY_SHOW_OP";
	public static final String STORY_TEXT_ID="STORY_TEXT";
	public static final String STORY_TEXT_OK_ID="STORY_TEXT_OK";
	public static final String STORY_MEDIA_ID="STORY_MEDIA";
	public static final String STORY_VOICEOK_ID="STORY_VOICEOK";
	public static final String STORY_IMGOK_ID="STORY_IMGOK";
	public static final String STORY_VIDEOOK_ID="STORY_VIDEOOK";
	public static final String STORY_AUDIOOK_ID="STORY_AUDIOOK";
	public static final String STORY_NO_STORY_ID="STORY_NO_STORY";
	public static final String STORY_LIST_OP_ID="STORY_LIST_OP";
	public static final String STORY_LIST_OP_ID_N="STORY_LIST_OP_N";
	public static final String STORY_LIST_OP_ID_P="STORY_LIST_OP_P";
	public static final String STORY_LIST_OP_ID_NP="STORY_LIST_OP_NP";
	public static final String STORY_INVALID_ID="STORY_INVALID_ID";
	public static final String STORY_DEL_ID="STORY_DEL";
	public static final String STORY_GET_SEARCH_ID = "STORY_GET_SEARCH";
	
	
	public static final String TOURIST_LOC_ID="TOURIST_LOC";
	public static final String TOURIST_NO_SITE_ID="TOURIST_NO_SITE";
	public static final String TOURIST_LIST_OP_ID="TOURIST_LIST_OP";
	public static final String TOURIST_INVALID_ID="TOURIST_INVALID_ID";
	public static final String TOURIST_LIST_OP_ID_P = "TOURIST_LIST_OP_P";
	public static final String TOURIST_LIST_OP_ID_N = "TOURIST_LIST_OP_N";
	public static final String TOURIST_LIST_OP_ID_NP = "TOURIST_LIST_OP_NP";
	
	
	public static final String MAP_URLS_ID="MAP_URLS";
	
	
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
