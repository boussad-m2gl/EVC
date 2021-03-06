/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.client;

import evc.client.control.CUniv;
import evc.net.MulticastClient;
import evc.net.RmiClient;
import java.io.IOException;
import java.rmi.RemoteException;
import javax.vecmath.Vector3d;

/**
 *
 * @author eagle
 */
public class ClientProxy implements  IClientProxy{
    

   private  CUniv cuniv;
     
   private MulticastClient  clientMcast;
   private RmiClient clientrmi;
           
  ClientProxy (){
      
      //  Creation du ontroller de l'univer 
      cuniv= new CUniv(this);
  
      // MultiCast  thread
       clientMcast = new MulticastClient(cuniv);
       clientMcast.start(); //  demarer le multi cast thread coté client 
 
     //RMI  coté client 
      clientrmi = new RmiClient(); 
  
  }   
  public static void main(String[] args) throws IOException, ClassNotFoundException { 
    
     new ClientProxy ();
  }
 
  @Override
  public void reQ2ServerCreate(Vector3d deltaTrans, Vector3d delatRot, 
          int geom, boolean isVrml, String _vrmlPath){
    try{
            clientrmi.getInstance().c2screateObject(0, 0, 0, geom, isVrml, _vrmlPath);
       }catch(RemoteException e){
		e.printStackTrace();
       }
  }

   @Override
    public void reQ2ServerUpdate(String objId, Vector3d deltaTrans, Vector3d delatRot){
        try{
          clientrmi.getInstance().c2cUpdateObject(objId, deltaTrans, delatRot); 
        }catch(RemoteException e){
		e.printStackTrace();
       }
       
    }
    
    @Override
     public void reQ2ServerDelete(String objId){
       
          try{
                clientrmi.getInstance().c2sDeleteObject(objId);
            }catch(RemoteException e){
		e.printStackTrace();
       }
          
        
    }
     @Override
    public void reQ2ServerCreatePOV(String pov_name,Vector3d vectTrans){
      try{
                clientrmi.getInstance().c2sCreatePOV(pov_name,vectTrans);
            }catch(RemoteException e){
		e.printStackTrace();
       }
    }
 
 
}

   
