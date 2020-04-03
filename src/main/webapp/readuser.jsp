<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO,it.contrader.model.User.Usertype"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="User Read">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Read User</title>
</head>
<body>
	<%@ include file="./css/header.jsp"%>

<div class="navbar">
	  <a href="/homeadmin.jsp">Home</a>
	  <a href="/users/getAll">Users</a>
	  <a href="/conversionmanager.jsp">Conversions</a>
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

	<br>

	<div class="main">
		<%
			UserDTO uDto = (UserDTO) request.getSession().getAttribute("dto");
		%>


		<table>
			<tr>
				<th>ID</th>
				<th>Username</th>
				<th>Password</th>
				<th>Usertype</th>
			</tr>
			<tr>
				<td><%=uDto.getId()%></td>
				<td><%=uDto.getUsername()%></td>
				<td><%=uDto.getPassword()%></td>
				<td><%=uDto.getUsertype()%></td>
			</tr>
		</table>

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>


	</div>

	<%@ include file="./css/footer.jsp"%>
</body>
</html>