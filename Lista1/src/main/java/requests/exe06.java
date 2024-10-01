package requests;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class exe06 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> comentarios = new ArrayList<>();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String nome = request.getParameter("nome");
		String comentario = request.getParameter("comentario");

		if (nome != null && !nome.trim().isEmpty() && comentario != null && !comentario.trim().isEmpty()) {
			comentarios.add(nome + ": " + comentario);
		}

		exibirComentarios(response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		exibirComentarios(response);
	}

	private void exibirComentarios(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Lista de Comentários</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Lista de Comentários</h1>");

			if (comentarios.isEmpty()) {
				out.println("<p>Nenhum comentário foi adicionado ainda.</p>");
			} else {
				out.println("<ul>");
				for (String comentario : comentarios) {
					out.println("<li>" + comentario + "</li>");
				}
				out.println("</ul>");
			}

			out.println("<a href='comentarios.html'>Ir para o formulário</a>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
