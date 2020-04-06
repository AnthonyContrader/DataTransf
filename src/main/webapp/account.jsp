<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO,it.contrader.model.User.Usertype"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Read User</title>
</head>
<body>
	<%@ include file="./css/header.jsp"%>

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
	   <a class="active" href="/account.jsp">My Conversion</a>
	   <a href="/conversionmanager.jsp">New Conversion</a>
	   <a href="/conversion/findAllByIdUser?idUser=${user.getId()}">My Conversions</a>
	<% } %>
	   <a href="/user/logout" id="logout">Logout</a>
</div>

	<br>

	<div class="main">
	
	
	 <% if(u.getUsertype().equals(Usertype.ADMIN)) { %>

<h3> L'Account modificato è il seguente :  </h3>
     <% } %>
     <br>
     <br>
		<table>
			<tr>
				<th>Username</th>
				<th>Password</th>
				<th>Usertype</th>
				<th></th>
				<th></th>
			</tr>
 		
			<tr>
				<td><%=u.getUsername()%></td>
				<td><%=u.getPassword()%></td>
				<td><%=u.getUsertype()%></td>
		   	    <td><a href="/user/preupdate?id=<%=u.getId()%>">Edit</a></td>
		   	    <td><a href="/user/delete?id=<%=u.getId()%>">Delete</a></td>

			</tr>
		</table>

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>


	</div>

	<%@ include file="./css/footer.jsp"%>
</body>
</html>