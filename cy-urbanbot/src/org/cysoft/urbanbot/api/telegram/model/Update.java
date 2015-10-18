package org.cysoft.urbanbot.api.telegram.model;

public class Update {
	
	
	private long update_id;
	public long getUpdate_id() {
		return update_id;
	}
	public void setUpdate_id(long update_id) {
		this.update_id = update_id;
	}
	
	private Message message;
		public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	
	@Override
	public String toString() {
		return "Update [update_id=" + update_id + ", message=" + message + "]";
	}
	
	
}
