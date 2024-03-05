public class Passenger {
    private String lastName;
    private String bookingRef;
    private double baggageWeight;
    private double baggageVolume;
    private double excessBaggageFee;

    // Constructor
    public Passenger(String lastName, String bookingRef, double baggageWeight, double baggageVolume, double excessBaggageFee) {
        this.lastName = lastName;
        this.bookingRef = bookingRef;
        this.baggageWeight = baggageWeight;
        this.baggageVolume = baggageVolume;
        this.excessBaggageFee = excessBaggageFee;
    }

    // Getters and setters
    public String getLastName() {
        return lastName;
    }

    public String getBookingRef() {
        return bookingRef;
    }

    public double getBaggageWeight() {
        return baggageWeight;
    }

    public double getBaggageVolume() {
        return baggageVolume;
    }

    public double getExcessBaggageFee() {
        return excessBaggageFee;
    }

    public void setBaggageWeight(double weight) {
        this.baggageWeight = weight;
    }

    public void setBaggageVolume(double volume) {
        this.baggageVolume = volume;
    }

    public void setExcessBaggageFee(double fee) {
        this.excessBaggageFee = fee;
    }
}
