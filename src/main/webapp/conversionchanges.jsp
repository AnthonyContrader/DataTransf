<%@page import="it.contrader.model.User.Usertype"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="../css/vittoriostyle.css" rel="stylesheet">
</head>
<body>
<%@ include file="../css/header.jsp" %>


<div class="navbar">
	  <a href="/homeadmin.jsp">Home</a>
	  <a href="/user/getall">Users</a>
	  <a class="active" href="/conversionmanager.jsp">Conversions</a>
	   	<%
  		UserDTO u = (UserDTO) session.getAttribute("user");
  		%>
			<a href="/conversion/findAllByIdUser?idUser=${user.getId()}">My Conversion</a>
		<%
  		if(u.getUsertype().equals(Usertype.ADMIN)) { 
 		%>
  			<a href="/conversion/findAll">All Conversion</a>
		<%
			} 
		%>
	  <a href="/user/logout" id="logout">Logout</a>
</div>

<%
	
%>
 
<%@ include file="../css/footer.jsp" %> 
  
</body>
</html>