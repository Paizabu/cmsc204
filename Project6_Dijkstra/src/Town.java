//author: Paizabu Min
import java.util.*;

public class Town implements Comparable<Town>{
	
	private String name;
	private List<Town> adjacentTowns = new LinkedList<>();
	
	/**
	 * @param name
	 */
	public Town(String name) {
		this.name = name;
	}
	
	public Town(Town templateTown) {
		this.name = templateTown.getName();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	@Override
	public int compareTo(Town o) {
		if(this.getName().equals(o.getName()))
			return 0;
		else return -1;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(adjacentTowns, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Town other = (Town) obj;
		return this.name.equals(other.name);
	}

	@Override
	public String toString() {
		return name;
	}

}
