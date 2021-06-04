
package views;

import controllers.*;
import java.util.List;
import java.util.Scanner;
import models.Usuario;
import static views.cores.ANSI_RESET;
import static views.cores.ANSI_YELLOW;


public class criaConta {
     
    public controllerUsuario c = controllerUsuario.getInstance();
    
     public void criaConta(List<Usuario> usuarios) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(ANSI_YELLOW + "Informe seu login: " + ANSI_RESET);
        String login = entrada.nextLine();
        if (c.buscaUsuarioLogin(login, usuarios) == null) {
            System.out.println(ANSI_YELLOW + "Digite sua senha: " + ANSI_RESET);
            String senha = entrada.nextLine();
            System.out.println(ANSI_YELLOW + "Digite o seu nome: " + ANSI_RESET);
            String nome = entrada.nextLine();
            if (nome.equals("")) {
                Usuario convidado = new Usuario(login, senha, "Convidado", "Vazio");
                usuarios.add(convidado);
                System.out.println(convidado);
                Thread fluxoMembro = new Thread(convidado);
                fluxoMembro.start();

            } else {
                Usuario membro = new Usuario(login, senha, nome, "Vazio");
                usuarios.add(membro);
                System.out.println(membro);
                Thread fluxoMembro = new Thread(membro);
                fluxoMembro.start();
            }
        } else {
            System.err.println("LOGIN EXISTENTE!");
        }
    }
}
