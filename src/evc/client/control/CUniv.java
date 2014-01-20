/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.client.control;

import evc.client.ClientProxy;
import evc.client.view.PUniv;
import evc.launcher.CPointOfView;
import evc.message.OpType;
import evc.message.SCMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.vecmath.Vector3d;

/**
 *
 * @author 
 */
public class CUniv {
    
    
     private PUniv puniv;
     private ArrayList<CObject>  liCntrlObject;
    
     private ArrayList<CPointOfView> _listCPOV;
     private String POV_TAG;  // to identify the point of view of current client
             
     private ClientProxy cli_prx;
     
    public CUniv (ClientProxy cli_prx){
       liCntrlObject = new ArrayList<CObject>();
       _listCPOV = new ArrayList<CPointOfView>();
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
      
          case OpType.CREATE_OP: {  createObject(mess.getIdMessage(),mess.getDelta_trans(),mess.getDelta_rot(),
                  
                  mess.getGeometry(),mess.isIsVrmlFile(), mess.getObj_path());
          }; break; 
          case OpType.UPDATE_OP: { //System.out.println("CUniv:right : "); 
                   updateObject(mess.getIdMessage(),mess.getDelta_trans(),mess.getDelta_rot());
                   
          }; break; 
          case OpType.DELATE_OP: {
                 delateObject(mess.getIdMessage());
          } 
          case OpType.INIT_POV_OP :{
              System.out.println(" CUniv : received REQ initialisation of POV  ");
              initializePOV();
          } ;break;
          case OpType.CREATE_POV_OP:{  // pour creer Un poiv , il on a besoin de sont id c'est tt, as defaut c'est une sphere
             System.out.println(" CUniv : received creation of POV  ");
                createPOVObject(mess.getIdMessage(),mess.getDelta_trans(),mess.getDelta_rot(),
                  
                  mess.getGeometry(),mess.isIsVrmlFile(), mess.getObj_path());
          };break;    
          default:  
              System.err.println(" Client received unknown message ");
      }
    }
    
    
    public  Collection<CObject>  getListObject(){
       
        return liCntrlObject;
    
    }
    
    public void initializePOV(){
           //  demande de un identifaint unique pour creater un point de vue 
           UUID idOne = UUID.randomUUID();
           String uddipov = idOne.toString(); 
           POV_TAG = uddipov;
           System.out.println("  CUniv : pov initialized well id :"+uddipov);
           Cuniv2ServerRegisterPov(uddipov);
    
    }
  
    public void Cuniv2ServerRegisterPov (String pov_name){
        
       cli_prx.reQ2ServerCreatePOV(pov_name);
    }
    
    
    
    // Many parameters to reduce them later 
    public void createPOVObject(String obId, Vector3d deltapos, Vector3d deltarot, int geom, boolean isVrml, 
            String path_vrml){
    
         //  Creer un point un point de vue , l'ajouter a l'univer
         CPointOfView  cntrlpov = new CPointOfView(obId, deltapos,geom ); //  control pov ok :
         _listCPOV.add(cntrlpov);
         puniv.addObject(cntrlpov.getPrentation().get3DPresentation(), obId);
    }
    
    
    // setters / getter 
       
    public   void createObject(String obId, Vector3d deltapos, Vector3d deltarot, int geom, boolean isVrml, 
            String path_vrml){
        
         System.out.println(" ****** create obj"+obId+" , X :"+deltapos.x+"  Y :"+deltapos.y+" Z:"+deltapos.z);
         // create object with the correspondig coordinate 
         //CObject obj1 = new CObject(new Vector3d(deltapos.x,deltapos.y,deltapos.z),obId);
         
         CObject obj1 = new CObject(new Vector3d(deltapos.x,deltapos.y,deltapos.z),obId,geom,isVrml,path_vrml);
         
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
         //  In case of updatinf the point of view 
         for(CPointOfView cpov :_listCPOV ){
           if( cpov.getName().equals( objname)){
               System.out.println("CUniv:  object POV found ");
               System.out.println("CUniv : "+objname+"traslate x :"+deltapos.x+"  , y"+deltapos.y+" z:"+deltapos.z);
               cpov.updatePosition(deltapos);
               cpov.updateRotation(deltarot);
               // Check if it is my point of view then move my camera: 
               if(cpov.getName().equals(POV_TAG)){
                   System.out.println("CUniv:  Its my camera , I have to move it  by :"+
                           "x :"+ deltapos.x+" y :"+deltapos.y+" z:"+deltapos.z);
                   puniv.c2pMoveCamera( deltapos.x,deltapos.y,deltapos.z);
                }else{
                   System.out.println("CUniv: It is not my camera this one ");
                  }
              }
        }
         
   }
    
    public void  delateObject(String objname){
    
      
         
         CObject objtodelete=null; 
          for(CObject co :liCntrlObject ){
           if(co.getName().equals( objname)){
                 objtodelete=co; break;
              }
          }
         if(objtodelete!= null) {
            puniv.removeObject(objtodelete.getPrentation().get3DPresentation(), objname);
             liCntrlObject.remove(objtodelete);
         }
    } 
    /*  Translate an object by a specifique value */
   
    
    public void p2cCreateObject (Vector3d deltaTrans, Vector3d delatRot, 
          int geom, boolean isVrml, String _vrmlPath){
        
       cli_prx.reQ2ServerCreate(deltaTrans, delatRot, geom, isVrml, _vrmlPath);
    }
    
   /* public void p2cCreateObject(){
     // cli_prx.requestToServerCreate();
        cli_prx.requestToServerOperation("", new Vector3d(0,0,0),  new Vector3d(0,0,0),OpType.CREATE_OP);
        
    }  */
    
    public void p2cUpdateObject(String objname,Vector3d deltapos, Vector3d deltarot){
        
       //cli_prx.requestToServerOperation(objname, deltapos, deltarot, OpType.UPDATE_OP); 
        cli_prx.reQ2ServerUpdate(objname, deltapos, deltarot);
    
    }
   /* public void p2cTranslateLeft(String objId, Vector3d deltaTrans, Vector3d delatRot){
      cli_prx.requestToServerOperation(objId, deltaTrans,delatRot, OpType.UPDATE_OP);
    }*/
    
    public void p2cDeleteObject(String objName){
      // cli_prx.requestToServerOperation(objName, new  Vector3d(), new Vector3d(),OpType.DELATE_OP);
        cli_prx.reQ2ServerDelete(objName, null, null);
    }
     
    // public void ()
} 
