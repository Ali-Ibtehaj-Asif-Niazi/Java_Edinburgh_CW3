public class Flight {
    // Instance variables to store flight details
    String flightCode;
    String destinationAirport;
    String carrier;
    int capacity;
    int currentCapacity;
    double maxBaggageWeight;
    double maxBaggageVolume;
    double currentBaggageVolume;
    double currentBaggageWeight;

    // Constructor to initialize Flight object with provided details
    public Flight(String flightCode, String destinationAirport, String carrier, int capacity, double maxBaggageWeight, double maxBaggageVolume){
        // Set instance variablesthis.flightCode = flightCode;
        this.flightCode = flightCode;
        this.destinationAirport = destinationAirport;
        this.carrier = carrier;
        this.capacity = capacity;
        this.maxBaggageWeight = maxBaggageWeight;
        this.maxBaggageVolume = maxBaggageVolume;
        currentBaggageWeight = 0;
        currentBaggageVolume = 0;
        currentCapacity = 0;
    }
    
    public void updateFlight(double weight,double volume) {
    	currentCapacity +=1;
    	currentBaggageVolume += volume;
    	currentBaggageWeight += weight;
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
    
   // Getter method to retrieve current capacity
    public int getCurrentCapacity() {
    	return currentCapacity;
    }
    // Getter method to retrieve current baggage weight
    public double getCurrentBaggageWeight() {
        return currentBaggageWeight;
    }

    // Getter method to retrieve current baggage volume
    public double getCurrentBaggageVolume() {
        return currentBaggageVolume;
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
