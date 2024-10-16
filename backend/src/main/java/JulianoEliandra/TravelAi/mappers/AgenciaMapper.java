package JulianoEliandra.TravelAi.mappers;

import JulianoEliandra.TravelAi.dtos.RegistroAgenciaDto;
import JulianoEliandra.TravelAi.models.Agencia;

public class AgenciaMapper {
    public static Agencia toEntity(RegistroAgenciaDto registroAgenciaDto) {
        return new Agencia(
                registroAgenciaDto.nome(),
                registroAgenciaDto.email(),
                registroAgenciaDto.logo()
        );
    }
}
