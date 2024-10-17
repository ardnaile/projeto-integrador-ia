package JulianoEliandra.TravelAi.repositories;

import JulianoEliandra.TravelAi.models.Dica;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DicaRepository extends MongoRepository<Dica, String> {

    Dica findByIdDica(ObjectId idDica);

    Dica findByIdRoteiro(String idRoteiro);

    Dica deleteByIdRoteiro(String idRoteiro);

    List<Dica> findAllByIdRoteiro(String idRoteiro);

}
