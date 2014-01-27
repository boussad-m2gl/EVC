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
 * @author 
 * 
 *  Interface abstraction univers 
 */
public interface IAUniv {
     
    /**
     * 
     * @return list de object virtuels gérés par l'univer  
     */
    public ArrayList<CObject> getLiCntrlObject();
     /**
      *   Set la liste des object virtuel qui serot érer par l'univers 
      * @param liCntrlObject 
      */
    public void setLiCntrlObject(ArrayList<CObject> liCntrlObject);
     /**
      *   Ajout un object a l'univers
      * @param cobj 
      */
    public void addObject(CObject cobj);
    /**
     * 
     * @return  list des points de vues enregistré dans l'univers 
     */
    public ArrayList<CPointOfView> getListCPOV() ;

    /**
     * 
     * @param _listCPOV  set list des point de vue 
     */
    public void setListCPOV(ArrayList<CPointOfView> _listCPOV);
    /**
     * Ajouter un Point de vue a l'univers 
     * @param cpov : 
     */
    public void addPov(CPointOfView cpov);
    
    /**
     * 
     * @param index
     * @return  l'objet virtuel referencé par cet index 
     */
     public CObject getCntrlObject(int index);
    
      /**
       * 
       * @param index
       * @return l'objet point de vue reference par cet index 
       */
     public CPointOfView getCntrlPOV(int index);
     
     /**
      *   Suppression de l'objet virtuel de l'abstration de l'univers 
      * @param cobj 
      */
    public void removeObject(CObject cobj);
    
}
