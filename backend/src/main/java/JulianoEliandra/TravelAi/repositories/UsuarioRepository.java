package JulianoEliandra.TravelAi.repositories;

import JulianoEliandra.TravelAi.models.Usuario;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Usuario findByNomeUsuario(String nomeUsuario);
}
