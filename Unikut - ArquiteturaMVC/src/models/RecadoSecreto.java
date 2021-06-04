package models;
import static views.cores.*;

public class RecadoSecreto {
    private String texto;
    private String emetente;
    private String senhaRecado;
    
    public RecadoSecreto (String texto, String emetente, String senhaRecado){
        this.texto = texto;
        this.emetente = emetente;
        this.senhaRecado = senhaRecado;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getEmetente() {
        return emetente;
    }

    public void setEmetente(String emetente) {
        this.emetente = emetente;
    }

    public String getSenhaRecado() {
        return senhaRecado;
    }

    public void setSenhaRecado(String senhaRecado) {
        this.senhaRecado = senhaRecado;
    }
    
    @Override
    public String toString() {
        return ANSI_YELLOW + ANSI_RED_BACKGROUND + " -RECADO SECRETO-" + ANSI_RESET + "\n"
               + ANSI_YELLOW + "RECADO: " + ANSI_RESET + this.getTexto() + "\n"
               + ANSI_YELLOW + "EMITENTE: " + ANSI_RESET + this.getEmetente() + "\n";
    }
    
    
}
