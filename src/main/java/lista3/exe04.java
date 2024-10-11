package lista3;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class exe04 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("set".equals(action)) {
			setCookie(request, response);
		} else if ("get".equals(action)) {
			getCookie(request, response);
		} else if ("remove".equals(action)) {
			removeCookie(request, response);
		} else {
			showForm(response);
		}
	}

	private void setCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		if (username != null && !username.trim().isEmpty()) {
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(24 * 60 * 60);
			response.addCookie(cookie);
			response.getWriter().println("Cookie 'username' foi definido com o valor: " + username);
		} else {
			response.getWriter().println("Por favor, forneça um nome de usuário.");
		}
	}

	private void getCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();
		String username = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("username".equals(cookie.getName())) {
					username = cookie.getValue();
					break;
				}
			}
		}
		if (username != null) {
			response.getWriter().println("O valor do cookie 'username' é: " + username);
		} else {
			response.getWriter().println("Cookie 'username' inexistente.");
		}
	}

	private void removeCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie cookie = new Cookie("username", "");
		cookie.setMaxAge(0); 
		response.addCookie(cookie);
		response.getWriter().println("Cookie 'username' foi removido.");
	}

	private void showForm(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>Gerenciamento de Cookies</h2>");
		out.println("<form action='exe04' method='GET'>");
		out.println("Nome de usuário: <input type='text' name='username'><br>");
		out.println("<input type='hidden' name='action' value='set'>");
		out.println("<input type='submit' value='Definir Cookie'>");
		out.println("</form>");
		out.println("<br><a href='exe04?action=get'>Ler Cookie</a>");
		out.println("<br><a href='exe04?action=remove'>Remover Cookie</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
