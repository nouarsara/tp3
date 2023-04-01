package reseaux_tp333;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class RecepteurMulticast {
    public static void main(String[] args) throws IOException {
        // Crée un objet MulticastSocket pour recevoir les datagrammes multicast
        MulticastSocket multicastSocket = new MulticastSocket(12368);
        InetAddress multicastAddress = InetAddress.getByName("224.0.0.1");
        multicastSocket.joinGroup(multicastAddress);

        byte[] buffer = new byte[1024];

        // Crée un objet FileOutputStream pour écrire les données reçues dans un fichier
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\wakcomputer\\Desktop\\tous\\recepteur1\\tp3 reseau.txt");

        while (true) {
            // Crée un DatagramPacket pour recevoir les données envoyées par l'émetteur
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            // Attend la réception d'un DatagramPacket
            multicastSocket.receive(packet);

            // Récupère l'adresse et le port de l'émetteur
            InetAddress senderAddress = packet.getAddress();
            int senderPort = packet.getPort();

            // Écrit les données reçues dans le fichier
            fileOutputStream.write(packet.getData(), 0, packet.getLength());
            fileOutputStream.flush();

            System.out.println("Données reçues de " + senderAddress + ":" + senderPort);
            if(packet.getLength() <buffer.length) break;
        }

        // Ne sera jamais exécuté car la boucle while est infinie
         fileOutputStream.close();
         multicastSocket.leaveGroup(multicastAddress);
         multicastSocket.close();
    }
}
