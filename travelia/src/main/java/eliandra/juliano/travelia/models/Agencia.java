package eliandra.juliano.travelia.models;

import java.util.UUID;

public class Agencia {

    private UUID id_agencia;
    private String nome;
    private String email;
    private String logo;

    public Agencia(String nome, String email, String logo) {
        this.nome = nome;
        this.email = email;
        this.logo = logo;
    }

    public UUID getId_agencia() {
        return id_agencia;
    }

    public void setId_agencia(UUID id_agencia) {
        this.id_agencia = id_agencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
