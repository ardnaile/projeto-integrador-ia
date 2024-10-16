package JulianoEliandra.TravelAi.services;

import JulianoEliandra.TravelAi.models.Dica;
import JulianoEliandra.TravelAi.models.Roteiro;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


@Service
public class DocumentoService {
    public String gerarPdfRoteiroEDicas(Roteiro roteiro, Dica dica) throws FileNotFoundException {
        Document document = new Document();
        String caminhoArquivo = "roteiro_dicas_" + roteiro.getIdRoteiro() + ".pdf";

        try {
            PdfWriter.getInstance(document, new FileOutputStream(caminhoArquivo));
            document.open();

            // Adicionar informações do Roteiro
            document.add(new Paragraph(roteiro.getTitulo()));
            document.add(new Paragraph("Destino: " + roteiro.getDestino()));
            document.add(new Paragraph("Data de Ida: " + roteiro.getDt_inicio()));
            document.add(new Paragraph("Data de Volta: " + roteiro.getDt_fim()));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Atividades: " + roteiro.getAtividades()));
            document.add(new Paragraph("Acomodação: " + roteiro.getAcomodacao()));
            document.add(new Paragraph("Transporte: " + roteiro.getTransporte()));
            document.add(new Paragraph("Gastronomia: " + roteiro.getGastronomia()));


            // Adicionar informações da Dica
            document.add(new Paragraph("Dicas de Viagem"));
            document.add(new Paragraph("Bagagem: " + dica.getBagagem()));
            document.add(new Paragraph("Saúde: " + dica.getSaude()));
            document.add(new Paragraph("Costumes: " + dica.getCostumes()));
            document.add(new Paragraph("Moeda: " + dica.getMoeda()));
            document.add(new Paragraph("Idioma: " + dica.getIdioma()));
            document.add(new Paragraph("Documentos Necessários: " + dica.getDocumentos()));
            document.add(new Paragraph("Clima: " + dica.getClima()));

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return caminhoArquivo; // Retorna o caminho do arquivo gerado
    }

}
