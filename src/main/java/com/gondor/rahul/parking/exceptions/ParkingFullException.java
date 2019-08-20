package com.gondor.rahul.parking.exceptions;

/**
 * class {@code ParkingFullException} is a subclass of the Exception
 * class hierarchy. It should be used for catching to indicate that,
 * there is been no space left for any vehicle to park.
 * 
 */
public class ParkingFullException extends Exception{
	
	private static final long serialVersionUID = -8790138216120310046L;

	public ParkingFullException(String msg){
		super(msg);
	}
}
