/**
 * Class: CMSC203 
 * Program: Lab 1
 * Instructor: Professor Farnaz Eivazi
 * Summary of Description: Main driver for movie class to gather user inputed movie info
 * and print out info 
 * Due Date: 07/03/2023 
 * Integrity Pledge: I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 */

//import scanner class
import java.util.*;
public class MovieDriver_Task1 {
	public static void main (String[] args) {
		//new scanner object
		Scanner scanner = new Scanner(System.in);
		//new movie object
		Movie movie = new Movie();
		
		//collect user data using scanner class
		System.out.println("Enter the name of a movie");
		movie.setTitle(scanner.nextLine());
		
		System.out.println("Enter the rating of the movie");
		movie.setRating(scanner.nextLine());
		
		System.out.println("Enter the number of tickets sold for this movie");
		movie.setSoldTickets(scanner.nextInt());
		
		//print out data using to-string method
		System.out.println(movie.toString() + "\nGoodbye");
		
		//close scanner
		scanner.close();
	}
}
