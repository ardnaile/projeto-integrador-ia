package eliandra.juliano.travelia.models;

import java.util.UUID;

public class Usuario {

    private UUID idEstudante;
    private String nome_usuario;
    private String email;
    private String senha;
    private String id_agencia;

    public Usuario(String nome_usuario, String email, String senha, String id_agencia) {
        this.nome_usuario = nome_usuario;
        this.email = email;
        this.senha = senha;
        this.id_agencia = id_agencia;
    }
// Getters e Setters
    public UUID getIdEstudante() {
        return idEstudante;
    }

    public void setIdEstudante(UUID idEstudante) {
        this.idEstudante = idEstudante;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
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

    public String getId_agencia() {
        return id_agencia;
    }

    public void setId_agencia(String id_agencia) {
        this.id_agencia = id_agencia;
    }
}

