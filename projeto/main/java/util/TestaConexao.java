package util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {
	public static void main(String[] args) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
