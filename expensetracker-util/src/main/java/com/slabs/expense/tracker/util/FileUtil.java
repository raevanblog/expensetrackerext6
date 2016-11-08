package com.slabs.expense.tracker.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link FileUtil} - Utility to work on files and directory
 *
 * @author Shyam Natarajan
 */
public class FileUtil {

	private static final Logger L = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * This method will check for the directory availability and creates the
	 * directory if it is not available based on the create flag.
	 * 
	 * @param directoryPath
	 *            {@link String} - Directory path
	 * @param create
	 *            {@link Boolean} - True to create directory if not present
	 * @return {@link Boolean} - True if the directory is present else false
	 * @throws FileNotFoundException
	 *             - throws {@link FileNotFoundException}
	 */
	public static synchronized boolean isDirectoryAvailable(String directoryPath, boolean... create)
			throws FileNotFoundException {

		File f = new File(directoryPath);
		if (f.exists() && !f.isDirectory()) {
			throw new FileNotFoundException(directoryPath + " is not a directory");
		} else {

			if (f.exists() && f.isDirectory()) {
				L.info(directoryPath + " exists...");
				return true;
			} else if (!f.exists() && create.length == 1 && create[0]) {
				L.info(directoryPath + " does not exist.Creating the directory....");
				f.mkdir();
				return true;
			} else if (create.length == 0) {
				if (!f.exists()) {
					L.info(directoryPath + " does not exist");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method will return an arry of {@link File} inside the directory
	 * 
	 * @param directory
	 *            {@link File} - Directory from which files need to be loaded
	 * @return {@link File} - Array of files
	 * @throws FileNotFoundException
	 *             - throws {@link FileNotFoundException}
	 */
	public static synchronized File[] loadFiles(File directory) throws FileNotFoundException {

		return loadFiles(directory, true);

	}

	/**
	 * This method will load the files/directory inside a directory
	 * 
	 * @param directory
	 *            {@link File} - Directory from which files/directory need to be
	 *            loaded
	 * @param includeDirectory
	 *            {@link Boolean} - True to include the directory while loading
	 * @return {@link File} - Array of files
	 * @throws FileNotFoundException
	 *             - throws {@link FileNotFoundException}
	 */
	public static synchronized File[] loadFiles(File directory, boolean includeDirectory)
			throws FileNotFoundException {

		if (directory.exists() && directory.isDirectory()) {
			return directory.listFiles(new DirectoryIncludeFilter(includeDirectory));
		}

		throw new FileNotFoundException(directory.getPath() + "is not a directory");
	}

	/***
	 *
	 * This method loads the files under the specified directory with the
	 * specified extension and returns an array of <code>File</code> objects
	 *
	 * @param directory
	 *            - Directory from which the files to be loaded
	 * @param extension
	 *            - Extension of the file inside the directory
	 * @return - Array of file objects.
	 * @throws FileNotFoundException
	 *             - throws {@link FileNotFoundException}
	 * 
	 */
	public static synchronized File[] loadFilesWithExtension(File directory,
			boolean includeExtension, String... extension) throws FileNotFoundException {

		if (directory.exists() && directory.isDirectory()) {
			if (extension != null && extension.length > 0) {
				return directory
						.listFiles(new FileExtensionIncludeFilter(includeExtension, extension));
			} else {
				return directory.listFiles();
			}
		}
		throw new FileNotFoundException(directory.getPath() + "is not a directory");
	}

	/**
	 * This method will load the specified file inside the directory
	 * 
	 * @param directory
	 *            {@link File} - Directory from which the file need to be loaded
	 * @param fileName
	 *            {@link String} - Name of the file
	 * @return {@link File} - File
	 * @throws FileNotFoundException
	 *             - throws {@link FileNotFoundException}
	 */
	public static synchronized File loadFile(File directory, String fileName)
			throws FileNotFoundException {

		if (directory.exists() && directory.isDirectory()) {
			File[] files = loadFilesWithExtension(directory, true);
			for (File file : files) {
				if (file.getName().equals(fileName)) {
					return file;
				}
			}
		}
		return null;
	}

	/**
	 * This method returns a file object creating a file under the specified
	 * directory if file does not exist.
	 * 
	 * @param directory
	 *            {@link File} - Directory, inside which the file need to be
	 *            created
	 * @param fileName
	 *            - {@link String} - File name
	 * @return {@link File} - File that is created
	 * @throws IOException
	 *             - throws {@link IOException}
	 * 
	 */
	public static synchronized File createFile(File directory, String fileName) throws IOException {

		File f = new File(directory, fileName);
		if (!f.exists()) {
			f.createNewFile();
			return f;
		}
		return null;
	}

	/**
	 * This method will create a directory inside the specified directory
	 * 
	 * @param directory
	 *            {@link File} - Directory inside which the new directory need
	 *            to be created
	 * @param directoryName
	 *            {@link String} - Directory name
	 * @return {@link Boolean} - True if the directory is created else False
	 * @throws IOException
	 *             - throws {@link IOException}
	 */
	public static synchronized boolean createDirectory(File directory, String directoryName)
			throws IOException {

		File f = new File(directory, directoryName);
		if (!f.exists()) {
			return f.mkdir();
		} else {
			return false;
		}

	}

	/**
	 * This method will delete the specified {@link File}
	 * 
	 * @param file
	 *            {@link File} - File to delete
	 * @return {@link Boolean} - True if the file is deleted else False
	 * @throws IOException
	 *             - throws {@link IOException}
	 */
	public static synchronized boolean deleteFile(File file) throws IOException {

		if (file.exists()) {
			if (file.isDirectory()) {
				if (file.listFiles().length == 0) {
					return file.delete();
				} else if (file.listFiles().length > 0) {
					File[] children = file.listFiles();
					for (File child : children) {
						deleteFile(child);
					}
					return file.delete();
				}
			} else {
				return file.delete();
			}
		}

		return false;
	}

	/**
	 * This method will move the specified directory/file to the target
	 * directory
	 * 
	 * @param srcFile
	 *            {@link File} - Source file/directory
	 * @param destFile
	 *            {@link File} - Destination directory
	 * @return {@link Boolean} - True if the directory is moved else false
	 * @throws IOException
	 *             - throws {@link IOException}
	 */
	public static synchronized boolean moveFile(File srcFile, File destFile) throws IOException {

		if (srcFile.exists() && destFile.exists()) {
			if (srcFile.isDirectory() && destFile.isDirectory()) {
				FileUtils.moveDirectoryToDirectory(srcFile, destFile, false);
			} else if (srcFile.isFile() && destFile.isDirectory()) {
				FileUtils.moveFileToDirectory(srcFile, destFile, false);
			}
			return true;
		}

		return false;
	}

	/***
	 * This method takes a directory path and creates the directories if it does
	 * not exist and creates the specified file under the directory.
	 *
	 * @param directoryPath
	 *            - Directory path in which the file is to be created
	 * @param fileName
	 *            - File name;
	 * @return {@link File} - File that is created
	 * @throws IOException
	 *             - throws {@link IOException}
	 */
	public static synchronized File createFile(String directoryPath, String fileName)
			throws IOException {

		File dir = new File(directoryPath);
		if (!dir.exists()) {
			dir.mkdir();
		}
		File file = new File(dir, fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	/**
	 * This methos will read the specified file
	 * 
	 * @param file
	 *            {@link File} - File to read
	 * @return {@link String} - File content as String
	 */
	public static synchronized String readFile(File file) {

		BufferedReader reader = null;
		if (file.exists()) {
			if (file.isFile()) {
				try {
					StringBuffer fileString = new StringBuffer();
					String temp = "";
					reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
					while ((temp = reader.readLine()) != null) {
						fileString.append(temp);
					}
					return fileString.toString();
				} catch (IOException ex) {
					L.error("Error reading file {}, exception {}", file.getName(), ex);
				} finally {
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (IOException ex) {
						L.error("Error closing stream {}", ex);
					}
				}
			}
		}
		return "";
	}

	/**
	 * This method will read the specified files and return the response as list
	 * of lines
	 * 
	 * @param file
	 *            {@link File} - File to read
	 * @return {@link String} - List of lines from the file
	 */
	public static synchronized List<String> readLines(File file) {

		BufferedReader reader = null;
		if (file.exists()) {
			if (file.isFile()) {
				try {
					List<String> fileString = new ArrayList<String>();
					String temp = "";
					reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
					while ((temp = reader.readLine()) != null) {
						fileString.add(temp);
					}
					return fileString;
				} catch (IOException ex) {
					L.error("Error reading file {}, exception {}", file.getName(), ex);
				} finally {
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (IOException ex) {
						L.error("Error closing stream {}", ex);
					}
				}
			}
		}
		return Collections.emptyList();
	}

	/**
	 * This method will read the file from classpath
	 * 
	 * @param fileName
	 *            {@link String} - File name to read from classpath
	 * @return {@link String} - File content as String
	 */
	public static synchronized String readFileFromClassPath(String fileName) {

		BufferedReader reader = null;
		try {
			InputStream stream = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
			reader = new BufferedReader(new InputStreamReader(stream));
			StringBuffer fileString = new StringBuffer();
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				fileString.append(temp);
			}

			return fileString.toString();
		} catch (IOException ex) {
			L.error("Error reading file from classpath {}, exception {}", fileName, ex);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException ex) {
				L.error("Error closing stream {}", ex);
			}
		}

		return "";
	}

}