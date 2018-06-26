package de.tubs.mt.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class FileControl {
	
	private static final String resultsTXT = "Results.txt";
	private static final String tmp_folder = "tmp";
	private static final String prep_folder = "prep";
	private static final String exec_folder = "exec";
	private static final String RESULT_PATH = "Results";
	private static File testclassesPath = new File("Testclasses"); 
	
	
	public static void createResultFile(List<String> lines) {
		try {
			Files.write(Paths.get(FileControl.getResultHandle().getPath()), lines, Charset.forName("UTF-8"),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void initStructure() throws IOException {
		File tmp = getTmpPath();

		if (tmp.exists()) {
			deleteRecursevly(tmp);
		}

		tmp.mkdirs();
		
		rebuildExecPath();
		
		rebuildPrepPath();
		
		
		if (!getResultHandle().exists()) {
			getResultHandle().createNewFile();
		}
	}
	
	private static void rebuildPrepPath() {
		File prep = getPrepPath();
		if(prep.exists()) {
			System.out.println("delete");
			deleteRecursevly(prep);
		}
		prep.mkdirs();
	}
	
	public static void rebuildExecPath() {
		File exec = getExecPath();
		if(exec.exists()) {
			deleteRecursevly(exec);
		}
		exec.mkdir();
	}
	
	private static void deleteRecursevly(File f) {
		if (f.isDirectory()) {
			for (File subFile : f.listFiles()) {
				deleteRecursevly(subFile);
			}
		}
		f.delete();
	}
	
	public static void createResultFilePath() {
		if (!new File(RESULT_PATH).exists()) {
			new File(RESULT_PATH).mkdir();
		}		
	}
	
	
	private static File getResultHandle() {
		return new File(RESULT_PATH.replaceAll("/", "") + "/" + resultsTXT);
	}
	
	private static File getTmpPath() {
		return new File(RESULT_PATH.replaceAll("/", "") + "/" + tmp_folder.replaceAll("/", "") + "/");
	}
	
	public static File getPrepPath() {
		return new File(RESULT_PATH.replaceAll("/", "") + "/" + tmp_folder.replaceAll("/", "") + "/" + prep_folder.replaceAll("/", "") + "/");
	}
	
	public static File getExecPath() {
		return new File(RESULT_PATH.replaceAll("/", "") + "/" + tmp_folder.replaceAll("/", "") + "/" + exec_folder.replaceAll("/", "") + "/");
	}
	
	
	public static File getTestclassesPath() {
		return testclassesPath;
	}
	
	public static String getExcelPath(String verifyEffort) {
		return RESULT_PATH + "/results_" + verifyEffort + ".xls";
	}

}
