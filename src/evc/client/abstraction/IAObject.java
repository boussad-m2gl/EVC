/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.client.abstraction;

import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

/**
 *
 * @author me
 *   Interface abstraction de l'objet virtuel 
 */
public interface IAObject {
    /**
     * 
     * @return retourne la geometrie de l'objet 
     */
    public int getGeom();
    /**
     *   Set le géometrie 
     * @param _geom 
     */
    public void setGeom(int _geom);
    /**
     * 
     * @return true si l'objet virtuel est une  fichier vrml 
     */
    public boolean isVrmlObj();
    /**
     * 
     * @param _vrmlObj true si l'objet virtuel est une  fichier vrml 
     */
    public void setVrmlObj(boolean _vrmlObj);
    /**
     * 
     * @return  le chemin pour le chargement du fichier vrml 
     */
    public String getPath_vrml();
    /**
     * 
     * @param _path_vrml : chemin d'acces au fichier vrml
     */
    public void setPath_vrml(String _path_vrml);
    /**
     * 
     * @return  la position de l'objet 
     */
    public Vector3d getPosition();
    /**
     * 
     * @param pos : set le position de l'objet 
     */
    public void setPosition(Vector3d  pos);
    /**
     * 
     * @return le nom e l'objet virtuel
     */
    public String getName();
    /**
     * 
     * @return la rotation de l'objet virtuel 
     */
    public Quat4d getRotation();
    /**
     * 
     * @param rotation : set la rotation de l'objet virtuel 
     */
    public void setRotation(Quat4d rotation);
}
