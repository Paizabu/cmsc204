//author: Paizabu Min
public class UnmatchedException extends RuntimeException{
	public UnmatchedException() {
		super("Passwords do not match");
	}
}
