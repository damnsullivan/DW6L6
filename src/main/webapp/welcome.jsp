<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// Verifica se o usuário está logado
String username = (String) session.getAttribute("username");
if (username == null) {
	response.sendRedirect("login2.html");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bem-vindo</title>
</head>
<body>
	<h1>
		Bem-vindo,
		<%=username%>!
	</h1>
	<p>Você está logado com sucesso.</p>
	<a href="exe03?action=logout">Logout</a>
</body>
</html>
