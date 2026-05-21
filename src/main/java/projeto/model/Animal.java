
package projeto.model;

public abstract class Animal {
    protected int id;
    protected String nome;
    protected String especie;
    protected String raca;
    protected int idade;
    protected Proprietario proprietario;

    public Animal(int id, String nome, String especie, String raca, int idade, Proprietario proprietario) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.proprietario = proprietario;
    }

    public int getId() { return id; }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public int getIdade() {
        return idade;
    }
    public Proprietario getProprietario() { return proprietario; }

    public abstract String emitirSom();


    @Override
    public String toString() {
        return "ID: " + id +
                " | Nome: " + nome +
                " | Espécie: " + especie +
                " | Raça: " + raca +
                " | Idade: " + idade +
                " | Proprietário: " + proprietario.getNome() +
                " (ID: " + proprietario.getId() + ")";
    }
}