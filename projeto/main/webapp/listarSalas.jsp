<%@page import="model.Sala"%>
<%@page import="java.util.List"%>

<%
List<Sala> salas = (List<Sala>) request.getAttribute("salas");
%>

<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista de Salas</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: 'Arial', sans-serif;
	background-color: #f9f9f9;
	color: #333;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.container {
	background-color: #fff;
	padding: 30px;
	border-radius: 12px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
	width: 80%;
	max-width: 1000px;
	overflow-x: auto;
}

h2 {
	font-size: 1.8rem;
	color: #333;
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 12px;
	text-align: left;
}

th {
	background-color: #4CAF50;
	color: white;
}

td {
	background-color: #f9f9f9;
	border-bottom: 1px solid #ddd;
}

td a {
	text-decoration: none;
	color: #4CAF50;
	font-weight: bold;
	transition: color 0.3s;
}

td a:hover {
	color: #45a049;
}

@media ( max-width : 768px) {
	table {
		font-size: 0.9rem;
	}
	th, td {
		padding: 8px;
	}
}
</style>
</head>
<body>
	<div class="container">
		<h2>Lista de Salas</h2>
		<a href="/SistemaReservaSalas"
			style="text-decoration: none; color: #4CAF50; font-weight: bold; margin-bottom: 20px; display: inline-block;">Voltar
			para a Página Inicial</a>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Capacidade</th>
					<th>Localização</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Sala sala : salas) {
				%>
				<tr>
					<td><a
						href="/SistemaReservaSalas/salas?id=<%=sala.getId()%>"><%=sala.getId()%></a></td>
					<td><%=sala.getNome()%></td>
					<td><%=sala.getCapacidade()%></td>
					<td><%=sala.getLocalizacao()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>

</html>
