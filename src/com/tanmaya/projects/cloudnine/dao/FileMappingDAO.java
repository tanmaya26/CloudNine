package com.tanmaya.projects.cloudnine.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.tanmaya.projects.cloudnine.bean.FileMapping;

public class FileMappingDAO {
	public static final String jdbcURL = "jdbc:mysql://localhost:3306/photobucket";
	public static final String user = "root";
	public static final String password = "expertiza";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;

	public void createFileMapping(FileMapping mapping) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("INSERT INTO file_mappings (filepath, filename, is_deleted) VALUES" + "('"
						+ mapping.getFilepath() + "','" + mapping.getFilename() + "'," + mapping.getIsDeleted() + ")");
				System.out.println("File Mapping added successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
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
