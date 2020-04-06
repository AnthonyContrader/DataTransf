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
	  <a href="/homeadmin.jsp">Home</a>
	   <%
  		UserDTO u = (UserDTO) session.getAttribute("user");
	   if(u.getUsertype().equals(Usertype.ADMIN)) { %>
	   
	   <a href="/user/getall">Users</a>
	   <a class = "active" href="/conversionmanager.jsp">New Conversion</a>
	   <a href="/conversion/findAllByIdUser?idUser=${user.getId()}">My Conversions</a>
	   <a href="/conversion/findAll">All Conversion</a>
  	   <% } else { %>
	   <a href="/account.jsp">My Account</a>
	   <a class = "active" href="/conversionmanager.jsp">New Conversion</a>
	   <a href="/conversion/findAllByIdUser?idUser=${user.getId()}">My Conversions</a>
	<% } %>
	   <a href="/user/logout" id="logout">Logout</a>
</div>

<h1>Il risultato della tua conversione è questo </h1>

<p> <%=session.getAttribute("output")%> </p>


</body>
</html>