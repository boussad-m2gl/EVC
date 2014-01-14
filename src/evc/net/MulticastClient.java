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
 * @author eagle
 */
public class MulticastClient   extends Thread {

  private CUniv _cuniv;
  
  private  String receptMcastAdress;
  private int receptMcastPort;
  
  public MulticastClient (CUniv cuniv) {
     this._cuniv = cuniv;
     receptMcastAdress = ConfigReseau.MULTICAST_IP_ADR;
     receptMcastPort= ConfigReseau.MULTICAST_PORT_NUMBER;
  }
  
  public void run() {
    
    
    
    MulticastSocket socket; 
         try {
          while(true){   
                socket = new MulticastSocket(receptMcastPort); // must bind receive side
                socket.joinGroup(InetAddress.getByName(receptMcastAdress));
                byte[] b = new byte[65535];
                ByteArrayInputStream b_in = new ByteArrayInputStream(b);
                DatagramPacket dgram = new DatagramPacket(b, b.length);

                socket.receive(dgram); // blocks
                ObjectInputStream o_in = new ObjectInputStream(b_in);
                SCMessage omes = ( SCMessage)o_in.readObject();
                //
                // CALL A CALL BACK FUNCTION TO ACTIVATE 
                 _cuniv.passMessage(omes);
                // 
                System.out.println("  Client side : received , id: "+omes.getIdMessage()+
                       "TRANSLATE : x" + omes.getDelta_trans().x+" y:"+omes.getDelta_trans().y+"  z:"+omes.getDelta_trans().z+
                        "ROT: dh" + omes.getDelta_trans().x+" dp:"+omes.getDelta_trans().y+"  dr:"+omes.getDelta_trans().z);
                dgram.setLength(b.length); // must reset length field!
                b_in.reset(); // reset so next read is from start of byte[] again
          }
        
         }
         catch(Exception e)
         {e.printStackTrace();}
  }
}
