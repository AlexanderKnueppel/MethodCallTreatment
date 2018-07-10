package de.tubs.mt.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.tubs.mt.codeanalyze.PrepMethod;

public class ViewMethodsField extends JPanel{
	
	private ListSelectionListener selListener = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			//changeStarter();
		}
	};
	
	public ViewMethodsField() {
		
		this.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		this.setBackground(UIManager.getColor("EditorPane.foreground"));
		this.setLayout(new GridLayout(3, 1, 0, 0));
		
		Vector v = new Vector<PrepMethod>();
		JList list = new JList(v);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		list.addListSelectionListener(selListener);

		JScrollPane scrollPane = new JScrollPane(list);
		this.add(scrollPane);
		list.setCellRenderer(new DefaultListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (value instanceof PrepMethod) {
					PrepMethod nextMethod = (PrepMethod) value;
					setText(nextMethod.name);
					if (nextMethod.jml) {
						setBackground(Color.GREEN);
					} else {
						setBackground(Color.RED);
					}
					if (isSelected) {
						setBackground(getBackground().darker());
					}
				} else {
					setText("wrong instance");
				}
				return c;
			}

		});
		
		
		
		JLabel lblSpecification = new JLabel("");
		lblSpecification.setForeground(UIManager.getColor("Button.background"));
		
		JPanel panelClassMethod = new JPanel();
		panelClassMethod.setBackground(UIManager.getColor("Button.foreground"));
		panelClassMethod.setLayout(new GridLayout(5, 1, 20, 5));
		
		JLabel lblClass = new JLabel("Class:");
		lblClass.setHorizontalAlignment(SwingConstants.CENTER);
		lblClass.setBackground(UIManager.getColor("Button.foreground"));
		panelClassMethod.add(lblClass);
		lblClass.setForeground(UIManager.getColor("Button.background"));
		
		JComboBox<String> comboBoxClasses = new JComboBox<String>();
		panelClassMethod.add(comboBoxClasses);
		comboBoxClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//setClassDependedMethods();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Starter-Method:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelClassMethod.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(UIManager.getColor("text"));
		
		JTextField textFieldStarterM = new JTextField();
		panelClassMethod.add(textFieldStarterM);
		textFieldStarterM.setEditable(false);
		textFieldStarterM.setColumns(10);
		JTextField textFieldinfo = new JTextField();
		panelClassMethod.add(textFieldinfo);
		textFieldinfo.setForeground(Color.WHITE);
		textFieldinfo.setBackground(Color.BLACK);
		textFieldinfo.setEnabled(false);
		textFieldinfo.setEditable(false);
		textFieldinfo.setColumns(10);
		
		
		this.add(lblSpecification);
		this.add(panelClassMethod);
			
	}

}
