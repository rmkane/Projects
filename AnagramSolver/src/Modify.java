import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Modify {

	private String filename;
	private String text;

	public Modify() {
		filename = "";
		text = "";
		open();
		convert();
		save();
	}

	public static void main(String[] args) {
		new Modify();
	}

	public void open() {
		String text = "";
		JFileChooser open = new JFileChooser(new File(".").getAbsolutePath());
		int option = open.showOpenDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) {
			filename = open.getSelectedFile().getPath();
			try {
				Scanner scan = new Scanner(new FileReader(filename));
				while (scan.hasNext()) {
					text += scan.nextLine() + "\n";
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			this.text = text;
		}
	}

	public void convert() {
		text = text.toLowerCase();
		System.out.println(text);
	}

	public void save() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(filename));
			out.write(text);
			out.close(); // close the file stream
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}