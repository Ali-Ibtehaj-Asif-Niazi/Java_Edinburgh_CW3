package src.main.java;
public class Flight {
    // Instance variables to store flight details
    String flightCode;
    String destinationAirport;
    String carrier;
    int capacity;
    double maxBaggageWeight;
    double maxBaggageVolume;

    // Constructor to initialize Flight object with provided details
    public Flight(String flightCode, String destinationAirport, String carrier, int capacity, double maxBaggageWeight, double maxBaggageVolume) throws IllegalArgumentException {
        try {
            // Validating flight code format
            if (!isValidFlightCode(flightCode)) {
                throw new IllegalArgumentException("IllegalArgumentException: Invalid flight code: " + flightCode + " in the flights.txt file.\nThe correct format is capital F and 2 numbers e.g F12.\nThe data corresponding to the wrong Flight Code is removed.\nApplication started with the correct data only.\n");
            }
            // Set instance variablesthis.flightCode = flightCode;
            this.flightCode = flightCode;
            this.destinationAirport = destinationAirport;
            this.carrier = carrier;
            this.capacity = capacity;
            this.maxBaggageWeight = maxBaggageWeight;
            this.maxBaggageVolume = maxBaggageVolume;
        } catch (IllegalArgumentException e) {
            this.capacity = -1; //indicator for null state
            this.maxBaggageWeight = -1.00; //indicator for null state
            this.maxBaggageVolume = -1.00; //indicator for null state
            // Print error message
            System.out.println(e.getMessage());
        }
    }

    // Method to validate flight code format
    protected boolean isValidFlightCode(String flightCode) {
        // Regular expression pattern for the flight code format
        String pattern = "^F\\d{2}$"; // F followed by two digits

        // Check if the flight code matches the pattern
        return flightCode.matches(pattern);
    }

    // Getter method to retrieve flight code
    public String getFlightCode() {
        return flightCode;
    }

    // Getter method to retrieve destination airport
    public String getDestinationAirport() {
        return destinationAirport;
    }

    // Getter method to retrieve carrier
    public String getCarrier() {
        return carrier;
    }

    // Getter method to retrieve capacity
    public int getCapacity() {
        return capacity;
    }

    // Getter method to retrieve maximum baggage weight allowed
    public double getMaxBaggageWeight() {
        return maxBaggageWeight;
    }

    // Getter method to retrieve maximum baggage volume allowed
    public double getMaxBaggageVolume() {
        return maxBaggageVolume;
    }
}
