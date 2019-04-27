package com.tanmaya.projects.cloudnine.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tanmaya.projects.cloudnine.bean.FileMapping;
import com.tanmaya.projects.cloudnine.bean.FileMeta;
import com.tanmaya.projects.cloudnine.dao.DirectoryDAO;
import com.tanmaya.projects.cloudnine.dao.FileMappingDAO;
import com.tanmaya.projects.cloudnine.dao.FileMetaDAO;

@WebServlet("/FileMetaServlet")
public class FileMetaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FileMetaDAO metaDao;
	private FileMeta metadata;
	String hash;
	Date dateModified;
	String size;
	String extension = "";
	String owner = "admin";
	DateFormat df = new SimpleDateFormat("dd/MM/yy");
	int fileId;

	public FileMetaServlet() {
		super();
	}
	
	public void init() {
		metaDao = new FileMetaDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String relativePath = (String) request.getAttribute("relativepath");
			String filePath = (String) request.getAttribute("filepath");
			String fileName = (String) request.getAttribute("filename");
			fileId = (int) request.getAttribute("fileId");
			File file = new File(filePath);
			BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			size = Long.toString(attr.size());
			int i = fileName.length() - 1;
			while (i > 0 && fileName.charAt(i) != '.') {
				extension += fileName.charAt(i);
				i -= 1;
			}
			StringBuilder ext = new StringBuilder(extension);
			extension = ext.reverse().toString();
			dateModified = df.parse(df.format(attr.creationTime().toMillis()));
			MessageDigest md5Digest = MessageDigest.getInstance("SHA-512");
			hash = FileChecksum.getFileChecksum(md5Digest, file);
			metadata = new FileMeta(fileId, hash, dateModified, size, extension, owner);
			metaDao.createFileMeta(metadata);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
			dispatcher.forward(request, response);
			

		} catch (Exception e) {

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
