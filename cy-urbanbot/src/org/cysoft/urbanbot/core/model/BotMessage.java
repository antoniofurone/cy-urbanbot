package org.cysoft.urbanbot.core.model;

public class BotMessage {
	public static String WAIT_FOR_LOCK_SESSION_ID="waitforlocksession";
	public static String WELCOME_MENU="welcomemenu";
	public static String COMMAND_NOT_RECOGNIZED="commandnot";
	public static String INVALID_SESSION="invalidsession";
	public static String SEND_WARNING="sendwarn";
	public static String SEND_WARNIMGORLOC="sendwarn_imgorloc";
	public static String SEND_WARNLOCOK="sendwarn_locok";
	public static String SEND_WARNIMG="sendwarn_img";
	public static String SEND_WARNIMGOK="sendwarn_imgok";
	
	
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
