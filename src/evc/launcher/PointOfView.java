/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.launcher;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Sphere;
import evc.client.control.CObject;
import java.awt.Color;
import javax.media.j3d.Appearance;
import javax.media.j3d.Geometry;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

/**
 *
 * @author eagle
 */
public class PointOfView {
 
    CPointOfView _cpov;
    TransformGroup _pofvTrans;
    
   public PointOfView(CPointOfView cpv){
              
               _cpov = cpv;
               
               
               Transform3D translation = new Transform3D();
		translation.setTranslation(_cpov.getPosition()); // new Vector3d(0,0,-3)
		TransformGroup objTrans = new TransformGroup(translation);
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		objTrans.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
              
                Sphere sphere = new Sphere(0.10f);  // Sphere(0.25f);
                sphere.setName(_cpov.getName());
		Appearance sphereAppearance = new Appearance();
		Color3f c3f = new Color3f(Color.WHITE);
		sphereAppearance.setMaterial(new Material(c3f, c3f, c3f, c3f, .5f));
		sphere.setAppearance(sphereAppearance);
                objTrans.addChild(sphere);
                
                _pofvTrans= objTrans;
                _pofvTrans.setName(_cpov.getAbstraction().getName());
    }
    
   public TransformGroup getPresentation3d(){
   
     return _pofvTrans;
   }
    

  
   public void setTransform(Transform3D trs3d){
       _pofvTrans.setTransform(trs3d);
    
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
			if(rx !=0 )  rx=0.005;
                        if(ry !=0 )  ry=0.005;
                        if(rz !=0 )  rz=0.005;
		Vector3d rotate = new Vector3d();
                rotate.set(rx, ry, rz);
                
                Transform3D localT3D = new Transform3D();
		localT3D.setEuler(rotate);
				
		Transform3D  newT3D = new Transform3D();
		newT3D.mul(oldT3D, localT3D);
		this.get3DPresentation().setTransform(newT3D);
                
                
                
  }
  
   public Vector3d getPosition(){
      
      Transform3D transT3D = new Transform3D();
      _pofvTrans.getTransform(transT3D);
      Vector3d position  = new Vector3d();
      transT3D.get(position);
      return position;
  
  }
   
  /**
   *  Get the current rotation 
   */
  public Quat4d getRotation(){
     
      Transform3D transT3D = new Transform3D();
      _pofvTrans.getTransform(transT3D);
      Quat4d rot  = new Quat4d();
      transT3D.get(rot);
      return rot;
  
  }
   
  public TransformGroup  get3DPresentation(){
      return  _pofvTrans; 
  }
   
   public void translate(Vector3d delatpos){
                Transform3D oldT3D = new Transform3D () ;
		 _pofvTrans.getTransform (oldT3D) ;
		Vector3d translate = new Vector3d () ;
		translate.set (delatpos.x, delatpos.y, delatpos.z) ;
		Transform3D localT3D = new Transform3D () ;
		localT3D.setTranslation (translate) ;
		Transform3D newT3D = new Transform3D () ;
		// newT3D.mul (oldT3D, localT3D) ;
		newT3D.mul (localT3D, oldT3D) ;
		_pofvTrans.setTransform (newT3D) ;
  }
  
  public void rotate(Vector3d delatrot){
                Transform3D oldT3D = new Transform3D () ;
		_pofvTrans.getTransform (oldT3D) ;
		Vector3d rotate = new Vector3d () ;
		rotate.set (delatrot.x, delatrot.y, delatrot.z) ;
		Transform3D localT3D = new Transform3D () ;
		localT3D.setEuler (rotate) ;
		Transform3D newT3D = new Transform3D () ;
		newT3D.mul (oldT3D, localT3D) ;
		_pofvTrans.setTransform (newT3D) ;
      
  }
}
