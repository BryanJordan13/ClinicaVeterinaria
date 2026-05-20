
package projeto.model;

public class Cao extends Animal {

    public Cao(int id, String nome, String raca, int idade, Proprietario proprietario) {
        super(id, nome, "Cão", raca, idade, proprietario);
    }

    @Override
    public String emitirSom() {
        return "Au Au";
    }
}
