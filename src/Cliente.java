import java.rmi.*;

public class Cliente {

    private int ID;
    private String nome;
    private int debito;
    private boolean alocado;

    public Cliente(int ID, String nome) {
        this.ID = ID;
        this.nome = nome;
        this.debito = 0;
        this.alocado = false;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDebito() {
        return debito;
    }

    public void setDebito(int debito) {
        this.debito = debito;
    }

    public boolean isAlocado() {
        return alocado;
    }

    public void setAlocado(boolean alocado) {
        this.alocado = alocado;
    }
}