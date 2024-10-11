package eliandra.juliano.travelia.models;

import java.util.Date;
import java.util.UUID;

public class Roteiro {
    private UUID id_roteiro;
    private String titulo;
    private String id_usuario; // autor
    private String atividades;
    private String acomodacao;
    private String transporte;
    private String gastronomia;
    private Date dt_inicio;
    private Date dt_fim;

    public Roteiro(String titulo, String id_usuario, String atividades, String acomodacao, String transporte, String gastronomia, Date dt_inicio, Date dt_fim) {
        this.titulo = titulo;
        this.id_usuario = id_usuario;
        this.atividades = atividades;
        this.acomodacao = acomodacao;
        this.transporte = transporte;
        this.gastronomia = gastronomia;
        this.dt_inicio = dt_inicio;
        this.dt_fim = dt_fim;
    }

    public UUID getId_roteiro() {
        return id_roteiro;
    }

    public void setId_roteiro(UUID id_roteiro) {
        this.id_roteiro = id_roteiro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
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
