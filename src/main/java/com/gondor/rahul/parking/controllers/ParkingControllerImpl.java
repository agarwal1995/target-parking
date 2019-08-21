package com.gondor.rahul.parking.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gondor.rahul.parking.exceptions.InvalidRequestException;
import com.gondor.rahul.parking.factory.ParkingServiceFactory;
import com.gondor.rahul.parking.factory.VehicleFactory;
import com.gondor.rahul.parking.helper.UtilityService;
import com.gondor.rahul.parking.models.parking.ParkingLot;
import com.gondor.rahul.parking.models.parking.ParkingTicket;
import com.gondor.rahul.parking.models.vehicles.Vehicle;
import com.gondor.rahul.parking.services.ParkingService;

/**
 * class {@code ParkingControllerImpl} implements the ParkingController
 * interface. All methods of this class are being defined for the different
 * event driven functionalities
 *
 */
public class ParkingControllerImpl implements ParkingController {

	// Get the parkingService Object
	ParkingService parkingService;

	private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	private static final Logger LOG = LoggerFactory.getLogger(ParkingControllerImpl.class);

	private ParkingLot parkingLot;

	private Map<String, Vehicle> vehiclesList;
	private Map<String, ParkingTicket> vehiclesAssignedToSlots;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gondor.rahul.parking.controllers.ParkingController#start() This
	 * method is called at the startup, it initializes the ParkingLot object,
	 * and ask for the choice of entry or exit in a parking lot. This also makes
	 * a call to the parkingService service for the display of the summary board
	 * of the Parking Lot.
	 * 
	 */
	@Override
	public void start() {
		try {
			vehiclesList = new HashMap<>();
			vehiclesAssignedToSlots = new HashMap<>();

			LOG.info("Enter the Number Of Floors : ");
			int noOfFloor = Integer.parseInt(BR.readLine());

			// Instantiating The Parking Lot with n number of floors
			parkingLot = ParkingLot.getInstance(noOfFloor);
			parkingService = ParkingServiceFactory.getParkingService();

			do {
				LOG.info("Press 1 For Entrance && 2 For Exit :");
				String option = BR.readLine();

				try {
					switch (option) {
					case "1":
						vehicleEntry();
						break;
					case "2":
						vehicleExit();
						break;
					default:
						LOG.error("Wrong Selection, Try Again");
						continue;
					}
					parkingService.displaySummary();
				} catch (Exception e) {
					LOG.error(e.getMessage());
					e.printStackTrace();
				}

			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gondor.rahul.parking.controllers.ParkingController#vehicleEntry()
	 * This method is the entry point for incoming vehicles. It prompt the user
	 * to enter the vehicle type and vehicle no and adds the vehicle to the list
	 * if not present or if it is repeat vehicle then, get the object from the
	 * vehiclesList map object. After Getting the required details ,it calls the
	 * placeVehicle service of the ParkingService service
	 */
	@Override
	public void vehicleEntry() throws Exception {
		System.out.println("Enter The Vehicle Type :");
		System.out.println("Press 1 for a Car");
		System.out.println("Press 2 for a Bike");
		System.out.println("Press 3 for a Royal Car");
		System.out.println("Press 4 for a Pooled Car ");
		System.out.println("Press 5 for a Elder Person Car");

		int choice = Integer.parseInt(BR.readLine());

		System.out.println("Enter The Vehicle License Plate Number : ");

		String licensePlateNumber = BR.readLine();

		Vehicle vehicle;
		checkLicensePlateValidity(licensePlateNumber);

		boolean isNewUser = vehiclesList.containsKey(licensePlateNumber);

		if (isNewUser) {
			vehicle = vehiclesList.get(licensePlateNumber);
		} else {
			vehicle = VehicleFactory.getVehicleByChoice(choice, licensePlateNumber);
		}

		ParkingTicket parkingTicket = parkingService.allocateParkingToVehicle(vehicle);
		parkingTicket.setNewUser(isNewUser);

		vehiclesAssignedToSlots.put(licensePlateNumber, parkingTicket);

		System.out.println("Slot Number For The Incoming Vehicle : ");
		System.out.println("Floor Number:" + parkingTicket.getFloorIndex() + "  Spot Number :"
				+ parkingTicket.getSlotNumber() + "   StackLevel " + parkingTicket.getLevel());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gondor.rahul.parking.controllers.ParkingController#vehicleExit()
	 * This method is the exit method for the outgoing vehicles, it prompts the
	 * user for the vehicle number and type and then call the
	 * removeVehicleFromSlot method if found
	 * 
	 */
	@Override
	public void vehicleExit() throws Exception {

		System.out.println("Enter the vehicle Number :");

		String vehicleLicensePlateNumber = BR.readLine();

		checkRemoveRequestValidity(vehicleLicensePlateNumber);

		parkingService.deallocateParkingFromVehicle(vehiclesAssignedToSlots.get(vehicleLicensePlateNumber));

		vehiclesAssignedToSlots.remove(vehicleLicensePlateNumber);
	}

	/*
	 * this is a private method which checks for the validity of the
	 * licensePlate, or whether it is there in the parking log
	 */
	private void checkLicensePlateValidity(String licensePlateNumber) throws Exception {
		UtilityService.isValidLicensePlate(licensePlateNumber);

		if (vehiclesAssignedToSlots.containsKey(licensePlateNumber)) {
			throw new InvalidRequestException("the requested vehicle is already been assigned");
		}
	}

	/*
	 * this is a private method which checks for the validity of the
	 * licensePlate, and whether the particular vehicle has been assigned a slot
	 * or not
	 */
	private void checkRemoveRequestValidity(String licensePlateNumber) throws Exception {
		if (!vehiclesAssignedToSlots.containsKey(licensePlateNumber)) {
			throw new InvalidRequestException("the given licensePlateNumber does not exist");
		}
	}

}
