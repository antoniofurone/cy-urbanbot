package org.cysoft.urbanbot.model;

public class User {
	
	private long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	private String first_name="";
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	

	private String last_name="";
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	

	private String username="";
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + first_name + ", last_name="
				+ last_name + ", username=" + username + "]";
	}
	

	

}
