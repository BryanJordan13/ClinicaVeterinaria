package projeto.service;

import projeto.model.*;
import projeto.repository.AnimalRepositoryMySQL;
import projeto.repository.ProprietarioRepositoryMySQL;
import projeto.repository.VeterinarioRepositoryMySQL;

import java.util.ArrayList;
import java.util.List;

public class ClinicaService {

    private AnimalRepositoryMySQL repository =
            new AnimalRepositoryMySQL();

    private ProprietarioRepositoryMySQL proprietarioRepository =
            new ProprietarioRepositoryMySQL();

    private VeterinarioRepositoryMySQL veterinarioRepository =
            new VeterinarioRepositoryMySQL();

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
}