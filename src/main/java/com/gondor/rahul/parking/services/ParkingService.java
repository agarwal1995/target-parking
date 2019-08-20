package com.gondor.rahul.parking.services;

import com.gondor.rahul.parking.models.parking.ParkingTicket;
import com.gondor.rahul.parking.models.vehicles.Vehicle;

/**
 * Interface {@code ParkingService} represents a collection of 
 * method which deals with the Parking of the vehicles in the 
 * various ParkingFloor of the ParkingLot.
 *
 */
public interface ParkingService {
	
	ParkingTicket allocateParkingToVehicle(Vehicle vehicle) throws Exception;
	
	void deallocateParkingFromVehicle(ParkingTicket parkingTicket);
	
	void displaySummary();

}
