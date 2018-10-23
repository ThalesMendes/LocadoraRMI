import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Filial extends UnicastRemoteObject implements Locadora {

    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private Random random = new Random();
    private static int numCarros;

    public Filial(int num) throws RemoteException {
        this.numCarros = num;
    }

    public static int getNumCarros() {
        return numCarros;
    }

    public static void setNumCarros(int numCarros) {
        Filial.numCarros = numCarros;
    }

    public void criarCliente() {

        int id;
        int flag = 0;

        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();

        while(true){
            id = random.nextInt(1000);
            for (Cliente cID : clientes) {
                if (cID.getID() != id)
                  flag ++;
            }
            if(flag == clientes.size())
                break;
        }
        Cliente novo = new Cliente(id, nome);
        clientes.add(novo);
    }

    public void locacao(Cliente c) {
        if(c.getDebito() != 0 && c.isAlocado() && getNumCarros() > 0)
            return;
        else
        {
            c.setAlocado(true);
            c.setDebito(c.getDebito() + 1);
            setNumCarros(getNumCarros() - 1);
        }
    }

    public void devolucao(Cliente c) {
        c.setAlocado(false);
        setNumCarros(getNumCarros() + 1);
    }

    public void pagamento(Cliente c) {
        c.setDebito(c.getDebito() - 1);
    }
}