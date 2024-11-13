<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Criar Nova Reserva</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f4f9;
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
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 600px;
	text-align: center;
}

h2 {
	font-size: 2rem;
	color: #333;
	margin-bottom: 25px;
}

form {
	display: flex;
	flex-direction: column;
	gap: 20px;
	text-align: left;
}

label {
	font-size: 1rem;
	color: #555;
	margin-bottom: 5px;
}

input[type="text"], input[type="number"], input[type="date"], input[type="time"]
	{
	padding: 12px;
	font-size: 1rem;
	border: 1px solid #ddd;
	border-radius: 8px;
	width: 100%;
	margin-top: 5px;
}

button {
	background-color: #4CAF50;
	color: white;
	font-size: 1.1rem;
	padding: 12px;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	transition: background-color 0.3s;
}

button:hover {
	background-color: #45a049;
}

a {
	display: inline-block;
	margin-top: 20px;
	text-decoration: none;
	color: #4CAF50;
	font-weight: bold;
	transition: color 0.3s;
}

a:hover {
	color: #45a049;
}

@media ( max-width : 768px) {
	.container {
		padding: 25px;
	}
	button {
		padding: 14px;
	}
}
</style>
</head>
<body>
	<div class="container">
		<h2>Criar Nova Reserva</h2>
		<form action="reservas" method="POST">
			<div class="form-group">
				<label for="sala_id">ID da Sala:</label> <input type="number"
					id="sala_id" name="sala_id" required>
			</div>

			<div class="form-group">
				<label for="hora_inicio">Hora de Início:</label> <input type="time"
					id="hora_inicio" name="hora_inicio" required>
			</div>

			<div class="form-group">
				<label for="hora_fim">Hora de Fim:</label> <input type="time"
					id="hora_fim" name="hora_fim" required>
			</div>

			<div class="form-group">
				<label for="data_reserva">Data da Reserva:</label> <input
					type="date" name="data_reserva" required
					pattern="\d{4}-\d{2}-\d{2}">
			</div>

			<div class="form-group">
				<label for="reservado_por">Reservado Por:</label> <input type="text"
					id="reservado_por" name="reservado_por" required>
			</div>

			<button type="submit">Salvar Reserva</button>
		</form>

		<a href="listarReservas.jsp" class="link-back">Voltar para Lista
			de Reservas</a>
	</div>
</body>
</html>