package pi;

import java.util.ArrayList;
import java.util.Scanner;

public class Escola {
    private ArrayList<Aluno> alunos = new ArrayList<Aluno>();
    private AlunoDAO alunodao;

    private Scanner teclado;

    public Escola(Scanner teclado) {
        this.teclado = teclado;
        
        alunodao = new AlunoDAO();
        
        alunos = alunodao.listar();

    }


    public void CriarAluno() {
        Aluno aluno = new Aluno();
        aluno = PerguntarInformacoesAluno(aluno);
        if (aluno == null) {
            System.out.println("Falha ao cadastrar aluno");
        } else {
            AtribuirIdentificadorAoAluno(aluno);
            if(alunodao.inserir(aluno)) {
            	System.out.println("Salvo no banco de dados");
            }else {
            	System.out.println("Erro ao salvar no banco de dados");
            }
        }
        alunos = alunodao.listar();
        
        
    }
    public void ListarAlunos() {
        for (int i = 0; i < alunos.size(); i++) {
            alunos.get(i).PrintarInformacoes();
        }
    }
    public void PesquisarAluno() {
        System.out.println("Digite o nome do estudante para pesquisar: ");
        String nomeAluno = teclado.nextLine();

        Aluno aluno = BuscarAlunoPorNome(nomeAluno);
        if (aluno != null) {
            aluno.PrintarInformacoes();
        } else {
            System.out.println("Estudante não localizado");
        }
    }
    public void AtualizarAluno() {
        System.out.println("Digite o ID do aluno para atualizar: ");
        String id = teclado.nextLine();

        int asciiId = id.charAt(0);
        if(asciiId < 48 || asciiId > 57 ) {
            System.out.println("Por favor, digite um número");
            return;
        }

        int idAluno = Integer.parseInt(id);
        Aluno aluno = BuscarAlunoPorID(idAluno);
        if (aluno == null) {
            System.out.println("Aluno não encontrado");
            return;
        }

        Aluno alunoAtualizado = PerguntarInformacoesAluno(aluno);
        if (alunoAtualizado == null) {
            System.out.println("Falha ao atualizar aluno");
            return;
        }
        alunodao.atualizar(idAluno, alunoAtualizado);

        alunos = alunodao.listar();
    }
    public void RemoverAluno() {
        System.out.println("Entre com o ID");
        String idRemove = teclado.nextLine();

        int asciiIdRemove = idRemove.charAt(0);
        if(asciiIdRemove < 48 || asciiIdRemove > 57 ) {
            System.out.println("Por favor, digite um número");
            return;
        }

        int idAluno = Integer.parseInt(idRemove);
        Aluno aluno = BuscarAlunoPorID(idAluno);
        
        if (aluno == null) {
            System.out.println("Aluno não encontrado");
            return;
        }

        int indexAluno = alunos.indexOf(aluno);
        alunos.remove(indexAluno);
        alunodao.remover(idAluno);
        alunos = alunodao.listar();
    }

    private Aluno PerguntarInformacoesAluno(Aluno aluno) {
        System.out.println("Digite o nome do estudante: ");
        String nomeAluno = teclado.nextLine();

        System.out.println("Digite a data de nascimento do estudante: ");
        String dataNascimento = teclado.nextLine();

        System.out.println("Digite o CPF do estudante: ");
        String cpfAluno = teclado.nextLine();

        System.out.println("Digite o ID do professor que vai ser tutor deste aluno: ");
        Integer tutorID = Integer.valueOf(teclado.nextLine());
       
        System.out.println("Digite o ID do departamento: ");
        Integer departamentoID = Integer.valueOf(teclado.nextLine());
       
        aluno.setDisciplina(registrarDisciplinas());
        aluno.setNome(nomeAluno);
        aluno.setDataNascimento(dataNascimento);
        aluno.setCpf(cpfAluno);
        aluno.setDepartamento(new Departamento(departamentoID, null));
        aluno.setTutor(new Professor(tutorID, null));



        return aluno;
    }
    private void AtribuirIdentificadorAoAluno(Aluno aluno) {
        int novoID;

        if (alunos.size() > 0) {
            int ultimoID = alunos.get(alunos.size() - 1).getID();
            novoID = ultimoID + 1;
        } else {
            novoID = 0;
        }

        aluno.setID(novoID);
    }
   
    private ArrayList<Disciplina> registrarDisciplinas(){
    	ArrayList<Disciplina> disc = new ArrayList<Disciplina>();
    	int opc = 1;
    	do {
    		System.out.println("=====registro de disciplina=====");
    		System.out.println("Digite (0) para sair");
    		System.out.println("Digite (1) para registrar uma disciplina");
    		opc = Integer.parseInt(teclado.nextLine());
    		switch(opc) {
    		case 0:
    			if(disc.isEmpty()) {
    				System.out.println("Por favor, registre pelo menos uma disciplina");
    				opc = 1;
    			}
    		break;
    		case 1:
    			System.out.println("=====Digite o ID da disciplina=====");
    			Integer idDisciplina;
    			idDisciplina = Integer.parseInt(teclado.nextLine());
    			disc.add(new Disciplina(idDisciplina, null));
    			break;
    		}
    		
    	}while(opc == 1 && disc.size() != 6);
    	
    	return disc;
    }

    private Aluno BuscarAlunoPorID(int ID) {
        Aluno aluno = null;

        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getID() == ID) {
                aluno = alunos.get(i);
                break;
            }
        }

        return aluno;
    }
    private Aluno BuscarAlunoPorNome(String nome) {
        Aluno aluno = null;

        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getNome().equals(nome)) {
                aluno = alunos.get(i);
                break;
            }
        }

        return aluno;
    }

}
