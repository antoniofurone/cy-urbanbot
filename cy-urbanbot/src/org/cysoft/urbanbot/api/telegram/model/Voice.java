package org.cysoft.urbanbot.api.telegram.model;

public class Voice {

	private String file_id="";
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	
	private int duration;
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
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
		return "Voice [file_id=" + file_id + ", duration=" + duration
				+ ", mime_type=" + mime_type + ", file_size=" + file_size + "]";
	}
	
}
