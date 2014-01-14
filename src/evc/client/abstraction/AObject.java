/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.client.abstraction;

import javax.media.j3d.TransformGroup;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

/**
 *
 * @author eagle
 */
public class AObject {
    
    
   private String _name;  // id 
   private Vector3d _position;
   protected Quat4d rotation;

  
   public  AObject(Vector3d pos,String name){
        _position =  pos;
        _name=  name;   
    }
   
   public Vector3d getPosition(){
   
     return _position;
   }
   
    public void setPosition(Vector3d  pos){
   
      _position=pos;
   }
    
   public String getName(){
      return  _name;
   }
   
   public Quat4d getRotation() {
        return rotation;
   }

    public void setRotation(Quat4d rotation) {
        this.rotation = rotation;
   }
   
}
