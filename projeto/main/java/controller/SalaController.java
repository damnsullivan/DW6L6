package controller;

import dao.SalaDAO;
import jakarta.servlet.annotation.WebServlet;
import model.Sala;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@WebServlet("/salas")
public class SalaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalaDAO salaDAO;

	@Override
	public void init() throws ServletException {
		salaDAO = new SalaDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Optional<Integer> id = Optional.ofNullable(request.getParameter("id")).map(Integer::parseInt);
		if (id.isEmpty()) {
			List<Sala> salas = salaDAO.listar();
			request.setAttribute("salas", salas);
			request.getRequestDispatcher("listarSalas.jsp").forward(request, response);
			return;
		}

		request.setAttribute("sala", salaDAO.buscarPorId(id.get()));
		request.getRequestDispatcher("editarSala.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Sala sala = mapSala(request);
		if (Objects.isNull(sala.getId())) {
			salaDAO.salvar(sala);
		} else {
			if ("deletar".equals(request.getParameter("action")))
				salaDAO.excluir(sala.getId());
			else
				salaDAO.atualizar(sala);
		}
		doGet(request, response);
	}

	private static Sala mapSala(HttpServletRequest request) {
		Integer id = Optional.ofNullable(request.getParameter("id")).map(Integer::parseInt).orElse(null);
		String nome = request.getParameter("nome");
		Integer capacidade = Optional.ofNullable(request.getParameter("capacidade")).map(Integer::parseInt)
				.orElse(null);
		String localizacao = request.getParameter("localizacao");
		return new Sala(id, nome, capacidade, localizacao);
	}

	private void excluirSala(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			salaDAO.excluir(id);
		} catch (NumberFormatException e) {
		}
		response.sendRedirect("SalaController?action=listar");
	}
}
