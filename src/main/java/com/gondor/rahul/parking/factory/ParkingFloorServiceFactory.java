package com.gondor.rahul.parking.factory;

import com.gondor.rahul.parking.services.ParkingFloorService;
import com.gondor.rahul.parking.services.ParkingFloorServiceImpl;

public class ParkingFloorServiceFactory {
	
	private static final ParkingFloorService parkingFloorService = new ParkingFloorServiceImpl();
	
	public static ParkingFloorService getParkingFloorService() {
		return parkingFloorService;
	}
}
