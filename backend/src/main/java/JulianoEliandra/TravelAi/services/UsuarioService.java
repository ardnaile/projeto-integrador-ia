package JulianoEliandra.TravelAi.services;

import JulianoEliandra.TravelAi.dtos.LoginDto;
import JulianoEliandra.TravelAi.models.Usuario;
import JulianoEliandra.TravelAi.repositories.UsuarioRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public ObjectId authenticate(LoginDto loginDto) {

        Usuario usuario = usuarioRepository.findByNomeUsuario(loginDto.nomeUsuario());

        if (usuario != null && usuario.getSenha().equals(loginDto.senha())){
            return usuario.getIdUsuario();
        } else {
            return null;
        }
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
