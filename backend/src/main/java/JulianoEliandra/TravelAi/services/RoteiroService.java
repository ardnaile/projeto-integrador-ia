package JulianoEliandra.TravelAi.services;

import JulianoEliandra.TravelAi.models.Prompt;
import JulianoEliandra.TravelAi.models.Roteiro;
import JulianoEliandra.TravelAi.repositories.RoteiroRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RoteiroService {
    @Autowired
    RoteiroRepository roteiroRepository;

    // Validamos o que o usuário enviou e colocamos valores default se tiver algo null

    public Prompt validarPrompt(Prompt prompt){
        if (prompt.getTitulo() == null || prompt.getTitulo().isEmpty()) {
            prompt.setTitulo("Roteiro Padrão");
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

        // Validação das datas
        if (prompt.getDt_inicio() == null) {
            throw new IllegalArgumentException("A data de início não pode ser nula.");
        }
        if (prompt.getDt_fim() == null) {
            throw new IllegalArgumentException("A data de fim não pode ser nula.");
        }

        return prompt;
        // retornamos um prompt, que é um json com titulo, atividade, acomodacao,
        // transporte, gastronomia e datas
    }

    public Roteiro gerarNovoRoteiro(Prompt prompt) {

        // Aqui o prompt validado será passado para o ChatGPT gerar o roteiro

        // Aqui está sendo gerado um roteiro padrão para exemplo
        Roteiro roteiro = new Roteiro(
                "Roteiro de Viagem Padrão",
                null,
                "Visita a pontos turísticos, passeios de barco, e degustação de comidas locais.",
                "Hotel Padrão",
                "Ônibus turístico",
                "Culinária local e restaurantes recomendados",
                prompt.getDt_inicio(),
                prompt.getDt_fim()
        );


        // Salvar o roteiro no banco de dados
        Roteiro roteiroSalvo = roteiroRepository.save(roteiro);

        // Retornar o roteiro salvo
        return roteiroSalvo;
    }

    // Editar roteiro existente

    public Roteiro editarRoteiro(ObjectId idRoteiro, Roteiro novoRoteiro) {

        // Busca o roteiro pelo id
        Roteiro roteiro = roteiroRepository.findByIdRoteiro(idRoteiro);

        if (roteiro != null) {
            // Atualiza os campos com os novos valores, se fornecidos
            roteiro.setTitulo(novoRoteiro.getTitulo() != null ? novoRoteiro.getTitulo() : roteiro.getTitulo());
            roteiro.setAtividades(novoRoteiro.getAtividades() != null ? novoRoteiro.getAtividades() : roteiro.getAtividades());
            roteiro.setAcomodacao(novoRoteiro.getAcomodacao() != null ? novoRoteiro.getAcomodacao() : roteiro.getAcomodacao());
            roteiro.setTransporte(novoRoteiro.getTransporte() != null ? novoRoteiro.getTransporte() : roteiro.getTransporte());
            roteiro.setGastronomia(novoRoteiro.getGastronomia() != null ? novoRoteiro.getGastronomia() : roteiro.getGastronomia());
            roteiro.setDt_inicio(novoRoteiro.getDt_inicio() != null ? novoRoteiro.getDt_inicio() : roteiro.getDt_inicio());
            roteiro.setDt_fim(novoRoteiro.getDt_fim() != null ? novoRoteiro.getDt_fim() : roteiro.getDt_fim());

            // Retorna o roteiro atualizado (simulando um retorno do banco de dados)
            return roteiro;
        } else {
            // Caso o roteiro não seja encontrado
            throw new NoSuchElementException("Roteiro não encontrado.");
        }
    }

    // Excluir roteiro
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

    // Enviar roteiro
}
