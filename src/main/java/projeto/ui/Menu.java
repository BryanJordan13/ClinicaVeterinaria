package projeto.ui;

import projeto.model.*;
import projeto.service.ClinicaService;

import java.io.Console;
import java.time.LocalDate;
import java.util.Scanner;

public class Menu {

    private Scanner sc = new Scanner(System.in);
    private ClinicaService service;
    private final String ADMIN_USER = "admin";
    private final String ADMIN_PASSWORD = "1234";
    private final String VET_USER = "vet";
    private final String VET_PASSWORD = "vet123";

    public Menu(ClinicaService service) {
        this.service = service;
    }

    public void iniciar() {

        int op;

        do {

            System.out.println("1. Menu Admin");

            System.out.println("2. Gestão de Animais");

            System.out.println("3. Gestão de Proprietários");

            System.out.println("4. Gestão de Veterinários");

            System.out.println("5. Gestão de Consultas");

            System.out.println("0. Sair");

            System.out.print("Seleciona uma opção: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1 -> menuAdmin();

                case 2 -> menuAnimais();

                case 3 -> menuProprietarios();

                case 4 -> menuVeterinarios();

                case 5 -> menuConsultas();

                case 0 -> System.out.println("Programa terminado.");

                default -> System.out.println("Opção inválida.");
            }

        } while (op != 0);
    }

    private void registarProprietario() {

        String nome = lerNomeValido("Nome do proprietário: ");

        Proprietario proprietario = new Proprietario(service.proprietarios.size() + 1, nome);

        service.adicionarProprietario(proprietario);

        System.out.println("Proprietário registado com sucesso.");

        // pergunta se deseja registar animal
        System.out.print("Deseja registar um animal agora? (s/n): ");

        String resposta = sc.nextLine().trim().toLowerCase();

        if (resposta.equals("s") || resposta.equals("sim")) {

            registarAnimal();
        }
    }

    private void registarVeterinario() {

        String nome = lerNomeValido("Nome do veterinário: ");

        System.out.print("Especialidade: ");
        String especialidade = sc.nextLine();

        System.out.print("Número da cédula profissional: ");
        String cedula = sc.nextLine();

        service.adicionarVeterinario(new Veterinario(service.veterinarios.size() + 1, nome, especialidade, cedula));

        System.out.println("Veterinário registado com sucesso.");
    }

    private void registarAnimal() {

        if (service.proprietarios.isEmpty()) {

            System.out.println("Sem proprietários registados.");
            return;
        }

        String nome = lerNomeValido("Nome do animal: ");

        System.out.println("Espécie:");
        System.out.println("1. Cão");
        System.out.println("2. Gato");
        System.out.println("3. Ave");
        System.out.println("4. Outro");

        System.out.print("Escolha: ");
        int especie = Integer.parseInt(sc.nextLine());

        String especieTexto;

        switch (especie) {

            case 1 -> especieTexto = "Cão";

            case 2 -> especieTexto = "Gato";

            case 3 -> especieTexto = "Ave";

            case 4 -> {
                System.out.print("Indique a espécie: ");
                especieTexto = sc.nextLine();
            }

            default -> {
                System.out.println("Espécie inválida.");
                return;
            }
        }

// --- RAÇA ---
        String raca;
        while (true) {
            System.out.print("Raça: ");
            raca = sc.nextLine().trim();

            if (!raca.matches("[a-zA-Z ]+")) {
                System.out.println("Raça inválida. Use apenas letras.");
                continue;
            }
            break;
        }

// --- IDADE ---
        int idade;
        while (true) {
            System.out.print("Idade: ");
            String idadeStr = sc.nextLine().trim();

            if (!idadeStr.matches("\\d+")) {
                System.out.println("Idade inválida. Insira apenas números inteiros.");
                continue;
            }

            idade = Integer.parseInt(idadeStr);

            if (idade <= 0) {
                System.out.println("A idade não pode ser negativa.");
                continue;
            }

            break;
        }

// --- COR ---
        String cor;
        while (true) {
            System.out.print("Cor: ");
            cor = sc.nextLine().trim();

            if (!cor.matches("[a-zA-Z ]+")) {
                System.out.println("Cor inválida. Use apenas letras.");
                continue;
            }
            break;
        }

// --- TIPO DE PELAGEM ---
        String pelagem;
        while (true) {
            System.out.print("Tipo de pelagem: ");
            pelagem = sc.nextLine().trim();

            if (!pelagem.matches("[a-zA-Z ]+")) {
                System.out.println("Pelagem inválida. Use apenas letras.");
                continue;
            }
            break;
        }
// ---
        Proprietario p;

        System.out.println("\n=== PROPRIETÁRIOS ===");

        System.out.println("1. Escolher proprietário existente");

        System.out.println("2. Criar novo proprietário");

        System.out.print("Opção: ");

        int opcaoProprietario = Integer.parseInt(sc.nextLine());

        if (opcaoProprietario == 1) {

            if (service.proprietarios.isEmpty()) {

                System.out.println("Não existem proprietários registados.");

                return;
            }

            System.out.println("\n--- PROPRIETÁRIOS ---");

            for (int i = 0; i < service.proprietarios.size(); i++) {

                System.out.println((i + 1) + ". " + service.proprietarios.get(i).getNome());
            }

            System.out.print("Escolha o proprietário: ");

            int escolha = Integer.parseInt(sc.nextLine());

            p = service.proprietarios.get(escolha - 1);

        } else if (opcaoProprietario == 2) {

            String nomeProprietario = lerNomeValido("Nome do proprietário: ");

            p = new Proprietario(

                    service.proprietarios.size() + 1,

                    nomeProprietario);

            service.adicionarProprietario(p);

            System.out.println("Proprietário criado com sucesso!");

        } else {

            System.out.println("Opção inválida.");

            return;
        }

        Animal animal;

        switch (especieTexto.toLowerCase()) {

            case "cão":
            case "cao":

                animal = new Cao(service.animais.size() + 1, nome, raca, idade, p);
                break;

            case "gato":

                animal = new Gato(service.animais.size() + 1, nome, raca, idade, p);
                break;

            case "ave":

                animal = new Ave(service.animais.size() + 1, nome, raca, idade, p);
                break;

            default:

                animal = new Animal(service.animais.size() + 1, nome, especieTexto, raca, idade, p) {

                    @Override
                    public String emitirSom() {

                        return "Som desconhecido";
                    }
                };
        }


        service.adicionarAnimal(animal);

        System.out.println("Animal registado com sucesso.");
    }

