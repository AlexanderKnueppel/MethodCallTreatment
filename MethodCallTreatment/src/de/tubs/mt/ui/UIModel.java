
package de.tubs.mt.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.tubs.mt.codeanalyze.PrepClasses;
import de.tubs.mt.codeanalyze.PrepMethod;
import de.tubs.mt.programfactory.IProgram;
import de.tubs.mt.result.ChartResults;
import de.tubs.mt.result.XYChart;


/**
 * The Class UIModel.
 */
public class UIModel {
	
	
	//Parameters
	//##########
	
	/** The width. */
	private int width;
	
	/** The depth. */
	private int depth;
	
	/** The is to depth. */
	private boolean isToDepth;
	
	/** The method list. */
	private List<PrepMethod> methodList = new ArrayList<PrepMethod>();
	
	/** The class list. */
	private List<PrepClasses> classList = new ArrayList<PrepClasses>();
	
	/** The result lists. */
	private ArrayList<List<ChartResults>> resultLists = new ArrayList<List<ChartResults>>();
	
	/** The properties list. */
	private List<String> propertiesList = new ArrayList<String>();
	
	/** The chosen file. */
	private File choosenFile;
	
	/** The program. */
	private IProgram program;
	
	/** The strategy. */
	private String strategy;
	
	/** The xychart. */
	private XYChart xychart;
	
	/** The generate program. */
	private String generateProgram;
	
	/** The start perc. */
	private int startPerc;
	
	/** The end perc. */
	private int endPerc;
	
	/** The granulation. */
	private int granulation;
	
	/** The run. */
	private int run;
	
	/**
	 * Gets the tree or tiny code.
	 *
	 * @return the tree or tiny code
	 */
	public String getTreeOrTinyCode() {
		return treeOrTinyCode;
	}

	/**
	 * Sets the tree or tiny code.
	 *
	 * @param treeOrTinyCode the new tree or tiny code
	 */
	public void setTreeOrTinyCode(String treeOrTinyCode) {
		this.treeOrTinyCode = treeOrTinyCode;
	}

	/** The starter. */
	private String starter;
	
	/** The contracting. */
	private boolean contracting;
	
	/** The tree or tiny code. */
	private String treeOrTinyCode;
	
	
	
	
	// Getter and Setter
	//##################
	

	/**
	 * Gets the generate program.
	 *
	 * @return the generate program
	 */
	public String getGenerateProgram() {
		return generateProgram;
	}

	/**
	 * Sets the generate program.
	 *
	 * @param generateProgram the new generate program
	 */
	public void setGenerateProgram(String generateProgram) {
		this.generateProgram = generateProgram;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gets the depth.
	 *
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * Sets the depth.
	 *
	 * @param depth the new depth
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * Checks if is to depth.
	 *
	 * @return true, if is to depth
	 */
	public boolean isToDepth() {
		return isToDepth;
	}

	/**
	 * Sets the to depth.
	 *
	 * @param isToDepth the new to depth
	 */
	public void setToDepth(boolean isToDepth) {
		this.isToDepth = isToDepth;
	}

	/**
	 * Gets the method list.
	 *
	 * @return the method list
	 */
	public List<PrepMethod> getMethodList() {
		return methodList;
	}

	/**
	 * Sets the method list.
	 *
	 * @param methodList the new method list
	 */
	public void setMethodList(List<PrepMethod> methodList) {
		this.methodList = methodList;
	}

	/**
	 * Gets the class list.
	 *
	 * @return the class list
	 */
	public List<PrepClasses> getClassList() {
		return classList;
	}

	/**
	 * Sets the class list.
	 *
	 * @param classList the new class list
	 */
	public void setClassList(List<PrepClasses> classList) {
		this.classList = classList;
	}

	/**
	 * Gets the result lists.
	 *
	 * @return the result lists
	 */
	public ArrayList<List<ChartResults>> getResultLists() {
		return resultLists;
	}

	/**
	 * Sets the result lists.
	 *
	 * @param resultLists the new result lists
	 */
	public void setResultLists(ArrayList<List<ChartResults>> resultLists) {
		this.resultLists = resultLists;
	}

	/**
	 * Gets the properties list.
	 *
	 * @return the properties list
	 */
	public List<String> getPropertiesList() {
		return propertiesList;
	}

	/**
	 * Sets the properties list.
	 *
	 * @param propertiesList the new properties list
	 */
	public void setPropertiesList(List<String> propertiesList) {
		this.propertiesList = propertiesList;
	}

	/**
	 * Gets the choosen file.
	 *
	 * @return the choosen file
	 */
	public File getChoosenFile() {
		return choosenFile;
	}

	/**
	 * Sets the choosen file.
	 *
	 * @param choosenFile the new choosen file
	 */
	public void setChoosenFile(File choosenFile) {
		this.choosenFile = choosenFile;
	}

	/**
	 * Gets the program.
	 *
	 * @return the program
	 */
	public IProgram getProgram() {
		return program;
	}

	/**
	 * Sets the program.
	 *
	 * @param program the new program
	 */
	public void setProgram(IProgram program) {
		this.program = program;
	}

	
	/**
	 * Gets the xychart.
	 *
	 * @return the xychart
	 */
	public XYChart getXychart() {
		return xychart;
	}

	/**
	 * Sets the xychart.
	 *
	 * @param xychart the new xychart
	 */
	public void setXychart(XYChart xychart) {
		this.xychart = xychart;
	}

	/**
	 * Gets the strategy.
	 *
	 * @return the strategy
	 */
	public String getStrategy() {
		return strategy;
	}

	/**
	 * Sets the strategy.
	 *
	 * @param strategy the new strategy
	 */
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	/**
	 * Gets the start perc.
	 *
	 * @return the start perc
	 */
	public int getStartPerc() {
		return startPerc;
	}

	/**
	 * Sets the start perc.
	 *
	 * @param startPerc the new start perc
	 */
	public void setStartPerc(int startPerc) {
		this.startPerc = startPerc;
	}

	/**
	 * Gets the end perc.
	 *
	 * @return the end perc
	 */
	public int getEndPerc() {
		return endPerc;
	}

	/**
	 * Sets the end perc.
	 *
	 * @param endPerc the new end perc
	 */
	public void setEndPerc(int endPerc) {
		this.endPerc = endPerc;
	}

	/**
	 * Gets the granulation.
	 *
	 * @return the granulation
	 */
	public int getGranulation() {
		return granulation;
	}

	/**
	 * Sets the granulation.
	 *
	 * @param granulation the new granulation
	 */
	public void setGranulation(int granulation) {
		this.granulation = granulation;
	}

	/**
	 * Gets the run.
	 *
	 * @return the run
	 */
	public int getRun() {
		return run;
	}

	/**
	 * Sets the run.
	 *
	 * @param run the new run
	 */
	public void setRun(int run) {
		this.run = run;
	}

	/**
	 * Gets the starter.
	 *
	 * @return the starter
	 */
	public String getStarter() {
		return starter;
	}

	/**
	 * Sets the starter.
	 *
	 * @param starter the new starter
	 */
	public void setStarter(String starter) {
		this.starter = starter;
	}

	/**
	 * Checks if is contracting.
	 *
	 * @return true, if is contracting
	 */
	public boolean isContracting() {
		return contracting;
	}

	/**
	 * Sets the contracting.
	 *
	 * @param contracting the new contracting
	 */
	public void setContracting(boolean contracting) {
		this.contracting = contracting;
	}

}
