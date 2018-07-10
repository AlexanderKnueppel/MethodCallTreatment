package de.tubs.mt.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class ProgrammGenerateField extends JPanel{
	
	
	public ProgrammGenerateField() {
		this.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setBackground(UIManager.getColor("Button.shadow"));
		this.setLayout(new GridLayout(0, 2, 20, 5));

		JLabel lblCreate = new JLabel("Create");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Add", "Bubblesort" }));
		
		JLabel lblWidth = new JLabel("Width");
		
		JTextField textFieldwidth = new JTextField();
		textFieldwidth.setText("2");
		textFieldwidth.setColumns(10);
		
		JCheckBox chckbxFromTo = new JCheckBox("1 to Depth");
		chckbxFromTo.setBackground(UIManager.getColor("Button.select"));
		
		JTextField textFielddepth = new JTextField();
		textFielddepth.setText("2");
		textFielddepth.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		
		JLabel lblNewLabel_2 = new JLabel("");
		
		JCheckBox chckbxChooseExistingJava = new JCheckBox("Choose File");
		chckbxChooseExistingJava.setBackground(UIManager.getColor("Button.select"));
		
		JLabel lblNewLabel_3 = new JLabel("");
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chooseInputFile();
			}
		});
		
		JTextField textFieldsearch = new JTextField();
		textFieldsearch.setEditable(false);
		textFieldsearch.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("");
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setForeground(new Color(0, 128, 0));
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//executeGenerate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		this.add(lblCreate);
		this.add(comboBox);
		this.add(lblWidth);
		this.add(textFieldwidth);
		this.add(chckbxFromTo);
		this.add(textFielddepth);
		this.add(lblNewLabel);
		this.add(lblNewLabel_2);
		this.add(chckbxChooseExistingJava);
		this.add(lblNewLabel_3);
		this.add(btnSearch);
		this.add(textFieldsearch);
		this.add(lblNewLabel_4);
		this.add(btnGenerate);
		
	}

}
