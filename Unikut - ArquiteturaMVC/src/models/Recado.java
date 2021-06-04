package models;
import static views.cores.*;

public class Recado{
    private String texto;
    private String emitente;
    private String topico;
    public Recado (String texto, String emitente, String topico){
        this.texto = texto;
        this.emitente = emitente;
        this.topico = topico;
            
    }
   
    public Recado(String texto, String emitente) {
        this.texto = texto;
        this.emitente = emitente;
    }

    public Recado(String topico) {
        this.topico = topico;
    }

    public String getEmitente() {
        return emitente;
    }

    public void setEmitente(String emitente) {
        this.emitente = emitente;
    }
   
    public String getTopico() {
        return topico;
    }

    public void setTopico(String topico) {
        this.topico = topico;
    }

    public Recado(){
        
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
     
    @Override
    public String toString() {
        return ANSI_YELLOW + ANSI_RED_BACKGROUND + " -MENSAGEM RECEBIDA-" + ANSI_RESET + "\n"
               + ANSI_YELLOW + "MENSAGEM: " + ANSI_RESET + "\n"
               + texto + "\n"
               + ANSI_YELLOW + "EMITENTE: " + ANSI_RESET + "\n"
               + emitente + "\n"
               + ANSI_YELLOW + "TÓPICO: " + ANSI_RESET + "\n"
               + topico;
                    
    }
    
    
}
