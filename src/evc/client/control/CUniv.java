/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.client.control;

import evc.client.ClientProxy;
import evc.client.view.PUniv;
import evc.message.OpType;
import evc.message.SCMessage;
import java.util.ArrayList;
import java.util.Collection;
import javax.vecmath.Vector3d;

/**
 *
 * @author 
 */
public class CUniv {
    
    
     private PUniv puniv;
     private ArrayList<CObject>  liCntrlObject;
    
     private ClientProxy cli_prx;
     
    public CUniv (ClientProxy cli_prx){
       liCntrlObject = new ArrayList<CObject>();
       //this.initObjectList(); 
       puniv = new PUniv(this);
       this.cli_prx=cli_prx;   
    
    }
    
    /*
     *  Lancement du controlleur  
     */
    /*public static void main(String args[]) {
       
          new CUniv(null);
        
        
    }*/
    
    
    public void passMessage(SCMessage mess){
    
      switch( mess.getOperationType()){
      
          case OpType.CREATE_OP: {  createObject(mess.getIdMessage(),mess.getDelta_trans(),mess.getDelta_rot());
          }; break; 
          case OpType.UPDATE_OP: { //System.out.println("CUniv:right : "); 
                   updateObject(mess.getIdMessage(),mess.getDelta_trans(),mess.getDelta_rot());
                   
          }; break; 
          default:  
              System.err.println(" Client received unknown message ");
      }
    }
    
    
    public  Collection<CObject>  getListObject(){
       
        return liCntrlObject;
    
    }
    
  
    // setters / getter 
       
    public   void createObject(String obId, Vector3d deltapos, Vector3d deltarot){
        
         System.out.println(" ****** create obj"+obId+" , X :"+deltapos.x+"  Y :"+deltapos.y+" Z:"+deltapos.z);
         // create object with the correspondig coordinate 
         CObject obj1 = new CObject(new Vector3d(deltapos.x,deltapos.y,deltapos.z),obId);
         liCntrlObject.add(obj1); 
         puniv.addObject(obj1.getPrentation().get3DPresentation(), obId);
         
    } 
    public CObject getSpecificCObject(int indexobj){
      
        return  liCntrlObject.get(indexobj);
    }
    
     public void updateObject( String objname,Vector3d deltapos, Vector3d deltarot){
        System.out.println("CUnivers:: making self update ");
           // Trouver l(object qui correspond
           // appliquer translation
           // appliquer la rotation 
         for(CObject co :liCntrlObject ){
           if(co.getName().equals( objname)){
               System.out.println("CUniverse:  object found ");
               System.out.println("CUniv : "+objname+"traslate x :"+deltapos.x+"  , y"+deltapos.y+" z:"+deltapos.z);
               co.updatePosition(deltapos);
               co.updateRotation(deltarot);
              }
        }
   }
    
    /*  Translate an object by a specifique value */
   
      
    public void p2cCreateObject(){
     // cli_prx.requestToServerCreate();
        cli_prx.requestToServerOperation("", new Vector3d(0,0,0),  new Vector3d(0,0,0),OpType.CREATE_OP);
        
    }  
    
    public void p2cUpdateObject(String objname,Vector3d deltapos, Vector3d deltarot){
        
       cli_prx.requestToServerOperation(objname, deltapos, deltarot, OpType.UPDATE_OP); 
    
    }
    public void p2cTranslateLeft(String objId, Vector3d deltaTrans, Vector3d delatRot){
      cli_prx.requestToServerOperation(objId, deltaTrans,delatRot, OpType.UPDATE_OP);
    }
    
    
     
    // public void ()
} 
