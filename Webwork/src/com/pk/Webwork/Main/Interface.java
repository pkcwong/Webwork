package com.pk.Webwork.Main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Interface extends JFrame {

	private JScrollPane scrPane;
	private JPanel Panel = new JPanel();
	private JLabel Head_t = new JLabel("Output:");
	private JLabel Body_t = new JLabel("Console:");
	private JTextArea Head = new JTextArea("Output goes here.");
	private JTextArea Body = new JTextArea("Console goes here.");

	public Interface() {
		super("Console");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.Head.setEditable(false);
		this.Body.setEditable(false);
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
		this.Head.setText(text + "\n" + this.Head.getText());
	}

	/**
	 * WRITES TO BODY
	 * <p>
	 * 
	 * @param text
	 *            string
	 */
	public void writeBody(String text) {
		this.Body.setText(text + "\n" + this.Body.getText());
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
		GridBagLayout layout=new GridBagLayout();
		this.Panel.setLayout(layout);
		this.Panel.add(this.Head_t, this.grid(0, 0, 1, 1));
		JScrollPane scr_h = new JScrollPane(this.Head);
		this.Panel.add(scr_h, this.grid(0, 1, 4, 2));
		this.Panel.add(this.Body_t, this.grid(0, 3, 1, 1));
		JScrollPane scr_b = new JScrollPane(this.Body);
		this.Panel.add(scr_b, this.grid(0, 4, 4, 2));
		this.Panel.validate();
		this.setContentPane(this.Panel);
		this.validate();
		Insets insets = this.getInsets();
		this.setSize(400 + insets.left + insets.right, 300 + insets.top + insets.bottom);
		this.setLocationRelativeTo(null);
	}

	private GridBagConstraints grid(int gridx, int gridy, int gridwidth, int gridheight) {
		GridBagConstraints c=new GridBagConstraints();
		c.gridx=gridx;
		c.gridy=gridy;
		c.gridwidth=gridwidth;
		c.gridheight=gridheight;
		c.weightx=1;
		c.weighty=1;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.anchor=GridBagConstraints.FIRST_LINE_START ;
		return c;
	}
	
	private File loader() {
		JFileChooser fc = new JFileChooser();
		int err=fc.showOpenDialog(this);
		if (err==JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		else {
			return null;
		}
	}

}
