/**
 * Class: CMSC203 
 * Program: Lab 1
 * Instructor: Professor Farnaz Eivazi
 * Summary of Description: Main driver for movie class to gather user inputed movie info
 * and print out info. loops if user requests
 * Due Date: 07/03/2023 
 * Integrity Pledge: I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 */

import java.util.*;
public class MovieDriver_Task2 {
	public static void main (String[] args) {
		//new scanner object
		Scanner scanner = new Scanner(System.in);
		
		//do-while loop to detect if user want to continue
		do {
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
			System.out.println(movie.toString() + 
					"\nDo you want to enter another? (y or n)");
			scanner.nextLine();
			
		//while loop continues if user enters y
		}while(scanner.nextLine().equals("y"));
		
		System.out.println("Goodbye");
		//close scanner
		scanner.close();
	}
}
