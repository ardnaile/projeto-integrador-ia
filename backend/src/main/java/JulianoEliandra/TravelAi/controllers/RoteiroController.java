package JulianoEliandra.TravelAi.controllers;

import JulianoEliandra.TravelAi.dtos.ItinerarioDto;
import JulianoEliandra.TravelAi.dtos.PdfEmailDto;
import JulianoEliandra.TravelAi.models.Dica;
import JulianoEliandra.TravelAi.models.Prompt;
import JulianoEliandra.TravelAi.models.Roteiro;
import JulianoEliandra.TravelAi.repositories.DicaRepository;
import JulianoEliandra.TravelAi.repositories.RoteiroRepository;
import JulianoEliandra.TravelAi.services.RoteiroService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/roteiro")
public class RoteiroController {
    @Autowired
    private RoteiroService roteiroService;

    @Autowired
    private RoteiroRepository roteiroRepository;

    @Autowired
    private DicaRepository dicaRepository;

    @PostMapping("/gerar")
    public ResponseEntity<ItinerarioDto> gerarRoteiro(@RequestBody Prompt prompt){

        Prompt promptValidado = roteiroService.validarPrompt(prompt);

        Roteiro novoRoteiro = roteiroService.gerarRoteiro(promptValidado);
        Dica novaDica = roteiroService.gerarDica(promptValidado, novoRoteiro);

        ItinerarioDto itinerarioDto = new ItinerarioDto(novoRoteiro, novaDica);

        return ResponseEntity.ok(itinerarioDto);
    }

    @PostMapping("/salvar")
    public ResponseEntity<ItinerarioDto> salvarRoteiro(@RequestBody ItinerarioDto itinerarioDto){
        ItinerarioDto itinerario = roteiroService.salvarRoteiroDica(itinerarioDto.roteiro(), itinerarioDto.dica());
        return ResponseEntity.ok(itinerario);
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarRoteiroDicasPorEmail(@RequestBody PdfEmailDto pdfEmailDto) throws FileNotFoundException, MessagingException {
        // Recuperar o roteiro e as dicas
        Roteiro roteiro = roteiroRepository.findByIdRoteiro(pdfEmailDto.getIdRoteiro());
        Dica dica = dicaRepository.findByIdRoteiro(String.valueOf(pdfEmailDto.getIdRoteiro()));
        roteiroService.gerarPdfEmail(roteiro, dica, pdfEmailDto.getEmailCliente());

        return ResponseEntity.ok("Roteiro e dicas enviados por e-mail com sucesso!");
    }

}
