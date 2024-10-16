package JulianoEliandra.TravelAi.repositories;

import JulianoEliandra.TravelAi.models.Agencia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgenciaRepository extends MongoRepository<Agencia, String> {

    Agencia findByIdAgencia(String idAgencia);
}
