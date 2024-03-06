package src.main.java;
// Custom exception class for handling invalid booking reference numbers
public class IncorrectRefNumException extends Exception {
    // Constructor to create an instance of IncorrectRefNumException with a custom error message
    public IncorrectRefNumException(String dup) {
        // Calling the superclass constructor to set the error message
        super("The Reference Number " + dup + " in the bookings.txt is Invalid.\nThe correct format is 3 capital letters and 3 numbers e.g ABC123.\nThe data corresponding to the wrong booking ref number is removed.\nApplication started with the correct data only.\n");
    }
}
