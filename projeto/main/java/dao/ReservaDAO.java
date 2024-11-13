package dao;

import model.Reserva;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

	public List<Reserva> listar() {
		List<Reserva> reservas = new ArrayList<>();
		String sql = "SELECT * FROM reservas";

		try (Connection conn = ConnectionFactory.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				reservas.add(mapFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reservas;
	}

	public Reserva buscarPorId(int id) {
		String sql = "SELECT * FROM reservas WHERE id = ?";
		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return mapFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Reserva mapFromResultSet(ResultSet rs) throws SQLException {
		Reserva reserva = new Reserva();
		reserva.setId(rs.getInt("id"));
		reserva.setSalaId(rs.getInt("sala_id"));
		reserva.setDataReserva(rs.getDate("data_reserva"));
		reserva.setHoraInicio(rs.getTime("hora_inicio"));
		reserva.setHoraFim(rs.getTime("hora_fim"));
		reserva.setReservadoPor(rs.getString("reservado_por"));
		return reserva;
	}

	public void atualizar(Reserva reserva) {
		String sql = "UPDATE reservas SET sala_id = ?, data_reserva = ?, hora_inicio = ?, hora_fim = ?, reservado_por = ? WHERE id = ?";
		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, reserva.getSalaId());
			stmt.setDate(2, reserva.getDataReserva());
			stmt.setTime(3, reserva.getHoraInicio());
			stmt.setTime(4, reserva.getHoraFim());
			stmt.setString(5, reserva.getReservadoPor());
			stmt.setInt(6, reserva.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void salvar(Reserva reserva) {
		String sql = "INSERT INTO reservas (sala_id, data_reserva, hora_inicio, hora_fim, reservado_por) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, reserva.getSalaId());
			stmt.setDate(2, reserva.getDataReserva());
			stmt.setTime(3, reserva.getHoraInicio());
			stmt.setTime(4, reserva.getHoraFim());
			stmt.setString(5, reserva.getReservadoPor());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean deletar(Integer id) {
		String sql = "DELETE FROM reservas WHERE id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			int linhasAfetadas = stmt.executeUpdate();

			return linhasAfetadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
