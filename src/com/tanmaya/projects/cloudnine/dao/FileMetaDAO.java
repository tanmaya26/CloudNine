package com.tanmaya.projects.cloudnine.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.tanmaya.projects.cloudnine.bean.FileMeta;

public class FileMetaDAO {
	public static final String jdbcURL = "jdbc:mysql://localhost:3306/photobucket";
	public static final String user = "root";
	public static final String password = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;

	public void createFileMeta(FileMeta filemeta) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
//				java.sql.Date sqlDate = new java.sql.Date(patient.getDob().getTime());
//				statement.executeUpdate("INSERT INTO patients (name, dob, gender, ssn, address, phone_no, age) VALUES"
//						+ "('" + patient.getName() + "','" + sqlDate + "','" + patient.getGender() + "','"
//						+ patient.getSsn() + "','" + patient.getAddress() + "','" + patient.getPhoneNo() + "',"
//						+ patient.getAge() + ")");
				System.out.println("File Metadata added successfully!");
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
