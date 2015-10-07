package org.cysoft.urbanbot.model;

public class UserGroupChat extends User{
	private String title="";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "UserGroupChat [user=" + super.toString() +", title=" + title + "]";
	}
	
	

}
