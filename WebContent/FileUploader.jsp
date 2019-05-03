<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<div>
		<h3>Select a file</h3>
		<form action="FileUpload" method="post" enctype="multipart/form-data">
			<input type="hidden" name="directory" value="${directory}" /> <input
				type="file" name="file" size="50" /> <br /> <input type="submit"
				value="Upload File" />
		</form>
		<form action="FileUpload" method="get" enctype="multipart/form-data">
			<input type="submit" value="Dummy Form" />
		</form>
	</div>
</body>
</html>