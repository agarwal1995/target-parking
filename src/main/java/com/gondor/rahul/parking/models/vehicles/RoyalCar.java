package com.gondor.rahul.parking.models.vehicles;

import com.gondor.rahul.parking.enums.VehicleCategory;

/*
 * class {@code RoyalCar} is a subclass of the Vehicle
 * 
 */
public class RoyalCar extends Vehicle{
	
	public RoyalCar(String licensePlateNumber) {
		super(VehicleCategory.ROYAL_CAR,licensePlateNumber);
	}
}
