import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server ready at port number" +ss.getLocalPort());

        try{
            while (true) {
                Socket server = ss.accept();
                System.out.println("Client is connected at port "+server);
            }
        }
        catch (Exception e) {
            System.out.println("Error in server");
        }
    }
}
