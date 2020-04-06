<%@page import="it.contrader.model.User.Usertype"%>
<%@ page import="it.contrader.dto.UserDTO" import="java.util.*"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="User Management">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>User Manager</title>

</head>
<body>
	<%@ include file="./css/header.jsp"%>

<div class="navbar">
	  <a href="/homeadmin.jsp">Home</a>
	   <%
  		UserDTO u = (UserDTO) session.getAttribute("user");
	   if(u.getUsertype().equals(Usertype.ADMIN)) { %>
	   
	   <a class = "active" href="/user/getall">Users</a>
	   <a href="/conversionmanager.jsp">New Conversion</a>
	   <a href="/conversion/findAllByIdUser?idUser=${user.getId()}">My Conversions</a>
	   <a href="/conversion/findAll">All Conversion</a>
  	   <% } else { %>
	   <a href="/account.jsp">My Account</a>
	   <a href="/conversionmanager.jsp">New Conversion</a>
	   <a href="/conversion/findAllByIdUser?idUser=${user.getId()}">My Conversions</a>
	<% } %>
	   <a href="/user/logout" id="logout">Logout</a>
</div>


	<div class="main">
		<%
			List<UserDTO> list = (List<UserDTO>) session.getAttribute("list");
		System.out.println(list);
		%>

		<br>

		<table>
			<tr>
				<th>Username</th>
				<th>Password</th>
				<th>Usertype</th>
				<th></th>
				<th></th>
			</tr>
 		
 		<% 
				for (UserDTO dto : list) {
			%>
			<tr>
				<td><a href="/user/read?id=<%=dto.getId()%>"> <%=dto.getUsername()%>
				</a></td>
				<td><%=dto.getPassword()%></td>
				<td><%=dto.getUsertype()%></td>
		   	    <td><a href="/user/preupdate?id=<%=dto.getId()%>">Edit</a></td>
				<td><a href="/user/delete?id=<%=dto.getId()%>">Delete</a></td>

			</tr>
			<%
				}
			%>
		</table>

		<form id="floatright" action="/user/insert" method="post">
			<div class="row">
				<div class="col-25">
					<label for="user">Username</label>
				</div>
				<div class="col-75">
					<input type="text" id="user" name="username"
						placeholder="inserisci username">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="pass">Password</label>
				</div>
				<div class="col-75">
					<input type="text" id="pass" name="password"
						placeholder="inserisci password">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="type">Usertype</label>
				</div>
				<div class="col-75">
					<select id="type" name="usertype">
						<option value="ADMIN">ADMIN</option>
						<option value="USER">USER</option>

					</select>
				</div>
			</div>
			<button type="submit">Insert</button>
		</form>

	</div>
	<br>
	<%@ include file="./css/footer.jsp"%>
</body>
</html>