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
import com.github.javaparser.ast.comments.Comment;

import de.tubs.mt.FileControl;

public abstract class MethodPrinter {

	/**
	 * 
	 * @param input
	 * @param seed
	 * @return
	 */
	public static List<PrepMethod> getMethodList(String input, int seed) {
		List<PrepMethod> methodList = new ArrayList<PrepMethod>();

		try {
			FileInputStream in = new FileInputStream(FileControl.getPrepPath().getPath() + "/" + seed + "/" + input);
			CompilationUnit cu = JavaParser.parse(in);

			NodeList<TypeDeclaration<?>> types = cu.getTypes();

			for (TypeDeclaration<?> type : types) {
				NodeList<BodyDeclaration<?>> members = type.getMembers();
				for (BodyDeclaration<?> member : members) {
					if (member instanceof MethodDeclaration) {
						MethodDeclaration method = (MethodDeclaration) member;
						methodList.add(new PrepMethod(method.getName().asString(), method.hasComment()));
					}
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return methodList;
	}

	/**
	 * 
	 * @param input
	 * @param seed
	 * @throws Exception
	 */
	public static void moveOwnJavaClassToPrep(String input, int seed) throws Exception {
		FileInputStream in = new FileInputStream("TestClasses/" + input);

		CompilationUnit cu = JavaParser.parse(in);
		List<Comment> lc = cu.getComments();

		List<String> lines = new LinkedList<String>();
		lines.add(cu.toString());

		File f = new File(FileControl.getPrepPath().getPath() + "/" + seed);
		f.mkdir();

		Files.write(Paths.get(FileControl.getPrepPath().getPath() + "/" + seed + "/" + input), lines,
				Charset.forName("UTF-8"), StandardOpenOption.CREATE);
	}

	/**
	 * 
	 * @param seed
	 * @param startPercent
	 * @param endPercent
	 * @param methodName
	 * @param whitelist
	 * @param methodList
	 * @throws IOException
	 */
	public static void recreateJavaFile(int seed, final int perc, String methodName, String whitelist, List<PrepMethod> methodList)
			throws IOException {
		FileControl.rebuildExecPath();
		int size = methodList.size();
		int blackSize = getBlackSize(methodList);

		try {
			int delMethods = perc != 0 ? ((int) (size - Math.floor(size * perc / 100))) : size - 1;
			delMethods = delMethods == size ? delMethods - (blackSize + 1) : delMethods - blackSize;
			List<String> blacklist = getJMLBlackList(methodList, whitelist, delMethods);
			System.out.println("Blacklist for " + perc + "% Spec " + blacklist.toString());
			deleteJML(blacklist, seed, methodName, perc);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static int getBlackSize(List<PrepMethod> methodList) {
		int tmp = 0;
		for (PrepMethod pm : methodList) {
			tmp = !pm.jml ? tmp + 1 : tmp;
		}
		return tmp;
	}

	/**
	 * 
	 * @param methodList
	 * @param whitelist
	 * @param delMethods
	 * @return
	 */
	private static List<String> getJMLBlackList(List<PrepMethod> methodList, String whitelist, int delMethods) {
		int tmp = 0;
		int next;
		int size = methodList.size();
		List<String> blackList = new ArrayList<String>();

		for (PrepMethod pm : methodList) {
			if (!pm.jml)
				blackList.add(pm.name);
		}

		while (tmp != delMethods) {
			next = (int) (Math.random() * size);
			if (methodList.get(next).jml && !methodList.get(next).name.equals(whitelist)
					&& !blackList.contains(methodList.get(next).name)) {
				blackList.add(methodList.get(next).name);
				tmp++;
			}
		}
		return blackList;

	}

	/**
	 * 
	 * @param blacklist
	 * @param seed
	 * @param methodName
	 * @param percent
	 * @throws IOException
	 */
	private static void deleteJML(List<String> blacklist, int seed, String methodName, int percent) throws IOException {
		FileInputStream in = new FileInputStream(FileControl.getPrepPath().getPath() + "/" + seed + "/" + methodName);
		CompilationUnit cu = JavaParser.parse(in);

		NodeList<TypeDeclaration<?>> types = cu.getTypes();

		for (TypeDeclaration<?> type : types) {
			NodeList<BodyDeclaration<?>> members = type.getMembers();
			for (BodyDeclaration<?> member : members) {
				if (member instanceof MethodDeclaration) {
					MethodDeclaration method = (MethodDeclaration) member;
					if (blacklist.contains(method.getName().asString())) {
						method.removeComment();
					}

				}
			}

			List<String> lines = new LinkedList<String>();
			lines.add(cu.toString());

			File f = new File(FileControl.getExecPath().getPath() + "/" + seed);
			f.mkdir();
			String name = "P" + percent + methodName;

			Files.write(Paths.get(FileControl.getExecPath().getPath() + "/" + seed + "/" + name), lines,
					Charset.forName("UTF-8"), StandardOpenOption.CREATE);

		}
	}

	/**
	 * 
	 * @param methodList
	 * @return
	 */
	public static int getSpecPercent(List<PrepMethod> methodList) {
		int size = methodList.size();
		int specMethod = 0;
		for (PrepMethod pm : methodList) {
			if (pm.jml) {
				specMethod++;
			}
		}
		return specMethod * 100 / size;
	}
}