package com.gondor.rahul.parking.services;

import com.gondor.rahul.parking.models.parking.ParkingFloor;
import com.gondor.rahul.parking.models.parking.ParkingTicket;
import com.gondor.rahul.parking.models.vehicles.Vehicle;

/**
 * Interface {@code ParkingFloorService} represents a collection of method which
 * deals with the Parking of the vehicles in the ParkingSlot of the floor.
 *
 */
public interface ParkingFloorService {

	boolean isFull(ParkingFloor parkingFloor, Vehicle vehicle);

	ParkingTicket allocateParkingToVehicle(ParkingFloor parkingFloor, Vehicle vehicle);

	void deallocateParkingForVehicle(ParkingFloor parkingFloor, ParkingTicket parkingTicket);
}
