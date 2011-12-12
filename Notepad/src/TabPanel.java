import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TabPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int id;
	private JTextArea text_area;
	private JScrollPane scroll;

	public TabPanel() {
		this.setLayout(new BorderLayout());
		text_area = new JTextArea();
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

	public void setFontStyle(Font font) {
		text_area.setFont(font);
	}

	public void setLineWrap(boolean wrap) {
		text_area.setLineWrap(wrap);
	}
}