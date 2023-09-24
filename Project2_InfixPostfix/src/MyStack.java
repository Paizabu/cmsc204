//author: Paizabu Min
import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T>{
	
	private Node<T> topNode;
	private int capacity;
	
	/**
	 * Provide two constructors
	 * 1. takes in an int as the size of the stack
	 * 2. default constructor - uses default as the size of the stack
	 */
	public MyStack(){
		this(10);
	}
	
	public MyStack(int capacity) {
		topNode = null;
		this.capacity = capacity;
	}

	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	public boolean isEmpty() {
		return topNode == null;
	}

	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull() {
		return size() == capacity;
	}

	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T pop() throws StackUnderflowException{
		T stackTop = top();
		if(!isEmpty()) {
			topNode = topNode.next;
		} else throw new StackUnderflowException();
		return stackTop;
	}
	
	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T top() throws StackUnderflowException{
		if(isEmpty()) {
			throw new StackUnderflowException();
		} else {
			return topNode.data;
		}
	}

	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	public int size() {
		Node<T> tempNode = topNode;
		int counter = 0;
		
		while(tempNode != null) {
			tempNode = tempNode.next;
			counter++;
		}
		
		return counter;
	}
	
	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */
	public boolean push(T e) throws StackOverflowException{
		if(!isFull()) {
			Node<T> newNode = new Node<T>(e, topNode);
			topNode = newNode;
			return true;
		} else throw new StackOverflowException();
	}
	
	
	/**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString() {
		String result = "";
		Node<T> tempNode = topNode;
		
		while(tempNode != null) {
			result = tempNode.data + result;
		
			tempNode = tempNode.next;
		}
		return result;
	}
	
	/**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	public String toString(String delimiter) {
		String result = "";
		Node<T> tempNode = topNode;
		
		while(tempNode != null) {
			
			if(tempNode == topNode) {
				result += tempNode.data;
			} else {
				result = tempNode.data + delimiter + result;
			}
			tempNode = tempNode.next;
		}
		return result;
	}
	
	 /**
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
	  * list reference within your Stack, you will be allowing direct access to the data of
	  * your Stack causing a possible security breech.
	  * @param list elements to be added to the Stack from bottom to top
	  * @throws StackOverflowException if stack gets full
	  */
	public void fill(ArrayList<T> list) {
		try {
			ArrayList<T> tempList = new ArrayList<T>(list);
			
			for(int i = 0; i < tempList.size(); i++) {
				push(tempList.get(i));
			}
			
		} catch(StackOverflowException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//author: Paizabu Min
	public class Node<T> {
		T data; // Entry in bag
		Node<T> next; // Link to next node
		
		public Node(T dataPortion) {
			this(dataPortion, null);	
		} 

		public Node(T dataPortion, Node<T> nextNode) {
			data = dataPortion;
			next = nextNode;
		}

	}

 
}
