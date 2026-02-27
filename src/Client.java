import java.io.*;
import java.net.Socket;

public class Client {
    private String nome;
    private Socket socket;

    public Client(String nome) {
        this.nome = nome;
    }

    public void connetti(String host, int porta) {
        try {
            socket = new Socket(host, porta);
            System.out.println("Client " + nome + " si è connesso al server.");
        } catch (IOException e) {
            throw new RuntimeException("Errore connessione: " + e.getMessage(), e);
        }
    }

    public void scrivi(String messaggio) {
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(messaggio);
        } catch (IOException e) {
            System.err.println("Errore durante l'invio: " + e.getMessage());
        }
    }

    public String leggi() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Errore durante la lettura: " + e.getMessage());
            return null;
        }
    }

    public String attendi() {
        System.out.println("Client " + nome + " in attesa di un messaggio dal server...");
        return leggi();
    }

    public void chiudi() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                System.out.println("Client " + nome + " ha chiuso la connessione.");
            }
        } catch (IOException e) {
            System.err.println("Errore chiusura socket: " + e.getMessage());
        }
    }
}