package eliandra.juliano.travelia.dtos;

// infos que o usuario envia para registrar
public record RegisterDto(String usuario, String email, String senha, String id_agencia) {
}
