public class Mainserver {
    public static void main(String[] args) {
        Server server = new Server(5000);

        while (true) {
            server.attendi();
            String messaggio = server.attendiMessaggio();
            System.out.println("CLIENT: " + messaggio);
            server.scrivi("Messaggio ricevuto!");
            server.chiudi();
        }
    }
}