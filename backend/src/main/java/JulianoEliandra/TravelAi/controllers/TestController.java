package JulianoEliandra.TravelAi.controllers;

import JulianoEliandra.TravelAi.dtos.ItinerarioDto;
import JulianoEliandra.TravelAi.models.Input;
import JulianoEliandra.TravelAi.models.Roteiro;
import JulianoEliandra.TravelAi.services.ChatGPTService;
import JulianoEliandra.TravelAi.services.PromptService;
import JulianoEliandra.TravelAi.services.RoteiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/testes")
public class TestController {

    @Autowired
    PromptService promptService;

    @Autowired
    ChatGPTService chatGPTService;

    @Autowired
    RoteiroService roteiroService;

    @PostMapping("/prompt")
    public ResponseEntity<?> testarPrompt(@RequestBody Input input){
        try{
            String inputValidado = promptService.validarInput(input);

            String prompt = promptService.buildPrompt(inputValidado);

            String resposta = chatGPTService.getChatResponse(prompt);

            ItinerarioDto roteiroDica = roteiroService.gerarRoteiroDica(resposta, input.getDt_inicio(), input.getDt_fim());

            return ResponseEntity.ok().body(roteiroDica);
        } catch (Exception e){
            return ResponseEntity.status(500).body("Erro" + e.getMessage());
        }
    }

}
