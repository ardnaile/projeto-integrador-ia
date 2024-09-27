package eliandra.juliano.travelia.dtos;

// infos que o usuario envia pra login
public record LoginRequestDto(String usuario, String senha) {
}


/*
exemplo de dado

{
    "username": "admin",
    "password": "123456"
}

 */