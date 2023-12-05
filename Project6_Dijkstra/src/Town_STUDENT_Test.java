import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//author: Paizabu Min
public class Town_STUDENT_Test {
	Town t1;
    Town t2;
    Town t3;

    @Before
    public void setUp() throws Exception {
        t1 = new Town("Town1");
        t2 = new Town("Town2");
        t3 = new Town("Town3");
    }

    @After
    public void tearDown() throws Exception {
        t1 = null;
        t2 = null;
        t3 = null;
    }

    @Test
    public void testEquals() {
        Town town1Copy = new Town("Town1");
        assertTrue(town1Copy.equals(t1));
        assertFalse(t2.equals(t1));
    }

    @Test
    public void testGetName() {
        Town town1Copy = new Town("Town1");
        assertEquals(town1Copy.getName(), t1.getName());
    }

    @Test
    public void testToString() {
        assertEquals("Town1", t1.toString());
    }
}
