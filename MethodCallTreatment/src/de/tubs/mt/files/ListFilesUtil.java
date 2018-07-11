package de.tubs.mt.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * The Class ListFilesUtil.
 */
public abstract class ListFilesUtil {

	
	/** The file list. */
	private static List<File> fileList = new ArrayList<File>();

	
	/**
	 * List all files from a directory and its subdirectories.
	 *
	 * @param directory the directory
	 * @return the list
	 */
	public static List<File> listFilesAndFilesSubDirectories(File directory) {
		clearFileList();
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				fileList.add(file);
			} else if (file.isDirectory()) {
				listFilesAndFilesSubDirectories(file);
			}
		}
		return fileList;
	}
	
	
	/**
	 * Clear file list.
	 */
	private static void clearFileList() {
		fileList.clear();
	}
}