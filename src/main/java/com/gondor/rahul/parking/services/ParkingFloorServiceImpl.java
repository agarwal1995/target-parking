package com.gondor.rahul.parking.services;

import com.gondor.rahul.parking.enums.StackLevel;
import com.gondor.rahul.parking.enums.VehicleCategory;
import com.gondor.rahul.parking.models.parking.ParkingFloor;
import com.gondor.rahul.parking.models.parking.ParkingLot;
import com.gondor.rahul.parking.models.parking.ParkingSlot;
import com.gondor.rahul.parking.models.parking.ParkingTicket;
import com.gondor.rahul.parking.models.vehicles.Vehicle;

public class ParkingFloorServiceImpl implements ParkingFloorService {

	/**
	 * This code {@code isFull} method is a overridden method of the interface
	 * ParkingFloorService and has been used to check whether there are any
	 * vacant spots present in the current ParkingFloor Object or they all have
	 * been filled. It takes constant time for all different types of Objects.
	 *
	 */
	@Override
	public boolean isFull(ParkingFloor parkingFloor, Vehicle vehicle) {
		VehicleCategory vehicleCategory = vehicle.getType();

		if (vehicleCategory == VehicleCategory.CAR) {
			return (parkingFloor.getFreeCarSpotsInCurrentFloor() == 0);
		}

		/*
		 * if it is a royal vehicle then check in each floor as it has the
		 * requirement of both the stack of the slot being empty
		 */
		if (vehicleCategory == VehicleCategory.ROYAL_CAR) {
			ParkingSlot[] lowerSlots = parkingFloor.getLowerParkingSlot();
			ParkingSlot[] upperSlots = parkingFloor.getLowerParkingSlot();
			for (int i = 0; i < 20; i++) {

				if (lowerSlots[i].getFilled() == 0 && upperSlots[i].getFilled() == 0) {
					return false;
				}
			}
			return true;
		}

		if (vehicleCategory == VehicleCategory.BIKE) {
			return (parkingFloor.getFreeCarSpotsInCurrentFloor() == 0
					&& parkingFloor.getFreeBikeSpotsInCurrentFloor() == 0);
		}

		return true;
	}

	/**
	 * code {@code placeVehicleToSpot} method should be used to assign the
	 * parking slot to a vehicle for a particular floor.This is a overridden
	 * method of the implemented interface ParkingFloorService. If the vehicle
	 * is of category {@value CAR} or {@value CARPOOL} or
	 * {@value ELDER_PERSON_VEHICLE} then it will search for the empty spots and
	 * will increments the count variable of the assigned vehicles & decrement
	 * the freespots accordingly. If the vehicle is of the category
	 * {@value ROYAL_CAR},then it will use both the stack of the slots. If the
	 * vehicle is of category {@value BIKE} then for different stack it will
	 * have a fixed size for the capacity of the Slots For {@value Level.Lower}
	 * the capacity for a Bike Spot is 2 and for {@value Level.Upper} Stack the
	 * capacity for the bike spot is 3. As The Size of the various vehicles type
	 * differ and have been defined beforehand ,therefore to check for
	 * particular vehicles slot they have been hardcoded.
	 * 
	 */
	@Override
	public ParkingTicket allocateParkingToVehicle(ParkingFloor parkingFloor, Vehicle vehicle) {
		ParkingTicket parkingTicket = null;
		ParkingSlot[] lowerSlots = parkingFloor.getLowerParkingSlot();
		ParkingSlot[] upperSlots = parkingFloor.getUpperParkingSlot();

		if (vehicle.getType() == VehicleCategory.CAR) {

			parkingTicket = allocateParkingToVehicleCar(parkingFloor, lowerSlots, upperSlots, parkingTicket, vehicle);

		}

		if (vehicle.getType() == VehicleCategory.ROYAL_CAR) {

			parkingTicket = allocateParkingToVehicleRoyalCar(parkingFloor, lowerSlots, upperSlots, parkingTicket,
					vehicle);

		}

		if (vehicle.getType() == VehicleCategory.BIKE) {

			parkingTicket = allocateParkingToVehicleBike(parkingFloor, lowerSlots, upperSlots, parkingTicket, vehicle);

		}

		return parkingTicket;
	}

