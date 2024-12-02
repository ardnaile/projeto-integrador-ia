package JulianoEliandra.TravelAi.controllers;

import JulianoEliandra.TravelAi.dtos.LoginDto;
import JulianoEliandra.TravelAi.dtos.RegistroUsuarioDto;
import JulianoEliandra.TravelAi.mappers.UsuarioMapper;
import JulianoEliandra.TravelAi.models.Agencia;
import JulianoEliandra.TravelAi.models.Usuario;
import JulianoEliandra.TravelAi.repositories.AgenciaRepository;
import JulianoEliandra.TravelAi.services.UsuarioService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AgenciaRepository agenciaRepository;


    @PostMapping("/usuarios")
    public ResponseEntity<String> registroUsuario(@RequestBody RegistroUsuarioDto registroUsuarioDto) {
        try{
            Usuario usuario = UsuarioMapper.toEntity(registroUsuarioDto);

            Agencia agencia = agenciaRepository.findByIdAgencia(usuario.getIdAgencia());

            if (agencia != null){
                Usuario novoUsuario = usuarioService.salvarUsuario(usuario);
                return ResponseEntity.ok(novoUsuario.getNomeUsuario());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A agência informada não existe!");
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        ObjectId id = usuarioService.authenticate(loginDto); // chama o service

        if (id != null) { // verifica se o id não é null
            return ResponseEntity.ok(String.valueOf(id)); // login bem-sucedido
        } else {
            return ResponseEntity.status(401).body("Invalid credentials"); // login falhou
        }
    }

}
