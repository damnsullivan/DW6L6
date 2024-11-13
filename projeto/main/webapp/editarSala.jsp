<%@ page import="model.Sala"%>

<%
Sala sala = (Sala) request.getAttribute("sala");
%>

<form action="salas" method="post">
	<input type="hidden" name="id" value="<%=sala.getId()%>"> <label>Nome:
		<input type="text" name="nome" value="<%=sala.getNome()%>">
	</label><br> <label>Capacidade: <input type="number"
		name="capacidade" value="<%=sala.getCapacidade()%>"></label><br>
	<label>Localizacao: <input type="text" name="localizacao"
		value="<%=sala.getLocalizacao()%>"></label><br>
	<button type="submit">Atualizar</button>
</form>
<form action="salas?action=deletar" method="post">
	<input type="hidden" name="id" value="<%=sala.getId()%>">
	<button type="submit">Deletar</button>
</form>
