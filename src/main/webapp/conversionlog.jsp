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
	 <a  href="/homeadmin.jsp">Home</a>
	  <a href="/users/getAll">Users</a>
	  <a href="/conversionmanager.jsp">Conversions</a>
	   <%
  		UserDTO u = (UserDTO) session.getAttribute("user");
  		%>
		<a class="active" href="/conversion/findAllByIdUser?idUser=${u.getId()}">My Conversion</a>
		<%
  		if(u.getUsertype().equals(Usertype.ADMIN)) { 
 		 %>
  		<a href="/conversion/findAll">All Conversion</a>
<%} %>

	
	  <a href="./LogoutServlet" id="logout">Logout</a>
	</div>

	<%
		List<ConversionDTO> list = (List<ConversionDTO>) request.getSession().getAttribute("Log");
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