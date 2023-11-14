import java.io.*;
import java.util.*;

//author: Paizabu Min
public class MorseCodeConverter {
	
	public static MorseCodeTree<String> morseTree = new MorseCodeTree<String>();
	
	public MorseCodeConverter() {}
	
	/**
	 * Converts Morse code into English. Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	 * @param code - the Morse code;
	 * @return the English translation
	 */
	public static String convertToEnglish(String code) {
		String result = "";
		String[] codeStr = code.split(" ");
		for(int i = 0; i < codeStr.length; i++) {
			if(codeStr[i].equals("/")) {
				result += " ";
				continue;
			}
			result += morseTree.fetch(codeStr[i]);
		}
		return result;
	}
	
	/**
	 * Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	 * @param codeFile - name of the File that contains Morse Code
	 * @return the English translation of the file
	 * @throws FileNotFoundException
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException{
		String result = "";
		
		Scanner sc = new Scanner(codeFile);
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			result += convertToEnglish(line);
		}
		return result;
	}
	
	/**
	 * returns a string with all the data in the tree in LNR order with an space in between them.
	 * @return string representation of the data in the tree in LNR order separated by a space.
	 */
	public static String printTree() {
		String result = "";
		ArrayList<String> list = morseTree.toArrayList();
		for(int i = 0; i < list.size(); i++) {
			result += list.get(i) + " ";
		}
		return result.trim();
	}
}
