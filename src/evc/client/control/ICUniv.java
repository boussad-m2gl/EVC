/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.client.control;

import evc.message.SCMessage;
import java.util.Collection;
import javax.vecmath.Vector3d;

/**
 *
 * @author me
 *   Interface Controle Univers 
 */
public interface ICUniv {
    
    /**
     * 
     * @param mess : message passé par le client proxy au controlleur
     *               ce message represente une commande parvenue du serveur 
     */
    public void passMessage(SCMessage mess);
    
    /**
     * 
     * @return Liste des objets gérés par ce controlleur univers 
     */
    public Collection<CObject> getListObject() ;
    /**
     * 
     * @param vectTrans initialiser un point de vue 
     */
    public void initializePOV(Vector3d vectTrans);
    /**
     * 
     * @param pov_name: nom du point de vue 
     * @param vectTrans  : la position du point de vue 
     *   CUniv demande l'entegistrement d'un point de vue auprés du serveur
     */
    public void Cuniv2ServerRegisterPov(String pov_name, Vector3d vectTrans);
     /**
      *  Création d'un point de vue 
      * @param obId  : id point de vue 
      * @param deltapos  :position 
      * @param deltarot  : rotation 
      * @param geom
      * @param isVrml
      * @param path_vrml 
      */
    public void createPOVObject(String obId, Vector3d deltapos, Vector3d deltarot, int geom, boolean isVrml,
            String path_vrml);
    /**
     *  Création d'un objet virtuel dans l'univers
     * @param obId
     * @param deltapos
     * @param deltarot
     * @param geom
     * @param isVrml
     * @param path_vrml 
     */
    public void createObject(String obId, Vector3d deltapos, Vector3d deltarot, int geom, boolean isVrml,
            String path_vrml);
    /**
     * 
     * @param indexobj
     * @return un objet reperé par son index 
     */
    public CObject getSpecificCObject(int indexobj) ;
    /**
     *  Mettre a jour un objet dans l'univers 
     * @param objname
     * @param deltapos
     * @param deltarot 
     */
    public void updateObject(String objname, Vector3d deltapos, Vector3d deltarot);
    /**
     * Supprimer un objet dans l'univers 
     * @param objname 
     */
    public void delateObject(String objname);
    /*  Translate an object by a specifique value */

    /**
     * Presentation 2 Controlleur : demande de création d'un objet par le presentation
     * @param deltaTrans
     * @param delatRot
     * @param geom
     * @param isVrml
     * @param _vrmlPath 
     */
    public void p2cCreateObject(Vector3d deltaTrans, Vector3d delatRot,
            int geom, boolean isVrml, String _vrmlPath);
   /**
    * Presentation 2 Controlleur : demande de mettre a jour d'un objet par le presentation
    * @param objname
    * @param deltapos
    * @param deltarot 
    */
    public void p2cUpdateObject(String objname, Vector3d deltapos, Vector3d deltarot);
    /**
     * Presentation 2 Controlleur : demande de supprimer d'un objet par le presentation
     * @param objName : nome de l'objet a supprimer 
     */
    public void p2cDeleteObject(String objName);
    
}
