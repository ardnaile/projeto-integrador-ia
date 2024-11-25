package JulianoEliandra.TravelAi.controllers;

import JulianoEliandra.TravelAi.dtos.ItinerarioDto;
import JulianoEliandra.TravelAi.dtos.EmailDto;
import JulianoEliandra.TravelAi.models.Dica;
import JulianoEliandra.TravelAi.models.Input;
import JulianoEliandra.TravelAi.models.Roteiro;
import JulianoEliandra.TravelAi.repositories.DicaRepository;
import JulianoEliandra.TravelAi.repositories.RoteiroRepository;
import JulianoEliandra.TravelAi.services.DocumentoService;
import JulianoEliandra.TravelAi.services.EmailService;
import JulianoEliandra.TravelAi.services.PromptService;
import JulianoEliandra.TravelAi.services.RoteiroService;
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

    @PostMapping("")
    public ResponseEntity<?> gerarRoteiro(@RequestBody Input input){
        try {
            String inputValidado = promptService.validarInput(input);

            String prompt = promptService.buildPrompt(inputValidado);

            Date dt_inicio = input.getDt_inicio();
            Date dt_fim = input.getDt_fim();

            ItinerarioDto novoRoteiroDica = roteiroService.gerarRoteiroDica(prompt, dt_inicio, dt_fim);

            ItinerarioDto itinerarioDto = roteiroService.salvarRoteiroDica(novoRoteiroDica);
            
            return ResponseEntity.ok(itinerarioDto);

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
