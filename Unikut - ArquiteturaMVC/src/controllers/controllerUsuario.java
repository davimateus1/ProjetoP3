package controllers;

import java.util.List;
import models.Usuario;

public class controllerUsuario {

    private static controllerUsuario uniqueInstance;

    private controllerUsuario() {

    }

    public static synchronized controllerUsuario getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new controllerUsuario();
        }
        return uniqueInstance;
    }

    public Usuario buscaUsuarioSenha(String senha, List<Usuario> usuarios) {
        if (usuarios != null) {
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).getSenha().equals(senha)) {
                    return usuarios.get(i);
                }
            }
        }
        return null;
    }

    public Usuario acessaConta(Usuario cad, List<Usuario> usuarios) {
        if (usuarios.contains((cad))) {
            return cad;
        } else {
            return null;
        }
    }

    public Usuario buscaUsuarioLogin(String login, List<Usuario> usuarios) {
        if (usuarios != null) {
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).getLogin().equals(login)) {
                    return usuarios.get(i);
                }
            }
        }
        return null;

    }
}
