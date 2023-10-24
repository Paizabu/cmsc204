//author: Paizabu Min
public class CourseDBElement implements Comparable<CourseDBElement>{
	
	private String ID;
	private int CRN;
	private int credits;
	private String room;
	private String name;
	private String msg;
	
	/**
	 * @param iD
	 * @param cRN
	 * @param credits
	 * @param room
	 */
	public CourseDBElement(String ID, int CRN, int credits, String room, String name) {
		this.ID = ID;
		this.CRN = CRN;
		this.credits = credits;
		this.room = room;
		this.name = name;
	}
	
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public CourseDBElement() {};
	
	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @return the cRN
	 */
	public int getCRN() {
		return CRN;
	}

	/**
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * @return the room
	 */
	public String getRoomNum() {
		return room;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(String ID) {
		this.ID = ID;
	}

	/**
	 * @param cRN the cRN to set
	 */
	public void setCRN(int CRN) {
		this.CRN = CRN;
	}

	/**
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}

	/**
	 * @param room the room to set
	 */
	public void setRoom(String room) {
		this.room = room;
	}
	
	@Override
	public int compareTo(CourseDBElement element) {
		if (this.CRN == element.CRN && this.ID == element.ID
				&& this.credits == element.credits && this.name.equals(element.name)
				&& this.room.equals(element.room)) {
			return 0;
		}
		else {
			return -1;
		}
		
	}
	
	@Override
	public String toString() {
		return "\nCourse:"+ ID + " CRN:" + CRN + " Credits:" + credits 
				+ " Instructor:" + name + " Room:" + room;
	}

}
