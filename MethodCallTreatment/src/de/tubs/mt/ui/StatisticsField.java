package de.tubs.mt.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class StatisticsField extends JPanel{
	
	public StatisticsField() {
		
		this.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setBackground(UIManager.getColor("Button.select"));
		this.setLayout(new GridLayout(0, 2, 20, 5));

		JCheckBox chckbxMergeIntoLast = new JCheckBox("Merge");
		chckbxMergeIntoLast.setToolTipText("Merge last results into chart.\nOnly possible for a line-chart.");
		chckbxMergeIntoLast.setBackground(UIManager.getColor("Button.select"));
		
		JComboBox comboBoxChart = new JComboBox();
		comboBoxChart.setModel(new DefaultComboBoxModel(new String[] { "Line", "Bar", "Boxplot" }));
		
		JButton btnClearData = new JButton("Clear Data");
		btnClearData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clearData();
			}
		});
		
		JButton btnShowXychart = new JButton("Show Chart");
		btnShowXychart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//createChart();

			}
		});
		
		JButton btnCreateExcelFile = new JButton("Create Excel File");
		btnCreateExcelFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//createExcelFile();
			}
		});
		
		JLabel label = new JLabel("");
		JLabel label_1 = new JLabel("");
		JLabel label_2 = new JLabel("");
		JLabel label_3 = new JLabel("");
		JLabel label_4 = new JLabel("");
		
		this.add(chckbxMergeIntoLast);
		this.add(comboBoxChart);
		this.add(btnClearData);
		this.add(btnShowXychart);
		this.add(btnCreateExcelFile);
		this.add(label);
		this.add(label_1);
		this.add(label_2);
		this.add(label_3);
		this.add(label_4);
	
	}
}
