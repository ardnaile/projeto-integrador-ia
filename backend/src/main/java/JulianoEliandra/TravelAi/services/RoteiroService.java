package JulianoEliandra.TravelAi.services;

import JulianoEliandra.TravelAi.dtos.ItinerarioDto;
import JulianoEliandra.TravelAi.models.Dica;
import JulianoEliandra.TravelAi.models.Prompt;
import JulianoEliandra.TravelAi.models.Roteiro;
import JulianoEliandra.TravelAi.repositories.DicaRepository;
import JulianoEliandra.TravelAi.repositories.RoteiroRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

@Service
public class RoteiroService {
    @Autowired
    RoteiroRepository roteiroRepository;

    @Autowired
    DicaRepository dicaRepository;

    @Autowired
    DocumentoService documentoService;

    @Autowired
    EmailService emailService;

    public Prompt validarPrompt(Prompt prompt){

        // Validamos o que o usuário enviou e colocamos valores default se tiver algo null

        if (prompt.getTitulo() == null || prompt.getTitulo().isEmpty()) {
            prompt.setTitulo("Roteiro Padrão");
        }
        if (prompt.getDestino() == null || prompt.getDestino().isEmpty()) {
            prompt.setDestino("Destino Padrão");
        }
        if (prompt.getAtividades() == null || prompt.getAtividades().isEmpty()) {
            prompt.setAtividades("Atividades de exemplo: passeio pela cidade, trilhas.");
        }
        if (prompt.getAcomodacao() == null || prompt.getAcomodacao().isEmpty()) {
            prompt.setAcomodacao("Acomodação padrão: hotel local.");
        }
        if (prompt.getTransporte() == null || prompt.getTransporte().isEmpty()) {
            prompt.setTransporte("Transporte padrão: ônibus ou carro.");
        }
        if (prompt.getGastronomia() == null || prompt.getGastronomia().isEmpty()) {
            prompt.setGastronomia("Gastronomia local: pratos típicos da região.");
        }
        if (prompt.getOrcamento() == null || prompt.getOrcamento().isEmpty()) {
            prompt.setOrcamento("Orçamento médio.");
        }

        // Validação das datas

        if (prompt.getDt_inicio() == null) {
            throw new IllegalArgumentException("A data de início não pode ser nula.");
        }
        if (prompt.getDt_fim() == null) {
            throw new IllegalArgumentException("A data de fim não pode ser nula.");
        }

        return prompt;
    }

    public Roteiro gerarRoteiro(Prompt prompt) {

        // Aqui o prompt validado será passado para o ChatGPT gerar o roteiro

        // Aqui está sendo gerado um roteiro padrão para exemplo
        Roteiro roteiro = new Roteiro(
                "Roteiro de Viagem Padrão",
                "Destino padrão",
                null,
                "Visita a pontos turísticos, passeios de barco, e degustação de comidas locais.",
                "Hotel Padrão",
                "Ônibus turístico",
                "Culinária local e restaurantes recomendados",
                prompt.getDt_inicio(),
                prompt.getDt_fim()
        );

        return roteiro; // retorna para a tela de edição
    }

    public Dica gerarDica(Prompt prompt, Roteiro roteiro){

        // No momento de gerar o roteiro, também vamos rodar essa função e gerar as dicas com base no prompt

        Dica dica = new Dica(
                String.valueOf(roteiro.getIdRoteiro()),
                "teste",
                "teste",
                "teste",
                "teste",
                "teste",
                "teste",
                "teste"
        );

        return dica;
    }

    // Ao apertar o botão salvar:

    public ItinerarioDto salvarRoteiroDica(Roteiro roteiro, Dica dica){
        Roteiro roteiroSalvo = roteiroRepository.save(roteiro);

        // setamos o id do roteiro dentro de dica
        dica.setIdRoteiro(String.valueOf(roteiroSalvo.getIdRoteiro()));

        // e aí sim salva
        Dica dicaSalva = dicaRepository.save(dica);
        return new ItinerarioDto(roteiroSalvo, dicaSalva);
    }

//    Esse método retorna os dois juntos assim:
//    {
//        "roteiro": {
//        // campos do roteiro
//    },
//        "dica": {
//        // campos da dica
//    }
//    }
//    Retorna para jogar na tela de roteiro pronto

    // EDITAR ROTEIRO EXISTENTE


    // EXCLUIR ROTEIRO
    public void excluirRoteiro(ObjectId idRoteiro) {
        // Verifica se o roteiro existe
        if (roteiroRepository.existsById(String.valueOf(idRoteiro))) {
            // Exclui o roteiro do MongoDB
            roteiroRepository.deleteById(String.valueOf(idRoteiro));
            System.out.println("Roteiro com id " + idRoteiro + " foi excluído.");
        } else {
            throw new NoSuchElementException("Roteiro não encontrado.");
        }
    }

    // PDF e Envio
    // Passar um id???
    public void gerarPdfEmail(Roteiro roteiro, Dica dica, String emailCliente) throws FileNotFoundException, MessagingException {
        // Gerar o PDF
        String caminhoArquivo = documentoService.gerarPdfRoteiroEDicas(roteiro, dica);

        // Enviar o PDF por e-mail
        String assunto = "Seu Roteiro de Viagem e Dicas!";
        String corpo = "Olá, segue em anexo o seu roteiro de viagem e dicas personalizadas.";
        emailService.enviarEmailComAnexo(emailCliente, assunto, corpo, caminhoArquivo);
    }
}
