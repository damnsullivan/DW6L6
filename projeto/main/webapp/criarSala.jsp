<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Criar Nova Sala</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: 'Arial', sans-serif;
	background-color: #f7f7f7;
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
	width: 100%;
	max-width: 600px;
	text-align: center;
}

h2 {
	font-size: 1.8rem;
	color: #333;
	margin-bottom: 20px;
}

form {
	display: flex;
	flex-direction: column;
	gap: 20px;
}

label {
	text-align: left;
	font-size: 1rem;
	color: #555;
}

input[type="text"], input[type="number"] {
	padding: 10px;
	font-size: 1rem;
	border: 1px solid #ddd;
	border-radius: 8px;
	width: 100%;
	margin-top: 5px;
}

button {
	background-color: #4CAF50;
	color: white;
	font-size: 1rem;
	padding: 10px;
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
		padding: 20px;
	}
	button {
		padding: 12px;
	}
}
</style>
</head>
<body>
	<div class="container">
		<h2>Criar Nova Sala</h2>
		<form action="salas" method="post">
			<label for="nome">Nome:</label> <input type="text" id="nome"
				name="nome" required> <label for="capacidade">Capacidade:</label>
			<input type="number" id="capacidade" name="capacidade" required>

			<label for="localizacao">Localização:</label> <input type="text"
				id="localizacao" name="localizacao">

			<button type="submit">Salvar Sala</button>
		</form>
		<a href="/SistemaReservaSalas/salas">Voltar para Lista de Salas</a>
	</div>
</body>
</html>
