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
 * @author eagle
 */
public class MulticastServer {
    
  
    DatagramSocket socket; 
    DatagramPacket outPacket = null;
    byte[] outBuf;
    int PORT;
    String multicastAdr;

    public MulticastServer() {
        multicastAdr = ConfigReseau.MULTICAST_IP_ADR;
        PORT= ConfigReseau.MULTICAST_PORT_NUMBER;
    }
    
    
    
    public  void diffuseMessage(SCMessage message) throws IOException {
  
        socket =  new DatagramSocket();
        outPacket = null;
        try {

           ByteArrayOutputStream b_out = new ByteArrayOutputStream();
           ObjectOutputStream o_out = new ObjectOutputStream(b_out);

           o_out.writeObject(message);

           byte[] b = b_out.toByteArray();

           DatagramPacket dgram = new DatagramPacket(b, b.length,
             InetAddress.getByName(multicastAdr), PORT); // multicast
           socket.send(dgram);
         }
          catch (Exception ioe) {
              ioe.printStackTrace();
          System.out.println(ioe);
         }
   }
     
}
