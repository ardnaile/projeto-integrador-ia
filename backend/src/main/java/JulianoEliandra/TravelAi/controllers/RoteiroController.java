package JulianoEliandra.TravelAi.controllers;

import JulianoEliandra.TravelAi.dtos.ItinerarioDto;
import JulianoEliandra.TravelAi.dtos.EmailDto;
import JulianoEliandra.TravelAi.models.Dica;
import JulianoEliandra.TravelAi.models.Prompt;
import JulianoEliandra.TravelAi.models.Roteiro;
import JulianoEliandra.TravelAi.repositories.DicaRepository;
import JulianoEliandra.TravelAi.repositories.RoteiroRepository;
import JulianoEliandra.TravelAi.services.DocumentoService;
import JulianoEliandra.TravelAi.services.EmailService;
import JulianoEliandra.TravelAi.services.RoteiroService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/roteiro")
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

    @PostMapping("/gerar")
    public ResponseEntity<?> gerarRoteiro(@RequestBody Prompt prompt){
        try {
            Prompt promptValidado = roteiroService.validarPrompt(prompt);

            Roteiro novoRoteiro = roteiroService.gerarRoteiro(promptValidado);
            Dica novaDica = roteiroService.gerarDica(promptValidado, novoRoteiro);

            ItinerarioDto itinerarioDto = roteiroService.salvarRoteiroDica(novoRoteiro, novaDica);
            
            return ResponseEntity.ok(itinerarioDto);

        } catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao gerar roteiro " + e.getMessage());
        }
    }

    @PostMapping("/enviar/{id}")
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

    @PatchMapping("/editar/{id}")
    public ResponseEntity<?> editarRoteiro(@PathVariable ObjectId id, @RequestBody ItinerarioDto itinerarioDto){
        try {
            ItinerarioDto roteiroEditado = roteiroService.editarRoteiro(id, itinerarioDto);
            return ResponseEntity.ok(roteiroEditado);
        } catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao atualizar roteiro " + e.getMessage());
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirRoteiro(@PathVariable ObjectId id){
        try{
            roteiroService.excluirRoteiro(id);
            return ResponseEntity.ok("Excluido");
        } catch (NoSuchElementException e){
            return ResponseEntity.status(500).body("Erro ao excluir");
        }
    }

}
