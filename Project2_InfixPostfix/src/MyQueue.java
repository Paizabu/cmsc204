//author: Paizabu Min
import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T>{
	private T[] queue;
	private int frontIndex;
	private int backIndex;
	private int numOfElements;
	private static final int DEFAULT_INITIAL_CAPACITY = 50;
	
	/** provide two constructors 
	 * 1. takes an int as the size of the queue
	 * 2. default constructor - uses a default as the size of the queue
	 * 
	 */
	public MyQueue() {
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	public MyQueue(int size) {
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[size + 1];
		queue = tempQueue;
		backIndex = size;
		frontIndex = 0;
	}
	
	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	public boolean isEmpty() {
		return frontIndex == ((backIndex + 1) % queue.length);
	}
	
	/**
	 * Determines of the Queue is Full
	 * @return true if Queue is full, false if not
	 */
	public boolean isFull() {
		return frontIndex == ((backIndex + 2) % queue.length);
	}
	
	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException if queue is empty
	 */
	public T dequeue() throws QueueUnderflowException{
		T front = null;
		if(!isEmpty()) {
			front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1) % queue.length;
			numOfElements--;
		} else
			throw new QueueUnderflowException();
		
		return front;
	}
	
	/**
	 * Returns number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	public int size() {
		return numOfElements;
	}
	
	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
	public boolean enqueue(T e) throws QueueOverflowException{
		if(!isFull()) {
			backIndex = (backIndex + 1) % queue.length;
			queue[backIndex] = e;
			numOfElements++;
			return true;
		}
		else throw new QueueOverflowException();
	}
	
	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	public String toString() {
		String result = "";
		for(int i = frontIndex; i != ((backIndex+1) % queue.length); i = ((i+1) % queue.length)) {
			result += queue[i];
		}
		return result;
	}
	
	/**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	public String toString(String delimiter) {
		String result = "";
		for(int i = frontIndex; i != ((backIndex+1) % queue.length); i = ((i+1) % queue.length)) {
			if(i == frontIndex) {
				result += queue[i];
			}else
				result += delimiter + queue[i];
		}
		return result;
	}
	
	/**
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	  * is the first element in the Queue
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
	  * list reference within your Queue, you will be allowing direct access to the data of
	  * your Queue causing a possible security breech.
	  * @param list elements to be added to the Queue
	  * @throws QueueOverflowException if queue is full
	 
	  */
	public void fill(ArrayList<T> list) {
		ArrayList<T> tempList = new ArrayList<T>(list);
		try {
			for(int i = 0; i < tempList.size(); i++) {
				enqueue(tempList.get(i));
			}
		} catch(QueueOverflowException e) {
			System.out.println(e.getMessage());
		}
	}
}
