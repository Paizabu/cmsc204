import java.io.*;
import java.util.*;

//author: Paizabu Min
public class CourseDBStructure implements CourseDBStructureInterface{
	
	private LinkedList<CourseDBElement>[] bucketArray;
	private int tableSize;
	private final double LOADING_FACTOR = 1.5;
	
	@SuppressWarnings("unchecked")
	public CourseDBStructure(int size) {
		int tempSize = (int)Math.round(size/LOADING_FACTOR);
		while(!is4KPrime(tempSize)) {
			tempSize++;
		}
		tableSize = tempSize;
		bucketArray = new LinkedList[tableSize];
	}
	
	@SuppressWarnings("unchecked")
	public CourseDBStructure(String s, int size) {
		tableSize = size;
		bucketArray = new LinkedList[tableSize];
	}
	
	/** 
	* Adds a CourseDBElement object to the CourseDBStructure using the hashcode
	* of the CourseDatabaseElement object's CRN value.
	* If the CourseDatabaseElement already exists, exit quietly
	*  
	* @param element the CourseDBElement to be added to CourseDBStructure
	*/
	public void add(CourseDBElement element) {
		checkCapacity();
		int index = findIndex(element);
		boolean cont = false;
		if(isValidCredit(element.getCredits()) && isValidCRN(element.getCRN()) && isValidRoom(element.getRoomNum())) {
			cont = true;
		}
		if(cont) {
			if(bucketArray[index] == null) {
				LinkedList<CourseDBElement> bList = new LinkedList<>();
				bList.add(element);
				bucketArray[index] = bList;
				tableSize++;
			}
			else if(hasDuplicate(bucketArray, element)) {
				LinkedList<CourseDBElement> list = bucketArray[index];
				for(CourseDBElement e : list) {
					if(e.compareTo(element) != 0 && e.getCRN() == element.getCRN()) {
						bucketArray[index].remove(e);
						bucketArray[index].add(element);
						System.out.println("Duplicate found: Couse Information has been updated");
					}
				}
			}
			else {
				bucketArray[index].add(element);
				tableSize++;
			} 
		}
	}
	
	/**
	 * Find a courseDatabaseElement based on the key (crn) of the
	 * courseDatabaseElement If the CourseDatabaseElement is found return it If not,
	 * throw an IOException
	 * 
	 * @param crn crn (key) whose associated courseDatabaseElement is to be returned
	 * @return a CourseDBElement whose crn is mapped to the key
	 * @throws IOException if key is not found
	 */

	public CourseDBElement get(int crn) throws IOException{
		boolean check = false;
		int index = crn % bucketArray.length;
		CourseDBElement element = new CourseDBElement();
		LinkedList<CourseDBElement> list = bucketArray[index];
		if(list != null) {
			for(int i = 0; i < list.size(); i++) {
				int val = list.get(i).getCRN() ;
				if(val == crn) {
					check = true;
					element = list.get(i);
				}
				
			}
		}
		if(check) {
			return element;
		} else throw new IOException();
	}

	/**
	 * @return an array list of string representation of each course in 
	 * the data structure separated by a new line. 
	 * Refer to the following example:
	 * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	 * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	 */
	
	public ArrayList<String> showAll(){
		ArrayList<String> result = new ArrayList<>();
		for(LinkedList<CourseDBElement> list: bucketArray) {
			if(list != null) {
				for(int i = 0; i < list.size(); i++) {
					result.add(list.get(i).toString());
				}
			}
		}
		return result;
	}
	/**
	* Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	*/
	public int getTableSize() {
		return tableSize;
	}
	
	public boolean is4KPrime(double num) {
		
		boolean flag = true;
		for (int i = 2; i <= num / 2; ++i) {
			// condition for nonprime number
		    if(num % i == 0) {
		        flag = false;
		        break;
		    }   
		} 
		if(((((num-3.0)/4.0)*10.0) % 10) != 0) {
			flag = false;
		}
		return flag;
	}
	
	public int findIndex(CourseDBElement ele) {
		return ele.getCRN() % bucketArray.length;
	}

	@SuppressWarnings("unchecked")
	public void checkCapacity() {
		double ratio = tableSize/bucketArray.length;
		if(ratio >= LOADING_FACTOR) {
			int tempSize = tableSize+1;
			while(!is4KPrime(tempSize)) {
				tempSize++;
			}
			
			ArrayList<CourseDBElement> eleArray = new ArrayList<>();
			
			tableSize = tempSize;
			for(LinkedList<CourseDBElement> e : bucketArray) {
				if(e != null) {
					for(CourseDBElement d : e) {
						if(d != null) {
							eleArray.add(d);
						}
					}
				}
			}
			
			bucketArray = new LinkedList[tempSize];
			for(CourseDBElement e : eleArray) {
				add(e);
			}
			
		}
	}
	
	public boolean hasDuplicate(LinkedList<CourseDBElement>[] array, CourseDBElement element) {
		boolean check = false;
		for(LinkedList<CourseDBElement> list: bucketArray) {
			if(list != null) {
				for(int i = 0; i < list.size(); i++) {
					if(list.get(i).getCRN() == element.getCRN()) {
						check = true;
					}
				}
			}
		}
		
		return check;
	}

	public boolean isValidCRN(int CRN) {
		return (CRN >= 10000 && CRN <= 99999);
	}
	
	public boolean isValidCredit(int cred) {
		return (cred <= 4 && cred >= 1);
	}
	
	public boolean isValidRoom(String room) {
		if(room.equals("Distance") || room.equals("Distance-Learning")) {
			return true;
		}
		for(int i = 0; i < room.length(); i++) {
			if(Character.isDigit(room.charAt(i))){
				return true;
			}
		}
		return false;
	}
}
