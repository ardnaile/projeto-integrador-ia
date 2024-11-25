package JulianoEliandra.TravelAi.services;

import JulianoEliandra.TravelAi.dtos.ItinerarioDto;
import JulianoEliandra.TravelAi.models.Dica;
import JulianoEliandra.TravelAi.models.Input;
import JulianoEliandra.TravelAi.models.Roteiro;
import JulianoEliandra.TravelAi.repositories.DicaRepository;
import JulianoEliandra.TravelAi.repositories.RoteiroRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RoteiroService {
    @Autowired
    RoteiroRepository roteiroRepository;

    @Autowired
    DicaRepository dicaRepository;

    public ItinerarioDto gerarRoteiroDica(String resposta, Date dt_inicio, Date dt_fim) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Converte a string JSON em um JsonNode
        JsonNode rootNode = objectMapper.readTree(resposta);

        // Acessa o objeto 'roteiro' dentro do JSON
        JsonNode roteiroNode = rootNode.path("roteiro");

        // Acessa os campos do objeto 'roteiro'
        String titulo = roteiroNode.path("titulo").asText();
        String destino = roteiroNode.path("destino").toString();  // 'destino' é uma lista, então usamos toString()
        String atividades = roteiroNode.path("atividades").toString(); // 'atividades' também é uma lista
        String acomodacao = roteiroNode.path("acomodacao").asText();
        String transporte = roteiroNode.path("transporte").asText();
        String gastronomia = roteiroNode.path("gastronomia").asText();


        // Acessa o objeto 'dica' dentro do JSON
        JsonNode dicaNode = rootNode.path("dicas");

        // Acessa os campos do objeto 'dicas'
        String bagagem = dicaNode.path("bagagem").asText();
        String costumes = dicaNode.path("costumes").asText();
        String moeda = dicaNode.path("moedas").asText();
        String idioma = dicaNode.path("idioma").asText();
        String documentos = dicaNode.path("documentos").asText();
        String clima = dicaNode.path("clima").asText();

        // Criação do objeto Roteiro
        Roteiro roteiro = new Roteiro(
                titulo,
                destino,
                null,
                atividades,
                acomodacao,
                transporte,
                gastronomia,
                dt_inicio,
                dt_fim
        );

        // Criação do objeto Dica
        Dica dica = new Dica(
                null,
                bagagem,
                costumes,
                moeda,
                idioma,
                documentos,
                clima
        );

        // Criação do objeto ItinerarioDto
        ItinerarioDto roteiroDica = new ItinerarioDto(roteiro, dica);

        return roteiroDica;
    }

    // Salvar roteiro

    public ItinerarioDto salvarRoteiroDica(ItinerarioDto itinerarioDto) {
        // Recebemos o itinerário e salvamos separadamente
        Roteiro roteiro = itinerarioDto.roteiro();
        Dica dica = itinerarioDto.dica();

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
