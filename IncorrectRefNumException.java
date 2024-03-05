// Custom exception class for handling invalid booking reference numbers
public class IncorrectRefNumException extends Exception {
    // Constructor to create an instance of IncorrectRefNumException with a custom error message
    public IncorrectRefNumException(String dup) {
        // Calling the superclass constructor to set the error message
        super("The Reference Number " + dup + " is Invalid. The correct format is 3 capital letters and 3 numbers e.g ABC123. Fix this and restart the application.");
    }
}
