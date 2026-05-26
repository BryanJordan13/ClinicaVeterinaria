package projeto.repository;

import projeto.database.DatabaseConnection;
import projeto.model.Animal;
import projeto.model.Gato;
import projeto.model.Ave;
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

        String sql = """
    SELECT a.*,
           p.nome AS nome_proprietario
    FROM animais a
    JOIN proprietarios p
    ON a.proprietario_id = p.id
    """;

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

                int proprietarioId =
                        rs.getInt("proprietario_id");

                String nomeProprietario =
                        rs.getString("nome_proprietario");

                Proprietario p =
                        new Proprietario(
                                proprietarioId,
                                nomeProprietario
                        );

                Animal animal;

                switch (especie.toLowerCase()) {

                    case "cão":
                    case "cao":

                        animal = new Cao(id, nome, raca, idade, p);

                        break;

                    case "gato":

                        animal = new Gato(id, nome, raca, idade, p);

                        break;

                    case "ave":

                        animal = new Ave(id, nome, raca, idade, p);

                        break;

                    default:

                        animal = new Animal(
                                id,
                                nome,
                                especie,
                                raca,
                                idade,
                                p
                        ) {

                            @Override
                            public String emitirSom() {

                                return "Som desconhecido";
                            }
                        };
                }

                animais.add(animal);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return animais;
    }
}