package JulianoEliandra.TravelAi.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "Usuario")
public class Usuario {
    @MongoId
    private ObjectId idUsuario;
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

    public ObjectId getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(ObjectId idUsuario) {
        this.idUsuario = idUsuario;
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
