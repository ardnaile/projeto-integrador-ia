package JulianoEliandra.TravelAi.controllers;

import JulianoEliandra.TravelAi.models.Prompt;
import JulianoEliandra.TravelAi.models.Roteiro;
import JulianoEliandra.TravelAi.services.RoteiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roteiro")
public class RoteiroController {
    @Autowired
    private RoteiroService roteiroService;

    @PostMapping("/gerar")
    public ResponseEntity<Roteiro> gerarRoteiro(@RequestBody Prompt prompt){

        Prompt promptValidado = roteiroService.validarPrompt(prompt);

        Roteiro novoRoteiro = roteiroService.gerarNovoRoteiro(promptValidado);

        return ResponseEntity.ok(novoRoteiro);
    }
}
