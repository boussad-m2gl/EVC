/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.client.control;

import evc.client.abstraction.APointOfView;
import evc.client.view.PointOfView;
import javax.vecmath.Vector3d;

/**
 *
 * @author me Controle d'un point de vue
 */
public class CPointOfView {

    private APointOfView obj;  //  abstraction d'un point de vue
    private PointOfView pobj;  // Presnetation d'un point de vue 

    public CPointOfView(String name, Vector3d pos, int geom) {
        obj = new APointOfView(pos, name, geom);
        pobj = new PointOfView(this);
    }

    public APointOfView getObj() {
        return obj;
    }

    public Vector3d getPosition() {
        return obj.getPosition();
    }

    public APointOfView getAbstraction() {
        return obj;
    }

    public String getName() {
        return obj.getName();
    }

    public PointOfView getPrentation() {
        return pobj;
    }

    public void setObj(APointOfView obj) {
        this.obj = obj;
    }

    public PointOfView getPobj() {
        return pobj;
    }

    public void setPobj(PointOfView pobj) {
        this.pobj = pobj;
    }

    public void updatePosition(double x, double y, double z) {
        pobj.translate((float) x, (float) y, (float) z);
        updateAbstraction();
    }

    public void updateRotation(double h, double p, double r) {
        pobj.rotate(h, p, r);
        updateAbstraction();
    }

    public void updatePosition(Vector3d deltapos) {
        pobj.translate(deltapos);
        updateAbstraction();
    }

    public void updateRotation(Vector3d deltarot) {
        pobj.rotate(deltarot);
        updateAbstraction();
    }

    public void updateAbstraction() {
        obj.setPosition(pobj.getPosition());
        obj.setRotation(pobj.getRotation());

    }
}
