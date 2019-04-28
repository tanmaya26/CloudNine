package com.tanmaya.projects.cloudnine.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import com.tanmaya.projects.cloudnine.bean.FileList;
import com.tanmaya.projects.cloudnine.bean.FileMeta;

public class FileListDAO {
	Config conf = null;
	public String jdbcURL;
	public String user;
	public String password;

	public FileListDAO() {
		conf = new Config();
		jdbcURL = conf.getJdbcURL();
		user = conf.getUser();
		password = conf.getPassword();
	}

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
				ResultSet rs = statement.executeQuery(
						"select file_mappings.id, filename, file_metadata.id, date_modified, size, extension, owner, filepath from file_mappings, file_metadata where file_mappings.id = file_metadata.mapping_id AND is_deleted = 0;");
				while (rs.next()) {
					int fid = rs.getInt(1);
					String fname = rs.getString("filename");
					int fmid = rs.getInt(3);
					Date dateModified = rs.getDate("date_modified");
					String size = rs.getString("size");
					String extension = rs.getString("extension");
					String owner = rs.getString("owner");
					directory = rs.getString("filepath");
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

	public void deDuplicate(String absoluteBasePath) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				HashMap<String,String> hashList = new HashMap<String,String>();
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(
						"select file_metadata.id, hash, is_dedup, filepath from file_mappings, file_metadata where file_mappings.id = file_metadata.mapping_id AND is_dedup = 0;");
				HashMap<String, Integer> deDup = new HashMap<String, Integer>();
				while (rs.next()) {
					int fid = rs.getInt(1);
					String hash = rs.getString("hash");
					int isDedup = rs.getInt(3);
					String relativeFilePath = rs.getString("filepath");
					String filePath = relativeFilePath;
					if (hashList.containsKey(hash) && isDedup == 0) {
						System.out.println(fid + " has to be deleted");
						relativeFilePath = relativeFilePath.replace('/', '\\');
						String adjustedFilePath = absoluteBasePath + relativeFilePath.substring(1);
						System.out.println("File to be deleted from: " + adjustedFilePath);
						File file = new File(adjustedFilePath);
						if (file.delete()) {
							System.out.println("File Deleted");
							deDup.put(hashList.get(hash), fid);
						} else {
							System.out.println("File Not Found");
							deDup.put(hashList.get(hash), fid);
						}
					} else{
						hashList.put(hash,filePath);
					}
				}
				for (Entry<String, Integer> entry : deDup.entrySet()) {
					System.out.println("Here!");
					statement.executeUpdate("UPDATE file_mappings SET is_dedup = 1 , link = '" + entry.getKey()
							+ "' where id = " + entry.getValue() + ";");
				}

			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
		return;
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
