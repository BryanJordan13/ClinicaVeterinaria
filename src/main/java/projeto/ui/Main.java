package projeto.ui;

import projeto.database.DatabaseConnection;
import projeto.service.ClinicaService;

public class Main {

    public static void main(String[] args) {


        // CRIAR TABELAS
        DatabaseConnection.criarTabelas();

        ClinicaService service = new ClinicaService();

        // CARREGAR DADOS DA BD
        service.carregarAnimaisDaBD();

        service.carregarProprietariosDaBD();

        service.carregarVeterinariosDaBD();

        service.carregarConsultasDaBD();

        // MENU
        Menu menu = new Menu(service);

        menu.iniciar();
    }
}