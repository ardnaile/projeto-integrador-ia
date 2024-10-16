package JulianoEliandra.TravelAi.repositories;

import JulianoEliandra.TravelAi.models.Roteiro;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoteiroRepository extends MongoRepository<Roteiro, String> {
    Roteiro findByIdRoteiro(ObjectId idRoteiro);
}
