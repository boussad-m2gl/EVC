/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.server;

import evc.message.OpType;
import evc.message.SCMessage;
import evc.net.MulticastServer;
import evc.net.RmiServer;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.vecmath.Vector3d;
//import server.ReceiveMessageImpl;

/**
 *
 * @author eagle
 */
public class ServerProxy  {
   
   private Collection<String> servObjList;
   private MulticastServer mulicastServ;
   
   
   ServerProxy (){
 
        JFrame frame = new JFrame("Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // JButton butSendReqCreate = new JButton(" mul-cast create obj");
       // JButton butSendReqTran = new JButton(" mul-cast translate obj");
       // frame.getContentPane().add(butSendReqCreate, BorderLayout.EAST);
       // frame.getContentPane().add(butSendReqTran, BorderLayout.WEST);
        JLabel lab = new JLabel("        Server Admin Interface ");
        frame.getContentPane().add(lab, BorderLayout.CENTER);
         
        frame.setPreferredSize(new Dimension(400,400));
        frame.pack();
        frame.setVisible(true);
        
        
        mulicastServ = new MulticastServer();
        
        
        servObjList = new ArrayList();
         //  creation du server RMI
        try {
           
            new RmiServer(this);
        } catch (RemoteException ex) {
            Logger.getLogger(ServerProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
   } 
    
   public static void main(String[] args) throws IOException{
   
       new  ServerProxy();   
         // Test RMI 
       
   } 
    
  public void diffuseCreateObject(double x, double y, double z){
        System.out.println(" ServerProxy : diffuse create objet");
     try {
           String objId;
           objId="object"+(servObjList.size()+1);
           servObjList.add(objId);     
         // Multicast 
                /* Evoie des commandes  de creatio de plusieurs objects */
                SCMessage mes1 = new SCMessage(objId, OpType.CREATE_OP, new Vector3d (1, 0, -3),
                        new Vector3d (0, 0, 0));
               // S2CMessage mes2 = new S2CMessage("object2", OpType.CREATE, 0.0, -1, 0, -3);
                // diffuser les orders  au clients 
                mulicastServ.diffuseMessage(mes1);
               // diffuse(mes2);
                } catch (IOException ex) {
                    Logger.getLogger(ServerProxy.class.getName()).log(Level.SEVERE, null, ex);
                }
     
  }
  
  
  public void diffuseUpdateObject(String objectId, Vector3d delatTrans ,Vector3d delatRot ){
           System.out.println(" ServerProxy : diffuse translate  objet : by values:  ");
           System.out.println(" ------> x: "+ delatTrans.x+" y:"+ delatTrans.y+"z:"+ delatTrans.z);
           try {
                    // Multicast 
                /* Evoie des commandes  de creatio de plusieurs objects */
                SCMessage mes1 = new SCMessage(objectId, OpType.UPDATE_OP, delatTrans,delatRot);
               
               mulicastServ.diffuseMessage(mes1);
               // diffuse(mes2);
                } catch (IOException ex) {
                    Logger.getLogger(ServerProxy.class.getName()).log(Level.SEVERE, null, ex);
                }
  }
 
  
  public void diffuseDeleteObject(String objectId){
  
      System.out.println(" Serveur proxy : diffuse translate object ");
       try {
                    // Multicast 
                /* Evoie des commandes  de creatio de plusieurs objects */
                SCMessage mes1 = new SCMessage(objectId, OpType.DELATE_OP, new Vector3d(), new Vector3d());
               
               mulicastServ.diffuseMessage(mes1);
               // diffuse(mes2);
                } catch (IOException ex) {
                    Logger.getLogger(ServerProxy.class.getName()).log(Level.SEVERE, null, ex);
                }
  }
  
}
   
     
    

