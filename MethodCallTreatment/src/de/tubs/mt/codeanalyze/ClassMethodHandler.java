package de.tubs.mt.codeanalyze;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

import de.tubs.mt.files.ListFilesUtil;




/**
 * The Class ClassMethodHandler.
 */
public abstract class ClassMethodHandler {

	/** The class list. */
	private static List<PrepClasses> classList = new ArrayList<PrepClasses>();
	
	/** The file list. */
	private static List<File> fileList = new ArrayList<File>();

	
	
	/**
	 * Gets the class list.
	 *
	 * @param input the input
	 * @return the class list
	 */
	public static List<PrepClasses> getClassList(File input) {
		classList.clear();

		if (input.isFile()) {
			classList.add(new PrepClasses(input.getName(), getMethodList(input.getAbsolutePath()), input.getAbsolutePath()));
		} else {
			fileList = ListFilesUtil.listFilesAndFilesSubDirectories(input);
			for (File file : fileList) {
				classList.add(
						new PrepClasses(file.getName(), getMethodList(file.getAbsolutePath()), file.getAbsolutePath()));
			}
		}

		return classList;
	}

	/**
	 * Gets the method list.
	 *
	 * @param input the input
	 * @return the method list
	 */
	@SuppressWarnings("deprecation")
	public static List<PrepMethod> getMethodList(String input) {
		List<PrepMethod> methodList = new ArrayList<PrepMethod>();

		try {
			FileInputStream in = new FileInputStream(input);
			CompilationUnit cu = JavaParser.parse(in);

			NodeList<TypeDeclaration<?>> types = cu.getTypes();

			for (TypeDeclaration<?> type : types) {
				NodeList<BodyDeclaration<?>> members = type.getMembers();
				for (BodyDeclaration<?> member : members) {
					if (member instanceof MethodDeclaration) {
						MethodDeclaration method = (MethodDeclaration) member;
						methodList.add(new PrepMethod(method.getName().asString(), method.hasComment(), method.getParameters().toString(), method.toString()));
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return methodList;
	}

	/**
	 * Gets the specification in percent.
	 *
	 * @param methodList the method list
	 * @return the spec percent
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