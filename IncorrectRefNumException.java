public class IncorrectRefNumException extends Exception{
	public IncorrectRefNumException(String dup){
		super("The Reference Number " + dup + " is Invalid. The correct format is 3 capital letters and 3 numbers e.g ABC123. Fix this and restart the application.");
	}
}