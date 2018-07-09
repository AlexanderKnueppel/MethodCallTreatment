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
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;

public class UILaunch extends JFrame {


	private JTextField textFieldsearch;
	private JTextField textFieldruns;
	private JTextField textFieldwidth;
	private JTextField textFielddepth;
	private JTextField textFieldinfo;
	private JTextField textFieldStarterM;
	private JTextField textFieldStartPercent;
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
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;
	private JLabel label_12;
	private JLabel label_13;
	private JLabel label_14;
	private JLabel label_15;
	private JLabel label_16;
	private JLabel label_20;
	private JLabel label_23;
	private JLabel label_24;
	private JLabel label_25;
	private JLabel label_26;
	private JLabel lblStatistics;
	
	public UILaunch() {
		this.setSize(1027, 665);
		this.setLocation(200, 100);
		getContentPane().setBackground(SystemColor.infoText);

		JPanel panelProperties = new JPanel();
		panelProperties.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelProperties.setBackground(UIManager.getColor("Button.darkShadow"));

		panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("EditorPane.foreground"));
		
				JPanel panel_3 = new JPanel();
				panel_3.setBackground(UIManager.getColor("MenuItem.selectionBackground"));
						panel_3.setLayout(new GridLayout(0, 3, 0, 0));
						
						label_23 = new JLabel("");
						panel_3.add(label_23);
										
										label_24 = new JLabel("");
										panel_3.add(label_24);
										
										label_25 = new JLabel("");
										panel_3.add(label_25);
										
