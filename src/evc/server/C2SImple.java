/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.vecmath.Vector3d;

/**
 *
 * @author eagle
 */
public class C2SImple extends UnicastRemoteObject implements  C2SInterface{
    
    ServerProxy _prx;
    
    public C2SImple(ServerProxy prx)throws RemoteException{
      this._prx = prx;
    }

 
    public void c2screateObject(double x, double y, double z) throws RemoteException {
       System.out.println(" Server received create object .....");
       _prx.diffuseCreateObject(x,y,z);
    }

    @Override
    public void c2cUpdateObject(String obId, Vector3d deltaTrans, Vector3d delatRot) throws RemoteException {
        System.out.println(" Server received update operation from client  :  values ");
        System.out.println(" <<<<  x: "+ deltaTrans.x+" y:"+ deltaTrans.y+"z:"+ deltaTrans.z);
        _prx.diffuseUpdateObject(obId, deltaTrans, delatRot);
    }

   
  
    
}
