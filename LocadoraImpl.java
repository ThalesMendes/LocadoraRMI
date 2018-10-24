import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

public class LocadoraImpl extends UnicastRemoteObject implements Locadora {
	
	//Array que guarda todos os clientes dessa locadora
    private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    public LocadoraImpl() throws RemoteException {
        super();
    }

	//cadastra os cliente existentes na locadora
    @Override
    public void cadastarCliente(Cliente cliente) throws RemoteException {
        Random rand = new Random();
		//Id aleatorio do cliente que é cadastrado nessa locadora
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

	//lista todos os clientes cadastrados
    @Override
    public void listarClientes(){
        for(Cliente x: this.clientes){
            System.out.println(x.getNome() +"\t" +x.getNumero());
        }
    }
	
	//Essa função recebe um Cliente da aplicacao cliente e aluga um veiculo em seu nome, caso ele não tenha débito    
	@Override
    public void alugarVeiculo(Cliente cliente){
		if(clientes.size() != 0){
			Cliente clienteSelecionado = this.clientes.get(cliente.getId());
		    if(clienteSelecionado.getDebito() == 0){
		        clienteSelecionado.setDebito(clienteSelecionado.getDebito()+1);
		        System.out.println("VEICULO ALUGADO");
		    }
		    else{
		        System.out.println("ESSE CLIENTE TEM DEBITO COM A LOCADORA AINDA");
		    }
		}
		else
			System.out.println("AINDA NAO EXISTEM CLIENTES CADASTRADOS");
    }

	//Essa função recebe um Cliente da aplicacao cliente e devolve um veiculo em seu nome, caso ele tenha débito
    @Override
    public void devolverVeiculo(Cliente cliente){
		if(clientes.size() != 0){

			Cliente clienteSelecionado = this.clientes.get(cliente.getId());
		    if(clienteSelecionado.getDebito() > 0){
		        clienteSelecionado.setDebito(clienteSelecionado.getDebito()-1);
		        System.out.println("VEICULO DEVOLVIDO");
		    }
		    else{
		        System.out.println("ESSE CLIENTE NAO TEM DEBITO COM A LOCADORA AINDA");
		    }
		}
		else
			System.out.println("AINDA NAO EXISTEM CLIENTES CADASTRADOS");
    }
}
