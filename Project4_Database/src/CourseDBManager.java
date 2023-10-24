import java.io.*;
import java.util.*;

//author: Paizabu Min
public class CourseDBManager implements CourseDBManagerInterface{
	private CourseDBStructure courseList;
	private final int DEFAULT_CAPACITY = 20;
	
	public CourseDBManager() {
		courseList = new CourseDBStructure(DEFAULT_CAPACITY);
	}
	/**
	 * Adds a course (CourseDBElement) with the given information
	 * to CourseDBStructure.
	 * @param id course id 
	 * @param crn course crn
	 * @param credits number of credits
	 * @param roomNum course room number
	 * @param instructor name of the instructor
	 */
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
		if(isValidCredit(credits) && isValidCRN(crn) && isValidRoom(roomNum)) {
			courseList.add(element);
		}
		else {
			System.out.println("Invalid Course Format: " + element.toString());
		}
	}
	
	/**
	 * finds  CourseDBElement based on the crn key
	 * @param crn course crn (key)
	 * @return a CourseDBElement object
	 * 
	 */
	public CourseDBElement get(int crn) {
		try {
			return courseList.get(crn);
		} catch(IOException e) {
			System.out.println("Course does not exist");
			return null;
		}
	}
	
	/**
	 * Reads the information of courses from a test file and adds them
	 * to the CourseDBStructure data structure
	 * @param input input file 
	 * @throws FileNotFoundException if file does not exists
	 */
	public void readFile(File input) throws FileNotFoundException{
		Scanner scan = new Scanner(input);
		ArrayList<String[]> list = new ArrayList<>();
		while(scan.hasNext()) {
			String line = scan.nextLine();
			if(line.contains("#")) {
				line = line.substring(0, line.indexOf("#"));
			}
			if(line != null && !line.isEmpty()){
				String[] element = line.split(" ");
				if(element != null) {
					list.add(element);
				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			boolean check = true;
			String ID = "";
			int CRN = 0;
			int cred = 0;
			String room = "";
			String name = "";
			if(list.get(i).length > 5) {
				ID = list.get(i)[0];
				room = list.get(i)[3];
				try {
					CRN = Integer.parseInt(list.get(i)[1]);
					cred = Integer.parseInt(list.get(i)[2]);
					
					if(!isValidCredit(cred) || !isValidCRN(CRN) || !isValidRoom(room)) {
						check = false;
						continue;
					}
					for(int j = 4; j < list.get(i).length; j++) {
						if(j == list.get(i).length-1) {
							name += list.get(i)[j];
						} else {
							name += list.get(i)[j] + " ";
						}
					}
					
				} catch(NumberFormatException n){
					check = false;
					System.out.print("\nWrong number format: ");
					for(int k = 0; k < list.get(i).length; k++) {
						System.out.print(list.get(i)[k] + " ");
					}
					continue;
				}
			}
			if(check && CRN != 0) {
				add(ID, CRN, cred, room, name);
			} else {
				System.out.print("\nInvalid format: ");
				for(int k = 0; k < list.get(i).length; k++) {
					System.out.print(list.get(i)[k] + " ");
				}
			}
		}
//		if(list.size() > 5)
//		for(String[] s : list) {
//			String ID = s[0];
//			int CRN = Integer.parseInt(s[1]);
//			int credits = Integer.parseInt(s[2]);
//			String room = s[3];
//			
//		}
		
		
	}
	/**
	 * @return an array list of string representation of each course in 
	 * the data structure separated by a new line. 
	 * Refer to the following example:
	 * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	 * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	 */
	public ArrayList<String> showAll(){
		return courseList.showAll();
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
