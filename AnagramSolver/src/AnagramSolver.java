import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class AnagramSolver {

	private ArrayList<String> dictionary;
	private ArrayList<String> found;
	private String blacklist;
	private String whitelist;

	public AnagramSolver() {
		init();
		System.out.println("Anagram Solver v1.0");
		System.out.print("Jumbled letters: ");
		String letters = new Scanner(System.in).nextLine();
		solve(letters);
		print();
	}

	private void init() {
		dictionary = new ArrayList<String>();
		found = new ArrayList<String>();
		blacklist = blacklist();
		readFile();
	}

	private String blacklist() {
		ArrayList<Character> list = new ArrayList<Character>();
		String blacklist = "";
		// Special Characters and Numerals
		for (int i = 0; i < 127; i++) {
			if (i < 97 || i > 122) {
				list.add((char) i);
			}
		}
		// Append blacklisted characters to the list
		for (int i = 0; i < list.size()-1; i++) {
			blacklist += list.get(i) + "|";
		}
		blacklist += list.get(list.size()-1);
		System.out.println(blacklist);
		return blacklist;
	}
	
	private String whitelist(String letters) {
		ArrayList<Character> list = new ArrayList<Character>();
		String whitelist = "";		
		for (int i = 0; i < letters.length(); i++) {
			list.add(letters.charAt(i));
		}
		// Append letters to the list
		for (int i = 0; i < list.size()-1; i++) {
			whitelist += list.get(i) + "|";
		}
		whitelist += list.get(list.size()-1);
		System.out.println(whitelist);
		return whitelist;
	}

	private void readFile() {
		try {
			File file = new File("src/resources/dictionary.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				dictionary.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void solve(String letters) {
		letters = letters.toLowerCase();
		if (blacklist.contains(letters)) {
			System.out.println("Sorry, these are not letters!");
		} else {
			whitelist = whitelist(letters);
			for (int i = 0; i < getDictionary().size(); i++) {
				if (getDictionary().get(i).contains(whitelist)) {
					found.add(getDictionary().get(i));
				}
			}
		}
	}
	
	private void print() {
		for (int i = 0; i < found.size(); i++) {
			System.out.println(found.get(i));
		}
	}
	
	public ArrayList<String> getDictionary() {		
		return dictionary;
	}

	public static void main(String[] args) {
		new AnagramSolver();
	}
}
