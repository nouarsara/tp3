
package tp3.reseaux;

import java.io.*;
import java.net.*;

public class Emetteur {
    public static void main(String[] args) throws IOException {
        // Création du socket UDP
        DatagramSocket socket = new DatagramSocket();

        // Lecture du fichier
        File file = new File("C:\\Users\\Dr. Imad Nouar\\Documents\\sara nouar\\sssss\\master 1\\monFichierRecu.txt");
        byte[] fileData = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(fileData);

        // Envoi du fichier
        InetAddress receiverAddress = InetAddress.getByName("localhost");
        int receiverPort = 1234;
        DatagramPacket packet = new DatagramPacket(fileData, fileData.length, receiverAddress, receiverPort);
        socket.send(packet);

        System.out.println("Fichier envoyé !");
        socket.close();
    }
}
