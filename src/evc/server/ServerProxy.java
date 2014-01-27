/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.server;

import evc.util.ObjType;
import evc.util.OpType;
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
 * @author me
 * 
 *   Un proxy coté server 
 * 
 */
public class ServerProxy implements IServerProxy {

    //  List des différents objets gerés par le server 
    private Collection<String> servObjList;
    
    private MulticastServer mulicastServ;
    int typeObjet = 2;

    public ServerProxy() {

        JFrame frame = new JFrame("Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // JButton butSendReqCreate = new JButton(" mul-cast create obj");
        // JButton butSendReqTran = new JButton(" mul-cast translate obj");
        // frame.getContentPane().add(butSendReqCreate, BorderLayout.EAST);
        // frame.getContentPane().add(butSendReqTran, BorderLayout.WEST);
        JLabel lab = new JLabel("        Server Admin Interface ");
        frame.getContentPane().add(lab, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(400, 400));
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

    public static void main(String[] args) throws IOException {

        new ServerProxy();
        // Test RMI 

    }

    
    @Override
    public void diffuseCreatePOVObject(String objname, double x, double y, double z, int geom, boolean isVrml,
            String _vrmlPath) {

        System.out.println(" ServerProxy : diffuse create objet (POV) : geometrY : " + geom);
        try {
            servObjList.add(objname);
            // Multicast 
                /* Evoie des commandes  de creatio de plusieurs objects */
            SCMessage mes1 = new SCMessage(objname, OpType.CREATE_POV_OP, new Vector3d(x, y, z),//1, 0, -3
                    new Vector3d(0, 0, 0), ObjType.SPHERE, false, "");
            // diffuser les orders  au clients 
            mulicastServ.diffuseMessage(mes1);

            System.out.println(" diffuse done  coté serveur !");
            // diffuse(mes2);
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
            Logger.getLogger(ServerProxy.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @Override 
    public void diffuseCreateObject(double x, double y, double z, int geom, boolean isVrml, String _vrmlPath) {
        System.out.println(" ServerProxy : diffuse create objet : geometrY : " + geom);
        try {
            String objId;
            objId = "object" + (servObjList.size() + 1);
            servObjList.add(objId);
            // Multicast 
                /* Evoie des commandes  de creatio de plusieurs objects */
            SCMessage mes1 = new SCMessage(objId, OpType.CREATE_OP, new Vector3d(1, 0, -3),
                    new Vector3d(0, 0, 0), geom, isVrml, _vrmlPath);
            // diffuser les orders  au clients 
            mulicastServ.diffuseMessage(mes1);
            // diffuse(mes2);
        } catch (IOException ex) {
            Logger.getLogger(ServerProxy.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @Override
    public void diffuseUpdateObject(String objectId, Vector3d delatTrans, Vector3d delatRot) {
        System.out.println(" ServerProxy : diffuse translate  objet : by values:  ");
        System.out.println(" ------> x: " + delatTrans.x + " y:" + delatTrans.y + "z:" + delatTrans.z);
        try {
            // Multicast 
                /* Evoie des commandes  de creatio de plusieurs objects */
            SCMessage mes1 = new SCMessage(objectId, OpType.UPDATE_OP, delatTrans, delatRot, typeObjet);

            mulicastServ.diffuseMessage(mes1);
            // diffuse(mes2);
        } catch (IOException ex) {
            Logger.getLogger(ServerProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void diffuseDeleteObject(String objectId) {

        System.out.println(" Serveur proxy : diffuse translate object ");
        try {
            // Multicast 
                /* Evoie des commandes  de creatio de plusieurs objects */
            SCMessage mes1 = new SCMessage(objectId, OpType.DELATE_OP, new Vector3d(), new Vector3d(), typeObjet);

            mulicastServ.diffuseMessage(mes1);
            // diffuse(mes2);
        } catch (IOException ex) {
            Logger.getLogger(ServerProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
