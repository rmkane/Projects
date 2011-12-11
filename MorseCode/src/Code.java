public class Code {
	private char character;
	private String sequence;

	public Code(char character, String sequence) {
		this.character = character;
		this.sequence = " " + sequence + " ";
	}

	public char getCharacter() {
		return character;
	}

	public String getSequence() {
		return sequence;
	}
}
