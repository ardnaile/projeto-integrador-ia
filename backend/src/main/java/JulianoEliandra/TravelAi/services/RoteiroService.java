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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    // Validar prompt

    public Prompt validarPrompt(Prompt prompt) {

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

    // Gerar roteiro

    public Roteiro gerarRoteiro(Prompt prompt) { // vamos pegar o id do usuário de parametro tb depois

        // Aqui o prompt validado será passado para o ChatGPT gerar o roteiro

        // Aqui está sendo gerado um roteiro padrão para exemplo
        Roteiro roteiro = new Roteiro(
                prompt.getTitulo(), // colocando o título do prompt para ter maior controle dos testes
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

    // Gerar dica

    public Dica gerarDica(Prompt prompt, Roteiro roteiro) {

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

    // Salvar roteiro

    public ItinerarioDto salvarRoteiroDica(Roteiro roteiro, Dica dica) {
        Roteiro roteiroSalvo = roteiroRepository.save(roteiro);

        // setamos o id do roteiro dentro de dica
        dica.setIdRoteiro(String.valueOf(roteiroSalvo.getIdRoteiro()));

        // e aí sim salva
        Dica dicaSalva = dicaRepository.save(dica);
        return new ItinerarioDto(roteiroSalvo, dicaSalva);
    }


    // Editar roteiro existente
    private void atualizarCampos(Object original, Object alterado, List<String> camposAlteraveis) {
        for (String campoNome : camposAlteraveis) {
            try {
                Field campo = original.getClass().getDeclaredField(campoNome);
                campo.setAccessible(true); // Permite acesso a campos privados

                Object valorAlterado = campo.get(alterado);
                if (valorAlterado != null) {
                    campo.set(original, valorAlterado); // Atualiza o valor do campo
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace(); // Trata exceção se o campo não for encontrado
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // Trata exceção de acesso ilegal
            }
        }
    }

    public ItinerarioDto editarRoteiro(ObjectId id, ItinerarioDto itinerarioDto) {
        // Recuperamos o roteiro e a dica a partir do id fornecido pelo cliente
        Roteiro roteiro = roteiroRepository.findByIdRoteiro(id);
        Dica dica = dicaRepository.findByIdRoteiro(String.valueOf(id));

        if (roteiro == null) {
            throw new RuntimeException("Roteiro não encontrado");
        }

        // Recuperamos os dados do roteiro que foram alterados
        Roteiro roteiroAlterado = itinerarioDto.roteiro();

        // Setamos quais campos podem ser alterados
        List<String> camposRoteiro = Arrays.asList("titulo", "destino", "atividades", "acomodacao", "transporte", "gastronomia", "dt_inicio", "dt_fim");

        // Atualizamos os campos
        atualizarCampos(roteiro, roteiroAlterado, camposRoteiro);

        // Salvamos
        roteiroRepository.save(roteiro);


        // Mesma coisa com dica
        Dica dicaAlterada = itinerarioDto.dica();
        List<String> camposDica = Arrays.asList("bagagem", "saude", "costumes", "moeda", "idioma", "documentos", "clima");
        atualizarCampos(dica, dicaAlterada, camposDica);
        dicaRepository.save(dica);

        // Retornamos
        return new ItinerarioDto(roteiro, dica);
    }


    // Excluir roteiro

    public void excluirRoteiro(ObjectId idRoteiro) {
        // Verifica se o roteiro existe
        if (roteiroRepository.existsById(String.valueOf(idRoteiro))) {
            // Exclui o roteiro do MongoDB
            roteiroRepository.deleteById(String.valueOf(idRoteiro));
            dicaRepository.deleteByIdRoteiro(String.valueOf(idRoteiro));
            System.out.println("Roteiro com id " + idRoteiro + " foi excluído.");
        } else {
            throw new NoSuchElementException("Roteiro não encontrado.");
        }
    }

    public List<ItinerarioDto> listarRoteiros(ObjectId id) {
        // Busca todos os roteiros pelo id do usuário
        List<Roteiro> roteiros = roteiroRepository.findAllByIdRoteiro(id);

        // Busca todas as dicas associadas ao idRoteiro
        List<Dica> dicas = dicaRepository.findAllByIdRoteiro(String.valueOf(id));

        // Cria uma lista para armazenar os ItinerarioDto
        List<ItinerarioDto> itinerarios = new ArrayList<>();

        // Itera sobre cada roteiro
        for (Roteiro roteiro : roteiros) {
            // Itera sobre cada dica correspondente ao roteiro
            for (Dica dica : dicas) {
                if (dica.getIdRoteiro().equals(roteiro.getIdRoteiro())) {
                    // Cria um novo ItinerarioDto com o Roteiro e a Dica
                    ItinerarioDto itinerarioDto = new ItinerarioDto(roteiro, dica);

                    // Adiciona o ItinerarioDto à lista
                    itinerarios.add(itinerarioDto);
                }
            }
        }

        // Retorna a lista de ItinerarioDto
        return itinerarios;
    }

}
