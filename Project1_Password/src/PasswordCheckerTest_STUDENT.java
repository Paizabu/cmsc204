//author: Paizabu Min
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Paizabu Min
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> passwords;
	String pass0, pass1, pass2, pass3, pass4, pass5, pass6, pass7;

	@Before
	public void setUp() throws Exception {
		pass0 = "StrongPass@27";
		pass1 = "Ref23";
		pass2 = "galver@23";
		pass3 = "HIPPO$32";
		pass4 = "Exoliver@Now";
		pass5 = "BattleWound26";
		pass6 = "Optimmm@35";
		pass7 = "Craz@35";
		String[] p = {pass0, pass1, pass2, pass3, pass4, pass5, pass6, pass7};
		passwords = new ArrayList<String>();
		passwords.addAll(Arrays.asList(p));
	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try{
			//first case passes
			assertTrue(PasswordCheckerUtility.isValidPassword​(pass0));
			assertTrue("Pass0 is valid (passed)",true);
			//second case fails
			assertTrue(PasswordCheckerUtility.isValidPassword​(pass1));
		} catch(LengthException e) {
			assertTrue("Pass1 threw a lengthExcepetion (failed)",true);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try{
			//first case passes
			assertTrue(PasswordCheckerUtility.isValidPassword​(pass0));
			assertTrue("Pass0 is valid (passed)",true);
			//second case fails
			assertTrue(PasswordCheckerUtility.isValidPassword​(pass2));
		} catch(NoUpperAlphaException e) {
			assertTrue("Pass2 threw a NoUpperAlphaException (failed)",true);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try{
			//first case passes
			assertTrue(PasswordCheckerUtility.isValidPassword​(pass0));
			assertTrue("Pass0 is valid (passed)",true);
			//second case fails
			assertTrue(PasswordCheckerUtility.isValidPassword​(pass3));
		} catch(NoLowerAlphaException e) {
			assertTrue("Pass2 threw a NoLowerAlphaException (failed)",true);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try{
			//first case passes
			assertFalse(PasswordCheckerUtility.isWeakPassword​(pass0));
			assertTrue("Pass0 is valid (passed)",true);
			//second case fails
			assertTrue(PasswordCheckerUtility.isWeakPassword​(pass7));
		} catch(WeakPasswordException e) {
			assertTrue("Pass2 threw a WeakPasswordException (failed)",true);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try{
			//first case passes
			assertTrue(PasswordCheckerUtility.isValidPassword​(pass0));
			assertTrue("Pass0 is valid (passed)",true);
			//second case fails
			assertTrue(PasswordCheckerUtility.isValidPassword​(pass6));
		} catch(InvalidSequenceException e) {
			assertTrue("Pass2 threw a InvalidSequenceException (failed)",true);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try{
			//first case passes
			assertTrue(PasswordCheckerUtility.isValidPassword​(pass0));
			assertTrue("Pass0 is valid (passed)",true);
			//second case fails
			assertTrue(PasswordCheckerUtility.isValidPassword​(pass4));
		} catch(NoDigitException e) {
			assertTrue("Pass2 threw a NoDigitException (failed)",true);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		assertTrue(PasswordCheckerUtility.isValidPassword​(pass0));
		assertTrue(PasswordCheckerUtility.isValidPassword​("CMSC204@Lover"));
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> invalidList;
		invalidList = PasswordCheckerUtility.getInvalidPasswords​(passwords);
		assertEquals(invalidList.get(0), pass1 + " The password must be at least 6 characters long");
		assertEquals(invalidList.get(5), pass6 + " The password cannot contain more than two of the same character in sequence");
	}
	
}
