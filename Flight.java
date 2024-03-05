public class Flight {
    // Instance variables to store flight details
    String flightCode;
    String destinationAirport;
    String carrier;
    int capacity;
    double maxBaggageWeight;
    double maxBaggageVolume;

    // Constructor to initialize Flight object with provided details
    public Flight(String flightCode, String destinationAirport, String carrier, int capacity, double maxBaggageWeight, double maxBaggageVolume) {
        this.flightCode = flightCode;
        this.destinationAirport = destinationAirport;
        this.carrier = carrier;
        this.capacity = capacity;
        this.maxBaggageWeight = maxBaggageWeight;
        this.maxBaggageVolume = maxBaggageVolume;
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
