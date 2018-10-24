import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente implements Serializable {

    //identificador do cliente
    private int id;
    //nome do cliente
    private String nome;
    //quanto de debito o cliente tem
    private int debito;
    //se o cliente esta com um carro alugado
    private boolean alocado;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    private int numero;

    public Cliente(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.debito = 0;
        this.alocado = false;
    }

    //retorna o ID do cliente
    public int getId() {
        return id;
    }

    //define o ID do cliente
    public void setId(int id) {
        this.id = id;
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
            Registry registro = LocateRegistry.getRegistry(4444);
            Locadora locadora = (Locadora) registro.lookup("//localhost/locadoraService");
			
			//estrutura que guarda os novos clientes cadastrados
            ArrayList<Cliente> clientes = new ArrayList<Cliente>();

            int i = 0;
            Scanner scan = new Scanner(System.in);
            System.out.println("DIGITE A OPCAO DESEJADA: \n 1 - Criar Cliente \t 2 - Cadastrar clientes existentes \t 3 - Listar Clientes \t 4 - Acessar com um cliente");

            int opcao = scan.nextInt();
            while(opcao != 0) {
                switch (opcao) {
					//Case 1: criar novo cliente
                    case 1:
                        System.out.println("DIGITE O NOME DO CLIENTE: ");
                        String n = scan.next();
                        Cliente cn = new Cliente(i,n);
                        clientes.add(cn);
                        i++;
                        break;

					//Case 2: cadastrar na locadora todos os cliente existentes	
                    case 2:
                        System.out.println("VC DESEJA CADASTRAR?");
                        for(Cliente x: clientes){
                            System.out.println(x.getId() + "   " + x.getNome());
                        }

                        System.out.println("1 - SIM \t 2 - NAO");
                        int opcao2 = scan.nextInt();
                        if(opcao2 == 1){
                            for(Cliente x: clientes){
                                locadora.cadastarCliente(x);
                            }
                        }
                        break;

                    case 3:
						//Case 3: listar cliente cadastrados na locadora
                        locadora.listarClientes();
                        break;
                    
					case 4:
						//Case 4: Alugar ou devolver um veiculo
                        System.out.println("Por favor selecione o cliente desejado: ");
                        for(Cliente x: clientes){
                            System.out.println(x.getId() + "   " + x.getNome());
                        }
                        int opcao3 = scan.nextInt();

                        Cliente clienteSelecionado = clientes.get(opcao3);   
						System.out.println("O que voce deseja fazer?");
                        System.out.println("1 - ALUGAR VEICULO \t 2 - DEVOLVER VEICULO");
                        int opcao4 = scan.nextInt();
                        switch (opcao4){
                            case 1:
                                locadora.alugarVeiculo(clienteSelecionado);
                                break;
                            case 2:
                                locadora.devolverVeiculo(clienteSelecionado);
                                break;
                                default:
                                    System.out.println("OPCAO INVALIDA");
                        }
                        break;

                    case 5:
                        //Caso 5: Imprimir os cliente
                        FileWriter arq = new FileWriter("clientes.txt");
                        PrintWriter gravarArq = new PrintWriter(arq);
                        for(Cliente x: clientes)
                            gravarArq.println(x.getId() + "   " + x.getNome() + "   " + x.getDebito());
                        arq.close();
                        System.out.println("Arquivo gravado com sucesso");
                    }
            }

		//Pegando as exceções
        } catch (RemoteException re) {
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
