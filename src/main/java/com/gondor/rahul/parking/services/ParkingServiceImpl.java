package com.gondor.rahul.parking.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gondor.rahul.parking.enums.VehicleCategory;
import com.gondor.rahul.parking.enums.VehicleProperty;
import com.gondor.rahul.parking.exceptions.ParkingFullException;
import com.gondor.rahul.parking.factory.ParkingFloorServiceFactory;
import com.gondor.rahul.parking.models.parking.ParkingFloor;
import com.gondor.rahul.parking.models.parking.ParkingLot;
import com.gondor.rahul.parking.models.parking.ParkingTicket;
import com.gondor.rahul.parking.models.vehicles.Vehicle;

/**
 * class {@code ParkingServiceImpl} class implements the ParkingService 
 * interface. The methods of the class should be use to handle the 
 * ParkingSlots Business Logic in the diffrent {@value n} floor
 *
 */
public class ParkingServiceImpl implements ParkingService{

	private ParkingFloorService parkingFloorService = ParkingFloorServiceFactory.getParkingFloorService();
	private ParkingLot parkingLot = ParkingLot.getInstance();
	private static final Logger LOG = LoggerFactory.getLogger(ParkingServiceImpl.class);
	
	/*
	 * {@variable parkingFloorService} is the ParkingFloorService var that is being
	 * used to use the service for more designated methods
	 */
	
	/**
	 * code {@code placeVehicleToSlot} method should be used to assign the parking slot to 
	 * a vehicle.This is a overridden method of the implemented interface ParkingService.
	 * If the vehicle is of the category {@value ELDER_PERSON_VEHICLE} or {@value ROYAL_CAR},then
	 * the search for an empty spot is being started from the ground floor itself which is the nearest.
	 * If the vehicle is of category {@value CAR} or {@value CARPOOL} then the search for an empty
	 * spot will be started from the 1st floor and then at last the ground floor.
	 * If the vehicle is of category {@value BIKE} then it will first check for any half filled 
	 * bike spot ,then if not found a car spot will be assigned to the bike.
	 * 
	 */
	@Override
	public ParkingTicket allocateParkingToVehicle(Vehicle vehicle) throws Exception{
		
		if(isFull(vehicle)){
			throw new ParkingFullException("There is no place available for parking the required vehicle");
		}
		
		ParkingTicket parkingTicket;
		
		if(vehicle.getType() == VehicleCategory.CAR) {
			if(vehicle.getVehicleProperty() == VehicleProperty.ELDER_PERSON_VEHICLE) {
				for(ParkingFloor parkingFloor : parkingLot.getParkingFloors()) {
					if(!parkingFloorService.isFull(parkingFloor,vehicle)) {
						parkingTicket = parkingFloorService.allocateParkingToVehicle(parkingFloor, vehicle);
						
						ParkingLot.setNoOfCarParked(ParkingLot.getNoOfCarParked()+1);
						
						return parkingTicket;
					}
				}
			}
			
			if(vehicle.getVehicleProperty() == VehicleProperty.CARPOOL || vehicle.getVehicleProperty() == VehicleProperty.DEFAULT) {
				ParkingLot.setNoOfCarParked(ParkingLot.getNoOfCarParked()+1);
				
				for(int i=1;i<parkingLot.getNoOfFloors();i++){
					ParkingFloor parkingFloor = parkingLot.getParkingFloors().get(i);
					if(!parkingFloorService.isFull(parkingFloor,vehicle)){
						parkingTicket = parkingFloorService.allocateParkingToVehicle(parkingFloor, vehicle);
						return parkingTicket;
					}
				}
				
				ParkingFloor parkingFloor = parkingLot.getParkingFloors().get(0);
				parkingTicket = parkingFloorService.allocateParkingToVehicle(parkingFloor, vehicle);
				
				return parkingTicket;
			}
		}
		
		if(vehicle.getType() == VehicleCategory.ROYAL_CAR) {
			for(ParkingFloor parkingFloor : parkingLot.getParkingFloors()) {
				if(!parkingFloorService.isFull(parkingFloor,vehicle)) {
					parkingTicket = parkingFloorService.allocateParkingToVehicle(parkingFloor, vehicle);
					
					ParkingLot.setNoOfRoyalVehicleParked(ParkingLot.getNoOfRoyalVehicleParked()+1);
					
					return parkingTicket;
				}
			}
		}
		
		if(vehicle.getType() == VehicleCategory.BIKE){
			ParkingLot.setNoOfBikeParked(ParkingLot.getNoOfBikeParked()+1);
			if(ParkingLot.getFreeBikeSpots()>0){
				for(ParkingFloor parkingFloor : parkingLot.getParkingFloors()){
					if(parkingFloor.getFreeBikeSpotsInCurrentFloor()!=0){
						parkingTicket = parkingFloorService.allocateParkingToVehicle(parkingFloor, vehicle);
						return parkingTicket;
					}
				}
			}
			for(int i=1;i<parkingLot.getNoOfFloors();i++){
				ParkingFloor parkingFloor = parkingLot.getParkingFloors().get(i);
				if(!parkingFloorService.isFull(parkingFloor,vehicle)){
					parkingTicket = parkingFloorService.allocateParkingToVehicle(parkingFloor, vehicle);
					return parkingTicket;
				}
			}
			ParkingFloor parkingFloor = parkingLot.getParkingFloors().get(0);
			parkingTicket = parkingFloorService.allocateParkingToVehicle(parkingFloor, vehicle);
			return parkingTicket;
		}
		
		return null;
	}

