package org.cysoft.urbanbot.api.telegram.model;

public class InlineQueryResultArticle implements InlineQueryResult{
	private String type="article";
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	private String message_text;
	public String getMessage_text() {
		return message_text;
	}
	public void setMessage_text(String message_text) {
		this.message_text = message_text;
	}

	private String parse_mode;
	public String getParse_mode() {
		return parse_mode;
	}
	public void setParse_mode(String parse_mode) {
		this.parse_mode = parse_mode;
	}
	@Override
	public String toString() {
		return "InlineQueryResultArticle [type=" + type + ", id=" + id + ", title=" + title + ", message_text="
				+ message_text + ", parse_mode=" + parse_mode + "]";
	}
	
	
	
}
