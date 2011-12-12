import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ReplaceExample extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextArea textArea = new JTextArea(8, 40);
	private JScrollPane scrollPane = new JScrollPane(textArea);
	private JTextField fromField = new JTextField(8);
	private JTextField toField = new JTextField(8);

	public ReplaceExample() {
		this.setTitle("TextEditTest");
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);

		JPanel panel = new JPanel();
		JButton replaceButton = new JButton("Replace");
		replaceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String from = fromField.getText();
				int start = textArea.getText().indexOf(from);
				if (start >= 0 && from.length() > 0)
					textArea.replaceRange(toField.getText(), start, start
							+ from.length());
			}
		});

		panel.add(replaceButton);
		panel.add(fromField);
		panel.add(new JLabel("with"));
		panel.add(toField);
		this.add(panel, "South");
		this.add(scrollPane, "Center");
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new ReplaceExample();
	}
}
