<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cloudnine - Home</title>
<link rel="stylesheet" type="text/css" href="css/header.css">
<script src="js/jquery.js"></script>
<style>
#logout {
	height: 50px;
	width: 100px;
	background: #1a8180;
	position: fixed;
	top: 0px;
	color: white;
	font-size: 30px;
	right: 0px;
	line-height: 50px;
	vertical-align: middle;
	text-align: center;
	z-index: 3;
	cursor: pointer;
	font-family: simplifica !important;
}

#logout:hover {
	font-size: 35px;
	background-color: 3cb3b2;
}

#welcomeBox {
	height: 50px;
	width: 200px;
	background: #1a8180;
	position: fixed;
	top: 0px;
	color: white;
	font-size: 30px;
	line-height: 50px;
	vertical-align: middle;
	text-align: center;
	z-index: 3;
	font-family: simplifica !important;
}

#buttonbox {
	width: 230px;
	height: 230px;
	position: absolute;
	margin-left: -115px;
	margin-top: -130px;
	left: 50%;
	top: 50%;
}

.buttoner {
	width: 150px;
	height: 150px;
	border-radius: 75px;
	position: absolute;
	cursor: pointer;
	color: white;
	text-align: center;
	line-height: 150px;
	vertical-align: middle;
	font-size: 30px;
}

#directoryLister {
	position: absolute;
	top: 120px;
	left: 50%;
	margin-left: -350px;
	max-height: 300px;
	overflow-y: scroll;
	width: 500px;
	height: 45px;
	background-color: #1a8180;
	color: white;
	border-radius: 5px;
	line-height: 45px;
	text-align: center;
	vertical-align: middle;
	font-family: simplifica !important;
}

#sizeBox {
	position: absolute;
	top: 120px;
	left: 50%;
	margin-left: 200px;
	max-height: 300px;
	overflow-y: scroll;
	width: 300px;
	height: 45px;
	background-color: #1a8180;
	color: white;
	border-radius: 5px;
	line-height: 45px;
	text-align: center;
	vertical-align: middle;
	font-family: simplifica !important;
}

#uploadButton {
	position: absolute;
	top: 120px;
	left: 50%;
	margin-left: -500px;
	max-height: 300px;
	overflow-y: scroll;
	width: 100px;
	height: 45px;
	background-color: #1a8180;
	color: white;
	border-radius: 5px;
	line-height: 45px;
	text-align: center;
	vertical-align: middle;
	font-family: simplifica !important;
	cursor: pointer;
}

#contentBox {
	position: absolute;
	top: 190px;
	left: 50%;
	width: 1080px;
	margin-left: -540px;
	height: 720px;
	overflow-y: scroll;
	border-width: 2px;
	border-style: solid;
	border-radius: 5px;
	border-color: #1a8180;
}

#iconDiv {
	width: 40px;
	height: 40px;
	cursor: pointer;
}

::-webkit-scrollbar {
	width: 5px;
}

::-webkit-scrollbar-track {
	border-radius: 0px;
}

::-webkit-scrollbar-thumb {
	border-radius: 0px;
	background: #1a8180;
}
</style>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/css/foundation.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/js/foundation.min.js"></script>
</head>
<body>
	<div class="header">Cloudnine: serving beyond the skyline!</div>
	<div id="welcomeBox">Welcome, User!</div>
	<div id="logout">Logout</div>

	<div id="directoryLister">PhotoBucket > ${currdir}</div>
	<div id="sizeBox"></div>
	<div id="uploadButton" onclick="redirectToFileUpload();">Upload
		File</div>
	<div id="contentBox">
		<table border="1" cellpadding="5">
			<tr>
				<th></th>
				<th>Filename</th>
				<th>Path</th>
				<th>Date Modified</th>
				<th>Size</th>
				<th>Extension</th>
				<th>Owner</th>
			</tr>
			<c:forEach var="filedesc" items="${filedesc}">
				<tr>
					<td><div id="iconDiv"></div></td>
					<td><c:out value="${filedesc.filename}" /></td>
					<td><c:out value="${filedesc.filepath}" /></td>
					<td><c:out value="${filedesc.dateModified}" /></td>
					<td><c:out value="${filedesc.size}" /></td>
					<td><c:out value="${filedesc.extension}" /></td>
					<td><c:out value="${filedesc.owner}" /></td>
					<td>
						<form action="FileMappingServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit">
								<a>Delete</a>
							</button>
							<input type="hidden" name="operation" value="deleteFile" /> <input
								type="hidden" name="mapping_id" value="${filedesc.mappingId}" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>


		<table border="1" cellpadding="5">
			<tr>
				<th></th>
				<th>Folder Name</th>
			</tr>
			<c:forEach var="dirlist" items="${dirlist}">
				<tr>
					<td><div id="iconDiv" onclick="openSelectedDir('${dirlist}');">
							<img src="images/folder.png" width="100%" height="100%" />
						</div></td>
					<td><c:out value="${dirlist}" /></td>
				</tr>
			</c:forEach>
		</table>

		<div style="text-align: center">
			<form style="display: inline-block;" action="DirectoryServlet"
				method="get" enctype="multipart/form-data" id="createNewFolderForm">
				<input type="button" class="button" value="Create New Folder"
					onclick="promptFoldername();"> <input type="hidden"
					name="operation" value="create" /> <input type="hidden"
					name="foldername" value="" id="folernameInp" /> <input
					type="hidden" name="currdir" value="${currdir}" id="getcurrdir" />
			</form>
		</div>

	</div>


	<form style="display: none;" action="DirectoryServlet" method="get"
		enctype="multipart/form-data" id="switchDirectoryForm">
		<input type="hidden" name="operation" value="listDirContents" /> <input
			type="hidden" name="currdir" value="" id="setOpenFolderDir" />
	</form>

</body>
<script>
	var currdir = document.getElementById("getcurrdir").value;
	fetchDirSize();
	//alert("Currdir is: " + currdir);
	function openSelectedDir(dir) {
		var openDir = currdir + "/" + dir;
		document.getElementById("setOpenFolderDir").value = openDir;
		document.getElementById("switchDirectoryForm").submit();
		//alert("Function Called with : " + openDir);
	}

	function promptFoldername() {
		var foldername = prompt("Please enter folder name", "new folder");
		if (foldername != null) {
			document.getElementById("folernameInp").value = foldername;
			document.getElementById("createNewFolderForm").submit();
			//document.getElementById("getcurrdir").value 
		}
	}

	function fetchDirSize() {
		$.get("DirectoryServlet", {
			operation : "fetchDirSize",
			currdir : currdir,
		}, function(data, status) {
			rxdata = JSON.stringify(data);
			//alert(rxdata);
			document.getElementById("sizeBox").innerHTML = rxdata;
		});
	}

	function redirectToFileUpload() {
		window.location = "FileUploader.jsp";
	}
</script>

</html>