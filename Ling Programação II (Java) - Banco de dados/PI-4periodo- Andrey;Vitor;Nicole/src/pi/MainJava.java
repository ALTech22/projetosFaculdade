package pi;

import java.util.Scanner;

public class MainJava {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Escola escola = new Escola(teclado);

        int opcao;
        do {
            System.out.println("Cadastro de Estudantes");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Pesquisar");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Remover");
            System.out.println("6 - Sair");
            opcao = Integer.parseInt(teclado.nextLine());

            switch(opcao) {
                case 1:
                    escola.CriarAluno();
                    break;
                case 2:
                    escola.ListarAlunos();
                    break;
                case 3:
                    escola.PesquisarAluno();
                    break;
                case 4:
                    escola.AtualizarAluno();
                    break;
                case 5:
                    escola.RemoverAluno();
                    break;
                case 6:
                    System.out.println("Até a próximaa! :D");
                    break;
                default :
                    System.out.println("Opção inválida");
                    break;
            }
        } while(opcao != 6);
    }
}
