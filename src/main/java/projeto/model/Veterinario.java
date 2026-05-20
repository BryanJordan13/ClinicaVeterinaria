
package projeto.model;

public class Veterinario {

    private int id;
    private String nome;
    private String especialidade;
    private String cedulaProfissional;

    public Veterinario(int id,
                       String nome,
                       String especialidade,
                       String cedulaProfissional) {

        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.cedulaProfissional = cedulaProfissional;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getCedulaProfissional() {
        return cedulaProfissional;
    }

    @Override
    public String toString() {

        return id + " - "
                + nome
                + " | Especialidade: "
                + especialidade
                + " | Cédula: "
                + cedulaProfissional;
    }
}
