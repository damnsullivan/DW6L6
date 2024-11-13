<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="dto.ReservaDto"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.sql.Time"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Editar Reserva</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

.container {
	width: 80%;
	margin: 0 auto;
	padding: 20px;
	background-color: white;
	border-radius: 5px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h2 {
	color: #333;
}

.form-group {
	margin-bottom: 15px;
}

label {
	display: block;
	margin-bottom: 5px;
	color: #666;
}

input {
	width: 100%;
	padding: 10px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

button {
	padding: 10px 20px;
	background-color: #4CAF50;
	color: white;
	border: none;
	cursor: pointer;
	border-radius: 4px;
}

button:hover {
	background-color: #45a049;
}

.link-back {
	display: inline-block;
	margin-top: 20px;
	color: #007BFF;
	text-decoration: none;
}

.link-back:hover {
	text-decoration: underline;
}
</style>

</head>
<body>
	<div class="container">
		<h2>Editar Reserva</h2>

		<%
		ReservaDto reserva = (ReservaDto) request.getAttribute("reserva");
		if (reserva != null) {
		%>

		<form action="/SistemaReservaSalas/reservas" method="POST">
			<input type="hidden" name="id" value="<%=reserva.getId()%>">

			<div class="form-group">
				<label for="sala_id">ID da Sala:</label> <input type="number"
					id="sala_id" name="sala_id" value="<%=reserva.getSalaId()%>"
					required>
			</div>

			<div class="form-group">
				<label for="data_reserva">Data da Reserva:</label> <input
					type="date" id="data_reserva" name="data_reserva"
					value="<%=reserva.getDataReserva()%>" required>
			</div>

			<div class="form-group">
				<label for="hora_inicio">Hora de Início:</label> <input type="time"
					id="hora_inicio" name="hora_inicio"
					value="<%=reserva.getHoraInicio() != null ? reserva.getHoraInicio().toString().substring(0, 5) : ""%>"
					required>
			</div>

			<div class="form-group">
				<label for="hora_fim">Hora de Fim:</label> <input type="time"
					id="hora_fim" name="hora_fim"
					value="<%=reserva.getHoraFim() != null ? reserva.getHoraFim().toString().substring(0, 5) : ""%>"
					required>
			</div>

			<div class="form-group">
				<label for="reservado_por">Reservado Por:</label> <input type="text"
					id="reservado_por" name="reservado_por"
					value="<%=reserva.getReservadoPor()%>" required>
			</div>

			<button type="submit" class="btn-primary">Salvar Alterações</button>
		</form>

		<%
		} else {
		%>
		<p>Reserva não encontrada.</p>
		<%
		}
		%>

		<a href="/SistemaReservaSalas/reservas" class="link-back">Voltar
			para a Lista</a>
	</div>
</body>
</html>
