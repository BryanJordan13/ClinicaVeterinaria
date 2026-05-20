
package projeto.ui;

import projeto.model.*;
import projeto.service.ClinicaService;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {

    private Scanner sc = new Scanner(System.in);
    private ClinicaService service;

    public Menu(ClinicaService service) {
        this.service = service;
    }

    public void iniciar() {

        int op;

        do {

            System.out.println("\n=== CLÍNICA VETERINÁRIA ===");
            System.out.println("1. Registar proprietário");
            System.out.println("2. Registar veterinário");
            System.out.println("3. Registar Animal");
            System.out.println("4. Registar consulta");
            System.out.println("5. Listar animais");
            System.out.println("6. Listar consultas");
            System.out.println("0. Sair");

            System.out.print("Seleciona uma opção: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1 -> registarProprietario();

                case 2 -> registarVeterinario();

                case 3 -> registarAnimal();

                case 4 -> registarConsulta();

                case 5 -> service.listarAnimais();

                case 6 -> service.listarConsultas();

                case 0 -> System.out.println("Programa terminado.");

                default -> System.out.println("Opção inválida.");
            }

        } while (op != 0);
    }

    private void registarProprietario() {

        String nome = lerNomeValido("Nome do proprietário: ");

        Proprietario proprietario = new Proprietario(
                service.proprietarios.size() + 1,
                nome
        );

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

        service.adicionarVeterinario(
                new Veterinario(
                        service.veterinarios.size() + 1,
                        nome,
                        especialidade,
                        cedula
                )
        );

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
        System.out.println("\n--- PROPRIETÁRIOS ---");

        for (int i = 0; i < service.proprietarios.size(); i++) {

            System.out.println(
                    (i + 1) + ". "
                            + service.proprietarios.get(i).getNome()
            );
        }

        System.out.print("Escolha o proprietário: ");

        int escolha = Integer.parseInt(sc.nextLine());

        Proprietario p =
                service.proprietarios.get(escolha - 1);

        Animal animal;

        switch (especieTexto.toLowerCase()) {

            case "cão":
            case "cao":

                animal = new Cao(
                        service.animais.size() + 1,
                        nome,
                        raca,
                        idade,
                        p
                );
                break;

            case "gato":

                animal = new Gato(
                        service.animais.size() + 1,
                        nome,
                        raca,
                        idade,
                        p
                );
                break;

            case "ave":

                animal = new Ave(
                        service.animais.size() + 1,
                        nome,
                        raca,
                        idade,
                        p
                );
                break;

            default:

                animal = new Animal(
                        service.animais.size() + 1,
                        nome,
                        especieTexto,
                        raca,
                        idade,
                        p
                ) {

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

            System.out.println(
                    "Não existem animais registados."
            );

            return;
        }

        if (service.veterinarios.isEmpty()) {

            System.out.println(
                    "Não existem veterinários registados."
            );

            return;
        }

        // LISTAR ANIMAIS
        System.out.println("\n--- ANIMAIS ---");

        for (int i = 0;
             i < service.animais.size();
             i++) {

            System.out.println(
                    (i + 1)
                            + ". "
                            + service.animais.get(i).getNome()
            );
        }

        System.out.print("Escolha o animal: ");

        int escolhaAnimal =
                Integer.parseInt(sc.nextLine());

        if (escolhaAnimal < 1
                || escolhaAnimal > service.animais.size()) {

            System.out.println("Animal inválido.");
            return;
        }

        Animal animal =
                service.animais.get(escolhaAnimal - 1);

        // LISTAR VETERINÁRIOS
        System.out.println("\n--- VETERINÁRIOS ---");

        for (int i = 0;
             i < service.veterinarios.size();
             i++) {

            System.out.println(
                    (i + 1)
                            + ". "
                            + service.veterinarios.get(i).getNome()
            );
        }

        System.out.print("Escolha o veterinário: ");

        int escolhaVeterinario =
                Integer.parseInt(sc.nextLine());

        if (escolhaVeterinario < 1
                || escolhaVeterinario >
                service.veterinarios.size()) {

            System.out.println(
                    "Veterinário inválido."
            );

            return;
        }

        Veterinario veterinario =
                service.veterinarios.get(
                        escolhaVeterinario - 1
                );

        // DIAGNÓSTICO
        String diagnostico;

        while (true) {

            System.out.print("Diagnóstico: ");

            diagnostico =
                    sc.nextLine().trim();

            if (diagnostico.isEmpty()) {

                System.out.println(
                        "Diagnóstico inválido."
                );

                continue;
            }

            break;
        }

        Consulta consulta = new Consulta(
                animal,
                veterinario,
                LocalDate.now(),
                diagnostico
        );

        service.adicionarConsulta(consulta);

        System.out.println(
                "Consulta registada com sucesso."
        );
    }

    private String lerNomeValido(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String nome = sc.nextLine().trim();

            if (nome.isEmpty()) {

                System.out.println(
                        "O nome não pode estar vazio."
                );

                continue;
            }

            if (!nome.matches("[a-zA-Z ]+")) {

                System.out.println(
                        "Nome inválido. Use apenas letras."
                );

                continue;
            }

            return nome;
        }
    }
}