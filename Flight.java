import java.util.List;

public class Flight {
  String flightCode;
	String destinationAirport;
	String carrier;
  int capacity;
  double maxBaggageWeight;
  double maxBaggageVolume;
    
  public Flight(String flightCode, String destinationAirport, String carrier, int capacity, double maxBaggageWeight, double maxBaggageVolume) {
    this.flightCode = flightCode;
    this.destinationAirport = destinationAirport;
    this.carrier = carrier;
    this.capacity = capacity;
    this.maxBaggageWeight = maxBaggageWeight;
    this.maxBaggageVolume = maxBaggageVolume;
	}
  
  public  String getFlightCode(){
		return flightCode;
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
}
