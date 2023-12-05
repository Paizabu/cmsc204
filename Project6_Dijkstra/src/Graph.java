import java.util.*;

//author: Paizabu Min
public class Graph implements GraphInterface<Town, Road> {
	private int verticesCount;
	private Map<Town, LinkedList<Road>> adjacencyList;
	private Set<Road> roadSet;
	private Map<Town, Integer> dijkstraQuickPath;
	private Map<Town, Town> dijkstraMap;
	
	public Graph() {
		dijkstraQuickPath = new HashMap<>();
		adjacencyList = new HashMap<>();
		roadSet = new HashSet<>();
		dijkstraMap = new HashMap<>();
		verticesCount = 0;				
	}
    /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
    	if(sourceVertex == null || destinationVertex == null) 
    		return null;
    	if(containsEdge(sourceVertex, destinationVertex)) {
    		for(int i = 0; i<adjacencyList.size(); i++) {
    			if(adjacencyList.get(sourceVertex).get(i).getDestination().equals(destinationVertex)){
    				return adjacencyList.get(sourceVertex).get(i);
    			}
    		}
    	}
    	return null;
    }


    /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
    	if(sourceVertex == null || destinationVertex == null) 
    		throw new NullPointerException("Specified vertices is null");
    	if(!containsVertex(sourceVertex) || !containsVertex(destinationVertex)) 
    		throw new IllegalArgumentException("Target vertices are not found in the graph");
    	
    	Road roadA = new Road(sourceVertex, destinationVertex, weight, description);
    	Road roadB = new Road(destinationVertex, sourceVertex, weight, description);
    	
    	if(!containsEdge(sourceVertex, destinationVertex)) {
    		adjacencyList.get(sourceVertex).add(roadA);
    		roadSet.add(roadA);
    		adjacencyList.get(destinationVertex).add(roadB);
    	}
    	return roadA;
    }

    /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
    public boolean addVertex(Town town) {
    	if(town == null)
    		throw new NullPointerException();
    	
    	if(!containsVertex(town)) {
    		adjacencyList.put(town, new LinkedList<Road>());
    		dijkstraQuickPath.put(town, Integer.MAX_VALUE);
    		verticesCount++;
    		return true;
    	}
    	else return false;
    }

    /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
    	boolean found = false;
    	if(adjacencyList.containsKey(sourceVertex) && !adjacencyList.get(sourceVertex).isEmpty()) {
    		for(int i = 0; i < adjacencyList.get(sourceVertex).size(); i++) {
    			if(adjacencyList.get(sourceVertex).get(i).getDestination().equals(destinationVertex)) {
    				found = true;
    			}
    		}
    	}
