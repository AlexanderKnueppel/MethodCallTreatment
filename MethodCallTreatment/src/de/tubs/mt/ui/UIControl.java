package de.tubs.mt.ui;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;

import de.tubs.mt.codeanalyze.ClassMethodHandler;
import de.tubs.mt.codeanalyze.PrepClasses;
import de.tubs.mt.codeanalyze.PrepMethod;
import de.tubs.mt.programfactory.ProgramFactory;
import de.tubs.mt.result.ChartResults;
import de.tubs.mt.result.ExcelFile;
import de.tubs.mt.result.ResultHandler;
import de.tubs.mt.result.XYChart;
import de.tubs.mt.result.XYChart.Chart;



/**
 * The Class UIControl.
 */
class UIControl {
	
	/** The model. */
	private UIModel model;
	
	/** The view. */
	private UIView view;
	
	/**
	 * Instantiates a new UI control.
	 *
	 * @param view the view
	 */
	public UIControl(UIView view) {
		this.model = new UIModel();
		this.view = view;
	}
	
	
	/**
	 * Choose input file.
	 */
	public void chooseInputFile() {
		final JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("TestClasses/"));
		chooser.setDialogTitle("choosertitle");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
			model.setChoosenFile(chooser.getSelectedFile());
			view.getTextFieldsearch().setText(model.getChoosenFile().getName());
		} else {
			System.out.println("No Selection ");
		}
	}
	

	
	/**
	 * Execute generate.
	 *
	 * @throws Exception the exception
	 */
	public void executeGenerate() throws Exception {
		String programType = "generated";
		if (view.getChckbxChooseExistingJava().isSelected()) {
			programType = model.getChoosenFile().isFile() ? "single" : "codebase";
		}
		model.setProgram(ProgramFactory.getProgram(programType));
		setParameter();
		model.getProgram().prepare(model.getChoosenFile());
		showMethods();
	}
	
	
	/**
	 * Sets the parameter.
	 */
	private void setParameter() {
		model.setDepth(Integer.parseInt(view.getTextFielddepth().getText()));
		model.setWidth(Integer.parseInt(view.getTextFieldwidth().getText()));
		model.setToDepth(view.getChckbxFromTo().isSelected());
		model.setGenerateProgram(view.getComboBoxProgram().getSelectedItem().toString());
		model.getProgram().setParameters(model);
	}
	
	
	/**
	 * Show methods.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void showMethods() throws Exception {
		model.getMethodList().clear();
		model.getClassList().clear();
		view.getComboBoxClasses().removeAll();
		view.getList().removeAll();

		List<String> classString = new ArrayList<String>();
		model.setClassList(model.getProgram().getClasses());

		for (PrepClasses pc : model.getClassList()) {
			classString.add(pc.name);
			model.getMethodList().addAll(pc.prepMethods);
		}

		String properties = "Classes: " + model.getClassList().size() + "   Methods: " + model.getMethodList().size() + "   Spec: "
				+ ClassMethodHandler.getSpecPercent(model.getMethodList()) + "%";
		view.getComboBoxClasses().setModel(new DefaultComboBoxModel(classString.toArray()));
		view.getLblSpecification().setText(properties);
		view.getTextFieldEndPercent().setText("" + ClassMethodHandler.getSpecPercent(model.getMethodList()));
		setClassDependedMethods();
	}
	
	
	
	/**
	 * Sets the class depended methods.
	 */
	@SuppressWarnings("unchecked")
	public void setClassDependedMethods() {
		String className = view.getComboBoxClasses().getSelectedItem().toString();
		view.getMethodVector().clear();
		for (PrepClasses pc : model.getClassList()) {
			if (pc.name.equals(className)) {
				for (PrepMethod meth : pc.prepMethods) {
					view.getMethodVector().add(meth);
				}
			}
		}
		view.getList().removeListSelectionListener(view.getListSelectionListener());
		view.getList().setListData(view.getMethodVector());
		view.getList().addListSelectionListener(view.getListSelectionListener());
	}
	
	
	/**
	 * Execute verify.
	 *
	 * @throws Exception the exception
	 */
	public void executeVerify() throws Exception {
		view.getTextFieldinfo().setText("Start to run");
		setParameter();
		String starter = view.getTextFieldStarterM().getText();
		if (starter.equals("")) {
			view.getTextFieldinfo().setText("Choose a starter");
			return;
		}
		int startP = Integer.parseInt(view.getTextFieldStartPercent().getText());
		int endP = Integer.parseInt(view.getTextFieldEndPercent().getText());
		int gran = Integer.parseInt(view.getComboBoxGranulation().getSelectedItem().toString());
		//boolean randomized = view.getChckbxRandomized().isSelected(); //TODO add different strategies of deleting jml
		if (!view.getChckbxSetSpecification().isSelected()) {
			startP = endP;
		}
		boolean contracting = view.getChckbxContracting().isSelected();
		int run = Integer.parseInt(view.getTextFieldruns().getText());

		model.getProgram().verify(run, contracting, startP, endP, gran, starter);

		List<ChartResults> res = new ArrayList<>();
		res.addAll(ResultHandler.getResultsForXY());
		model.getResultLists().add(res);
		if (view.getChckbxContracting().isSelected()) {
			model.getPropertiesList().add("Contracting-Width" + view.getTextFieldwidth().getText());
		} else {
			model.getPropertiesList().add("Inlining-Width" + view.getTextFieldwidth().getText());
		}

		System.out.println("---------Ready--------");
		view.getTextFieldinfo().setText("Ready");

	}
	
	/**
	 * Clear data.
	 */
	public void clearData() {
		model.getResultLists().clear();
		view.getTextFieldinfo().setText("Resultlist is empty.");
	}
	
	/**
	 * Creates the chart.
	 */
	public void createChart() {
		String plot = view.getComboBoxChart().getSelectedItem().toString();
		Chart chart;
		if (plot.equals("Line")) {
			chart = Chart.LINE;
		} else if (plot.equals("Bar")) {
			chart = Chart.BAR;
		} else {
			chart = Chart.BOXPLOT;
		}
		if (model.getResultLists().isEmpty()) {
			view.getTextFieldinfo().setText("No charts available.");
		} else {
			model.setXychart(new XYChart("XY-Chart", "ContractTest", model.getResultLists(), model.getPropertiesList(), chart,
					view.getChckbxMergeIntoLast().isSelected()));
		}
	}
	

	/**
	 * Creates the excel file.
	 */
	public void createExcelFile() {
		int width = model.getWidth();
		boolean contracting = view.getChckbxContracting().isSelected();
		String name = (contracting ? "contracting_" : "inlining_") + "width" + width;
		try {
			ExcelFile.createTable(model.getResultLists(), name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
