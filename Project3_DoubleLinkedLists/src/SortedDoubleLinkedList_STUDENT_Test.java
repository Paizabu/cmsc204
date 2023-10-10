import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//author: Paizabu Min
public class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<String> linkedString;
	SortedDoubleLinkedList<Double> linkedDouble;
	SortedDoubleLinkedList<Car> linkedCar;
	StringComparator comparator;
	DoubleComparator comparatorD;
	CarComparator comparatorCar;
	
	public Car a=new Car("Ford", "F150", 2005);
	public Car b=new Car("Jeep", "Renegade", 2005);
	public Car c=new Car("Honda", "Civic", 2005);
	public Car d=new Car("Subaru", "Outback", 2005);
	public Car e=new Car("Chevrolet", "Silverado", 2005);
	public Car f=new Car("Chrysler", "PTCruiser", 2005);
	
	public ArrayList<Car> fill = new ArrayList<Car>();
	
	@Before
	public void setUp() throws Exception {
		comparator = new StringComparator();
		linkedString = new SortedDoubleLinkedList<String>(comparator);
		
		comparatorD = new DoubleComparator();
		linkedDouble = new SortedDoubleLinkedList<Double>(comparatorD);
		
		comparatorCar = new CarComparator();
		linkedCar= new SortedDoubleLinkedList<Car>(comparatorCar);
		
		
	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
		linkedDouble = null;
		linkedCar = null;
		comparatorD = null;
		comparator = null;
	}

	@Test
	public void testAdd() {
		linkedString.add​("Hello");
		linkedString.add​("World");
		assertEquals("Hello", linkedString.getFirst());
		assertEquals("World", linkedString.getLast());
		linkedString.add​("Apple");
		linkedString.add​("Zillow");
		assertEquals("Apple", linkedString.getFirst());
		assertEquals("Zillow", linkedString.getLast());
		
		linkedDouble.add​(15.0);
		linkedDouble.add​(100.0);
		assertEquals(15.0, linkedDouble.getFirst(), .001);
		assertEquals(100.0, linkedDouble.getLast(), .001);
		linkedDouble.add​(-1.0);
		linkedDouble.add​(10000.0);
		assertEquals(-1.0, linkedDouble.getFirst(), .001);
		assertEquals(10000.0, linkedDouble.getLast(), .001);
		
		linkedCar.add​(b);
		linkedCar.add​(c);
		assertEquals(c,linkedCar.getFirst());
		assertEquals(b,linkedCar.getLast());
		linkedCar.add​(a);
		linkedCar.add​(d);
		assertEquals(a,linkedCar.getFirst());
		assertEquals(d,linkedCar.getLast());
		
	}
	
	@Test
	public void testAddToEnd() {
		try {
			linkedString.addToEnd​("Hello");
		} catch(UnsupportedOperationException e){
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
	}
	
	@Test
	public void testAddToFront() {
		try {
			linkedString.addToFront​("Hello");
		} catch(UnsupportedOperationException e){
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
	}
	
	@Test
	public void testIterator() {
		linkedCar.add​(a);
		linkedCar.add​(b);
		linkedCar.add​(c);
		linkedCar.add​(d);
		ListIterator<Car> iteratorCar = linkedCar.iterator();		
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(false, iteratorCar.hasPrevious());
		assertEquals(a, iteratorCar.next());
		assertEquals(c, iteratorCar.next());
		assertEquals(true, iteratorCar.hasPrevious());
		assertEquals(b, iteratorCar.next());
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(d, iteratorCar.next());
		assertEquals(false, iteratorCar.hasNext());
		
		try{
			//remove is not supported for the iterator
			iteratorCar.remove();
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
		linkedString.add​("Hello");
		linkedString.add​("World");
		linkedString.remove​("Hello", comparator);
		assertEquals("World", linkedString.getFirst());
		
		linkedString.add​("Apple");
		linkedString.add​("Zillow");
		linkedString.remove​("Zillow", comparator);
		assertEquals("World", linkedString.getLast());
		

		linkedDouble.add​(15.0);
		linkedDouble.add​(100.0);
		linkedDouble.remove​(15.0, comparatorD);
		assertEquals(100.0, linkedDouble.getFirst(), .001);
		
		linkedDouble.add​(-1.0);
		linkedDouble.add​(10000.0);
		linkedDouble.remove​(10000.0, comparatorD);
		assertEquals(100, linkedDouble.getLast(), .001);
		
		
		linkedCar.add​(b);
		linkedCar.add​(c);
		linkedCar.remove​(c, comparatorCar);
		assertEquals(b,linkedCar.getFirst());

		linkedCar.add​(a);
		linkedCar.add​(d);
		linkedCar.remove​(d, comparatorCar);
		assertEquals(b,linkedCar.getLast());
	}

	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class CarComparator implements Comparator<Car>
	{

		@Override
		public int compare(Car arg0, Car arg1) {
			// Just put cars in alphabetic order by make
			return arg0.toString().compareTo(arg1.toString());
		}
		
	}
	
	private class Car{
		String make;
		String model;
		int year;
		
		public Car(String make, String model, int year){
			this.make = make;
			this.model = model;
			this.year = year;
		}
		
		public String getMake(){
			return make;
		}
		public String getModel(){
			return model;
		}
		public int getYear(){
			return year;
		}
		
		public String toString() {
			return (getMake()+" "+getModel()+" "+getYear());
		}
	}
}
