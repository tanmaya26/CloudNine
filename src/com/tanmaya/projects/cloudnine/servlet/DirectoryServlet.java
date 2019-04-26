package com.tanmaya.projects.cloudnine.servlet;

import java.io.File;
import java.io.IOException;
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

@WebServlet("/DirectoryServlet")
public class DirectoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DirectoryServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String operation;
			if (request.getParameter("operation") == null)
				operation = "listDirContents";
			else
				operation = request.getParameter("operation");

			if (operation.equals("create")) {
				String dirPath = request.getParameter("dirPath");
				DirectoryDAO ddao = new DirectoryDAO();
				ddao.createDirectory(dirPath);
				//request.setAttribute("filedesc", filelist);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
				dispatcher.forward(request, response);
			} 
			else if (operation.equals("listDirContents")) {
				String currdir = request.getParameter("currdir");
				if (currdir == null) {
					currdir = "/root";
				}
				System.out.println("Directory Path to list contents is (should be relative): " +currdir);
				DirectoryDAO ddao = new DirectoryDAO();
				FileListDAO fdao = new FileListDAO();
				List<String> dirlist = new ArrayList<>();
				List<FileList> filelist = new ArrayList<>();
				String root = getServletContext().getRealPath(currdir);
				dirlist = ddao.listDirectory(root);
				filelist = fdao.listFiles(root);
				//System.out.println("No of Files: " + filelist.size());
				request.setAttribute("dirlist", dirlist);
				request.setAttribute("filedesc", filelist);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
				dispatcher.forward(request, response);
			}
			else {
				
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
