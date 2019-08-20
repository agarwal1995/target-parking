package com.gondor.rahul.parking.models.parking;

/**
 * class {@code ParkingFloor} is the model class for the individual floor in 
 * the multi level parking class;
 *
 */
public class ParkingFloor {
	private Integer floorIndex;
	private int noOfCarsAssigned;
	private int noOfBikesAssigned;
	private int noOfRoyalVehiclesAssigned;
	private int noOfFreeCarSpotsInCurrentFloor;
	private int noOfFreeBikeSpotsInCurrentFloor;
	
	private ParkingSlot[] lowerParkingSlot = new ParkingSlot[20];
	private ParkingSlot[] upperParkingSlot = new ParkingSlot[20];
	
	public ParkingFloor(int floorIndex) {
		this.floorIndex = floorIndex;
		this.noOfCarsAssigned = 0;
		this.noOfBikesAssigned = 0;
		this.noOfRoyalVehiclesAssigned = 0;
		this.noOfFreeCarSpotsInCurrentFloor = 40;
		this.noOfFreeBikeSpotsInCurrentFloor = 40;
		
		for(int i=0;i<20;i++){
			lowerParkingSlot[i] = new LowerParkingSlot(this.floorIndex,i);
			upperParkingSlot[i] = new UpperParkingSlot(this.floorIndex,i);
		}
		
	}

	public Integer getFloorIndex() {
		return floorIndex;
	}

	public void setFloorIndex(Integer floorIndex) {
		this.floorIndex = floorIndex;
	}

	public int getNoOfCarsAssigned() {
		return noOfCarsAssigned;
	}

	public void setNoOfCarsAssigned(int noOfCarsAssigned) {
		this.noOfCarsAssigned = noOfCarsAssigned;
	}

	public int getNoOfBikesAssigned() {
		return noOfBikesAssigned;
	}

	public void setNoOfBikesAssigned(int noOfBikesAssigned) {
		this.noOfBikesAssigned = noOfBikesAssigned;
	}

	public int getNoOfRoyalVehiclesAssigned() {
		return noOfRoyalVehiclesAssigned;
	}

	public void setNoOfRoyalVehiclesAssigned(int noOfRoyalVehiclesAssigned) {
		this.noOfRoyalVehiclesAssigned = noOfRoyalVehiclesAssigned;
	}

	public int getFreeCarSpotsInCurrentFloor() {
		return noOfFreeCarSpotsInCurrentFloor;
	}

	public void setFreeCarSpotsInCurrentFloor(int noOfFreeCarSpotsInCurrentFloor) {
		this.noOfFreeCarSpotsInCurrentFloor = noOfFreeCarSpotsInCurrentFloor;
	}

	public int getFreeBikeSpotsInCurrentFloor() {
		return noOfFreeBikeSpotsInCurrentFloor;
	}

	public void setFreeBikeSpotsInCurrentFloor(int noOfFreeBikeSpotsInCurrentFloor) {
		this.noOfFreeBikeSpotsInCurrentFloor = noOfFreeBikeSpotsInCurrentFloor;
	}

	public ParkingSlot[] getLowerParkingSlot() {
		return lowerParkingSlot;
	}

	public void setLowerParkingSlot(ParkingSlot[] lowerParkingSlot) {
		this.lowerParkingSlot = lowerParkingSlot;
	}

	public ParkingSlot[] getUpperParkingSlot() {
		return upperParkingSlot;
	}

	public void setUpperParkingSlot(ParkingSlot[] upperParkingSlot) {
		this.upperParkingSlot = upperParkingSlot;
	}

}
