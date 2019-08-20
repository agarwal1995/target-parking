package com.gondor.rahul.parking.models.parking;

import com.gondor.rahul.parking.enums.StackLevel;
import com.gondor.rahul.parking.enums.VehicleCategory;

/**
 * This class {@code LowerParkingSlot} is a business object for the
 * Parking in the lower stack region of the Parking Slot.
 * This class is a subclass of the ParkingSlot class.
 *
 */
public class LowerParkingSlot extends ParkingSlot{
	
	public LowerParkingSlot(int floorIndex, int spotNumber) {
		super(floorIndex,spotNumber,StackLevel.LOWER);
	}
	
	public void setCapacity() {
		if(this.vehicleType == VehicleCategory.BIKE){
			this.capacity = 2;
		}else{
			this.capacity = 1;
		}
	}
}
