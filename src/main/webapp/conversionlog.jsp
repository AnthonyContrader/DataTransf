<%@page import="it.contrader.model.User.Usertype"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.ConversionDTO, it.contrader.dto.UserDTO"
    import="java.util.List"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="../css/vittoriostyle.css" rel="stylesheet">
</head>
<body><%@ include file="../css/header.jsp" %>

<h1>Welcome ${user.getUsername()}</h1>

	<div class="navbar">
	 <a  href="./homeadmin.jsp">Home</a>
	  <a href="UserServlet?mode=userlist">Users</a>
	  <a href="conversion/conversionmanager.jsp">Conversions</a>
	  <a <% if(request.getParameter("mode").equalsIgnoreCase("read")) { %> class="active" <% } %> href="ConversionLogServlet?mode=read&userId=${user.getId()}&usertype=${user.getUsertype()}">My Conversion</a>
	  
	  <%
	  	UserDTO u = (UserDTO) session.getAttribute("user");
	  	if(u.getUsertype().equals(Usertype.ADMIN)) { 
	  %>
	  	<a <% if(request.getParameter("mode").equalsIgnoreCase("readAll")) { %> class="active" <% } %> href="ConversionLogServlet?mode=readAll">All Conversion</a>
	  <% 
	  	} 
	  %>
	  <a href="./LogoutServlet" id="logout">Logout</a>
	</div>

	<%
		List<ConversionDTO> list = (List<ConversionDTO>) request.getAttribute("log");
	%>

	<table>
		<tr>
			<th>ID</th>
			<th>Source</th>
			<th>Source Type</th>
			<th>Output Type</th>
			<th>Changes Id</th>
		</tr>
		<%
			for (ConversionDTO c : list) {
		%>
		<tr>
			<td><%=c.getIdConversion()%></td>
			<td><%=c.getSource()%></td>
			<td><%=c.getSourceType()%></td>
			<td><%=c.getOutputType()%></td>
			<td><%=c.getChanges()%></td>
		</tr>
		<%
			}
		%>
	</table>

	<%@ include file="../css/footer.jsp" %>
</body>
</html>