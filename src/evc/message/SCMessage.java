/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.message;

import javax.vecmath.Vector3d;

/**
 *
 * @author eagle
 */
public class SCMessage implements java.io.Serializable{
    
    private  String _idMessage; // message conserne un objet
    private  int _operationType;
    private  Vector3d _delta_trans;
    private  Vector3d _delta_rot;
   
    private int _geometry;  // geometry of the object  to create 
    private boolean _isVrmlFile=false;
    private String _obj_path;  // path ob the object in case of VRML file

    public int getGeometry() {
        return _geometry;
    }

    public void setGeometry(int geometry) {
        this._geometry = geometry;
    }

    public boolean isIsVrmlFile() {
        return _isVrmlFile;
    }

    public void setIsVrmlFile(boolean isVrmlFile) {
        this._isVrmlFile = isVrmlFile;
    }

    public String getObj_path() {
        return _obj_path;
    }

    public void setObj_path(String obj_path) {
        this._obj_path = obj_path;
    }
    
  
    public Vector3d getDelta_trans() {
        return _delta_trans;
    }

    public void setDelta_trans(Vector3d _delta_trans) {
        this._delta_trans = _delta_trans;
    }

    public Vector3d getDelta_rot() {
        return _delta_rot;
    }

    public int getType() {
        return _typeObjet;
    }
    
    public void setDelta_rot(Vector3d _delta_rot) {
        this._delta_rot = _delta_rot;
    }
    
    public SCMessage(String idMessage, int operationType, Vector3d delta_trans,Vector3d delta_rot, int geom,
            Boolean isvrml, String path_ifvrml) {
       
         this._idMessage = idMessage;
         this._operationType = operationType;
        _delta_trans=delta_trans;
        _delta_rot=delta_rot;
        
        _geometry=geom;  // geometry of the object  to create 
        _isVrmlFile=isvrml;
        _obj_path=path_ifvrml;
        
    }
    
    public SCMessage(String idMessage, int operationType, Vector3d delta_trans,Vector3d delta_rot, int typeObjet ) {
        this._idMessage = idMessage;
        this._operationType = operationType;
        _delta_trans=delta_trans;
        _delta_rot=delta_rot;
        _typeObjet=typeObjet;
    }

    public String getIdMessage() {
        return _idMessage;
    }

    public void setIdMessage(String idMessage) {
        this._idMessage = idMessage;
    }

    public int getOperationType() {
        return _operationType;
    }

    public void setOperationType(int operationType) {
        this._operationType = operationType;
    }

  
    
    
}
