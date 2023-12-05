import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//author: Paizabu Min
public class TownGraphManager_STUDENT_Test {

	private TownGraphManagerInterface graph;
	private String[] town;
	  
	@Before
	public void setUp() throws Exception {
		  graph = new TownGraphManager();
		  town = new String[6];
		  
		  for (int i = 1; i < 6; i++) {
			  town[i] = "Town_" + i;
			  graph.addTown(town[i]);
		  }

		  graph.addRoad(town[1], town[2], 6, "Road_1");
		  graph.addRoad(town[1], town[4], 1, "Road_2");
		  graph.addRoad(town[2], town[4], 2, "Road_3");
		  graph.addRoad(town[2], town[5], 2, "Road_4");
		  graph.addRoad(town[2], town[3], 5, "Road_5");
		  graph.addRoad(town[4], town[5], 1, "Road_6");
		  graph.addRoad(town[3], town[5], 5, "Road_7");
		 
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
		assertEquals("Road_3", roads.get(2));
		assertEquals("Road_4", roads.get(3));
		graph.addRoad(town[1], town[5], 8,"Road_8");
		roads = graph.allRoads();
		assertEquals("Road_8", roads.get(7));
		
	}

	@Test
	public void testGetRoad() {
		assertEquals("Road_6", graph.getRoad(town[4], town[5]));
		assertEquals("Road_3", graph.getRoad(town[2], town[4]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Town_6"));
		graph.addTown("Town_6");
		assertEquals(true, graph.containsTown("Town_6"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("Town_8"));
		graph.addTown("Town_8");
		ArrayList<String> path = graph.getPath(town[1],"Town_8");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		assertEquals(false, graph.containsTown("Town_8"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[2], town[5]));
		assertEquals(false, graph.containsRoadConnection(town[1], town[3]));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
		assertEquals("Road_3", roads.get(2));
		assertEquals("Road_4", roads.get(3));
		assertEquals("Road_5", roads.get(4));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[2], town[1]));
		graph.deleteRoadConnection(town[2], town[1], "Road_1");
		assertEquals(false, graph.containsRoadConnection(town[2], town[1]));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		graph.deleteTown(town[2]);
		assertEquals(false, graph.containsTown("Town_2"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = graph.allTowns();
		assertEquals("Town_1", roads.get(0));
		assertEquals("Town_2", roads.get(1));
		assertEquals("Town_3", roads.get(2));
		assertEquals("Town_4", roads.get(3));
		assertEquals("Town_5", roads.get(4));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(town[1],town[3]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via Road_2 to Town_4 1 mi",path.get(0).trim());
		  assertEquals("Town_4 via Road_6 to Town_5 1 mi",path.get(1).trim());
		  assertEquals("Town_5 via Road_7 to Town_3 5 mi",path.get(2).trim());

	}
	
}
