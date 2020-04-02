<%@page import="it.contrader.model.User.Usertype"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<link href="../css/vittoriostyle.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>conversionOutput</title>	
</head>
<body>

<%@ include file="../css/header.jsp" %>

<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a href="UserServlet?mode=userlist">Users</a>
  <a class="active" href="./conversion/conversionmanager.jsp">Conversions</a>
  <%
  	UserDTO u = (UserDTO) session.getAttribute("user");
  %>
		 <a href="/conversion/findAllByIdUser?idUser=${u.getId()}">My Conversion</a>
	<%
  	if(u.getUsertype().equals(Usertype.ADMIN)) { 
  %>
  	<a href="/conversion/findAll">All Conversion</a>
  <% 
  	} 
  %>
  <a href="/user/Logout" id="logout">Logout</a>
</div>

<h1>Il risultato della tua conversione è questo </h1>

<p> <%=session.getAttribute("output")%> </p>


</body>
</html>