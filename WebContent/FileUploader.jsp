<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dummy Upload Page</title>
</head>
<body>
	<div>
       <h3> Select a file </h3>
       <form action="FirstServlet" method="post" enctype="multipart/form-data">
           <input type="file" name="file" />
           <input type="submit" value="upload" />
       </form>          
   </div>
</body>
</html>