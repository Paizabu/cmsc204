//Driver class
import java.util.Scanner;
public class SocSecProcessor {
	public static void main(String[] args) throws SocSecException{
		Scanner sc = new Scanner(System.in);
		boolean cont;
		do {
			cont = false;
			System.out.print("Name? ");
			String name = sc.nextLine();
			try {
				System.out.print("SSN? ");
				String ssn = sc.nextLine();
				isValid(ssn);
				System.out.print(name + " " + ssn + " is valid");
			} catch(SocSecException e) {
				System.out.print(e.getMessage());;
			} finally {
				System.out.print("\nContinue? (y/n) ");
				String response = sc.nextLine();
				cont = response.equals("y") ? true : false;
			}
		}while(cont == true);
	}
	public static boolean isValid (String ssn) throws SocSecException{
		if(ssn.length() != 11) {
			throw new SocSecException("wrong number of characters");
		}
		for(int i = 0; i < ssn.length(); i++) {
			if(i == 3 || i == 6) {
				if(ssn.charAt(i) != '-') {
					throw new SocSecException("dashes at wrong positions");
				}
			}
			else if(!Character.isDigit(ssn.charAt(i))) {
				if(ssn.charAt(i) == '-') {
					throw new SocSecException("dashes at wrong positions");
				}
				else throw new SocSecException("contains a character that is not a digit");
			}
			
		}
		return true;
	}
}
