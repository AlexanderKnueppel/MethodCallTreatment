
package de.tubs.mt.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.tubs.mt.codeanalyze.PrepClasses;
import de.tubs.mt.codeanalyze.PrepMethod;
import de.tubs.mt.programfactory.IProgram;
import de.tubs.mt.result.ChartResults;
import de.tubs.mt.result.XYChart;

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
	
	
	/** The xychart. */
	private XYChart xychart;
	
	/** The generate program. */
	private String generateProgram;
	
	
	
	
	// Getter and Setter
	//##################
	

	public String getGenerateProgram() {
		return generateProgram;
	}

	public void setGenerateProgram(String generateProgram) {
		this.generateProgram = generateProgram;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public boolean isToDepth() {
		return isToDepth;
	}

	public void setToDepth(boolean isToDepth) {
		this.isToDepth = isToDepth;
	}

	public List<PrepMethod> getMethodList() {
		return methodList;
	}

	public void setMethodList(List<PrepMethod> methodList) {
		this.methodList = methodList;
	}

	public List<PrepClasses> getClassList() {
		return classList;
	}

	public void setClassList(List<PrepClasses> classList) {
		this.classList = classList;
	}

	public ArrayList<List<ChartResults>> getResultLists() {
		return resultLists;
	}

	public void setResultLists(ArrayList<List<ChartResults>> resultLists) {
		this.resultLists = resultLists;
	}

	public List<String> getPropertiesList() {
		return propertiesList;
	}

	public void setPropertiesList(List<String> propertiesList) {
		this.propertiesList = propertiesList;
	}

	public File getChoosenFile() {
		return choosenFile;
	}

	public void setChoosenFile(File choosenFile) {
		this.choosenFile = choosenFile;
	}

	public IProgram getProgram() {
		return program;
	}

	public void setProgram(IProgram program) {
		this.program = program;
	}

	
	public XYChart getXychart() {
		return xychart;
	}

	public void setXychart(XYChart xychart) {
		this.xychart = xychart;
	}

}
