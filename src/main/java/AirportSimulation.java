import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



// public class AirportCheckIn {
//     // File objects to hold booking and flight data
//     public File bookingsFile;
//     public File flightsFile;
//     // HashMaps to store bookings and flights data
//     public HashMap<String, Booking> bookings;
//     public HashMap<String, Flight> flights;
//     // GUI object for check-in interface
//     private CheckInGUI checkInGUI;

//     // Constructor initializes HashMaps for bookings and flights
//     public AirportCheckIn() {
//         bookings = new HashMap<>();
//         flights = new HashMap<>();
//     }

//     // Method to load bookings from a file
//     public void loadBookingsFromFile() {
//         String line = "";  
//         try {
//             BufferedReader br = new BufferedReader(new FileReader("bookings.txt"));  
//             while ((line = br.readLine()) != null) {
//                 // Splitting each line by comma to extract booking information
//                 String[] fileLine = line.split(",");
//                 try {
//                     // Validating booking reference number format
//                     if (!isValidBookingReference(fileLine[0])) {
//                         throw new IncorrectRefNumException(fileLine[0]);
//                     }
//                     // Creating new Booking object and storing it in HashMap
//                     boolean checkedIn = fileLine[3].equals("true");
//                     Booking newBooking = new Booking(fileLine[0], fileLine[1], fileLine[2], checkedIn);
//                     bookings.put(fileLine[0], newBooking);
//                 } catch (IncorrectRefNumException e) {
//                     // Handling incorrect reference number exception
//                     System.out.println(e);
//                     // Skip the rest of the code in this iteration
//                     continue;
//                 }
//             }

//             // Printing loaded booking information, used for debugguing commented out
// 			// right now to avoid clutter in the terminal
//             // for (HashMap.Entry<String, Booking> entry : bookings.entrySet()) {
//             //     String key = entry.getKey();
//             //     Booking booking = entry.getValue();
//             //     String bookingInfo = "Booking ID: " + key +
//             //                         ", Passenger Name: " + booking.getPassengerName() +
//             //                         ", Flight Code: " + booking.getFlightCode() +
//             //                         ", Checked In: " + booking.isCheckedIn();
//             //     System.out.println(bookingInfo);
//             // }
//             // System.out.println("\n");
//             br.close();
//         }
//         catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // Method to validate booking reference number format
//     private boolean isValidBookingReference(String bookingRef) {
//         Pattern pattern = Pattern.compile("^[A-Z]{3}\\d{3}$");
//         Matcher matcher = pattern.matcher(bookingRef);
//         return matcher.matches();
//     }
    
//     // Method to load flights from a file
//     public void loadFlightsFromFile() {
//         String line = "";  
//         try {
//             BufferedReader br = new BufferedReader(new FileReader("flights.txt"));  
//             while ((line = br.readLine()) != null)
//             {  
//                 // Splitting each line by comma to extract flight information
//                 String[] fileLine = line.split(",");
//                 // Creating new Flight object and storing it in HashMap
//                 Flight newFlight = new Flight(fileLine[0],fileLine[1],fileLine[2],Integer.parseInt(fileLine[3]),Double.parseDouble(fileLine[4]),Double.parseDouble(fileLine[5]));
//                 flights.put(fileLine[0], newFlight);
//             }
//             // Printing loaded flight information, used for debugguing and commented out
// 			// right now to avoid clutter in the terminal
//             // for (HashMap.Entry<String, Flight> entry : flights.entrySet()) {
//             //     String key = entry.getKey();
//             //     Flight flight = entry.getValue();
//             //     String flightInfo = "FLight ID: " + key +
//             //                         ", Des: " + flight.getDestinationAirport() +
//             //                         ", carrie: " + flight.getCarrier() +
//             //                         ", Capacity: " + flight.getCapacity();
//             //     System.out.println(flightInfo);
//             // }
//             br.close();
//         }
//         catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
    
//     // Method to display check-in kiosk GUI
//     public void displayCheckInKiosk() {
//         // Creating CheckInGUI object and setting it up
//         CheckInGUI cg = new CheckInGUI(bookings);
//         // Adding a shutdown hook to generate a report before closing
//         Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//             generateReport("report.txt", cg);
//         }));
//     }

