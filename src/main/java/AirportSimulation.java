import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Class representing the Airport simulation
public class AirportSimulation {
    // File objects to hold booking and flight data
    public File bookingsFile;
    public File flightsFile;
    // HashMaps to store bookings and flights data
    public HashMap<String, Booking> bookings;
    public HashMap<String, Flight> flights;
    public AirportGUI airportGUI;
    private static final long SIMULATION_DURATION_MS = 2 * 60 * 1000; // Simulation duration: 2 minutes

    private static final AirportLogger LOGGER = AirportLogger.getInstance();

    private volatile boolean isAddingPassengers = true;
    private volatile boolean forOnce = true;

    Queue<Passenger> passengerQueue;
    List<Thread> checkInThreads;
    List<CheckInDesk> desks; // Maintain references to CheckInDesk instances
    private int processingTime;
    
    public AirportSimulation(AirportGUI airportGUI) {
        // Initialise queues, flights, bookings, etc.
        flights = new HashMap<>();
        bookings = new HashMap<>();
        passengerQueue = new LinkedList<>();
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
            System.out.println("\n Bookings Loaded from File\n");
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
            System.out.println(" Flights Loaded from File\n");
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
        LOGGER.log("Simulation Started");
        Thread threadQueue = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isAddingPassengers) {
                    if(forOnce){
                        for (Booking booking : bookings.values()) {
                            Passenger passenger = createPassengerFromBooking(booking);
                            if (passenger != null) {
                                try {
                                    LOGGER.log("Passenger joined the queue, Last Name: " + passenger.getLastName());
                                    passengerQueue.offer(passenger);
                                    if (airportGUI != null) {
                                        updateGUI();
                                    }
                                    Thread.sleep(processingTime / 3); // Wait for prcessingTime / 3  milliseconds before adding the next passenger (/3 to build up a queue for demonstration)
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        forOnce = false;
                    }
                    else{}
                }
            }
        });
        threadQueue.start();

        
        // Create and start check-in threads
        openDesks(2);
        addGUI();

        //Schedule a task to stop the simulation after the specified duration
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LOGGER.log("All Check-in desks are now closed. Happy Flight!");
                stopQueueProcessing();
                for (CheckInDesk desk_running : desks) {
                    desk_running.stopProcessing();
                    updateGUI();
                }
                timer.cancel();
            }
        }, SIMULATION_DURATION_MS);
        //System.exit(0);
    }

    void openDesks(int n) {
    	if (n > 0) {
    		int s  = desks.size();
    		for (int i = s; i < n + s; i++) { // Assuming 2 check-in desks
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
    		
    	}
    	else if (n < 0) {
    		for (int i = 0; i < -n; i++) {
    			desks.get(i).stop();
    			desks.remove(i);
    		}
    	}
    	if (n != 0) {
    		addGUI();
    	}
    }

    // Method to stop the thread processing
    public void stopQueueProcessing() {
        isAddingPassengers = false;
    }

    private void addGUI() {
    	airportGUI.addFlights(flights);
    	airportGUI.addDesks(desks);
    }

    private void updateGUI() {
   	    airportGUI.updateDesks(desks);
	    airportGUI.updateFlights(flights);
		airportGUI.updateQueue(passengerQueue);  
		openDesks(airportGUI.checkDesksToOpen());
		processingTime = airportGUI.getProcessingTime();
    }

    // Method to create Passenger from Booking data
    private Passenger createPassengerFromBooking(Booking booking) {
        // Check if the passenger is checked in
        if (!booking.isCheckedIn()) {
            // Generate random baggage weight and dimensions
            Random random = new Random();
            double baggageWeight = random.nextDouble() * 30 + 1; // Random baggage weight
            double baggageLength = random.nextDouble() * 20; // Random baggage length in inches
            double baggageWidth = random.nextDouble() * 10; // Random baggage width in inches
            double baggageHeight = random.nextDouble() * 35; // Random baggage height in inches
            double baggageVolume = baggageLength * baggageWidth * baggageHeight * 0.0163871; // Volume calculation (litres) (inch*inch*inch*0.0163871 = litres)
            double excessBaggageFee = 0.0;
            double maxWeight = 40;
            double maxVolume = 110; //my bag's volume
            if (baggageWeight > maxWeight && baggageVolume > maxVolume) { 
                excessBaggageFee = 50.0; // If both weight and volume exceed the limits
            } else if (baggageWeight > maxWeight) {
                excessBaggageFee = 20.0; // If only weight exceeds the limit
            } else if (baggageVolume > maxVolume) { 
                excessBaggageFee = 30.0; // If only volume exceeds the limit
            }
            // Create Passenger object with generated data
            return new Passenger(booking.getPassengerName(), booking.getBookingRefCode(), baggageWeight, baggageHeight, baggageWidth, baggageLength, baggageVolume, excessBaggageFee);
        }
        return null; // Passenger is already checked in
    }

    // Inner class representing a Check-in Desk as a Runnable
    class CheckInDesk implements Runnable {
        private int deskNumber;
        private Passenger currentPassenger;
        private volatile boolean processing; // Flag to control processing

        public CheckInDesk(int deskNumber) {
            this.deskNumber = deskNumber;
            this.processing = true; // Start processing initially
        }
        public int getDeskNumber() {
        	return deskNumber;
        }
        public void stop() {
        	processing = false;
        }
        @Override
        public void run() {
            while (processing) {
                if(!passengerQueue.isEmpty()) {
                    try {
                    	
                        currentPassenger = passengerQueue.poll();
                        processPassenger(currentPassenger);
                        Thread.sleep(processingTime); // Simulate processing time
                        getOnFlight(currentPassenger);
                        Thread.sleep(200); // Simulate processing time
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        currentPassenger = null;
                        processPassenger(currentPassenger);
                        Thread.sleep(processingTime); // Simulate processing time
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (airportGUI != null) {
                	updateGUI();
                }
            }
        }

        private void getOnFlight(Passenger passenger) {
            LOGGER.log("Passenger Ready to board flight, Last Name: " + passenger.getLastName());
        	String ref = passenger.getBookingRef();
        	Booking booking = bookings.get(ref);
        	Flight flight = flights.get(booking.getFlightCode());
        	booking.setCheckedIn(true);
        	flight.updateFlight(passenger.getBaggageWeight(), passenger.getBaggageVolume());
        }

        // Method to process passenger
        private void processPassenger(Passenger passenger) {
            if(passenger!=null)
            {LOGGER.log("Passenger checking in, Last Name: " + passenger.getLastName());}
        }

        // Method to get the current passenger being processed
        public Passenger getCurrentPassenger() {
            return currentPassenger;
        }

        // Method to stop processing passengers
        public void stopProcessing() {
            this.processing = false;
        }
    }

}
