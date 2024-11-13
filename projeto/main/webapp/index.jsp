<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sistema de Reservas</title>
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
	text-align: center;
	margin: 0;
}

.container {
	background-color: #ffffff;
	padding: 40px 30px;
	border-radius: 12px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 450px;
}

h1 {
	font-size: 2rem;
	color: #333;
	margin-bottom: 30px;
	font-weight: normal;
}

ul {
	list-style: none;
	padding: 0;
}

li {
	margin: 15px 0;
}

a {
	text-decoration: none;
	color: #4CAF50;
	font-size: 1.1rem;
	padding: 12px 20px;
	border: 2px solid #4CAF50;
	border-radius: 6px;
	display: inline-block;
	transition: all 0.3s ease;
}

a:hover {
	background-color: #4CAF50;
	color: #fff;
	transform: translateY(-2px);
}

a:active {
	transform: translateY(1px);
}

footer {
	font-size: 0.85rem;
	margin-top: 20px;
	color: #777;
}

footer a {
	color: #4CAF50;
	text-decoration: none;
}

footer a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Bem-vindo ao Sistema de Reservas</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath}/salas">Listar
					Salas</a></li>
			<li><a href="${pageContext.request.contextPath}/criarSala.jsp">Criar
					Nova Sala</a></li>
			<li><a href="${pageContext.request.contextPath}/reservas">Listar
					Reservas</a></li>
			<li><a
				href="${pageContext.request.contextPath}/criarReserva.jsp">Criar
					Nova Reserva</a></li>
		</ul>
	</div>
</body>
</html>
