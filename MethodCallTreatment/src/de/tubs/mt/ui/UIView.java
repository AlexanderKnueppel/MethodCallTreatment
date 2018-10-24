package de.tubs.mt.ui;

import de.tubs.mt.codeanalyze.PrepMethod;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.BevelBorder;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;


/**
 * The Class UIView.
 */
public class UIView extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The control. */
	private UIControl control;

	// Gui - Elements for programm-generate field
	// ###########################################

	/** The text field search. */
	private JTextField textFieldSearch;

	/**
	 * Gets the text fieldsearch.
	 *
	 * @return the text fieldsearch
	 */
	public JTextField getTextFieldsearch() {
		return textFieldSearch;
	}

	/** The text fieldwidth. */
	private JTextField textFieldwidth;

	/**
	 * Gets the text fieldwidth.
	 *
	 * @return the text fieldwidth
	 */
	public JTextField getTextFieldwidth() {
		return textFieldwidth;
	}

	/** The text fielddepth. */
	private JTextField textFielddepth;

	/**
	 * Gets the text fielddepth.
	 *
	 * @return the text fielddepth
	 */
	public JTextField getTextFielddepth() {
		return textFielddepth;
	}

	/** The chckbx from to. */
	private JCheckBox chckbxFromTo;

	/**
	 * Gets the chckbx from to.
	 *
	 * @return the chckbx from to
	 */
	public JCheckBox getChckbxFromTo() {
		return chckbxFromTo;
	}

	/** The chckbx choose existing java. */
	private JCheckBox chckbxChooseExistingJava;

	/**
	 * Gets the chckbx choose existing java.
	 *
	 * @return the chckbx choose existing java
	 */
	public JCheckBox getChckbxChooseExistingJava() {
		return chckbxChooseExistingJava;
	}

	/** The combo box program. */
	private JComboBox<String> comboBoxProgram;

	/**
	 * Gets the combo box program.
	 *
	 * @return the combo box program
	 */
	public JComboBox<String> getComboBoxProgram() {
		return comboBoxProgram;
	}
	
	/** The combo box program. */
	private JComboBox<String> comboBoxTinyTree;

	/**
	 * Gets the combo box tiny tree.
	 *
	 * @return the combo box tiny tree
	 */
	public JComboBox<String> getComboBoxTinyTree() {
		return comboBoxTinyTree;
	}
	

	private JButton btnSearch;

	
	public JButton getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}
	// Gui - Elements for verify-option-field
	// #######################################

	

	/** The text fieldruns. */
	private JTextField textFieldruns;

	/**
	 * Gets the text fieldruns.
	 *
	 * @return the text fieldruns
	 */
	public JTextField getTextFieldruns() {
		return textFieldruns;
	}

	/** The text field start percent. */
	private JTextField textFieldStartPercent;

	/**
	 * Gets the text field start percent.
	 *
	 * @return the text field start percent
	 */
	public JTextField getTextFieldStartPercent() {
		return textFieldStartPercent;
	}

	/** The text field end percent. */
	private JTextField textFieldEndPercent;

	/**
	 * Gets the text field end percent.
	 *
	 * @return the text field end percent
	 */
	public JTextField getTextFieldEndPercent() {
		return textFieldEndPercent;
	}

	/** The chckbx contracting. */
	private JCheckBox chckbxContracting;

	/**
	 * Gets the chckbx contracting.
	 *
	 * @return the chckbx contracting
	 */
	public JCheckBox getChckbxContracting() {
		return chckbxContracting;
	}

	/** The combo box strategy. */
	private JComboBox<String> comboBoxStrategy;

	/**
	 * Gets the combo box strategy.
	 *
	 * @return the combo box strategy
	 */
	public JComboBox<String> getComboBoxStrategy() {
		return comboBoxStrategy;
	}

	/** The chckbx set specification. */
	private JCheckBox chckbxSetSpecification;

	/**
	 * Gets the chckbx set specification.
	 *
	 * @return the chckbx set specification
	 */
	public JCheckBox getChckbxSetSpecification() {
		return chckbxSetSpecification;
	}

	/** The combo box granulation. */
	private JComboBox<String> comboBoxGranulation;

	/**
	 * Gets the combo box granulation.
	 *
	 * @return the combo box granulation
	 */
	public JComboBox<String> getComboBoxGranulation() {
		return comboBoxGranulation;
	}
	
	
	private JButton btnExecVerify;

	public JButton getBtnExecVerify() {
		return btnExecVerify;
	}

	public void setBtnExecVerify(JButton btnExecVerify) {
		this.btnExecVerify = btnExecVerify;
	}

	// Gui - Elements for statistics-field
	// #######################################

	

	/** The chckbx merge into last. */
	private JCheckBox chckbxMergeIntoLast;

	/**
	 * Gets the chckbx merge into last.
	 *
	 * @return the chckbx merge into last
	 */
	public JCheckBox getChckbxMergeIntoLast() {
		return chckbxMergeIntoLast;
	}

	/** The combo box chart. */
	private JComboBox<String> comboBoxChart;

	/**
	 * Gets the combo box chart.
	 *
	 * @return the combo box chart
	 */
	public JComboBox<String> getComboBoxChart() {
		return comboBoxChart;
	}

	// Gui - Elements for classes-method view
	// #######################################

	/** The text fieldinfo. */
	private JTextField textFieldinfo;

	/**
	 * Gets the text fieldinfo.
	 *
	 * @return the text fieldinfo
	 */
	public JTextField getTextFieldinfo() {
		return textFieldinfo;
	}

	/** The text field starter M. */
	private JTextField textFieldStarterM;

	/**
	 * Gets the text field starter M.
	 *
	 * @return the text field starter M
	 */
	public JTextField getTextFieldStarterM() {
		return textFieldStarterM;
	}

	/** The combo box classes. */
	private JComboBox<String> comboBoxClasses;

	/**
	 * Gets the combo box classes.
	 *
	 * @return the combo box classes
	 */
	public JComboBox<String> getComboBoxClasses() {
		return comboBoxClasses;
	}

	/** The method vector. */
	private Vector<PrepMethod> methodVector = new Vector<PrepMethod>();

	/**
	 * Gets the method vector.
	 *
	 * @return the method vector
	 */
	public Vector<PrepMethod> getMethodVector() {
		return methodVector;
	}

	/** The list. */
	@SuppressWarnings("rawtypes")
	private JList list;

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	@SuppressWarnings("rawtypes")
	public JList getList() {
		return list;
	}

	/** The sel listener. */
	private ListSelectionListener selListener = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			changeStarter();
		}
	};

	/**
	 * Gets the list selection listener.
	 *
	 * @return the list selection listener
	 */
	public ListSelectionListener getListSelectionListener() {
		return selListener;
	}

	/** The lbl specification. */
	private JLabel lblSpecification;

	/**
	 * Gets the lbl specification.
	 *
	 * @return the lbl specification
	 */
	public JLabel getLblSpecification() {
		return lblSpecification;
	}

	// Gui - Elements for output view
	// #######################################

	/** The output text. Static - changes by LogObserver class */
	public static JTextArea outputText;
	
	/** The method text. Static */
	public JTextArea methodBodyText;
	
	/** The Tab of method/output */
	private JTabbedPane tabpane;
	

	/**
	 * Instantiates a new UI view.
	 */
	public UIView() {
		control = new UIControl(this);
		this.setSize(1000, 665);
		this.setLocation(400, 100);
		this.setTitle("Method Call Treatment");
		getContentPane().setBackground(SystemColor.infoText);

		JPanel mainControlPanel = new JPanel();
		JSplitPane splitPanel = new JSplitPane();
		splitPanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		mainControlPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelProperties = new JPanel();
		panelProperties.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelProperties.setBackground(UIManager.getColor("Button.foreground"));
		panelProperties.setLayout(new GridLayout(0, 1, 5, 5));
		panelProperties.add(getProgrammGenerateField());
		panelProperties.add(getVerifyOptionsField());
		panelProperties.add(getStatisticsField());
		
		mainControlPanel.add(panelProperties);
		mainControlPanel.add(getViewMethodsField());

		splitPanel.setLeftComponent(mainControlPanel);
		splitPanel.setRightComponent(getOutputArea());
		getContentPane().add(splitPanel);

	}

	/**
	 * Gets the programm-generate field # Panel with GridLayout.
	 *
	 * @return the programm-generate field
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JPanel getProgrammGenerateField() {
		JPanel ProgrammGenerateField = new JPanel();

		ProgrammGenerateField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ProgrammGenerateField.setBackground(UIManager.getColor("Button.shadow"));
		ProgrammGenerateField.setLayout(new GridLayout(0, 2, 20, 5));

		JLabel lblCreate = new JLabel("Create");

		comboBoxProgram = new JComboBox<String>();
		comboBoxProgram.setModel(new DefaultComboBoxModel(new String[] { "Add", "Bubblesort" }));

		comboBoxTinyTree = new JComboBox<String>();
		comboBoxTinyTree.setModel(new DefaultComboBoxModel(new String[] { "Tree", "Tiny" }));
		
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
		chckbxChooseExistingJava.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            	enableFileSearch(e);
            }
        });
		
		chckbxChooseExistingJava.setBackground(UIManager.getColor("Button.select"));

		textFieldSearch = new JTextField();
		textFieldSearch.setEditable(false);
		textFieldSearch.setColumns(10);

		btnSearch = new JButton("Search");
		btnSearch.setEnabled(false);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.chooseInputFile();
				;
			}
		});

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setForeground(new Color(0, 128, 0));
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					control.executeGenerate();
					textFieldStarterM.setText("");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		ProgrammGenerateField.add(new JLabel(""));
		ProgrammGenerateField.add(comboBoxTinyTree);
		ProgrammGenerateField.add(lblCreate);
		ProgrammGenerateField.add(comboBoxProgram);
		ProgrammGenerateField.add(lblWidth);
		ProgrammGenerateField.add(textFieldwidth);
		ProgrammGenerateField.add(chckbxFromTo);
		ProgrammGenerateField.add(textFielddepth);
		ProgrammGenerateField.add(new JLabel(""));
		ProgrammGenerateField.add(new JLabel(""));
		ProgrammGenerateField.add(btnSearch);
		ProgrammGenerateField.add(chckbxChooseExistingJava);
		ProgrammGenerateField.add(textFieldSearch);
		ProgrammGenerateField.add(btnGenerate);
		
		return ProgrammGenerateField;

	}

	/**
	 * Gets the verify-options field. # Panel with GridLayout
	 *
	 * @return the verify-options field
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		comboBoxStrategy = new JComboBox<String>();
		comboBoxStrategy.setModel(new DefaultComboBoxModel(new String[] { "Straight", "Randomized" }));

		JLabel lblGranulation = new JLabel("Granulation");
		comboBoxGranulation = new JComboBox<String>();
		comboBoxGranulation
				.setModel(new DefaultComboBoxModel(new String[] { "1", "5", "10", "15", "20", "30", "40", "50" }));
		comboBoxGranulation.setSelectedIndex(2);
		btnExecVerify = new JButton("Verify");
		btnExecVerify.setEnabled(false);
		btnExecVerify.setForeground(new Color(0, 128, 0));
		btnExecVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabpane.setSelectedIndex(1);
				outputText.setText("");
				try {
					/**
					 * We use a SwingWorker cause the verfication
					 * is a time-consuming computation and will
					 * freeze the gui for (maybe) a long time
					 */
					new SwingWorker<String, Object>() {

						@Override
						protected String doInBackground() throws Exception {
							control.executeVerify();
							return null;
						}
					}.execute();
				} catch (Exception e1) {
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
		VerifyOptionsField.add(comboBoxStrategy);
		VerifyOptionsField.add(textFieldStartPercent);
		VerifyOptionsField.add(textFieldEndPercent);
		VerifyOptionsField.add(lblGranulation);
		VerifyOptionsField.add(comboBoxGranulation);
		VerifyOptionsField.add(new JLabel(""));
		VerifyOptionsField.add(btnExecVerify);

		return VerifyOptionsField;

	}

	/**
	 * Gets the statistics-field. # Panel with GridLayout
	 *
	 * @return the statistics-field
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
	 * Gets the view methods field. # Panel with GridLayout
	 *
	 * @return the view methods field
	 */
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
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
					setText(nextMethod.name + " " + nextMethod.parameters);
					if (nextMethod.jml) {
						setBackground(Color.GREEN);
					} else {
						setBackground(Color.RED);
					}
					if (isSelected) {
						methodBodyText.setText(nextMethod.body);
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

	/**
	 * Gets the output and method-body area.
	 *
	 * @return the output area
	 */
	private JTabbedPane getOutputArea() {
		tabpane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT );
		
		outputText = new JTextArea();
		outputText.setFont(new Font("Monospaced", Font.PLAIN, 12));
		outputText.setEditable(false);
		outputText.setForeground(Color.WHITE);
		outputText.setBackground(Color.BLACK);
		JScrollPane outputPanel = new JScrollPane(outputText);
		
		methodBodyText = new JTextArea();
		methodBodyText.setEditable(false);
		methodBodyText.setFont(new Font("Monospaced", Font.PLAIN, 12));
		
		
		tabpane.addTab("Method", methodBodyText);
		tabpane.addTab("Output", outputPanel);

		return tabpane;
	}

	/**
	 * Change starter.
	 */
	private void changeStarter() {
		PrepMethod pm = (PrepMethod) list.getModel().getElementAt(list.getSelectedIndex());
		textFieldStarterM.setText(pm.name);
		textFieldinfo.setText("");
	}
	
	
	/**
	 * 
	 */
	private void enableFileSearch(ItemEvent e){
		if(e.getStateChange() == ItemEvent.SELECTED) {
			btnSearch.setEnabled(true);
		} else {
			btnSearch.setEnabled(false);
		}
	}

}
