package projeto.repository;

import projeto.database.DatabaseConnection;
import projeto.model.Animal;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AnimalRepositoryMySQL {

    public void guardarAnimal(Animal animal) {

        String sql = """
                INSERT INTO animais
                (nome, especie, raca, idade)
                VALUES (?, ?, ?, ?)
                """;

        try (

                Connection conn = DatabaseConnection.getConnection();

                PreparedStatement stmt = conn.prepareStatement(sql)

        ) {

            stmt.setString(1, animal.getNome());

            stmt.setString(2, animal.getEspecie());

            stmt.setString(3, animal.getRaca());

            stmt.setInt(4, animal.getIdade());

            stmt.executeUpdate();

            System.out.println("Animal guardado na BD!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}