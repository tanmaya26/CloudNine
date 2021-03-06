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

import org.apache.commons.io.FilenameUtils;

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
			
			if(operation.equals("deleteFile")) {
				int mapping_id = Integer.parseInt(request.getParameter("mapping_id"));
				mappingDAO.deleteFileMapping(mapping_id);
				FileListDAO fdao = new FileListDAO();
				List<String> dirlist = new ArrayList<>();
				List<FileList> filelist = new ArrayList<>();
				String root = getServletContext().getRealPath("/root");
				DirectoryDAO ddao = new DirectoryDAO();
				dirlist = ddao.listDirectory(root);
				filelist = fdao.listFiles(root);
				request.setAttribute("dirlist", dirlist);
				request.setAttribute("filedesc", filelist);
				request.setAttribute("currdir", "/root");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
				dispatcher.forward(request, response);
			}
			else {
				String relativePath = (String) request.getAttribute("relativepath");
				String filePath = (String) request.getAttribute("filepath");
				String fileName = (String) request.getAttribute("filename");
				FilenameUtils.separatorsToSystem(filePath);
				File file = new File(filePath);
				int isDeleted = 0;
				fileMapping = new FileMapping(relativePath, fileName, isDeleted);
				int fileId = mappingDAO.createFileMapping(fileMapping);
				request.setAttribute("relativepath", relativePath);
				request.setAttribute("filepath", filePath);
				request.setAttribute("fileId", fileId);
				request.setAttribute("filename", fileName);
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
