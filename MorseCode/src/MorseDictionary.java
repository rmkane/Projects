import java.util.ArrayList;

public class MorseDictionary {
	private ArrayList<MorseCode> dict;

	public MorseDictionary() {
		dict = new ArrayList<MorseCode>();
		dict.add(new MorseCode(' ', "+"));
		dict.add(new MorseCode('0', "-----"));
		dict.add(new MorseCode('1', ".----"));
		dict.add(new MorseCode('2', "..---"));
		dict.add(new MorseCode('3', "...--"));
		dict.add(new MorseCode('4', "....-"));
		dict.add(new MorseCode('5', "....."));
		dict.add(new MorseCode('6', "-...."));
		dict.add(new MorseCode('7', "--..."));
		dict.add(new MorseCode('8', "---.."));
		dict.add(new MorseCode('9', "----."));
		dict.add(new MorseCode('A', ".-"));
		dict.add(new MorseCode('B', "-..."));
		dict.add(new MorseCode('C', "-.-."));
		dict.add(new MorseCode('D', "-.."));
		dict.add(new MorseCode('E', "."));
		dict.add(new MorseCode('F', "..-."));
		dict.add(new MorseCode('G', "--."));
		dict.add(new MorseCode('H', "...."));
		dict.add(new MorseCode('I', ".."));
		dict.add(new MorseCode('J', ".---"));
		dict.add(new MorseCode('K', "-.-"));
		dict.add(new MorseCode('L', ".-.."));
		dict.add(new MorseCode('M', "--"));
		dict.add(new MorseCode('N', "-."));
		dict.add(new MorseCode('O', "---"));
		dict.add(new MorseCode('P', ".--."));
		dict.add(new MorseCode('Q', "--.-"));
		dict.add(new MorseCode('R', ".-."));
		dict.add(new MorseCode('S', "..."));
		dict.add(new MorseCode('T', "-"));
		dict.add(new MorseCode('U', "..-"));
		dict.add(new MorseCode('V', "...-"));
		dict.add(new MorseCode('W', ".--"));
		dict.add(new MorseCode('X', "-..-"));
		dict.add(new MorseCode('Y', "-.--"));
		dict.add(new MorseCode('Z', "--.."));
	}

	public ArrayList<MorseCode> getDictionary() {
		return dict;
	}
}
