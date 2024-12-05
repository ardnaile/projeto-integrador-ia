package JulianoEliandra.TravelAi.controllers;

import JulianoEliandra.TravelAi.dtos.ItinerarioDto;
import JulianoEliandra.TravelAi.dtos.EmailDto;
import JulianoEliandra.TravelAi.models.Dica;
import JulianoEliandra.TravelAi.models.Input;
import JulianoEliandra.TravelAi.models.Roteiro;
import JulianoEliandra.TravelAi.repositories.DicaRepository;
import JulianoEliandra.TravelAi.repositories.RoteiroRepository;
import JulianoEliandra.TravelAi.services.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/roteiros")
public class RoteiroController {
    @Autowired
    private RoteiroService roteiroService;

    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RoteiroRepository roteiroRepository;

    @Autowired
    private DicaRepository dicaRepository;

    @Autowired
    private PromptService promptService;

    @Autowired
    private ChatGPTService chatGPTService;

    @PostMapping("")
    public ResponseEntity<?> gerarRoteiro(@RequestBody Input input){ //, @PathVariable ObjectId idUsuario
        try {
            String inputValidado = promptService.validarInput(input);

            String prompt = promptService.buildPrompt(inputValidado);

            String resposta = chatGPTService.getChatResponse(prompt);

            ItinerarioDto roteiroDica = roteiroService.gerarRoteiroDica(resposta, input.getDt_inicio(), input.getDt_fim());
//            roteiroDica.roteiro().setIdUsuario(idUsuario.toString());

            ItinerarioDto roteiroDicaSalvo = roteiroService.salvarRoteiroDica(roteiroDica);
            
            return ResponseEntity.ok(roteiroDicaSalvo);

        } catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao gerar roteiro " + e.getMessage());
        }
    }

    @PostMapping("/{id}/enviar-email")
    public ResponseEntity<String> enviarRoteiro(@PathVariable ObjectId id, @RequestBody EmailDto emailDto) throws FileNotFoundException {
        try {

            // Recuperar o roteiro e as dicas
            Roteiro roteiro = roteiroRepository.findByIdRoteiro(id);
            Dica dica = dicaRepository.findByIdRoteiro(String.valueOf(id));

            // Gerar o PDF
            String caminhoArquivo = documentoService.gerarPdf(roteiro, dica);

            // Enviar o PDF por e-mail
            emailService.enviarEmail(emailDto.getEmailCliente(), emailDto.getMensagem(), caminhoArquivo);

            return ResponseEntity.ok("Roteiro enviado!");
        } catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao enviar e-mail " + e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editarRoteiro(@PathVariable ObjectId id, @RequestBody ItinerarioDto itinerarioDto){
        try {
            ItinerarioDto roteiroEditado = roteiroService.editarRoteiro(id, itinerarioDto);
            return ResponseEntity.ok(roteiroEditado);
        } catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao atualizar roteiro " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirRoteiro(@PathVariable ObjectId id){
        try{
            roteiroService.excluirRoteiro(id);
            return ResponseEntity.ok("Excluido");
        } catch (NoSuchElementException e){
            return ResponseEntity.status(500).body("Erro ao excluir");
        }
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<ItinerarioDto>> listarRoteiros(@PathVariable ObjectId idUsuario){
        List<ItinerarioDto> roteiros = roteiroService.listarRoteiros(idUsuario);
        return ResponseEntity.ok(roteiros);
    }
    
}
