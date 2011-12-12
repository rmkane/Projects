import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class TabPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int id;
	private JTextArea text_area;
	private JScrollPane scroll;
	private Border border;
	private int pad = 10;

	public TabPanel() {
		this.setLayout(new BorderLayout());
		text_area = new JTextArea();
		border = BorderFactory.createEmptyBorder(pad, pad, pad, pad);
		text_area.setBorder(border);
		text_area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		scroll = new JScrollPane(text_area);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scroll);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setText(String text) {
		text_area.setText(text);
	}

	public String getText() {
		return text_area.getText();
	}

	public void deleteSelectedText() {
		String from = text_area.getText();
		int start = text_area.getText().indexOf(from);
		if (start >= 0 && from.length() > 0) {
			text_area.replaceRange("", start,
					start + from.length());
		}
	}

	public String getSelectedText() {
		return text_area.getSelectedText();
	}

	public void insert(String text) {
		text_area.insert(text, text_area.getCaretPosition());
	}

	public void setFontStyle(Font font) {
		text_area.setFont(font);
	}

	public void setLineWrap(boolean wrap) {
		text_area.setLineWrap(wrap);
	}
}