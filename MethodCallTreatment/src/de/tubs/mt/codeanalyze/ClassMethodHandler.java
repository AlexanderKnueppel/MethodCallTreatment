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

import org.apache.commons.io.FileUtils;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.Comment;

import de.tubs.mt.files.FileControl;
import de.tubs.mt.files.ListFilesUtil;





public abstract class ClassMethodHandler {

	public static List<String> whiteList = new ArrayList<String>();
	public static String starterMethod;
	public static List<PrepClasses> classList = new ArrayList<PrepClasses>();
	private static List<File> fileList = new ArrayList<File>();

	
	
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
	 * 
	 * @param input
	 * @param seed
	 * @return
	 */
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
						methodList.add(new PrepMethod(method.getName().asString(), method.hasComment()));
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return methodList;
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