	/**
	 * code {@code allocateParkingToVehicleCar} method is a method to allocate a
	 * parking slot for the vehicle Car.
	 * 
	 * @param parkingFloor
	 * @param lowerSlots
	 * @param upperSlots
	 * @param parkingTicket
	 * @param vehicle
	 * @return
	 */
	private ParkingTicket allocateParkingToVehicleCar(ParkingFloor parkingFloor, ParkingSlot[] lowerSlots,
			ParkingSlot[] upperSlots, ParkingTicket parkingTicket, Vehicle vehicle) {
		ParkingLot.setFreeCarSpots(ParkingLot.getFreeCarSpots() - 1);
		ParkingLot.setFreeBikeSpots(ParkingLot.getFreeBikeSpots() - 1);

		parkingFloor.setNoOfCarsAssigned(parkingFloor.getNoOfCarsAssigned() + 1);
		parkingFloor.setFreeCarSpotsInCurrentFloor(parkingFloor.getFreeCarSpotsInCurrentFloor() - 1);
		parkingFloor.setFreeBikeSpotsInCurrentFloor(parkingFloor.getFreeBikeSpotsInCurrentFloor() - 1);

		/*
		 * check emptiness of the spot respectively for the lower and upper
		 * stack of the slots
		 */
		for (int i = 0; i < 20; i++) {

			if (lowerSlots[i].getFilled() == 0) {
				lowerSlots[i].setVehicleType(VehicleCategory.CAR);
				lowerSlots[i].setFilled(1);
				parkingTicket = new ParkingTicket(lowerSlots[i].getFloorIndex(), lowerSlots[i].getSpotNumber(),
						StackLevel.LOWER, vehicle);
				return parkingTicket;
			}

			if (upperSlots[i].getFilled() == 0) {
				upperSlots[i].setVehicleType(VehicleCategory.CAR);
				upperSlots[i].setFilled(1);
				parkingTicket = new ParkingTicket(upperSlots[i].getFloorIndex(), upperSlots[i].getSpotNumber(),
						StackLevel.UPPER, vehicle);
				return parkingTicket;
			}
		}
		return parkingTicket;
	}

	/**
	 * code {@code allocateParkingToVehicleRoyalCar} method is a method to
	 * allocate a parking slot for the vehicle Car.
	 * 
	 * @param parkingFloor
	 * @param lowerSlots
	 * @param upperSlots
	 * @param parkingTicket
	 * @param vehicle
	 * @return
	 */
	private ParkingTicket allocateParkingToVehicleRoyalCar(ParkingFloor parkingFloor, ParkingSlot[] lowerSlots,
			ParkingSlot[] upperSlots, ParkingTicket parkingTicket, Vehicle vehicle) {
		ParkingLot.setFreeCarSpots(ParkingLot.getFreeCarSpots() - 2);
		ParkingLot.setFreeBikeSpots(ParkingLot.getFreeBikeSpots() - 2);

		parkingFloor.setNoOfRoyalVehiclesAssigned(parkingFloor.getNoOfRoyalVehiclesAssigned() + 1);
		parkingFloor.setFreeCarSpotsInCurrentFloor(parkingFloor.getFreeCarSpotsInCurrentFloor() - 2);
		parkingFloor.setFreeBikeSpotsInCurrentFloor(parkingFloor.getFreeBikeSpotsInCurrentFloor() - 2);

		for (int i = 0; i < 20; i++) {
			/*
			 * allocate both the stack of the given slot if found empty for the
			 * RoyalCar
			 */
			if (lowerSlots[i].getFilled() == 0 && upperSlots[i].getFilled() == 0) {
				lowerSlots[i].setVehicleType(VehicleCategory.ROYAL_CAR);
				lowerSlots[i].setFilled(1);
				upperSlots[i].setVehicleType(VehicleCategory.ROYAL_CAR);
				upperSlots[i].setFilled(1);

				parkingTicket = new ParkingTicket(lowerSlots[i].getFloorIndex(), lowerSlots[i].getSpotNumber(),
						StackLevel.LOWER, vehicle);

				return parkingTicket;
			}
		}

		return parkingTicket;
	}

