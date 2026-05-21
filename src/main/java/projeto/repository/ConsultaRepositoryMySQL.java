package projeto.repository;

import projeto.database.DatabaseConnection;
import projeto.model.Consulta;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ConsultaRepositoryMySQL {

    public void guardar(Consulta consulta) {

        String sql = """
                INSERT INTO consultas
                (animal_id, veterinario_id, diagnostico, data)
                VALUES (?, ?, ?, ?)
                """;

        try (

                Connection conn = DatabaseConnection.getConnection();

                PreparedStatement stmt = conn.prepareStatement(sql)

        ) {

            stmt.setString(1, consulta.getDescricao());

            stmt.executeUpdate();

            System.out.println("Consulta guardada!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}