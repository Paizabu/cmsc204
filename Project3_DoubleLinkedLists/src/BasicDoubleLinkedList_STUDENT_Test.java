import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//author: Paizabu Min
public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<String> linkedString;
	BasicDoubleLinkedList<Double> linkedDouble;
	BasicDoubleLinkedList<Food> linkedFood;
	StringComparator comparatorS;
	DoubleComparator comparatorD;
	FoodComparator comparatorFood;
	
	public Food a=new Food("Fries", "Large", 300);
	public Food b=new Food("Jello", "Small", 500);
	public Food c=new Food("Horchata", "Small", 600);
	public Food d=new Food("Soup", "Medium", 300);
	public Food e=new Food("Celery", "Medium", 50);

	public ArrayList<Food> fill = new ArrayList<Food>();
	

	@Before
	public void setUp() throws Exception {
		linkedString = new BasicDoubleLinkedList<String>();
		linkedString.addToEnd​("Hole");
		linkedString.addToEnd​("Wall");
		comparatorS = new StringComparator();
		
		linkedDouble = new BasicDoubleLinkedList<Double>();
		linkedDouble.addToEnd​(20.0);
		linkedDouble.addToEnd​(115.0);
		comparatorD = new DoubleComparator();
		
		linkedFood= new BasicDoubleLinkedList<Food>();
		linkedFood.addToEnd​(b);
		linkedFood.addToEnd​(c);
		comparatorFood = new FoodComparator();
	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
		linkedDouble = null;
		linkedFood = null;
		comparatorD = null;
		comparatorS = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(2,linkedString.getSize());
		assertEquals(2,linkedDouble.getSize());
		assertEquals(2,linkedFood.getSize());
	}
	
	@Test
	public void testAddToEnd() {
		assertEquals("Wall", linkedString.getLast());
		linkedString.addToEnd​("Emote");
		assertEquals("Emote", linkedString.getLast());
		
		assertEquals(c,linkedFood.getLast());
		linkedFood.addToEnd​(d);
		assertEquals(d,linkedFood.getLast());
	}
	
	@Test
	public void testAddToFront() {
		assertEquals("Hole", linkedString.getFirst());
		linkedString.addToFront​("Begin");
		assertEquals("Begin", linkedString.getFirst());
		
		assertEquals(b,linkedFood.getFirst());
		linkedFood.addToFront​(a);
		assertEquals(a,linkedFood.getFirst());
	}
	
	@Test
	public void testGetFirst() {
		assertEquals("Hole", linkedString.getFirst());
		linkedString.addToFront​("Nice");
		assertEquals("Nice", linkedString.getFirst());
		
		assertEquals(b,linkedFood.getFirst());
		linkedFood.addToFront​(a);
		assertEquals(a,linkedFood.getFirst());
	}

	@Test
	public void testGetLast() {
		assertEquals("Wall", linkedString.getLast());
		linkedString.addToEnd​("Nice");
		assertEquals("Nice", linkedString.getLast());
		
		assertEquals(c,linkedFood.getLast());
		linkedFood.addToEnd​(d);
		assertEquals(d,linkedFood.getLast());
	}
	
	@Test
	public void testToArrayList()
	{
		ArrayList<Food> list;
		linkedFood.addToFront​(a);
		linkedFood.addToEnd​(d);
		list = linkedFood.toArrayList();
		assertEquals(a,list.get(0));
		assertEquals(b,list.get(1));
		assertEquals(c,list.get(2));
		assertEquals(d,list.get(3));
	}
	
	@Test
	public void testIteratorSuccessfulNext() {
		linkedString.addToFront​("Begin");
		linkedString.addToEnd​("Emote");
		ListIterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Hole", iterator.next());
		assertEquals("Wall", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("Emote", iterator.next());
		
		linkedFood.addToFront​(a);
		linkedFood.addToEnd​(d);
		ListIterator<Food> iteratorFood = linkedFood.iterator();
		assertEquals(true, iteratorFood.hasNext());
		assertEquals(a, iteratorFood.next());
		assertEquals(b, iteratorFood.next());
		assertEquals(c, iteratorFood.next());
		assertEquals(true, iteratorFood.hasNext());
		assertEquals(d, iteratorFood.next());
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedFood.addToFront​(a);
		linkedFood.addToEnd​(d);
		ListIterator<Food> iteratorFood = linkedFood.iterator();
		assertEquals(true, iteratorFood.hasNext());
		assertEquals(a, iteratorFood.next());
		assertEquals(b, iteratorFood.next());
		assertEquals(c, iteratorFood.next());
		assertEquals(d, iteratorFood.next());
		assertEquals(true, iteratorFood.hasPrevious());
		assertEquals(d, iteratorFood.previous());
		assertEquals(c, iteratorFood.previous());
		assertEquals(b, iteratorFood.previous());
		assertEquals(a, iteratorFood.previous());
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedFood.addToFront​(a);
		linkedFood.addToEnd​(d);
		ListIterator<Food> iteratorFood = linkedFood.iterator();		
		assertEquals(true, iteratorFood.hasNext());
		assertEquals(a, iteratorFood.next());
		assertEquals(b, iteratorFood.next());
		assertEquals(c, iteratorFood.next());
		assertEquals(true, iteratorFood.hasNext());
		assertEquals(d, iteratorFood.next());
		
		try{
			//no more elements in list
			iteratorFood.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedFood.addToFront​(a);
		linkedFood.addToEnd​(d);
		ListIterator<Food> iteratorFood = linkedFood.iterator();		
		assertEquals(true, iteratorFood.hasNext());
		assertEquals(a, iteratorFood.next());
		assertEquals(b, iteratorFood.next());
		assertEquals(c, iteratorFood.next());
		assertEquals(d, iteratorFood.next());
		assertEquals(true, iteratorFood.hasPrevious());
		assertEquals(d, iteratorFood.previous());
		assertEquals(c, iteratorFood.previous());
		assertEquals(b, iteratorFood.previous());
		assertEquals(a, iteratorFood.previous());
		
		try{
			//no more elements in list
			iteratorFood.previous();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorUnsupportedOperationException() {
		linkedFood.addToFront​(a);
		linkedFood.addToEnd​(d);
		ListIterator<Food> iteratorFood = linkedFood.iterator();		
		assertEquals(true, iteratorFood.hasNext());
		assertEquals(a, iteratorFood.next());
		assertEquals(b, iteratorFood.next());
		assertEquals(c, iteratorFood.next());
		assertEquals(true, iteratorFood.hasNext());
		assertEquals(d, iteratorFood.next());
		
		try{
			//remove is not supported for the iterator
			iteratorFood.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		
	}
	
	@Test
	public void testRemove() {
		// remove the first
		assertEquals(b, linkedFood.getFirst());
		assertEquals(c, linkedFood.getLast());
		linkedFood.addToFront​(a);
		assertEquals(a, linkedFood.getFirst());
		linkedFood.remove​(a, comparatorFood);
		assertEquals(b, linkedFood.getFirst());
		//remove from the end of the list
		linkedFood.addToEnd​(d);
		assertEquals(d, linkedFood.getLast());
		linkedFood.remove​(d, comparatorFood);
		assertEquals(c, linkedFood.getLast());
		//remove from middle of list
		linkedFood.addToFront​(a);
		assertEquals(a, linkedFood.getFirst());
		assertEquals(c, linkedFood.getLast());
		linkedFood.remove​(b, comparatorFood);
		assertEquals(a, linkedFood.getFirst());
		assertEquals(c, linkedFood.getLast());
		
	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(b, linkedFood.getFirst());
		linkedFood.addToFront​(a);
		assertEquals(a, linkedFood.getFirst());
		assertEquals(a, linkedFood.retrieveFirstElement());
		assertEquals(b,linkedFood.getFirst());
		assertEquals(b, linkedFood.retrieveFirstElement());
		assertEquals(c,linkedFood.getFirst());
		
		assertEquals("Hole", linkedString.getFirst());
		linkedString.addToFront​("Nice");
		assertEquals("Nice", linkedString.getFirst());
		assertEquals("Nice", linkedString.retrieveFirstElement());
		assertEquals("Hole",linkedString.getFirst());
		assertEquals("Hole", linkedString.retrieveFirstElement());
		assertEquals("Wall",linkedString.getFirst());
		
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(c, linkedFood.getLast());
		linkedFood.addToEnd​(d);
		assertEquals(d, linkedFood.getLast());
		assertEquals(d, linkedFood.retrieveLastElement());
		assertEquals(c,linkedFood.getLast());
		
		assertEquals("Wall", linkedString.getLast());
		linkedString.addToEnd​("Nice");
		assertEquals("Nice", linkedString.getLast());
		assertEquals("Nice", linkedString.retrieveLastElement());
		assertEquals("Wall",linkedString.getLast());
	}

	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class FoodComparator implements Comparator<Food>
	{

		@Override
		public int compare(Food arg0, Food arg1) {
			// put food in alphabetic order by type
			return arg0.toString().compareTo(arg1.toString());
		}
		
	}
	
	private class Food{
		String type;
		String size;
		int calories;
		
		public Food(String type, String size, int calories){
			this.type = type;
			this.size = size;
			this.calories = calories;
		}
		
		public String getType(){
			return type;
		}
		public String getSize(){
			return size;
		}
		public int getCalories(){
			return calories;
		}
		
		public String toString() {
			return (getType()+" "+getSize()+" "+getCalories());
		}
	}
}
