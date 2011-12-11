import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AnagramSolver {

	private ArrayList<String> dictionary;
	private ArrayList<String> found;
	private ArrayList<Character> blacklist;
	private ArrayList<Character> whitelist;

	public AnagramSolver() {
		init();
		System.out.println("Anagram Solver v1.0");
		System.out.print("Jumbled letters: ");
		String letters = new Scanner(System.in).nextLine();
		solve(letters);
		print();
	}

	// Initializer
	private void init() {
		dictionary = new ArrayList<String>();
		found = new ArrayList<String>();
		blacklist = blacklist();
		readFile();
	}

	// Stores invalid letters - Not used at the moment
	private ArrayList<Character> blacklist() {
		ArrayList<Character> blacklist = new ArrayList<Character>();
		// Special Characters and Numerals
		for (int i = 0; i < 127; i++) {
			if (i < 97 || i > 122) {
				blacklist.add((char) i);
			}
		}
		return blacklist;
	}

	// Stores valid letters
	private ArrayList<Character> whitelist(String letters) {
		ArrayList<Character> whitelist = new ArrayList<Character>();
		for (int i = 0; i < letters.length(); i++) {
			whitelist.add(letters.charAt(i));
		}
		return whitelist;
	}

	// Reads in a dictionary text file
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

	// Solves an anagram
	private void solve(String letters) {
		letters = letters.toLowerCase();
		// Add the letters to a white list
		whitelist = whitelist(letters);
		// Loop to check a word in dictionary
		for (int i = 0; i < getDictionary().size(); i++) {
			// Validator array stores integers. The purpose of this array is to
			// hold a 1 or 0 for each letter in the selected word from the
			// dictionary. After the whole word has been scanned, a checksum
			// will be calculated. Each integer is multiplied by one another. If
			// there is at least one 0 then the word will be invalid, because
			// it has to be composed of every letter in the whitelist.
			ArrayList<Integer> valid = new ArrayList<Integer>();
			// Loop to compare each letter in the selected word
			loop: for (int d = 0; d < getDictionary().get(i).length(); d++) {
				int match = 0; // Default value of 0 until proven true
				// Compare each whitelist letter to each letter of the
				// selected word.
				for (int c = 0; c < whitelist.size(); c++) {
					match = 0; // Reassign the value 0.
					if (getDictionary().get(i).charAt(d) == whitelist.get(c)) {
						// If the selected word contains a letter from the
						// whitelist then verify a match and move on to the next
						// letter in the word/
						match = 1;
						continue loop;
					}
				}
				valid.add(match);
			}
			int checksum = 1;
			for (Integer v : valid) {
				checksum *= v;
			}
			if (checksum == 1
					&& getDictionary().get(i).length() <= whitelist.size()
					&& getDictionary().get(i).length() > 3) {
				found.add(getDictionary().get(i));
			}
		}
	}

	// Prints the list of anagrams for a given set of letters
	private void print() {
		for (int i = 0; i < found.size(); i++) {
			System.out.println(found.get(i));
		}
	}

	// Retrieves the dictionary
	public ArrayList<String> getDictionary() {
		return dictionary;
	}

	public static void main(String[] args) {
		new AnagramSolver();
	}
}
