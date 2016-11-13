package client.main;

import server.api.API;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private Registry registry;
    private API stub;

    private final int PORT = 3001;

    public Client() {
        try {
            this.registry = LocateRegistry.getRegistry(PORT);
            this.stub = (API) this.registry.lookup("ServerInterface");
        } catch (RemoteException e) {
            System.err.println( "Error importing stub" );
        } catch (NotBoundException e) {
            System.err.println( "Error getting access to registry" );
        }
    }

    public API getStub() {
        return stub;
    }
}
