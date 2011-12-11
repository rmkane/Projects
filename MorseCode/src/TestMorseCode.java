import java.util.ArrayList;
import java.util.Scanner;

public class TestMorseCode {
	private MorseDictionary dictionary = new MorseDictionary();
	private ArrayList<MorseCode> code = dictionary.getDictionary();

	public TestMorseCode() {
		System.out
				.println("This progrm will convert english plain-text to morse code.");
		System.out.print("Input: ");
		String input = new Scanner(System.in).nextLine();
		input = input.toUpperCase();
		System.out.println("Output: " + decode(input));
	}

	private String decode(String input) {
		String output = "";
		for (int i = 0; i < input.length(); i++) {
			for (int d = 0; d < code.size(); d++) {
				if (input.charAt(i) == code.get(d).getCharacter()) {
					output += code.get(d).getSequence();
				}
			}
		}
		return output;
	}

	public static void main(String[] args) {
		new TestMorseCode();
	}
}