import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

public class LocadoraImpl extends UnicastRemoteObject implements Locadora {

    private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    public LocadoraImpl() throws RemoteException {
        super();
    }

    @Override
    public void cadastarCliente(Cliente cliente) throws RemoteException {
        Random rand = new Random();
        int numero = rand.nextInt(1000);
        if(numero < 0)
            numero*=-1;
        cliente.setNumero(numero);
        clientes.add(cliente);
        System.out.println("CLIENTE CADASTRADO NA LOCADORA");
    }

    @Override
    public void teste(){
        System.out.println("THIS IS A TEST");
    }

    @Override
    public void listarClientes(){
        for(Cliente x: this.clientes){
            System.out.println(x.getNome() +"\t" +x.getNumero());
        }
    }

    @Override
    public void alugarVeiculo(Cliente cliente){
        if(cliente.getDebito() == 0){
            cliente.setDebito(cliente.getDebito()+1);
            System.out.println("VEICULO ALUGADO");
        }
        else{
            System.out.println("ESSE CLIENTE TEM DEBITO COM A LOCADORA AINDA");
        }
    }

    @Override
    public void devolverVeiculo(Cliente cliente){
        if(cliente.getDebito() > 0){
            cliente.setDebito(cliente.getDebito()-1);
            System.out.println("VEICULO DEVOLVIDO");
        }
        else{
            System.out.println("ESSE CLIENTE NAO TEM DEBITO COM A LOCADORA AINDA");
        }
    }
}