import java.util.List;

public class Flight {
	String destinationAirport;
	String carrier;
    int capacity;
    double maxBaggageWeight;
    double maxBaggageVolume;
    List<Passenger> passengers;
    
    public Flight(String destinationAirport, String carrier, int capacity, double maxBaggageWeight, double maxBaggageVolume) {
		this.destinationAirport = destinationAirport;
		this.carrier = carrier;
		this.capacity = capacity;
		this.maxBaggageWeight = maxBaggageWeight;
		this.maxBaggageVolume = maxBaggageVolume;
	}
	public String getDestinationAirport() {
    	return destinationAirport;
    }
    public String getCarrier(){
    	return carrier;
    }
    public int getCapacity(){
    	return capacity;
    }
    public double getMaxBaggageWeight(){
    	return maxBaggageWeight;
    }
    public double getMaxBaggageVolume(){
    	return maxBaggageVolume;
    }
    public List<Passenger> getPassengers(){
    	return passengers;
    }
    public void addPassenger(Passenger passenger){
    	passengers.add(passenger);
    }
}
