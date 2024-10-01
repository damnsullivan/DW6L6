package requests;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class exe09 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<String> nomes = new ArrayList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String nomeParaAdicionar = request.getParameter("adicionar");
		String nomeParaRemover = request.getParameter("remover");

		if (nomeParaAdicionar != null && !nomeParaAdicionar.trim().isEmpty()) {
			nomes.add(nomeParaAdicionar.trim());
		}

		if (nomeParaRemover != null && !nomeParaRemover.trim().isEmpty()) {
			nomes.remove(nomeParaRemover.trim());
		}

		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Lista de Nomes</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Lista de Nomes</h1>");

			if (nomes.isEmpty()) {
				out.println("<p>A lista está vazia.</p>");
			} else {
				out.println("<ul>");
				for (String nome : nomes) {
					out.println("<li>" + nome + " <a href='exe09?remover=" + nome + "'>[Remover]</a></li>");
				}
				out.println("</ul>");
			}

			out.println("<h2>Adicionar Nome</h2>");
			out.println("<form action='exe09' method='GET'>");
			out.println("  <input type='text' name='adicionar' required>");
			out.println("  <input type='submit' value='Adicionar'>");
			out.println("</form>");

			out.println(
					"<p>Para remover um nome, clique no link [Remover] ao lado do nome ou use a URL: /exe09?remover=NomeParaRemover</p>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
