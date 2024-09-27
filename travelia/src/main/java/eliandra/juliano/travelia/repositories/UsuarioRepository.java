package eliandra.juliano.travelia.repositories;

import eliandra.juliano.travelia.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByUsername(String usuario);
    Optional<Usuario> findByEmail(String email);
}
