package org.cysoft.urbanbot.api.telegram.model;

public class Video {

	private String file_id="";
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	private int width; 
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	private int height;
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	private int duration;
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	private PhotoSize thumb=null;
	public PhotoSize getThumb() {
		return thumb;
	}
	public void setThumb(PhotoSize thumb) {
		this.thumb = thumb;
	}

	private String mime_type="";
	public String getMime_type() {
		return mime_type;
	}
	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}

	private int file_size;
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	@Override
	public String toString() {
		return "Video [file_id=" + file_id + ", width=" + width + ", height="
				+ height + ", duration=" + duration + ", thumb=" + thumb
				+ ", mime_type=" + mime_type + ", file_size=" + file_size + "]";
	}
	
	
}
