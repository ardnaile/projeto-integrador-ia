package eliandra.juliano.travelia.services;

import eliandra.juliano.travelia.models.Usuario;
import eliandra.juliano.travelia.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private static UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean authenticate(String nomeUsuario, String senha) {
        return usuarioRepository.findByNomeUsuario(nomeUsuario)
                .map(user -> passwordEncoder.matches(senha, user.getSenha()))
                .orElse(false);
    }
    public static Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}

