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

<form id="floatright" action="ConversionServlet?mode=a" method="post">
  <div class="row">
    <div class="col-25">
      <label for="source">Message</label>
    </div>
    <div class="col-75">
      <input type="text" id="source" name="insert message" placeholder="">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="sourceType">SourceType</label>
    </div>
    <div class="col-75">
   		<select id="SourceType" name="SourceType">
     	<option value="Json">Json</option>
  		<option value="XML">XML</option> 
  		</select>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="OutputType">OutputType</label>
    </div>
   		 <div class="col-75">
 			<select id="OutputType" name="OutputType">
  				<option value="Json">Json</option>
  				<option value="XML">XML</option> 
 			</select>
    	</div>
  </div>
      <button type="submit" >Continue</button></form>



</body>
</html>