package com.gondor.rahul.parking.models.vehicles;

import com.gondor.rahul.parking.enums.VehicleCategory;
import com.gondor.rahul.parking.enums.VehicleProperty;

/*
 * class {@code Car} is a subclass of the Vehicle
 * 
 */
public class Car extends Vehicle{
	
	public Car(String licensePlateNumber) {
		super(VehicleCategory.CAR,licensePlateNumber);
	}

	public Car(String licensePlateNumber,VehicleProperty vehicleProperty) {
		super(licensePlateNumber, VehicleCategory.CAR, vehicleProperty);
	}
}
