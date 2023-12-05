import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {

    Road r1;
    Road r2;
    Town t1;
    Town t2;
    Town t3;

    @Before
    public void setUp() throws Exception {
        t1 = new Town("Town1");
        t2 = new Town("Town2");
        t3 = new Town("Town3");
        r1 = new Road(t1, t2, 5, "Road1");
        r2 = new Road(t2, t3, 7, "Road2");
    }

    @After
    public void tearDown() throws Exception {
        t1 = null;
        t2 = null;
        t3 = null;
        r1 = null;
        r2 = null;
    }

    @Test
    public void testContains() {
        assertTrue(r1.contains(t1));
        assertFalse(r1.contains(t3));
    }

    @Test
    public void testToString() {
        assertEquals(r1.toString(), "Town1 via Road1 to Town2 5 mi");
    }

    @Test
    public void testEquals() {
        Road road1Copy = new Road(t1, t2, 5, "Road1");
        assertTrue(road1Copy.equals(r1));
        assertFalse(road1Copy.equals(r2));
    }

    @Test
    public void testGetSource() {
        assertEquals(r1.getSource(), t1);
    }

    @Test
    public void testGetDestination() {
        assertEquals(r1.getDestination(), t2);
    }

    @Test
    public void testGetWeight() {
        assertEquals(r1.getWeight(), 5);
    }

    @Test
    public void testGetName() {
        assertEquals(r1.getName(), "Road1");
    }

}
