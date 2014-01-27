/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.net;

import evc.client.control.CUniv;
import evc.message.SCMessage;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author me
 * 
 *   Un thread  coté client pour écouter les multicast du serveur
 */
public class MulticastClient extends Thread {

    private CUniv _cuniv;  //  controle de l'univers 
    private String receptMcastAdress;  // adress  de multicast
    private int receptMcastPort;  //  port d'ecout multicast 

    public MulticastClient(CUniv cuniv) {
        this._cuniv = cuniv;
        receptMcastAdress = ConfigReseau.MULTICAST_IP_ADR;
        receptMcastPort = ConfigReseau.MULTICAST_PORT_NUMBER;
    }

    public void run() {



        MulticastSocket socket;
        try {
            while (true) {
                socket = new MulticastSocket(receptMcastPort); // must bind receive side
                socket.joinGroup(InetAddress.getByName(receptMcastAdress));
                byte[] b = new byte[65535];
                ByteArrayInputStream b_in = new ByteArrayInputStream(b);
                DatagramPacket dgram = new DatagramPacket(b, b.length);

                socket.receive(dgram); // blocks
                ObjectInputStream o_in = new ObjectInputStream(b_in);
                /*
                 *  Cast vers SCMessage car c'est le seul format de mesage
                 *  utilisé la. 
                 */
                SCMessage omes = (SCMessage) o_in.readObject();

                System.out.println("  Client side : received , id: " + omes.getIdMessage()
                        + "TRANSLATE : x" + omes.getDelta_trans().x + " y:" + omes.getDelta_trans().y + "  z:" + omes.getDelta_trans().z
                        + "ROT: dh" + omes.getDelta_trans().x + " dp:" + omes.getDelta_trans().y + "  dr:" + omes.getDelta_trans().z);
                dgram.setLength(b.length); // resete le longeur du champ 
                b_in.reset(); // reset so next read is from start of byte[] again
                //  passer le message vers le controlleur de l'univers
                _cuniv.passMessage(omes);
              
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
