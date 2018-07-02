package de.tubs.mt.codegen;

import java.io.FileNotFoundException;

import de.tubs.mt.files.FileControl;

public abstract class CallGenerator {

	public static enum Program {
		ADD, BUBBLESORT, OWN
	}

	
	/**
	 * random specified, runs >=1, completeSpec = false
	 * 
	 * @param program
	 * @param width
	 * @param depth
	 * @param seed
	 */
	public static void callProgramGenerator(Program program, int width, int depth, int seed, int run, String name) {
		try {
			if (program == Program.ADD) {
				Incrementer.generateProgramForAdd(width, depth, seed, FileControl.getPrepPath().getPath(), name);
			} else {
				Incrementer.generateProgramForBubbleSort(width, depth, seed,
						FileControl.getPrepPath().getPath(), name);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
