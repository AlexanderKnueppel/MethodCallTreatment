package de.tubs.mt;

import java.io.FileNotFoundException;

public abstract class CallGenerator {

	public static enum Program {
		ADD, BUBBLESORT
	}

	/**
	 * full specified, runs = 1, completeSpec = true
	 * 
	 * @param seed
	 * @param width
	 * @param depth
	 */
	public static void callFullSpecifiedProgramGenerator(Program program, int seed, int width, int depth) {
		try {
			if (program == Program.BUBBLESORT)
				Incrementer.generateFullySpecifiedProgramBubble(width, depth, seed, FileControl.getTmpPath().getPath());
			else
				Incrementer.generateFullySpecifiedProgramAdd(width, depth, seed, FileControl.getTmpPath().getPath());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
	/**
	 * random specified, runs >=1, completeSpec = false
	 * 
	 * @param program
	 * @param width
	 * @param depth
	 * @param seed
	 */
	public static void callRandomSpecifiedProgramGenerator(Program program, int width, int depth, int seed, int run) {
		try {
			if (program == Program.ADD) {
				Incrementer.generatRandomSpecifiedProgramsForAdd(width, depth, seed, FileControl.getTmpPath().getPath(),
						(run > 0 ? false : true));
			} else {
				Incrementer.generatRandomSpecifiedProgramsForBubbleSort(width, depth, seed,
						FileControl.getTmpPath().getPath(), (run > 0 ? false : true));
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
