package views;

import controllers.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.ContaAdm;
import models.Recado;
import models.RecadoSecreto;
import models.Usuario;
import static views.cores.*;

public class view {

    public static controllerUsuario c = controllerUsuario.getInstance();
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner entrada = new Scanner(System.in);
        int op;
        Usuario mural = new Usuario();
        menuUsuario acessoUsuario = new menuUsuario();
        menuAdm acessoAdm = new menuAdm();
        ContaAdm admin = new ContaAdm();
        admin.setLogin("admin");
        admin.setNome("Administrador");
        admin.setSenha("123");
        admin.setBio("Olá, sou o administrador.");
        ArrayList<Usuario> usuariosSistema = new ArrayList<>();
        ArrayList<RecadoSecreto> recadosSecretos = new ArrayList<>();
        ArrayList<Recado> recados = new ArrayList<>();
        usuariosSistema.add(admin);
        do {
            System.out.println(ANSI_BLUE + ANSI_WHITE_BACKGROUND + "BEM VINDO, OQUE VOCÊ DESEJA FAZER?" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "1 - CRIAR CONTA; " + ANSI_RESET);
            System.out.println(ANSI_BLUE + "2 - ACESSAR CONTA;" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "3 - CONTA ADIMINISTRADOR" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "0 - FECHAR REDE SOCIAL;" + ANSI_RESET);
            System.out.print(ANSI_WHITE + " -> " + ANSI_RESET);
            op = in.nextInt();
            switch (op) {
                case 1:
                    criaConta(usuariosSistema);
                    break;
                case 2:
                    System.out.println(ANSI_GREEN + "Informe o login para acessar a conta: " + ANSI_RESET);
                    String login = entrada.nextLine();
                    in.nextLine();
                    Usuario cadLogin = c.buscaUsuarioLogin(login, usuariosSistema);
                    if (cadLogin != null) {
                        System.out.println(ANSI_GREEN + "Informe a senha: " + ANSI_RESET);
                        String senha = in.nextLine();
                        if (senha.equals(cadLogin.getSenha())) {
                            Usuario acesso = c.acessaConta(cadLogin, usuariosSistema);
                            if (acesso != null) {
                                System.out.println(ANSI_GREEN + "O PERFIL ESTÁ SENDO ACESSADO..." + ANSI_RESET);
                                acessoUsuario.contaAcessada(cadLogin, usuariosSistema, mural, recadosSecretos, recados);
                            } else {
                                System.err.println("ERRO!");
                            }
                        } else {
                            System.err.println("SENHA INVÁLIDA!");
                        }
                    } else {
                        System.err.println("LOGIN INVÁLIDO!");
                    }
                    break;
                case 3:
                    System.out.println(ANSI_GREEN + "Informe o login para acessar a conta secreta: " + ANSI_RESET);
                    String loginSecreto = entrada.nextLine();
                    in.nextLine();
                    ContaAdm admLogin = (ContaAdm) c.buscaUsuarioLogin(loginSecreto, usuariosSistema);
                    if (admLogin != null) {
                        System.out.println(ANSI_GREEN + "Informe a senha: " + ANSI_RESET);
                        String senhaSecreta = in.nextLine();
                        if (senhaSecreta.equals(admLogin.getSenha())) {
                            ContaAdm acesso = (ContaAdm) c.acessaConta(admLogin, usuariosSistema);
                            if (acesso != null) {
                                System.out.println(ANSI_GREEN + "O PERFIL ESTÁ SENDO ACESSADO..." + ANSI_RESET);
                                acessoAdm.contaAdmAcessada(admLogin, usuariosSistema);
                            } else {
                                System.err.println("ERRO!");
                            }
                        } else {
                            System.err.println("SENHA INVÁLIDA");
                        }
                    } else {
                        System.err.println("LOGIN INVÁLIDO");
                    }
                    break;

            }

        } while (op != 0);
        System.out.println(usuariosSistema);
        System.out.println(admin);

    }

    public static void criaConta(List<Usuario> usuarios) {
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
