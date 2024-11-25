package JulianoEliandra.TravelAi.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.UUID;

@Document(collection = "Roteiro")
public class Roteiro {
    @MongoId
    private ObjectId idRoteiro;
    private String titulo;
    private String destino;
    private String idUsuario; // autor
    private String atividades;
    private String acomodacao;
    private String transporte;
    private String gastronomia;
    private Date dt_inicio;
    private Date dt_fim;

    public Roteiro(String titulo, String destino, String idUsuario, String atividades, String acomodacao, String transporte, String gastronomia, Date dt_inicio, Date dt_fim) {
        this.titulo = titulo;
        this.destino = destino;
        this.idUsuario = idUsuario;
        this.atividades = atividades;
        this.acomodacao = acomodacao;
        this.transporte = transporte;
        this.gastronomia = gastronomia;
        this.dt_inicio = dt_inicio;
        this.dt_fim = dt_fim;
    }
//    public Roteiro(){};

    public ObjectId getIdRoteiro() {
        return idRoteiro;
    }

    public void setIdRoteiro(ObjectId idRoteiro) {
        this.idRoteiro = idRoteiro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAtividades() {
        return atividades;
    }

    public void setAtividades(String atividades) {
        this.atividades = atividades;
    }

    public String getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(String acomodacao) {
        this.acomodacao = acomodacao;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getGastronomia() {
        return gastronomia;
    }

    public void setGastronomia(String gastronomia) {
        this.gastronomia = gastronomia;
    }

    public Date getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(Date dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public Date getDt_fim() {
        return dt_fim;
    }

    public void setDt_fim(Date dt_fim) {
        this.dt_fim = dt_fim;
    }
}
