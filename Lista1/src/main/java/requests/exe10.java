package requests;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class exe10 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<String> tarefas = new ArrayList<>();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String novaTarefa = request.getParameter("tarefa");

		if (novaTarefa != null && !novaTarefa.trim().isEmpty()) {
			tarefas.add(novaTarefa.trim());
		}

		response.sendRedirect("exe10");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Lista de Tarefas</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Lista de Tarefas</h1>");

			if (tarefas.isEmpty()) {
				out.println("<p>Nenhuma tarefa foi adicionada ainda.</p>");
			} else {
				out.println("<ul>");
				for (String tarefa : tarefas) {
					out.println("<li>" + tarefa + "</li>");
				}
				out.println("</ul>");
			}

			out.println("<h2>Adicionar Nova Tarefa</h2>");
			out.println("<form action='exe10' method='POST'>");
			out.println("  <label for='tarefa'>Descrição da Tarefa:</label><br>");
			out.println("  <input type='text' id='tarefa' name='tarefa' required><br><br>");
			out.println("  <input type='submit' value='Adicionar Tarefa'>");
			out.println("</form>");

			out.println("</body>");
			out.println("</html>");
		}
	}

}
