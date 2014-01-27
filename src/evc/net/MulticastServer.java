/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.net;

import evc.message.SCMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author me
 *    Multicast Classe coté Serveur 
 */
public class MulticastServer {
    //multicast  stuff 
    DatagramSocket socket;
    DatagramPacket outPacket = null;
    byte[] outBuf;
    int PORT;  // multicast port
    String multicastAdr;  // multicast adresse 

    public MulticastServer() {
        multicastAdr = ConfigReseau.MULTICAST_IP_ADR;
        PORT = ConfigReseau.MULTICAST_PORT_NUMBER;
    }
    /**
     * 
     * @param message  : message a diffuser en multicast sur le reseau 
     * @throws IOException 
     */
    public void diffuseMessage(SCMessage message) throws IOException {

        socket = new DatagramSocket();
        outPacket = null;
        try {

            ByteArrayOutputStream b_out = new ByteArrayOutputStream();
            ObjectOutputStream o_out = new ObjectOutputStream(b_out);

            o_out.writeObject(message);  // serialiser le message 

            byte[] b = b_out.toByteArray();

            DatagramPacket dgram = new DatagramPacket(b, b.length,
                    InetAddress.getByName(multicastAdr), PORT); // multicast
            socket.send(dgram);     //  envoie du message sur le resaux 
        } catch (Exception ioe) {
            ioe.printStackTrace();
            System.out.println(ioe);
        }
    }
}
