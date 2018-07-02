package de.tubs.mt.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class ListFilesUtil {

	
	static List<File> fileList = new ArrayList<File>();
	/**
	 * 
	 * List all the files and folders from a directory
	 * 
	 * @param directoryName
	 *            to be listed
	 * 
	 */
	public static void listFilesAndFolders(String directoryName) {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			System.out.println(file.getName());
		}
	}

	/**
	 * 
	 * List all the files under a directory
	 * 
	 * @param directoryName
	 *            to be listed
	 * 
	 */
	public static void listFiles(String directoryName) {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				System.out.println(file.getName());
			}
		}
	}

	/**
	 * 
	 * List all the folder under a directory
	 * 
	 * @param directoryName
	 *            to be listed
	 * 
	 */
	public static void listFolders(String directoryName) {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isDirectory()) {
				System.out.println(file.getName());
			}
		}
	}

	
	/**
	 * 
	 * List all files from a directory and its subdirectories
	 * 
	 * @param directoryName
	 *            to be listed
	 * 
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
	
	
	public static void clearFileList() {
		fileList.clear();
	}
}