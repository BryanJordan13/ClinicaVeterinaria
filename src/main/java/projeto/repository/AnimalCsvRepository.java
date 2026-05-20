package projeto.repository;

import projeto.model.Animal;
import projeto.model.Cao;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AnimalCsvRepository {
    private static final String FICHEIRO = "data/animais.csv";
    // CONSTRUTOR

    public AnimalCsvRepository() {
        try {
            // CRIAR PASTA DATA

            File pasta = new File("data");

            if (!pasta.exists()) {

                pasta.mkdirs();

            }

            // CRIAR FICHEIRO CSV

            File ficheiro = new File(FICHEIRO);

            if (!ficheiro.exists()) {

                ficheiro.createNewFile();

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    // GUARDAR ANIMAIS
    public void guardar(List<Animal> animais) {

        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(FICHEIRO), StandardCharsets.UTF_8))) {

            for (Animal animal : animais) {

                writer.println(
                        animal.getId() + ";"
                                + animal.getNome() + ";"
                                + animal.getEspecie() + ";"
                                + animal.getRaca() + ";"
                                + animal.getIdade()
                );
            }

        } catch (IOException e) {

            System.out.println("Erro ao guardar animais.");
        }
    }

    public void carregar(List<Animal> animais) {

        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIRO))) {

            String linha;

            while ((linha = reader.readLine()) != null) {

                // TESTE
                System.out.println("Linha: " + linha);

                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);

                String nome = dados[1];

                Animal animal = new Cao(id, nome, "Desconhecida", 0, null);

                animais.add(animal);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}