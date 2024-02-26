public class Passenger {
	String lastName;
	String bookingRef;
	double baggageWeight;
	double baggageVolume;
	
	public String getLastName() {
		return lastName;
	}
	public String getBookingRef(){
		return bookingRef;
	}
	public double getBaggageWeight(){
		return baggageWeight;
	}
	public double getBaggageVolume(){
		return baggageVolume;
	}
	public void setBaggageWeight(double weight){
		this.baggageWeight = weight;
	}
	public void setBaggageVolume(double volume){
		this.baggageVolume = volume;
	}
}