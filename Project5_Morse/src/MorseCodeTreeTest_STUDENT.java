import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//author: Paizabu Min
public class MorseCodeTreeTest_STUDENT {
	MorseCodeTree<String> tree;

	@Before
	public void setUp() throws Exception {
		tree = new MorseCodeTree<String>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void fetchTest() {
		assertEquals("e", tree.fetch("."));
		assertEquals("m", tree.fetch("--"));
		assertEquals("k", tree.fetch("-.-"));
		assertEquals("j", tree.fetch(".---"));
	}

}
