import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private final String PROGRAM_NAME = "Ryan's Notepad";
	private final int WINDOW_WIDTH = 1024, WINDOW_HEIGHT = 768;
	private JMenuBar menu;
	private JMenu file, edit, help;
	private JMenuItem new_, open, save, close, exit, properties, cut, copy,
			paste, manual, about;
	private JPanel main_pnl;
	private JTabbedPane tab;
	private String clipboard;

	public GUI() {
		init();
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setTitle(PROGRAM_NAME);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setVisible(true);
	}

	private void init() {
		clipboard = "";
		menu();
		components();
	}

	private void menu() {
		// Initialize
		menu = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		help = new JMenu("Help");
		new_ = new JMenuItem("New");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		close = new JMenuItem("Close");
		exit = new JMenuItem("Exit");
		properties = new JMenuItem("Properties");
		cut = new JMenuItem("Cut");
		copy = new JMenuItem("Copy");
		paste = new JMenuItem("Paste");
		manual = new JMenuItem("Manual");
		about = new JMenuItem("About");

		// Set mnemonics
		file.setMnemonic(KeyEvent.VK_F);
		edit.setMnemonic(KeyEvent.VK_E);
		help.setMnemonic(KeyEvent.VK_H);
		new_.setMnemonic(KeyEvent.VK_N);
		open.setMnemonic(KeyEvent.VK_O);
		save.setMnemonic(KeyEvent.VK_S);
		close.setMnemonic(KeyEvent.VK_C);
		exit.setMnemonic(KeyEvent.VK_X);
		properties.setMnemonic(KeyEvent.VK_P);
		cut.setMnemonic(KeyEvent.VK_U);
		copy.setMnemonic(KeyEvent.VK_O);
		paste.setMnemonic(KeyEvent.VK_P);
		manual.setMnemonic(KeyEvent.VK_M);
		about.setMnemonic(KeyEvent.VK_A);

		// Add listeners
		new_.addActionListener(new NewAction());
		open.addActionListener(new OpenAction());
		save.addActionListener(new SaveAction());
		close.addActionListener(new CloseAction());
		exit.addActionListener(new ExitAction());
		properties.addActionListener(new PropertiesAction());
		cut.addActionListener(new CutAction());
		copy.addActionListener(new CopyAction());
		paste.addActionListener(new PasteAction());
		manual.addActionListener(new ManualAction());
		about.addActionListener(new AboutAction());

		// Nest menus
		menu.add(file);
		menu.add(edit);
		menu.add(help);
		file.add(new_);
		file.add(open);
		file.add(save);
		file.add(close);
		file.add(exit);
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		help.add(manual);
		help.add(about);
		this.setJMenuBar(menu);
	}

	private void components() {
		main_pnl = new JPanel(new BorderLayout());
		tab = new JTabbedPane();
		tab.addTab("New", new TabPanel());
		main_pnl.add(tab);
		this.add(main_pnl);
	}

	private class NewAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			tab.addTab("New", new TabPanel());
		}

	}

	private class OpenAction implements ActionListener {

		public void open() {
			// open up a file chooser (a dialog for the user to browse files to
			// open)
			JFileChooser open = new JFileChooser(
					new File(".").getAbsolutePath());
			// get the option that the user selected (approve or cancel)
			int option = open.showOpenDialog(null);
			// NOTE: because we are OPENing a file, we call showOpenDialog~
			// if the user clicked OK, we have "APPROVE_OPTION"
			// so we want to open the file
			if (option == JFileChooser.APPROVE_OPTION) {
				String filename = "";
				String text = "";
				try {
					// create a scanner to read the file
					// (getSelectedFile().getPath() will get the path to the
					// file)
					Scanner scan = new Scanner(new FileReader(open
							.getSelectedFile().getPath()));
					filename = open.getSelectedFile().getPath();
					while (scan.hasNext()) {
						text += scan.nextLine() + "\n";
					}
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
				TabPanel panel = new TabPanel();
				panel.setText(text);
				tab.addTab(filename, panel);
				tab.setSelectedIndex(tab.getTabCount() - 1);
			}
		}

		public void actionPerformed(ActionEvent e) {
			open();
		}

	}

	private class SaveAction implements ActionListener {

		public void save() {
			// again, open a file chooser
			JFileChooser save = new JFileChooser();
			// similar to the open file, only this time we call showSaveDialog
			// instead of showOpenDialog if the user clicked OK (and not cancel)
			int option = save.showSaveDialog(null);

			if (option == JFileChooser.APPROVE_OPTION) {
				String filename = "";
				String text = "";
				try {
					// create a buffered writer to write to a file
					BufferedWriter out = new BufferedWriter(new FileWriter(save
							.getSelectedFile().getPath()));
					filename = save.getSelectedFile().getPath();
					text = ((TabPanel) tab.getComponentAt(tab
							.getSelectedIndex())).getText();
					tab.setTitleAt(tab.getSelectedIndex(), filename);
					out.write(text);
					// write the contents of the TextArea to the file
					out.close(); // close the file stream
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}

		public void actionPerformed(ActionEvent e) {
			if (tab.getTabCount() != 0) {
				save();
			}
		}
	}

	private class CloseAction implements ActionListener {

		public void close() {
			String filename = tab.getTitleAt(tab.getSelectedIndex());
			int response = JOptionPane.showConfirmDialog(null,
					"Do you want to save the changes you made to\n" + filename
							+ "?", PROGRAM_NAME, JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.NO_OPTION) {
				// Don't save
				tab.remove(tab.getSelectedIndex());
			} else if (response == JOptionPane.YES_OPTION) {
				new SaveAction().save();
				tab.remove(tab.getSelectedIndex());
			} else if (response == JOptionPane.CLOSED_OPTION) {
				// Close canceled
			}
		}

		public void actionPerformed(ActionEvent e) {
			close();
		}
	}

	private class ExitAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			for (int i = tab.getTabCount() - 1; i >= 0; i--) {
				String filename = tab.getTitleAt(i);
				int response = JOptionPane
						.showConfirmDialog(null,
								"Do you want to save the changes you made to\n"
										+ filename + "?", PROGRAM_NAME,
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {
					// Don't save
					tab.remove(i);
				} else if (response == JOptionPane.YES_OPTION) {
					new SaveAction().save();
					tab.remove(i);
				} else if (response == JOptionPane.CLOSED_OPTION) {
					// Close canceled
				}
			}
			System.exit(0);
		}

	}

	private class PropertiesAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// Bring up a window:
			// Allow user to change font for all tabs.
		}

	}

	private class CutAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			clipboard = ((TabPanel) tab.getComponentAt(tab.getSelectedIndex()))
					.getSelectedText();
			((TabPanel) tab.getComponentAt(tab.getSelectedIndex())).setSelectedText();
		}

	}

	private class CopyAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			clipboard = ((TabPanel) tab.getComponentAt(tab.getSelectedIndex()))
					.getSelectedText();
		}

	}

	private class PasteAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			((TabPanel) tab.getComponentAt(tab.getSelectedIndex()))
					.insert(clipboard);
		}
	}

	private class ManualAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// Help manual
		}

	}

	private class AboutAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// About application
		}

	}

	public static void main(String[] args) {
		new GUI();
	}
}
