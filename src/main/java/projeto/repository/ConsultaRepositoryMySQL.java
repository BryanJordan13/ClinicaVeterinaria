package projeto.repository;

import projeto.database.DatabaseConnection;
import projeto.model.Animal;
import projeto.model.Consulta;
import projeto.model.Veterinario;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaRepositoryMySQL {

    public void guardar(Consulta consulta) {

        String sql = """
                INSERT INTO consultas
                (
                 animal_id,
                 veterinario_id,
                 diagnostico,
                 data,
                 emitir_fatura,
                 contribuinte,
                 observacoes,
                 tratamento,
                 medicacao,
                 estado_animal
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, consulta.getAnimal().getId());
            stmt.setInt(2, consulta.getVeterinario().getId());
            stmt.setString(3, consulta.getDescricao());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(consulta.getDataHora()));
            stmt.setBoolean(5, consulta.isEmitirFatura());
            stmt.setString(6, consulta.getContribuinte());
            stmt.setString(7, consulta.getObservacoes());
            stmt.setString(8, consulta.getTratamento());
            stmt.setString(9, consulta.getMedicacao());
            stmt.setString(10, consulta.getEstadoAnimal());
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
                LocalDateTime dataHora = rs.getTimestamp("data_hora").toLocalDateTime();

                boolean emitirFatura = rs.getBoolean("emitir_fatura");
                String contribuinte = rs.getString("contribuinte");

                Animal animal = null;
                Veterinario veterinario = null;

                String observacoes = rs.getString("observacoes");

                String tratamento = rs.getString("tratamento");

                String medicacao = rs.getString("medicacao");

                String estadoAnimal = rs.getString("estado_animal");


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

                    Consulta consulta = new Consulta(
                            animal,
                            veterinario,
                            dataHora,
                            diagnostico,
                            emitirFatura,
                            contribuinte
                    );

                    consultas.add(consulta);
                    consulta.setObservacoes(observacoes);
                    consulta.setTratamento(tratamento);
                    consulta.setMedicacao(medicacao);
                    consulta.setEstadoAnimal(estadoAnimal);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return consultas;
    }

}