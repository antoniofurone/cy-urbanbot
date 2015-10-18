package org.cysoft.urbanbot.api.telegram.response;

public abstract class ResponseAdapter implements IResponse {
	private boolean ok=false;
	
	public boolean isOk(){
		return ok;
	}
	public void setOk(boolean ok){
		this.ok=ok;
	}

}
