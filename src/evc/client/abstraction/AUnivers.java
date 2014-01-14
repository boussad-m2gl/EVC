/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.client.abstraction;
import java.util.Collection;

/**
 *
 * @author eagle
 */
public class AUnivers {
    
    
    
     private Collection <AObject> objets;
    
    
    
     public AUnivers() {
    
     }

    public Collection<AObject> getObjets() {
        return objets;
    }

    public void setObjets(Collection<AObject> objets) {
        this.objets = objets;
    }
     
    
     
    
}
