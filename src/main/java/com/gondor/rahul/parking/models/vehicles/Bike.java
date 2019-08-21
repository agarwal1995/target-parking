package com.gondor.rahul.parking.models.vehicles;

import com.gondor.rahul.parking.enums.VehicleCategory;

/*
 * class {@code Bike} is a subclass of the Vehicle
 * 
 */
public class Bike extends Vehicle {

	public Bike(String licensePlateNumber) {
		super(VehicleCategory.BIKE, licensePlateNumber);
	}
}
