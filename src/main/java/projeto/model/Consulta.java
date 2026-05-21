package projeto.model;

import java.time.LocalDate;

public class Consulta {

    private Animal animal;
    private Veterinario veterinario;
    private LocalDate data;
    private String diagnostico;

    public Consulta(
            Animal animal,
            Veterinario veterinario,
            LocalDate data,
            String diagnostico
    ) {

        this.animal = animal;
        this.veterinario = veterinario;
        this.data = data;
        this.diagnostico = diagnostico;
    }

    @Override
    public String toString() {

        return "\n=============================="
                + "\n🐾 Animal: " + animal.getNome()
                + "\n👨‍⚕️ Veterinário: " + veterinario.getNome()
                + "\n🩺 Especialidade: " + veterinario.getEspecialidade()
                + "\n🪪 Cédula: " + veterinario.getCedulaProfissional()
                + "\n📅 Data: " + data
                + "\n📋 Diagnóstico: " + diagnostico
                + "\n==============================";
    }

    public String getDescricao() {
        return diagnostico;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public LocalDate getData() {
        return data;
    }
}