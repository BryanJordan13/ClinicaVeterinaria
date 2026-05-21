package projeto.repository;

import projeto.database.DatabaseConnection;
import projeto.model.Animal;

import projeto.model.Cao;
import projeto.model.Proprietario;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AnimalRepositoryMySQL {

    public void guardarAnimal(Animal animal) {

        String sql = """
                INSERT INTO animais
                (nome, especie, raca, idade, proprietario_id)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (

                Connection conn = DatabaseConnection.getConnection();

                PreparedStatement stmt = conn.prepareStatement(sql)

        ) {

            stmt.setString(1, animal.getNome());

            stmt.setString(2, animal.getEspecie());

            stmt.setString(3, animal.getRaca());

            stmt.setInt(4, animal.getIdade());

            stmt.setInt(5, animal.getProprietario().getId());

            stmt.executeUpdate();

            System.out.println("Animal guardado na BD!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public List<Animal> buscarTodos() {

        List<Animal> animais = new ArrayList<>();

        String sql = "SELECT * FROM animais";

        try (

                Connection conn = DatabaseConnection.getConnection();

                PreparedStatement stmt = conn.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery()

        ) {

            while (rs.next()) {

                int id = rs.getInt("id");

                String nome = rs.getString("nome");

                String especie = rs.getString("especie");

                String raca = rs.getString("raca");

                int idade = rs.getInt("idade");

                Proprietario p = new Proprietario(0, "Desconhecido");

                Animal animal = new Cao(id, nome, raca, idade, p);

                animais.add(animal);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return animais;
    }
}