import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 5000);
        System.out.println("Client is connecting to server at port no" +s.getPort());

    }
}
