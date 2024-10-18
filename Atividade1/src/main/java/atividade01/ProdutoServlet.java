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

@WebServlet("/produto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static List<Produto> produtos = new ArrayList<>();
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
			deleteProduto(request, response);
			break;
		default:
			listProdutos(response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("add".equals(action)) {
			String nome = request.getParameter("nome");
			double preco = Double.parseDouble(request.getParameter("preco"));
			Produto produto = new Produto(nextId++, nome, preco);
			produtos.add(produto);
		} else if ("edit".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String nome = request.getParameter("nome");
			double preco = Double.parseDouble(request.getParameter("preco"));
			for (Produto produto : produtos) {
				if (produto.getId() == id) {
					produto.setNome(nome);
					produto.setPreco(preco);
					break;
				}
			}
		}

		response.sendRedirect("produto");
	}

	private void showAddForm(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>Adicionar Produto</h2>");
		out.println("<form method='post' action='produto'>");
		out.println("<input type='hidden' name='action' value='add'>");
		out.println("Nome: <input type='text' name='nome'><br>");
		out.println("Preço: <input type='number' step='0.01' name='preco'><br>");
		out.println("<input type='submit' value='Adicionar'>");
		out.println("</form>");
		out.println("<a href='produto'>Voltar para lista</a>");
		out.println("</body></html>");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Produto produto = null;
		for (Produto p : produtos) {
			if (p.getId() == id) {
				produto = p;
				break;
			}
		}

		if (produto != null) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<h2>Editar Produto</h2>");
			out.println("<form method='post' action='produto'>");
			out.println("<input type='hidden' name='action' value='edit'>");
			out.println("<input type='hidden' name='id' value='" + produto.getId() + "'>");
			out.println("Nome: <input type='text' name='nome' value='" + produto.getNome() + "'><br>");
			out.println("Preço: <input type='number' step='0.01' name='preco' value='" + produto.getPreco() + "'><br>");
			out.println("<input type='submit' value='Atualizar'>");
			out.println("</form>");
			out.println("<a href='produto'>Voltar para lista</a>");
			out.println("</body></html>");
		}
	}

	private void deleteProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		produtos.removeIf(p -> p.getId() == id);
		response.sendRedirect("produto");
	}

	private void listProdutos(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>Lista de Produtos</h2>");
		out.println("<table border='1'><tr><th>ID</th><th>Nome</th><th>Preço</th><th>Ações</th></tr>");
		for (Produto produto : produtos) {
			out.println("<tr><td>" + produto.getId() + "</td><td>" + produto.getNome() + "</td><td>"
					+ produto.getPreco() + "</td>");
			out.println("<td><a href='produto?action=edit&id=" + produto.getId() + "'>Editar</a> | ");
			out.println("<a href='produto?action=delete&id=" + produto.getId() + "'>Excluir</a></td></tr>");
		}
		out.println("</table>");
		out.println("<br><a href='produto?action=add'>Adicionar novo produto</a>");
		out.println("</body></html>");
	}

}