//    	else if(adjacencyList.containsKey(destinationVertex) && !adjacencyList.get(destinationVertex).isEmpty()) {
//		for(int i = 0; i < adjacencyList.get(destinationVertex).size(); i++) {
//    			if(adjacencyList.get(destinationVertex).get(i).getDestination().equals(sourceVertex)) {
//    				found = true;
//    			}
//    		}
//    	}
    	return found;
    }

    /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
    public boolean containsVertex(Town town) {
    	if(adjacencyList.containsKey(town)) {
    		return true;
    	}
    	else return false;
    }

    /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
    public Set<Road> edgeSet(){
    	
    	return roadSet;
    }

    /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
    public Set<Road> edgesOf(Town vertex){
    	Set<Road> result = new HashSet<>();
    	if (vertex == null){
    			throw new NullPointerException("vertex is null");
    	}
    	if (!adjacencyList.containsKey(vertex)){
    		throw new IllegalArgumentException("vertex is not found in the graph");
    	}
    	
    	for(int i = 0; i < adjacencyList.get(vertex).size(); i++) {
    		result.add(adjacencyList.get(vertex).get(i));
    	}
    	
    	return result;
    }


    /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
    	Road road = null;
    	if(weight <= -1 || description ==null || !containsEdge(sourceVertex, destinationVertex)) {
    		road = null;
    	}   	
    	else {	
    		while(containsEdge(sourceVertex, destinationVertex)) {	
    			for(int i = 0; i < adjacencyList.get(destinationVertex).size(); i++) {
    				if(adjacencyList.get(destinationVertex).get(i).getDestination().equals(sourceVertex)) {
	    				road = adjacencyList.get(destinationVertex).get(i);
	    				adjacencyList.get(destinationVertex).remove(road);
	    			}
    			}
    			for(int i = 0; i < adjacencyList.get(sourceVertex).size(); i++) {
    				if(adjacencyList.get(sourceVertex).get(i).getDestination().equals(destinationVertex)) {
	    				road = adjacencyList.get(sourceVertex).get(i);
	    				adjacencyList.get(sourceVertex).remove(road);
	    				roadSet.remove(road);
	    			}
    			}
    		}
    	}
    	return road;
    }


    /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
    public boolean removeVertex(Town town) {
    	boolean found = false;
    	if(containsVertex(town)) {
    		adjacencyList.remove(town);
    		dijkstraQuickPath.remove(town);
    		verticesCount--;
    		found = true;
    	}	
    	return found;
    }

    /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
    public Set<Town> vertexSet(){
    	Set<Town> set = adjacencyList.keySet();
    	return set;
    }

    
    /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraQuickPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */   
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex){
    	ArrayList<String> path = new ArrayList<>();
    	dijkstraShortestPath(sourceVertex);
    	while(dijkstraMap.get(destinationVertex) != null) {
    		Road road = null;
    		for(Road r : edgesOf(destinationVertex)) {
    			if (r.getDestination().equals(dijkstraMap.get(destinationVertex))) {
    				road = r;
    			}
    		}
    		path.add(0, dijkstraMap.get(destinationVertex).getName() + " via " + road.getName()
    					+ " to " + destinationVertex.getName() + 
    					" " + road.getWeight() + " mi");
    		destinationVertex = dijkstraMap.get(destinationVertex);
    	}
    	
    	return path;
    }
    
    /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
    public void dijkstraShortestPath(Town sourceVertex) {
    	dijkstraQuickPath.put(sourceVertex, 0);
    	dijkstraMap.put(sourceVertex, null);
    	
    	Set<Town> settledVertex = new HashSet<>();
    	Set<Town> unsettledVertex = new HashSet<>(vertexSet());
    	
    	while(!unsettledVertex.isEmpty()) {
	    	unsettledVertex.remove(sourceVertex);
	    	settledVertex.add(sourceVertex);
	    	//checks if all vertex has been visited
	    	if(!unsettledVertex.isEmpty()) {	
	    		//adds to a new road set of all adjacent edges that aren't visited
		    	Set<Road> adjUnvisitedRoads = new HashSet<>();		    	
		    	for(Road r : edgesOf(sourceVertex)) {
		    		if(!settledVertex.contains(r.getDestination())) {
		    			adjUnvisitedRoads.add(r);
		    		}
		    	}
		    	int lowestDistance = Integer.MAX_VALUE;	
		    	//checks if the current node is a dead end
		    	if(!adjUnvisitedRoads.isEmpty()) {	
		    		Town currentTown = sourceVertex;
		    		for(Road road : adjUnvisitedRoads) {		    			
			    		//get new calculated distance of each new edge from source
			    		int runningWeight = road.getWeight() + dijkstraQuickPath.get(road.getSource());
			    		//if current road weight is less that dijstra's respective path weight
			    		//from source to road destination, replace with lesser weight
			    		if(runningWeight < dijkstraQuickPath.get(road.getDestination())) {
			    			dijkstraQuickPath.put(road.getDestination(), runningWeight);
			    			dijkstraMap.put(road.getDestination(), currentTown);
			    		}
			    		//find the lowest weight of all unsettled roads
			    		for(Town t : unsettledVertex) {
				    		if(dijkstraQuickPath.get(t) < lowestDistance) {
				    			lowestDistance = dijkstraQuickPath.get(t);
				    			sourceVertex = t;
				    		}
			    		}	    		
			    	}
		    	} else {
		    		for(Town t : unsettledVertex) {
			    		if(dijkstraQuickPath.get(t) < lowestDistance) {
			    			lowestDistance = dijkstraQuickPath.get(t);
			    			sourceVertex = t;
			    		}
		    		}	  
		    	}
	    	}
    	}    	
    }
}
