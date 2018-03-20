package gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
		saveButton.setIcon(createIcon("images/Save16.gif"));
		refreshButton.setIcon(createIcon("images/Refresh16.gif"));
		
		saveButton.addActionListener(this);
		refreshButton.addActionListener(this);
		
		add(saveButton);
		add(refreshButton);
	}
	
	private ImageIcon createIcon(String path) {
		URL url = getClass().getResource(path);
		if(url==null) {
			System.err.println("Unable to load image: "+path);
		}
		ImageIcon icon = new ImageIcon(url);
		return icon;
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
