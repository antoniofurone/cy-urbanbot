package org.cysoft.urbanbot.model;

public class Message {
	
	private long message_id;
	public long getMessage_id() {
		return message_id;
	}
	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}
	
	private User from;
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	
	
	private UserGroupChat chat;
	public UserGroupChat getChat() {
		return chat;
	}
	public void setChat(UserGroupChat chat) {
		this.chat = chat;
	}
	
	
	@Override
	public String toString() {
		return "Message [message_id=" + message_id + ", from=" + from
				+ ", chat=" + chat + "]";
	}
	
	
	
	
	
	

}
