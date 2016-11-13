package runners;

import server.main.Server;
import java.io.IOException;

public class ServerRunner {
    public static void main(String[] args) {
        Server server = Server.getInstance();

        try {
            int a = System.in.read();
            System.out.println((char)a);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
