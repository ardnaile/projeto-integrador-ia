package eliandra.juliano.travelia.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Document(collection = "Usuario")
public class Usuario {
    @MongoId
    private UUID idEstudante;
    private String nomeUsuario;
    private String email;
    private String senha;
    private String idAgencia;

    public Usuario(String nomeUsuario, String email, String senha, String idAgencia) {
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.idAgencia = idAgencia;
    }

    public Usuario() {

    }

    // Getters e Setters

    public UUID getIdEstudante() {
        return idEstudante;
    }

    public void setIdEstudante(UUID idEstudante) {
        this.idEstudante = idEstudante;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(String idAgencia) {
        this.idAgencia = idAgencia;
    }

}

