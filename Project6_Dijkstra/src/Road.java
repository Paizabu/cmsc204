import java.util.Objects;

//author: Paizabu Min
public class Road implements Comparable<Road>{

	private Town source;
	private Town destination;
	private int weight = Integer.MAX_VALUE;
	private String name;
	
	/**
	 * @param source
	 * @param destination
	 * @param weight
	 * @param name
	 */
	public Road(Town source, Town destination, int weight, String name) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		this.name = name;
	}
	
	/**
	 * @param source
	 * @param destination
	 * @param distance
	 * @param name
	 */
	public Road(Town source, Town destination, String name) {
		this.source = source;
		this.destination = destination;
		this.weight = 1;
		this.name = name;
	}

	/**
	 * @return the source
	 */
	public Town getSource() {
		return source;
	}

	/**
	 * @return the destination
	 */
	public Town getDestination() {
		return destination;
	}

	/**
	 * @return the distance
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Returns true only if the edge contains the given town
	 * @param town
	 * @return true only if the edge is connected to the given vertex
	 */
	public boolean contains(Town town) {
		return (this.source.equals(town) || this.destination.equals(town));
	}

	@Override
	public int compareTo(Road o) {
		if(this.getName() == o.getName())
			return 0;
		else return -1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Road other = (Road) obj;
		return (this.contains(other.destination) && this.contains(other.source));
	}

	@Override
	public String toString() {
		return source + " via " + name  + " to "+ destination + " " + weight + " mi";
	}

}
