package JulianoEliandra.TravelAi.services;

import JulianoEliandra.TravelAi.models.Dica;
import JulianoEliandra.TravelAi.models.Roteiro;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


@Service
public class DocumentoService {
    public String gerarPdf(Roteiro roteiro, Dica dica) throws FileNotFoundException {
        Document document = new Document();
        String caminhoArquivo = "roteiro_dicas_" + roteiro.getIdRoteiro() + ".pdf";

        try {
            PdfWriter.getInstance(document, new FileOutputStream(caminhoArquivo));
            document.open();

            // Fonte personalizada
            Font tituloFonte = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font subtituloFonte = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Font textoFonte = FontFactory.getFont(FontFactory.HELVETICA, 12);

            // Título
            Paragraph  titulo = new Paragraph(roteiro.getTitulo(), tituloFonte);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            document.add(new Paragraph("\n"));

            // Informações do Roteiro
            document.add(new Paragraph("Informações do Roteiro", subtituloFonte));
            document.add(new Paragraph("Destino: " + roteiro.getDestino(), textoFonte));
            document.add(new Paragraph("Data de Ida: " + roteiro.getDt_inicio(), textoFonte));
            document.add(new Paragraph("Data de Volta: " + roteiro.getDt_fim(), textoFonte));
            document.add(new Paragraph("Atividades: " + roteiro.getAtividades(), textoFonte));
            document.add(new Paragraph("Acomodação: " + roteiro.getAcomodacao(), textoFonte));
            document.add(new Paragraph("Transporte: " + roteiro.getTransporte(), textoFonte));
            document.add(new Paragraph("Gastronomia: " + roteiro.getGastronomia(), textoFonte));

            document.add(new Paragraph("\n"));

            // Adicionar informações da Dica
            document.add(new Paragraph("Dicas de Viagem", subtituloFonte));
            document.add(new Paragraph("Bagagem: " + dica.getBagagem(), textoFonte));
            document.add(new Paragraph("Costumes: " + dica.getCostumes(), textoFonte));
            document.add(new Paragraph("Moeda: " + dica.getMoeda(), textoFonte));
            document.add(new Paragraph("Idioma: " + dica.getIdioma(), textoFonte));
            document.add(new Paragraph("Documentos Necessários: " + dica.getDocumentos(), textoFonte));
            document.add(new Paragraph("Clima: " + dica.getClima(), textoFonte));

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return caminhoArquivo; // Retorna o caminho do arquivo gerado
    }

}
