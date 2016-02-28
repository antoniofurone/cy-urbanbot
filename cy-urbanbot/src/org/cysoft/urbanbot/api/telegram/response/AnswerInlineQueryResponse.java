package org.cysoft.urbanbot.api.telegram.response;

public class AnswerInlineQueryResponse extends ResponseAdapter{
	
	private boolean result;
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "AnswerInlineQueryResponse [result=" + result + "]";
	}

}