    private void registarConsulta() {

        if (service.animais.isEmpty()) {

            System.out.println("Não existem animais registados.");

            return;
        }

        if (service.veterinarios.isEmpty()) {

            System.out.println("Não existem veterinários registados.");

            return;
        }

        // LISTAR ANIMAIS
        System.out.println("\n--- ANIMAIS ---");

        for (int i = 0; i < service.animais.size(); i++) {

            System.out.println((i + 1) + ". " + service.animais.get(i).getNome());
        }

        System.out.print("Escolha o animal: ");

        int escolhaAnimal = Integer.parseInt(sc.nextLine());

        if (escolhaAnimal < 1 || escolhaAnimal > service.animais.size()) {

            System.out.println("Animal inválido.");
            return;
        }

        Animal animal = service.animais.get(escolhaAnimal - 1);

        // LISTAR VETERINÁRIOS
        System.out.println("\n--- VETERINÁRIOS ---");

        for (int i = 0; i < service.veterinarios.size(); i++) {

            System.out.println((i + 1) + ". " + service.veterinarios.get(i).getNome());
        }

        System.out.print("Escolha o veterinário: ");

        int escolhaVeterinario = Integer.parseInt(sc.nextLine());

        if (escolhaVeterinario < 1 || escolhaVeterinario > service.veterinarios.size()) {

            System.out.println("Veterinário inválido.");

            return;
        }

        Veterinario veterinario = service.veterinarios.get(escolhaVeterinario - 1);

        // DIAGNÓSTICO
        String diagnostico;

        while (true) {

            System.out.print("Diagnóstico: ");

            diagnostico = sc.nextLine().trim();

            if (diagnostico.isEmpty()) {

                System.out.println("Diagnóstico inválido.");

                continue;
            }

            break;
        }

        Consulta consulta = new Consulta(animal, veterinario, LocalDate.now(), diagnostico);

        service.adicionarConsulta(consulta);

        System.out.println("Consulta registada com sucesso.");
    }

