package pi;

public class Disciplina {
    private int ID;
    private String nome;

    public Disciplina(int ID, String nome) {
        this.ID = ID;
        this.nome = nome;
    }

    public int getID() {
        return ID;
    }
    public String getNome() {
        return nome;
    }
}
