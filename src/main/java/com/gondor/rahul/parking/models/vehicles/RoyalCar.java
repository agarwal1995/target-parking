package com.gondor.rahul.parking.models.vehicles;

import com.gondor.rahul.parking.enums.VehicleCategory;

/*
 * class {@code RoyalCar} is a subclass of the Vehicle
 * It is taken as a vehicle as it is treated entirely a different vehicle when it comes for parking and also takes double the spots of the car
 */
public class RoyalCar extends Vehicle {

	public RoyalCar(String licensePlateNumber) {
		super(VehicleCategory.ROYAL_CAR, licensePlateNumber);
	}
}
