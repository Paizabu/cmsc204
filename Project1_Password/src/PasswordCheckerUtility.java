//author: Paizabu Min
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class PasswordCheckerUtility {
	//default constructor
	public PasswordCheckerUtility() {}
	
	/**
	 * Return true if valid password (follows all the following rules), returns false if an invalid password 
	 * @param password
	 * @return true if valid password (follows all rules from above), false if an invalid password
	 * @throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException
	 */
	public static boolean isValidPassword​(String password) 
			throws LengthException, NoUpperAlphaException, NoLowerAlphaException, 
			NoDigitException, NoSpecialCharacterException, InvalidSequenceException{

		isValidLength​(password);
		hasUpperAlpha​(password);
		hasLowerAlpha​(password);
		hasDigit​(password);
		hasSpecialChar​(password);
		NoSameCharInSequence​(password);
		
		return true;
	}
	
	/**
	 * Compare equality of two passwords; UnmatchedException - thrown if not same (case sensitive)
	 * @param password
	 * @param passwordConfirm
	 * @throws UnmatchedException
	 */
	public static void comparePasswords​(String password, String passwordConfirm) throws UnmatchedException{
		if(password.compareTo(passwordConfirm)!=0) {
			throw new UnmatchedException();
		}
	}
	
	/**
	 * Compare equality of two passwords with return value
	 * @param password
	 * @param passwordConfirm
	 * @return true if both same (case sensitive), false otherwise
	 */
	public static boolean comparePasswordsWithReturn​(String password, String passwordConfirm) {
		if(password.compareTo(passwordConfirm)==0) {
			return true;
		} else return false;
	}
	
	/**
	 * Checks the password length requirement - The password must be at least 6 characters long
	 * @param password
	 * @return true if meets minimum length requirement
	 * @throws LengthException
	 */
	public static boolean isValidLength​(String password) throws LengthException{
		if(password.length() < 6) {
			throw new LengthException();
		}else return false;
	}
	
	/**
	 * Checks the password alpha character requirement - Password must contain an uppercase alpha character
	 * @param password
	 * @return true if meet alpha character requirement
	 * @throws NoUpperAlphaException
	 */
	public static boolean hasUpperAlpha​(String password) throws NoUpperAlphaException{
		boolean hasUpper = false;
		for(int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				hasUpper = true;
			}
		}
		if(hasUpper==false) throw new NoUpperAlphaException();
		else return hasUpper;
	}
	
	/**
	 * Checks the password lowercase requirement - Password must contain at least one lowercase alpha character
	 * @param password
	 * @return true if meets lowercase requirement
	 * @throws NoLowerAlphaException
	 */
	public static boolean hasLowerAlpha​(String password) throws NoLowerAlphaException{
		boolean hasLower = false;
		for(int i = 0; i < password.length(); i++) {
			if (Character.isLowerCase(password.charAt(i))) {
				hasLower = true;
			}
		}
		if(hasLower==false) throw new NoLowerAlphaException();
		else return hasLower;
	}
	
	/**
	 * Checks the password Digit requirement - Password must contain a numeric character
	 * @param password
	 * @return true if meet Digit requirement
	 * @throws NoDigitException
	 */
	public static boolean hasDigit​(String password) throws NoDigitException{
		boolean hasDigit = false;
		for(int i = 0; i < password.length(); i++) {
			if (Character.isDigit(password.charAt(i))) {
				hasDigit = true;
			}
		}
		if (hasDigit==false) throw new NoDigitException();
		else return hasDigit;
	}
	
	/**
	 * Checks the password SpecialCharacter requirement - Password must contain a Special Character
	 * @param password
	 * @return true if meets SpecialCharacter requirement
	 * @throws NoSpecialCharacterException
	 */
	public static boolean hasSpecialChar​(String password) throws NoSpecialCharacterException{
		Pattern pattern = Pattern.compile("\\W");
		Matcher matcher = pattern.matcher(password);
		if(matcher.find()==false) throw new NoSpecialCharacterException();
		else return (matcher.find());
	}
	
	/**
	 * Checks the password Sequence requirement - 
	 * Password should not contain more than 2 of the same character in sequence
	 * @param password
	 * @return false if does NOT meet Sequence requirement
	 * @throws InvalidSequenceException
	 */
	public static boolean NoSameCharInSequence​(String password) throws InvalidSequenceException{
		Pattern pattern = Pattern.compile("(\\S)\\1\\1");
		Matcher matcher = pattern.matcher(password);
		if(matcher.find()==true) throw new InvalidSequenceException();
		else return (matcher.find());
	}
	
	/**
	 * checks if the password contains 6 to 9 characters
	 * @param password
	 * @return true if password contains 6 to 9 characters, false otherwise
	 */
	public static boolean hasBetweenSixAndNineChars​(String password) {
		if(password.length() >= 6 && password.length() <=9) {
			return true;
		} else return false;
	}
	
	/**
	 * Checks if password is VALID and the length is NOT between 6-9 characters
	 * @param password
	 * @return false if the password is valid and the length of password is NOT between 6 and 9 (inclusive).
	 * @throws WeakPasswordException
	 */
	public static boolean isWeakPassword​(String password) throws WeakPasswordException{
		if(isValidPassword​(password) == true && hasBetweenSixAndNineChars​(password)) {
			throw new WeakPasswordException();
		} else return false;
	}
	
	/**
	 * This method will accept an ArrayList of passwords as the parameter and 
	 * return an ArrayList with the status of any invalid passwords 
	 * (weak passwords are not considered invalid). 
	 * The ArrayList of invalid passwords will be of the following format: 
	 * password BLANK message of the exception thrown
	 * @param passwords
	 * @return
	 */ 
	public static ArrayList<String> getInvalidPasswords​(ArrayList<String> passwords){
		ArrayList<String> invalidList = new ArrayList<>();
		int count = 0;
		while(count < passwords.size()){
			try {
				isValidPassword​(passwords.get(count));
			} catch(Exception e){
				invalidList.add(passwords.get(count) + " " + e.getMessage());
			} 
			count++;
		}
		return invalidList;
	}

}
