import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LocadoraServer {
    public LocadoraServer() {
        try {
            Locadora locadora = new LocadoraImpl();
            Registry registro = LocateRegistry.createRegistry(1099);
            registro.rebind("//localhost/locadoraService",locadora);
        }catch (Exception e){
            System.out.println("Erro: " + e);
        }
    }

    public static void main(String[] args) {
        new LocadoraServer();
    }
}
