package org.cysoft.urbanbot.api.telegram.model;

public class PhotoSize {
	private String file_id="";
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	
	
	@Override
	public String toString() {
		return "PhotoSize [file_id=" + file_id + "]";
	}
	
	
}
