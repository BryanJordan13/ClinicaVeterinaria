package projeto.service;

import projeto.model.*;
import projeto.repository.AnimalCsvRepository;

import java.util.ArrayList;
import java.util.List;

public class ClinicaService {

    private AnimalCsvRepository repository =
            new AnimalCsvRepository();

    public List<Animal> animais = new ArrayList<>();
    public List<Proprietario> proprietarios = new ArrayList<>();
    public List<Veterinario> veterinarios = new ArrayList<>();
    public List<Consulta> consultas = new ArrayList<>();

    public void adicionarAnimal(Animal animal) {

        animais.add(animal);

        repository.guardar(animais);
    }

    public void adicionarVeterinario(Veterinario veterinario) {

        veterinarios.add(veterinario);
    }

    public void adicionarProprietario(Proprietario proprietario) {

        proprietarios.add(proprietario);
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