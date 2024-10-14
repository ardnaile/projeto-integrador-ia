package JulianoEliandra.TravelAi.models;

import java.util.Date;

public class Prompt {
    private String titulo;
    private String atividades;
    private String acomodacao;
    private String transporte;
    private String gastronomia;
    private Date dt_inicio;
    private Date dt_fim;

    public Prompt(String titulo, String atividades, String acomodacao, String transporte, String gastronomia, Date dt_inicio, Date dt_fim) {
        this.titulo = titulo;
        this.atividades = atividades;
        this.acomodacao = acomodacao;
        this.transporte = transporte;
        this.gastronomia = gastronomia;
        this.dt_inicio = dt_inicio;
        this.dt_fim = dt_fim;
    }

    public Prompt(){}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
