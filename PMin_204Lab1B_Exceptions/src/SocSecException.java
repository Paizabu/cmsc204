//Exception class
public class SocSecException extends RuntimeException{
	public SocSecException (String error) {
		super("Invalid social security number, " + error);
	}
}