    private String lerNomeValido(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String nome = sc.nextLine().trim();

            if (nome.isEmpty()) {

                System.out.println("O nome não pode estar vazio.");

                continue;
            }

            if (!nome.matches("[a-zA-Z ]+")) {

                System.out.println("Nome inválido. Use apenas letras.");

                continue;
            }

            return nome;
        }
    }

    private void menuAnimais() {

        int op;

        do {

            System.out.println("\n=== GESTÃO DE ANIMAIS ===");

            System.out.println("1. Registar animal");

            System.out.println("2. Listar animais");

            System.out.println("0. Voltar");

            System.out.print("Opção: ");

            op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1 -> registarAnimal();

                case 2 -> service.listarAnimais();

                case 0 -> {
                }

                default -> System.out.println("Opção inválida.");
            }

        } while (op != 0);
    }

    private void menuProprietarios() {

        int op;

        do {

            System.out.println("\n=== GESTÃO DE PROPRIETÁRIOS ===");

            System.out.println("1. Registar proprietário");

            System.out.println("2. Listar proprietários");

            System.out.println("0. Voltar");

            System.out.print("Opção: ");

            op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1 -> registarProprietario();

                case 2 -> service.listarProprietarios();

                case 0 -> {
                }

                default -> System.out.println("Opção inválida.");
            }

        } while (op != 0);
    }

    private void menuVeterinarios() {

        int op;

        do {

            System.out.println("\n=== GESTÃO DE VETERINÁRIOS ===");

            System.out.println("1. Registar veterinário");

            System.out.println("2. Listar veterinários");

            System.out.println("0. Voltar");

            System.out.print("Opção: ");

            op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1 -> registarVeterinario();

                case 2 -> service.listarVeterinarios();

                case 0 -> {
                }

                default -> System.out.println("Opção inválida.");
            }

        } while (op != 0);
    }

    private void menuConsultas() {

        int op;

        do {

            System.out.println("\n=== GESTÃO DE CONSULTAS ===");

            System.out.println("1. Registar consulta");

            System.out.println("2. Listar consultas");

            System.out.println("3. Histórico clínico");

            System.out.println("0. Voltar");

            System.out.print("Opção: ");

            op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1 -> registarConsulta();

                case 2 -> service.listarConsultas();

                case 3 -> mostrarHistorico();

                case 0 -> {
                }

                default -> System.out.println("Opção inválida.");
            }

        } while (op != 0);
    }

    private void mostrarHistorico() {

        if (service.animais.isEmpty()) {

            System.out.println("Não existem animais.");

            return;
        }

        System.out.println("\n--- ANIMAIS ---");

        for (int i = 0; i < service.animais.size(); i++) {

            System.out.println((i + 1) + ". " + service.animais.get(i).getNome());
        }

        System.out.print("Escolha o animal: ");

        int escolha = Integer.parseInt(sc.nextLine());

        if (escolha < 1 || escolha > service.animais.size()) {

            System.out.println("Animal inválido.");

            return;
        }

        Animal animal = service.animais.get(escolha - 1);

        System.out.println("\n=== HISTÓRICO CLÍNICO ===");

        service.mostrarHistoricoAnimal(animal);
    }

    private void menuEstatisticas() {

        System.out.println("\n=== ESTATÍSTICAS ===");

        System.out.println("Total de animais: " + service.animais.size());

        System.out.println("Total de proprietários: " + service.proprietarios.size());

        System.out.println("Total de veterinários: " + service.veterinarios.size());

        System.out.println("Total de consultas: " + service.consultas.size());

        System.out.println("\nEspécie mais comum: " + service.especieMaisComum());

        System.out.println("Veterinário com mais consultas: " + service.veterinarioMaisConsultas());
    }

    private void menuAdmin() {

        // LOGIN
        if (!menuLogin()) {

            return;
        }

        int op;

        do {

            System.out.println("\n=== MENU ADMIN ===");

            System.out.println("1. Relatórios");

            System.out.println("2. Estatísticas");

            System.out.println("0. Voltar");

            System.out.print("Opção: ");

            op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1 -> menuRelatorios();

                case 2 -> menuEstatisticas();

                case 0 -> {
                }

                default -> System.out.println("Opção inválida.");
            }

        } while (op != 0);
    }



    private void menuRelatorios() {

        int op;

        do {

            System.out.println("\n=== RELATÓRIOS ===");

            System.out.println("1. Listar animais");

            System.out.println("2. Listar consultas");

            System.out.println("3. Histórico clínico");

            System.out.println("0. Voltar");

            System.out.print("Opção: ");

            op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1 -> service.listarAnimais();

                case 2 -> service.listarConsultas();

                case 3 -> mostrarHistorico();

                case 0 -> {
                }

                default -> System.out.println("Opção inválida.");
            }

        } while (op != 0);
    }


    private boolean loginSistema(String utilizadorCorreto, String passwordCorreta) {

        int tentativas = 3;

        while (tentativas > 0) {

            System.out.print("Username: ");

            String user = sc.nextLine();

            String password;

            Console console = System.console();

            if (console != null) {

                char[] passwordChars = console.readPassword("Password: ");

                password = new String(passwordChars);

            } else {

                System.out.print("Password: ");

                password = sc.nextLine();
            }

            if (user.equals(utilizadorCorreto) && password.equals(passwordCorreta)) {

                System.out.println("Login efetuado com sucesso!");

                return true;
            }

            tentativas--;

            System.out.println("Credenciais inválidas!");

            if (tentativas > 0) {

                System.out.println("Tentativas restantes: " + tentativas);
            }
        }

        System.out.println("Acesso bloqueado!");

        return false;
    }

    private boolean menuLogin() {

        int op;

        System.out.println("\n=== LOGIN ===");

        System.out.println("1. Admin");

        System.out.println("2. Veterinário");

        System.out.println("0. Voltar");

        System.out.print("Opção: ");

        op = Integer.parseInt(sc.nextLine());

        switch (op) {

            case 1:

                return loginSistema(ADMIN_USER, ADMIN_PASSWORD);

            case 2:

                return loginSistema(VET_USER, VET_PASSWORD);

            case 0:

                return false;

            default:

                System.out.println("Opção inválida.");

                return false;
        }
    }
}