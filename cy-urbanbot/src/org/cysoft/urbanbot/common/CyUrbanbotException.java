package org.cysoft.urbanbot.common;

public class CyUrbanbotException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CyUrbanbotException(Exception e){
		super(e);
	}

	public CyUrbanbotException(String  msg){
		super(msg);
	}
	
}
