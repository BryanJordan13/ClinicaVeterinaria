package projeto.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    // SERVIDOR MYSQL
    private static final String URL = "jdbc:mysql://localhost:3306/";

    // NOME DA BASE DE DADOS
    private static final String DATABASE = "clinica_veterinaria";

    // UTILIZADOR MYSQL
    private static final String USER = "root";

    // PASSWORD MYSQL
    private static final String PASSWORD = "Password1!";

    // CRIAR BASE DE DADOS AUTOMATICAMENTE
    public static void criarBaseDeDados() {

        try (

                Connection conn = DriverManager.getConnection(URL + "?allowPublicKeyRetrieval=true" + "&useSSL=false", USER, PASSWORD);

                Statement stmt = conn.createStatement()

        ) {

            String sql = "CREATE DATABASE IF NOT EXISTS " + DATABASE;

            stmt.executeUpdate(sql);

            System.out.println("Base de dados criada com sucesso.");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // LIGAÇÃO À BASE DE DADOS
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL + DATABASE + "?allowPublicKeyRetrieval=true" + "&useSSL=false", USER, PASSWORD);
    }

    public static void criarTabelas() {

        String sqlAnimais = """
                CREATE TABLE IF NOT EXISTS animais (
                
                    id INT PRIMARY KEY AUTO_INCREMENT,
                
                    nome VARCHAR(100),
                
                    especie VARCHAR(100),
                
                    raca VARCHAR(100),
                
                    idade INT
                
                )
                """;

        try (

                Connection conn = getConnection();

                Statement stmt = conn.createStatement()

        ) {

            stmt.execute(sqlAnimais);

            System.out.println("Tabela animais criada!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}