package org.cysoft.urbanbot.api.google.model;

public class GeoLocationResult {
	
	private Geometry geometry=null;

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	@Override
	public String toString() {
		return "GeoLocationResult [geometry=" + geometry + "]";
	}
	
	

}