	/**
	 * code {@code allocateParkingToVehicleBike} method is a method to allocate
	 * a parking slot for the vehicle Car.
	 * 
	 * @param parkingFloor
	 * @param lowerSlots
	 * @param upperSlots
	 * @param parkingTicket
	 * @param vehicle
	 * @return
	 */
	private ParkingTicket allocateParkingToVehicleBike(ParkingFloor parkingFloor, ParkingSlot[] lowerSlots,
			ParkingSlot[] upperSlots, ParkingTicket parkingTicket, Vehicle vehicle) {
		parkingFloor.setNoOfBikesAssigned(parkingFloor.getNoOfBikesAssigned() + 1);

		/*
		 * First check whether there is any half filled slot where another bikes
		 * are being placed , if yes then place the bike there else will allot a
		 * car slot to the bike
		 */
		for (int i = 0; i < 20; i++) {

			if (lowerSlots[i].getVehicleType() == VehicleCategory.BIKE && lowerSlots[i].getFilled() < 2) {
				lowerSlots[i].setFilled(lowerSlots[i].getFilled() + 1);

				if (!lowerSlots[i].isFree()) {
					parkingFloor.setFreeBikeSpotsInCurrentFloor(parkingFloor.getFreeBikeSpotsInCurrentFloor() - 1);
				}

				parkingTicket = new ParkingTicket(lowerSlots[i].getFloorIndex(), lowerSlots[i].getSpotNumber(),
						StackLevel.LOWER, vehicle);
				return parkingTicket;
			} else if (upperSlots[i].getVehicleType() == VehicleCategory.BIKE && upperSlots[i].getFilled() < 3) {
				upperSlots[i].setFilled(upperSlots[i].getFilled() + 1);

				if (!upperSlots[i].isFree()) {
					parkingFloor.setFreeBikeSpotsInCurrentFloor(parkingFloor.getFreeBikeSpotsInCurrentFloor() - 1);
				}

				parkingTicket = new ParkingTicket(upperSlots[i].getFloorIndex(), upperSlots[i].getSpotNumber(),
						StackLevel.UPPER, vehicle);
				return parkingTicket;
			}
		}
		if (parkingTicket == null) {
			ParkingLot.setFreeCarSpots(ParkingLot.getFreeCarSpots() - 1);
			ParkingLot.setFreeBikeSpots(ParkingLot.getFreeBikeSpots() - 1);

			parkingFloor.setFreeCarSpotsInCurrentFloor(parkingFloor.getFreeCarSpotsInCurrentFloor() - 1);

			for (int i = 0; i < 20; i++) {
				if (lowerSlots[i].getFilled() == 0) {

					lowerSlots[i].setVehicleType(VehicleCategory.BIKE);
					lowerSlots[i].setFilled(1);
					parkingFloor.setFreeBikeSpotsInCurrentFloor(1);
					parkingTicket = new ParkingTicket(lowerSlots[i].getFloorIndex(), lowerSlots[i].getSpotNumber(),
							StackLevel.LOWER, vehicle);
					return parkingTicket;

				} else if (upperSlots[i].getFilled() == 0) {

					upperSlots[i].setVehicleType(VehicleCategory.BIKE);
					upperSlots[i].setFilled(1);
					parkingFloor.setFreeBikeSpotsInCurrentFloor(2);
					parkingTicket = new ParkingTicket(lowerSlots[i].getFloorIndex(), upperSlots[i].getSpotNumber(),
							StackLevel.UPPER, vehicle);
					return parkingTicket;
				}
			}
		}

		return parkingTicket;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gondor.rahul.parking.services.ParkingFloorService#
	 * removeVehicleFromSpot(com.gondor.rahul.parking.models.parking.
	 * ParkingTicket, com.gondor.rahul.parking.models.parking.ParkingFloor) code
	 * {@code removeVehicleFromSpot} method should be used to remove the vehicle
	 * from the spot(i.e free the spot) and decrease the assigned vehicle count
	 * and at the same time increase the free slots count. If the Vehicle is if
	 * category {@value BIKE} then if the slot become empty after removing the
	 * vehicle,then the slot becomes available again for the {@value CAR} As The
	 * Size of the various vehicles type differ and have been defined beforehand
	 * ,therefore to check for particular vehicles slot they have been
	 * hardcoded.
	 * 
	 */
	@Override
	public void deallocateParkingForVehicle(ParkingFloor parkingFloor, ParkingTicket parkingTicket) {
		Vehicle vehicle = parkingTicket.getVehicle();
		int slotNumber = parkingTicket.getSlotNumber();
		ParkingSlot[] lowerSlots = parkingFloor.getLowerParkingSlot();
		ParkingSlot[] upperSlots = parkingFloor.getUpperParkingSlot();

		if (vehicle.getType() == VehicleCategory.CAR) {

			deallocateParkingForVehicleCar(parkingFloor, parkingTicket, vehicle, slotNumber, lowerSlots, upperSlots);

		}

		if (vehicle.getType() == VehicleCategory.ROYAL_CAR) {

			deallocateParkingForVehicleRoyalCar(parkingFloor, parkingTicket, vehicle, slotNumber, lowerSlots,
					upperSlots);

		}

		if (vehicle.getType() == VehicleCategory.BIKE) {

			deallocateParkingForVehicleBike(parkingFloor, parkingTicket, vehicle, slotNumber, lowerSlots, upperSlots);

		}
	}

	/**
	 * code {@code deallocateParkingForVehicleCar} is the method that should be
	 * used for the deallocating of the Car Vehicle from the ParkingSlot
	 * 
	 * @param parkingFloor
	 * @param parkingTicket
	 * @param vehicle
	 * @param slotNumber
	 * @param lowerSlots
	 * @param upperSlots
	 */
	private void deallocateParkingForVehicleCar(ParkingFloor parkingFloor, ParkingTicket parkingTicket, Vehicle vehicle,
			int slotNumber, ParkingSlot[] lowerSlots, ParkingSlot[] upperSlots) {
		ParkingLot.setFreeCarSpots(ParkingLot.getFreeCarSpots() + 1);
		ParkingLot.setNoOfCarParked(ParkingLot.getNoOfCarParked() - 1);

		parkingFloor.setFreeCarSpotsInCurrentFloor(parkingFloor.getFreeCarSpotsInCurrentFloor() + 1);

		if (parkingTicket.getLevel() == StackLevel.LOWER) {
			lowerSlots[slotNumber].setVehicleType(VehicleCategory.CAR);
			lowerSlots[slotNumber].setFilled(0);
		} else {
			upperSlots[slotNumber].setVehicleType(VehicleCategory.CAR);
			upperSlots[slotNumber].setFilled(0);
		}
	}

	/**
	 * code {@code deallocateParkingForVehicleRoyalCar} is the method that
	 * should be used for the deallocating of the Royal Vehicle from the
	 * ParkingSlot
	 * 
	 * @param parkingFloor
	 * @param parkingTicket
	 * @param vehicle
	 * @param slotNumber
	 * @param lowerSlots
	 * @param upperSlots
	 */
	private void deallocateParkingForVehicleRoyalCar(ParkingFloor parkingFloor, ParkingTicket parkingTicket,
			Vehicle vehicle, int slotNumber, ParkingSlot[] lowerSlots, ParkingSlot[] upperSlots) {
		ParkingLot.setFreeCarSpots(ParkingLot.getFreeCarSpots() + 2);
		ParkingLot.setNoOfRoyalVehicleParked(ParkingLot.getNoOfRoyalVehicleParked() - 1);

		parkingFloor.setFreeCarSpotsInCurrentFloor(parkingFloor.getFreeCarSpotsInCurrentFloor() + 2);

		/* Empty both the lower and upper stack of the slot */
		lowerSlots[slotNumber].setVehicleType(VehicleCategory.CAR);
		upperSlots[slotNumber].setVehicleType(VehicleCategory.CAR);
		lowerSlots[slotNumber].setFilled(0);
		upperSlots[slotNumber].setFilled(0);
	}

	/**
	 * code {@code deallocateParkingForVehicleBike} is the method that should be
	 * used for the deallocating of the Bike Vehicle from the ParkingSlot
	 * 
	 * @param parkingFloor
	 * @param parkingTicket
	 * @param vehicle
	 * @param slotNumber
	 * @param lowerSlots
	 * @param upperSlots
	 */
	private void deallocateParkingForVehicleBike(ParkingFloor parkingFloor, ParkingTicket parkingTicket,
			Vehicle vehicle, int slotNumber, ParkingSlot[] lowerSlots, ParkingSlot[] upperSlots) {
		ParkingLot.setNoOfBikeParked(ParkingLot.getNoOfBikeParked() - 1);

		/*
		 * check if a bike is at lower level or upper level respectively as for
		 * lower level stack it can hold 2 bikes per slot and for upper level 3
		 * bikes.
		 */
		if (parkingTicket.getLevel() == StackLevel.LOWER) {

			if (lowerSlots[slotNumber].getFilled() == 1) {
				ParkingLot.setFreeCarSpots(ParkingLot.getFreeCarSpots() + 1);
				parkingFloor.setFreeCarSpotsInCurrentFloor(parkingFloor.getFreeCarSpotsInCurrentFloor() + 1);
				lowerSlots[slotNumber].setVehicleType(VehicleCategory.CAR);
				lowerSlots[slotNumber].setFilled(0);
			} else {
				lowerSlots[slotNumber].setFilled(1);
			}

		} else {

			if (upperSlots[slotNumber].getFilled() == 1) {
				ParkingLot.setFreeCarSpots(ParkingLot.getFreeCarSpots() + 1);
				parkingFloor.setFreeCarSpotsInCurrentFloor(parkingFloor.getFreeCarSpotsInCurrentFloor() + 1);
				upperSlots[slotNumber].setVehicleType(VehicleCategory.CAR);
				upperSlots[slotNumber].setFilled(0);
			} else {
				upperSlots[slotNumber].setFilled(upperSlots[slotNumber].getFilled() - 1);

				if (upperSlots[slotNumber].getFilled() == 2) {
					ParkingLot.setFreeCarSpots(ParkingLot.getFreeBikeSpots() + 1);
					parkingFloor.setFreeBikeSpotsInCurrentFloor(parkingFloor.getFreeBikeSpotsInCurrentFloor() + 1);
				}
			}

		}
	}
}
