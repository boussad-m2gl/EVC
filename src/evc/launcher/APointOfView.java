/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.launcher;

import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

/**
 *
 * @author eagle
 */
public class APointOfView {
 
    
   private String _name;  // id 
   private Vector3d _position;
   protected Quat4d rotation;
   private int _geom; 
   
   public APointOfView(Vector3d pos, String name, int geom){
      _name=name;
      _position=pos;
      _geom=geom;
   }
    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public Vector3d getPosition() {
        return _position;
    }

    public void setPosition(Vector3d _position) {
        this._position = _position;
    }

    public Quat4d getRotation() {
        return rotation;
    }

    public void setRotation(Quat4d rotation) {
        this.rotation = rotation;
    }
   
   

}
