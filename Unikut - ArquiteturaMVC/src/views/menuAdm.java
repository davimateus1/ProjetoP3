
package views;

import java.util.List;
import java.util.Scanner;
import models.Usuario;
import static views.cores.*;


public class menuAdm {
    
    admView acesso = new admView();
    
    public void contaAdmAcessada(Usuario conta, List<Usuario> usuarios){
        Scanner in = new Scanner(System.in);
        int op;
        String meuLogin = conta.getLogin();
        System.out.println(meuLogin);
        do {
            menuAdm();
            op = in.nextInt();
            switch (op) {
                case 1:
                    acesso.alterarUsuario(usuarios);
                    break;
                case 2:
                    acesso.removeConta(usuarios);
                    break;
                case 3:
                    acesso.enviarSolicitação(usuarios);
                    break;
                case 4:
                    acesso.aceitarSolicitaçao(conta, usuarios);
                    break;
                case 5:
                    acesso.enviaRecados(usuarios);
                    break;
                case 6:
                    acesso.exibeConversa(usuarios);
                    break;
                case 0:
                    System.out.println(ANSI_GREEN + "RETORANDO AO MENU..." + ANSI_RESET);
                    break;

            }
        } while (op != 0);
    }
    public void menuAdm() {
        System.out.println(ANSI_BLUE + "1 - ALTERAR UM USUÁRIO" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "2 - EXCLUIR UM USUÁRIO DO SISTEMA" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "3 - ADICIONAR AMIGOS" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "4 - ACEITAR SOLICITAÇÕES DE AMIZADE" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "5 - ENVIAR RECADOS" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "6 - EXIBIR RECADOS" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "0 - ENCERRAR SESSÃO" + ANSI_RESET);
    }

}

