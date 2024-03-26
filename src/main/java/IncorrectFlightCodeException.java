// Custom exception class for handling invalid flight code
public class IncorrectFlightCodeException extends Exception {
    // Constructor to create an instance of IncorrectRefNumException with a custom error message
    public IncorrectFlightCodeException(String dup) {
        // Calling the superclass constructor to set the error message
        super("The Flight Code " + dup + " in the flights.txt is Invalid.\nThe correct format is capital F and 3 numbers e.g F123.\nThe data corresponding to the wrong Flight Code is removed.\nApplication started with the correct data only.\n");
    }
}
