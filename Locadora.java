import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Locadora extends Remote{

    void cadastarCliente(Cliente cliente) throws RemoteException;
    void listarClientes() throws RemoteException;
    void alugarVeiculo(Cliente cliente) throws  RemoteException;
    void devolverVeiculo(Cliente cliente) throws  RemoteException;
    void teste() throws RemoteException;
}