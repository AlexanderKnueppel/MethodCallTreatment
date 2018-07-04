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

public abstract class JMLManipulator {

	public static List<String> whiteList = new ArrayList<String>();
	public static List<String> blackList = new ArrayList<String>();
	private static int specListLength;
	private static int methodListLength;
	private static int whiteListLength;
	private static int toDelMethods;
	private static String starterMethod;

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

	public static void clearBlackList() {
		blackList.clear();
	}

	public static void setStaterMethdod(String starter) {
		starterMethod = starter;
	}

	static void setWhiteListLength() {
		whiteListLength = whiteList.size();
	}

	/**
	 * (1) fist init Whitelist
	 * 
	 * @param pmList
	 */
	public static void setWhiteList(List<PrepMethod> pmList) {
		whiteList.clear();
		for (PrepMethod pm : pmList) {
			if (pm.jml) {
				whiteList.add(pm.name);
			}
		}
	}

	static void setToDelMethods(int specPerc) {
		double tmp = (double) methodListLength * specPerc / 100;
		toDelMethods = (int) (specListLength - Math.ceil(tmp) - blackList
				.size());
		if (toDelMethods >= specListLength) {
			toDelMethods = specListLength - 1;
		}
	}

	static void setMethodListLength(List<PrepMethod> pmList) {
		methodListLength = pmList.size();
	}

	static void setSpecListLength(List<PrepMethod> pmList) {
		int tmp = 0;
		for (PrepMethod pm : pmList) {
			if (pm.jml) {
				tmp++;
			}
		}
		specListLength = tmp;
	}

	public static void iterateClasses(List<PrepClasses> pcList) {
		for (PrepClasses pc : pcList) {
			recreateJavaFile(pc);
		}
	}

	static void recreateJavaFile(PrepClasses pc) {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
