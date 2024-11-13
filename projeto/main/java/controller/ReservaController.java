package controller;

import dao.ReservaDAO;
import dto.ReservaDto;
import jakarta.servlet.annotation.WebServlet;
import model.Reserva;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@WebServlet(urlPatterns = { "/reservas", "/reservas/editar" })
public class ReservaController extends HttpServlet {

	private final ReservaDAO reservaDAO = new ReservaDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String pathInfo = request.getServletPath();

		if ("/reservas/editar".equals(pathInfo)) {
			handleEditarGet(request, response);
		} else {
			handleListarGet(request, response);
		}
	}

	private void handleListarGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Reserva> reservas = reservaDAO.listar();
		List<ReservaDto> reservasDto = reservas.stream().map(reserva -> ReservaDto.fromReserva(reserva, null)).toList();
		request.setAttribute("reservas", reservasDto);
		request.getRequestDispatcher("listarReservas.jsp").forward(request, response);
	}

	private void handleEditarGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStr = request.getParameter("id");

		try {
			if (idStr != null && !idStr.trim().isEmpty()) {
				Integer id = Integer.parseInt(idStr);
				Reserva reserva = reservaDAO.buscarPorId(id);

				if (reserva != null) {
					ReservaDto reservaDto = ReservaDto.fromReserva(reserva, null);
					request.setAttribute("reserva", reservaDto);
					request.getRequestDispatcher("/editarReserva.jsp").forward(request, response);
					return;
				}
			}
			response.sendRedirect("/SistemaReservaSalas/reservas");
		} catch (NumberFormatException e) {
			response.sendRedirect("/SistemaReservaSalas/reservas");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Reserva reserva = mapReserva(request);
		if (Objects.isNull(reserva.getId())) {
			reservaDAO.salvar(reserva);
		} else {
			reservaDAO.atualizar(reserva);
		}
		response.sendRedirect("/SistemaReservaSalas/reservas");
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String idStr = request.getParameter("id");
			if (idStr != null && !idStr.trim().isEmpty()) {
				Integer id = Integer.parseInt(idStr);
				boolean sucesso = reservaDAO.deletar(id);

				if (sucesso) {
					response.setStatus(HttpServletResponse.SC_OK);
				} else {
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				}
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private static Reserva mapReserva(HttpServletRequest request) {
		Integer id = parseInteger(request.getParameter("id"));
		Integer salaId = parseInteger(request.getParameter("sala_id"));
		Date dataReserva = parseDate(request.getParameter("data_reserva"));
		Time horaInicio = parseTime(request.getParameter("hora_inicio"));
		Time horaFim = parseTime(request.getParameter("hora_fim"));
		String reservadoPor = request.getParameter("reservado_por");

		return new Reserva(id, salaId, dataReserva, horaInicio, horaFim, reservadoPor);
	}

	private static Integer parseInteger(String value) {
		try {
			return Optional.ofNullable(value).filter(s -> !s.isEmpty()).map(Integer::parseInt).orElse(null);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Date parseDate(String value) {
		try {
			if (value == null || value.trim().isEmpty()) {
				System.out.println("parseDate: valor recebido está nulo ou vazio");
				return null;
			}

			value = value.trim();
			System.out.println("parseDate: tentando converter valor: '" + value + "'");

			if (!value.matches("\\d{4}-\\d{2}-\\d{2}")) {
				System.out.println("parseDate: formato inválido. Esperado: yyyy-MM-dd");
				return null;
			}

			Date data = Date.valueOf(value);
			System.out.println("parseDate: conversão bem sucedida: " + data);
			return data;
		} catch (IllegalArgumentException e) {
			System.out.println("parseDate: erro na conversão: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	private static Time parseTime(String value) {
		try {
			if (value == null || value.trim().isEmpty()) {
				System.out.println("Hora recebida está vazia");
				return null;
			}
			if (value.matches("\\d{2}:\\d{2}")) {
				value = value + ":00";
			}
			System.out.println("Tentando converter hora: " + value);
			return Time.valueOf(value);
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao converter hora: " + value);
			e.printStackTrace();
			return null;
		}
	}
}
