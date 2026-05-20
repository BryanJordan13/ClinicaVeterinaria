
package projeto.model;

public class Ave extends Animal {

    public Ave(int id, String nome, String raca, int idade, Proprietario proprietario) {
        super(id, nome, "Ave", raca, idade, proprietario);
    }

    @Override
    public String emitirSom() {
        return "Piu";
    }
}
