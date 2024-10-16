package JulianoEliandra.TravelAi.services;

import JulianoEliandra.TravelAi.models.Agencia;
import JulianoEliandra.TravelAi.repositories.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgenciaService {
    @Autowired
    private AgenciaRepository agenciaRepository;

    public Agencia salvarAgencia(Agencia agencia){
        return agenciaRepository.save(agencia);
    }
}
