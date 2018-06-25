package de.tubs.mt.codegen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.tubs.mt.codegen.add.BroadCodeGenerator;
import de.tubs.mt.codegen.bubble.BubbleCodeGenerator;


public class Incrementer {

	public static int counter = 0;

	public static int numberOfMethods(int level, int width) {
		return (int) Math.pow(width, level - 1);
	}

	public static int totalNumberOfMethods(int width, int depth) {
		int res = 0;
		for (int i = 1; i <= depth; ++i)
			res += numberOfMethods(i, width);
		return res;
	}




	public static void generateProgramForAdd(final int width, final int depth, int seed, String path)
			throws FileNotFoundException {

		int total = totalNumberOfMethods(width, depth) + 1;
		System.out.println("Total number of methods: " + total);

		counter = 1;

		TreeCodeGenerator cg = new BroadCodeGenerator();

		String name = "AddDepth" + (depth) + "Width" + (width);

		File f = new File(path + "/" + seed);
		f.mkdir();
		f = new File(path + "/" + seed + "/" + name + ".java");
		f.delete();
		final FileOutputStream fos = new FileOutputStream(f);

		try {
			cg.generateCode(fos, depth, width, name);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		

	}

	public static void generateProgramForBubbleSort(final int width, final int depth, int seed,
			String path) throws FileNotFoundException {

		int total = totalNumberOfMethods(width, depth) + 1;
		System.out.println("Total number of methods " + total);

		counter = 1;
		
		final TreeCodeGenerator cg = new BubbleCodeGenerator();

		String name = "BubbleSortDepth" + (depth) + "Width" + (width);

		File f = new File(path + "/" + seed);
		f.mkdir();
		f = new File(path + "/" + seed + "/" + name + ".java");
		f.delete();
		final FileOutputStream fos = new FileOutputStream(f);

		try {
			cg.generateCode(fos, depth, width, name);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

		System.out.println("created: " + f.getAbsolutePath());
	}

}