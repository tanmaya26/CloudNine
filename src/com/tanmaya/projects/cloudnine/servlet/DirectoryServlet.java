package com.tanmaya.projects.cloudnine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tanmaya.projects.cloudnine.bean.FileList;
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
				String currdir = request.getParameter("currdir");
				if (currdir == null)
					currdir = "/root";
				String foldername = request.getParameter("foldername");
				DirectoryDAO ddao = new DirectoryDAO();
				String absolutePath = getServletContext().getRealPath(currdir);
				ddao.createDirectory(absolutePath, foldername);

				// Fetch and list directory contents

				System.out.println("Directory Path to list contents is (should be relative): " + currdir);
				FileListDAO fdao = new FileListDAO();
				List<String> dirlist = new ArrayList<>();
				List<FileList> filelist = new ArrayList<>();
				String root = getServletContext().getRealPath(currdir);
				dirlist = ddao.listDirectory(root);
				filelist = fdao.listFiles(root);
				request.setAttribute("dirlist", dirlist);
				request.setAttribute("filedesc", filelist);
				request.setAttribute("currdir", currdir);
				request.setAttribute("absolutePath", absolutePath);
				// ---------------------------------

				RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
				dispatcher.forward(request, response);
			} else if (operation.equals("listDirContents")) {
				String currdir = request.getParameter("currdir");
				if (currdir == null) {
					currdir = "/root";
				}
				System.out.println("Directory Path to list contents is (should be relative): " + currdir);
				DirectoryDAO ddao = new DirectoryDAO();
				FileListDAO fdao = new FileListDAO();
				List<String> dirlist = new ArrayList<>();
				List<FileList> filelist = new ArrayList<>();
				String root = getServletContext().getRealPath(currdir);
				dirlist = ddao.listDirectory(root);
				filelist = fdao.listFiles(root);
				// System.out.println("No of Files: " + filelist.size());
				request.setAttribute("dirlist", dirlist);
				request.setAttribute("filedesc", filelist);
				request.setAttribute("currdir", currdir);
				request.setAttribute("absolutePath", root);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
				dispatcher.forward(request, response);
			} else if(operation.equals("fetchDirSize")){
				String currdir = request.getParameter("currdir");
				if (currdir == null) {
					currdir = "/root";
				}
				DirectoryDAO ddao = new DirectoryDAO();
				int size = ddao.sizeOfDirInKB(currdir);
				PrintWriter out = response.getWriter();
				out.print("Drive occupies: " + size + "KB Memory");
				System.out.println("Fetch Size Called for dir: " + currdir);
			}
			else if (operation.equals("UploadFile")){
				String dir = request.getParameter("directory");
				request.setAttribute("directory", dir);
				RequestDispatcher dispatcher = request.getRequestDispatcher("FileUploader.jsp");
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
