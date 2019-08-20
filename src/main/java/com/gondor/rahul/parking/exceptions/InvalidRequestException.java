package com.gondor.rahul.parking.exceptions;

/**
 * class {@code InvalidRequestException} is a subclass of the Exception
 * class hierarchy. It should be used for catching any request which has
 * been deviated from what requires.
 * 
 */
public class InvalidRequestException extends Exception{
	
	private static final long serialVersionUID = 4012589804567156835L;

	public InvalidRequestException(String msg){
		super(msg);
	}
}
