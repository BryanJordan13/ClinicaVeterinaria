package projeto.service;

import projeto.model.*;
import projeto.repository.AnimalRepositoryMySQL;
import projeto.repository.ProprietarioRepositoryMySQL;
import projeto.repository.VeterinarioRepositoryMySQL;
import projeto.repository.ConsultaRepositoryMySQL;

import java.util.ArrayList;
import java.util.List;


public class ClinicaService {

    private AnimalRepositoryMySQL repository = new AnimalRepositoryMySQL();

    private ProprietarioRepositoryMySQL proprietarioRepository = new ProprietarioRepositoryMySQL();

    private VeterinarioRepositoryMySQL veterinarioRepository = new VeterinarioRepositoryMySQL();

    private ConsultaRepositoryMySQL consultaRepository = new ConsultaRepositoryMySQL();


    public List<Animal> animais = new ArrayList<>();
    public List<Proprietario> proprietarios = new ArrayList<>();
    public List<Veterinario> veterinarios = new ArrayList<>();
    public List<Consulta> consultas = new ArrayList<>();


    public void adicionarAnimal(Animal animal) {

        animais.add(animal);

        repository.guardarAnimal(animal);
    }

    public void adicionarVeterinario(Veterinario veterinario) {

        veterinarios.add(veterinario);

        veterinarioRepository.guardar(veterinario);
    }

    public void adicionarProprietario(Proprietario proprietario) {

        proprietarios.add(proprietario);

        proprietarioRepository.guardar(proprietario);
    }

    public void adicionarConsulta(Consulta consulta) {

        consultas.add(consulta);

        consultaRepository.guardar(consulta);
    }

    public void listarAnimais() {

        if (animais.isEmpty()) {

            System.out.println("Não existem animais registados.");
            return;
        }

        for (Animal animal : animais) {

            System.out.println(animal);
        }
    }

    public void listarConsultas() {

        if (consultas.isEmpty()) {

            System.out.println("Não existem consultas.");
            return;
        }

        int contador = 1;

        for (Consulta consulta : consultas) {

            System.out.println("\nCONSULTA #" + contador++);

            System.out.println(consulta);
        }
    }

    public void carregarAnimaisDaBD() {

        animais = repository.buscarTodos();
    }

    public void listarProprietarios() {

        if (proprietarios.isEmpty()) {

            System.out.println("Não existem proprietários.");

            return;
        }

        for (Proprietario p : proprietarios) {

            System.out.println(p);
        }
    }

    public void carregarProprietariosDaBD() {

        proprietarios = proprietarioRepository.buscarTodos();
    }


    public void listarVeterinarios() {

        if (veterinarios.isEmpty()) {

            System.out.println("Não existem veterinários.");

            return;
        }

        for (Veterinario v : veterinarios) {

            System.out.println(v);
        }
    }

    public void carregarVeterinariosDaBD() {

        veterinarios = veterinarioRepository.buscarTodos();
    }

    public void carregarConsultasDaBD() {

        consultas = consultaRepository.buscarTodos(animais, veterinarios);
    }

    public void mostrarHistoricoAnimal(Animal animal) {

        boolean encontrou = false;

        for (Consulta consulta : consultas) {

            if (consulta.getAnimal().getId() == animal.getId()) {

                System.out.println(consulta);

                encontrou = true;
            }
        }

        if (!encontrou) {

            System.out.println("Este animal não possui consultas.");
        }
    }

    public String especieMaisComum() {

        if (animais.isEmpty()) {

            return "Sem dados";
        }

        int caes = 0;

        int gatos = 0;

        int aves = 0;

        for (Animal animal : animais) {

            switch (animal.getEspecie().toLowerCase()) {

                case "cão":
                case "cao":

                    caes++;
                    break;

                case "gato":

                    gatos++;
                    break;

                case "ave":

                    aves++;
                    break;
            }
        }

        if (caes >= gatos && caes >= aves) {

            return "Cão";
        }

        if (gatos >= caes && gatos >= aves) {

            return "Gato";
        }

        return "Ave";
    }

    public String veterinarioMaisConsultas() {

        if (consultas.isEmpty()) {

            return "Sem consultas";
        }

        Veterinario melhor = null;

        int max = 0;

        for (Veterinario v : veterinarios) {

            int contador = 0;

            for (Consulta c : consultas) {

                if (c.getVeterinario().getId() == v.getId()) {

                    contador++;
                }
            }

            if (contador > max) {

                max = contador;

                melhor = v;
            }
        }

        if (melhor == null) {

            return "Sem dados";
        }

        return melhor.getNome() + " (" + max + " consultas)";
    }

    public double calcularFaturacaoTotal() {

        double total = 0;

        for (Consulta c : consultas) {

            total += c.calcularTotal();
        }

        return total;
    }
    public void mostrarDashboard() {

        System.out.println("\n========== DASHBOARD ==========");

        System.out.println("🐾 Total Animais: "
                + animais.size());

        System.out.println("👤 Total Proprietários: "
                + proprietarios.size());

        System.out.println("📋 Total Consultas: "
                + consultas.size());

        System.out.println("👨‍⚕️ Total Veterinários: "
                + veterinarios.size());

        System.out.println("💰 Faturação Total: "
                + calcularFaturacaoTotal() + "€");

        System.out.println("🐶 Espécie mais comum: "
                + especieMaisComum());

        System.out.println("🏆 Veterinário com mais consultas: "
                + veterinarioMaisConsultas());

        System.out.println("================================");
    }
}
