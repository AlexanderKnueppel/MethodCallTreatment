package de.tubs.mt.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JList;
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
	private List<String> propertiesList = new ArrayList<String>();

	private IProgram program;
	private JLabel lblSpecification;


	public UILaunch() {
		this.setSize(1027, 665);
		this.setLocation(200, 100);
		this.setTitle("Method Call Treatment");
		getContentPane().setBackground(SystemColor.infoText);
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelProperties = new JPanel();
		panelProperties.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelProperties.setBackground(UIManager.getColor("Button.foreground"));
		panelProperties.setLayout(new GridLayout(0, 1, 5, 5));

		
		panelProperties.add(new ProgrammGenerateField());
		panelProperties.add(new VerifyOptionsField());
		panelProperties.add(new StatisticsField());
		
		
		getContentPane().add(panelProperties);
		getContentPane().add(new ViewMethodsField());

	}

	
	
	
	
	private void createChart() {
		String plot = comboBoxChart.getSelectedItem().toString();
		Chart chart;
		if (plot.equals("Line")) {
			chart = Chart.LINE;
		} else if (plot.equals("Bar")) {
			chart = Chart.BAR;
		} else {
			chart = Chart.BOXPLOT;
		}
		if (resultLists.isEmpty()) {
			textFieldinfo.setText("No charts available.");
		} else {
			xychart = new XYChart("XY-Chart", "ContractTest", resultLists, propertiesList, chart,
					chckbxMergeIntoLast.isSelected());
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
		// setParameter();
		String starter = textFieldStarterM.getText();
		if (starter.equals("")) {
			textFieldinfo.setText("Choose a starter");
			return;
		}
		int startP = Integer.parseInt(textFieldStartPercent.getText());
		int endP = Integer.parseInt(textFieldEndPercent.getText());
		int gran = Integer.parseInt(comboBoxGranulation.getSelectedItem().toString());
		boolean randomized = chckbxRandomized.isSelected();
		if (!chckbxSetSpecification.isSelected()) {
			startP = endP;
		}
		boolean contract6ing = chckbxContracting.isSelected();
		int run = Integer.parseInt(textFieldruns.getText());

		program.verify(run, contract6ing, startP, endP, gran, starter);

		List<ChartResults> res = new ArrayList<>();
		res.addAll(ResultHandler.getResultsForXY());
		resultLists.add(res);
		if (chckbxContracting.isSelected()) {
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

	private void showMethods() throws Exception {
		methodList.clear();
		classList.clear();
		comboBoxClasses.removeAll();
		list.removeAll();

		List<String> classString = new ArrayList<String>();
		classList = program.getClasses();

		for (PrepClasses pc : classList) {
			classString.add(pc.name);
			methodList.addAll(pc.prepMethods);
		}

		String properties = "Classes: " + classList.size() + "   Methods: " + methodList.size() + "   Spec: "
				+ ClassMethodHandler.getSpecPercent(methodList) + "%";
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
		for (PrepClasses pc : classList) {
			if (pc.name.equals(className)) {
				for (PrepMethod meth : pc.prepMethods) {
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
		} else {
			System.out.println("No Selection ");
		}
	}
}
