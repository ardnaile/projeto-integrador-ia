package JulianoEliandra.TravelAi.controllers;

import JulianoEliandra.TravelAi.models.Input;
import JulianoEliandra.TravelAi.services.PromptService;
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

    @PostMapping("/prompt")
    public ResponseEntity<String> testarPrompt(@RequestBody Input input){
        try{
            String inputValidado = promptService.validarInput(input);

            String prompt = promptService.buildPrompt(inputValidado);

            return ResponseEntity.ok().body(prompt);
        } catch (Exception e){
            return ResponseEntity.status(500).body("Erro" + e.getMessage());
        }
    }

}
