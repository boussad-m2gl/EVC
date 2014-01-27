/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.message;

import javax.vecmath.Vector3d;

/**
 *
 * @author me
 *   Interface d'un message echangé entrele serveur et le client
 */
public interface ISCMessage {
   /**
    * 
    * @return   la géometrie d'un object conserné par ce message 
    * @see  evc.util.ObjType
    */
    public int getGeometry();
    /**
     * 
     * @param geometry : le géometrie de 'objet conserné par ce message 
     * @see  evc.util.ObjType
     */
    public void setGeometry(int geometry);
    /**
     * 
     * @return true si l'object consrné par ce message est un vrml file
     */
    public boolean isIsVrmlFile();
    /**
     * 
     * @param isVrmlFile : true si l'object consrné par ce message est un vrml file
     */
    public void setIsVrmlFile(boolean isVrmlFile);
   /**
    * 
    * @return le chemin vers l'objet conserné par ce message si c'est fichier vrml 
    */
    public String getObj_path();
    /**
     * 
     * @param obj_path : chemin d'acces a l'objet 
     */
    public void setObj_path(String obj_path);

    /**
     * 
     * @return le delta translation qui sera exercer sur l'objet 
     */ 
    public Vector3d getDelta_trans();
   /**
    * 
    * @param _delta_trans fixe la detla translation qui sera exercer sur l'obje
    */
    public void setDelta_trans(Vector3d _delta_trans);
    /**
     * 
     * @return le delta rotation qui sera exercer sur l'objet
     */       
    public Vector3d getDelta_rot();
   /**
    * 
    * @param _delta_rot :  le delta rotation qui sera exercer sur l'objet
    */
    public void setDelta_rot(Vector3d _delta_rot);
    /**
     * 
     * @return identifiant du message = identifiant de lobjet conserné par ce message 
     */
    public String getIdMessage();
    /**
     * 
     * @param idMessage set identifiant du message = identifiant de lobjet conserné par ce message
     */
    public void setIdMessage(String idMessage);
    /**
     * 
     * @return type d'opération a effectuer 
     * @see  evc.util.OpType
     */
    public int getOperationType();
     /**
      * 
      * @param operationType type d'opération a effectuer
      * @see  evc.util.OpType
      */
    public void setOperationType(int operationType);
}
