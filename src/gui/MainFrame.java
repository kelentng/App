package gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.Controller;

public class MainFrame extends JFrame {
	private ToolBar toolBar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private TablePanel tablePanel;
	private Controller controller;
	private PrefsDialog prefsDialog;
	private Preferences prefs;
	public MainFrame() {
		super("Hello World");
		setSize(600,500);
		setLayout(new BorderLayout());
		setJMenuBar(createMenuBar());
		
		toolBar = new ToolBar();
		formPanel = new FormPanel();
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new PersonFileFilter());
		tablePanel = new TablePanel();
		controller = new Controller();
		tablePanel.setData(controller.getPeople());
		prefsDialog = new PrefsDialog(this);
		prefs = Preferences.userRoot().node("db");
		
		
		add(toolBar,BorderLayout.NORTH);
		add(formPanel,BorderLayout.WEST);
		add(tablePanel,BorderLayout.CENTER);
		
		toolBar.setStringListener(new StringListener() {

			@Override
			public void TextEmitted(String text) {
			}
			
		});
		
		formPanel.setFormListener(new FormListener() {

			@Override
			public void formEventOccurred(FormEvent e) {
				controller.addPerson(e);
				tablePanel.refresh();
			}
			
		});
		
		tablePanel.setPersonTableListener(new PersonTableListener() {

			@Override
			public void rowDeleted(int row) {
				controller.removePerson(row);
			}
			
		});
		
		prefsDialog.setPrefsListener(new PrefsListener() {

			@Override
			public void preferencesSet(String user, String password, int port) {
				prefs.put("user", user);
				prefs.put("password", password);
				prefs.putInt("port", port);
			}
			
		});
		String user = prefs.get("user", "");
		String password = prefs.get("password", "");
		int port = prefs.getInt("port", 3306);
		prefsDialog.setDefaults(user, password, port);
				
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem importdataItem = new JMenuItem("Import Data...");
		JMenuItem exportdataItem = new JMenuItem("Export Data...");
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(importdataItem);
		fileMenu.add(exportdataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Show Form");
		showFormItem.setSelected(true);
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		JMenuItem prefsItem = new JMenuItem("Preferences...");
		windowMenu.add(prefsItem);
		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		showFormItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();
				formPanel.setVisible(menuItem.isSelected());
			}
			
		});
		
		importdataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(fileChooser.showOpenDialog(MainFrame.this)==JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
		
		exportdataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(fileChooser.showSaveDialog(MainFrame.this)==JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e) {
						JOptionPane.showMessageDialog(MainFrame.this, "Could not save data to file.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
		
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
		
		prefsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				prefsDialog.setVisible(true);
			}
			
		});
		
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		importdataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
		exportdataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		return menuBar;
	}
}
