import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Mainserver {
    public static void main(String[] args) {
        Server server = new Server(5000);

        while (true) {
            try {
                final Socket clientSocket = server.attendi();

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(clientSocket.getInputStream()));
                            String messaggio = reader.readLine();
                            System.out.println("CLIENT: " + messaggio);

                            PrintWriter writer = new PrintWriter(
                                    clientSocket.getOutputStream(), true);
                            writer.println("Messaggio ricevuto!");

                            clientSocket.close();
                            System.out.println("Connessione chiusa con il client");

                        } catch (Exception e) {
                            System.err.println("Errore gestione client: " + e.getMessage());
                        }
                    }
                });

                t.start();

            } catch (Exception e) {
                System.err.println("Errore server: " + e.getMessage());
            }
        }
    }
}