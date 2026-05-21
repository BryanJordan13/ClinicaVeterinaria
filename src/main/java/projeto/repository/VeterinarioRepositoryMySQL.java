package projeto.repository;

import projeto.database.DatabaseConnection;
import projeto.model.Veterinario;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class VeterinarioRepositoryMySQL {

    public void guardar(Veterinario veterinario) {

        String sql = """
                INSERT INTO veterinarios(nome, especialidade)
                VALUES (?, ?)
                """;

        try (

                Connection conn = DatabaseConnection.getConnection();

                PreparedStatement stmt = conn.prepareStatement(sql)

        ) {

            stmt.setString(1, veterinario.getNome());

            stmt.setString(2, veterinario.getEspecialidade());

            stmt.executeUpdate();

            System.out.println("Veterinário guardado!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}