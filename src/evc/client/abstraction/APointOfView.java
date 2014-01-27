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
 *  Classe Abstraction d'un point de vue 
 */
public class APointOfView  extends AObject {
 
   
   public APointOfView(Vector3d pos, String name, int geom){
        super(pos,name,geom,false,"");
   
   }
  

}
