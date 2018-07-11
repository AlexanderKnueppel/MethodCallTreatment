package de.tubs.mt.codeanalyze;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

import de.tubs.mt.files.FileControl;


/**
 * The Class JMLManipulator.
 */
public abstract class JMLManipulator {

	/** The white list. */
	private static List<String> whiteList = new ArrayList<String>();
	
	/** The black list. */
	private static List<String> blackList = new ArrayList<String>();
	
	/** The spec list length. */
	private static int specListLength;
	
	/** The method list length. */
	private static int methodListLength;
	
	/** The white list length. */
	private static int whiteListLength;
	
	/** The to del methods. */
	private static int toDelMethods;
	
	/** The starter method. */
	private static String starterMethod;

	/**
	 * Sets the black list.
	 *
	 * @param specPerc the spec perc
	 * @param pmList the pm list
	 * @param strategy the strategy
	 */
	public static void setBlackList(int specPerc, List<PrepMethod> pmList,
			String strategy) {
		setMethodListLength(pmList);
		setSpecListLength(pmList);
		setToDelMethods(specPerc);
		setWhiteListLength();

		int tmp = 0;
		int next = 0;

		while (tmp != toDelMethods) {
			if (whiteList.size() == 1) {
				tmp++;
			} else {
				next = (int) (Math.random() * whiteListLength);
				if (!whiteList.get(next).equals(starterMethod)) {
					blackList.add(whiteList.get(next));
					whiteList.remove(next);
					setWhiteListLength();
					tmp++;
				}
			}
		}
		System.out.println("Specification: " + specPerc + "%");
		System.out.println("BlackList: " + blackList.toString());
		System.out.println("WhiteList: " + whiteList.toString());

	}

	/**
	 * Clear black list.
	 */
	public static void clearBlackList() {
		blackList.clear();
	}

	/**
	 * Sets the stater methdod.
	 *
	 * @param starter the new stater methdod
	 */
	public static void setStaterMethdod(String starter) {
		starterMethod = starter;
	}

	/**
	 * Sets the white list length.
	 */
	private static void setWhiteListLength() {
		whiteListLength = whiteList.size();
	}

	/**
	 * (1) fist init Whitelist.
	 *
	 * @param pmList the new white list
	 */
	public static void setWhiteList(List<PrepMethod> pmList) {
		whiteList.clear();
		for (PrepMethod pm : pmList) {
			if (pm.jml) {
				whiteList.add(pm.name);
			}
		}
	}

	/**
	 * Sets the to del methods.
	 *
	 * @param specPerc the new to del methods
	 */
	private static void setToDelMethods(int specPerc) {
		double tmp = (double) methodListLength * specPerc / 100;
		toDelMethods = (int) (specListLength - Math.ceil(tmp) - blackList
				.size());
		if (toDelMethods >= specListLength) {
			toDelMethods = specListLength - 1;
		}
	}

	/**
	 * Sets the method list length.
	 *
	 * @param pmList the new method list length
	 */
	private static void setMethodListLength(List<PrepMethod> pmList) {
		methodListLength = pmList.size();
	}

	/**
	 * Sets the spec list length.
	 *
	 * @param pmList the new spec list length
	 */
	private static void setSpecListLength(List<PrepMethod> pmList) {
		int tmp = 0;
		for (PrepMethod pm : pmList) {
			if (pm.jml) {
				tmp++;
			}
		}
		specListLength = tmp;
	}

	/**
	 * Iterate classes.
	 *
	 * @param pcList the pc list
	 */
	public static void iterateClasses(List<PrepClasses> pcList) {
		for (PrepClasses pc : pcList) {
			recreateJavaFile(pc);
		}
	}

	/**
	 * Recreate java file.
	 *
	 * @param pc the pc
	 */
	private static void recreateJavaFile(PrepClasses pc) {

		try {
			FileInputStream in = new FileInputStream(pc.path);
			CompilationUnit cu = JavaParser.parse(in);

			NodeList<TypeDeclaration<?>> types = cu.getTypes();

			for (TypeDeclaration<?> type : types) {
				NodeList<BodyDeclaration<?>> members = type.getMembers();
				for (BodyDeclaration<?> member : members) {
					if (member instanceof MethodDeclaration) {
						MethodDeclaration method = (MethodDeclaration) member;
						if (blackList.contains(method.getName().asString())) {
							method.removeComment();
						}

					}
				}

				List<String> lines = new LinkedList<String>();
				lines.add(cu.toString());

				File f = new File(FileControl.getExecPath().getPath() + "/");
				if (!f.exists()) {
					f.mkdir();
				}

				Files.write(
						Paths.get(FileControl.getExecPath().getPath() + "/"
								+ pc.name), lines, Charset.forName("UTF-8"),
						StandardOpenOption.CREATE);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
