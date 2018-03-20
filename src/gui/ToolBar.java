package gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolBar extends JPanel implements ActionListener{
	private JButton saveButton;
	private JButton refreshButton;
	private ToolbarListener listener;
	public ToolBar() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBorder(BorderFactory.createEtchedBorder());
		saveButton = new JButton("Save");
		refreshButton = new JButton("Refresh");
		
		saveButton.addActionListener(this);
		refreshButton.addActionListener(this);
		
		add(saveButton);
		add(refreshButton);
	}
	
	public void setToolbarListener(ToolbarListener listener) {
		this.listener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		if(clicked==saveButton) {
			if(listener!=null) {
				listener.saveEventOccured();
			}
		}
		else if(clicked==refreshButton){
			if(listener!=null) {
				listener.refreshEventOccured();
			}
		}
	}

}
