package requests;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class exe07 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, Integer> acessosPorUsuario = new HashMap<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String nome = request.getParameter("nome");

		if (nome != null && !nome.trim().isEmpty()) {
			acessosPorUsuario.put(nome, acessosPorUsuario.getOrDefault(nome, 0) + 1);
		}

		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Contador de Acessos por Usuário</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Contador de Acessos por Usuário</h1>");

			if (acessosPorUsuario.isEmpty()) {
				out.println("<p>Nenhum usuário acessou a página ainda.</p>");
			} else {
				out.println("<ul>");
				for (Map.Entry<String, Integer> entry : acessosPorUsuario.entrySet()) {
					out.println("<li>" + entry.getKey() + ": " + entry.getValue() + " acesso(s)</li>");
				}
				out.println("</ul>");
			}

			out.println("<p>Para registrar um acesso, use a URL: /exe07?nome=SeuNome</p>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
