package com.slabs.expensetracker.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

/**
 * {@link FileExtensionIncludeFilter} is a file extension filter implementing
 * {@link FilenameFilter}
 *
 * @author Shyam Natarajan
 */
public class FileExtensionIncludeFilter implements FilenameFilter {

	private List<String> extensions;

	private boolean isInclude;

	public FileExtensionIncludeFilter(boolean isInclude, String... extensions) {

		this.extensions = Arrays.asList(extensions);
		this.isInclude = isInclude;
	}

	public boolean accept(File dir, String name) {

		String fileExtension = name.substring(name.lastIndexOf("."));

		if (isInclude) {
			return extensions.contains(fileExtension);
		} else {
			return !extensions.contains(fileExtension);
		}

	}
}