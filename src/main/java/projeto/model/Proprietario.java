
package projeto.model;

public class Proprietario {
    private int id;
    private String nome;

    public Proprietario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() { return id; }

    @Override
    public String toString() {
        return id + " - " + nome;
    }

    public String getNome() {
        return nome;
    }
}

