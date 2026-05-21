
package projeto.model;

public class Proprietario {
    private int id;
    private String nome;

    public Proprietario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() { return id; }


    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {

        return "ID: " + id +
                " | Nome: " + nome;
    }
}

