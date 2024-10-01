package requests;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class exe08 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<Produto> produtos = new ArrayList<>();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String nome = request.getParameter("nome");
		String precoStr = request.getParameter("preco");

		if (nome != null && !nome.trim().isEmpty() && precoStr != null && !precoStr.trim().isEmpty()) {
			try {
				double preco = Double.parseDouble(precoStr);
				produtos.add(new Produto(nome, preco));
			} catch (NumberFormatException e) {
			}
		}

		response.sendRedirect("exe08");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Lista de Produtos</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Lista de Produtos</h1>");

			if (produtos.isEmpty()) {
				out.println("<p>Nenhum produto foi cadastrado ainda.</p>");
			} else {
				out.println("<table border='1'>");
				out.println("<tr><th>Nome</th><th>Preço</th></tr>");
				for (Produto produto : produtos) {
					out.println("<tr><td>" + produto.getNome() + "</td><td>R$ "
							+ String.format("%.2f", produto.getPreco()) + "</td></tr>");
				}
				out.println("</table>");
			}

			out.println("<br><a href='cadastro_produto.html'>Cadastrar Novo Produto</a>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	private static class Produto {
		private String nome;
		private double preco;

		public Produto(String nome, double preco) {
			this.nome = nome;
			this.preco = preco;
		}

		public String getNome() {
			return nome;
		}

		public double getPreco() {
			return preco;
		}
	}

}
