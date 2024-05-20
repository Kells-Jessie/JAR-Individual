import com.github.britooo.looca.api.group.sistema.Sistema;
import maquina.Limite;
import maquina.Maquina;
import maquina.Registro;
import usuario.Usuario;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Integer opcao;
        do {
            System.out.printf("""
                    *------------------------------------*
                    | Olá, Seja bem-vindo(a) a Life Line!|
                    *------------------------------------*
                    | Digite o que deseja realizar!      |
                    |                                    |
                    | 0 - Encerrar App                   |
                    | 1 - Login                          |
                    *------------------------------------* 
                    """);
            opcao = leitor.nextInt();

            if (opcao == 0) {
                System.out.printf("Encerrando...");
            } else if (opcao == 1) {
                Boolean usuarioLogado = false;
                Usuario usuario = login();

                do {
                    if (usuario.getIdUsuario() != null) {
                        usuarioLogado = true;
                    } else {
                        usuario = login();
                    }
                } while (!usuarioLogado); // Login bem sucedido

                maquina(usuario); // Pos login
            } else {
                System.out.println("Digite uma opção válida!");
            }
        } while (opcao != 1 && opcao != 0);
    }

    private static Usuario login() {
        Scanner leitor = new Scanner(System.in);
            System.out.printf("""
                *------------------------------------*
                |        Login - Life Line           |
                *------------------------------------*
                |Digite o seu email:                 |
                *------------------------------------*
                """
            );
            String email = leitor.next();
            System.out.printf("""
                *------------------------------------*
                |Digite a sua senha:                 |
                *------------------------------------*
                """
            );
            String senha = leitor.next();
            return new Usuario(email, senha);
    }

    private static void maquina(Usuario usuario) {
        Scanner leitor = new Scanner(System.in);
        Maquina maquinaUsuario = new Maquina();
        System.out.println("""
        *------------------------------------*
        |                Menu                |
        *------------------------------------*
        | 1 - Registros                      |
        | 2 - Características                |
        | 3 - Tempo de uso                   |
        *------------------------------------*""");
        Integer escolha = leitor.nextInt();
        if (escolha.equals(1)) {
            maquinaUsuario.verificarMaquina(usuario.getIdUsuario());
        } else if (escolha.equals(2)) {
            System.out.println(maquinaUsuario.toString());
        } else if (escolha.equals(3)) {
//            System.out.println(maquinaUsuario.mostrarTempoDeUso());
            maquinaUsuario.mostrarTempoDeUso();
        }
        else{
            System.out.println("valor inválido");
        };
    }
}
