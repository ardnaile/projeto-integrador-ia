package eliandra.juliano.travelia.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Document(collection = "Usuario")
public class Usuario implements UserDetails {
    @MongoId
    private UUID idEstudante;
    private String usuario;
    private String email;
    private String senha;
    private String id_agencia;

    public Usuario(String usuario, String email, String senha, String id_agencia) {
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
        this.id_agencia = id_agencia;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    // Métodos User Details
    @Override
    public String getPassword(){
        return this.senha;
    }
    @Override
    public String getUsername(){
        return this.usuario;
    }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}

