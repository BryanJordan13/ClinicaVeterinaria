package projeto.model;

import java.time.LocalDate;

public class Consulta {

    private Animal animal;
    private Veterinario veterinario;
    private LocalDate data;
    private String diagnostico;

    private boolean emitirFatura;   // NOVO
    private String contribuinte;    // NOVO

    public Consulta(
            Animal animal,
            Veterinario veterinario,
            LocalDate data,
            String diagnostico,
            boolean emitirFatura,
            String contribuinte
    ) {

        this.animal = animal;
        this.veterinario = veterinario;
        this.data = data;
        this.diagnostico = diagnostico;
        this.emitirFatura = emitirFatura;
        this.contribuinte = contribuinte;
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
                + "\n💶 Emitir Fatura: " + (emitirFatura ? "Sim" : "Não")
                + (emitirFatura ? "\n🔢 Contribuinte: " + contribuinte : "")
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

    public boolean isEmitirFatura() {
        return emitirFatura;
    }

    public String getContribuinte() {
        return contribuinte;
    }
}