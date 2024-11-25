package JulianoEliandra.TravelAi.services;

import JulianoEliandra.TravelAi.models.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PromptService {

    public String validarInput(Input input) {
        String inputValidado = "";

        // Verifica cada campo e adiciona os que estiverem preenchidos

        if (input.getDestino() != null && !input.getDestino().isEmpty()) {
            inputValidado += "destino: " + input.getDestino() + ",";
        }
        if (input.getAtividades() != null && !input.getAtividades().isEmpty()) {
            inputValidado += "atividades: " + input.getAtividades() + ",";
        }
        if (input.getAcomodacao() != null && !input.getAcomodacao().isEmpty()) {
            inputValidado += "acomodacao: " + input.getAcomodacao() + ",";
        }
        if (input.getTransporte() != null && !input.getTransporte().isEmpty()) {
            inputValidado += "transporte: " + input.getTransporte() + ",";
        }
        if (input.getGastronomia() != null && !input.getGastronomia().isEmpty()) {
            inputValidado += "gastronomia: " + input.getGastronomia() + ",";
        }
        if (input.getOrcamento() != null && !input.getOrcamento().isEmpty()) {
            inputValidado += "orçamento: " + input.getOrcamento() + ",";
        }

        // Validação das datas

        if (input.getDt_inicio() == null) {
            throw new IllegalArgumentException("A data de início não pode ser nula.");
        }
        if (input.getDt_fim() == null) {
            throw new IllegalArgumentException("A data de fim não pode ser nula.");
        }
        if (input.getDt_fim().compareTo(input.getDt_inicio()) < 0) {
            throw new IllegalArgumentException("A data de fim deve ser posterior à data de início");
        }

        System.out.println("validarInput: " + inputValidado);
        return inputValidado;
    }

    public String buildPrompt(String input) throws Exception {
        String prompt = "Crie um objeto JSON com as chaves 'roteiro' (incluindo apenas titulo, destino, atividades, acomodacao, transporte e gastronomia) " +
                "e 'dicas' (incluindo apenas bagagem, costumes, moedas, idioma, documentos e clima) sem hierarquia interna. Considere: " + input;

        // Concatenar o prompt com o JSON do usuário
        return prompt;
    }

}
