import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class GUI extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private Translator translator = new Translator();
	private JMenuBar menu;
	private JMenu file, help;
	private JMenuItem reset, about;
	private JPanel main_pnl;
	private JLabel input_lbl, output_lbl;
	private JTextArea input_txt, output_txt;
	private JButton swap_btn, translate_btn, dictionary_btn;

	private final int CENTER = GridBagConstraints.CENTER;
	private String clipboard;

	private enum State {
		ENCODE, DECODE
	};

	private State mode;

	public GUI() {
		this.setSize(500, 400);
		this.setTitle("Morse Code Translator");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		init();
		this.setVisible(true);
	}

	public void init() {
		menu = new JMenuBar();
		file = new JMenu("File");
		help = new JMenu("Help");
		reset = new JMenuItem("Reset");
		about = new JMenuItem("About");
		main_pnl = new JPanel(new GridBagLayout());
		input_lbl = new JLabel("Input English plain-text");
		input_txt = new JTextArea(4, 36);
		output_lbl = new JLabel("Output Morse Code");
		output_txt = new JTextArea(4, 36);
		swap_btn = new JButton("Swap");
		translate_btn = new JButton("Encode");
		dictionary_btn = new JButton("Dictionary");

		// Menu
		file.setMnemonic(KeyEvent.VK_F);
		help.setMnemonic(KeyEvent.VK_H);
		reset.setMnemonic(KeyEvent.VK_R);
		about.setMnemonic(KeyEvent.VK_A);
		reset.addActionListener(new ResetListener());
		about.addActionListener(new AboutListener());
		this.setJMenuBar(menu);
		menu.add(file);
		menu.add(help);
		file.add(reset);
		help.add(about);

		// Panels
		main_pnl.setBackground(new Color(0x8888FF));

		// Labels
		input_lbl.setForeground(Color.WHITE);
		output_lbl.setForeground(Color.WHITE);

		// Text Areas
		input_txt.setLineWrap(true);
		input_txt.setBackground(new Color(0xFFFFA8));
		input_txt.setBorder(new LineBorder(new Color(0x000044)));
		output_txt.setFocusable(false);
		output_txt.setLineWrap(true);
		output_txt.setBackground(new Color(0xFFFFA8));
		output_txt.setBorder(new LineBorder(new Color(0x000044)));
		output_txt.addMouseListener(this);

		// Buttons
		swap_btn.addActionListener(new SwapListener());
		translate_btn.addActionListener(new TranslateListener());
		dictionary_btn.addActionListener(new DictionaryListener());

		// Add components
		this.add(main_pnl);
		addComponent(main_pnl, input_lbl, 0, 0, 3, 1, CENTER);
		addComponent(main_pnl, input_txt, 0, 1, 3, 1, CENTER);
		addComponent(main_pnl, output_lbl, 0, 2, 3, 1, CENTER);
		addComponent(main_pnl, output_txt, 0, 3, 3, 1, CENTER);
		addComponent(main_pnl, swap_btn, 0, 4, 1, 1, CENTER);
		addComponent(main_pnl, translate_btn, 1, 4, 1, 1, CENTER);
		addComponent(main_pnl, dictionary_btn, 2, 4, 1, 1, CENTER);

		mode = State.ENCODE;
		clipboard = "";
	}

	private void addComponent(JPanel p, JComponent c, int x, int y, int width,
			int height, int align) {
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = width;
		gc.gridheight = height;
		gc.weightx = 100.0;
		gc.weighty = 100.0;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.anchor = align;
		gc.fill = GridBagConstraints.NONE;
		p.add(c, gc);
	}

	public static void main(String[] args) {
		new GUI();
	}

	class SwapListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (mode == State.ENCODE) {
				translate_btn.setText("Decode");
				mode = State.DECODE;

			} else {
				translate_btn.setText("Encode");
				mode = State.ENCODE;
			}

			String tmp = input_txt.getText();
			input_txt.setText(output_txt.getText());
			output_txt.setText(tmp);

			tmp = input_lbl.getText();
			input_lbl.setText(output_lbl.getText());
			output_lbl.setText(tmp);
		}
	}

	class TranslateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (mode == State.ENCODE) {
				output_txt.setText(translator.encode(input_txt.getText()));
			}
			if (mode == State.DECODE) {
				output_txt.setText(translator.decode(input_txt.getText()));
			}
		}
	}

	class ResetListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			input_lbl.setText("Input English plain-text");
			output_lbl.setText("Output Morse Code");
			translate_btn.setText("Encode");
			mode = State.ENCODE;
			clipboard = "";
			input_txt.setText("");
			output_txt.setText("");
		}
	}

	class AboutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String title = "Instructions";
			String instructions = "When decoding morse code into\n"
					+ "English plain-text, use spaces\n"
					+ "between letters and use a '+' to\n"
					+ "add spaces between separate words.";
			Font font = new Font("Sans", Font.PLAIN, 16);
			MyDialog d = new MyDialog(title, instructions, font);
			d.setSize(300, 160);
			d.setLocationRelativeTo(null);
			d.setVisible(true);
		}
	}

	class DictionaryListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String title = "Morse Code Dictionary";
			String dict = new Dictionary().toString();
			Font font = new Font("Courier New", Font.BOLD, 20);
			MyDialog d = new MyDialog(title, dict, font);
			d.setSize(250, 500);
			d.setLocationRelativeTo(null);
			d.setVisible(true);

		}
	}

	class MyDialog extends JDialog {

		private static final long serialVersionUID = 1L;

		public MyDialog(String title, String text, Font font) {
			JTextArea txt = new JTextArea(text);
			txt.setFont(font);
			txt.setLineWrap(true);
			txt.setEditable(false);
			this.setTitle(title);
			this.setLayout(new BorderLayout());
			this.add(new JScrollPane(txt));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mouseButton = e.getButton();

		if (mouseButton == MouseEvent.BUTTON1) {
			input_txt.append(clipboard);

		} else if (mouseButton == MouseEvent.BUTTON2) {
			// Center Mouse Click

		} else if (mouseButton == MouseEvent.BUTTON3) {
			// Right Mouse Click
			clipboard = output_txt.getText();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}