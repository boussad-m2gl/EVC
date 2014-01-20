/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.server;

import evc.message.ObjType;
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
     @Override
    public void c2screateObject(double x, double y, double z, int geom, boolean isVrml, String _path) throws RemoteException {
          System.out.println(" Server received create object .....");
         _prx.diffuseCreateObject(x,y,z, geom, isVrml, _path);
    }
 
    public void c2screateObject(double x, double y, double z) throws RemoteException {
       System.out.println(" Server received create object .....");
      // _prx.diffuseCreateObject(x,y,z);
    }
    
    public void c2screateVRMLObject(double x, double y, double z) throws RemoteException {
       System.out.println(" Server received create object .....");
       _prx.diffuseCreateVRMLObject(x,y,z);
    }

    @Override
    public void c2cUpdateObject(String obId, Vector3d deltaTrans, Vector3d delatRot) throws RemoteException {
        System.out.println(" Server received update operation from client  :  values ");
        System.out.println(" <<<<  x: "+ deltaTrans.x+" y:"+ deltaTrans.y+"z:"+ deltaTrans.z);
        _prx.diffuseUpdateObject(obId, deltaTrans, delatRot);
    }

    @Override
    public void c2sDeleteObject(String obId, Vector3d deltaTrans, Vector3d delatRot) throws RemoteException {
        System.out.println(" Server received delete operation from client  : obj is "+obId);
        _prx.diffuseDeleteObject(obId);
    }

    @Override
    public void c2sCreatePOV(String povid) throws RemoteException {
         System.out.println(" Server received create <POV> from client  : obj is "+povid);
         _prx.diffuseCreatePOVObject(povid, 0, 0, 0, ObjType.SPHERE, false, "");
    }

   

   
  
    
}
