<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
  <a class="active" href="../homeadmin.jsp">Home</a>
  <a href="../UserServlet?mode=userlist">Users</a>
  <a href="../conversion/conversionmanager.jsp">Conversions</a>
  <a href="../LogoutServlet" id="logout">Logout</a>
</div>

 
<form id="newConversion" action="../ChangesServlet?mode=create_changes" method="post">
	<div class="row">
    <div class="col-25">
      <label for="source">Source</label>
    </div>
    <div class="col-75">
    <input type="text" name="source">
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