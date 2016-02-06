package org.cysoft.urbanbot.api.google.model;

import java.util.List;

public class GeoLocation {
	private String status="";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private List<GeoLocationResult> results=null;

	public List<GeoLocationResult> getResults() {
		return results;
	}

	public void setResults(List<GeoLocationResult> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "GeoLocation [status=" + status + ", results=" + results + "]";
	}
	
	

}
