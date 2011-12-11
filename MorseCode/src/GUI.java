import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Translator translator = new Translator();
	private JPanel main_pnl;
	private JLabel input_lbl, output_lbl;
	private JTextArea input_txt, output_txt;
	private JButton swap_btn, translate_btn, dictionary_btn;
	private final int CENTER = GridBagConstraints.CENTER;

	public GUI() {
		this.setSize(500, 300);
		this.setTitle("Morse Code Translator");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		init();
		this.setVisible(true);
	}

	public void init() {
		main_pnl = new JPanel(new GridBagLayout());
		input_lbl = new JLabel("Input");
		input_txt = new JTextArea("Enter English plain-text", 4, 36);
		output_lbl = new JLabel("Output");
		output_txt = new JTextArea("Output MorseCode", 4, 36);
		swap_btn = new JButton("Swap");
		translate_btn = new JButton("Encode");
		dictionary_btn = new JButton("Dictionary");

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
			if (translate_btn.getText().equalsIgnoreCase("Encode")) {
				translate_btn.setText("Decode");
			} else {
				translate_btn.setText("Encode");
			}
		}
	}

	class TranslateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (translate_btn.getText().equalsIgnoreCase("Encode")) {
				output_txt.setText(translator.encode(input_txt.getText()));
			}
			if (translate_btn.getText().equalsIgnoreCase("Decode")) {
				output_txt.setText(translator.decode(input_txt.getText()));
			}
		}
	}

	class DictionaryListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new Lookup();

		}
	}
}
