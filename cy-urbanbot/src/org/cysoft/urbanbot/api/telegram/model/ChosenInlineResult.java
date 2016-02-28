package org.cysoft.urbanbot.api.telegram.model;

public class ChosenInlineResult {
	
	private String result_id;
	public String getResult_id() {
		return result_id;
	}

	public void setResult_id(String result_id) {
		this.result_id = result_id;
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

	@Override
	public String toString() {
		return "ChosenInlineResult [result_id=" + result_id + ", from=" + from + ", query=" + query + "]";
	}
	
	
}
