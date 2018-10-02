package de.tubs.mt.codegen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.tubs.mt.codegen.add.BroadCodeGenerator;
import de.tubs.mt.codegen.add.TinyCodeGenerator;
import de.tubs.mt.codegen.bubble.BubbleCodeGenerator;

// TODO: Auto-generated Javadoc
/**
 * The Class Incrementer.
 */
public class Incrementer {

	/** The counter. */
	public static int counter = 0;

	/** The jml white list. */
	public static List<Integer> jmlWhiteList = new ArrayList<Integer>();

	/**
	 * Gets the complete.
	 *
	 * @param depth the depth
	 * @return the complete
	 */
	public static List<Integer> getComplete(int depth) {
		List<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i <= depth; ++i) {
			res.add(i);
		}

		return res;
	}

	/**
	 * Number of methods.
	 * 
	 * @param level
	 *            the level
	 * @param width
	 *            the width
	 * @return the int
	 */
	private static int numberOfMethods(int level, int width) {
		return (int) Math.pow(width, level - 1);
	}

	/**
	 * Total number of methods.
	 * 
	 * @param width
	 *            the width
	 * @param depth
	 *            the depth
	 * @return the int
	 */
	private static int totalNumberOfMethods(int width, int depth) {
		int res = 0;
		for (int i = 1; i <= depth; ++i)
			res += numberOfMethods(i, width);
		return res;
	}

	/**
	 * Generate program for add.
	 *
	 * @param width            the width
	 * @param depth            the depth
	 * @param seed            the seed
	 * @param path            the path
	 * @param name            the name
	 * @param isTree the is tree
	 * @throws FileNotFoundException             the file not found exception
	 */
	public static void generateProgramForAdd(final int width, final int depth,
			int seed, String path, String name, boolean isTree)
			throws FileNotFoundException {

		int total = totalNumberOfMethods(width, depth) + 1;
		System.out.println("Total number of methods: " + total);
		TreeCodeGenerator cg;
		if (isTree) {
			cg = new BroadCodeGenerator();
		} else {
			counter = 1;
			jmlWhiteList = getComplete(depth);
			cg = new TinyCodeGenerator();
		}

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

	/**
	 * Generate program for bubble sort.
	 * 
	 * @param width
	 *            the width
	 * @param depth
	 *            the depth
	 * @param seed
	 *            the seed
	 * @param path
	 *            the path
	 * @param name
	 *            the name
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public static void generateProgramForBubbleSort(final int width,
			final int depth, int seed, String path, String name)
			throws FileNotFoundException {

		int total = totalNumberOfMethods(width, depth) + 1;
		System.out.println("Total number of methods " + total);

		counter = 1;

		final TreeCodeGenerator cg = new BubbleCodeGenerator();

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
