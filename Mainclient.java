public class Mainclient {
    public static void main(String[] args) {
        Client client = new Client("Riccardo");
        client.connetti("localhost", 5000);
        client.scrivi("Ciao Server!");
        String risposta = client.attendi();
        System.out.println("RISPOSTA SERVER: " + risposta);
        client.chiudi();
    }
}