import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseDBManager_STUDENT_Test {
	private CourseDBManagerInterface dataMgr = new CourseDBManager();

	/**
	 * Create an instance of CourseDBManager
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		dataMgr = new CourseDBManager();
	}

	/**
	 * Set dataMgr reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		dataMgr = null;
	}

	/**
	 * Test for the add method
	 */
	@Test
	public void testAddToDB() {
		try {
			dataMgr.add("CMSC203",30504,4,"SC450","Douglas Mc. Farty");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	@Test
	public void testGet() {
		try {
			dataMgr.add("CMSC203",30504,4,"SC450","Douglas Mc. Farty");
			assertEquals(dataMgr.get(30504).getName(), "Douglas Mc. Farty");
			dataMgr.get(22222);
		} catch(Exception e) {
			assertTrue("Should have caused an Exception", true);
		}
	}
	
	/**
	 * Test for the showAll method
	 */
	@Test
	public void testShowAll() {
		try {
			
			dataMgr.add("CMSC203",30504,4,"SC450","Douglas Mc. Farty");
			dataMgr.add("CMSC203",30503,4,"SC450","Hilga Ester");
			dataMgr.add("CMSC204",30559,4,"SC450","Fickle D. Shmickle");
			ArrayList<String> list = dataMgr.showAll();
			assertEquals(list.get(0),"\nCourse:CMSC204 CRN:30559 Credits:4 Instructor:Fickle D. Shmickle Room:SC450");
		 	assertEquals(list.get(1),"\nCourse:CMSC203 CRN:30503 Credits:4 Instructor:Hilga Ester Room:SC450");
			assertEquals(list.get(2),"\nCourse:CMSC203 CRN:30504 Credits:4 Instructor:Douglas Mc. Farty Room:SC450");
			assertEquals(list.get(3),"\nCourse:CMSC203 CRN:30504 Credits:4 Instructor:Douglas Mc. Farty Room:SC450");

		} catch(Exception e) {
			assertTrue("Successfully threw a NullException",true);
		}
	}
	
	/**
	 * Test for the read method
	 */
	@Test
	public void testRead() {
		try {
			File inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("# Ignore this");
			inFile.println("CMSC100 223AB 2 SW217 Gloria E. Barron");
			inFile.println("CMSC203 30504 4 SC450 Douglas Mc. Farty # Ignore this");
			inFile.println("CMSC110 3 SC450 Behzad Maghami");
			inFile.println("CMSC110 21564 SC451 Behzad Maghami");
			inFile.print("CMSC204 30503 4 SC450 Hilga Ester");
			
			inFile.close();
			dataMgr.readFile(inputFile);
			assertEquals("Douglas Mc. Farty",dataMgr.get(30504).getName());
			assertEquals("CMSC204",dataMgr.get(30503).getID());
			//assertEquals("SC451",dataMgr.get(21564).getRoomNum());
			assertEquals("Hilga Ester",dataMgr.get(30503).getName());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
		
		try {
			File inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CMSC110 3 SC450 Behzad Maghami");
			
			inFile.close();
			dataMgr.readFile(inputFile);
			assertEquals("SC451",dataMgr.get(21564).getRoomNum());
			fail("Should not have passed");
		} catch(Exception e) {
			assertTrue("Successfully threw an Exception",true);
		}
	}

}
