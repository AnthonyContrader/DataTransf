<%@page import="it.contrader.model.User.Usertype"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"%>
    
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Homepage for Admin ">
<meta name="author" content="Vittorio Valent">

<title>Home</title>

<!-- Bootstrap core CSS -->
<!--  <link href="/css/bootstrap.min.css" rel="stylesheet"> -->

<!-- Custom styles for this template -->
<link href="/css/vittoriostyle.css" rel="stylesheet">
</head>

<body>
	<%@include file="css/header.jsp"%>


<div class="navbar">
	  <a href="/homeadmin.jsp">Home</a>
	   <%
  		UserDTO u = (UserDTO) session.getAttribute("user");
	   if(u.getUsertype().equals(Usertype.ADMIN)) { %>
	   
	   <a href="/user/getall">Users</a>
	   <a href="/conversionmanager.jsp">New Conversion</a>
	   <a href="/conversion/findAllByIdUser?idUser=${user.getId()}">My Conversions</a>
	   <a href="/conversion/findAll">All Conversion</a>
  	   <% } else { %>
	   <a href="/account.jsp">My Account</a>
	   <a href="/conversionmanager.jsp">New Conversion</a>
	   <a href="/conversion/findAllByIdUser?idUser=${user.getId()}">My Conversions</a>
	<% } %>
	   <a href="/user/logout" id="logout">Logout</a>
</div>


	<div class="main">
		<h1>Welcome ${user.getUsername()}</h1>

Se sei venuto per effettuare una conversione di un file XML o un file JSON sei nel posto giusto! <br>
Utilizza la NAVBAR per muoverti con libertà all'interno del nostro sito. <br>
<br>
<ul>
<li>Nella pagina User potrai vedere la tua homepage utente;</li>
<li>Nella pagina Conversione potrai effettuare una nuova conversione da XML a JSON oppure da JSON ad XML;</li>
<li>Nella pagina MYCONVERSION potrai vedere il log di tutte le tue CONVERSIONI;</li>
 <%
 if(u.getUsertype().equals(Usertype.ADMIN)) { 
	  %>
	  	<li>Per te che sei un nostro ADMIN potrai inserire, modificare, eliminare e vedere tutti gli utenti iscritti al nostro sito,<br>
    Inoltre potrai vedere tutti i log degli utenti registrati nella pagina ALLCONVERSION.</li>
	  <% 
	  	} 
	  %>
</ul>


	</div>


	<%@ include file="css/footer.jsp"%>

</body>
</html>