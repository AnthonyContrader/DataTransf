<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<link href="../css/vittoriostyle.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>New Changes</title>	
</head>
<body>

<%@ include file="../css/header.jsp" %>

<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a href="UserServlet?mode=userlist">Users</a>
  <a class="active" href="../conversion/conversionmanager.jsp">Conversions</a>
  <a href="ConversionLogServlet?mode=read&userId=${user.getId()}&usertype=${user.getUsertype()}">My Conversion</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>

	<%
		Map<String, String> changes = (Map<String, String>) session.getAttribute("changes");
	%>

	<form id="newChanges" action="ConversionServlet?mode=a" method="post">
	
	<%
		for(Map.Entry<String, String> change : changes.entrySet()) {
	%>
		<div class="row">
	    <div class="col-25">
	      <label for="<%=change.getKey()%>"><%=change.getKey()%></label>
	    </div>
	    <div class="col-75">
	    	<input type="text" name="<%=change.getKey()%>" value="<%=change.getValue()%>" >
	    </div>
	  	</div>
	<%
		}
	%>
	 <button type="submit">Save</button>
	</form>

</body>
</html>