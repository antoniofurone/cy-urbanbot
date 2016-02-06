package org.cysoft.urbanbot.api.google.model;

public class Geometry {
	
	private Location location=null;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Geometry [location=" + location + "]";
	}

	
}
