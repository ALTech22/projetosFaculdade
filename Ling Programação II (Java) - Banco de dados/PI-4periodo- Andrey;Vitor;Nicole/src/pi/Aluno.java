package pi;

import java.util.ArrayList;

public class Aluno extends Pessoa{
    private Integer ID;
    private String dataNascimento;
    private Professor tutor;
    private ArrayList<Disciplina> disciplinas;
    private Departamento departamento;

    public Aluno() {
        
    }

    public int getID() {
        return ID;
    }

    public Departamento getDepartamento() {
    	return departamento;
    }
    public Professor getTutor() {
    	return tutor;
    }

    public String getDataNascimento() {
    	return dataNascimento;
    }
    public ArrayList<Disciplina> getDisciplina(){
    	return disciplinas;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setTutor(Professor tutor) {
        this.tutor = tutor;
    }
    public void setDisciplina(ArrayList<Disciplina> disciplina) {
        disciplinas = disciplina;
    }
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public void PrintarInformacoes() {
       String strDisciplina = "";

        for (int i = 0; i < disciplinas.size(); i++) {
        	if(i == (disciplinas.size() - 1))
        		strDisciplina += disciplinas.get(i).getNome();
        	else
        		strDisciplina += disciplinas.get(i).getNome() + ", ";
        }

        System.out.println(
        	"========================================="
        	+ "\nO ID do estudante é: " + ID
            + "\n- Nome: " + nome
            + "\n- Data de Nascimento: " + dataNascimento
            + "\n- CPF: " + cpf
            + "\n- Tutor: " + tutor.getNome()
            + "\n- Disciplinas: " + strDisciplina
            + "\n- Departamento: " + departamento.getNome()
            + "\n========================================="
        );
    }
}
