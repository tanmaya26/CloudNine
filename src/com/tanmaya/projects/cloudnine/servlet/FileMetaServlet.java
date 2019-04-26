package com.tanmaya.projects.cloudnine.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tanmaya.projects.cloudnine.bean.FileMapping;
import com.tanmaya.projects.cloudnine.dao.DirectoryDAO;
import com.tanmaya.projects.cloudnine.dao.FileMappingDAO;

@WebServlet("/FileMetaServlet")
public class FileMetaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FileMetaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		DirectoryDAO ddao = new DirectoryDAO();
//		List<String> dirlist = new ArrayList<>();
//		String root = getServletContext().getRealPath("/root");
//		//System.out.println(root);
//		dirlist = ddao.listDirectory(root);
//		request.setAttribute("dirlist", dirlist);
//		//System.out.println("No of patients: " + patients.size());
//		RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
//		for(String s: dirlist) {
//			System.out.println(s);
//		}
//		dispatcher.forward(request, response);
		
		FileMappingDAO fmdao = new FileMappingDAO();
		FileMapping fm = new FileMapping("112", "hey", 0);
		fmdao.createFileMapping(fm);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