//     // Method to generate a report based on check-in data
//     public void generateReport(String filename, CheckInGUI checkInGUI) {
//         try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
//             // Getting list of passengers from GUI
//             ArrayList<Passenger> passengers = checkInGUI.getPassengers();
//             // Iterating through flights to generate report for each flight
//             for (HashMap.Entry<String, Flight> entry : flights.entrySet()) {
//                 Flight flight = entry.getValue();

//                 int checkedInPassengers = 0;
//                 double totalBaggageWeight = 0.0;
//                 double totalBaggageVolume = 0.0;
//                 double totalExcessBaggageFees = 0.0;
//                 String flightCode = flight.getFlightCode();

//                 // Calculating total checked-in passengers and baggage statistics for the flight
//                 for (Booking booking : bookings.values()) {
//                     if (booking.getFlightCode().equals(flightCode) && booking.isCheckedIn()) {
//                         checkedInPassengers++;
//                     }
//                     for (Passenger passenger : passengers) {
//                         if (booking.getBookingRefCode().equals(passenger.getBookingRef()) && booking.getFlightCode().equals(flightCode)) {
//                             totalBaggageWeight += passenger.getBaggageWeight();
//                             totalBaggageVolume += passenger.getBaggageVolume();
//                             totalExcessBaggageFees += passenger.getExcessBaggageFee();
//                         }
//                     }
//                 }

//                 // Checking if flight capacity is exceeded
//                 boolean isPassengerCapExceed = checkedInPassengers > flight.getCapacity();
//                 boolean isWeightCapExceed = totalBaggageWeight > flight.getMaxBaggageWeight();
//                 boolean isVolumeCapExceed = totalBaggageVolume > flight.getMaxBaggageVolume();

//                 // Writing flight report to file
//                 if (flightCode != null) {
//                     writer.write("Flight: " + flightCode);
//                     writer.newLine();
//                     writer.write("Checked-in Passengers: " + checkedInPassengers);
//                     writer.newLine();
//                     writer.write("Total Baggage Weight: " + totalBaggageWeight);
//                     writer.newLine();
//                     writer.write("Total Baggage Volume: " + totalBaggageVolume);
//                     writer.newLine();
//                     writer.write("Total Excess Baggage Fees: " + totalExcessBaggageFees);
//                     writer.newLine();
//                     writer.write("Flight Passenger Capacity Exceeded: " + isPassengerCapExceed);
//                     writer.newLine();
//                     writer.write("Flight Weight Capacity Exceeded: " + isWeightCapExceed);
//                     writer.newLine();
//                     writer.write("Flight Volume Capacity Exceeded: " + isVolumeCapExceed);
//                     writer.newLine();
//                     writer.write("--------------------------------------");
//                     writer.newLine();
//                 }
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }

// Class representing the Airport simulation
public class AirportSimulation {
    // File objects to hold booking and flight data
    public File bookingsFile;
    public File flightsFile;
    // HashMaps to store bookings and flights data
    public HashMap<String, Booking> bookings;
    public HashMap<String, Flight> flights;
    public AirportGUI airportGUI;
    private Lock consoleLock = new ReentrantLock();

    BlockingQueue<Passenger> passengerQueue;
    List<Thread> checkInThreads;
    List<CheckInDesk> desks; // Maintain references to CheckInDesk instances

