package JulianoEliandra.TravelAi.dtos;

import org.bson.types.ObjectId;

public class PdfEmailDto {
    private ObjectId idRoteiro;
    private String emailCliente;

    // Getters e Setters
    public ObjectId getIdRoteiro() {
        return idRoteiro;
    }

    public void setIdRoteiro(ObjectId idRoteiro) {
        this.idRoteiro = idRoteiro;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
}
