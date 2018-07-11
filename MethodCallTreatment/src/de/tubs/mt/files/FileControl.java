package de.tubs.mt.files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * The Class FileControl.
 */
public abstract class FileControl {
	
	/** The Constant resultsTXT. */
	private static final String resultsTXT = "Results.txt";
	
	/** The Constant tmp_folder. */
	private static final String tmp_folder = "tmp";
	
	/** The Constant prep_folder. */
	private static final String prep_folder = "prep";
	
	/** The Constant exec_folder. */
	private static final String exec_folder = "exec";
	
	/** The Constant RESULT_PATH. */
	private static final String RESULT_PATH = "Results";
	
	
	
	/**
	 * Creates the result file.
	 *
	 * @param lines the lines
	 */
	public static void createResultFile(List<String> lines) {
		try {
			Files.write(Paths.get(FileControl.getResultHandle().getPath()), lines, Charset.forName("UTF-8"),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Inits the structure.
	 */
	public static void initStructure() {
		File tmp = getTmpPath();
		if (tmp.exists()) {
			deleteRecursevly(tmp);
		}
		tmp.mkdirs();
		rebuildExecPath();
		rebuildPrepPath();
		
		if (!getResultHandle().exists()) {
			try {
				getResultHandle().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Rebuild prep path.
	 */
	private static void rebuildPrepPath() {
		File prep = getPrepPath();
		if(prep.exists()) {
			deleteRecursevly(prep);
		}
		prep.mkdirs();
	}
	
	/**
	 * Rebuild exec path.
	 */
	public static void rebuildExecPath() {
		File exec = getExecPath();
		if(exec.exists()) {
			deleteRecursevly(exec);
		}
		exec.mkdir();
	}
	
	/**
	 * Delete recursevly.
	 *
	 * @param f the f
	 */
	private static void deleteRecursevly(File f) {
		if (f.isDirectory()) {
			for (File subFile : f.listFiles()) {
				deleteRecursevly(subFile);
			}
		}
		f.delete();
	}
	
	/**
	 * Creates the result file path.
	 */
	public static void createResultFilePath() {
		if (!new File(RESULT_PATH).exists()) {
			new File(RESULT_PATH).mkdir();
		}		
	}
	
	
	/**
	 * Gets the result handle.
	 *
	 * @return the result handle
	 */
	private static File getResultHandle() {
		return new File(RESULT_PATH.replaceAll("/", "") + "/" + resultsTXT);
	}
	
	/**
	 * Gets the tmp path.
	 *
	 * @return the tmp path
	 */
	private static File getTmpPath() {
		return new File(RESULT_PATH.replaceAll("/", "") + "/" + tmp_folder.replaceAll("/", "") + "/");
	}
	
	/**
	 * Gets the prep path.
	 *
	 * @return the prep path
	 */
	public static File getPrepPath() {
		return new File(RESULT_PATH.replaceAll("/", "") + "/" + tmp_folder.replaceAll("/", "") + "/" + prep_folder.replaceAll("/", "") + "/");
	}
	
	/**
	 * Gets the exec path.
	 *
	 * @return the exec path
	 */
	public static File getExecPath() {
		return new File(RESULT_PATH.replaceAll("/", "") + "/" + tmp_folder.replaceAll("/", "") + "/" + exec_folder.replaceAll("/", "") + "/");
	}
	
	/**
	 * Gets the excel path.
	 *
	 * @param verifyEffort the verify effort
	 * @return the excel path
	 */
	public static String getExcelPath(String verifyEffort) {
		return RESULT_PATH + "/results_" + verifyEffort + ".xls";
	}

}
