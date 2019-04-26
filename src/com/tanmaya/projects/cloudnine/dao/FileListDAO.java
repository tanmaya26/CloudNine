package com.tanmaya.projects.cloudnine.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tanmaya.projects.cloudnine.bean.FileList;
import com.tanmaya.projects.cloudnine.bean.FileMeta;

public class FileListDAO {
	public static final String jdbcURL = "jdbc:mysql://localhost:3306/photobucket";
	public static final String user = "root";
	public static final String password = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;

	// Lists all files present in the server, irrespective of the directory
	public List<FileList> listFiles(String directory) {
		List<FileList> fileList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select file_mappings.id, filename, file_metadata.id, date_modified, size, extension, owner from file_mappings, file_metadata where file_mappings.id = file_metadata.mapping_id;");
				while(rs.next()) {
					int fid = rs.getInt(1);
					String fname = rs.getString("filename");
					int fmid = rs.getInt(3);
					Date dateModified = rs.getDate("date_modified");
					String size = rs.getString("size");
					String extension = rs.getString("extension");
					String owner = rs.getString("owner");
					fileList.add(new FileList(fmid, fid, dateModified, size, extension, owner, directory, fname));
				}
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
		return fileList;
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
