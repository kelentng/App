package gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel{
	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JList ageList;
	private JComboBox empCombo;
	private JCheckBox citizenCheck;
	private JLabel taxLabel;
	private JTextField taxField;
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;
	private JButton okBtn;
	private FormListener listener;
	
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width=250;
		setPreferredSize(dim);
		setMinimumSize(dim);
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));
		
		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		ageList = new JList();
		empCombo = new  JComboBox();
		citizenCheck = new JCheckBox();
		taxLabel = new JLabel("Tax ID: ");
		taxField = new JTextField(10);
		maleRadio = new JRadioButton("male");
		femaleRadio = new JRadioButton("female");
		genderGroup = new ButtonGroup();
		maleRadio.setSelected(true);
		maleRadio.setActionCommand("male");
		femaleRadio.setActionCommand("female");
		okBtn = new JButton("OK");
		
		//setup Mnemorics
		okBtn.setMnemonic(KeyEvent.VK_O);
		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameField);

		
		//setup JList
		ageList.setPreferredSize(new Dimension(116,70));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		DefaultListModel ageModel = new DefaultListModel();
		ageList.setModel(ageModel);
		ageModel.addElement(new AgeCategory(0,"Under 18"));
		ageModel.addElement(new AgeCategory(1,"18 to 65"));
		ageModel.addElement(new AgeCategory(0,"65 or over"));
		ageList.setSelectedIndex(0);
		
		//setup Combobox
		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("employed");
		empModel.addElement("self-employed");
		empModel.addElement("unemployed");
		empCombo.setModel(empModel);
		empCombo.setSelectedIndex(0);
		empCombo.setEditable(true);
		
		//setup CheckBox
		taxLabel.setEnabled(false);
		taxField.setEnabled(false);
		citizenCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isChecked = citizenCheck.isSelected();
				taxLabel.setEnabled(isChecked);
				taxField.setEnabled(isChecked);
				
			}
			
		});
		
		//setup Radio
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		
		
		
		
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategory ageCat = (AgeCategory)ageList.getSelectedValue();
				String empCat = (String)empCombo.getSelectedItem();
				boolean usCitizen = citizenCheck.isSelected();
				String taxId = taxField.getText();
				String genderCommand = genderGroup.getSelection().getActionCommand();
				FormEvent ev = new FormEvent(this, name, occupation, ageCat.getId(), empCat, usCitizen, taxId, genderCommand);
				if(listener!=null) {
					listener.formEventOccurred(ev);
				}
			}
			
		});
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		
		
		///////////////////Dòng 1 //////////////////////////////
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;

		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(nameLabel,gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(nameField,gc);

		///////////////////Dòng 2 //////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(occupationLabel,gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(occupationField,gc);
		
		///////////////////Dòng 3 //////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(new JLabel("Age: "),gc);
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(ageList,gc);
		
		///////////////////Dòng tiep//////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(new JLabel("Employment: "),gc);
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(empCombo,gc);
		
		///////////////////Dòng tiep//////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(new JLabel("US citizen: "),gc);
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(citizenCheck,gc);
		
		///////////////////Dòng tiep//////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(taxLabel,gc);
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(taxField,gc);
		
		///////////////////Dòng tiep//////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(new JLabel("Gender: "),gc);
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(maleRadio,gc);
		
		///////////////////Dòng tiep//////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;


		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(femaleRadio,gc);
		
		///////////////////Dòng OK //////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 2.0;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(okBtn,gc);
		
	}
	
	public void setFormListener(FormListener listener) {
		this.listener = listener;
	}
}

class AgeCategory{
	private int id;
	private String text;
	public AgeCategory(int id, String text) {
		this.id = id;
		this.text = text;
	}
	public String toString() {
		return text;
	}
	public int getId() {
		return id;
	}
}
