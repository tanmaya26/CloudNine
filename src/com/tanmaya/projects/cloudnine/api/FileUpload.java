package com.tanmaya.projects.cloudnine.api;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(description = "Servlet to upload file", urlPatterns = { "/FileUpload" , "/FileUpload.do"})
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html><body>";
	public static final String HTML_END="</body></html>";
	public static final String dbConnectionString = "";
       
	private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file ;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Date date = new Date();
		out.println(HTML_START + "<h2>Hi There!</h2><br/><h3>Date="+date +"</h3>"+HTML_END);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (isMultipart) {
			//Create a factory for disk-based file items 
			FileItemFactory factory = new DiskFileItemFactory();
               // Create a new file upload handler
               ServletFileUpload upload = new ServletFileUpload(factory);
               try {
                   List items = upload.parseRequest(request);
                   Iterator iterator = items.iterator();
                   while (iterator.hasNext()) {
                       FileItem item = (FileItem) iterator.next();
                       if (!item.isFormField()) {
                           String fileName = item.getName();    
                           String root = getServletContext().getRealPath("/root");
                           File path = new File(root);
                           if (!path.exists()) {
                               boolean status = path.mkdirs();
                           }
                           File uploadedFile = new File(path + "/" + fileName);
                           System.out.println(uploadedFile.getAbsolutePath());
                           item.write(uploadedFile);
                       }
                   }
               } catch (FileUploadException e) {
                   e.printStackTrace();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }

}
}
