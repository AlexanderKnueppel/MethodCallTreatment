package de.tubs.mt.launch;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;


import de.tubs.mt.codeanalyze.ClassMethodHandler;
import de.tubs.mt.codeanalyze.PrepClasses;
import de.tubs.mt.codeanalyze.PrepMethod;
import de.tubs.mt.programfactory.IProgram;
import de.tubs.mt.programfactory.ProgramFactory;
import de.tubs.mt.result.ChartResults;
import de.tubs.mt.result.ExcelFile;
import de.tubs.mt.result.ResultHandler;
import de.tubs.mt.result.XYChart;
import de.tubs.mt.result.XYChart.Chart;

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


import java.awt.SystemColor;

public class UILaunch extends JFrame {


	private JTextField textFieldsearch;
	private JTextField textFieldruns;
	private JTextField textFieldwidth;
	private JTextField textFielddepth;
	private JTextField textFieldinfo;
	private JTextField textFieldStarterM;
	private JTextField textFieldStartPercent;
	private JLabel lblTo;
	private JLabel lblSpecification;
	private JLabel lblNewLabel_1;
	private JCheckBox chckbxContracting;
	private JCheckBox chckbxFromTo;
	private JCheckBox chckbxRandomized;
	private JCheckBox chckbxMergeIntoLast;
	private JCheckBox chckbxSetSpecification;
	private JCheckBox chckbxChooseExistingJava;
	private JButton btnSearch;
	private JButton btnGenerate;
	private JComboBox comboBox;
	private JComboBox comboBoxGranulation;
	private JComboBox comboBoxChart;
	private JComboBox comboBoxClasses;
	private JPanel panel_2;
	private Vector methodVector = new Vector<PrepMethod>();

