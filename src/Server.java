import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int porta;

    public Server(int porta) {
        this.porta = porta;
        try {
            serverSocket = new ServerSocket(porta);
            System.out.println("Server in ascolto sulla porta " + porta);
        } catch (IOException e) {
            throw new RuntimeException("Errore apertura : " + e.getMessage(), e);
        }
    }

    public Socket attendi() {
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Client connesso: " + clientSocket.getInetAddress());
            return clientSocket;
        } catch (IOException e) {
            throw new RuntimeException("Errore  client: " + e.getMessage(), e);
        }
    }

    public String attendiMessaggio() {
        System.out.println("Server in attesa di un messaggio");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Errore lettura messaggio: " + e.getMessage());
            return null;
        }
    }

    public void scrivi(String messaggio) {
        try {
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(messaggio);
        } catch (IOException e) {
            System.err.println("Errore invio messaggio: " + e.getMessage());
        }
    }

    public void chiudi() {
        try {
            if (clientSocket != null && !clientSocket.isClosed())
                clientSocket.close();
        } catch (IOException e) {
            System.err.println("Errore chiusura client socket: " + e.getMessage());
        }
    }

    public void termina() {
        try {
            if (serverSocket != null && !serverSocket.isClosed())
                serverSocket.close();
        } catch (IOException e) {
            System.err.println("Errore chiusura server: " + e.getMessage());
        }
    }
}