<%@page import="it.contrader.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Conversion Manager</title>
<link href="../css/vittoriostyle.css" rel="stylesheet">
</head>
<body><%@ include file="../css/header.jsp" %>

<div class="navbar">
 <a  href="../homeadmin.jsp">Home</a>
  <a href="../UserServlet?mode=userlist">Users</a>
  <a class="active" href="#">Conversions</a>  
  <a href="../ConversionLogServlet?mode=read&userId=${user.getId()}&usertype=${user.getUsertype()}">My Conversion</a>
  <%
  	UserDTO u = (UserDTO) session.getAttribute("user");
  	if(u.getUsertype().equalsIgnoreCase("admin")) { 
  %>
  	<a href="../ConversionLogServlet?mode=readAll">All Conversion</a>
  <% 
  	} 
  %>
  <a href="../LogoutServlet" id="logout">Logout</a>
</div>

<h1>Welcome in Conversion ${user.getUsername()}</h1>
<div class="main">

<p>In questa pagina potrai convertire il tuo file da XML a JSON oppure da JSON a XML.<br>
Per effettuare questa conversione hai bisogno di 3 semplici elementi:<br>

	<ul>
		<li>Il file da convertire;</li>
		<li>Il formato del file di input;</li>
		<li>Il formato del file di output;</li>
	</ul>   

Prima di convertire il messaggio potrai effettuare delle operazioni all'interno:

	<ul>
		<li>Cambiare il nome dei tag in modo permanente oppure solo per questa conversione;</li>
		<li>Ordinare i tag in modo tale da averli nella posizione che vuoi.</li>
	
	</ul>  


</p>

<br>

<a style="color: white; background-color: #00a990; border-radius: 7px; padding: 12px;" href="newconversion.jsp">New Conversion</a>

</div>

<body><%@ include file="../css/footer.jsp" %>

</body>
</html>