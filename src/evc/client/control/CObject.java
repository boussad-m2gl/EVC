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
 * @author 
 */
public class CObject {
    
    private  AObject  obj;
    private  PObject  pobj;   //  pas besoin normalement
    
   /* public final static int CREATE_OP =0;
    public final static int LEFT_OP =1;
    public final static int RIGHT_OP =2;
    public final static int UP_OP =3;
    public final static int DOWN_OP =4;
    public final static int FORWARD_OP =5;
    public final static int BACKWARD_OP =6;
    
    public final static int OP_ROT_X=10;
    public final static int OP_ROT_Y=11;
    public final static int OP_ROT_Z=12;*/
    
    
    /*public CObject(){
       obj = new AObject(new Vector3d(1,0,-3),"Object1");
       pobj = new PObject(this);
    }*/
    
    public CObject(Vector3d pos, String name, int geom, boolean isvrml,String path_vrml){
       obj = new AObject(pos, name, geom, isvrml, path_vrml);
       pobj = new PObject(this);
    }
    
    public CObject(Vector3d pos,String name){
       obj = new AObject(pos,name);
       pobj = new PObject(this);
    }
     
    public AObject getAbstraction(){
         return obj;
    } 
    
    public Vector3d getPosition(){
         return   obj.getPosition();
    }
    
    
    public String getName(){
        return obj.getName();
    } 
    
    public PObject getPrentation(){
        return pobj;
    }
    
     public void translateRight(float translation_Value){
       
         pobj.translate(translation_Value,0.0f,0.0f);
         updateAbstraction();
    }
    
     public void translateLeft(float translation_Value){
         
          pobj.translate( -translation_Value,0.0f,0.0f);
          updateAbstraction();
          
    }
     
     public void translateUp(float translation_Value){
     
         pobj.translate( 0.0f,translation_Value,0.0f);
          updateAbstraction();
     } 
     public void translateDown(float translation_Value){
     
          pobj.translate(0.0f,-translation_Value,0.0f);
         updateAbstraction();
     } 
     public void translateForward(float translation_Value){
        
          pobj.translate( 0.0f,0.0f,translation_Value);
          updateAbstraction();
     } 
     public void translateBackward(float translation_Value){
     
        
           pobj.translate( 0.0f,0.0f,-translation_Value);
           updateAbstraction();
     } 
      
     public void  rotateX(double rot_Value){

        pobj.rotate(rot_Value,0,0);
        updateAbstraction();
     }
     
      public void  rotateY(double rot_Value){
        
         pobj.rotate(0,rot_Value,0);
         updateAbstraction();
     }
      
       public void  rotateZ(double rot_Value){
        
         pobj.rotate(0,0,rot_Value);
         updateAbstraction();
     }
       
      public void updatePosition (double x, double y, double z){
          pobj.translate( (float)x, (float)y,(float)z);
          updateAbstraction();
      }
      
      public void updateRotation (double h, double p, double r){
            pobj.rotate(h, p,r);
            updateAbstraction();
      }
       
       public void updatePosition (Vector3d deltapos){
          pobj.translate(deltapos);
          updateAbstraction();
      }
      
      public void updateRotation (Vector3d deltarot){
            pobj.rotate(deltarot);
            updateAbstraction();
      }
     public void updateAbstraction(){
          obj.setPosition(pobj.getPosition());
          obj.setRotation(pobj.getRotation());
          
          // 
        /*  Vector3d newpos = pobj.getPosition();
            Quat4d newrot = pobj.getRotation();
            System.out.println("CObject : new position x:"+newpos.x+" , y:"+newpos.y+" , z:"+newpos.z);
            System.out.println("CObject : new rot x:"+newrot.x+" , y:"+newrot.y+" , z:"+newrot.z+",  w:"+newrot.w);
         */
     }  
     
}
