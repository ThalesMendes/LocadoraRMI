import java.net.MalformedURLException;
import java.rmi.*;

public class Cliente {

    //identificador do cliente
    private int ID;
    //nome do cliente
    private String nome;
    //quanto de debito o cliente tem
    private int debito;
    //se o cliente esta com um carro alugado
    private boolean alocado;

    public Cliente(int ID, String nome) {
        this.ID = ID;
        this.nome = nome;
        this.debito = 0;
        this.alocado = false;
    }

    //retorna o ID do cliente
    public int getID() {
        return ID;
    }

    //define o ID do cliente
    public void setID(int ID) {
        this.ID = ID;
    }

    //retorna o nome do cliente
    public String getNome() {
        return nome;
    }

    //define o nome do cliente
    public void setNome(String nome) {
        this.nome = nome;
    }

    //retorna o debito do cliente
    public int getDebito() {
        return debito;
    }

    //define o debito do cliente
    public void setDebito(int debito) {
        this.debito = debito;
    }

    //retorna se o cliente esta com um carro alugado
    public boolean isAlocado() { return alocado; }

    //define se o cliente esta ou nao com um carro alugado
    public void setAlocado(boolean alocado) {
        this.alocado = alocado;
    }
    
    public static void main(String[] args) { 
        try { 
            Cliente c = (Cliente) Naming.lookup("rmi://localhost/FilialService");  
        } 
        catch (MalformedURLException murle) { 
            System.out.println(); 
            System.out.println(
              "MalformedURLException"); 
            System.out.println(murle); 
        } 
        catch (RemoteException re) { 
            System.out.println(); 
            System.out.println(
                        "RemoteException"); 
            System.out.println(re); 
        } 
        catch (NotBoundException nbe) { 
            System.out.println(); 
            System.out.println(
                       "NotBoundException"); 
            System.out.println(nbe); 
        } 
        catch (
            java.lang.ArithmeticException
                                      ae) { 
            System.out.println(); 
            System.out.println(
             "java.lang.ArithmeticException"); 
            System.out.println(ae); 
        } 
    }    
}

