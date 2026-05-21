package projeto.repository;

import projeto.database.DatabaseConnection;
import projeto.model.Proprietario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

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

    public List<Proprietario> buscarTodos() {

        List<Proprietario> proprietarios = new ArrayList<>();

        String sql = "SELECT * FROM proprietarios";

        try (

                Connection conn = DatabaseConnection.getConnection();

                PreparedStatement stmt = conn.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery()

        ) {

            while (rs.next()) {

                Proprietario p = new Proprietario(

                        rs.getInt("id"),

                        rs.getString("nome"));

                proprietarios.add(p);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return proprietarios;
    }
}