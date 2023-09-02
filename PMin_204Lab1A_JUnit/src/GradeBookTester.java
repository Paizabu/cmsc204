import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTester {
	GradeBook g1, g2;
	
	@BeforeEach
	void setUp() throws Exception {
		g1 = new GradeBook(5);
		g1.addScore(50);
		g1.addScore(75);
		g2 = new GradeBook(5);
		g2.addScore(60);
		g2.addScore(70);
	}

	@AfterEach
	void tearDown() throws Exception {
		g1 = null;
		g2 = null;
	}
	
	@Test
	void testAddScore() {
		assertTrue(g1.addScore(80));
		assertTrue(g1.toString().equals("50.0 75.0 80.0 "));
		g1.addScore(85);
		g1.addScore(90);
		assertFalse(g1.addScore(100));
		assertEquals(5, g1.getScoreSize(), .0001);
	}
	
	@Test
	void testSum() {
		assertEquals(125.0, g1.sum(), .00001);
		g1.addScore(80);
		assertEquals(205.0, g1.sum(), .00001);
	}
	
	@Test
	void testMinimum() {
		assertEquals(50.0, g1.minimum(), .00001);
		g1.addScore(45);
		assertEquals(45.0, g1.minimum(), .00001);
	}
	
	@Test 
	void testFinalScore() {
		assertEquals(75.0, g1.finalScore(), .0001);
		g1.addScore(45);
		assertEquals(125.0, g1.finalScore(), .0001);
	}
	
	@Test
	void testToString() {
		assertTrue(g1.toString().equals("50.0 75.0 "));
		assertTrue(g2.toString().equals("60.0 70.0 "));
	}

}
