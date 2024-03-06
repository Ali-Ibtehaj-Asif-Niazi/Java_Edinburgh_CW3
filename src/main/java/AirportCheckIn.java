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

public class AirportCheckIn {
    // File objects to hold booking and flight data
    public File bookingsFile;
    public File flightsFile;
    // HashMaps to store bookings and flights data
    public HashMap<String, Booking> bookings;
    public HashMap<String, Flight> flights;
    // GUI object for check-in interface
    private CheckInGUI checkInGUI;

    // Constructor initializes HashMaps for bookings and flights
    public AirportCheckIn() {
        bookings = new HashMap<>();
        flights = new HashMap<>();
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

            // Printing loaded booking information, used for debugguing commented out
			// right now to avoid clutter in the terminal
            // for (HashMap.Entry<String, Booking> entry : bookings.entrySet()) {
            //     String key = entry.getKey();
            //     Booking booking = entry.getValue();
            //     String bookingInfo = "Booking ID: " + key +
            //                         ", Passenger Name: " + booking.getPassengerName() +
            //                         ", Flight Code: " + booking.getFlightCode() +
            //                         ", Checked In: " + booking.isCheckedIn();
            //     System.out.println(bookingInfo);
            // }
            // System.out.println("\n");
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
                Flight newFlight = new Flight(fileLine[0],fileLine[1],fileLine[2],Integer.parseInt(fileLine[3]),Double.parseDouble(fileLine[4]),Double.parseDouble(fileLine[5]));
                flights.put(fileLine[0], newFlight);
            }
            // Printing loaded flight information, used for debugguing and commented out
			// right now to avoid clutter in the terminal
            // for (HashMap.Entry<String, Flight> entry : flights.entrySet()) {
            //     String key = entry.getKey();
            //     Flight flight = entry.getValue();
            //     String flightInfo = "FLight ID: " + key +
            //                         ", Des: " + flight.getDestinationAirport() +
            //                         ", carrie: " + flight.getCarrier() +
            //                         ", Capacity: " + flight.getCapacity();
            //     System.out.println(flightInfo);
            // }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Method to display check-in kiosk GUI
    public void displayCheckInKiosk() {
        // Creating CheckInGUI object and setting it up
        CheckInGUI cg = new CheckInGUI(bookings);
        // Adding a shutdown hook to generate a report before closing
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            generateReport("report.txt", cg);
        }));
    }

    // Method to generate a report based on check-in data
    public void generateReport(String filename, CheckInGUI checkInGUI) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Getting list of passengers from GUI
            ArrayList<Passenger> passengers = checkInGUI.getPassengers();
            // Iterating through flights to generate report for each flight
            for (HashMap.Entry<String, Flight> entry : flights.entrySet()) {
                Flight flight = entry.getValue();

                int checkedInPassengers = 0;
                double totalBaggageWeight = 0.0;
                double totalBaggageVolume = 0.0;
                double totalExcessBaggageFees = 0.0;
                String flightCode = flight.getFlightCode();

                // Calculating total checked-in passengers and baggage statistics for the flight
                for (Booking booking : bookings.values()) {
                    if (booking.getFlightCode().equals(flightCode) && booking.isCheckedIn()) {
                        checkedInPassengers++;
                    }
                    for (Passenger passenger : passengers) {
                        if (booking.getBookingRefCode().equals(passenger.getBookingRef()) && booking.getFlightCode().equals(flightCode)) {
                            totalBaggageWeight += passenger.getBaggageWeight();
                            totalBaggageVolume += passenger.getBaggageVolume();
                            totalExcessBaggageFees += passenger.getExcessBaggageFee();
                        }
                    }
                }

                // Checking if flight capacity is exceeded
                boolean isPassengerCapExceed = checkedInPassengers > flight.getCapacity();
                boolean isWeightCapExceed = totalBaggageWeight > flight.getMaxBaggageWeight();
                boolean isVolumeCapExceed = totalBaggageVolume > flight.getMaxBaggageVolume();

                // Writing flight report to file
                if (flightCode != null) {
                    writer.write("Flight: " + flightCode);
                    writer.newLine();
                    writer.write("Checked-in Passengers: " + checkedInPassengers);
                    writer.newLine();
                    writer.write("Total Baggage Weight: " + totalBaggageWeight);
                    writer.newLine();
                    writer.write("Total Baggage Volume: " + totalBaggageVolume);
                    writer.newLine();
                    writer.write("Total Excess Baggage Fees: " + totalExcessBaggageFees);
                    writer.newLine();
                    writer.write("Flight Passenger Capacity Exceeded: " + isPassengerCapExceed);
                    writer.newLine();
                    writer.write("Flight Weight Capacity Exceeded: " + isWeightCapExceed);
                    writer.newLine();
                    writer.write("Flight Volume Capacity Exceeded: " + isVolumeCapExceed);
                    writer.newLine();
                    writer.write("--------------------------------------");
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
