package JulianoEliandra.TravelAi.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Document(collection = "Agencia")
public class Agencia {
    @MongoId
    private ObjectId idAgencia;
    private String nome;
    private String email;
    private String logo;

    public Agencia(String nome, String email, String logo) {
        this.nome = nome;
        this.email = email;
        this.logo = logo;
    }

    public ObjectId getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(ObjectId idAgencia) {
        this.idAgencia = idAgencia;
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
