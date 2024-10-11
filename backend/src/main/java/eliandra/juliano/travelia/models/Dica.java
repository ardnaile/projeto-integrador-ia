package eliandra.juliano.travelia.models;

import java.util.UUID;

public class Dica {
    private UUID id_dica;
    private String id_roteiro;
    private String bagagem;
    private String saude;
    private String costumes;
    private String moeda;
    private String idioma;
    private String documentos;
    private String clima;

    public Dica(String id_roteiro, String bagagem, String saude, String costumes, String moeda, String idioma, String documentos, String clima) {
        this.id_roteiro = id_roteiro;
        this.bagagem = bagagem;
        this.saude = saude;
        this.costumes = costumes;
        this.moeda = moeda;
        this.idioma = idioma;
        this.documentos = documentos;
        this.clima = clima;
    }

    public UUID getId_dica() {
        return id_dica;
    }

    public void setId_dica(UUID id_dica) {
        this.id_dica = id_dica;
    }

    public String getId_roteiro() {
        return id_roteiro;
    }

    public void setId_roteiro(String id_roteiro) {
        this.id_roteiro = id_roteiro;
    }

    public String getBagagem() {
        return bagagem;
    }

    public void setBagagem(String bagagem) {
        this.bagagem = bagagem;
    }

    public String getSaude() {
        return saude;
    }

    public void setSaude(String saude) {
        this.saude = saude;
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
