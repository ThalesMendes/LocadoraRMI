import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Filial extends UnicastRemoteObject implements Locadora {

    //lista de clientes
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    //numero de carros que podem ser alugados
    private static int numCarros;

    //usado para gerar o ID do cliente
    private Random random = new Random();

    public Filial(int num) throws RemoteException {
        this.numCarros = num;
       
    }
    public Filial() throws RemoteException {
        try {
          Filial filial = new Filial(50);
          Naming.rebind("rmi://localhost:1099/FilialService", filial);
        } catch (Exception e) {
            System.out.println("Exeção: " + e);
        }
   }
    
   //retorna o numero de carros que podem ser alugados
    public static int getNumCarros() {
        return numCarros;
    }

    //define o numero de carros que podem ser alugados
    public static void setNumCarros(int numCarros) {
        Filial.numCarros = numCarros;
    }


    //cria um cliente
    @Override
    public void criarCliente() throws RemoteException {

        int id;
        //funciona como um contador
        int cont = 0;

        Scanner scanner = new Scanner(System.in);
        //recebe o nome do cliente que é lido do teclado
        String nome = scanner.nextLine();

        while(true){
            //gera um id aleatorio
            id = random.nextInt(1000);
            //percorre os clientes vendo se o ID já foi dado a um cliente
            for (Cliente cID : clientes) {
                if (cID.getID() != id)
                  cont ++;
            }
            //se nennhum cliente tem o ID sai do while, se não refaz o while com outro id aleatorio
            if(cont == clientes.size())
                break;
        }

        Cliente novo = new Cliente(id, nome);
        clientes.add(novo);
    }

    //faz a locação de um veiculo
    @Override
    public void locacao(Cliente c) throws RemoteException{
        //se o cliente  tiver com debito, tiver locado um carro ou não existe carros para serem emprestados sai da função
        if(c.getDebito() != 0 && c.isAlocado() && getNumCarros() > 0)
            return;
        //se nao houverem pendencias aceita a locação
        else
        {
            //define o alocado para verdadeiro
            c.setAlocado(true);
            //aumenta o debito do cliente
            c.setDebito(c.getDebito() + 1);
            //diminui o numero de carros que podem ser emprestados
            setNumCarros(getNumCarros() - 1);
        }
    }

    //faz a devolucao de um veiculo
    public void devolucao(Cliente c) throws RemoteException{
        //define o alocado para falso
        c.setAlocado(false);
        //aumenta o numero de carros que podem ser emprestados
        setNumCarros(getNumCarros() + 1);
    }

    //faz o pagamento do debito
    public void pagamento(Cliente c) throws RemoteException {
        c.setDebito(c.getDebito() - 1);
    }
    

   public static void main(String args[]) throws RemoteException {
     new Filial();
   }
}