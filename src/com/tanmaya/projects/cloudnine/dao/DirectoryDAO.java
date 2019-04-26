package com.tanmaya.projects.cloudnine.dao;

import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List; 

import com.tanmaya.projects.cloudnine.bean.FileMeta;

public class DirectoryDAO {
	public static final String jdbcURL = "jdbc:mysql://localhost:3306/photobucket";
	public static final String user = "root";
	public static final String password = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;

	public List<String> listDirectory(String directory) {
		List <String> dirList = new ArrayList<>();
		try {
			//String root = getServletContext().getRealPath("/root");
			File currDir = new File(directory);
			File[] filesList = currDir.listFiles();
			if(filesList == null)
				return dirList;
			for(File f : filesList) {
				if(f.isDirectory()) {
					dirList.add(f.getPath());
				}
			}
			
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
		return dirList;
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
