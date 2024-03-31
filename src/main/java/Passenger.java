public class Passenger {
    // Instance variables to store passenger details
    private String lastName;
    private String bookingRef;
    private double baggageWeight;
    private double baggageVolume;

    private double baggageLength;
    private double baggageWidth;
    private double baggageHeight;

    private double excessBaggageFee;
    boolean cabinClass; //true = economy & false = business

    // Constructor to initialize Passenger object with provided details
    public Passenger(String lastName, String bookingRef, double baggageWeight, double baggageHeight, double baggageWidth, double baggageLength, double baggageVolume, double excessBaggageFee, boolean cabinClass) {
        this.lastName = lastName;
        this.bookingRef = bookingRef;
        this.baggageWeight = baggageWeight;
        this.baggageVolume = baggageVolume;
        this.baggageLength = baggageLength;
        this.baggageWidth = baggageWidth;
        this.baggageHeight = baggageHeight;
        this.excessBaggageFee = excessBaggageFee;
        this.cabinClass = cabinClass;
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

    public double getBaggageLength() {
        return baggageLength;
    }

    public double getBaggageWidth() {
        return baggageWidth;
    }

    public double getBaggageHeight() {
        return baggageHeight;
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

    public void setBaggageLength(double length) {
        this.baggageLength = length;
    }

    public void setBaggageWidth(double width) {
        this.baggageWidth = width;
    }

    public void setBaggageHeight(double height) {
        this.baggageHeight = height;
    }

    // Setter method to update excess baggage fee
    public void setExcessBaggageFee(double fee) {
        this.excessBaggageFee = fee;
    }

    // Getter method to check cabin class
    public boolean getCabinClass() {
        return cabinClass;
    }
}
