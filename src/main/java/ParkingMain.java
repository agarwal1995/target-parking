import com.gondor.rahul.parking.controllers.ParkingController;
import com.gondor.rahul.parking.controllers.ParkingControllerImpl;

/**
 * 
 *	class {@code ParkingMain} is the entry class for the application
 *
 */
public class ParkingMain {
	
	/*
	 * This {@code main} method is the entry method for this application.
	 * It is responsible for making a call to the controller class and start
	 * the application working
	 * 
	 */
	public static void main(String[] args){
		ParkingController parkingController = new ParkingControllerImpl();
		parkingController.start();
	}
}
