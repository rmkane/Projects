import java.util.ArrayList;

public class Translator {
	private Dictionary dictionary = new Dictionary();
	private ArrayList<Code> code = dictionary.getDictionary();

	public Translator() {
	}

	public String decode(String input) {
		String delimiter = "  ";
		input += delimiter;
		String[] seg = input.split(delimiter);
		String output = "";

		for (int i = 0; i < seg.length; i++) {
			seg[i] = " " + seg[i] + " ";
			if (i == 0) seg[0] = seg[0].substring(1, seg[0].length());
			for (int d = 0; d < code.size(); d++) {
				if (seg[i].equalsIgnoreCase(code.get(d).getSequence())) {
					output += code.get(d).getCharacter();
				}
			}
		}
		return output;
	}

	public String encode(String input) {
		input = input.toUpperCase();
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
		new Translator();
	}
}