	private File choosenFile;
	private JList list;
	private ListSelectionListener selListener = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			changeStarter();
		}
	};

	private XYChart xychart;
	private JTextField textFieldEndPercent;
	private List<PrepMethod> methodList = new ArrayList<PrepMethod>();
	private List<PrepClasses> classList = new ArrayList<PrepClasses>();
	private ArrayList<List<ChartResults>> resultLists = new ArrayList<List<ChartResults>>();
	private List<String> propertiesList =  new ArrayList<String>();

	
	private IProgram program;
	
	public UILaunch() {
		this.setSize(1027, 626);
		this.setLocation(200, 100);
		getContentPane().setBackground(SystemColor.infoText);

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.darkShadow"));
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("Button.darkShadow"));
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		chckbxContracting = new JCheckBox("contracting");
		chckbxContracting.setBackground(UIManager.getColor("Button.darkShadow"));
		chckbxContracting.setSelected(true);
		textFieldruns = new JTextField();
		textFieldruns.setText("1");
		textFieldruns.setColumns(10);

		JLabel lblRuns = new JLabel("Runs");

		textFieldStartPercent = new JTextField();
		textFieldStartPercent.setEnabled(false);
		textFieldStartPercent.setText("0");
		textFieldStartPercent.setColumns(10);

		lblTo = new JLabel("to");

		textFieldEndPercent = new JTextField();
		textFieldEndPercent.setEnabled(false);
		textFieldEndPercent.setText("100");
		textFieldEndPercent.setColumns(10);

		JLabel lblGranulation = new JLabel("Granulation");

		comboBoxGranulation = new JComboBox();
		comboBoxGranulation
				.setModel(new DefaultComboBoxModel(new String[] { "1", "5", "10", "15", "20", "30", "40", "50" }));
		comboBoxGranulation.setSelectedIndex(2);

		chckbxRandomized = new JCheckBox("Randomized");
		chckbxRandomized.setBackground(UIManager.getColor("Button.darkShadow"));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(UIManager.getColor("MenuItem.selectionBackground"));
		
		chckbxSetSpecification = new JCheckBox("Set Specification");
		chckbxSetSpecification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxSetSpecification.isSelected()) {
					textFieldStartPercent.setEnabled(true);
					textFieldEndPercent.setEnabled(true);	
				} else {
					textFieldStartPercent.setEnabled(false);
					textFieldEndPercent.setEnabled(false);
				}
			}
		});
		chckbxSetSpecification.setBackground(UIManager.getColor("Button.darkShadow"));

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(chckbxContracting)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(chckbxSetSpecification)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
											.addComponent(textFieldStartPercent, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblTo, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
											.addGap(29)))
									.addPreferredGap(ComponentPlacement.RELATED, 2, Short.MAX_VALUE)
									.addComponent(textFieldEndPercent, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addGap(41))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(15)
									.addComponent(lblGranulation)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(comboBoxGranulation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxRandomized))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(21)
							.addComponent(lblRuns)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldruns, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
					.addGap(65))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(124)
					.addComponent(lblOptions)
					.addContainerGap(840, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblOptions)
									.addGap(64))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(chckbxContracting)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(2)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRuns)
								.addComponent(textFieldruns, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
							.addComponent(chckbxSetSpecification)
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldStartPercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldEndPercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTo)
								.addComponent(chckbxRandomized))
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGranulation)
								.addComponent(comboBoxGranulation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);

		JButton btnShowXychart = new JButton("Show Chart");
		btnShowXychart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createChart();

			}
		});

		JButton btnClearData = new JButton("Clear Data");
		btnClearData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearData();
			}
		});

		chckbxMergeIntoLast = new JCheckBox("Merge");
		chckbxMergeIntoLast.setToolTipText("Merge last results into chart.\nOnly possible for a line-chart.");
		chckbxMergeIntoLast.setBackground(UIManager.getColor("MenuItem.selectionBackground"));
		
		comboBoxChart = new JComboBox();
		comboBoxChart.setModel(new DefaultComboBoxModel(new String[] {"Line", "Bar", "Boxplot"}));
		
		JButton btnCreateExcelFile = new JButton("Create Excel File");
		btnCreateExcelFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createExcelFile();
			}
		});
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxMergeIntoLast)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(btnClearData)
							.addGap(49)
							.addComponent(btnCreateExcelFile)
							.addGap(30)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxChart, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnShowXychart, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap(119, Short.MAX_VALUE)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
							.addComponent(chckbxMergeIntoLast)
							.addGap(55))
						.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
							.addComponent(comboBoxChart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(28)))
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnClearData)
						.addComponent(btnShowXychart)
						.addComponent(btnCreateExcelFile))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		panel_1.setLayout(gl_panel_1);

		panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("EditorPane.foreground"));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 372,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 291,
										Short.MAX_VALUE)
								.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		SpringLayout sl_panel_2 = new SpringLayout();
		panel_2.setLayout(sl_panel_2);

		Vector v = new Vector<PrepMethod>();
		list = new JList(v);

		list.addListSelectionListener(selListener);

		JScrollPane scrollPane = new JScrollPane(list);
		sl_panel_2.putConstraint(SpringLayout.NORTH, scrollPane, 63, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, scrollPane, 25, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, scrollPane, -18, SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, scrollPane, 306, SpringLayout.WEST, panel_2);
		panel_2.add(scrollPane);
		sl_panel_2.putConstraint(SpringLayout.NORTH, list, 39, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, list, 46, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, list, -45, SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, list, -28, SpringLayout.EAST, panel_2);
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

		JLabel lblNewLabel = new JLabel("List of Methods   Green: Contract   Red: No Contract");
		sl_panel_2.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, lblNewLabel, 25, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, lblNewLabel, -95, SpringLayout.EAST, panel_2);
		lblNewLabel.setForeground(UIManager.getColor("DesktopIcon.background"));
		panel_2.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Starter-Method:");
		sl_panel_2.putConstraint(SpringLayout.WEST, lblNewLabel_1, 47, SpringLayout.EAST, scrollPane);
		sl_panel_2.putConstraint(SpringLayout.EAST, lblNewLabel_1, -37, SpringLayout.EAST, panel_2);
		lblNewLabel_1.setForeground(UIManager.getColor("text"));
		panel_2.add(lblNewLabel_1);

		textFieldStarterM = new JTextField();
		sl_panel_2.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -19, SpringLayout.NORTH, textFieldStarterM);
		sl_panel_2.putConstraint(SpringLayout.WEST, textFieldStarterM, 23, SpringLayout.EAST, scrollPane);
		textFieldStarterM.setEditable(false);
		panel_2.add(textFieldStarterM);
		textFieldStarterM.setColumns(10);

		JButton btnExecVerify = new JButton("Verify");
		sl_panel_2.putConstraint(SpringLayout.SOUTH, textFieldStarterM, -24, SpringLayout.NORTH, btnExecVerify);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnExecVerify, -66, SpringLayout.EAST, panel_2);
		btnExecVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					executeVerify();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_2.add(btnExecVerify);

		lblSpecification = new JLabel("");
		sl_panel_2.putConstraint(SpringLayout.EAST, lblSpecification, 0, SpringLayout.EAST, lblNewLabel_1);
		lblSpecification.setForeground(UIManager.getColor("menu"));
		sl_panel_2.putConstraint(SpringLayout.NORTH, lblSpecification, 6, SpringLayout.SOUTH, lblNewLabel);
		sl_panel_2.putConstraint(SpringLayout.WEST, lblSpecification, 0, SpringLayout.WEST, scrollPane);
		panel_2.add(lblSpecification);

		textFieldinfo = new JTextField();
		sl_panel_2.putConstraint(SpringLayout.WEST, textFieldinfo, 23, SpringLayout.EAST, scrollPane);
		sl_panel_2.putConstraint(SpringLayout.EAST, textFieldinfo, -28, SpringLayout.EAST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, textFieldStarterM, 0, SpringLayout.EAST, textFieldinfo);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnExecVerify, -25, SpringLayout.NORTH, textFieldinfo);
		textFieldinfo.setForeground(Color.WHITE);
		textFieldinfo.setBackground(Color.BLACK);
		textFieldinfo.setEnabled(false);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, textFieldinfo, 0, SpringLayout.SOUTH, scrollPane);
		panel_2.add(textFieldinfo);
		textFieldinfo.setEditable(false);
		textFieldinfo.setColumns(10);
		
		comboBoxClasses = new JComboBox();
		sl_panel_2.putConstraint(SpringLayout.EAST, comboBoxClasses, 0, SpringLayout.EAST, textFieldStarterM);
		comboBoxClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setClassDependedMethods();
			}
		});
		panel_2.add(comboBoxClasses);
		
		JLabel lblClass = new JLabel("Class:");
		sl_panel_2.putConstraint(SpringLayout.SOUTH, lblClass, -35, SpringLayout.NORTH, lblNewLabel_1);
		sl_panel_2.putConstraint(SpringLayout.NORTH, comboBoxClasses, -5, SpringLayout.NORTH, lblClass);
		sl_panel_2.putConstraint(SpringLayout.WEST, comboBoxClasses, 25, SpringLayout.EAST, lblClass);
		sl_panel_2.putConstraint(SpringLayout.WEST, lblClass, 0, SpringLayout.WEST, textFieldStarterM);
		lblClass.setForeground(Color.WHITE);
		panel_2.add(lblClass);

		JLabel lblProgramm = new JLabel("Programs");
		lblProgramm.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));

		chckbxChooseExistingJava = new JCheckBox("Choose existing Java Class");
		chckbxChooseExistingJava.setBackground(UIManager.getColor("Button.darkShadow"));

		JLabel lblCreate = new JLabel("Create");

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Add", "Bubblesort" }));

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseInputFile();
			}
		});

		textFieldsearch = new JTextField();
		textFieldsearch.setEditable(false);
		textFieldsearch.setColumns(10);

		btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					executeGenerate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JLabel lblWidth = new JLabel("Width");

		JLabel lblDepth = new JLabel("Depth");

		textFieldwidth = new JTextField();
		textFieldwidth.setText("2");
		textFieldwidth.setColumns(10);

		textFielddepth = new JTextField();
		textFielddepth.setText("2");
		textFielddepth.setColumns(10);

		chckbxFromTo = new JCheckBox("from 1 to depth");
		chckbxFromTo.setBackground(UIManager.getColor("Button.darkShadow"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(123).addComponent(lblProgramm))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCreate, GroupLayout.PREFERRED_SIZE, 64,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblWidth))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 166,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createSequentialGroup()
												.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(textFieldwidth, Alignment.LEADING, 0, 0,
																Short.MAX_VALUE)
														.addComponent(textFielddepth, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
												.addGap(29).addComponent(chckbxFromTo)))))
						.addContainerGap(60, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addGroup(gl_panel
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(chckbxChooseExistingJava, GroupLayout.PREFERRED_SIZE, 238,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel
								.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnGenerate, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 91,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(textFieldsearch, GroupLayout.PREFERRED_SIZE, 215,
												GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGap(35))
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblDepth).addContainerGap(317,
						Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap().addComponent(lblProgramm).addGap(24)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblCreate).addComponent(
						comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textFieldwidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(lblDepth)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFielddepth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(chckbxFromTo)))
				.addGap(32).addComponent(chckbxChooseExistingJava).addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnSearch).addComponent(
						textFieldsearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(btnGenerate, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addGap(21)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		setTitle("Method Call Treatment");
	}

	
	
	private void createChart() {
		String plot = comboBoxChart.getSelectedItem().toString();
		Chart chart;
		if(plot.equals("Line")) {
			chart = Chart.LINE;
		} else if (plot.equals("Bar")) {
			chart = Chart.BAR;
		} else {
			chart = Chart.BOXPLOT;
		}
		if(resultLists.isEmpty()) {
			textFieldinfo.setText("No charts available.");
		} else {
			xychart = new XYChart("XY-Chart", "ContractTest", resultLists, propertiesList, chart, chckbxMergeIntoLast.isSelected());
		}

		
	}
	
	private void createExcelFile() {
		int width = Integer.parseInt(textFieldwidth.getText());
		boolean contracting = chckbxContracting.isSelected();
		String name = (contracting ? "contracting_" : "inlining_") + "width" + width;
		try {
			ExcelFile.createTable(resultLists, name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void clearData() {
		resultLists.clear();
		textFieldinfo.setText("Resultlist is empty.");
	}

	private void changeStarter() {
		PrepMethod pm = (PrepMethod) list.getModel().getElementAt(list.getSelectedIndex());
		textFieldStarterM.setText(pm.name);
		textFieldinfo.setText("");
	}

	private void executeVerify() throws Exception {
		textFieldinfo.setText("Start to run");
		//setParameter();
		String starter = textFieldStarterM.getText();
		if (starter.equals("")) {
			textFieldinfo.setText("Choose a starter");
			return;
		}
		int startP = Integer.parseInt(textFieldStartPercent.getText());
		int endP = Integer.parseInt(textFieldEndPercent.getText());
		int gran = Integer.parseInt(comboBoxGranulation.getSelectedItem().toString());
		boolean randomized = chckbxRandomized.isSelected();
		if(!chckbxSetSpecification.isSelected()) {
			startP = endP;
		}
		boolean contract6ing = chckbxContracting.isSelected();
		int run = Integer.parseInt(textFieldruns.getText());
		
		program.verify(run, contract6ing, startP, endP, gran, starter);
		
		
		List<ChartResults> res = new ArrayList<>();
		res.addAll(ResultHandler.getResultsForXY());
		resultLists.add(res);
		if(chckbxContracting.isSelected()) {
			propertiesList.add("Contracting-Width" + textFieldwidth.getText());
		} else {
			propertiesList.add("Inlining-Width" + textFieldwidth.getText());
		}

		System.out.println("---------Ready--------");
		textFieldinfo.setText("Ready");
		
	}

	private void setParameter() {
		String genProgram = comboBox.getSelectedItem().toString();
		int width = Integer.parseInt(textFieldwidth.getText());
		int depth = Integer.parseInt(textFielddepth.getText());
		boolean isToDepth = chckbxFromTo.isSelected();
		program.setParameters(width, depth, isToDepth, genProgram);
	}
	
	private void showMethods() throws Exception{
		methodList.clear();
		classList.clear();
		comboBoxClasses.removeAll();
		list.removeAll();
		
		List<String> classString = new ArrayList<String>();
		classList = program.getClasses();
		
		for(PrepClasses  pc : classList) {
			classString.add(pc.name);
			methodList.addAll(pc.prepMethods);	
		}
		
		String properties = "Classes: " + classList.size() + "   Methods: " + methodList.size() + "   Spec: " + ClassMethodHandler.getSpecPercent(methodList) + "%";
		comboBoxClasses.setModel(new DefaultComboBoxModel(classString.toArray()));
		lblSpecification.setText(properties);
		textFieldEndPercent.setText("" + ClassMethodHandler.getSpecPercent(methodList));
		setClassDependedMethods();
	}

	private void executeGenerate() throws Exception {
		String programType = "generated";
		if (chckbxChooseExistingJava.isSelected()) {
			programType = choosenFile.isFile() ? "single" : "codebase";
		}
		program = ProgramFactory.getProgram(programType);
		setParameter();
		program.prepare(choosenFile);
		showMethods();
	}
	
	private void setClassDependedMethods() {
		String className = comboBoxClasses.getSelectedItem().toString();
		methodVector.clear();
		for(PrepClasses  pc : classList) {
			if(pc.name.equals(className)) {
				for(PrepMethod meth : pc.prepMethods) {
					methodVector.add(meth);
				}
			}
		}
		list.removeListSelectionListener(selListener);
		list.setListData(methodVector);
		list.addListSelectionListener(selListener);	
		
	}
	

	private void chooseInputFile() {
        
    final JFileChooser chooser = new JFileChooser(); 
    chooser.setCurrentDirectory(new java.io.File("TestClasses/"));
    chooser.setDialogTitle("choosertitle");
    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
		choosenFile = chooser.getSelectedFile();
		textFieldsearch.setText(choosenFile.getName());
      }
    else {
      System.out.println("No Selection ");
      }
     }
}
