package projeto.repository;

import projeto.database.DatabaseConnection;
import projeto.model.Veterinario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class VeterinarioRepositoryMySQL {

    public void guardar(Veterinario veterinario) {

        String sql = """
                INSERT INTO veterinarios
                (nome, especialidade)
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

    public List<Veterinario> buscarTodos() {

        List<Veterinario> veterinarios = new ArrayList<>();

        String sql = "SELECT * FROM veterinarios";

        try (

                Connection conn = DatabaseConnection.getConnection();

                PreparedStatement stmt = conn.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery()

        ) {

            while (rs.next()) {

                Veterinario v = new Veterinario(

                        rs.getInt("id"),

                        rs.getString("nome"),

                        rs.getString("especialidade"),

                        "N/A");

                veterinarios.add(v);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return veterinarios;
    }
}