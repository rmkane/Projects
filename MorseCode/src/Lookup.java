import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Lookup {

	public Lookup() {
		LookupDialog d = new LookupDialog(new Dictionary().toString());
		d.setSize(250, 500);
		d.setLocationRelativeTo(null);
		d.setVisible(true);
	}

	public static void main(String[] args) {
		new Lookup();
	}

	class LookupDialog extends JDialog {
		
		private static final long serialVersionUID = 1L;

		public LookupDialog(String text) {
			JTextArea txt = new JTextArea(text);
			Font font = new Font("Courier New", Font.BOLD, 20);
			txt.setFont(font);

			this.setTitle("Morse Code Dictionary");
			this.setLayout(new BorderLayout());
			this.add(new JScrollPane(txt));
		}
	}
}
