package reseaux_tp333;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class EmetteurMulticast {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\wakcomputer\\Desktop\\tous\\tp3 reseau.txt";
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);

        // Crée un objet MulticastSocket pour envoyer des datagrammes multicast
        MulticastSocket multicastSocket = new MulticastSocket();

        byte[] buffer = new byte[1024];
        int bytesRead;
        InetAddress multicastAddress = InetAddress.getByName("224.0.0.1");
        int multicastPort = 12368;

        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            // Crée un DatagramPacket avec les données à envoyer, l'adresse multicast et le port
            DatagramPacket packet = new DatagramPacket(buffer, bytesRead, multicastAddress, multicastPort);
            // Envoie le DatagramPacket à tous les membres du groupe multicast
            multicastSocket.send(packet);
        }

        fileInputStream.close();
        multicastSocket.close();
        System.out.println("Fichier envoyé en multicast avec succès.");
    }
}
