package JulianoEliandra.TravelAi.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Document(collection = "Dica")
public class Dica {
    @MongoId
    private ObjectId idDica;
    private String idRoteiro;
    private String bagagem;
    private String costumes;
    private String moeda;
    private String idioma;
    private String documentos;
    private String clima;

    public Dica(String idRoteiro, String bagagem, String costumes, String moeda, String idioma, String documentos, String clima) {
        this.idRoteiro = idRoteiro;
        this.bagagem = bagagem;
        this.costumes = costumes;
        this.moeda = moeda;
        this.idioma = idioma;
        this.documentos = documentos;
        this.clima = clima;
    }

    public Dica(){};

    public ObjectId getIdDica() {
        return idDica;
    }

    public void setIdDica(ObjectId idDica) {
        this.idDica = idDica;
    }

    public String getIdRoteiro() {
        return idRoteiro;
    }

    public void setIdRoteiro(String idRoteiro) {
        this.idRoteiro = idRoteiro;
    }

    public String getBagagem() {
        return bagagem;
    }

    public void setBagagem(String bagagem) {
        this.bagagem = bagagem;
    }

    public String getCostumes() {
        return costumes;
    }

    public void setCostumes(String costumes) {
        this.costumes = costumes;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getDocumentos() {
        return documentos;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }
}
