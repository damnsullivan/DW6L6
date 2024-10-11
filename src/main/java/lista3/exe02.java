package lista3;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

public class exe02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mensagem = request.getParameter("mensagem");

		if (mensagem == null || mensagem.trim().isEmpty()) {
			mensagem = "Nenhuma mensagem fornecida";
		}

		request.setAttribute("mensagemProcessada", "Mensagem processada: " + mensagem);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/servletexe02");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
