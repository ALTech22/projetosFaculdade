package pi;

public class Professor extends Pessoa{
    private int ID;


    public Professor(int ID, String nome) {
        this.ID = ID;
        this.nome = nome;
    }

    public int getID() {
        return ID;
    }

}
