/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.server;
import javax.vecmath.Vector3d;

/**
 *
 * @author me 
 *    
 *   L'interface du proxy Server 
 */
public interface IServerProxy {
    
    /**
     * 
     * @param objname  : nom de l'objet
     * @param x
     * @param y
     * @param z
     * @param geom
     * @param isVrml : true si c'est un objet vrml
     * @param _vrmlPath   : chemin d'acces a l'objet
     * 
     *   Diffuser la création d'un point de vue vers les  client
     */
    public void diffuseCreatePOVObject(String objname, double x, double y, double z, int geom, boolean isVrml,
            String _vrmlPath); 
    /**
     * 
     * @param x
     * @param y
     * @param z
     * @param geom
     * @param isVrml  :true si c'est un objet vrml
     * @param _vrmlPath   : : chemin d'acces a l'objet
     * 
     *  Diffuser la création d'un objet vers les  client
     */
    public void diffuseCreateObject(double x, double y, double z, int geom, boolean isVrml, String _vrmlPath);
     /**
      * 
      * @param objectId :  nom de l'objet
      * @param delatTrans : mise a jour avec delta translation 
      * @param delatRot  :  mise a jour avec delta rotation 
      */
    public void diffuseUpdateObject(String objectId, Vector3d delatTrans, Vector3d delatRot);
    /**
     * 
     * @param objectId : nom de l'objet
     *  Diffuser le suppression d'un objet 
     */
    public void diffuseDeleteObject(String objectId);
}
