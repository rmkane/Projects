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

	private void init() {
		dictionary = new ArrayList<String>();
		found = new ArrayList<String>();
		blacklist = blacklist();
		readFile();
	}

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

	private ArrayList<Character> whitelist(String letters) {
		ArrayList<Character> whitelist = new ArrayList<Character>();
		for (int i = 0; i < letters.length(); i++) {
			whitelist.add(letters.charAt(i));
		}
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
		whitelist = whitelist(letters);
		for (int i = 0; i < getDictionary().size(); i++) {
			ArrayList<Integer> valid = new ArrayList<Integer>(); 
			for (int c = 0; c < whitelist.size(); c++) {
				if (getDictionary().get(i).contains(Character.toString(whitelist.get(c)))) {
					valid.add(1);
				} else {
					valid.add(0);
				}
			}
			int checksum = 1;
			for (Integer v : valid) {
				checksum *= v;
			}
			if (checksum == 1) {
				found.add(getDictionary().get(i));
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
