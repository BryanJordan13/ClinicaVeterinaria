package projeto.model;

import java.time.LocalDate;

public class Consulta {

    private Animal animal;
    private Veterinario veterinario;
    private LocalDate data;
    private String diagnostico;
    private String observacoes;
    private String tratamento;
    private String medicacao;
    private String estadoAnimal;
    private double valorConsulta;
    private double valorMedicacao;
    private double valorExames;

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
                + "\n📅 Data: " + data
                + "\n📋 Diagnóstico: " + diagnostico
                + "\n==============================";
    }
    public String gerarRelatorioClinico() {

        return "\n========== RELATÓRIO =========="
                + "\n🐾 Animal: " + animal.getNome()
                + "\n👨‍⚕️ Veterinário: " + veterinario.getNome()
                + "\n📋 Diagnóstico: " + diagnostico
                + "\n📝 Observações: " + observacoes
                + "\n💊 Tratamento: " + tratamento
                + "\n💉 Medicação: " + medicacao
                + "\n📌 Estado: " + estadoAnimal
                + "\n==============================";
    }
    public String gerarFatura() {

        return "\n=========== FATURA ==========="
                + "\n🐾 Animal: " + animal.getNome()
                + "\n📅 Data: " + data
                + "\n💰 Consulta: " + valorConsulta + "€"
                + "\n💊 Medicação: " + valorMedicacao + "€"
                + "\n🧪 Exames: " + valorExames + "€"
                + "\n------------------------------"
                + "\nTOTAL: " + calcularTotal() + "€"
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
    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public String getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(String medicacao) {
        this.medicacao = medicacao;
    }

    public String getEstadoAnimal() {
        return estadoAnimal;
    }

    public void setEstadoAnimal(String estadoAnimal) {
        this.estadoAnimal = estadoAnimal;
    }
    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    public double getValorMedicacao() {
        return valorMedicacao;
    }

    public void setValorMedicacao(double valorMedicacao) {
        this.valorMedicacao = valorMedicacao;
    }

    public double getValorExames() {
        return valorExames;
    }

    public void setValorExames(double valorExames) {
        this.valorExames = valorExames;
    }
    public double calcularTotal() {

        return valorConsulta
                + valorMedicacao
                + valorExames;
    }
}