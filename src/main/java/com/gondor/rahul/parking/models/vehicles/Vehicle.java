package com.gondor.rahul.parking.models.vehicles;

import com.gondor.rahul.parking.enums.VehicleCategory;
import com.gondor.rahul.parking.enums.VehicleProperty;

/**
 * class {@code Vehicle} is root class for the vehicle hierarchy. Every vehicle
 * type will have {@code Vehicle} as a superclass.
 */
abstract public class Vehicle {

	private String licensePlateNumber;
	private VehicleCategory category;
	VehicleProperty vehicleProperty;

	Vehicle(VehicleCategory category, String licensePlateNumber) {
		this.category = category;
		this.licensePlateNumber = licensePlateNumber;
		this.vehicleProperty = VehicleProperty.DEFAULT;
	}

	public Vehicle(String licensePlateNumber, VehicleCategory category, VehicleProperty vehicleProperty) {
		this.licensePlateNumber = licensePlateNumber;
		this.category = category;
		this.vehicleProperty = vehicleProperty;
	}

	public String getLicenseNo() {
		return licensePlateNumber;
	}

	public void setLicenseNo(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	public VehicleCategory getType() {
		return category;
	}

	public void setType(VehicleCategory category) {
		this.category = category;
	}

	public VehicleProperty getVehicleProperty() {
		return vehicleProperty;
	}

	public void setVehicleProperty(VehicleProperty vehicleProperty) {
		this.vehicleProperty = vehicleProperty;
	}

}
