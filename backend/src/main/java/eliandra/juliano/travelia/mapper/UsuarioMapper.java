package eliandra.juliano.travelia.mapper;

import eliandra.juliano.travelia.dtos.RegisterDto;
import eliandra.juliano.travelia.models.Usuario;

public class UsuarioMapper {
    public static Usuario toEntity(RegisterDto registerDto) {
        return new Usuario(
                registerDto.nomeUsuario(),
                registerDto.email(),
                registerDto.senha(),
                registerDto.idAgencia()
        );
    }
}
