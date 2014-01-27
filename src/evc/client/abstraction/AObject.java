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
 * @author me
 * 
 *    Abstraction de l'objet virtuel 
 */
public class AObject {
    
    
   private String _name;  // id 
   private Vector3d _position;
   protected Quat4d rotation;
   private int _geom;
   private boolean _vrmlObj=false;
   private  String _path_vrml;

    public int getGeom() {
        return _geom;
    }

    public void setGeom(int _geom) {
        this._geom = _geom;
    }

    public boolean isVrmlObj() {
        return _vrmlObj;
    }

    public void setVrmlObj(boolean _vrmlObj) {
        this._vrmlObj = _vrmlObj;
    }

    public String getPath_vrml() {
        return _path_vrml;
    }

    public void setPath_vrml(String _path_vrml) {
        this._path_vrml = _path_vrml;
    }
     
    public AObject(Vector3d pos, String name, int geom, boolean isvrml,String path_vrml){
   
        _position =  pos;
        _name=  name; 
        _geom=geom;
        _vrmlObj=isvrml;
        _path_vrml=path_vrml;
        
    }
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
