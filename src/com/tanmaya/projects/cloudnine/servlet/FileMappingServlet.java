package com.tanmaya.projects.cloudnine.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tanmaya.projects.cloudnine.bean.FileMapping;
import com.tanmaya.projects.cloudnine.dao.FileMappingDAO;

@WebServlet("/FileMappingServlet")
public class FileMappingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FileMapping fileMapping;
	private FileMappingDAO mappingDAO;

	public FileMappingServlet() {
		super();
	}

	public void init() {
		mappingDAO = new FileMappingDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String filepath = (String) request.getAttribute("filepath");
			System.out.println(filepath);
			File file = new File(filepath);
			String filename = "";
			int isDeleted = 1;
			// BasicFileAttributes attr = Files.readAttributes(file.toPath(),
			// BasicFileAttributes.class);
//			System.out.println("creationTime: " + attr.creationTime());
//			System.out.println("lastAccessTime: " + attr.lastAccessTime());
//			System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
//			System.out.println("isDirectory: " + attr.isDirectory());
//			System.out.println("isOther: " + attr.isOther());
//			System.out.println("isRegularFile: " + attr.isRegularFile());
//			System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
//			System.out.println("size: " + attr.size());
			int i = filepath.length()-1;

			while (i > 0 && filepath.charAt(i) != '/') {
				filename += filepath.charAt(i);
				i -= 1;
			}

			fileMapping = new FileMapping(filepath, filename, isDeleted);
			mappingDAO.createFileMapping(fileMapping);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
