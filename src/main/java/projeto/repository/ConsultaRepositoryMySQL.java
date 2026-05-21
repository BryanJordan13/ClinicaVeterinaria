package projeto.repository;

import projeto.database.DatabaseConnection;
import projeto.model.Animal;
import projeto.model.Consulta;
import projeto.model.Veterinario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

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

            stmt.setInt(1, consulta.getAnimal().getId());

            stmt.setInt(2, consulta.getVeterinario().getId());

            stmt.setString(3, consulta.getDescricao());

            stmt.setDate(4, java.sql.Date.valueOf(consulta.getData()));

            stmt.executeUpdate();

            System.out.println("Consulta guardada!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public List<Consulta> buscarTodos(List<Animal> animais, List<Veterinario> veterinarios) {

        List<Consulta> consultas = new ArrayList<>();

        String sql = "SELECT * FROM consultas";

        try (

                Connection conn = DatabaseConnection.getConnection();

                PreparedStatement stmt = conn.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery()

        ) {

            while (rs.next()) {

                int animalId = rs.getInt("animal_id");

                int veterinarioId = rs.getInt("veterinario_id");

                String diagnostico = rs.getString("diagnostico");

                LocalDate data = rs.getDate("data").toLocalDate();

                Animal animal = null;

                Veterinario veterinario = null;

                // PROCURAR ANIMAL
                for (Animal a : animais) {

                    if (a.getId() == animalId) {

                        animal = a;
                        break;
                    }
                }

                // PROCURAR VETERINÁRIO
                for (Veterinario v : veterinarios) {

                    if (v.getId() == veterinarioId) {

                        veterinario = v;
                        break;
                    }
                }

                // CRIAR CONSULTA
                if (animal != null && veterinario != null) {

                    Consulta consulta = new Consulta(animal, veterinario, data, diagnostico);

                    consultas.add(consulta);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return consultas;
    }
}