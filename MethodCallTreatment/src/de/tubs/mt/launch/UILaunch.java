package de.tubs.mt.launch;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.data.xy.XYSeries;

import de.tubs.mt.codeanalyze.MethodPrinter;
import de.tubs.mt.codeanalyze.PrepMethod;
import de.tubs.mt.codegen.CallGenerator.Program;
import de.tubs.mt.files.FileControl;
import de.tubs.mt.chart.ExcelFile;
import de.tubs.mt.chart.ResultsForXY;
import de.tubs.mt.chart.XYChart;
import de.tubs.mt.chart.XYChart.Chart;

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
import javax.swing.JToggleButton;
import java.awt.SystemColor;

public class UILaunch extends JFrame {

	private Launcher launcher;

	private JTextField textFieldsearch;
	private JTextField textFieldruns;
	private JTextField textFieldwidth;
	private JTextField textFielddepth;
	private JTextField textFieldinfo;
	private JTextField textFieldStarterM;
	private JTextField textFieldSpecPerc;
	private JCheckBox chckbxContracting;
	private JCheckBox chckbxFromTo;
	private JCheckBox chckbxRandomized;
	private JCheckBox chckbxMergeIntoLast;
	private JButton btnGenerate;
	private JCheckBox chckbxChooseExistingJava;
	private JButton btnSearch;
	private JComboBox comboBox;
	private JComboBox comboBoxGranulation;
	private JComboBox comboBoxChart;
	private JPanel panel_2;
	private JList listUI;

