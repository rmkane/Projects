import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.EventListenerList;

public class ColorComboBox {
	static class ColorCellRenderer implements ListCellRenderer {
		protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

		// width doesn't matter as combobox will size
		private final static Dimension preferredSize = new Dimension(0, 20);

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			JLabel renderer = (JLabel) defaultRenderer
					.getListCellRendererComponent(list, value, index,
							isSelected, cellHasFocus);
			if (value instanceof Color) {
				renderer.setBackground((Color) value);
			}
			renderer.setPreferredSize(preferredSize);
			return renderer;
		}
	}

	public static void main(String args[]) {
		Color colors[] = { Color.black, Color.blue, Color.cyan, Color.darkGray,
				Color.gray, Color.green, Color.lightGray, Color.magenta,
				Color.orange, Color.pink, Color.red, Color.white, Color.yellow };
		JFrame frame = new JFrame("Color JComboBox");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();

		final JComboBox comboBox = new JComboBox(colors);
		comboBox.setMaximumRowCount(5);
		comboBox.setEditable(true);
		comboBox.setRenderer(new ColorCellRenderer());
		Color color = (Color) comboBox.getSelectedItem();
		ComboBoxEditor editor = new ColorComboBoxEditor(color);
		comboBox.setEditor(editor);
		contentPane.add(comboBox, BorderLayout.NORTH);

		final JLabel label = new JLabel();
		label.setOpaque(true);
		label.setBackground((Color) comboBox.getSelectedItem());
		contentPane.add(label, BorderLayout.CENTER);

		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				Color selectedColor = (Color) comboBox.getSelectedItem();
				label.setBackground(selectedColor);
			}
		};
		comboBox.addActionListener(actionListener);

		frame.setSize(300, 200);
		frame.setVisible(true);
	}
}

class ColorComboBoxEditor implements ComboBoxEditor {
	final protected JButton editor;

	transient protected EventListenerList listenerList = new EventListenerList();

	public ColorComboBoxEditor(Color initialColor) {
		editor = new JButton("");
		editor.setBackground(initialColor);
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color currentBackground = editor.getBackground();
				Color color = JColorChooser.showDialog(editor, "Color Chooser",
						currentBackground);
				if ((color != null) && (currentBackground != color)) {
					editor.setBackground(color);
					fireActionEvent(color);
				}
			}
		};
		editor.addActionListener(actionListener);
	}

	public void addActionListener(ActionListener l) {
		listenerList.add(ActionListener.class, l);
	}

	public Component getEditorComponent() {
		return editor;
	}

	public Object getItem() {
		return editor.getBackground();
	}

	public void removeActionListener(ActionListener l) {
		listenerList.remove(ActionListener.class, l);
	}

	public void selectAll() {
		// ignore
	}

	public void setItem(Object newValue) {
		if (newValue instanceof Color) {
			Color color = (Color) newValue;
			editor.setBackground(color);
		} else {
			// Try to decode
			try {
				Color color = Color.decode(newValue.toString());
				editor.setBackground(color);
			} catch (NumberFormatException e) {
				// ignore - value unchanged
			}
		}
	}

	protected void fireActionEvent(Color color) {
		Object listeners[] = listenerList.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == ActionListener.class) {
				ActionEvent actionEvent = new ActionEvent(editor,
						ActionEvent.ACTION_PERFORMED, color.toString());
				((ActionListener) listeners[i + 1])
						.actionPerformed(actionEvent);
			}
		}
	}
}
