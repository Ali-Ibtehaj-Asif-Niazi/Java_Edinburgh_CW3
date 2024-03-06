package src.main.java;
public class Passenger {
    // Instance variables to store passenger details
    private String lastName;
    private String bookingRef;
    private double baggageWeight;
    private double baggageVolume;
    private double excessBaggageFee;

    // Constructor to initialize Passenger object with provided details
    public Passenger(String lastName, String bookingRef, double baggageWeight, double baggageVolume, double excessBaggageFee) {
        this.lastName = lastName;
        this.bookingRef = bookingRef;
        this.baggageWeight = baggageWeight;
        this.baggageVolume = baggageVolume;
        this.excessBaggageFee = excessBaggageFee;
    }

    // Getter method to retrieve last name
    public String getLastName() {
        return lastName;
    }

    // Getter method to retrieve booking reference
    public String getBookingRef() {
        return bookingRef;
    }

    // Getter method to retrieve baggage weight
    public double getBaggageWeight() {
        return baggageWeight;
    }

    // Getter method to retrieve baggage volume
    public double getBaggageVolume() {
        return baggageVolume;
    }

    // Getter method to retrieve excess baggage fee
    public double getExcessBaggageFee() {
        return excessBaggageFee;
    }

    // Setter method to update baggage weight
    public void setBaggageWeight(double weight) {
        this.baggageWeight = weight;
    }

    // Setter method to update baggage volume
    public void setBaggageVolume(double volume) {
        this.baggageVolume = volume;
    }

    // Setter method to update excess baggage fee
    public void setExcessBaggageFee(double fee) {
        this.excessBaggageFee = fee;
    }
}
