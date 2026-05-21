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

    // CRIAR BASE DE DADOS
    public static void criarBaseDeDados() {

        try (

                Connection conn = DriverManager.getConnection(URL + "?allowPublicKeyRetrieval=true&useSSL=false", USER, PASSWORD);

                Statement stmt = conn.createStatement()

        ) {

            String sql = "CREATE DATABASE IF NOT EXISTS " + DATABASE;

            stmt.executeUpdate(sql);

            System.out.println("Base de dados criada com sucesso!");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // LIGAÇÃO À BASE DE DADOS
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(

                URL + DATABASE + "?allowPublicKeyRetrieval=true&useSSL=false",

                USER,

                PASSWORD);
    }

    // CRIAR TABELAS
    public static void criarTabelas() {

        // TABELA PROPRIETÁRIOS
        String sqlProprietarios = """
                CREATE TABLE IF NOT EXISTS proprietarios (
                
                    id INT PRIMARY KEY AUTO_INCREMENT,
                
                    nome VARCHAR(100)
                
                )
                """;

        // TABELA VETERINÁRIOS
        String sqlVeterinarios = """
                CREATE TABLE IF NOT EXISTS veterinarios (
                
                    id INT PRIMARY KEY AUTO_INCREMENT,
                
                    nome VARCHAR(100),
                
                    especialidade VARCHAR(100),
                
                    cedula_profissional VARCHAR(100)
                
                )
                """;

        // TABELA ANIMAIS
        String sqlAnimais = """
                CREATE TABLE IF NOT EXISTS animais (
                
                    id INT PRIMARY KEY AUTO_INCREMENT,
                
                    nome VARCHAR(100),
                
                    especie VARCHAR(100),
                
                    raca VARCHAR(100),
                
                    idade INT,
                
                    proprietario_id INT,
                
                    FOREIGN KEY (proprietario_id)
                    REFERENCES proprietarios(id)
                
                )
                """;

        // TABELA CONSULTAS
        String sqlConsultas = """
                CREATE TABLE IF NOT EXISTS consultas (
                
                    id INT PRIMARY KEY AUTO_INCREMENT,
                
                    animal_id INT,
                
                    veterinario_id INT,
                
                    diagnostico VARCHAR(255),
                
                    data DATE,
                
                    FOREIGN KEY (animal_id)
                    REFERENCES animais(id),
                
                    FOREIGN KEY (veterinario_id)
                    REFERENCES veterinarios(id)
                
                )
                """;

        try (

                Connection conn = getConnection();

                Statement stmt = conn.createStatement()

        ) {

            // ORDEM IMPORTANTE

            stmt.execute(sqlProprietarios);

            stmt.execute(sqlVeterinarios);

            stmt.execute(sqlAnimais);

            stmt.execute(sqlConsultas);

            System.out.println("Tabelas criadas com sucesso!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}