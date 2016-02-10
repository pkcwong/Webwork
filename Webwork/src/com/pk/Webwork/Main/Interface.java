package com.pk.Webwork.Main;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Interface extends JFrame {

	private JPanel Panel = new JPanel();
	private JLabel Head_t = new JLabel("Output:");
	private JLabel Body_t = new JLabel("Console:");
	private JLabel Input_t = new JLabel("Input args[ ]: ");
	private JTextArea Head = new JTextArea(5, 20);
	private JTextArea Body = new JTextArea(5, 20);
	public JTextArea Input = new JTextArea(12, 10);
	private JButton load = new JButton("Load");
	private JButton refresh = new JButton("Refresh");
	private JSpinner num = new JSpinner();

	public Interface() {
		super("Webwork");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.Head.setText("Output goes here");
		this.Head.setEditable(false);
		this.Body.setText("Console goes here");
		this.Body.setEditable(false);
		this.load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeBody("Execute 'load'");
				loader();
			}
		});
		this.refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeBody("Execute 'refresh'");
				refresh();
			}
		});
		SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 0, 1);
		num.setModel(model);
		JFormattedTextField tf = ((JSpinner.DefaultEditor) num.getEditor()).getTextField();
		tf.setEditable(false);
		tf.setBackground(Color.white);
		this.setContentPane(Panel);
		this.setResizable(false);
		this.clean();
		this.setVisible(true);
	}

	/**
	 * WRITES TO HEAD
	 * <p>
	 * 
	 * @param text
	 *            string
	 */
	public void writeHead(String text) {
		this.Head.setText(this.Head.getText() + "\n" + text);
	}

	/**
	 * WRITES TO BODY
	 * <p>
	 * 
	 * @param text
	 *            string
	 */
	public void writeBody(String text) {
		this.Body.setText(this.Body.getText() + "\n" + text);
	}

	/**
	 * UPDATES INTERFACE
	 * <p>
	 */
	public void update() {
		this.clean();
		this.repaint();
	}

	/**
	 * CLEANUP
	 * <p>
	 */
	private void clean() {
		this.Panel.removeAll();
		GridBagLayout layout = new GridBagLayout();
		this.Panel.setLayout(layout);
		this.Panel.add(this.Head_t, this.grid(0, 0, 1, 1));
		JScrollPane scr_h = new JScrollPane(this.Head);
		this.Panel.add(scr_h, this.grid(0, 1, 2, 2));
		this.Panel.add(this.Body_t, this.grid(0, 3, 1, 1));
		JScrollPane scr_b = new JScrollPane(this.Body);
		this.Panel.add(scr_b, this.grid(0, 4, 2, 2));
		this.Panel.add(this.Input_t, this.grid(2, 0, 1, 1));
		this.Panel.add(num, this.grid(3, 0, 1, 1));
		JScrollPane scr_i = new JScrollPane(this.Input);
		this.Panel.add(scr_i, this.grid(2, 1, 2, 5));
		this.Panel.add(this.load, this.grid(0, 6, 2, 1));
		this.Panel.add(this.refresh, this.grid(2, 6, 2, 1));
		this.Panel.validate();
		this.setContentPane(this.Panel);
		this.validate();
		Insets insets = this.getInsets();
		this.setSize(500 + insets.left + insets.right, 300 + insets.top + insets.bottom);
		this.setLocationRelativeTo(null);
	}

	private GridBagConstraints grid(int gridx, int gridy, int gridwidth, int gridheight) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = gridwidth;
		c.gridheight = gridheight;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		return c;
	}

	private void loader() {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter=new FileNameExtensionFilter("Answer Keys (*.pk)", "pk");
		fc.setFileFilter(filter);
		if (Main.key!=null) {
			fc.setCurrentDirectory(Main.key.getParentFile());
		}
		fc.showOpenDialog(this);
		if (Main.setFile(fc.getSelectedFile())) {
			SpinnerNumberModel model = new SpinnerNumberModel(1, 1, Main.getNumRecords(), 1);
			num.setModel(model);
		} else {
			SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 0, 1);
			num.setModel(model);
		}
		JFormattedTextField tf = ((JSpinner.DefaultEditor) num.getEditor()).getTextField();
		tf.setEditable(false);
	}

	private void refresh() {
		Main.load((int) (this.num.getValue()));
		Main.input(this.Input.getText());
		Main.execute();
	}

}
