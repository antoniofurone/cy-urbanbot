package org.cysoft.urbanbot.api.telegram.model;

public class PhotoSize {
	
	public static final int MAX_DOWNLOADABLE_SIZE=20000000;
	
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

	private int file_size;
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	
	private String file_path="";
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	
	@Override
	public String toString() {
		return "PhotoSize [file_id=" + file_id + ", width=" + width
				+ ", height=" + height + ", file_size=" + file_size
				+ ", file_path=" + file_path + "]";
	}
	
	
	
	
}
