package com.gondor.rahul.parking.models.parking;

import com.gondor.rahul.parking.enums.StackLevel;
import com.gondor.rahul.parking.models.vehicles.Vehicle;


/**
 * class {@code ParkingTicket} is the business object for the 
 * individual tickets assigned to the vehicle. It also acts as informative 
 * guide of how the vehicles have been arranged in the slots.
 */
public class ParkingTicket {
	private int floorIndex;
	private int slotNumber;
	private StackLevel level;
	private Vehicle vehicle;
	private boolean isNewUser;
	
	public ParkingTicket(int floorIndex, int slotNumber, StackLevel level, Vehicle vehicle) {
		this.floorIndex = floorIndex;
		this.slotNumber = slotNumber;
		this.level = level;
		this.vehicle = vehicle;
	}

	public int getFloorIndex() {
		return floorIndex;
	}

	public void setFloorIndex(int floorIndex) {
		this.floorIndex = floorIndex;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public StackLevel getLevel() {
		return level;
	}

	public void setLevel(StackLevel level) {
		this.level = level;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public boolean isNewUser() {
		return isNewUser;
	}

	public void setNewUser(boolean isNewUser) {
		this.isNewUser = isNewUser;
	}

	@Override
	public String toString() {
		return "ParkingTicket [floorIndex=" + floorIndex + ", slotNumber=" + slotNumber + ", level=" + level
				+ ", vehicle=" + vehicle + ", isNewUser=" + isNewUser + "]";
	}
	
}
