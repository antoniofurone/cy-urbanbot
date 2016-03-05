package org.cysoft.urbanbot.api.telegram.model;

public class InlineQueryResultPhoto implements InlineQueryResult{
	private String type="photo";
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
	
	private String photo_url;
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	
	private String thumb_url;
	public String getThumb_url() {
		return thumb_url;
	}
	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}
	
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	private String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	private String caption;
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	
	@Override
	public String toString() {
		return "InlineQueryResultPhoto [type=" + type + ", id=" + id + ", photo_url=" + photo_url + ", thumb_url="
				+ thumb_url + ", title=" + title + ", description=" + description + ", caption=" + caption + "]";
	}
	
	
}
