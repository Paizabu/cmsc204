//author: Paizabu Min
import java.io.*;
import java.util.*;

public class TownGraphManager implements TownGraphManagerInterface {
	private Graph graph;
	public TownGraphManager() {
		graph = new Graph();
	}
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		if(!graph.containsEdge(t1, t2)) {
			graph.addEdge(t1, t2, weight, roadName);
			return true;
		}
		else return false;	
	}
	
	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	public String getRoad(String town1, String town2) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		if(graph.containsEdge(t1, t2)) {
			return graph.getEdge(t1, t2).getName();
		}
		else return null;
	}
	
	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	public boolean addTown(String v) {
		Town t = new Town(v);
		if(!graph.containsVertex(t)) {
			graph.addVertex(t);
			return true;
		}
		else return false;
	}
	
	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	public Town getTown(String name) {
		Town t = new Town(name);
		if(graph.vertexSet().contains(t)){
			return t;
		}
		else return null;
	}
	
	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	public boolean containsTown(String v) {
		Town t = new Town(v);
		return graph.vertexSet().contains(t);
	}
	
	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	public boolean containsRoadConnection(String town1, String town2) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		return graph.containsEdge(t1, t2);
	}
	
	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	public ArrayList<String> allRoads(){
		ArrayList<String> roads = new ArrayList<>();
		for(Road r : graph.edgeSet()) {
			if(!roads.contains(r.getName())) {
				roads.add(r.getName());
			}
		}
		Collections.sort(roads);
		return roads;
	}
	
	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		Road r = graph.getEdge(t1, t2);
		if(graph.containsEdge(t1, t2)) {
			graph.removeEdge(t1, t2, r.getWeight(), road);
			return true;
		}
		else return false;
	}
	
	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	public boolean deleteTown(String v) {
		Town t = new Town(v);
		if(graph.containsVertex(t)) {
			graph.removeVertex(t);
			return true;
		}
		else return false;
	}

	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	public ArrayList<String> allTowns(){
		ArrayList<String> townSet = new ArrayList<>();
		for(Town t : graph.vertexSet()) {
			if(!townSet.contains(t.getName())) {
				townSet.add(t.getName());
			}
		}
		Collections.sort(townSet);
		return townSet;
	}
	
	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	public ArrayList<String> getPath(String town1, String town2){
		ArrayList<String> shortestPath = new ArrayList<>();

		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		if (graph.edgesOf(t1).isEmpty() || graph.edgesOf(t2).isEmpty()) {
			return shortestPath;
		}
		shortestPath = graph.shortestPath(t1, t2);

		return shortestPath;
	}
	
	/**
	 * reads from text file and populates the graph
	 * @param file
	 * @throws FileNotFoundException
	 */
	public void populateTownGraph(File file) throws FileNotFoundException{
		Scanner scn = new Scanner(file);
		Set<Road> roads = new HashSet<>();
		while(scn.hasNextLine()) {
			String line = scn.nextLine();
			String splitLine[] = line.split("[,;]");
			
			Town t1 = new Town(splitLine[2]);
			graph.addVertex(t1);
			Town t2 = new Town(splitLine[3]);
			graph.addVertex(t2);	
			
			Road r = new Road(t1, t2, Integer.parseInt(splitLine[1]), splitLine[0]);
			roads.add(r);
		}
		for(Road r : roads) {
			graph.addEdge(r.getSource(), r.getDestination(), r.getWeight(), r.getName());
		}
		scn.close();
	}
}
