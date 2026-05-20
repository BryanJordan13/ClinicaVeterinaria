
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

            if (idade < 0) {
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

        Proprietario proprietario = service.proprietarios.get(0);

        Animal animal;

        switch (especieTexto.toLowerCase()) {

            case "cão":
            case "cao":

                animal = new Cao(
                        service.animais.size() + 1,
                        nome,
                        raca,
                        idade,
                        proprietario
                );
                break;

            case "gato":

                animal = new Gato(
                        service.animais.size() + 1,
                        nome,
                        raca,
                        idade,
                        proprietario
                );
                break;

            case "ave":

                animal = new Ave(
                        service.animais.size() + 1,
                        nome,
                        raca,
                        idade,
                        proprietario
                );
                break;

            default:

                animal = new Animal(
                        service.animais.size() + 1,
                        nome,
                        especieTexto,
                        raca,
                        idade,
                        proprietario
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

        if (service.animais.isEmpty() || service.veterinarios.isEmpty()) {

            System.out.println("Necessita animais e veterinários.");
            return;
        }

        Consulta consulta = new Consulta(
                service.animais.get(0),
                service.veterinarios.get(0),
                LocalDate.now(),
                "Consulta geral"
        );

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

            // aceita apenas letras e espaços
            if (!nome.matches("[a-zA-Z ]+")) {

                System.out.println("Nome inválido. Use apenas letras.");
                continue;
            }

            return nome;
        }
    }
}