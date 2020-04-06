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

<% String active = session.getAttribute("active").toString(); %>

<div class="navbar">
	  <a href="/homeadmin.jsp">Home</a>
	   <%
  		UserDTO u = (UserDTO) session.getAttribute("user");
	   if(u.getUsertype().equals(Usertype.ADMIN)) { %>
	   
	   <a href="/user/getall">Users</a>
	   <a href="/conversionmanager.jsp">New Conversion</a>
	   <a class = "active" href="/conversion/findAllByIdUser?idUser=${user.getId()}">My Conversions</a>
	   <a href="/conversion/findAll">All Conversion</a>
  	   <% } else { %>
	   <a href="/account.jsp">My Account</a>
	   <a href="/conversionmanager.jsp">New Conversion</a>
	   <a class = "active" href="/conversion/findAllByIdUser?idUser=${user.getId()}">My Conversions</a>
	<% } %>
	   <a href="/user/logout" id="logout">Logout</a>
</div>

	<%
		List<ConversionDTO> list = (List<ConversionDTO>) request.getSession().getAttribute("Log");
	%>

	<table>
		<tr>
			<th>ID</th>
			<th>Source Type</th>
			<th>Output Type</th>
			<th>Changes Id</th>
		</tr>
		<%
			for (ConversionDTO c : list) {
		%>
		<tr>
			<td><%=c.getIdConversion()%></td>
			<td><%=c.getSourceType() %></td>
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