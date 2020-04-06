<%@page import="it.contrader.model.User.Usertype"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Conversion</title>
<link href="../css/vittoriostyle.css" rel="stylesheet">
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
	   <a class= "active" href="/conversionmanager.jsp">New Conversion</a>
	   <a href="/conversion/findAllByIdUser?idUser=${user.getId()}">My Conversions</a>
	<% } %>
	   <a href="/user/logout" id="logout">Logout</a>
</div>

 
<form id="newConversion" action="/changes/newchanges" method="post">
	<div class="row">
    <div class="col-25">
      <label for="source">Source</label>
    </div>
    <div class="col-75">
    <textarea rows="4" cols="50" name="source"> </textarea>
    </div>
  </div>
  <div class="row">
  	<div class="col-25">
      <label for="sourceType">Source Type</label>
    </div>
    <div class="col-75">
    <input type="radio" id="xml" name="sourceType" value="xml">
	<label for="xml">XML</label><br>
	<input type="radio" id="json" name="sourceType" value="json">
	<label for="json">JSON</label><br>
    </div>
  </div>
  <div class="row">
  	<div class="col-25">
      <label for="outputType">Output Type</label>
    </div>
    <div class="col-75">
    <input type="radio" id="xml" name="outputType" value="xml">
	<label for="xml">XML</label><br>
	<input type="radio" id="json" name="outputType" value="json">
	<label for="json">JSON</label><br>
    </div>
  </div>
  <button type="submit">Convert</button>
</form>

<%@ include file="../css/footer.jsp" %>

</body>
</html>