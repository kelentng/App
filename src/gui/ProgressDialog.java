package gui;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;

public class ProgressDialog extends JDialog {
	private JButton cancelButton;
	private JProgressBar progressBar;
	public ProgressDialog(Window parent) {
		super(parent,"Messages Downloading...",ModalityType.APPLICATION_MODAL);
		cancelButton = new JButton("Cancel");
		progressBar = new JProgressBar();
		setLayout(new FlowLayout());
		Dimension size = cancelButton.getPreferredSize();
		size.width = 400;
		progressBar.setPreferredSize(size);
		add(progressBar);
		add(cancelButton);
		setSize(400,200);
		pack();
		setLocationRelativeTo(parent);
	}
	
	public void setMaximum(int value) {
		progressBar.setMaximum(value);
	}
	
	public void setValue(int value) {
		progressBar.setValue(value);
	}

	
}
