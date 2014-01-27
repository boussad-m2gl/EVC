/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.client;

import java.rmi.RemoteException;
import javax.vecmath.Vector3d;

/**
 *
 * @author me
 *
 * L'interface du proxy Client
 */
public interface IClientProxy {

    
    /**
     * Requete du client vers le serveur pour creer un objet 
     * @param deltaTrans : valeur translation
     * @param delatRot  : valeur 
     * @param geom
     * @param isVrml
     * @param _vrmlPath 
     */
    public void reQ2ServerCreate(Vector3d deltaTrans, Vector3d delatRot,
            int geom, boolean isVrml, String _vrmlPath);

    /**
     * Requete du client vers le serveur pour mettre a jour l'objet 
     * @param objId : identifiant de l'objet
     * @param deltaTrans  : le delta translation
     * @param delatRot  : le delta rotation
     */
    public void reQ2ServerUpdate(String objId, Vector3d deltaTrans, Vector3d delatRot);
    /**
     * Requete du client vers le serveur pour supprimer un objet 
     * @param objId
     * @param deltaTrans
     * @param delatRot 
     */
    public void reQ2ServerDelete(String objId);
    /**
     *   Requete du client vers le serveur pour ceer un point de vue 
     * @param pov_name  : nom  du point de vue 
     * @param vectTrans 
     */
    public void reQ2ServerCreatePOV(String pov_name, Vector3d vectTrans);
}
