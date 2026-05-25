package projeto.database;

import java.sql.Connection;

public class TesteDBeaver {

    public static void main(String[] args) {


        // CRIAR TABELAS
        DatabaseConnection.criarTabelas();

        try (

                Connection conn = DatabaseConnection.getConnection()

        ) {

            System.out.println("Ligação com sucesso!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}