    public AirportSimulation() {
        // Initialize queues, flights, bookings, etc.
        flights = new HashMap<>();
        bookings = new HashMap<>();
        passengerQueue = new ArrayBlockingQueue<>(100);
        checkInThreads = new ArrayList<>();
        desks = new ArrayList<>();
    }
    public AirportSimulation(AirportGUI airportGUI) {
        // Initialize queues, flights, bookings, etc.
        flights = new HashMap<>();
        bookings = new HashMap<>();
        passengerQueue = new ArrayBlockingQueue<>(100);
        checkInThreads = new ArrayList<>();
        desks = new ArrayList<>();
        this.airportGUI = airportGUI;
    }
    // Method to load bookings from a file
    public void loadBookingsFromFile() {
        String line = "";  
        try {
            BufferedReader br = new BufferedReader(new FileReader("bookings.txt"));  
            while ((line = br.readLine()) != null) {
                // Splitting each line by comma to extract booking information
                String[] fileLine = line.split(",");
                try {
                    // Validating booking reference number format
                    if (!isValidBookingReference(fileLine[0])) {
                        throw new IncorrectRefNumException(fileLine[0]);
                    }
                    // Creating new Booking object and storing it in HashMap
                    boolean checkedIn = fileLine[3].equals("true");
                    Booking newBooking = new Booking(fileLine[0], fileLine[1], fileLine[2], checkedIn);
                    bookings.put(fileLine[0], newBooking);
                } catch (IncorrectRefNumException e) {
                    // Handling incorrect reference number exception
                    System.out.println(e);
                    // Skip the rest of the code in this iteration
                    continue;
                }
            }

            for (HashMap.Entry<String, Booking> entry : bookings.entrySet()) {
                String key = entry.getKey();
                Booking booking = entry.getValue();
                String bookingInfo = "Booking ID: " + key +
                                    ", Passenger Name: " + booking.getPassengerName() +
                                    ", Flight Code: " + booking.getFlightCode() +
                                    ", Checked In: " + booking.isCheckedIn();
                System.out.println(bookingInfo);
            }
            System.out.println("\n");
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to validate booking reference number format
    private boolean isValidBookingReference(String bookingRef) {
        Pattern pattern = Pattern.compile("^[A-Z]{3}\\d{3}$");
        Matcher matcher = pattern.matcher(bookingRef);
        return matcher.matches();
    }


    
    // Method to load flights from a file
    public void loadFlightsFromFile() {
        String line = "";  
        try {
            BufferedReader br = new BufferedReader(new FileReader("flights.txt"));  
            while ((line = br.readLine()) != null)
            {  
                // Splitting each line by comma to extract flight information
                String[] fileLine = line.split(",");
                // Creating new Flight object and storing it in HashMap
                try {
                    if (!isValidFlightCode(fileLine[0])) {
                        throw new IncorrectFlightCodeException(fileLine[0]);
                    }
                    Flight newFlight = new Flight(fileLine[0],fileLine[1],fileLine[2],Integer.parseInt(fileLine[3]),Double.parseDouble(fileLine[4]),Double.parseDouble(fileLine[5]));
                    flights.put(fileLine[0], newFlight);
                } catch (IncorrectFlightCodeException e) {
                    System.out.println(e);
                    continue;
                }
            }

            for (HashMap.Entry<String, Flight> entry : flights.entrySet()) {
                String key = entry.getKey();
                Flight flight = entry.getValue();
                String flightInfo = "FLight ID: " + key +
                                    ", Des: " + flight.getDestinationAirport() +
                                    ", carrie: " + flight.getCarrier() +
                                    ", Capacity: " + flight.getCapacity();
                System.out.println(flightInfo);
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to validate flight code format
    private boolean isValidFlightCode(String flightCode) {
        Pattern pattern = Pattern.compile("^F\\d{3}$");
        Matcher matcher = pattern.matcher(flightCode);
        return matcher.matches();
    }



    // Method to start the simulation
    public void startSimulation() {
        System.out.println("Simulation Started");

       // Iterate over bookings and create passengers
        for (Booking booking : bookings.values()) {
            Passenger passenger = createPassengerFromBooking(booking);
            if (passenger != null) {
                try {
                    passengerQueue.put(passenger);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Create and start check-in threads
        for (int i = 0; i < 3; i++) { // Assuming 2 check-in desks
            CheckInDesk desk = new CheckInDesk(i + 1);
            Thread thread = new Thread(desk);
            thread.start();
            try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            checkInThreads.add(thread);
            desks.add(desk); // Add desk reference to the list
        }
 
        // Wait for all threads to finish
        for (Thread thread : checkInThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void updateGUI() {
   	    airportGUI.updateDesks(desks);
	    airportGUI.updateFlights(flights);
		airportGUI.updateQueue(passengerQueue);  	
    }
    // Method to create Passenger from Booking data
    private Passenger createPassengerFromBooking(Booking booking) {
        // Check if the passenger is checked in
        if (!booking.isCheckedIn()) {
            // Generate random baggage weight and dimensions
            Random random = new Random();
            double baggageWeight = random.nextDouble() * 30 + 1; // Random baggage weight
            double baggageLength = random.nextDouble() * 100; // Random baggage length
            double baggageWidth = random.nextDouble() * 50; // Random baggage width
            double baggageHeight = random.nextDouble() * 30; // Random baggage height
            double baggageVolume = baggageLength * baggageWidth * baggageHeight; // Random baggage height
            double excessBaggageFee = 0.0;
            if (baggageWeight > 40.0 && baggageVolume > 6000.0) {
                excessBaggageFee = 50.0; // If both weight and volume exceed the limits
            } else if (baggageWeight > 40.0) {
                excessBaggageFee = 20.0; // If only weight exceeds the limit
            } else if (baggageVolume > 6000.0) {
                excessBaggageFee = 30.0; // If only volume exceeds the limit
            }
            // Create Passenger object with generated data
            return new Passenger(booking.getPassengerName(), booking.getBookingRefCode(), baggageWeight, baggageHeight, baggageWidth, baggageLength, baggageVolume, excessBaggageFee);
        }
        return null; // Passenger is already checked in
    }

    // Method to print the current simulation state
    // private void printSimulationState() {
    //     consoleLock.lock(); // Acquire lock
    //     try {
    //         // Print passengers in the queue
    //         System.out.println("Passengers in Queue:");
    //         for (Passenger passenger : passengerQueue) {
    //             System.out.println(passenger.getLastName());
    //         }
    //         System.out.println();

    //         // Print details of each check-in desk
    //         // for (CheckInDesk desk : desks) {
    //         //     System.out.println("Desk " + desk.deskNumber + ":");
    //         //     if (desk.currentPassenger != null) {
    //         //         System.out.println("Last Name: " + desk.currentPassenger.getLastName());
    //         //         System.out.println("Baggage Weight: " + desk.currentPassenger.getBaggageWeight());
    //         //         System.out.println("Excess Baggage Fee: " + desk.currentPassenger.getExcessBaggageFee());
    //         //     } else {
    //         //         System.out.println("No passenger being processed");
    //         //     }
    //         //     System.out.println();
    //         // }
    //     }finally {
    //         consoleLock.unlock(); // Release lock
    //     }
    // }


    // Inner class representing a Check-in Desk as a Runnable
    class CheckInDesk implements Runnable {
        private int deskNumber;
        private Passenger currentPassenger;

        public CheckInDesk(int deskNumber) {
            this.deskNumber = deskNumber;
        }
        public int getDeskNumber() {
        	return deskNumber;
        }
        @Override
        public void run() {
            while (!passengerQueue.isEmpty()) {
                try {
                    currentPassenger = passengerQueue.take();
                    processPassenger(currentPassenger);
                    
                    //printSimulationState();
                    Thread.sleep(2000); // Simulate processing time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentPassenger = null;
        }

        // Method to process passenger
        private void processPassenger(Passenger passenger) {
            consoleLock.lock(); // Acquire lock
            if (airportGUI != null) {
            	updateGUI();
            }
            try {
                //Print passengers in the queue
                System.out.println("Passengers in Queue:");
                for (Passenger passengerz : passengerQueue) {
                    System.out.println(passengerz.getLastName());
                }
                System.out.println();
                System.out.println("Desk " + deskNumber + ":");
                if (passenger != null) {
                    
                    System.out.println("Last Name: " + passenger.getLastName());
                    System.out.println("Baggage Weight: " + passenger.getBaggageWeight());
                    System.out.println("Excess Baggage Fee: " + passenger.getExcessBaggageFee());
                } else {
                    System.out.println("No passenger being processed");
                }
                System.out.println();
            } finally {
                consoleLock.unlock(); // Release lock
            }
        }

        // Method to get the current passenger being processed
        public Passenger getCurrentPassenger() {
            return currentPassenger;
        }
    }

}
