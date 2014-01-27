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
 *   
 *  Controle d'un objet virtuel
 */
public class CObject   implements  ICObject {
    
    private  AObject  obj;    //  abstraction d'un objet virtuel  
    private  PObject  pobj;   //  Presnetation d'un objet virtuel
    
   
    public CObject(Vector3d pos, String name, int geom, boolean isvrml,String path_vrml){
       obj = new AObject(pos, name, geom, isvrml, path_vrml);
       pobj = new PObject(this);
    }
    
    public CObject(Vector3d pos,String name,int typeObjet){
       obj = new AObject(pos,name);
      pobj = new PObject(this);
    }
    
    @Override
    public AObject getAbstraction(){
         return obj;
    } 
    @Override
    public Vector3d getPosition(){
         return   obj.getPosition();
    }
    
    @Override
    public String getName(){
        return obj.getName();
    } 
    @Override
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
      @Override 
      public void updatePosition (double x, double y, double z){
          pobj.translate( (float)x, (float)y,(float)z);
          updateAbstraction();
      }
      @Override
      public void updateRotation (double h, double p, double r){
            pobj.rotate(h, p,r);
            updateAbstraction();
      }
      @Override
       public void updatePosition (Vector3d deltapos){
          pobj.translate(deltapos);
          updateAbstraction();
      }
      @Override
      public void updateRotation (Vector3d deltarot){
            pobj.rotate(deltarot);
            updateAbstraction();
      }
      @Override
     public void updateAbstraction(){
          obj.setPosition(pobj.getPosition());
          obj.setRotation(pobj.getRotation());   
     }  
     
}