										label_26 = new JLabel("");
										panel_3.add(label_26);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelProperties, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(87)
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(77)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelProperties, GroupLayout.PREFERRED_SIZE, 622, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
							.addGap(71)
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(351))
		);
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

		lblSpecification = new JLabel("");
		sl_panel_2.putConstraint(SpringLayout.EAST, lblSpecification, 0, SpringLayout.EAST, lblNewLabel_1);
		lblSpecification.setForeground(UIManager.getColor("menu"));
		sl_panel_2.putConstraint(SpringLayout.NORTH, lblSpecification, 6, SpringLayout.SOUTH, lblNewLabel);
		sl_panel_2.putConstraint(SpringLayout.WEST, lblSpecification, 0, SpringLayout.WEST, scrollPane);
		panel_2.add(lblSpecification);

		textFieldinfo = new JTextField();
		sl_panel_2.putConstraint(SpringLayout.SOUTH, textFieldStarterM, -74, SpringLayout.NORTH, textFieldinfo);
		sl_panel_2.putConstraint(SpringLayout.WEST, textFieldinfo, 23, SpringLayout.EAST, scrollPane);
		sl_panel_2.putConstraint(SpringLayout.EAST, textFieldinfo, -28, SpringLayout.EAST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, textFieldStarterM, 0, SpringLayout.EAST, textFieldinfo);
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
		panelProperties.setLayout(new GridLayout(0, 2, 10, 5));
		
				JLabel lblProgramm = new JLabel("Program Options");
				lblProgramm.setForeground(UIManager.getColor("Button.background"));
				lblProgramm.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
				panelProperties.add(lblProgramm);
		
		label = new JLabel("");
		panelProperties.add(label);
		
				JLabel lblCreate = new JLabel("Create");
				panelProperties.add(lblCreate);
		
				comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] { "Add", "Bubblesort" }));
				panelProperties.add(comboBox);
		JLabel lblWidth = new JLabel("Width");
		panelProperties.add(lblWidth);
		
				textFieldwidth = new JTextField();
				textFieldwidth.setText("2");
				textFieldwidth.setColumns(10);
				panelProperties.add(textFieldwidth);
		
				JLabel lblDepth = new JLabel("Depth");
				panelProperties.add(lblDepth);
		
				textFielddepth = new JTextField();
				textFielddepth.setText("2");
				textFielddepth.setColumns(10);
				panelProperties.add(textFielddepth);
		
		label_1 = new JLabel("");
		panelProperties.add(label_1);
		
				chckbxFromTo = new JCheckBox("from 1 to depth");
				chckbxFromTo.setBackground(UIManager.getColor("Button.darkShadow"));
				panelProperties.add(chckbxFromTo);
		
		label_2 = new JLabel("");
		panelProperties.add(label_2);
		
		label_3 = new JLabel("");
		panelProperties.add(label_3);
		
				chckbxChooseExistingJava = new JCheckBox("Choose File");
				chckbxChooseExistingJava.setBackground(UIManager.getColor("Button.darkShadow"));
				panelProperties.add(chckbxChooseExistingJava);
		
		label_4 = new JLabel("");
		panelProperties.add(label_4);
		
				btnSearch = new JButton("Search");
				btnSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						chooseInputFile();
					}
				});
				panelProperties.add(btnSearch);
		
				textFieldsearch = new JTextField();
				textFieldsearch.setEditable(false);
				textFieldsearch.setColumns(10);
				panelProperties.add(textFieldsearch);
		
		label_5 = new JLabel("");
		panelProperties.add(label_5);
		
		label_6 = new JLabel("");
		panelProperties.add(label_6);
				
				label_7 = new JLabel("");
				panelProperties.add(label_7);
				
						btnGenerate = new JButton("Generate");
						btnGenerate.setForeground(new Color(0, 128, 0));
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
						panelProperties.add(btnGenerate);
				JLabel lblOptions = new JLabel("Verify Options");
				lblOptions.setForeground(UIManager.getColor("Button.background"));
				panelProperties.add(lblOptions);
				lblOptions.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
				
				label_8 = new JLabel("");
				panelProperties.add(label_8);
				chckbxContracting = new JCheckBox("contracting");
				panelProperties.add(chckbxContracting);
				chckbxContracting.setBackground(UIManager.getColor("Button.darkShadow"));
				chckbxContracting.setSelected(true);
				
				label_9 = new JLabel("");
				panelProperties.add(label_9);
		
				JLabel lblRuns = new JLabel("Runs");
				panelProperties.add(lblRuns);
		textFieldruns = new JTextField();
		panelProperties.add(textFieldruns);
		textFieldruns.setText("1");
		textFieldruns.setColumns(10);
		
		chckbxSetSpecification = new JCheckBox("Set Specification");
		panelProperties.add(chckbxSetSpecification);
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
		
				chckbxRandomized = new JCheckBox("Randomized");
				panelProperties.add(chckbxRandomized);
				chckbxRandomized.setBackground(UIManager.getColor("Button.darkShadow"));
		
				textFieldStartPercent = new JTextField();
				panelProperties.add(textFieldStartPercent);
				textFieldStartPercent.setEnabled(false);
				textFieldStartPercent.setText("0");
				textFieldStartPercent.setColumns(10);
		
				textFieldEndPercent = new JTextField();
				panelProperties.add(textFieldEndPercent);
				textFieldEndPercent.setEnabled(false);
				textFieldEndPercent.setText("100");
				textFieldEndPercent.setColumns(10);
		
				JLabel lblGranulation = new JLabel("Granulation");
				panelProperties.add(lblGranulation);
		
				comboBoxGranulation = new JComboBox();
				panelProperties.add(comboBoxGranulation);
				comboBoxGranulation
						.setModel(new DefaultComboBoxModel(new String[] { "1", "5", "10", "15", "20", "30", "40", "50" }));
				comboBoxGranulation.setSelectedIndex(2);
		
		label_10 = new JLabel("");
		panelProperties.add(label_10);
		
		label_11 = new JLabel("");
		panelProperties.add(label_11);
		
		label_12 = new JLabel("");
		panelProperties.add(label_12);
						
								JButton btnExecVerify = new JButton("Verify");
								btnExecVerify.setForeground(new Color(0, 128, 0));
								panelProperties.add(btnExecVerify);
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
								sl_panel_2.putConstraint(SpringLayout.SOUTH, btnExecVerify, -25, SpringLayout.NORTH, textFieldinfo);
						
						lblStatistics = new JLabel("Statistics");
						lblStatistics.setForeground(UIManager.getColor("Button.background"));
						lblStatistics.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
						panelProperties.add(lblStatistics);
						
						label_13 = new JLabel("");
						panelProperties.add(label_13);
						
								chckbxMergeIntoLast = new JCheckBox("Merge");
								panelProperties.add(chckbxMergeIntoLast);
								chckbxMergeIntoLast.setToolTipText("Merge last results into chart.\nOnly possible for a line-chart.");
								chckbxMergeIntoLast.setBackground(UIManager.getColor("MenuItem.selectionBackground"));
						
						comboBoxChart = new JComboBox();
						panelProperties.add(comboBoxChart);
						comboBoxChart.setModel(new DefaultComboBoxModel(new String[] {"Line", "Bar", "Boxplot"}));
						
								JButton btnClearData = new JButton("Clear Data");
								panelProperties.add(btnClearData);
								btnClearData.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										clearData();
									}
								});
						
								JButton btnShowXychart = new JButton("Show Chart");
								panelProperties.add(btnShowXychart);
								btnShowXychart.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										createChart();

									}
								});
						
						JButton btnCreateExcelFile = new JButton("Create Excel File");
						panelProperties.add(btnCreateExcelFile);
						btnCreateExcelFile.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								createExcelFile();
							}
						});
						
						label_14 = new JLabel("");
						panelProperties.add(label_14);
						
						label_15 = new JLabel("");
						panelProperties.add(label_15);
				
				label_16 = new JLabel("");
				panelProperties.add(label_16);
		
		label_20 = new JLabel("");
		panelProperties.add(label_20);
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
