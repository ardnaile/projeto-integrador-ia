package JulianoEliandra.TravelAi.mappers;

import JulianoEliandra.TravelAi.dtos.RegistroUsuarioDto;
import JulianoEliandra.TravelAi.models.Usuario;

public class UsuarioMapper {
    public static Usuario toEntity(RegistroUsuarioDto registroUsuarioDto) {
        return new Usuario(
                registroUsuarioDto.nomeUsuario(),
                registroUsuarioDto.email(),
                registroUsuarioDto.senha(),
                registroUsuarioDto.idAgencia()
        );
    }
}
