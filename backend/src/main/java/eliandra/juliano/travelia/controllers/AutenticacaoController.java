package eliandra.juliano.travelia.controllers;

import eliandra.juliano.travelia.dtos.RegisterDto;
import eliandra.juliano.travelia.mapper.UsuarioMapper;
import eliandra.juliano.travelia.models.Agencia;
import eliandra.juliano.travelia.models.Usuario;
import eliandra.juliano.travelia.repositories.AgenciaRepository;
import eliandra.juliano.travelia.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/exemplo")
public class AutenticacaoController {
    @Autowired
    private AutenticacaoService autenticacaoService;

    private AgenciaRepository agenciaRepository;


    @PostMapping("/novo")
    public ResponseEntity<String> registroUsuario(@RequestBody RegisterDto registerDto) {
        try{
            Usuario usuario = UsuarioMapper.toEntity(registerDto);

            Optional<Agencia> agencia = agenciaRepository.findByIdAgencia(usuario.getIdAgencia());

            if (agencia.isPresent()){
                Usuario novoUsuario = AutenticacaoService.salvarUsuario(usuario);
                return ResponseEntity.ok(novoUsuario.getIdAgencia());//"Estudante cadastrado com sucesso. ID: " +
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A agência informada não existe!");
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String nomeUsuario, @RequestParam String senha) {
        if (autenticacaoService.authenticate(nomeUsuario, senha)) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
