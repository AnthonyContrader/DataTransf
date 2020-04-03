<%@page import="it.contrader.model.User.Usertype"%>
<%@page import="it.contrader.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Conversion Manager</title>
<link href="/css/vittoriostyle.css" rel="stylesheet">
</head>
<body><%@ include file="/css/header.jsp" %>

<div class="navbar">
	  <a href="/homeadmin.jsp">Home</a>
	  <a href="/users/getAll">Users</a>
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
		<li>Eliminare i tag inutili;</li>
		<li>Ordinare i tag in modo tale da averli nella posizione che vuoi.</li>
		
	
	</ul>  
</p>
<br>

<a style="color: white; background-color: #00a990; border-radius: 7px; padding: 12px;" href="/newconversion.jsp">New Conversion</a>

</div>

<body><%@ include file="css/footer.jsp" %>

</body>
</html>