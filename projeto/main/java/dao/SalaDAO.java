package dao;

import model.Sala;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {
	private Connection connection;

	public SalaDAO() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao estabelecer conexão com o banco de dados.", e);
		}
	}

	public void salvar(Sala sala) {
		String sql = "INSERT INTO salas (nome, capacidade, localizacao) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, sala.getNome());
			stmt.setInt(2, sala.getCapacidade());
			stmt.setString(3, sala.getLocalizacao());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao salvar a sala: " + e.getMessage(), e);
		}
	}

	public List<Sala> listar() {
		List<Sala> salas = new ArrayList<>();
		String sql = "SELECT * FROM salas";

		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Sala sala = mapSala(rs);
				salas.add(sala);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao listar salas: " + e.getMessage(), e);
		}
		return salas;
	}

	public Sala buscarPorId(int id) {
		Sala sala = null;
		String sql = "SELECT * FROM salas WHERE id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					sala = mapSala(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao buscar sala por ID: " + e.getMessage(), e);
		}
		return sala;
	}

	public void atualizar(Sala sala) {
		String sql = "UPDATE salas SET nome = ?, capacidade = ?, localizacao = ? WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, sala.getNome());
			stmt.setInt(2, sala.getCapacidade());
			stmt.setString(3, sala.getLocalizacao());
			stmt.setInt(4, sala.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao atualizar a sala: " + e.getMessage(), e);
		}
	}

	public void excluir(int id) {
		String sql = "DELETE FROM salas WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao excluir a sala: " + e.getMessage(), e);
		}
	}

	private static Sala mapSala(ResultSet rs) throws SQLException {
		Sala sala = new Sala();
		sala.setId(rs.getInt("id"));
		sala.setNome(rs.getString("nome"));
		sala.setCapacidade(rs.getInt("capacidade"));
		sala.setLocalizacao(rs.getString("localizacao"));
		return sala;
	}

	public void fecharConexao() {
		try {
			if (this.connection != null && !this.connection.isClosed()) {
				this.connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
