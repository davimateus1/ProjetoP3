
package views;

import controllers.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Usuario;
import static views.cores.ANSI_BLUE;
import static views.cores.ANSI_GREEN;
import static views.cores.ANSI_RESET;
import static views.cores.ANSI_WHITE;
import static views.cores.ANSI_WHITE_BACKGROUND;
import static views.cores.ANSI_YELLOW;

public class admView extends usuarioView {

     
    public controllerUsuario c = controllerUsuario.getInstance();
    
    public void alterarUsuario(List<Usuario> usuarios) {
        Scanner in = new Scanner(System.in);
        Scanner entrada = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        System.out.println(ANSI_BLUE + ANSI_WHITE_BACKGROUND + "Informe o login do usuário que vai ser alterado: " + ANSI_RESET);
        System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
        String login = in.nextLine();
        Usuario conta = c.buscaUsuarioLogin(login, usuarios);
        if (conta != null) {
            int op;
            System.out.println(ANSI_BLUE + "1 - Alterar senha do usuário" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "2 - Alterar nome do usuário" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "3 - Adicionar biografia" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "4 - Voltar ao menu" + ANSI_RESET);
            op = in.nextInt();
            switch (op) {
                case 1:
                    System.out.println(ANSI_GREEN + "Informe a nova senha do usuário: " + ANSI_RESET);
                    System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
                    String senhaEdit = entrada.nextLine();
                    in.nextLine();
                    editaSenha(usuarios, senhaEdit, conta);
                    break;
                case 2:
                    System.out.println(ANSI_GREEN + "Informe o novo nome do usuário: " + ANSI_RESET);
                    System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
                    String nomeEdit = entrada.nextLine();
                    in.nextLine();
                    editaNome(usuarios, nomeEdit, conta);
                    break;
                case 3:
                    System.out.println( ANSI_GREEN + "Adicione coisas que você julgar necessário nesse ponto:" + ANSI_RESET);
                    System.out.println(ANSI_YELLOW + "(EXEMPLOS: Apelidos, GitHub, Hobbies e outros)" + ANSI_RESET);
                    String bio = input.nextLine();
                    in.nextLine();
                    adicionaBio(usuarios, bio, conta);
                    break;
                case 4:
                    System.out.println(ANSI_GREEN + "RETORANDO AO MENU..." + ANSI_RESET);
                    break;
                default:
                    System.err.println("COMANDO INVÁLIDO!");
            }
        } else {
            System.err.println("USUÁRIO NÃO ENCONTRADO!");
        }
    }
    public void removeConta(List<Usuario> usuarios) {
        Scanner in = new Scanner(System.in);
        
        System.out.println(ANSI_GREEN + "Informe o login do usuário a ser removido: " + ANSI_RESET);
        String login = in.nextLine();
        Usuario conta = c.buscaUsuarioLogin(login, usuarios);
        if (conta != null) {
            if(login.equals("admin")){
                System.err.println("Você não pode se excluir!");
            }else{
            ArrayList<Usuario> amigos = this.getAmigos();
            ArrayList<Usuario> amigosP = this.getAmigosPendentes();
            amigos.remove(conta);
            amigosP.remove(conta);
            usuarios.remove(conta);
            System.err.println("CONTA REMOVIDA!");
         }
        
    }else{
            System.err.println("USUÁRIO NÃO ENCONTRADO!");
        }
}
}

    
    

