import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class MyDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextArea txt;
	private Border border;
	private int pad;

	public MyDialog(String title, String text, Font font) {
		txt = new JTextArea(text);
		pad = 20;
		border = BorderFactory.createEmptyBorder(pad, pad, pad, pad);
		txt.setFont(font);
		txt.setBorder(border);
		txt.setLineWrap(true);
		txt.setEditable(false);
		this.setTitle(title);
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(txt));
	}
}