package JulianoEliandra.TravelAi.controllers;

import JulianoEliandra.TravelAi.dtos.RegistroAgenciaDto;
import JulianoEliandra.TravelAi.mappers.AgenciaMapper;
import JulianoEliandra.TravelAi.models.Agencia;
import JulianoEliandra.TravelAi.services.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agencias")
public class AgenciaController {
    @Autowired
    private AgenciaService agenciaService;

    @PostMapping("")
    public ResponseEntity<String> registroAgencia(@RequestBody RegistroAgenciaDto registroAgenciaDto){
        try{
            Agencia agencia = AgenciaMapper.toEntity(registroAgenciaDto);

            if (agencia.getEmail() != null && agencia.getNome() != null){
                Agencia novaAgencia = agenciaService.salvarAgencia(agencia);
                return ResponseEntity.ok(String.valueOf(novaAgencia.getIdAgencia()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados faltantes");
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao registrar agência: " + e.getMessage());
        }
    }
}
