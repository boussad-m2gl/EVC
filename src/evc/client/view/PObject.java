/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.client.view;

import com.sun.j3d.utils.geometry.ColorCube;
import evc.client.control.CObject;
import javax.media.j3d.Geometry;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

/**
 *
 * @author eagle
 */
public class PObject {
    
    
    CObject  _cobj;
    TransformGroup _objTrans;
    
   public  PObject(CObject  cobj){
                
                 _cobj = cobj;
                 this.computePresentation();
    }
   
   // Pour le moment il ne fait que faire des cube
   
   private void computePresentation(){
                Transform3D translation = new Transform3D();
		translation.setTranslation(_cobj.getPosition());
		TransformGroup objTrans = new TransformGroup(translation);
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		objTrans.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
		// Create a simple Shape3D node; add it to the scene graph.
		ColorCube cc = new ColorCube(0.2);
                cc.setName(_cobj.getName());
		cc.getGeometry().setCapability(Geometry.ALLOW_INTERSECT);
		objTrans.addChild(cc);
		_objTrans = objTrans;
                // set the name
                _objTrans.setName(_cobj.getAbstraction().getName());
                
   }
  
   
   public void translate(float x, float y, float z){
      
         Vector3d vectTrans= new Vector3d(x,y,z);
        
         Transform3D oldT3D = new Transform3D();
         this.get3DPresentation().getTransform(oldT3D);
         Transform3D  localT3D = new Transform3D();
         localT3D.setTranslation(vectTrans);
         Transform3D newT3D = new Transform3D();
         newT3D.mul(oldT3D,localT3D);          
         this.get3DPresentation().setTransform(newT3D);
   }
   
  /*
   * Push the object to rotate by a vector value 
   */ 
  public void rotate(double rx, double ry, double rz){
  
                Transform3D oldT3D = new Transform3D();
		this.get3DPresentation().getTransform(oldT3D);
				
		Vector3d rotate = new Vector3d();
                rotate.set(rx, ry, rz);
                
                Transform3D localT3D = new Transform3D();
		localT3D.setEuler(rotate);
				
		Transform3D  newT3D = new Transform3D();
		newT3D.mul(oldT3D, localT3D);
		this.get3DPresentation().setTransform(newT3D);
                
                
                
  }
  
  
  public void translate(Vector3d delatpos){
                Transform3D oldT3D = new Transform3D () ;
		 _objTrans.getTransform (oldT3D) ;
		Vector3d translate = new Vector3d () ;
		translate.set (delatpos.x, delatpos.y, delatpos.z) ;
		Transform3D localT3D = new Transform3D () ;
		localT3D.setTranslation (translate) ;
		Transform3D newT3D = new Transform3D () ;
		// newT3D.mul (oldT3D, localT3D) ;
		newT3D.mul (localT3D, oldT3D) ;
		 _objTrans.setTransform (newT3D) ;
  }
  
  public void rotate(Vector3d delatrot){
                Transform3D oldT3D = new Transform3D () ;
		 _objTrans.getTransform (oldT3D) ;
		Vector3d rotate = new Vector3d () ;
		rotate.set (delatrot.x, delatrot.y, delatrot.z) ;
		Transform3D localT3D = new Transform3D () ;
		localT3D.setEuler (rotate) ;
		Transform3D newT3D = new Transform3D () ;
		newT3D.mul (oldT3D, localT3D) ;
		_objTrans.setTransform (newT3D) ;
      
  }
  /**
   *  Get the current position 
   */
  public Vector3d getPosition(){
      
      Transform3D transT3D = new Transform3D();
      _objTrans.getTransform(transT3D);
      Vector3d position  = new Vector3d();
      transT3D.get(position);
      return position;
  
  }
   
  /**
   *  Get the current rotation 
   */
  public Quat4d getRotation(){
     
      Transform3D transT3D = new Transform3D();
      _objTrans.getTransform(transT3D);
      Quat4d rot  = new Quat4d();
      transT3D.get(rot);
      return rot;
  
  }
   
  public TransformGroup  get3DPresentation(){
      return  _objTrans; 
  }
   
    
}
