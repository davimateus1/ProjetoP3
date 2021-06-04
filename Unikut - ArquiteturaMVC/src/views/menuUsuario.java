
package views;
import java.util.List;
import java.util.Scanner;
import models.Recado;
import models.RecadoSecreto;
import models.Usuario;
import static views.usuarioView.menuUsuario;
import static views.cores.*;


public class menuUsuario extends Usuario {
public menuUsuario(){
    super();
}
    usuarioView acesso = new usuarioView();
    
    public void contaAcessada(Usuario logado, List<Usuario> usuarios, Usuario mural, List<RecadoSecreto> recadosSec, List<Recado> recados) {
        Scanner in = new Scanner(System.in);
        int op;
        String meuLogin = logado.getLogin();
        System.out.println(ANSI_YELLOW + ANSI_WHITE_BACKGROUND + "Perfil de: " + ANSI_RESET + meuLogin);
        do {
            menuUsuario();
            op = in.nextInt();
            switch (op) {
                case 1:
                    acesso.alterarDados(usuarios);
                    break;
                case 2:
                    acesso.enviarSolicitação(usuarios);
                    break;
                case 3:
                    acesso.aceitarSolicitaçao(logado, usuarios);
                    break;
                case 4:
                    acesso.enviaRecados(usuarios);
                    break;
                case 5:
                    acesso.exibeConversa(usuarios);
                    break;
                case 6:
                    acesso.enviaMensagemMural(usuarios);
                    break;
                case 7:
                    acesso.aceitaMensagemMural(logado, usuarios, mural);
                    break;
                case 8:
                    acesso.exibeMural(mural);
                    break;
                case 9:
                    acesso.enviaRecadosSecretos(usuarios);
                    break;
                case 10:
                    acesso.exibeRecadosSecretos(recadosSec, usuarios);
                    break;
                case 11:
                    acesso.enviarMatch(usuarios);
                    break;
            }
        } while (op != 0);
    }
}

