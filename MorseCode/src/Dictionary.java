import java.util.ArrayList;

public class Dictionary {
	private ArrayList<Code> dict;

	public Dictionary() {
		dict = new ArrayList<Code>();
		dict.add(new Code(' ', "+"));
		dict.add(new Code('0', "-----"));
		dict.add(new Code('1', ".----"));
		dict.add(new Code('2', "..---"));
		dict.add(new Code('3', "...--"));
		dict.add(new Code('4', "....-"));
		dict.add(new Code('5', "....."));
		dict.add(new Code('6', "-...."));
		dict.add(new Code('7', "--..."));
		dict.add(new Code('8', "---.."));
		dict.add(new Code('9', "----."));
		dict.add(new Code('A', ".-"));
		dict.add(new Code('B', "-..."));
		dict.add(new Code('C', "-.-."));
		dict.add(new Code('D', "-.."));
		dict.add(new Code('E', "."));
		dict.add(new Code('F', "..-."));
		dict.add(new Code('G', "--."));
		dict.add(new Code('H', "...."));
		dict.add(new Code('I', ".."));
		dict.add(new Code('J', ".---"));
		dict.add(new Code('K', "-.-"));
		dict.add(new Code('L', ".-.."));
		dict.add(new Code('M', "--"));
		dict.add(new Code('N', "-."));
		dict.add(new Code('O', "---"));
		dict.add(new Code('P', ".--."));
		dict.add(new Code('Q', "--.-"));
		dict.add(new Code('R', ".-."));
		dict.add(new Code('S', "..."));
		dict.add(new Code('T', "-"));
		dict.add(new Code('U', "..-"));
		dict.add(new Code('V', "...-"));
		dict.add(new Code('W', ".--"));
		dict.add(new Code('X', "-..-"));
		dict.add(new Code('Y', "-.--"));
		dict.add(new Code('Z', "--.."));
	}

	public ArrayList<Code> getDictionary() {
		return dict;
	}

	public int getSize() {
		return getDictionary().size();
	}

	public String toString() {
		String out = "Char\tSequence\n";
		Dictionary dict = new Dictionary();
		for (int i = 0; i < dict.getSize(); i++) {
			out += String.format(" '%c'\t%s\n", getDictionary().get(i)
					.getCharacter(), getDictionary().get(i).getSequence());
		}
		return out;
	}
}
