package projeto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    // SERVIDOR MYSQL
    private static final String URL =
            "jdbc:mysql://zephyr.proxy.rlwy.net:52428/railway?allowPublicKeyRetrieval=true&useSSL=false";

    // UTILIZADOR MYSQL
    private static final String USER = "root";

    // PASSWORD MYSQL
    private static final String PASSWORD = "lKDepECjJrRrtIFwytmHvRmjhwRIkGWA";

    // CRIAR BASE DE DADOS

    // LIGAÇÃO À BASE DE DADOS
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
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