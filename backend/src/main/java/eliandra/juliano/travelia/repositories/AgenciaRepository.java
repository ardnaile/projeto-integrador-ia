package eliandra.juliano.travelia.repositories;

import eliandra.juliano.travelia.models.Agencia;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AgenciaRepository extends MongoRepository<Agencia, String> {

    Optional<Agencia> findByIdAgencia(String idAgencia);
}
