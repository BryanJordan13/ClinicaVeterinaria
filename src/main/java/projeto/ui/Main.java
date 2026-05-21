package projeto.ui;

import projeto.database.DatabaseConnection;
import projeto.model.*;
import projeto.service.ClinicaService;

public class Main {

    public static void main(String[] args) {

        // CRIAR BASE DE DADOS
        DatabaseConnection.criarBaseDeDados();

        // CRIAR TABELAS
        DatabaseConnection.criarTabelas();

        ClinicaService service = new ClinicaService();

        // CARREGAR ANIMAIS DA BD
        service.carregarAnimaisDaBD();

        // PROPRIETÁRIOS
        Proprietario p1 = new Proprietario(1, "João Silva");

        Proprietario p2 = new Proprietario(2, "Maria Costa");

        service.adicionarProprietario(p1);
        service.adicionarProprietario(p2);

        // VETERINÁRIOS
        Veterinario v1 = new Veterinario(1, "Ana Ferreira", "Cirurgia", "CED123");

        Veterinario v2 = new Veterinario(2, "Carlos Sousa", "Dermatologia", "CED456");

        service.adicionarVeterinario(v1);
        service.adicionarVeterinario(v2);

        // ANIMAIS
        Animal a1 = new Cao(1, "Rex", "Labrador", 3, p1);

        Animal a2 = new Gato(2, "Mimi", "Persa", 2, p2);

        service.adicionarAnimal(a1);
        service.adicionarAnimal(a2);

        // MENU
        Menu menu = new Menu(service);

        menu.iniciar();
    }
}