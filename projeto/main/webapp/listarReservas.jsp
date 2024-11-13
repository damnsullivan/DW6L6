<%@ page import="java.util.List"%>
<%@ page import="dto.ReservaDto"%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista de Reservas</title>
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
	align-items: flex-start;
	height: 100vh;
	margin: 0;
	padding-top: 50px;
}

.container {
	background-color: #fff;
	padding: 30px;
	border-radius: 12px;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 800px;
	text-align: center;
}

h2 {
	font-size: 2rem;
	color: #333;
	margin-bottom: 25px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	padding: 12px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #4CAF50;
	color: white;
}

tr:hover {
	background-color: #f1f1f1;
}

.link-back {
	display: inline-block;
	margin-top: 20px;
	text-decoration: none;
	color: #4CAF50;
	font-weight: bold;
	transition: color 0.3s;
}

.link-back:hover {
	color: #45a049;
}

.action-links {
	margin-top: 10px;
}

.action-links a {
	text-decoration: none;
	margin: 0 5px;
	color: #ff5733;
}

.action-links a:hover {
	color: #ff0000;
}

@media ( max-width : 768px) {
	.container {
		padding: 20px;
	}
	table {
		font-size: 0.9rem;
	}
}
</style>
</head>
<body>
	<div class="container">
		<h2>Lista de Reservas</h2>
		<table>
			<thead>
				<tr>
					<th>ID da Sala</th>
					<th>Data da Reserva</th>
					<th>Hora de Início</th>
					<th>Hora de Fim</th>
					<th>Reservado Por</th>
					<th>Ações</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<ReservaDto> reservas = (List<ReservaDto>) request.getAttribute("reservas");
				if (reservas != null && !reservas.isEmpty()) {
					for (ReservaDto reservaDto : reservas) {
				%>
				<tr>
					<td><%=reservaDto.getSalaId()%></td>
					<td><%=reservaDto.getDataReserva()%></td>
					<td><%=reservaDto.getHoraInicio()%></td>
					<td><%=reservaDto.getHoraFim()%></td>
					<td><%=reservaDto.getReservadoPor()%></td>
					<td class="action-links"><a
						href="/SistemaReservaSalas/reservas/editar?id=<%=reservaDto.getId()%>">Editar</a>
						<a href="javascript:void(0);"
						onclick="confirmDelete(<%=reservaDto.getId()%>)">Excluir</a></td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="6">Nenhuma reserva encontrada.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<a href="/SistemaReservaSalas" class="link-back">Voltar para a
			Home</a>
	</div>

	<script>
    function confirmDelete(id) {
        if (confirm('Tem certeza que deseja excluir esta reserva?')) {
            fetch('/SistemaReservaSalas/reservas?id=' + id, {
                method: 'DELETE',
            })
            .then(response => {
                if (response.ok) {
                    alert('Reserva excluída com sucesso');
                    location.href = '/SistemaReservaSalas/reservas'; 
                } else if (response.status === 404) {
                    alert('Reserva não encontrada');
                } else if (response.status === 400) {
                    alert('Requisição inválida');
                } else {
                    alert('Erro ao excluir reserva: ' + response.status);
                }
            })
            .catch(error => {
                console.error('Erro:', error);
                alert('Erro ao tentar excluir reserva');
            });
        }
    }
</script>
</body>
</html>
