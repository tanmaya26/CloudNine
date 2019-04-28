package com.tanmaya.projects.cloudnine.dao;

import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.*;

import com.tanmaya.projects.cloudnine.bean.FileList;

public class DirectoryDAO {
	Config conf = null;
	public String jdbcURL;
	public String user;
	public String password;
	public String generatedDir;
	public String OS = System.getProperty("os.name").toLowerCase();

	public DirectoryDAO() {
		conf = new Config();
		jdbcURL = conf.getJdbcURL();
		user = conf.getUser();
		password = conf.getPassword();
	}

	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;

	public List<String> listDirectory(String directory) {
		List<String> dirList = new ArrayList<>();
		try {
			// String root = getServletContext().getRealPath("/root");
			File currDir = new File(directory);
			File[] filesList = currDir.listFiles();
			String dirname = "";
			if (filesList == null)
				return dirList;
			for (File f : filesList) {
				if (f.isDirectory()) {
					String absolutePath = f.getPath();
					System.out.println("Directory Path: " + absolutePath);
					String relativePath = absolutePath.substring(absolutePath.indexOf("root"));
					if (OS.indexOf("win") >= 0) {
						dirname = relativePath.substring(relativePath.lastIndexOf("\\") + 1);
					} else {
						dirname = relativePath.substring(relativePath.lastIndexOf("/") + 1);
					}

					dirList.add(dirname);
				}
			}

		} catch (Throwable oops) {
			oops.printStackTrace();
		}
		return dirList;
	}

	public int sizeOfDirInKB(String directory) {
		int size = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(
						"Select size from file_mappings, file_metadata WHERE file_metadata.mapping_id = file_mappings.id AND is_dedup = 0;");
				while (rs.next()) {
					int sizeInKB = Integer.parseInt(rs.getString("size"));
					sizeInKB /= 1000;
					size += sizeInKB;
				}
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
		return size;
	}

	// dirPath must include path and dir name
	public void createDirectory(String absolutePath, String foldername) {
		try {

			String OS = System.getProperty("os.name").toLowerCase();

			if (OS.indexOf("win") >= 0) {
				generatedDir = absolutePath + "\\" + foldername;

			} else {
				generatedDir = absolutePath + "/" + foldername;
			}

			System.out.println("Path from create: " + generatedDir);
			FilenameUtils.separatorsToSystem(generatedDir);
			File dir = new File(generatedDir);
			dir.mkdir();
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Throwable whatever) {
			}
		}
	}

	static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (Throwable whatever) {
			}
		}
	}

	static void close(ResultSet result) {
		if (result != null) {
			try {
				result.close();
			} catch (Throwable whatever) {
			}
		}
	}
}
