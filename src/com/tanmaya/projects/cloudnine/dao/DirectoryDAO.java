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
	Config conf = null;
	public  String jdbcURL;
	public  String user;
	public  String password;
	public DirectoryDAO(){
		conf = new Config();
		jdbcURL = conf.getJdbcURL();
		user = conf.getUser();
		password = conf.getPassword();
	}
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
	// dirPath must include path and dir name
	public void createDirectory(String dirPath) {
		try {
			
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
