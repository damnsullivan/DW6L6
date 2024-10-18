package atividade01;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/compra")
public class CompraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		case "remove":
			removeCompra(request, response);
			break;
		default:
			listCompras(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("add".equals(action)) {
			int clienteId = Integer.parseInt(request.getParameter("clienteId"));
			int produtoId = Integer.parseInt(request.getParameter("produtoId"));

			Cliente cliente = null;
			Produto produto = null;

			for (Cliente c : ClienteServlet.clientes) {
				if (c.getId() == clienteId) {
					cliente = c;
					break;
				}
			}

			for (Produto p : ProdutoServlet.produtos) {
				if (p.getId() == produtoId) {
					produto = p;
					break;
				}
			}

			if (cliente != null && produto != null) {
				cliente.addProduto(produto);
			}
		}

		response.sendRedirect("compra");
	}

	private void showAddForm(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>Adicionar Compra</h2>");
		out.println("<form method='post' action='compra'>");
		out.println("<input type='hidden' name='action' value='add'>");
		out.println("Cliente ID: <input type='number' name='clienteId'><br>");
		out.println("Produto ID: <input type='number' name='produtoId'><br>");
		out.println("<input type='submit' value='Adicionar Compra'>");
		out.println("</form>");
		out.println("<a href='compra'>Voltar para lista</a>");
		out.println("</body></html>");
	}

	private void removeCompra(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int clienteId = Integer.parseInt(request.getParameter("clienteId"));
		int produtoId = Integer.parseInt(request.getParameter("produtoId"));

		Cliente cliente = null;
		Produto produto = null;

		for (Cliente c : ClienteServlet.clientes) {
			if (c.getId() == clienteId) {
				cliente = c;
				break;
			}
		}
		if (cliente != null) {
			for (Produto p : cliente.getProdutos()) {
				if (p.getId() == produtoId) {
					produto = p;
					break;
				}
			}
			if (produto != null) {
				cliente.removeProduto(produto);
			}
		}

		response.sendRedirect("compra");
	}

	private void listCompras(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>Lista de Compras</h2>");

		for (Cliente cliente : ClienteServlet.clientes) {
			out.println("<h3>Cliente: " + cliente.getNome() + " (ID: " + cliente.getId() + ")</h3>");
			if (cliente.getProdutos().isEmpty()) {
				out.println("<p>Nenhum produto comprado.</p>");
			} else {
				out.println("<table border='1'><tr><th>ID</th><th>Nome</th><th>Preço</th><th>Ação</th></tr>");
				for (Produto produto : cliente.getProdutos()) {
					out.println("<tr><td>" + produto.getId() + "</td><td>" + produto.getNome() + "</td><td>"
							+ produto.getPreco() + "</td>");
					out.println("<td><a href='compra?action=remove&clienteId=" + cliente.getId() + "&produtoId="
							+ produto.getId() + "'>Remover</a></td></tr>");
				}
				out.println("</table>");
			}
		}

		out.println("<br><a href='compra?action=add'>Adicionar nova compra</a>");
		out.println("<br><a href='cliente'>Gerenciar Clientes</a>");
		out.println("<br><a href='produto'>Gerenciar Produtos</a>");
		out.println("</body></html>");
	}
}