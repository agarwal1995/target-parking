package com.gondor.rahul.parking.controllers;

/**
 * Interface that represents a collection of method of various user events in
 * the parking lot.
 *
 */
public interface ParkingController {

	void start();

	void vehicleEntry() throws Exception;

	void vehicleExit() throws Exception;

}
