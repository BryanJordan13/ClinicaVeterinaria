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

            content.beginText();

            content.setFont(PDType1Font.HELVETICA_BOLD, 18);

            content.newLineAtOffset(50, 750);

            content.showText("FATURA CLÍNICA VETERINÁRIA");

            content.setFont(PDType1Font.HELVETICA, 12);

            content.newLineAtOffset(0, -40);

            content.showText("Animal: "
                    + consulta.getAnimal().getNome());

            content.newLineAtOffset(0, -20);

            content.showText("Veterinário: "
                    + consulta.getVeterinario().getNome());

            content.newLineAtOffset(0, -20);

            content.showText("Diagnóstico: "
                    + consulta.getDescricao());

            content.newLineAtOffset(0, -20);

            content.showText("Valor Consulta: "
                    + consulta.getValorConsulta() + "€");

            content.newLineAtOffset(0, -20);

            content.showText("Valor Medicação: "
                    + consulta.getValorMedicacao() + "€");

            content.newLineAtOffset(0, -20);

            content.showText("Valor Exames: "
                    + consulta.getValorExames() + "€");

            content.newLineAtOffset(0, -20);

            content.showText("TOTAL: "
                    + consulta.calcularTotal() + "€");

            content.endText();

            content.close();

            String nomeFicheiro =
                    "fatura_"
                            + consulta.getAnimal().getNome()
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
