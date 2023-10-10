import java.util.Comparator;
import java.util.ListIterator;


//author: Paizabu Min
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> implements Iterable<T>{
	
	private Comparator<T> cp;
	
	public SortedDoubleLinkedList(Comparator<T> comparableObject){
		head = null;
		tail = null;
		numberOfEntries = 0;
		cp = comparableObject;
		
	}
	
	/**
	 * Inserts the specified element at the correct position in the sorted list. 
	 * Notice we can insert the same element several times. 
	 * Your implementation must traverse the list only once in order to perform the insertion.
	 * Do not implement this method using iterators. 
	 * Notice that you don't need to call any of the super class methods in order to implement this method.
	 * @param data the data to be added to the list
	 */
	public void add​(T data) {
		Node<T> newNode = new Node<T>(data);
		Node<T> currentNode = head;
		Node<T> nodeBefore = null;
		
		while(currentNode != null && cp.compare(data, currentNode.data) >= 0) {
			nodeBefore = currentNode;
			currentNode = currentNode.next;
		}
		
		if(numberOfEntries == 0 || nodeBefore == null) {
			newNode.next = head;
			if(head != null) {
				head.prev = newNode;
			}
			head = newNode;
			numberOfEntries++;
		}
		else {
			Node<T> nodeAfter = nodeBefore.next;
			newNode.next = nodeAfter;
			if(nodeAfter != null) {
				nodeAfter.prev = newNode;
			}
			newNode.prev = nodeBefore;
			nodeBefore.next = newNode;
			numberOfEntries++;
		}
		if(newNode.next == null) {
			tail = newNode;
		}
	}
	
	/**
	 * This operation is invalid for a sorted list. 
	 * An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
	 * @param data the data for the Node within the linked list
	 */
	public void addToEnd​(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * This operation is invalid for a sorted list. 
	 * An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
	 */
	public void addToFront​(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * Implements the iterator by calling the super class iterator method.
	 * @return ListIterator<T> an iterator positioned at the head of the list
	 */
	public ListIterator<T> iterator(){
		return super.iterator();
	}
	
	/**
	 * Implements the remove operation by calling the super class remove method.
	 * @param data - the data element to be removed
	 * @param comparator - the comparator to determine equality of data elements
	 * @return node containing the data or null
	 */
	public Node<T> remove​(T data, Comparator<T> comparator) {
		return super.remove​(data, comparator);
	}
}
