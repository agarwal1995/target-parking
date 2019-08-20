package com.gondor.rahul.parking.models.parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class {@code ParkingLot} is the Singleton Class which is the center class
 * for all parking related models.
 *
 */
public class ParkingLot {
	private static ParkingLot parkingLot = null;
	private final int noOfFloors;
	private static int noOfCarParked;
	private static int noOfBikeParked;
	private static int noOfRoyalVehicleParked;
	private static int freeCarSpots;
	private static int freeBikeSpots;
	
	private Map<String,ParkingTicket> parkings = new HashMap<>();
	private List<ParkingFloor> parkingFloors;
	
	private ParkingLot(int noOfFloors) {
		freeCarSpots = noOfFloors*40;
		freeBikeSpots = 0;
		noOfBikeParked = 0;
		noOfBikeParked = 0;
		noOfRoyalVehicleParked = 0;
		this.noOfFloors = noOfFloors;
		
		parkingFloors = new ArrayList<>();
		
		for(int i=0;i<noOfFloors;i++) {
			parkingFloors.add(new ParkingFloor(i));
		}
		
	}

	//Not used synchronized as only one thread is accessing
	public static ParkingLot getInstance(int n) {
		if(parkingLot==null){
			parkingLot = new ParkingLot(n);
		}
		return parkingLot;
	}
	
	//For returning the instance after the ParkingLot has been assigned
	public static ParkingLot getInstance(){
		return parkingLot;
	}

	public int getNoOfFloors() {
		return noOfFloors;
	}

	public Map<String, ParkingTicket> getParkings() {
		return parkings;
	}

	public void setParkings(Map<String, ParkingTicket> parkings) {
		this.parkings = parkings;
	}

	public List<ParkingFloor> getParkingFloors() {
		return parkingFloors;
	}

	public void setParkingFloors(List<ParkingFloor> parkingFloors) {
		this.parkingFloors = parkingFloors;
	}

	public static int getFreeCarSpots() {
		return freeCarSpots;
	}

	public static void setFreeCarSpots(int freeCarSpots) {
		ParkingLot.freeCarSpots = freeCarSpots;
	}

	public static int getFreeBikeSpots() {
		return freeBikeSpots;
	}

	public static void setFreeBikeSpots(int freeBikeSpots) {
		ParkingLot.freeBikeSpots = freeBikeSpots;
	}

	public static int getNoOfCarParked() {
		return noOfCarParked;
	}

	public static void setNoOfCarParked(int noOfCarParked) {
		ParkingLot.noOfCarParked = noOfCarParked;
	}

	public static int getNoOfBikeParked() {
		return noOfBikeParked;
	}

	public static void setNoOfBikeParked(int noOfBikeParked) {
		ParkingLot.noOfBikeParked = noOfBikeParked;
	}

	public static int getNoOfRoyalVehicleParked() {
		return noOfRoyalVehicleParked;
	}

	public static void setNoOfRoyalVehicleParked(int noOfRoyalVehicleParked) {
		ParkingLot.noOfRoyalVehicleParked = noOfRoyalVehicleParked;
	}
	
}
