package com.gondor.rahul.parking.factory;

import com.gondor.rahul.parking.enums.VehicleProperty;
import com.gondor.rahul.parking.exceptions.InvalidRequestException;
import com.gondor.rahul.parking.models.vehicles.Bike;
import com.gondor.rahul.parking.models.vehicles.Car;
import com.gondor.rahul.parking.models.vehicles.RoyalCar;
import com.gondor.rahul.parking.models.vehicles.Vehicle;

public class VehicleFactory {

	/*
	 * code {@code getVehicleByChoice} method gives the caller the required
	 * object on the basis of caller choice.
	 * 
	 */
	public static Vehicle getVehicleByChoice(Integer choice, String licensePlateNumber) throws Exception {
		switch (choice) {

		case 1:
			return new Car(licensePlateNumber, VehicleProperty.DEFAULT);

		case 2:
			return new Bike(licensePlateNumber);

		case 3:
			return new RoyalCar(licensePlateNumber);

		case 4:
			return new Car(licensePlateNumber, VehicleProperty.CARPOOL);

		case 5:
			return new Car(licensePlateNumber, VehicleProperty.ELDER_PERSON_VEHICLE);

		default:
			throw new InvalidRequestException("the requested option is invalid");

		}
	}
}
