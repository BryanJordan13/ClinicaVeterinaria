package projeto.ui;

import projeto.database.DatabaseConnection;
import projeto.service.ClinicaService;

public class Main {

    public static void main(String[] args) {

        // CRIAR BASE DE DADOS
        DatabaseConnection.criarBaseDeDados();

        // CRIAR TABELAS
        DatabaseConnection.criarTabelas();

        ClinicaService service = new ClinicaService();

        Menu menu = new Menu(service);

        menu.iniciar();
    }
}