	/**
	 * code {@code removeVehicleFromSlot} method should be used by the caller for the removal
	 * of the Vehicle from the ParkingSlot,and delete that particular ticket.
	 */
	@Override
	public void deallocateParkingFromVehicle(ParkingTicket parkingTicket) {
		
		parkingFloorService.deallocateParkingForVehicle(parkingLot.getParkingFloors().get(parkingTicket.getFloorIndex()),parkingTicket);
	
	}

	/**
	 * code {@code displaySummary} method should be used by the caller.
	 *  for the quick summary of the Parking Lot Current Information.
	 */
	@Override
	public void displaySummary() {
		
		System.out.println("***************************************************************************");
		System.out.println("------------------------------Parking Summary------------------------------");
		System.out.println("***************************************************************************");
		System.out.println("No of Car Parked : " + ParkingLot.getNoOfCarParked());
		System.out.println("No of Bike Parked : " + ParkingLot.getNoOfBikeParked());
		System.out.println("No of Royal Vehicle Parked : " + ParkingLot.getNoOfRoyalVehicleParked());
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Available Car Slots : " + ParkingLot.getFreeCarSpots());
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("***************************************************************************");
		
	}
	
	/**
	 * This code {@code isFull} method has been used to check
	 * whether there are any vacant spots present in the ParkingLot
	 * or they all have been filled.For Royal_Car objects it takes 
	 * O(n) time complexity,which for other Object it provides the 
	 * output in O(1) time complexity
	 * 
	 * @param vehicle
	 * @param parkingLot
	 * @return
	 */
	private boolean isFull(Vehicle vehicle){
		VehicleCategory vehicleCategory = vehicle.getType();
		
		if(vehicleCategory == VehicleCategory.CAR) {
			return (ParkingLot.getFreeCarSpots() ==0);
		}
		
		if(vehicleCategory == VehicleCategory.ROYAL_CAR) {
			for(ParkingFloor parkingFloor : parkingLot.getParkingFloors()) {
				
				if(!parkingFloorService.isFull(parkingFloor,vehicle)) {
					return false;
				}
			}
			return true;
		}
		
		if(vehicleCategory == VehicleCategory.BIKE){
			return (ParkingLot.getFreeBikeSpots()==0 && ParkingLot.getFreeCarSpots()==0);
		}
		
		return true;
	}
}
