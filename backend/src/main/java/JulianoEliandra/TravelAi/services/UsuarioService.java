package JulianoEliandra.TravelAi.services;

import JulianoEliandra.TravelAi.dtos.LoginDto;
import JulianoEliandra.TravelAi.models.Usuario;
import JulianoEliandra.TravelAi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean authenticate(LoginDto loginDto) {

        Usuario usuario = usuarioRepository.findByNomeUsuario(loginDto.nomeUsuario());

        return usuario != null && usuario.getSenha().equals(loginDto.senha()); // Retorna verdadeiro se a autenticação for bem-sucedida
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
