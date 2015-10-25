package org.cysoft.urbanbot.core.model;

public class BotMessage {
	public static String WAIT_FOR_LOCK_SESSION_ID="urbanbot.waitForLockSession";
	public static String WELCOME_MENU="urbanbot.welcomeMenu";
	public static String COMMAND_NOT_RECOGNIZED="urbanbot.commandnot";
	public static String INVALID_SESSION="urbanbot.invalidsession";
	public static String SEND_WARNING="urbanbot.sendwarning";
	public static String SEND_WARNIMGORLOC="urbanbot.sendwarnimgorloc";
	public static String SEND_WARNLOCOK="urbanbot.sendwarnlocok";
	public static String SEND_WARNIMG="urbanbot.sendwarnimg";
	public static String SEND_WARNIMGOK="urbanbot.sendwarnimgok";
	
	
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
