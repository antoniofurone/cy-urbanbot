package org.cysoft.urbanbot.api.telegram.response;

import org.cysoft.urbanbot.api.telegram.model.Message;

public class SendMessageResponse extends ResponseAdapter{
	private Message result=null;

	public Message getResult() {
		return result;
	}

	public void setResult(Message result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "SendMessageResponse [result=" + result + "]";
	}
	
	
}
