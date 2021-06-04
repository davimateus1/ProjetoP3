package views;

import controllers.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Recado;
import models.RecadoSecreto;
import models.Usuario;
import static views.cores.*;

public class usuarioView extends Usuario {

    
    public controllerUsuario c = controllerUsuario.getInstance();
    public controllerRecados d = controllerRecados.getInstance();
    
    public void exibeConversa(List<Usuario> usuarios) {
        Runnable runnable = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println(ANSI_GREEN + "Informe o seu login para exibição de conversas: " + ANSI_RESET);
            String login1 = in.nextLine();
            Usuario busca = c.buscaUsuarioLogin(login1, usuarios);
            if (busca == null) {
                System.err.println("Login inexistente!");
            } else {
                Thread t1 = new Thread(busca);
                t1.start();
                for (int i = 0; i < 1; i++) {
                    busca.run();
                    System.out.println(ANSI_YELLOW + ANSI_WHITE_BACKGROUND + "MENSAGENS" + ANSI_RESET);
                    System.out.println(busca.getRecados());
                }
            }
        };
    }

    public void exibeMural(Usuario usuario) {
        System.out.println(ANSI_GREEN + ANSI_WHITE_BACKGROUND + "BEM VINDO AO MURAL" + ANSI_RESET);
        System.out.println(usuario.getMsgMural());

    }

    public static void menuUsuario() {
        System.out.println(ANSI_YELLOW + ANSI_WHITE_BACKGROUND + "BEM VINDO AO SEU PERFIL" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "1 - EDITAR PERFIL/ADICIONAR ATRIBUTO AO PERFIL" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "2 - ADICIONAR AMIGOS" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "3 - ACEITAR SOLICITAÇÕES DE AMIZADE" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "4 - ENVIAR RECADOS" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "5 - EXIBIR RECADOS" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "6 - ENVIAR MENSAGEM - MURAL" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "7 - ACEITAR MENSAGEM - MURAL" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "8 - EXIBIR MURAL" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "9 - ENVIAR RECADO SECRETO" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "10 - EXIBIR RECADO SECRETO" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "0 - ENCERRAR" + ANSI_RESET);
        System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
    }

    public void alterarDados(List<Usuario> usuarios) {
        Scanner in = new Scanner(System.in);
        Scanner entrada = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        int op;
        System.out.println(ANSI_YELLOW + "Informe o seu login: " + ANSI_RESET);
        String login = in.nextLine();
        Usuario buscaLogin;
        buscaLogin = c.buscaUsuarioLogin(login, usuarios);
        if (buscaLogin != null) {
            System.out.println(ANSI_YELLOW + "Informe sua senha: " + ANSI_RESET);
            String senha = in.nextLine();
            if (senha.equals(buscaLogin.getSenha())) {
                do {
                    System.out.println(ANSI_BLUE + ANSI_WHITE_BACKGROUND + "OQUE VOCÊ DESEJA FAZER?" + ANSI_RESET);
                    System.out.println(ANSI_BLUE + "1 - Alterar senha do usuário" + ANSI_RESET);
                    System.out.println(ANSI_BLUE + "2 - Alterar nome do usuário" + ANSI_RESET);
                    System.out.println(ANSI_BLUE + "3 - Adicionar biografia" + ANSI_RESET);
                    System.out.println(ANSI_BLUE + "4 - Voltar ao menu" + ANSI_RESET);
                    System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
                    op = in.nextInt();
                    switch (op) {
                        case 1:
                            System.out.println(ANSI_YELLOW + "Informe a sua nova senha: " + ANSI_RESET);
                            String senhaEdit = entrada.nextLine();
                            in.nextLine();
                            editaSenha(usuarios, senhaEdit, buscaLogin);
                            break;
                        case 2:
                            System.out.println(ANSI_YELLOW + "Informe o seu novo nome: " + ANSI_RESET);
                            String nomeEdit = entrada.nextLine();
                            in.nextLine();
                            editaNome(usuarios, nomeEdit, buscaLogin);
                            break;
                        case 3:
                            System.out.println(ANSI_YELLOW + "Adicione coisas que você julgar importante para a sua biografia: " + ANSI_RESET);
                            System.out.println(ANSI_YELLOW + "EXEMPLOS: Apelidos, GitHub, Hobbies e outros" + ANSI_RESET);
                            String bio = input.nextLine();
                            in.nextLine();
                            adicionaBio(usuarios, bio, buscaLogin);
                            break;
                        case 4:
                            System.out.println(ANSI_GREEN + "RETORNANDO AO MENU..." + ANSI_RESET);
                            break;
                        default:
                            System.err.println("COMANDO INVÁLIDO!");
                    }

                } while (op != 4);

            } else {
                System.err.println("SENHA INVÁLIDA!");
            }
        } else {
            System.err.println("LOGIN INVÁLIDO!");
        }
    }

    protected void enviarSolicitação(List<Usuario> usuarios) {
        Scanner in = new Scanner(System.in);
        System.out.println(ANSI_YELLOW + "Informe seu login: " + ANSI_RESET);
        String meuLogin = in.nextLine();
        if (retornaUsuario(meuLogin, usuarios) == null) {
            System.err.println("LOGIN INVÁLIDO!");
        } else {
            System.out.println(ANSI_YELLOW + "Informe o login do usuario que vai receber um convite:" + ANSI_RESET);
            String login = in.nextLine();
            Usuario conta = c.buscaUsuarioLogin(login, usuarios);
            if (conta != null) {
                if (conta == retornaUsuario(meuLogin, usuarios)) {
                    System.err.println("VOCÊ NÃO PODE ADICINAR A SI MESMO!");
                } else {
                    ArrayList<Usuario> amigosP = conta.getAmigosPendentes();
                    amigosP.add(retornaUsuario(meuLogin, usuarios));
                    System.out.println(ANSI_GREEN + "CONVITE ENVIADO COM SUCESSO!" + ANSI_RESET);
                    Thread fluxo = new Thread(retornaUsuario(meuLogin, usuarios));
                    fluxo.start();
                }
            } else {
                System.err.println("LOGIN INVÁLIDO!");
            }

        }
    }

    protected void aceitarSolicitaçao(Usuario usuario, List<Usuario> usuarios) {
        Scanner in = new Scanner(System.in);
        System.out.println(ANSI_YELLOW + "Informe seu login: " + ANSI_RESET);
        String meuLogin = in.nextLine();
        if (retornaUsuario(meuLogin, usuarios) == null) {
            System.err.println("LOGIN INVÁLIDO!");
        } else {
            ArrayList<Usuario> amigosP = retornaUsuario(meuLogin, usuarios).getAmigosPendentes();
            System.out.println(ANSI_GREEN + ANSI_WHITE_BACKGROUND + "SOLICITAÇÕES" + ANSI_RESET);
            System.out.println(amigosP.size());
            System.out.println(ANSI_YELLOW + "Digite o login do usuário que vai receber um convite: " + ANSI_RESET);
            System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
            String login = in.nextLine();
            Usuario amigoEmPotencial = c.buscaUsuarioLogin(login, amigosP);
            if (amigoEmPotencial != null) {
                amigos = retornaUsuario(meuLogin, usuarios).getAmigos();
                amigos.add(amigoEmPotencial);
                amigosP.remove(amigoEmPotencial);
                amigoEmPotencial.getAmigos().add(retornaUsuario(meuLogin, usuarios));
                System.out.println(ANSI_GREEN + "SOLICITAÇÃO ACEITA!" + ANSI_RESET);
                System.err.println("CONVITE REMOVIDO!");
                Thread fluxo = new Thread(retornaUsuario(meuLogin, usuarios));
                Thread fluxoAmigo = new Thread(amigoEmPotencial);
                fluxo.start();
                fluxoAmigo.start();
            } else {
                System.err.println("LOGIN INVÁLIDO!");
            }

        }
    }

    protected void enviaRecados(List<Usuario> usuarios) {
        Scanner in = new Scanner(System.in);
        System.out.println(ANSI_YELLOW + "Informe seu login: " + ANSI_RESET);
        String meuLogin = in.nextLine();
        if (retornaUsuario(meuLogin, usuarios) == null) {
            System.err.println("LOGIN INVÁLIDO!");
        } else {
            System.out.println(ANSI_YELLOW + "Informe o login do usuário que vai receber a mensagem:" + ANSI_RESET);
            System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
            String login = in.nextLine();
            Usuario conta = c.buscaUsuarioLogin(login, usuarios);
            if (conta == null) {
                System.err.println("LOGIN INVÁLIDO!");
            } else if (conta == retornaUsuario(meuLogin, usuarios)) {
                System.err.println("VOCÊ NÃO PODE ENVIAR MENSAGEM A SI MESMO!");
            } else {
                System.out.println(ANSI_BLUE + ANSI_WHITE_BACKGROUND + "INFORME O TÓPICO DA CONVERSA: " + ANSI_RESET);
                System.out.println(ANSI_BLUE + "1 - JOGOS" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "2 - SAUDAÇÕES" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "3 - SÉRIES" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "4 - DIVERSOS " + ANSI_RESET);
                System.out.println(ANSI_BLUE + "0 - MENU" + ANSI_RESET);
                System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
                int op = in.nextInt();
                switch (op) {
                    case 1:
                        System.out.println(ANSI_YELLOW + "Você escolheu o tópico jogos!" + ANSI_RESET);
                        System.out.println(ANSI_GREEN + "Informe o recado que você deseja enviar ao login " + ANSI_RESET + login);
                        in.nextLine();
                        System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
                        String recadoJogos = in.nextLine();
                        ArrayList<Recado> recadosJogos = conta.getRecados();
                        Recado mensagemJogos = new Recado(recadoJogos, retornaUsuario(meuLogin, usuarios).getLogin(), "Jogos");
                        recadosJogos.add(mensagemJogos);
                        System.out.println(ANSI_GREEN + "MENSAGEM ENVIADA!" + ANSI_RESET);
                        System.out.println(ANSI_YELLOW + "TÓPICO: JOGOS." + ANSI_RESET);
                        break;
                    case 2:
                        System.out.println(ANSI_YELLOW + "Você escolheu o tópico saudações!" + ANSI_RESET);
                        System.out.println(ANSI_GREEN + "Informe o recado que você deseja enviar ao login " + ANSI_RESET + login);
                        in.nextLine();
                        System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
                        String recadoSaudaçao = in.nextLine();
                        ArrayList<Recado> recadosSaudaçao = conta.getRecados();
                        Recado mensagemSaudaçao = new Recado(recadoSaudaçao, retornaUsuario(meuLogin, usuarios).getLogin(), "Saudação");
                        recadosSaudaçao.add(mensagemSaudaçao);
                        System.out.println(ANSI_GREEN + "MENSAGEM ENVIADA!" + ANSI_RESET);
                        System.out.println(ANSI_YELLOW + "TÓPICO: SAUDAÇÕES." + ANSI_RESET);
                        break;
                    case 3:
                        System.out.println(ANSI_YELLOW + "Você escolheu o tópico séries!" + ANSI_RESET);
                        System.out.println(ANSI_GREEN + "Informe o recado que você deseja enviar ao login " + ANSI_RESET + login);
                        in.nextLine();
                        System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
                        String recadoSeries = in.nextLine();
                        ArrayList<Recado> recadosSeries = conta.getRecados();
                        Recado mensagemSeries = new Recado(recadoSeries, retornaUsuario(meuLogin, usuarios).getLogin(), "Séries");
                        recadosSeries.add(mensagemSeries);
                        System.out.println(ANSI_GREEN + "MENSAGEM ENVIADA!" + ANSI_RESET);
                        System.out.println(ANSI_YELLOW + "TÓPICO: SÉRIES." + ANSI_RESET);
                        break;
                    case 4:
                        System.out.println(ANSI_YELLOW + "Você escolheu o tópico diversos!" + ANSI_RESET);
                        System.out.println(ANSI_GREEN + "Informe o recado que você deseja enviar ao login " + ANSI_RESET + login);
                        in.nextLine();
                        System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
                        String recadoDiverso = in.nextLine();
                        ArrayList<Recado> recadosDiverso = conta.getRecados();
                        Recado mensagemDiverso = new Recado(recadoDiverso, retornaUsuario(meuLogin, usuarios).getLogin(), "Diversos");
                        recadosDiverso.add(mensagemDiverso);
                        System.out.println(ANSI_GREEN + "MENSAGEM ENVIADA!" + ANSI_RESET);
                        System.out.println(ANSI_YELLOW + "TÓPICO: DIVERSOS." + ANSI_RESET);
                        break;
                    case 0:
                        System.out.println(ANSI_GREEN + "RETORNANDO AO MENU..." + ANSI_RESET);
                        break;
                    default:
                        System.err.println("TÓPICO INEXISTENTE!");
                        break;

                }

            }
        }
    }

    public void enviarMatch(List<Usuario> usuarios) {
        Scanner in = new Scanner(System.in);
        System.out.println(ANSI_YELLOW + "Informe seu login: " + ANSI_RESET);
        String meuLogin = in.nextLine();
        if (retornaUsuario(meuLogin, usuarios) == null) {
            System.err.println("LOGIN INVÁLIDO!");
        } else {
            System.out.println(ANSI_GREEN + "Informe o login do usuario que você deseja dar um MATCH:" + ANSI_RESET);
            System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
            String login = in.nextLine();
            Usuario conta = c.buscaUsuarioLogin(login, usuarios);
            ArrayList<Usuario> matchh = retornaUsuario(meuLogin, usuarios).getMatchPendentes();
            if (conta == null) {
                System.err.println("LOGIN INEXISTENTE!");
            } else {
                if (!matchPendentes.contains(retornaUsuario(meuLogin, usuarios)) && !matchh.contains(conta)) {
                    ArrayList<Usuario> matchPendentes = conta.getMatchPendentes();
                    matchPendentes.add(retornaUsuario(meuLogin, usuarios));
                    System.out.println(ANSI_GREEN + "VOCE CURTIU ESTE PERFIL!" + ANSI_RESET);
                } else {
                    ArrayList<Usuario> matchAdiciona = conta.getMatch();
                    matchAdiciona.add(retornaUsuario(meuLogin, usuarios));
                    retornaUsuario(meuLogin, usuarios).getMatch().add(conta);
                    matchPendentes.remove(retornaUsuario(meuLogin, usuarios));
                    System.out.println(ANSI_GREEN + ANSI_WHITE_BACKGROUND + "MATCH!" + ANSI_RESET);
                }
            }
        }

    }

    protected void enviaMensagemMural(List<Usuario> usuarios) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(ANSI_YELLOW + "Informe seu login: " + ANSI_RESET);
        String meuLogin = entrada.nextLine();
        if (retornaUsuario(meuLogin, usuarios) == null) {
            System.err.println("LOGIN INVÁLIDO!");
        } else {
            System.out.println(ANSI_GREEN + "Informe o login do usuário que vai receber um mural: " + ANSI_RESET);
            System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
            String login = entrada.nextLine();
            Usuario conta = c.buscaUsuarioLogin(login, usuarios);
            if (conta != null) {
                System.out.println(ANSI_YELLOW + "OBS: Esta mensagem irá ser exibida no mural, caso o destinatário aceite-a." + ANSI_RESET);
                System.out.println(ANSI_GREEN + "Informe a mensagem que deseja enviar para o usuário: " + ANSI_RESET);
                System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
                String msgMural = entrada.nextLine();
                ArrayList<Recado> enviaMsgMural = conta.getMsgMuralPendente();
                Recado rec = new Recado(msgMural, retornaUsuario(meuLogin, usuarios).getLogin());
                enviaMsgMural.add(rec);
                System.out.println(ANSI_GREEN + "MENSAGEM ENVIADA!" + ANSI_RESET);
                System.out.println(rec);
            } else {
                System.err.println("LOGIN INVÁLIDO!");
            }
        }
    }

    protected void aceitaMensagemMural(Usuario usuario, List<Usuario> usuarios, Usuario mural) {
        Scanner in = new Scanner(System.in);
        System.out.println(ANSI_YELLOW + "Informe seu login: " + ANSI_RESET);
        String meuLogin = in.nextLine();
        if (retornaUsuario(meuLogin, usuarios) == null) {
            System.err.println("LOGIN INVÁLIDO!");
        } else {
            ArrayList<Recado> mensagensP = retornaUsuario(meuLogin, usuarios).getMsgMuralPendente();
            System.out.println(ANSI_GREEN + ANSI_WHITE_BACKGROUND + "MENSAGENS:" + ANSI_RESET);
            System.out.println(retornaUsuario(meuLogin, usuarios).getMsgMuralPendente());
            System.out.println(ANSI_YELLOW + "OBS: Se essa mensagem for aceita, ela irá para o seu mural" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "Informe o login do usuário que lhe enviou um mural: " + ANSI_RESET);
            System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
            String login = in.nextLine();
            Recado conta = d.buscaUsuarioRecado(login, mensagensP);
            if (conta == null) {
                System.err.println("LOGIN INVÁLIDO!");
            } else {
                ArrayList<Recado> enviaMural = mural.getMsgMural();
                System.out.println(enviaMural);
                enviaMural.add(conta);
                mensagensP.remove(conta);
                System.out.println(ANSI_GREEN + "Mensagem enviada ao mural!" + ANSI_RESET);
            }
        }
    }

    protected void enviaRecadosSecretos(List<Usuario> usuarios) {
        Scanner in = new Scanner(System.in);
        System.out.println(ANSI_YELLOW + "Informe seu login: " + ANSI_RESET);
        String meuLogin = in.nextLine();
        if (retornaUsuario(meuLogin, usuarios) == null) {
            System.err.println("LOGIN INVÁLIDO!");
        } else {
            System.out.println(ANSI_GREEN + "Informe o login do usuário que vai receber uma mensagem: " + ANSI_RESET);
            String login = in.nextLine();
            Usuario conta = c.buscaUsuarioLogin(login, usuarios);
            if (conta != null) {
                System.out.println(ANSI_GREEN + "Informe o recado que você deseja enviar: " + ANSI_RESET);
                System.out.print(ANSI_WHITE + "->" + ANSI_RESET);
                String recadoSecreto = in.nextLine();
                System.out.println(ANSI_GREEN + "Digite uma senha para criptografar esta mensagem: " + ANSI_RESET);
                String senhaRecado = in.nextLine();
                ArrayList<RecadoSecreto> recSec = conta.getRecadoSecreto();
                System.out.println(ANSI_GREEN + "Recado criptografado e enviado com sucesso! " + ANSI_RESET);
                RecadoSecreto rec = new RecadoSecreto(recadoSecreto, meuLogin, senhaRecado);
                recSec.add(rec);
                System.out.println(recSec);
            } else {
                System.err.println("USUÁRIO INVÁLIDO, TENTE NOVAMENTE");
            }
        }
    }

    protected void exibeRecadosSecretos(List<RecadoSecreto> recadosSec, List<Usuario> usuarios) {
        Scanner in = new Scanner(System.in);
        System.out.println(ANSI_YELLOW + "Informe seu login: " + ANSI_RESET);
        String meuLogin = in.nextLine();
        if (retornaUsuario(meuLogin, usuarios) == null) {
            System.err.println("LOGIN INVÁLIDO!");
        } else {
            recadosSec = retornaUsuario(meuLogin, usuarios).getRecadoSecreto();
            String senhaRecSecreto;
            System.out.println(ANSI_YELLOW + "Digite a senha para abrir a mensagem: " + ANSI_RESET);
            senhaRecSecreto = in.nextLine();
            RecadoSecreto recadoSecreto = d.buscaSenhaSecreta(senhaRecSecreto, recadosSec);
            if (recadoSecreto != null) {
                System.out.println(retornaUsuario(meuLogin, usuarios).getRecadoSecreto());
            } else {
                System.err.println("SENHA INCORRETA, TENTE NOVAMENTE");
            }

        }
    }

    public Usuario retornaUsuario(String login, List<Usuario> usuarios) {
        Usuario buscaLogin = c.buscaUsuarioLogin(login, usuarios);
        return buscaLogin;
    }
}
