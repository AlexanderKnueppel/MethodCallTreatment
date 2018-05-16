package de.tubs.mt;

import java.io.File;
import java.io.IOException;

public abstract class FileControl {
	
	private static String path;
	private static String resultsTXT = "Results.txt";
	private static String tmp_folder = "tmp";
	
	
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
	
	public static File getResultHandle() {
		return new File(path.replaceAll("/", "") + "/" + resultsTXT);
	}
	
	public static File getTmpPath() {
		return new File(path.replaceAll("/", "") + "/" + tmp_folder.replaceAll("/", "") + "/");
	}
	
	public File getTopPath() {
		return new File(path.replaceAll("/", "") + "/");
	}

	

}
