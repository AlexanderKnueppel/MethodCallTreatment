package de.tubs.mt.programfactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import de.tubs.mt.codeanalyze.PrepClasses;
import de.tubs.mt.codegen.Incrementer;
import de.tubs.mt.codegen.CallGenerator.Program;
import de.tubs.mt.files.FileControl;

public class Generated implements IProgram {

	private int width;
	private int depth;
	private boolean isToDepth;
	private String program;
	private String name;

	@Override
	public void setParameters(int width, int depth, boolean isToDepth,
			String program) {
		this.width = width;
		this.depth = depth;
		this.isToDepth = isToDepth;
		this.program = program;
		this.name = program + "Width" + width;

	}

	@Override
	public void prepare(File file) {
		FileControl.initStructure();
		for (int flowDepth = isToDepth ? 1 : depth; flowDepth <= depth; flowDepth++) {
			try {
				if (program.equals("Add")) {
					Incrementer.generateProgramForAdd(width, depth, flowDepth,
							FileControl.getPrepPath().getPath(), name);
				} else {
					Incrementer.generateProgramForBubbleSort(width, depth,
							flowDepth, FileControl.getPrepPath().getPath(),
							name);
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void manipulate(int startPercentage, int endPercentage,
			int granulation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void verify(int runs, boolean contracting) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PrepClasses> getClasses() {
		// TODO Auto-generated method stub
		return null;
	}

}
