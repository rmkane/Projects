public class ASCII {

	public ASCII() {

		System.out.println("Int" + "\t" + "Char");

		for (int i = 0; i < 128; i++) {
			System.out.println(i + "\t" + (char) i);
		}
	}

	public static void main(String[] args) {
		new ASCII();
	}
}
