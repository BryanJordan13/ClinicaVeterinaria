package projeto.utils;

import projeto.model.Consulta;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PdfExporter {

    public static void exportarFatura(Consulta consulta) {

        PDDocument document = new PDDocument();

        PDPage page = new PDPage();

        document.addPage(page);

        try {

            PDPageContentStream content =
                    new PDPageContentStream(document, page);

            // =========================
            // TÍTULO
            // =========================

            content.beginText();

            content.setFont(
                    PDType1Font.HELVETICA_BOLD,
                    22
            );

            // Azul
            content.setNonStrokingColor(
                    0,
                    102,
                    204
            );

            content.newLineAtOffset(180, 750);

            content.showText("FATURA");

            content.endText();

            // Voltar preto
            content.setNonStrokingColor(
                    0,
                    0,
                    0
            );

            // =========================
            // LINHA HEADER
            // =========================

            content.setStrokingColor(
                    0,
                    102,
                    204
            );

            content.moveTo(50, 730);

            content.lineTo(550, 730);

            content.stroke();

            // =========================
            // NOME CLÍNICA
            // =========================

            content.beginText();

            content.setFont(
                    PDType1Font.HELVETICA_BOLD,
                    14
            );

            content.newLineAtOffset(50, 700);

            content.showText(
                    "Clínica Veterinária"
            );

            content.endText();

            // =========================
            // DADOS FATURA
            // =========================

            content.beginText();

            content.setFont(
                    PDType1Font.HELVETICA,
                    12
            );

            content.newLineAtOffset(50, 650);

            content.showText(
                    "Animal: "
                            + consulta.getAnimal().getNome()
            );

            content.newLineAtOffset(0, -25);

            content.showText(
                    "Veterinário: "
                            + consulta.getVeterinario().getNome()
            );

            content.newLineAtOffset(0, -25);

            content.showText(
                    "Diagnóstico: "
                            + consulta.getDescricao()
            );

            content.newLineAtOffset(0, -25);

            content.showText(
                    "Data: "
                            + consulta.getDataHora()
            );

            content.newLineAtOffset(0, -40);

            // =========================
            // SECÇÃO VALORES
            // =========================

            content.setFont(
                    PDType1Font.HELVETICA_BOLD,
                    13
            );

            content.showText("VALORES");

            content.newLineAtOffset(0, -30);

            content.setFont(
                    PDType1Font.HELVETICA,
                    12
            );

            // Consulta
            content.showText(
                    "Consulta: "
                            + consulta.getValorConsulta()
                            + "€"
            );

            content.newLineAtOffset(0, -25);

            // Medicação
            content.showText(
                    "Medicação: "
                            + consulta.getValorMedicacao()
                            + "€"
            );

            content.newLineAtOffset(0, -25);

            // Exames
            content.showText(
                    "Exames: "
                            + consulta.getValorExames()
                            + "€"
            );

            content.newLineAtOffset(0, -40);

            // =========================
            // SUBTOTAL
            // =========================

            content.setFont(
                    PDType1Font.HELVETICA_BOLD,
                    14
            );

            content.showText(
                    "Subtotal: "
                            + consulta.calcularTotal()
                            + "€"
            );

            content.newLineAtOffset(0, -25);

            // =========================
            // IVA
            // =========================

            // Vermelho
            content.setNonStrokingColor(
                    204,
                    0,
                    0
            );

            content.showText(
                    "IVA (23%): "
                            + consulta.calcularIVA()
                            + "€"
            );

            // Voltar preto
            content.setNonStrokingColor(
                    0,
                    0,
                    0
            );

            content.newLineAtOffset(0, -40);

            // =========================
            // TOTAL FINAL
            // =========================

            content.setFont(
                    PDType1Font.HELVETICA_BOLD,
                    18
            );

            // Verde
            content.setNonStrokingColor(
                    0,
                    153,
                    76
            );

            content.showText(
                    "TOTAL FINAL: "
                            + consulta.calcularTotalComIVA()
                            + "€"
            );

            // Voltar preto
            content.setNonStrokingColor(
                    0,
                    0,
                    0
            );

            content.endText();

            // =========================
            // LINHA FOOTER
            // =========================

            content.setStrokingColor(
                    180,
                    180,
                    180
            );

            content.moveTo(50, 70);

            content.lineTo(550, 70);

            content.stroke();

            // =========================
            // FOOTER
            // =========================

            content.beginText();

            content.setFont(
                    PDType1Font.HELVETICA_OBLIQUE,
                    10
            );

            content.setNonStrokingColor(
                    120,
                    120,
                    120
            );

            content.newLineAtOffset(180, 50);

            content.showText(
                    "Obrigado pela preferência!"
            );

            content.endText();

            content.close();

            // =========================
            // PASTA FATURAS
            // =========================

            java.io.File pasta =
                    new java.io.File("faturas");

            if (!pasta.exists()) {

                pasta.mkdir();
            }

            // =========================
            // NOME FICHEIRO
            // =========================

            String nomeFicheiro =
                    "faturas/fatura_"
                            + consulta.getAnimal().getNome()
                            + "_"
                            + System.currentTimeMillis()
                            + ".pdf";

            document.save(nomeFicheiro);

            document.close();

            System.out.println(
                    "PDF exportado com sucesso!"
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}