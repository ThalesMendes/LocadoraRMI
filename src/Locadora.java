import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
 
public interface Locadora extends java.rmi.Remote{
 
 
    void criarCliente() throws RemoteException;
    void locacao(Cliente c) throws RemoteException;
    void devolucao(Cliente c) throws RemoteException;
    void pagamento(Cliente c) throws RemoteException;
}
