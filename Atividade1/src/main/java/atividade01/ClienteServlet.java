package atividade01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static List<Cliente> clientes = new ArrayList<>();
	private static int nextId = 1;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			action = "list";
		}

		switch (action) {
		case "add":
			showAddForm(response);
			break;
		case "edit":
			showEditForm(request, response);
			break;
		case "delete":
			deleteCliente(request, response);
			break;
		default:
			listClientes(response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("add".equals(action)) {
			String nome = request.getParameter("nome");
			Cliente cliente = new Cliente(nextId++, nome);
			clientes.add(cliente);
		} else if ("edit".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String nome = request.getParameter("nome");
			for (Cliente cliente : clientes) {
				if (cliente.getId() == id) {
					cliente.setNome(nome);
					break;
				}
			}
		}

		response.sendRedirect("cliente");
	}

	private void showAddForm(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>Adicionar Cliente</h2>");
		out.println("<form method='post' action='cliente'>");
		out.println("<input type='hidden' name='action' value='add'>");
		out.println("Nome: <input type='text' name='nome'><br>");
		out.println("<input type='submit' value='Adicionar'>");
		out.println("</form>");
		out.println("<a href='cliente'>Voltar para lista</a>");
		out.println("</body></html>");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Cliente cliente = null;
		for (Cliente c : clientes) {
			if (c.getId() == id) {
				cliente = c;
				break;
			}
		}

		if (cliente != null) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<h2>Editar Cliente</h2>");
			out.println("<form method='post' action='cliente'>");
			out.println("<input type='hidden' name='action' value='edit'>");
			out.println("<input type='hidden' name='id' value='" + cliente.getId() + "'>");
			out.println("Nome: <input type='text' name='nome' value='" + cliente.getNome() + "'><br>");
			out.println("<input type='submit' value='Atualizar'>");
			out.println("</form>");
			out.println("<a href='cliente'>Voltar para lista</a>");
			out.println("</body></html>");
		}
	}

	private void deleteCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		clientes.removeIf(c -> c.getId() == id);
		response.sendRedirect("cliente");
	}

	private void listClientes(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>Lista de Clientes</h2>");
		out.println("<table border='1'><tr><th>ID</th><th>Nome</th><th>Ações</th></tr>");
		for (Cliente cliente : clientes) {
			out.println("<tr><td>" + cliente.getId() + "</td><td>" + cliente.getNome() + "</td>");
			out.println("<td><a href='cliente?action=edit&id=" + cliente.getId() + "'>Editar</a> | ");
			out.println("<a href='cliente?action=delete&id=" + cliente.getId() + "'>Excluir</a></td></tr>");
		}
		out.println("</table>");
		out.println("<br><a href='cliente?action=add'>Adicionar novo cliente</a>");
		out.println("</body></html>");
	}

}
