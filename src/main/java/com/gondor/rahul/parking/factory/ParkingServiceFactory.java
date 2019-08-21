package com.gondor.rahul.parking.factory;

import com.gondor.rahul.parking.services.ParkingService;
import com.gondor.rahul.parking.services.ParkingServiceImpl;

public class ParkingServiceFactory {

	private static final ParkingService parkingService = new ParkingServiceImpl();

	public static ParkingService getParkingService() {
		return parkingService;
	}
}
