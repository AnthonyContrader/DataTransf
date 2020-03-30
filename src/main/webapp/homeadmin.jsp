<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Admin</title>
<link href="css/vittoriostyle.css" rel="stylesheet">
</head>
<body>

<%@include file="css/header.jsp"%>


<div class="navbar">
  <a class="active" href="homeadmin.jsp">Home</a>
  <a href="UserServlet?mode=userlist">Users</a>
  <a href="conversion/conversionmanager.jsp">Conversions</a>
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

<div class="main">
<h1>Welcome ${user.getUsername()}</h1>
<p>
Se sei venuto per effettuare una conversione di un file XML o un file JSON sei nel posto giusto! <br>
Utilizza la NAVBAR per muoverti con libertà all'interno del nostro sito. <br>
<br>
<li>Nella pagina User potrai vedere la tua homepage utente;
<li>Nella pagina Conversione potrai effettuare una nuova conversione da XML a JSON oppure da JSON ad XML;<br>
<li>Nella pagina MYCONVERSION potrai vedere il log di tutte le tue CONVERSIONI;<br>

	  <%
	  	UserDTO u1 = (UserDTO) session.getAttribute("user");
	  	if(u1.getUsertype().equalsIgnoreCase("admin")) { 
	  %>
	  	<li>Per te che sei un nostro ADMIN potrai inserire, modificare, eliminare e vedere tutti gli utenti iscritti al nostro sito,<br>
    Inoltre potrai vedere tutti i log degli utenti registrati nella pagina ALLCONVERSION.<br>
	  <% 
	  	} 
	  %>


</p>
</div>


<%@ include file="css/footer.jsp" %>

</body>
</html>