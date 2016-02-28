package org.cysoft.urbanbot.api.telegram.model;

public class InlineQuery {
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	private User from;
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	
	private String query;
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	private String offset;
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	
	@Override
	public String toString() {
		return "InlineQuery [id=" + id + ", from=" + from + ", query=" + query + ", offset=" + offset + "]";
	}
	
	

}
