/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.client.control;

import evc.client.abstraction.AObject;
import evc.client.view.PObject;
import javax.vecmath.Vector3d;

/**
 *
 * @author me
 *   Interface du controlleur de l'objet virtuel 
 */
public interface ICObject {
    /**
     * 
     * @return L'abstraction de l'objet geré par ce controlleur 
     */
    public AObject getAbstraction();

    /**
     *
     * @return position de l'objet virtuel
     */
    public Vector3d getPosition();

    /**
     *
     * @return le nome de l'objet virtuel
     */
    public String getName();
    /**
     * 
     * @return la présentation de l'objet geré par ce controlleur  
     */
    public PObject getPrentation();
   /**
    *  Mettre a jour la position 
    * @param x
    * @param y
    * @param z 
    */
    public void updatePosition(double x, double y, double z);
    /**
     *  Mettre a jour la rotation
     * @param h
     * @param p
     * @param r 
     */
    public void updateRotation(double h, double p, double r);
    /**
     * Mettre a jour la position 
     * @param deltapos 
     */
    public void updatePosition(Vector3d deltapos);

    /**
     * Mettre a jour la rotation avec delta rotation
     * @param deltarot 
     */
    public void updateRotation(Vector3d deltarot);

    /**
     * Mettre a jour l'abstraction 
     */ 
    public void updateAbstraction();
}
