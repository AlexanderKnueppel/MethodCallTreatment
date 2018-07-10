package de.tubs.mt.ui;


import de.tubs.mt.codeanalyze.PrepMethod;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.BevelBorder;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;


public class UIView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UIControl control;
	
	/** The text field search. */
	private JTextField textFieldSearch;
	public JTextField getTextFieldsearch() {
		return textFieldSearch;
	}
	
	/** The text fieldruns. */
	private JTextField textFieldruns;
	public JTextField getTextFieldruns() {
		return textFieldruns;
	}

	/** The text fieldwidth. */
	private JTextField textFieldwidth;
	public JTextField getTextFieldwidth() {
		return textFieldwidth;
	}

	/** The text fielddepth. */
	private JTextField textFielddepth;
	public JTextField getTextFielddepth() {
		return textFielddepth;
	}
	
	/** The chckbx from to. */
	private JCheckBox chckbxFromTo;
	public JCheckBox getChckbxFromTo() {
		return chckbxFromTo;
	}

	
	/** The text fieldinfo. */
	private JTextField textFieldinfo;
	public JTextField getTextFieldinfo() {
		return textFieldinfo;
	}

	/** The text field starter M. */
	private JTextField textFieldStarterM;
	public JTextField getTextFieldStarterM() {
		return textFieldStarterM;
	}

	/** The text field start percent. */
	private JTextField textFieldStartPercent;
	public JTextField getTextFieldStartPercent() {
		return textFieldStartPercent;
	}

	/** The text field end percent. */
	private JTextField textFieldEndPercent;
	public JTextField getTextFieldEndPercent() {
		return textFieldEndPercent;
	}

	/** The chckbx contracting. */
	private JCheckBox chckbxContracting;
	public JCheckBox getChckbxContracting() {
		return chckbxContracting;
	}

	/** The chckbx randomized. */
	private JCheckBox chckbxRandomized;
	public JCheckBox getChckbxRandomized() {
		return chckbxRandomized;
	}

	
	/** The chckbx set specification. */
	private JCheckBox chckbxSetSpecification;
	public JCheckBox getChckbxSetSpecification() {
		return chckbxSetSpecification;
	}

	/** The chckbx choose existing java. */
	private JCheckBox chckbxChooseExistingJava;
	public JCheckBox getChckbxChooseExistingJava() {
		return chckbxChooseExistingJava;
	}

	/** The chckbx merge into last. */
	private JCheckBox chckbxMergeIntoLast;
	public JCheckBox getChckbxMergeIntoLast() {
		return chckbxMergeIntoLast;
	}

	/** The combo box program. */
	private JComboBox<String> comboBoxProgram;
	public JComboBox<String> getComboBoxProgram() {
		return comboBoxProgram;
	}
	
	/** The combo box granulation. */
	private JComboBox<String> comboBoxGranulation;
	public JComboBox<String> getComboBoxGranulation() {
		return comboBoxGranulation;
	}

	/** The combo box chart. */
	private JComboBox<String> comboBoxChart;
	public JComboBox<String> getComboBoxChart() {
		return comboBoxChart;
	}

	/** The combo box classes. */
	private JComboBox<String> comboBoxClasses;
	public JComboBox<String> getComboBoxClasses() {
		return comboBoxClasses;
	}

	/** The method vector. */
	private Vector<PrepMethod> methodVector = new Vector<PrepMethod>();
	public Vector<PrepMethod> getMethodVector() {
		return methodVector;
	}

	
	/** The list. */
	private JList list;
	public JList getList() {
		return list;
	}
	
	/** The sel listener. */
	private ListSelectionListener selListener = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			changeStarter();
		}
	};
	public ListSelectionListener getListSelectionListener() {
		return selListener;
	}

	
	/** The lbl specification. */
	private JLabel lblSpecification;
	public JLabel getLblSpecification() {
		return lblSpecification;
	}
	
	
	
	/**
	 * Instantiates a new UI view.
	 */
	public UIView() {
		control = new UIControl(this);
		this.setSize(1000, 665);
		this.setLocation(200, 100);
		this.setTitle("Method Call Treatment");
		getContentPane().setBackground(SystemColor.infoText);
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panelProperties = new JPanel();
		panelProperties.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelProperties.setBackground(UIManager.getColor("Button.foreground"));
		panelProperties.setLayout(new GridLayout(0, 1, 5, 5));

		panelProperties.add(getProgrammGenerateField());
		panelProperties.add(getVerifyOptionsField());
		panelProperties.add(getStatisticsField());

		getContentPane().add(panelProperties);
		getContentPane().add(getViewMethodsField());

	}

	/**
	 * Gets the programm-generate field.
	 *
	 * @return the programm-generate field
	 */
	private JPanel getProgrammGenerateField() {
		JPanel ProgrammGenerateField = new JPanel();

		ProgrammGenerateField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ProgrammGenerateField.setBackground(UIManager.getColor("Button.shadow"));
		ProgrammGenerateField.setLayout(new GridLayout(0, 2, 20, 5));

		JLabel lblCreate = new JLabel("Create");

		comboBoxProgram = new JComboBox<String>();
		comboBoxProgram.setModel(new DefaultComboBoxModel(new String[] { "Add", "Bubblesort" }));

		JLabel lblWidth = new JLabel("Width");

		textFieldwidth = new JTextField();
		textFieldwidth.setText("2");
		textFieldwidth.setColumns(10);

		chckbxFromTo = new JCheckBox("1 to Depth");
		chckbxFromTo.setBackground(UIManager.getColor("Button.select"));

		textFielddepth = new JTextField();
		textFielddepth.setText("2");
		textFielddepth.setColumns(10);


		chckbxChooseExistingJava = new JCheckBox("Choose File");
		chckbxChooseExistingJava.setBackground(UIManager.getColor("Button.select"));

		textFieldSearch = new JTextField();
		textFieldSearch.setEditable(false);
		textFieldSearch.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.chooseInputFile();;
			}
		});

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setForeground(new Color(0, 128, 0));
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					control.executeGenerate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		ProgrammGenerateField.add(lblCreate);
		ProgrammGenerateField.add(comboBoxProgram);
		ProgrammGenerateField.add(lblWidth);
		ProgrammGenerateField.add(textFieldwidth);
		ProgrammGenerateField.add(chckbxFromTo);
		ProgrammGenerateField.add(textFielddepth);
		ProgrammGenerateField.add(new JLabel(""));
		ProgrammGenerateField.add(new JLabel(""));
		ProgrammGenerateField.add(chckbxChooseExistingJava);
		ProgrammGenerateField.add(new JLabel(""));
		ProgrammGenerateField.add(btnSearch);
		ProgrammGenerateField.add(textFieldSearch);
		ProgrammGenerateField.add(new JLabel(""));
		ProgrammGenerateField.add(btnGenerate);

		return ProgrammGenerateField;

	}


	/**
	 * Gets the verify-options field.
	 *
	 * @return the verify-options field
	 */
	private JPanel getVerifyOptionsField() {

		JPanel VerifyOptionsField = new JPanel();
		VerifyOptionsField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		VerifyOptionsField.setBackground(UIManager.getColor("Button.select"));
		VerifyOptionsField.setLayout(new GridLayout(0, 2, 20, 5));

		chckbxContracting = new JCheckBox("contracting");
		chckbxContracting.setBackground(UIManager.getColor("Button.select"));
		chckbxContracting.setSelected(true);

		JLabel lblRuns = new JLabel("Runs");
		textFieldruns = new JTextField("1");
		textFieldStartPercent = new JTextField("0");
		textFieldStartPercent.setEnabled(false);
		textFieldEndPercent = new JTextField("100");
		textFieldEndPercent.setEnabled(false);


		chckbxSetSpecification = new JCheckBox("Set Specification");
		chckbxSetSpecification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxSetSpecification.isSelected()) {
					textFieldStartPercent.setEnabled(true);
					textFieldEndPercent.setEnabled(true);
				} else {
					textFieldStartPercent.setEnabled(false);
					textFieldEndPercent.setEnabled(false);
				}
			}
		});
		chckbxSetSpecification.setBackground(UIManager.getColor("Button.select"));
		chckbxRandomized = new JCheckBox("Randomized");
		chckbxRandomized.setBackground(UIManager.getColor("Button.select"));
		
		JLabel lblGranulation = new JLabel("Granulation");
		comboBoxGranulation = new JComboBox<String>();
		comboBoxGranulation.setModel(new DefaultComboBoxModel(new String[] { "1", "5", "10", "15", "20", "30", "40", "50" }));
		comboBoxGranulation.setSelectedIndex(2);
		JButton btnExecVerify = new JButton("Verify");
		btnExecVerify.setForeground(new Color(0, 128, 0));
		btnExecVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					control.executeVerify();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		VerifyOptionsField.add(chckbxContracting);
		VerifyOptionsField.add(new JLabel(""));
		VerifyOptionsField.add(lblRuns);
		VerifyOptionsField.add(textFieldruns);
		VerifyOptionsField.add(new JLabel(""));
		VerifyOptionsField.add(new JLabel(""));
		VerifyOptionsField.add(chckbxSetSpecification);
		VerifyOptionsField.add(chckbxRandomized);
		VerifyOptionsField.add(textFieldStartPercent);
		VerifyOptionsField.add(textFieldEndPercent);
		VerifyOptionsField.add(lblGranulation);
		VerifyOptionsField.add(comboBoxGranulation);
		VerifyOptionsField.add(new JLabel(""));
		VerifyOptionsField.add(btnExecVerify);

		return VerifyOptionsField;

	}

	
	/**
	 * Gets the statistics-field.
	 *
	 * @return the statistics-field
	 */
	private JPanel getStatisticsField() {

		JPanel StatisticsField = new JPanel();

		StatisticsField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		StatisticsField.setBackground(UIManager.getColor("Button.select"));
		StatisticsField.setLayout(new GridLayout(0, 2, 20, 5));

		chckbxMergeIntoLast = new JCheckBox("Merge");
		chckbxMergeIntoLast.setToolTipText("Merge last results into chart.\nOnly possible for a line-chart.");
		chckbxMergeIntoLast.setBackground(UIManager.getColor("Button.select"));

		comboBoxChart = new JComboBox<String>();
		comboBoxChart.setModel(new DefaultComboBoxModel(new String[] { "Line", "Bar", "Boxplot" }));

		JButton btnClearData = new JButton("Clear Data");
		btnClearData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.clearData();
			}
		});

		JButton btnShowXychart = new JButton("Show Chart");
		btnShowXychart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.createChart();

			}
		});

		JButton btnCreateExcelFile = new JButton("Create Excel File");
		btnCreateExcelFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.createExcelFile();
			}
		});

		StatisticsField.add(chckbxMergeIntoLast);
		StatisticsField.add(comboBoxChart);
		StatisticsField.add(btnClearData);
		StatisticsField.add(btnShowXychart);
		StatisticsField.add(btnCreateExcelFile);
		StatisticsField.add(new JLabel(""));
		StatisticsField.add(new JLabel(""));
		StatisticsField.add(new JLabel(""));
		StatisticsField.add(new JLabel(""));
		StatisticsField.add(new JLabel(""));

		return StatisticsField;
	}

	
	/**
	 * Gets the view methods field.
	 *
	 * @return the view methods field
	 */
	private JPanel getViewMethodsField() {
		JPanel ViewMethodsField = new JPanel();

		ViewMethodsField.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ViewMethodsField.setBackground(UIManager.getColor("EditorPane.foreground"));
		ViewMethodsField.setLayout(new GridLayout(3, 1, 0, 0));

		Vector<PrepMethod> v = new Vector<PrepMethod>();
		list = new JList(v);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		list.addListSelectionListener(selListener);

		JScrollPane scrollPane = new JScrollPane(list);
		ViewMethodsField.add(scrollPane);
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

		lblSpecification = new JLabel("");
		lblSpecification.setForeground(UIManager.getColor("Button.background"));

		JPanel panelClassMethod = new JPanel();
		panelClassMethod.setBackground(UIManager.getColor("Button.foreground"));
		panelClassMethod.setLayout(new GridLayout(5, 1, 20, 5));

		JLabel lblClass = new JLabel("Class:");
		lblClass.setHorizontalAlignment(SwingConstants.CENTER);
		lblClass.setBackground(UIManager.getColor("Button.foreground"));
		panelClassMethod.add(lblClass);
		lblClass.setForeground(UIManager.getColor("Button.background"));

		comboBoxClasses = new JComboBox<String>();
		panelClassMethod.add(comboBoxClasses);
		comboBoxClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.setClassDependedMethods();
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Starter-Method:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelClassMethod.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(UIManager.getColor("text"));

		textFieldStarterM = new JTextField();
		panelClassMethod.add(textFieldStarterM);
		textFieldStarterM.setEditable(false);
		textFieldStarterM.setColumns(10);
		textFieldinfo = new JTextField();
		panelClassMethod.add(textFieldinfo);
		textFieldinfo.setForeground(Color.WHITE);
		textFieldinfo.setBackground(Color.BLACK);
		textFieldinfo.setEnabled(false);
		textFieldinfo.setEditable(false);
		textFieldinfo.setColumns(10);

		ViewMethodsField.add(lblSpecification);
		ViewMethodsField.add(panelClassMethod);

		return ViewMethodsField;

	}
	
	private void changeStarter() {
		PrepMethod pm = (PrepMethod) list.getModel().getElementAt(list.getSelectedIndex());
		textFieldStarterM.setText(pm.name);
		textFieldinfo.setText("");
	}

}
