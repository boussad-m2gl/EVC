/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.server;
import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.vecmath.Vector3d;

/**
 *
 * @author eagle
 */
public interface C2SInterface extends Remote {
    
    void c2sCreatePOV(String povid) throws RemoteException;
    void c2screateObject(double x, double y, double z , int geom, boolean isVrml, String _path)throws RemoteException;
    void c2cUpdateObject(String obId, Vector3d deltaTrans,Vector3d delatRot) throws RemoteException;
    void c2sDeleteObject(String obId, Vector3d deltaTrans,Vector3d delatRot) throws RemoteException;
    
}
