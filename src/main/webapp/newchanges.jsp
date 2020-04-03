<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Map"
    import="it.contrader.dto.UserDTO,java.util.ArrayDeque,it.contrader.model.User.Usertype" %>
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

	<%
	 ArrayDeque<Map.Entry<String, String>> changes = (ArrayDeque<Map.Entry<String, String>>) session.getAttribute("changes");
	%>

	<form id="newChanges" action="/changes/save" method="post">
	
	
	<p> Inserisci il nome da dare al preset : </p>
		<div class="row" style="border-bottom-style: solid; border-bottom-color: #00a990;">
	    <div class="col-25">
	      <label for="changesName">Preset Name</label>
	    </div>
	    <div class="col-75">
	    	<input type="text" name="changesName" placeholder=" preset..." >
	    </div>
	  	</div>
	
	
	
	<p> I tag contenuti all'interno del messaggio sono: </p> 
	
	<%
		for(Map.Entry<String, String> change : changes) {
	%>
		<div class="row">
	    <div class="col-25">
	      <label for="<%=change.getKey()%>"><%=change.getKey()%></label>
	    </div>
	    <div class="col-75" style="width: 70%">
	    	<input type="text" name="<%=change.getKey()%>" value="<%=change.getValue()%>" >
	    </div>
	    <div style="width: 5%;padding-top: 12px;float: left; margin-top: 6px;">
	    <img style="cursor: pointer; width: 100%" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIj8+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiBoZWlnaHQ9IjUxMnB4IiB2aWV3Qm94PSItNDAgMCA0MjcgNDI3LjAwMTMxIiB3aWR0aD0iNTEycHgiIGNsYXNzPSIiPjxnPjxwYXRoIGQ9Im0yMzIuMzk4NDM4IDE1NC43MDMxMjVjLTUuNTIzNDM4IDAtMTAgNC40NzY1NjMtMTAgMTB2MTg5YzAgNS41MTk1MzEgNC40NzY1NjIgMTAgMTAgMTAgNS41MjM0MzcgMCAxMC00LjQ4MDQ2OSAxMC0xMHYtMTg5YzAtNS41MjM0MzctNC40NzY1NjMtMTAtMTAtMTB6bTAgMCIgZGF0YS1vcmlnaW5hbD0iIzAwMDAwMCIgY2xhc3M9ImFjdGl2ZS1wYXRoIiBkYXRhLW9sZF9jb2xvcj0iIzAwMDAwMCIgZmlsbD0iIzAwQTk5MCIvPjxwYXRoIGQ9Im0xMTQuMzk4NDM4IDE1NC43MDMxMjVjLTUuNTIzNDM4IDAtMTAgNC40NzY1NjMtMTAgMTB2MTg5YzAgNS41MTk1MzEgNC40NzY1NjIgMTAgMTAgMTAgNS41MjM0MzcgMCAxMC00LjQ4MDQ2OSAxMC0xMHYtMTg5YzAtNS41MjM0MzctNC40NzY1NjMtMTAtMTAtMTB6bTAgMCIgZGF0YS1vcmlnaW5hbD0iIzAwMDAwMCIgY2xhc3M9ImFjdGl2ZS1wYXRoIiBkYXRhLW9sZF9jb2xvcj0iIzAwMDAwMCIgZmlsbD0iIzAwQTk5MCIvPjxwYXRoIGQ9Im0yOC4zOTg0MzggMTI3LjEyMTA5NHYyNDYuMzc4OTA2YzAgMTQuNTYyNSA1LjMzOTg0MyAyOC4yMzgyODEgMTQuNjY3OTY4IDM4LjA1MDc4MSA5LjI4NTE1NiA5LjgzOTg0NCAyMi4yMDcwMzIgMTUuNDI1NzgxIDM1LjczMDQ2OSAxNS40NDkyMTloMTg5LjIwMzEyNWMxMy41MjczNDQtLjAyMzQzOCAyNi40NDkyMTktNS42MDkzNzUgMzUuNzMwNDY5LTE1LjQ0OTIxOSA5LjMyODEyNS05LjgxMjUgMTQuNjY3OTY5LTIzLjQ4ODI4MSAxNC42Njc5NjktMzguMDUwNzgxdi0yNDYuMzc4OTA2YzE4LjU0Mjk2OC00LjkyMTg3NSAzMC41NTg1OTMtMjIuODM1OTM4IDI4LjA3ODEyNC00MS44NjMyODItMi40ODQzNzQtMTkuMDIzNDM3LTE4LjY5MTQwNi0zMy4yNTM5MDYtMzcuODc4OTA2LTMzLjI1NzgxMmgtNTEuMTk5MjE4di0xMi41Yy4wNTg1OTMtMTAuNTExNzE5LTQuMDk3NjU3LTIwLjYwNTQ2OS0xMS41MzkwNjMtMjguMDMxMjUtNy40NDE0MDYtNy40MjE4NzUtMTcuNTUwNzgxLTExLjU1NDY4NzUtMjguMDYyNS0xMS40Njg3NWgtODguNzk2ODc1Yy0xMC41MTE3MTktLjA4NTkzNzUtMjAuNjIxMDk0IDQuMDQ2ODc1LTI4LjA2MjUgMTEuNDY4NzUtNy40NDE0MDYgNy40MjU3ODEtMTEuNTk3NjU2IDE3LjUxOTUzMS0xMS41MzkwNjIgMjguMDMxMjV2MTIuNWgtNTEuMTk5MjE5Yy0xOS4xODc1LjAwMzkwNi0zNS4zOTQ1MzEgMTQuMjM0Mzc1LTM3Ljg3ODkwNyAzMy4yNTc4MTItMi40ODA0NjggMTkuMDI3MzQ0IDkuNTM1MTU3IDM2Ljk0MTQwNyAyOC4wNzgxMjYgNDEuODYzMjgyem0yMzkuNjAxNTYyIDI3OS44Nzg5MDZoLTE4OS4yMDMxMjVjLTE3LjA5NzY1NiAwLTMwLjM5ODQzNy0xNC42ODc1LTMwLjM5ODQzNy0zMy41di0yNDUuNWgyNTB2MjQ1LjVjMCAxOC44MTI1LTEzLjMwMDc4MiAzMy41LTMwLjM5ODQzOCAzMy41em0tMTU4LjYwMTU2Mi0zNjcuNWMtLjA2NjQwNy01LjIwNzAzMSAxLjk4MDQ2OC0xMC4yMTg3NSA1LjY3NTc4MS0xMy44OTQ1MzEgMy42OTE0MDYtMy42NzU3ODEgOC43MTQ4NDMtNS42OTUzMTMgMTMuOTI1NzgxLTUuNjA1NDY5aDg4Ljc5Njg3NWM1LjIxMDkzNy0uMDg5ODQ0IDEwLjIzNDM3NSAxLjkyOTY4OCAxMy45MjU3ODEgNS42MDU0NjkgMy42OTUzMTMgMy42NzE4NzUgNS43NDIxODggOC42ODc1IDUuNjc1NzgyIDEzLjg5NDUzMXYxMi41aC0xMjh6bS03MS4xOTkyMTkgMzIuNWgyNzAuMzk4NDM3YzkuOTQxNDA2IDAgMTggOC4wNTg1OTQgMTggMThzLTguMDU4NTk0IDE4LTE4IDE4aC0yNzAuMzk4NDM3Yy05Ljk0MTQwNyAwLTE4LTguMDU4NTk0LTE4LTE4czguMDU4NTkzLTE4IDE4LTE4em0wIDAiIGRhdGEtb3JpZ2luYWw9IiMwMDAwMDAiIGNsYXNzPSJhY3RpdmUtcGF0aCIgZGF0YS1vbGRfY29sb3I9IiMwMDAwMDAiIGZpbGw9IiMwMEE5OTAiLz48cGF0aCBkPSJtMTczLjM5ODQzOCAxNTQuNzAzMTI1Yy01LjUyMzQzOCAwLTEwIDQuNDc2NTYzLTEwIDEwdjE4OWMwIDUuNTE5NTMxIDQuNDc2NTYyIDEwIDEwIDEwIDUuNTIzNDM3IDAgMTAtNC40ODA0NjkgMTAtMTB2LTE4OWMwLTUuNTIzNDM3LTQuNDc2NTYzLTEwLTEwLTEwem0wIDAiIGRhdGEtb3JpZ2luYWw9IiMwMDAwMDAiIGNsYXNzPSJhY3RpdmUtcGF0aCIgZGF0YS1vbGRfY29sb3I9IiMwMDAwMDAiIGZpbGw9IiMwMEE5OTAiLz48L2c+IDwvc3ZnPgo=" style="width: 100%;">
	    </div>
	  	</div>
	<%
		}
	%>
	 <button type="submit">Save</button>
	</form>

<script type="text/javascript">
	let form = document.getElementById('newChanges');
	
	form.querySelectorAll("img").forEach(function(el){
	    el.addEventListener("click", function(e){
	        el.parentElement.parentElement.remove();
	    })
	})
</script>

</body>
</html>