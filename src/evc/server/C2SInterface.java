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
 * @author me
 * 
 *    -  Different methods that can be invoked by the client 
 */
public interface C2SInterface extends Remote {
    
    /**
     * 
     * @param povid : identifiant du point de vue 
     * @param vectTrans : translation du poit de vue 
     * @throws RemoteException 
     *        --  Méthode appelée par le client pour creer un point de vue 
     */
    void c2sCreatePOV(String povid,Vector3d vectTrans) throws RemoteException;
    /**
     * 
     * @param x : position x de l'objet a créer 
     * @param y :  position y de l'objet a créer 
     * @param z :  position z de l'objet a créer 
     * @param geom  :  geomtrie  l'objet a créer 
     * @param isVrml : true si c'est un fichier vrml 
     * @param _path : chemin du  fichier  vrml 
     * @throws RemoteException 
     * 
     *   --  Méthode appelée par le client pour creer un objet
     */
    void c2screateObject(double x, double y, double z , int geom, boolean isVrml, String _path)throws RemoteException;
     /**
      * 
      * @param obId  : identifiant de 'objet
      * @param deltaTrans  : delta translation a operer sur l'objet
      * @param delatRot   : delta rotation a operer sur l'objet 
      * @throws RemoteException 
      * 
      *  Méthode appelée par le client pour mettre a jour un objet
      */
    void c2cUpdateObject(String obId, Vector3d deltaTrans,Vector3d delatRot) throws RemoteException;
    /**
     * 
     * @param obId : identifiant de l'objet
     * @param deltaTrans : to delete  ca sert a rien 
     * @param delatRot  :  
     * @throws RemoteException 
     *     
     *   Méthode appelée par le client pour supprimer un objet
     */
    void c2sDeleteObject(String obId) throws RemoteException;
    
}
