package de.tubs.mt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class FileControl {
	
	private static final String resultsTXT = "Results.txt";
	private static final String tmp_folder = "tmp";
	public static final String PATH = "Results";
	public static final String FILE = PATH + "/cache.txt";
	private static File testclassesPath = new File("Testclasses"); 
	
	
	public static void initStructure() throws IOException {
		File tmp = getTmpPath();

		if (tmp.exists()) {
			deleteRecursevly(tmp);
		}

		tmp.mkdirs();

		if (!getResultHandle().exists()) {
			getResultHandle().createNewFile();
		}
	}
	
	public static void deleteRecursevly(File f) {
		if (f.isDirectory()) {
			for (File subFile : f.listFiles()) {
				deleteRecursevly(subFile);
			}
		}
		f.delete();
	}
	
	public static void createFile() {
		if (!new File(PATH).exists())
			new File(PATH).mkdir();

		if (!new File(FILE).exists()) {
			try {
				new File(FILE).createNewFile();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}
	
	public static File getResultHandle() {
		return new File(PATH.replaceAll("/", "") + "/" + resultsTXT);
	}
	
	public static File getTmpPath() {
		return new File(PATH.replaceAll("/", "") + "/" + tmp_folder.replaceAll("/", "") + "/");
	}
	
	public static File getTopPath() {
		return new File(PATH.replaceAll("/", "") + "/");
	}

	public static String getPath() {
		return PATH;
	}
	
	public static FileInputStream getFileInput(String input) throws FileNotFoundException {
		return new FileInputStream(input);
	}
	
	public static void printFile(String output, List<String> lines) throws IOException {
		Files.write(Paths.get(output), lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE);
	}
	
	public static File getTestclassesPath() {
		return testclassesPath;
	}
	
	public static String getExcelPath(String verifyEffort) {
		return PATH + "/results_" + verifyEffort + ".xls";
	}

}
