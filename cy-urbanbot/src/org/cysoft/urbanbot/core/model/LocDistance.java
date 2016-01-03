package org.cysoft.urbanbot.core.model;

import org.cysoft.bss.core.model.Location;

public class LocDistance {
	private final int R = 6371; // Radius of the earth
	
	private Location location=null;
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}

	private double distance=0;
	public double getDistance() {
		return distance;
	}
	
	public LocDistance(Location loc,double latRif,double longRif){
		
		this.location=loc;
		
		Double latDistance = Math.toRadians(loc.getLatitude() - latRif);
		Double lonDistance = Math.toRadians(loc.getLongitude() - longRif);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
		        + Math.cos(Math.toRadians(latRif)) * Math.cos(Math.toRadians(loc.getLatitude()))
		        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		distance = R * c * 1000; // convert to meters
	}

}
