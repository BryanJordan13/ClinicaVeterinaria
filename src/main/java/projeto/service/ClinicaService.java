package projeto.service;

import projeto.model.*;
import projeto.repository.AnimalRepositoryMySQL;
import projeto.repository.ProprietarioRepositoryMySQL;
import projeto.repository.VeterinarioRepositoryMySQL;
import projeto.repository.ConsultaRepositoryMySQL;

import java.util.ArrayList;
import java.util.List;

public class ClinicaService {

    private AnimalRepositoryMySQL repository =
            new AnimalRepositoryMySQL();

    private ProprietarioRepositoryMySQL proprietarioRepository =
            new ProprietarioRepositoryMySQL();

    private VeterinarioRepositoryMySQL veterinarioRepository =
            new VeterinarioRepositoryMySQL();

    private ConsultaRepositoryMySQL consultaRepository =
            new ConsultaRepositoryMySQL();


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

        if(animais.isEmpty()) {

            System.out.println("Não existem animais registados.");
            return;
        }

        for(Animal animal : animais) {

            System.out.println(animal);
        }
    }

    public void listarConsultas() {

        if(consultas.isEmpty()) {

            System.out.println("Não existem consultas.");
            return;
        }

        for(Consulta consulta : consultas) {

            System.out.println(consulta);
        }
    }
    public void carregarAnimaisDaBD() {

        animais = repository.buscarTodos();
    }
    public void listarProprietarios() {

        if(proprietarios.isEmpty()) {

            System.out.println(
                    "Não existem proprietários."
            );

            return;
        }

        for(Proprietario p : proprietarios) {

            System.out.println(p);
        }
    }

    public void listarVeterinarios() {

        if(veterinarios.isEmpty()) {

            System.out.println(
                    "Não existem veterinários."
            );

            return;
        }

        for(Veterinario v : veterinarios) {

            System.out.println(v);
        }
    }
}