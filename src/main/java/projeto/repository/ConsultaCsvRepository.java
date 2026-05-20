
package projeto.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsultaCsvRepository {

    private final String ficheiro = "consultas.csv";

    public void guardarConsulta(String linha) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ficheiro, true))) {
            pw.println(linha);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao guardar consulta.", e);
        }
    }

    public List<String[]> carregarConsultasRaw() {
        List<String[]> linhas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                linhas.add(linha.split(";"));
            }

        } catch (IOException e) {
            return linhas; // ficheiro ainda não existe
        }

        return linhas;
    }

}