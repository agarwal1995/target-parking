package com.gondor.rahul.parking.models.parking;

import com.gondor.rahul.parking.enums.StackLevel;
import com.gondor.rahul.parking.enums.VehicleCategory;

/**
 * This class {@code UpperParkingSlot} is a business object for the
 * Parking in the upper stack region of the Parking Slot.
 * This class is a subclass of the ParkingSlot class.
 *
 */
public class UpperParkingSlot extends ParkingSlot{
	
	public UpperParkingSlot(int floorIndex, int spotNumber){
		super(floorIndex,spotNumber,StackLevel.UPPER);
	}
	
	public void setCapacity() {
		
		if(this.vehicleType == VehicleCategory.BIKE){
			this.capacity = 3;
			return;
		}
		
		if(this.vehicleType == VehicleCategory.ROYAL_CAR){
			this.capacity = 0;
			return;
		}
		
		this.capacity = 1;
		
	}
}
