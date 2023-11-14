//author: Paizabu Min

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MorseCodeConverterTest_STUDENT {

	@Test
	public void testConvertToEnglishString() {	
		String converter1 = MorseCodeConverter.convertToEnglish(".... . .-.. .-.. --- / .-- --- .-. .-.. -.. ");
		assertEquals("hello world",converter1);
	}
	
	/**
	 * Testing for correct implementation of tree and traversal
	 */
	
	@Test
	public void testPrintTree() {	
		
		assertEquals("h s v i f u e l r a p w j  b d x n c k y t z g q m o", MorseCodeConverter.printTree());
	}
	
	/**
	 * Testing for correct conversion of all characters using key phrase to hit all letters
	 */
	
	@Test
	public void testConvertMorseStringToEnglishString() {	
		
		String converter1 = MorseCodeConverter.convertToEnglish("- .... . / --.- ..- .. -.-. -.- / -... .-. --- .-- -. / ..-. --- -..- / .--- ..- -- .--. ... / --- ...- . .-. / - .... . / .-.. .- --.. -.-- / -.. --- --.");
		assertEquals("the quick brown fox jumps over the lazy dog", converter1);
		
		//added student test
		String converter2 = MorseCodeConverter.convertToEnglish("- .... .. ... / .. ... / .- / ... - ..- -.. . -. - / - . ... -");
		assertEquals("this is a student test", converter2);
	}
	@Test
	public void testConvertMorseFileToEnglishString() {	
		
		/*Make sure howDoILoveThee.txt is in the src directory for this 
		  test to pass
		*/
		File file1 = new File("src/howDoILoveThee.txt"); 
		//added student files
		File file2 = new File("src/Daisy.txt"); 
		File file3 = new File("src/DaisyDaisy.txt"); 
		File file4 = new File("src/LoveLooksNot.txt"); 
		try {
			assertEquals("how do i love thee let me count the ways", MorseCodeConverter.convertToEnglish(file1));
			//added student tests
			assertEquals("give me your answer do", MorseCodeConverter.convertToEnglish(file2));
			assertEquals("im half crazy all for the love of you", MorseCodeConverter.convertToEnglish(file3));
			assertEquals("love looks not with the eyes but with the mind", MorseCodeConverter.convertToEnglish(file4));
		} catch (FileNotFoundException e) {
			assertTrue("An unwanted exception was caught", false);
		}
	}
	
}
