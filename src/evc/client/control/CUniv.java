/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.client.control;

import evc.client.ClientProxy;
import evc.client.abstraction.AUniv;
import evc.client.view.PUniv;
import evc.util.OpType;
import evc.message.SCMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.vecmath.Vector3d;

/**
 *
 * @author me
 * 
 * Classe Controlleur de l'univers virtuel 
 */
public class CUniv implements  ICUniv {

    private PUniv _puniv;  // prsentation de l'univers 
    private AUniv _auniv;
    //  Un TAG pour identifier son poit de vue 
    private String POV_TAG;
    //  Un proxy coté client pour communiquer avec le serveur 
    private ClientProxy cli_prx;

    
    
    public CUniv(ClientProxy cli_prx) {
      
        _auniv = new AUniv();   // instantiation de l'abstration   
        _puniv = new PUniv(this);  // instantiation de la presentation 
        this.cli_prx = cli_prx;  // inst du proxy client 

    }

    /*
     *  Lancement du controlleur  
     */
    /*public static void main(String args[]) {
       
     new CUniv(null);
        
        
     }*/
    
    
    @Override
    public void passMessage(SCMessage mess) {

        switch (mess.getOperationType()) {

            case OpType.CREATE_OP: {
                createObject(mess.getIdMessage(), mess.getDelta_trans(), mess.getDelta_rot(),
                        mess.getGeometry(), mess.isIsVrmlFile(), mess.getObj_path());
            }
            ;
            break;
            case OpType.UPDATE_OP: { //System.out.println("CUniv:right : "); 
                updateObject(mess.getIdMessage(), mess.getDelta_trans(), mess.getDelta_rot());

            }
            ;
            break;
            case OpType.DELATE_OP: {
                delateObject(mess.getIdMessage());
            }
            case OpType.INIT_POV_OP: {
                System.out.println(" CUniv : received REQ initialisation of POV  ");
                // initializePOV();
            }
            ;
            break;
            case OpType.CREATE_POV_OP: {  // pour creer Un poiv , il on a besoin de sont id c'est tt, as defaut c'est une sphere
                System.out.println(" CUniv : received creation of POV  ");
                createPOVObject(mess.getIdMessage(), mess.getDelta_trans(), mess.getDelta_rot(),
                        mess.getGeometry(), mess.isIsVrmlFile(), mess.getObj_path());
            }
            ;
            break;
            default:
                System.err.println(" Client received unknown message ");
        }
    }
   @Override
    public Collection<CObject> getListObject() {

        return _auniv.getLiCntrlObject(); 
    }
    @Override
    public void initializePOV(Vector3d vectTrans) {
        //  demande de un identifaint unique pour creater un point de vue 
        UUID idOne = UUID.randomUUID();
        String uddipov = idOne.toString();
        POV_TAG = uddipov;
        System.out.println("  CUniv : pov initialized well id :" + uddipov);
        Cuniv2ServerRegisterPov(uddipov, vectTrans);

    }
    @Override
    public void Cuniv2ServerRegisterPov(String pov_name, Vector3d vectTrans) {

        cli_prx.reQ2ServerCreatePOV(pov_name, vectTrans);
    }

    @Override
    public void createPOVObject(String obId, Vector3d deltapos, Vector3d deltarot, int geom, boolean isVrml,
            String path_vrml) {

        //  Creer un point un point de vue , l'ajouter a l'univers
        CPointOfView cntrlpov = new CPointOfView(obId, deltapos, geom); //  control pov ok :
        _auniv.addPov(cntrlpov);
        if (!obId.equals(POV_TAG)) { // ne pas l'ajouter si c'est son caméra 
            _puniv.addObject(cntrlpov.getPrentation().get3DPresentation(), obId);
        } else { // c'est notre caméra
            _puniv.addObjectToSelect(obId);
        }

    }

    @Override
    public void createObject(String obId, Vector3d deltapos, Vector3d deltarot, int geom, boolean isVrml,
            String path_vrml) {

        System.out.println(" ****** create obj" + obId + " , X :" + deltapos.x + "  Y :" + deltapos.y + " Z:" + deltapos.z);
        CObject obj1 = new CObject(new Vector3d(deltapos.x, deltapos.y, deltapos.z), obId, geom, isVrml, path_vrml);
        _auniv.addObject(obj1);
        _puniv.addObject(obj1.getPrentation().get3DPresentation(), obId);

    }
    @Override
    public CObject getSpecificCObject(int indexobj) {

        return _auniv.getCntrlObject(indexobj);
    }
    @Override
    public void updateObject(String objname, Vector3d deltapos, Vector3d deltarot) {
        System.out.println("CUnivers:: making self update ");
        // Trouver l(object qui correspond
        // appliquer translation
        // appliquer la rotation 
        for (CObject co : _auniv.getLiCntrlObject()) {
            if (co.getName().equals(objname)) {
                System.out.println("CUniverse:  object found ");
                System.out.println("CUniv : " + objname + "traslate x :" + deltapos.x + "  , y" + deltapos.y + " z:" + deltapos.z);
                System.out.println("CUniv : " + objname + "rotate x :" + deltarot.x + "  , y" + deltarot.y + " z:" + deltarot.z);
                co.updatePosition(deltapos);
                co.updateRotation(deltarot);
            }
        }
        //  In case of updating the point of view 
        for (CPointOfView cpov : _auniv.getListCPOV()) {
            if (cpov.getName().equals(objname)) {
                cpov.updatePosition(deltapos);
                cpov.updateRotation(deltarot);
                // Check if it is my point of view then move my camera: 
                if (cpov.getName().equals(POV_TAG)) {
                    _puniv.c2pRotateCamera(deltarot.x, deltarot.y, deltarot.z);
                    _puniv.c2pMoveCamera(deltapos.x, deltapos.y, deltapos.z);
                } else {
                    // System.out.println("CUniv: It is not my camera this one ");
                }
            }
        }

    }
   @Override
    public void delateObject(String objname) {


        CObject objtodelete = null;
        for (CObject co : _auniv.getLiCntrlObject()) {
            if (co.getName().equals(objname)) {
                objtodelete = co;
                break;
            }
        }
        if (objtodelete != null) {
            _puniv.removeObject(objtodelete.getPrentation().get3DPresentation(), objname);
            _auniv.removeObject(objtodelete);
        }
    }
    @Override
    public void p2cCreateObject(Vector3d deltaTrans, Vector3d delatRot,
            int geom, boolean isVrml, String _vrmlPath) {

        cli_prx.reQ2ServerCreate(deltaTrans, delatRot, geom, isVrml, _vrmlPath);
    }
    @Override
    public void p2cUpdateObject(String objname, Vector3d deltapos, Vector3d deltarot) {
        
        cli_prx.reQ2ServerUpdate(objname, deltapos, deltarot);

    }
    @Override
    public void p2cDeleteObject(String objName) {

        cli_prx.reQ2ServerDelete(objName);
    }
 
}
