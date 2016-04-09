package org.cysoft.urbanbot.core.model;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.cysoft.bss.core.model.ICyBssConst;

public class Session {
	
	private Map <String,Object> sessionVariabiles=null;
	
	private List<?> cachedItems=null;
	private int cachedItemsOffset=0;
	
	public Session(long id){
		this.id=id;
		this.creationTime=Calendar.getInstance().getTimeInMillis();
		this.lastUseTime=Calendar.getInstance().getTimeInMillis();
		this.sessionStatus=new SessionStatus();
		this.sessionVariabiles= new LinkedHashMap<String,Object>(); 
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
	
	private long personId;
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}

	private String firstName="";
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	private String secondName="";
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public void putVariable(String name,Object value){
		sessionVariabiles.put(name, value);
	}
	
	public Object getVariable(String name){
		return sessionVariabiles.get(name);
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
				+ sessionStatus.toString() + ", personId=" + personId + ", firstName="
				+ firstName + ", secondName=" + secondName + "]";
	}

	public List<?> getCachedItems() {
		return cachedItems;
	}

	public void setCachedItems(List<?> cachedItems) {
		this.cachedItems = cachedItems;
	}

	public int getCachedItemsOffset() {
		return cachedItemsOffset;
	}

	public void setCachedItemsOffset(int cachedItemsOffset) {
		this.cachedItemsOffset = cachedItemsOffset;
	}

	private boolean isSearch=false;
	public void setIsSearch(boolean value) {
		this.isSearch=value;
	}

	public boolean isSearch(){
		return isSearch;
	}
	
	
}
