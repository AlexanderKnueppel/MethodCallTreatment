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

public class VerifyOptionsField extends JPanel{
	
	public VerifyOptionsField() {

		
		this.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setBackground(UIManager.getColor("Button.select"));
		this.setLayout(new GridLayout(0, 2, 20, 5));
		
		
		
		JCheckBox chckbxContracting = new JCheckBox("contracting");
		chckbxContracting.setBackground(UIManager.getColor("Button.select"));
		chckbxContracting.setSelected(true);
		
		JLabel lblNewLabel_5 = new JLabel("");
		JLabel lblRuns = new JLabel("Runs");
		JTextField textFieldruns = new JTextField();
		textFieldruns.setText("1");
		textFieldruns.setColumns(10);
		JLabel lblNewLabel_6 = new JLabel("");
		JLabel lblNewLabel_7 = new JLabel("");
		final JCheckBox chckbxSetSpecification = new JCheckBox("Set Specification");
		chckbxSetSpecification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxSetSpecification.isSelected()) {
					//textFieldStartPercent.setEnabled(true);
					//textFieldEndPercent.setEnabled(true);
				} else {
					//textFieldStartPercent.setEnabled(false);
					//textFieldEndPercent.setEnabled(false);
				}
			}
		});
		chckbxSetSpecification.setBackground(UIManager.getColor("Button.select"));
		JCheckBox chckbxRandomized = new JCheckBox("Randomized");
		chckbxRandomized.setBackground(UIManager.getColor("Button.select"));
		JTextField textFieldStartPercent = new JTextField();
		textFieldStartPercent.setEnabled(false);
		textFieldStartPercent.setText("0");
		textFieldStartPercent.setColumns(10);
		JTextField textFieldEndPercent = new JTextField();
		textFieldEndPercent.setEnabled(false);
		textFieldEndPercent.setText("100");
		textFieldEndPercent.setColumns(10);
		JLabel lblGranulation = new JLabel("Granulation");
		JComboBox comboBoxGranulation = new JComboBox();
		comboBoxGranulation
				.setModel(new DefaultComboBoxModel(new String[] { "1", "5", "10", "15", "20", "30", "40", "50" }));
		comboBoxGranulation.setSelectedIndex(2);
		JLabel lblNewLabel_8 = new JLabel("");
		JButton btnExecVerify = new JButton("Verify");
		btnExecVerify.setForeground(new Color(0, 128, 0));
		btnExecVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//executeVerify();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		this.add(chckbxContracting);
		this.add(lblNewLabel_5);
		this.add(lblRuns);
		this.add(textFieldruns);
		this.add(lblNewLabel_6);
		this.add(lblNewLabel_7);
		this.add(chckbxSetSpecification);
		this.add(chckbxRandomized);
		this.add(textFieldStartPercent);
		this.add(textFieldEndPercent);
		this.add(lblGranulation);
		this.add(comboBoxGranulation);
		this.add(lblNewLabel_8);
		this.add(btnExecVerify);
		
	}

}
