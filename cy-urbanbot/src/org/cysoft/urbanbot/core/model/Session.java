package org.cysoft.urbanbot.core.model;

import java.util.Calendar;

import org.cysoft.bss.core.model.ICyBssConst;

public class Session {
	
	public Session(long id){
		this.id=id;
		this.creationTime=Calendar.getInstance().getTimeInMillis();
		this.lastUseTime=Calendar.getInstance().getTimeInMillis();
		this.sessionStatus=new SessionStatus();
	}
	
	private long id;
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	private String language=ICyBssConst.LOCALE_IT;
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	private long creationTime;
	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}
	
	private long lastUseTime;
		public long getLastUseTime() {
		return lastUseTime;
	}
	public void setLastUseTime(long lastUseTime) {
		this.lastUseTime = lastUseTime;
	}
	
	private boolean locked=false;
	public boolean isLocked() {
		return locked;
	}

	public synchronized void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	private SessionStatus sessionStatus=null;
	public SessionStatus getSessionStatus() {
		return sessionStatus;
	}
	public void setSessionStatus(SessionStatus sessionStatus) {
		this.sessionStatus = sessionStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Session other = (Session) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", language=" + language
				+ ", creationTime=" + creationTime + ", lastUseTime="
				+ lastUseTime + ", locked=" + locked + ", sessionStatus="
				+ sessionStatus.toString() + "]";
	}


}
