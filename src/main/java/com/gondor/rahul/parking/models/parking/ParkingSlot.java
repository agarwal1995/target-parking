package com.gondor.rahul.parking.models.parking;

import com.gondor.rahul.parking.enums.StackLevel;
import com.gondor.rahul.parking.enums.VehicleCategory;

/**
 * class {@code ParkingSlot} is the generalized model for the individual slots
 * in the parking. This class also acts as a superclass for the more dedicated
 * classes for the individual spots.
 * 
 */
public class ParkingSlot {
	private final int floorIndex;
	private final int spotNumber;
	protected int capacity;
	private int filled;
	private boolean isFree;
	protected VehicleCategory vehicleType;

	public ParkingSlot(int floorIndex, int spotNumber, StackLevel level) {
		this.floorIndex = floorIndex;
		this.spotNumber = spotNumber;
		this.filled = 0;
		this.isFree = true;
		this.capacity = 1;
		this.vehicleType = VehicleCategory.CAR;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity() {

	}

	public int getFilled() {
		return filled;
	}

	public void setFilled(int filled) {
		this.setCapacity();
		this.filled = filled;
		if (this.filled == this.capacity) {
			this.isFree = false;
		}
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public VehicleCategory getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleCategory vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getFloorIndex() {
		return floorIndex;
	}

	public int getSpotNumber() {
		return spotNumber;
	}

	@Override
	public String toString() {
		return "ParkingSlot [spotNumber=" + spotNumber + ", filled=" + filled + "]";
	}

}
