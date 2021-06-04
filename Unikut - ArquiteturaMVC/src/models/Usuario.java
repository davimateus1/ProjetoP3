package models;

import java.util.ArrayList;
import java.util.List;
import static views.cores.*;

public class Usuario implements Runnable {

    private String login;
    private String senha;
    private String nome;
    private String bio;

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    public ArrayList<Usuario> amigos = new ArrayList<>();
    private ArrayList<Usuario> amigosPendentes = new ArrayList<>();
    private ArrayList<Recado> recados = new ArrayList<>();
    private ArrayList<Recado> msgMuralPendente = new ArrayList<>();
    private ArrayList<Recado> msgMural = new ArrayList<>();
    private ArrayList<RecadoSecreto> recadoSecreto = new ArrayList<>();
    public ArrayList<Usuario> matchPendentes = new ArrayList<>();
    private ArrayList<Usuario> match = new ArrayList<>();

    public Usuario(String login, String senha, String nome, String bio) {
        this.setLogin(login);
        this.setSenha(senha);
        this.setNome(nome);
        this.setBio(bio);

    }

    public Usuario(String login) {
        this.login = login;
    }

    public Usuario() {

    }

    public ArrayList<Usuario> getMatchPendentes() {
        return matchPendentes;
    }

    public void setMatchPendentes(ArrayList<Usuario> matchPendentes) {
        this.matchPendentes = matchPendentes;
    }

    public ArrayList<Usuario> getMatch() {
        return match;
    }

    public void setMatch(ArrayList<Usuario> match) {
        this.match = match;
    }

    public ArrayList<RecadoSecreto> getRecadoSecreto() {
        return recadoSecreto;
    }

    public void setRecadoSecreto(ArrayList<RecadoSecreto> recadoSecreto) {
        this.recadoSecreto = recadoSecreto;
    }

    public ArrayList<Recado> getMsgMuralPendente() {
        return msgMuralPendente;
    }

    public void setMsgMuralPendente(ArrayList<Recado> msgMuralPendente) {
        this.msgMuralPendente = msgMuralPendente;
    }

    public ArrayList<Recado> getMsgMural() {
        return msgMural;
    }

    public void setMsgMural(ArrayList<Recado> msgMural) {
        this.msgMural = msgMural;
    }

    public ArrayList<Recado> getRecados() {
        return recados;
    }

    public void setRecados(ArrayList<Recado> recados) {
        this.recados = recados;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Usuario> getAmigosPendentes() {
        return amigosPendentes;
    }

    public void setAmigosPendentes(ArrayList<Usuario> amigosPendentes) {
        this.amigosPendentes = amigosPendentes;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return this.bio;
    }

    public ArrayList<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<Usuario> amigos) {
        this.amigos = amigos;
    }

    
    public void editaNome(List<Usuario> usuarios, String novoNome, Usuario usuario) {
        Usuario edit;
        edit = usuarios.get(usuarios.indexOf(usuario));
        edit.setNome(novoNome);
    }

    public void editaSenha(List<Usuario> usuarios, String novaSenha, Usuario usuario) {
        Usuario edit;
        edit = usuarios.get(usuarios.indexOf(usuario));
        edit.setSenha(novaSenha);
    }

    public void adicionaBio(List<Usuario> usuarios, String bio, Usuario usuario) {
        Usuario edit;
        edit = usuarios.get(usuarios.indexOf(usuario));
        edit.setBio(bio);
    }

    public static Recado buscaUsuarioTopico(String topico, List<Recado> recados) {
        if (recados != null) {
            for (int i = 0; i < recados.size(); i++) {
                if (recados.get(i).getTopico().equals(topico)) {
                    return recados.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public void run() {
        for (int i = 0; i < 0; i++) {
            System.out.println(ANSI_YELLOW + "SUAS MENSAGENS: " + ANSI_RESET + this.getRecados());
        }
    }
     @Override
    public String toString() {
        return "LOGIN: " + this.getLogin() + "\n"
                + "NOME: " + this.getNome() + "\n"
                + "BIOGRAFIA: " + this.getBio() + "\n"
                + "AMIGOS: " + this.getAmigos().size() + "\n"
                + "RECADOS: " + this.getRecados().size() + "\n";

    }
}

    