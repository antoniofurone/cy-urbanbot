package org.cysoft.urbanbot.api.telegram.model;

public class File {
	
	private String file_id="";
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
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
		return "File [file_id=" + file_id + ", file_path=" + file_path + "]";
	}

	

}
