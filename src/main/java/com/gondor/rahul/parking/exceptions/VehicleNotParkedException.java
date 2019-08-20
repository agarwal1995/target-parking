package com.gondor.rahul.parking.exceptions;


/**
 * class {@code VehicleAlreadyParkedException} is a subclass of the Exception
 * class hierarchy.Signals that a vehicle is not parked at the spot.
 * 
 */
public class VehicleNotParkedException extends Exception{

	private static final long serialVersionUID = 5121923651077139279L;

	public VehicleNotParkedException(String msg){
		super(msg);
	}
}
