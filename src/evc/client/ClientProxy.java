/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.client;

import evc.client.control.CObject;
import evc.client.control.CUniv;
import evc.message.OpType;
import evc.net.MulticastClient;
import evc.net.RmiClient;
import java.io.IOException;
import java.rmi.RemoteException;
import javax.vecmath.Vector3d;

/**
 *
 * @author eagle
 */
public class ClientProxy {
    

   private  CUniv cuniv;
     
   private MulticastClient  clientMcast;
   private RmiClient clientrmi;
           
  ClientProxy (){
      
      //  Creation du ontroller de l'univer 
      cuniv= new CUniv(this);
  
    // Multi cast Test  :   
       clientMcast = new MulticastClient(cuniv);
       clientMcast.start();
     
     
     //RMI
      clientrmi = new RmiClient(); 
  
  }   
  public static void main(String[] args) throws IOException, ClassNotFoundException { 
    
     new ClientProxy ();
  }
 

  public  void requestToServerOperation(String objId, Vector3d deltaTrans, Vector3d delatRot, int oper){

    
       
	try{
		
                switch (oper){
                    case  OpType.CREATE_OP: clientrmi.getInstance().c2screateObject(0, 0, 0)  ; break ;
                    case  OpType.CREATE_VRMLOP: clientrmi.getInstance().c2screateVRMLObject(0, 0, 0)  ; break ;
                    case  OpType.UPDATE_OP:{ 
                        clientrmi.getInstance().c2cUpdateObject(objId, deltaTrans, delatRot); } break ; 
                    case OpType.DELATE_OP: {
                         clientrmi.getInstance().c2sDeleteObject(objId, deltaTrans, delatRot);
                    }  
                     // TODO : add rotation operation        
                }
		//rmiServer.c2stranslateObjectLeft(objId, valtrans);
	}
	catch(RemoteException e){
		e.printStackTrace();
	}
	
	}
 
}

   
