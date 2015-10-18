package org.cysoft.urbanbot.api.telegram.model;

import java.util.List;

public class Message {
	
	//Unique message identifier
	private long message_id;
	public long getMessage_id() {
		return message_id;
	}
	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}
	
	//Optional. Sender, can be empty for messages sent to channels
	private User from;
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	
	//Date the message was sent in Unix time
	private long date;
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}

	//Conversation the message belongs to
	private Chat chat;
	public Chat getChat() {
		return chat;
	}
	public void setChat(Chat chat) {
		this.chat = chat;
	}
	
	//Optional. For forwarded messages, sender of the original message
	private User forward_from;
	public User getForward_from() {
		return forward_from;
	}
	public void setForward_from(User forward_from) {
		this.forward_from = forward_from;
	}
	
	//Optional. For forwarded messages, date the original message was sent in Unix time
	private long forward_date;
	public long getForward_date() {
		return forward_date;
	}
	public void setForward_date(long forward_date) {
		this.forward_date = forward_date;
	}
	
	//reply_to_message 	Message 	Optional. For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
	private Message reply_to_message;
	public Message getReply_to_message() {
		return reply_to_message;
	}
	public void setReply_to_message(Message reply_to_message) {
		this.reply_to_message = reply_to_message;
	}
	
	//Optional. For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
	private String text;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	//Optional. Message is an audio file, information about the file
	private Audio audio;
	public Audio getAudio() {
		return audio;
	}
	public void setAudio(Audio audio) {
		this.audio = audio;
	}
	
	//document 	Document 	Optional. Message is a general file, information about the file
	private Document document;
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	
	//photo 	Array of PhotoSize 	Optional. Message is a photo, available sizes of the photo
	private List<PhotoSize> photo; 
	public List<PhotoSize> getPhoto() {
		return photo;
	}
	public void setPhoto(List<PhotoSize> photo) {
		this.photo = photo;
	}
	
	//Optional. Message is a sticker, information about the sticker
	private Sticker sticker;
	public Sticker getSticker() {
		return sticker;
	}
	public void setSticker(Sticker sticker) {
		this.sticker = sticker;
	}
	
	//video 	Video 	Optional. Message is a video, information about the video
	private Video video;
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	
	//voice 	Voice 	Optional. Message is a voice message, information about the file
	private Voice voice;
	public Voice getVoice() {
		return voice;
	}
	public void setVoice(Voice voice) {
		this.voice = voice;
	}
	
	//caption 	String 	Optional. Caption for the photo or video
	private String caption="";
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	//contact 	Contact 	Optional. Message is a shared contact, information about the contact
	private Contact contact;
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	//location 	Location 	Optional. Message is a shared location, information about the location
	private Location location=null;
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	//new_chat_participant 	User 	Optional. A new member was added to the group, information about them (this member may be bot itself)
	private User new_chat_participant=null;
	public User getNew_chat_participant() {
		return new_chat_participant;
	}
	public void setNew_chat_participant(User new_chat_participant) {
		this.new_chat_participant = new_chat_participant;
	}
	
	//left_chat_participant 	User 	Optional. A member was removed from the group, information about them (this member may be bot itself)
	private User left_chat_participant=null;
	public User getLeft_chat_participant() {
		return left_chat_participant;
	}
	public void setLeft_chat_participant(User left_chat_participant) {
		this.left_chat_participant = left_chat_participant;
	}
	
	//new_chat_title 	String 	Optional. A chat title was changed to this value
	private String new_chat_title="";
	public String getNew_chat_title() {
		return new_chat_title;
	}
	public void setNew_chat_title(String new_chat_title) {
		this.new_chat_title = new_chat_title;
	}
	
	//new_chat_photo 	Array of PhotoSize 	Optional. A chat photo was change to this value
	private List<PhotoSize> new_chat_photo=null;
	public List<PhotoSize> getNew_chat_photo() {
		return new_chat_photo;
	}
	public void setNew_chat_photo(List<PhotoSize> new_chat_photo) {
		this.new_chat_photo = new_chat_photo;
	}
	
	@Override
	public String toString() {
		return "Message [message_id=" + message_id + ", from=" + from
				+ ", date=" + date + ", chat=" + chat + ", forward_from="
				+ forward_from + ", forward_date=" + forward_date
				+ ", reply_to_message=" + reply_to_message + ", text=" + text
				+ ", audio=" + audio + ", document=" + document + ", photo="
				+ photo + "]";
	}
	
	
	
	
	
	
	/*
	delete_chat_photo 	True 	Optional. Informs that the chat photo was deleted
	group_chat_created 	True 	Optional. Informs that the group has been created
	*/
	

}
