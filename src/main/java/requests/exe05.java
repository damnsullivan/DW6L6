package requests;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class exe05 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<String> nomes = new ArrayList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String novoNome = request.getParameter("nome");

		if (novoNome != null && !novoNome.trim().isEmpty()) {
			nomes.add(novoNome.trim());
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
				out.println("<p>Nenhum nome foi adicionado ainda.</p>");
			} else {
				out.println("<ul>");
				for (String nome : nomes) {
					out.println("<li>" + nome + "</li>");
				}
				out.println("</ul>");
			}

			out.println("<p>Para adicionar um novo nome, use a URL: /exe05?nome=SeuNome</p>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
