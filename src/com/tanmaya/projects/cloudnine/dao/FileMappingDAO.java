package com.tanmaya.projects.cloudnine.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.tanmaya.projects.cloudnine.bean.FileMapping;

public class FileMappingDAO {

	Config conf = null;
	public String jdbcURL;
	public String user;
	public String password;

	public FileMappingDAO() {
		conf = new Config();
		jdbcURL = conf.getJdbcURL();
		user = conf.getUser();
		password = conf.getPassword();
	}

	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;

	public int createFileMapping(FileMapping mapping) {
		int fileId = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("INSERT INTO file_mappings (filepath, filename, is_deleted) VALUES" + "('"
						+ mapping.getFilepath() + "','" + mapping.getFilename() + "'," + mapping.getIsDeleted() + ")");
				System.out.println("File Mapping added successfully!");
				ResultSet rs = statement.executeQuery("SELECT id FROM file_mappings WHERE filepath = '" + mapping.getFilepath()
						+ "' AND filename = '" + mapping.getFilename() + "'");
				while(rs.next()) {
					fileId = rs.getInt("id");
				}
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
		return fileId;
	}

	public void deleteFileMapping(int mapping_id) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("DELETE from file_mappings where id = " + mapping_id + ";");
				System.out.println("File mapping successfully deleted");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	public FileMapping selectFileMapping(int id) {
		FileMapping filemapping = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(
						"SELECT filepath, filename, is_deleted FROM file_mappings WHERE id = " + id + ";");
				while (rs.next()) {
					int is_deleted = rs.getInt("is_deleted");
					String filepath = rs.getString("filepath");
					String filename = rs.getString("filename");
					filemapping = new FileMapping(id, filepath, filename, is_deleted);
				}

			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
		return filemapping;
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
