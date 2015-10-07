package org.cysoft.urbanbot.response;

import java.util.ArrayList;
import java.util.List;

import org.cysoft.urbanbot.model.Update;

public class GetUpdatesResponse  extends ResponseAdapter{

	private List<Update> result=new ArrayList<Update>();

	public List<Update> getResult() {
		return result;
	}

	public void setResult(List<Update> updates) {
		this.result = updates;
	}

	@Override
	public String toString() {
		return "GetUpdatesResponse [results=" + result + "]";
	}

	
}
