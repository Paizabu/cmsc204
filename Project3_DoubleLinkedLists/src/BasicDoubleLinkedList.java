import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


//author: Paizabu Min
public class BasicDoubleLinkedList<T> implements Iterable<T>{
	
	protected Node<T> head;
	protected Node<T> tail;
	protected int numberOfEntries;
	
	/**
	 * Constructor to set to initialize head, tail and size to null, null and 0
	 */
	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
		numberOfEntries = 0;
	}
	
	/**
	 * Returns the number of nodes in the list.
	 * @return the size of the linked list
	 */
	public int getSize() {
		return numberOfEntries;
	}
	
	/**
	 * Adds an element to the end of the list and updated the size of the list
	 * @param data the data to be added to the list
	 */
	public void addToEnd​(T data) {
		Node<T> newNode = new Node<T>(data);
		
		if(head == null) {
			head = newNode;
			tail = newNode;
			head.prev = null;
			head.next = null;
		}
		
		else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			tail.next = null;
		}
		numberOfEntries++;
	}
	
	/**
	 * Adds element to the front of the list and updated the size of the list
	 * @param data the data to be added to the list
	 */
	public void addToFront​(T data) {
		Node<T> newNode = new Node<T>(data);
		
		if(head == null) {
			head = newNode;
			tail = newNode;
			head.prev = null;
			head.next = null;
		}
		
		else {
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
			head.prev = null;
		}
		numberOfEntries++;
	}
	
	/**
	 * Returns but does not remove the first element from the list.
	 * If there are no elements the method returns null.
	 * @return the data element or null
	 */
	public T getFirst() {
		if (head != null) {
			return head.data;
		} else return null;
	}
	
	/**
	 * Returns but does not remove the last element from the list.
	 * If there are no elements the method returns null.
	 * @return the data element or null
	 */
	public T getLast() {
		if (tail != null) {
			return tail.data;
		} else return null;
	}
	
	/**
	 * Removes and returns the first element from the list. 
	 * If there are no elements the method returns null.
	 * @return data element or null
	 */
	public T retrieveFirstElement() {
		T data = null;
		
		if(numberOfEntries > 0) {
			if(numberOfEntries == 1) {
				head = null;
				tail = null;
			}
			else {
				data = head.data;
				head = head.next;
				head.prev = null;
			}
			numberOfEntries--;
		}
		return data;
	}
	
	/**
	 * Removes and returns the last element from the list. 
	 * If there are no elements the method returns null. 
	 * @return data element or null
	 */
	public T retrieveLastElement() {
		T data = null;
		
		
		if(numberOfEntries > 0) {
			if(numberOfEntries == 1) {
				head = null;
				tail = null;
			}
			else {
				data = tail.data;
				tail = tail.prev;
				tail.next = null;
			}
			numberOfEntries--;
		}
		return data;
	}
	
	/**
	 * This method returns an object of the DoubleLinkedListIterator. 
	 * @return a ListIterator object
	 */
	public ListIterator<T> iterator(){
		return new DoubleLinkedListIterator();
	}
	
	/**
	 * Removes the first instance of the targetData from the list. 
	 * Must remove the elements by performing a single traversal over the list.
	 * <ust use the provided comparator (do not use equals) to find those elements that match the target. 
	 * @param targetData
	 * @param comparator
	 * @return a node containing the targetData or null
	 */
	public Node<T> remove​(T targetData, Comparator<T> comparator){
		
		Node<T> currentNode = head;
		for(int i = 0; i < numberOfEntries; i++) {
			if(comparator.compare(targetData, currentNode.data) == 0) {
				if(currentNode == head) {
					retrieveFirstElement();
				} else if(currentNode == tail) {
					retrieveLastElement();
				}
				else {
					Node<T> nodeAfter = currentNode.next;
					Node<T> nodeBefore = currentNode.prev;
					nodeBefore.next = nodeAfter;
					if(nodeAfter != null) {
						nodeAfter.prev = nodeBefore;
					}
					numberOfEntries--;
				}
				return currentNode;
			}
			else {
				currentNode = currentNode.next;
			}
		}
		return currentNode;
		
	}
	
	/**
	 * Returns an arraylist of all the items in the Double Linked list
	 * @return an arraylist of the items in the list
	 */
	public ArrayList<T> toArrayList(){
		ListIterator<T> itr = iterator();
		ArrayList<T> listArray = new ArrayList<>();
		while(itr.hasNext()) {
			T element = itr.next();
			listArray.add(element);
		}
		return listArray;
	}
	
	/**
	 * A generic inner class named DoubleLinkedListIterator that implements java’s ListIterator interface
	 * This inner class implements only the hasNext(), next(), hasPrevious() and previous()
	 * @author Paizabu Min
	 * @param <T>
	 */
	public class DoubleLinkedListIterator<T> implements ListIterator<T>{
		
		public Node nextNode;
		public Node prevNode;
		
		/**
		 * Constructor to initialize the current pointer to the head of the BasicDoubleLinkedList.
		 */
		private DoubleLinkedListIterator(){
			nextNode = head;
			prevNode = null;
		}
		
		public boolean hasNext() {
			return nextNode != null;
		}

		public T next() {
			if(hasNext()) {
				Node<T> returnNode = nextNode;
				prevNode = nextNode;
				nextNode = nextNode.next;
				return returnNode.data;
			}
			else throw new NoSuchElementException("Illegal call to next");
		}

		public boolean hasPrevious() {
			return prevNode != null;
		}

		public T previous(){
			if(hasPrevious()) {
				Node<T> returnNode = prevNode;
				nextNode = prevNode;
				prevNode = prevNode.prev;
				return returnNode.data;
			}
			else throw new NoSuchElementException("Illegal call to previous");
		}

		public int nextIndex() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		public int previousIndex() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		public void remove() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		public void set(T e) throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		public void add(T e) throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * A generic inner class Node
	 * @author Paizabu Min
	 * @param <T>
	 */
	public class Node<T> {
		T data; // Entry in bag
		Node<T> next; // Link to next node
		Node<T> prev; // Link to previous node
		
		public Node(T dataPortion) {
			this(null, dataPortion, null);	
		} 

		public Node(Node<T> prevNode, T dataPortion, Node<T> nextNode) {
			prev = prevNode;
			data = dataPortion;
			next = nextNode;
		}

	}
}
