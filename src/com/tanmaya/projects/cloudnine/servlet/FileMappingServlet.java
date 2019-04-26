package com.tanmaya.projects.cloudnine.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tanmaya.projects.cloudnine.bean.FileList;
import com.tanmaya.projects.cloudnine.bean.FileMapping;
import com.tanmaya.projects.cloudnine.dao.DirectoryDAO;
import com.tanmaya.projects.cloudnine.dao.FileListDAO;
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
			
			String operation;
			if(request.getParameter("operation") == null)
				operation = "saveFile";
			else
				operation = request.getParameter("operation");
			
			if(operation.equals("list")) {
				
			}
			else {
				String filepath = (String) request.getAttribute("filepath");
				String filename = (String) request.getAttribute("filename");
				System.out.println(filepath);
				File file = new File(filepath);
				int isDeleted = 0;
				// BasicFileAttributes attr = Files.readAttributes(file.toPath(),
				// BasicFileAttributes.class);
//				System.out.println("creationTime: " + attr.creationTime());
//				System.out.println("lastAccessTime: " + attr.lastAccessTime());
//				System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
//				System.out.println("isDirectory: " + attr.isDirectory());
//				System.out.println("isOther: " + attr.isOther());
//				System.out.println("isRegularFile: " + attr.isRegularFile());
//				System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
//				System.out.println("size: " + attr.size());
				String root = getServletContext().getRealPath("/root");
				String relativeFilePath = root.substring(root.indexOf("root"));
				relativeFilePath = relativeFilePath + "/" + filename;
				fileMapping = new FileMapping(filepath, filename, isDeleted);
				mappingDAO.createFileMapping(fileMapping);
				request.setAttribute("filepath", relativeFilePath);
				RequestDispatcher dispatcher = request.getRequestDispatcher("FileMetaServlet");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
