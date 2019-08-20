Gordon-Parking

This is a Multi Level Parking Solutions designed for the city of Minas Tirith.

HOW TO RUN :
	For Windows :
	
		To Start The Application find the run the batch file inside the target folder of the application

	From Command Line :
	
		goto the target directory and run the command : "java -jar gondor-parking-0.0.1-SNAPSHOT.jar"
	
	
Assumptions :
	
	Deduced From The Question :
	
		1. There are n number of floors
		2. Each Floor has 20 parking slots in the form of stacking, which basically made as 40 spots.
		3. Intially all slots is for car
		4. If any bike convert car parking to bike
		5. For Each Slot in lower level the slot can accomodate 2 bikes in it 
		6. For Each Slot in upper level the slot can accomodate 3 bikes in it (as being mentioned in the question that with stacking 5 bikes , so i deduced it to 2 for down & 3 for up)
		7. If any Royal Vehicle Came then assigned the whole slot to him , no vehicle should be around his car
		8. For Aged People there is a different category of vehicle defined "ELDER_PERSON_VEHICLE"
		9. For Pooling Of Vehicle there is a different category if vehicle defined "CARPOOL"
		
	Implemented Assumptions(Based on View Of What the output was required):
		
		1. A StandAlone Java Application 
		2. The Data is being stored in-memory ,i.e, no database is there to store the data. (The Database Service can be injected when needed)
		3. The Application is volatile ,i.e, once restarted whole data will be lost
		4. There is only 1 entrance and 1 exit gate in the multi level parking 
		5. For ROYAL_VEHICLE the entire slot is being allocated to them.
		6. Each Parking Slot has a specific size based on the vehicleCategory
			a.For Car Per Stack level - 1
			b.For CarPool Per Stack level - 1
			c.For ELDER_PERSON_VEHICLE per stack level - 1
			d.For ROYAL_VEHICLE per stack - 1(Both upper and lower spot of that slot)
			e.For Bike at Lower Level of Stack - 2
			e.For Bike at Upper Level of Stack - 3
		
Input :

	==> Integer n : n No.Of Floors 
	
	IN Loop(forever){
		==> Choice: 1 Or 2 
		    1 For Entrance 
			2 For Exit
			
		Select(1){
			==> Choice 1 or 2 or 3 or 4 or 5
				1 For Car
				2 For Bike
				3 For ROYAL_VEHICLE
				4 For CARPOOL
				5 For ELDER_PERSON_VEHICLE
			==> vehicleNumber (4 digit numeric character);
		}
		Select(2){
			==> Enter The Vehicle Category
			==> vehicleNumber(4 digit numeric character)
		}
	}


Output :

	For Selection 1 (Entrance Vehicle){
		onSuccess
		==>Vehicle - Floor Number ,Spot Number and The Stack Level
		==>Parking Level Summary
			It Inculdes :
				-> Number of Cars Parked (in cars all three category CAR,CARPOOL & ELDER_PERSON_VEHICLE come)
				-> Number of Bikes Parked (only of category BIKE)
				-> Number of Royal Vehicle Parked 
				
				-> No of Available Car Spots (this does show data on the basis of above Implemented Assumptions)
				
		onError
		==>Invalid vehicleNumber
		==>Invalid Option Request
		==>Parking is Already Full
	}
		
	For Selection 2 (Exit Vehicle){
		onSuccess
		==>Parking Level Summary
			It Inculdes :
				-> Number of Cars Parked (in cars all three category CAR,CARPOOL & ELDER_PERSON_VEHICLE come)
				-> Number of Bikes Parked (only of category BIKE)
				-> Number of Royal Vehicle Parked 
				
				-> No of Available Car Spots (this does show data on the basis of above Implemented Assumptions)
				
		onError
		==>Invalid vehicleNumber
		==>Invalid Option Request
		==>No Vehicle Parked	
	}
