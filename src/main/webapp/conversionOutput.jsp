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
  <a href="ConversionLogServlet?mode=read&userId=${user.getId()}&usertype=${user.getUsertype()}">My Conversion</a>
   <%
  	UserDTO u = (UserDTO) session.getAttribute("user");
  	if(u.getUsertype().equalsIgnoreCase("admin")) { 
  %>
  	<a href="../ConversionLogServlet?mode=readAll">All Conversion</a>
  <% 
  	} 
  %>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>

<h1>Il risultato della tua conversione è questo </h1>

<p> <%=request.getAttribute("output")%> </p>


</body>
</html>