package projeto.repository;

import projeto.database.DatabaseConnection;
import projeto.model.Proprietario;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProprietarioRepositoryMySQL {

    public void guardar(Proprietario proprietario) {

        String sql = """
                INSERT INTO proprietarios(nome)
                VALUES (?)
                """;

        try (

                Connection conn = DatabaseConnection.getConnection();

                PreparedStatement stmt = conn.prepareStatement(sql)

        ) {

            stmt.setString(1, proprietario.getNome());

            stmt.executeUpdate();

            System.out.println("Proprietário guardado!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}