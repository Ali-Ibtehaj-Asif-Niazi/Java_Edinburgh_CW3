public class Booking {
    // Instance variables to store booking details
    String bookingRefCode;
    String passengerName;
    String flightCode;
    boolean checkedIn;

    // Constructor to initialize Booking object with provided details
    public Booking(String bookingRefCode, String passengerName, String flightCode, boolean checkedIn) {
        this.bookingRefCode = bookingRefCode;
        this.passengerName = passengerName;
        this.flightCode = flightCode;
        this.checkedIn = checkedIn;
    }

    // Getter method to retrieve booking reference code
    public String getBookingRefCode() {
        return bookingRefCode;
    }

    // Getter method to retrieve passenger name
    public String getPassengerName() {
        return passengerName;
    }

    // Getter method to retrieve flight code
    public String getFlightCode() {
        return flightCode;
    }

    // Getter method to check if passenger is checked in
    public boolean isCheckedIn() {
        return checkedIn;
    }

    // Setter method to update checked-in status
    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
}
