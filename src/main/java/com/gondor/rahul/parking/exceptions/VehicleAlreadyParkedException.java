package com.gondor.rahul.parking.exceptions;

/**
 * class {@code VehicleAlreadyParkedException} is a subclass of the Exception
 * class hierarchy.Signals that a vehicle is already parked at the spot
 * 
 */
public class VehicleAlreadyParkedException extends Exception{
	
	private static final long serialVersionUID = -1423068373954556588L;

	public VehicleAlreadyParkedException(String msg){
		super(msg);
	}
}
