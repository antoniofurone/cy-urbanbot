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
	
	private InlineQuery inline_query; 	
	public InlineQuery getInline_query() {
		return inline_query;
	}
	public void setInline_query(InlineQuery inline_query) {
		this.inline_query = inline_query;
	}
	
	private ChosenInlineResult 	chosen_inline_result;
	public ChosenInlineResult getChosen_inline_result() {
		return chosen_inline_result;
	}
	public void setChosen_inline_result(ChosenInlineResult chosen_inline_result) {
		this.chosen_inline_result = chosen_inline_result;
	}
	
	@Override
	public String toString() {
		return "Update [update_id=" + update_id + ", message=" + message + ", inline_query=" + inline_query
				+ ", chosen_inline_result=" + chosen_inline_result + "]";
	}
	
	
	
}