	private File choosenFile;
	private JList list;
	private JLabel lblNewLabel_1;
	private ListSelectionListener selListener = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			changeStarter();
		}
	};
	private JLabel lblSetSpecification;
	private JTextField textFieldStartPercent;
	private JLabel lblTo;
	private JTextField textFieldEndPercent;
	private List<PrepMethod> methodList;
	private JLabel lblSpecification;
	private XYChart xychart;

	private ArrayList<List<ResultsForXY>> resultLists = new ArrayList<List<ResultsForXY>>();
	private List<String> propertiesList =  new ArrayList<String>();

	public UILaunch(Launcher launcher) {
		this.launcher = launcher;
		this.setSize(926, 626);
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

		lblSetSpecification = new JLabel("Set Specification (%)");

		textFieldStartPercent = new JTextField();
		textFieldStartPercent.setText("0");
		textFieldStartPercent.setColumns(10);

		lblTo = new JLabel("to");

		textFieldEndPercent = new JTextField();
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
		
		JButton btnCreateExcelFile = new JButton("Create Excel File");
		btnCreateExcelFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createExcelFile();
			}
		});

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(124)
							.addComponent(lblOptions))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addContainerGap()
									.addComponent(chckbxContracting)
									.addGap(51)
									.addComponent(lblRuns)
									.addGap(7)
									.addComponent(textFieldruns, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(40)
									.addComponent(btnCreateExcelFile)))
							.addGap(72)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSetSpecification)
										.addComponent(lblGranulation))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addComponent(comboBoxGranulation, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(textFieldStartPercent, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblTo, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(textFieldEndPercent, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(43)
							.addComponent(chckbxRandomized)))
					.addContainerGap(89, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblOptions)
					.addGap(39)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRuns)
						.addComponent(textFieldruns, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSetSpecification)
						.addComponent(textFieldStartPercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTo)
						.addComponent(textFieldEndPercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxRandomized)
						.addComponent(chckbxContracting))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGranulation)
								.addComponent(comboBoxGranulation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCreateExcelFile)
							.addGap(32))))
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
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(btnClearData)
						.addComponent(chckbxMergeIntoLast))
					.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBoxChart, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnShowXychart, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap(17, Short.MAX_VALUE)
							.addComponent(chckbxMergeIntoLast)
							.addGap(55))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addComponent(comboBoxChart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnClearData)
						.addComponent(btnShowXychart))
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
		sl_panel_2.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 38, SpringLayout.SOUTH, lblNewLabel);
		sl_panel_2.putConstraint(SpringLayout.WEST, lblNewLabel_1, 38, SpringLayout.EAST, scrollPane);
		sl_panel_2.putConstraint(SpringLayout.EAST, lblNewLabel_1, -46, SpringLayout.EAST, panel_2);
		lblNewLabel_1.setForeground(UIManager.getColor("text"));
		panel_2.add(lblNewLabel_1);

		textFieldStarterM = new JTextField();
		textFieldStarterM.setEditable(false);
		sl_panel_2.putConstraint(SpringLayout.NORTH, textFieldStarterM, 19, SpringLayout.SOUTH, lblNewLabel_1);
		sl_panel_2.putConstraint(SpringLayout.WEST, textFieldStarterM, 23, SpringLayout.EAST, scrollPane);
		sl_panel_2.putConstraint(SpringLayout.EAST, textFieldStarterM, 18, SpringLayout.EAST, lblNewLabel_1);
		panel_2.add(textFieldStarterM);
		textFieldStarterM.setColumns(10);

		JButton btnExecVerify = new JButton("Verify");
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnExecVerify, 67, SpringLayout.SOUTH, textFieldStarterM);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnExecVerify, -63, SpringLayout.EAST, panel_2);
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

		lblSpecification = new JLabel("Specification (%):");
		lblSpecification.setForeground(UIManager.getColor("menu"));
		sl_panel_2.putConstraint(SpringLayout.NORTH, lblSpecification, 6, SpringLayout.SOUTH, lblNewLabel);
		sl_panel_2.putConstraint(SpringLayout.WEST, lblSpecification, 0, SpringLayout.WEST, scrollPane);
		panel_2.add(lblSpecification);

		textFieldSpecPerc = new JTextField();
		sl_panel_2.putConstraint(SpringLayout.NORTH, textFieldSpecPerc, 4, SpringLayout.SOUTH, lblNewLabel);
		sl_panel_2.putConstraint(SpringLayout.WEST, textFieldSpecPerc, 16, SpringLayout.EAST, lblSpecification);
		sl_panel_2.putConstraint(SpringLayout.EAST, textFieldSpecPerc, 105, SpringLayout.EAST, lblSpecification);
		panel_2.add(textFieldSpecPerc);
		textFieldSpecPerc.setColumns(10);

		textFieldinfo = new JTextField();
		textFieldinfo.setForeground(Color.WHITE);
		textFieldinfo.setBackground(Color.BLACK);
		textFieldinfo.setEnabled(false);
		sl_panel_2.putConstraint(SpringLayout.WEST, textFieldinfo, 0, SpringLayout.WEST, textFieldStarterM);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, textFieldinfo, 0, SpringLayout.SOUTH, scrollPane);
		sl_panel_2.putConstraint(SpringLayout.EAST, textFieldinfo, 0, SpringLayout.EAST, textFieldStarterM);
		panel_2.add(textFieldinfo);
		textFieldinfo.setEditable(false);
		textFieldinfo.setColumns(10);

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
		setParameter();
		String whitelist = textFieldStarterM.getText();
		if (whitelist.equals("")) {
			textFieldinfo.setText("Choose a starter");
			return;
		}
		int startP = Integer.parseInt(textFieldStartPercent.getText());
		int endP = Integer.parseInt(textFieldEndPercent.getText());
		int gran = Integer.parseInt(comboBoxGranulation.getSelectedItem().toString());
		boolean randomized = chckbxRandomized.isSelected();

		launcher.executeLauncher(whitelist, startP, endP, gran, randomized);

		List<ResultsForXY> res = new ArrayList<>();
		res.addAll(launcher.getResultsForXY());
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
		Program program = Program.OWN;
		if (!chckbxChooseExistingJava.isSelected()) {
			program = comboBox.getSelectedItem().toString() == "Add" ? Program.ADD : Program.BUBBLESORT;
		}

		int runs = Integer.parseInt(textFieldruns.getText());
		int width = Integer.parseInt(textFieldwidth.getText());
		int depth = Integer.parseInt(textFielddepth.getText());
		boolean contracting = chckbxContracting.isSelected();
		boolean isToDepth = chckbxFromTo.isSelected();
		String javaFilePath = textFieldsearch.getText();

		launcher.setParameter(program, runs, width, depth, contracting, isToDepth, choosenFile);
	}

	private void executeGenerate() throws Exception {

		setParameter();
		methodList = launcher.runGenerate();

		Vector v = new Vector<PrepMethod>();
		for (PrepMethod meth : methodList) {
			v.add(meth);
		}

		textFieldSpecPerc.setText("" + MethodPrinter.getSpecPercent(methodList));
		textFieldEndPercent.setText("" + MethodPrinter.getSpecPercent(methodList));

		list.removeListSelectionListener(selListener);
		list.setListData(v);
		list.addListSelectionListener(selListener);
		textFieldinfo.setText("Code generated");

	}

	private void chooseInputFile() {
		/**
		
		final JFileChooser fc = new JFileChooser("TestClasses");
		fc.setCurrentDirectory(FileControl.getTestclassesPath());
		fc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Java Files", "java");
		fc.addChoosableFileFilter(filter);

		int selection = fc.showOpenDialog(this);

		if (selection == JFileChooser.APPROVE_OPTION) {
			choosenFile = fc.getSelectedFile().getName();
			textFieldsearch.setText(choosenFile);
		}
		
		*/
		
        
    final JFileChooser chooser = new JFileChooser(); 
    chooser.setCurrentDirectory(new java.io.File("TestClasses/"));
    chooser.setDialogTitle("choosertitle");
    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
      System.out.println("getSelectedFile() : " +  chooser.getSelectedFile());
		choosenFile = chooser.getSelectedFile();
		textFieldsearch.setText(choosenFile.getName());
      }
    else {
      System.out.println("No Selection ");
      }
     }
		 
}
