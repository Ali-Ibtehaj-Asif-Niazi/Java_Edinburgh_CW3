public class Booking {
	String bookingRefCode;
	String passengerName;
	String flightCode;
	boolean checkedIn;
	public Booking(String bookingRefCode,String passengerName,String flightCode,boolean checkedIn) {
		this.bookingRefCode = bookingRefCode;
		this.passengerName = passengerName;
		this.flightCode = flightCode;
		this.checkedIn = checkedIn;
	}
	
	public String getBookingRefCode() {
		return bookingRefCode;
	}
	public  String getPassengerName(){
		return passengerName;
	}
	public  String getFlightCode(){
		return flightCode;
	}
	public boolean isCheckedIn() {
		return checkedIn;
	}
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}
}