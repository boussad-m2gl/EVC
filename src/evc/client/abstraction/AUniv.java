/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.client.abstraction;
import evc.client.control.CObject;
import evc.client.control.CPointOfView;
import java.util.ArrayList;

/**
 *
 * @author me
 *   Abstraction de l'univers virtuel 
 * 
 */
public class AUniv  implements IAUniv {
    
    // list des objets virtuels 
    private ArrayList<CObject> liCntrlObject;
    // List des points de vue 
    private ArrayList<CPointOfView> _listCPOV;
    
    
    public AUniv() {
         liCntrlObject = new ArrayList<CObject>();
        _listCPOV = new ArrayList<CPointOfView>();
    }
    @Override
    public CObject getCntrlObject(int index){
       return liCntrlObject.get(index);
    }
    
    @Override
     public CPointOfView getCntrlPOV(int index){
       return _listCPOV.get(index);
    }
     
     
    @Override
    public void removeObject(CObject cobj){
      liCntrlObject.remove(cobj);
    } 
    @Override
    public ArrayList<CObject> getLiCntrlObject() {
        return liCntrlObject;
    }

    @Override
    public void setLiCntrlObject(ArrayList<CObject> liCntrlObject) {
        this.liCntrlObject = liCntrlObject;
    }

    @Override
    public ArrayList<CPointOfView> getListCPOV() {
        return _listCPOV;
    }

    @Override
    public void setListCPOV(ArrayList<CPointOfView> _listCPOV) {
        this._listCPOV = _listCPOV;
    }

    @Override
    public void addObject(CObject cobj) {
        liCntrlObject.add(cobj);
    }

    @Override
    public void addPov(CPointOfView cpov) {
          _listCPOV.add(cpov);
    }

 
}
