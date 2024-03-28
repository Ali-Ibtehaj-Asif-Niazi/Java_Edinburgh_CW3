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
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
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
    private static final long SIMULATION_DURATION_MS = TimeUnit.MINUTES.toMillis(1); // Simulation duration: 30 minutes

    private static final Logger LOGGER = Logger.getLogger(AirportSimulation.class.getName());
    private static final String LOG_FILE_PATH = "simulation.log";

    private volatile boolean isAddingPassengers = true;
    private volatile boolean forOnce = true;

    BlockingQueue<Passenger> passengerQueue;
    List<Thread> checkInThreads;
    List<CheckInDesk> desks; // Maintain references to CheckInDesk instances
    private int processingTime;
    
    public AirportSimulation(AirportGUI airportGUI) {
        // Initialise Logger
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE_PATH);
            fileHandler.setFormatter(new CustomFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Initialise queues, flights, bookings, etc.
        flights = new HashMap<>();
        bookings = new HashMap<>();
        passengerQueue = new ArrayBlockingQueue<>(100);
        checkInThreads = new ArrayList<>();
        desks = new ArrayList<>();
        this.airportGUI = airportGUI;
        processingTime = 5000;
    }

    // Custom log formatter to remove redundant parts
    static class CustomFormatter extends SimpleFormatter {
        @Override
        public synchronized String format(java.util.logging.LogRecord record) {
            return String.format("%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %2$s%n%4$s%n",
                    record.getMillis(),
                    record.getMessage(),
                    record.getLevel().getName(),
                    record.getThrown() != null ? record.getThrown() : "");
        }
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
        LOGGER.info("Simulation Started");
       
        Thread threadQueue = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isAddingPassengers) {
                    if(forOnce){
                        for (Booking booking : bookings.values()) {
                            Passenger passenger = createPassengerFromBooking(booking);
                            if (passenger != null) {
                                try {
                                    LOGGER.info("Passenger joined the queue, Last Name: " + passenger.getLastName());
                                    passengerQueue.put(passenger);
                                    if (airportGUI != null) {
                                        updateGUI();
                                    }
                                    Thread.sleep(2000); // Wait for 500 milliseconds before adding the next passenger
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

        
        // Introduce a delay before starting the check-in threads
        try {
            Thread.sleep(2000); // Wait few seconds before starting check-in threads
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Create and start check-in threads
        openDesks(2);
 
        //Schedule a task to stop the simulation after the specified duration
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Simulation ended. Check-in desks are now closed.");
                stopQueueProcessing();
                for (CheckInDesk desk_running : desks) {
                    desk_running.stopProcessing();
                }
                if (airportGUI != null) {
            	updateGUI();
            }
                timer.cancel();
            }
        }, SIMULATION_DURATION_MS);
        //System.exit(0);
    }

    void openDesks(int n) {
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
    // Method to stop the thread processing
    public void stopQueueProcessing() {
        isAddingPassengers = false;
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
        @Override
        public void run() {
            while (processing) {
                if(!passengerQueue.isEmpty()) {
                    try {
                        currentPassenger = passengerQueue.take();
                        processPassenger(currentPassenger);
                        Thread.sleep(processingTime); // Simulate processing time
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
            }
        }

        // Method to process passenger
        private void processPassenger(Passenger passenger) {
            if (airportGUI != null) {
            	updateGUI();
            }
            if(passenger!=null)
            {LOGGER.info("Passenger checking in, Last Name: "+passenger.getLastName());}
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
