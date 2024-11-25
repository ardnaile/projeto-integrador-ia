package JulianoEliandra.TravelAi.models;


import java.util.Date;

public class Input {
    private String destino;
    private String atividades;
    private String acomodacao;
    private String transporte;
    private String gastronomia;
    private String orcamento;

    private Date dt_inicio;
    private Date dt_fim;

    public Input(){}

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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
    public String getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(String orcamento) {
        this.orcamento = orcamento;
    }

    public boolean isEmpty() {
        return (this.destino == null || this.destino.isEmpty()) &&
                (this.atividades == null || this.atividades.isEmpty()) &&
                (this.acomodacao == null || this.acomodacao.isEmpty()) &&
                (this.transporte == null || this.transporte.isEmpty()) &&
                (this.gastronomia == null || this.gastronomia.isEmpty()) &&
                (this.orcamento == null || this.orcamento.isEmpty()) &&
                (this.dt_inicio == null) &&
                (this.dt_fim == null);
    }
}
