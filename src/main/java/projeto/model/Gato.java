
package projeto.model;

public class Gato extends Animal {

    public Gato(int id, String nome, String raca, int idade, Proprietario proprietario) {
        super(id, nome, "Gato", raca, idade, proprietario);
    }

    @Override
    public String emitirSom() {
        return "Miau";
    }
}
