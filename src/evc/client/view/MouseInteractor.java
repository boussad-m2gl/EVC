/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.client.view;


import javax.media.j3d.* ;
import javax.vecmath.* ;
import java.awt.* ;
import java.awt.event.* ;
import java.util.Enumeration ;
import com.sun.j3d.utils.picking.* ;

class MouseInteractor extends Behavior {

	private BranchGroup branche ;
	private WakeupOr wEvents ;
	private int buttonsInUse ;
	private boolean button1Pressed, button2Pressed, button3Pressed ;
	private TransformGroup objectInInteraction ;
	int x1, y1, x2, y2 ;

        PUniv  _puniv; 
        
	public MouseInteractor (BranchGroup b, PUniv  puniv) {
		branche = b ;
                _puniv = puniv;
	}

	public void initialize () {
		WakeupOnAWTEvent wAWTEvent = new WakeupOnAWTEvent (
		      AWTEvent.MOUSE_EVENT_MASK) ;
		WakeupOnAWTEvent wAWTEvent2 = new WakeupOnAWTEvent (
		      AWTEvent.MOUSE_MOTION_EVENT_MASK) ;
		WakeupCriterion [] conditions = { wAWTEvent, wAWTEvent2 } ;
		wEvents = new WakeupOr (conditions) ;
		wakeupOn (wEvents) ;
		buttonsInUse = 0 ;
		button1Pressed = false ;
		button2Pressed = false ;
		button3Pressed = false ;
	}

	@SuppressWarnings ("unchecked")
   public void processStimulus (Enumeration criteria) {
		while (criteria.hasMoreElements ()) {
			WakeupOnAWTEvent w = (WakeupOnAWTEvent)criteria.nextElement () ;
			AWTEvent events[] = w.getAWTEvent () ;
			for (int i = 0 ; i < events.length ; i++) {
				// System.out.println ("" + events [i] + " " + events [i].getSource
				// ()) ;
				if (events [i].getID () == MouseEvent.MOUSE_PRESSED) {
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON1) {
						button1Pressed = true ;
					}
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON2) {
						button2Pressed = true ;
					}
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON3) {
						button3Pressed = true ;
					}
					if (buttonsInUse == 0) {
						PickCanvas pickShape = new PickCanvas ((Canvas3D)events [i]
						      .getSource (), branche) ;
						pickShape.setShapeLocation ((MouseEvent)events [i]) ;
						x1 = ((MouseEvent)events [i]).getX () ;
						y1 = ((MouseEvent)events [i]).getY () ;
						PickResult [] sgPath = pickShape.pickAllSorted () ;
						if (sgPath != null) {
							// System.out.println (sgPath [0]) ;
							try {
								// System.out.println (sgPath [0].getObject ()) ;
								objectInInteraction = (TransformGroup)sgPath [0]
								      .getNode (PickResult.TRANSFORM_GROUP) ;
							} catch (Exception e) {
								System.out.println (e) ;
							}
						}
					}
					buttonsInUse++ ;
				} else if (events [i].getID () == MouseEvent.MOUSE_RELEASED) {
					buttonsInUse-- ;
					if (buttonsInUse == 0) {
						objectInInteraction = null ;
					}
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON1) {
						button1Pressed = false ;
					}
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON2) {
						button2Pressed = false ;
					}
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON3) {
						button3Pressed = false ;
					}
				} else if (events [i].getID () == MouseEvent.MOUSE_DRAGGED) {
					if (objectInInteraction != null) {
						double dx = 0, dy = 0, dz = 0 ;
						double dh = 0, dp = 0, dr = 0 ;
						x2 = ((MouseEvent)events [i]).getX () ;
						y2 = ((MouseEvent)events [i]).getY () ;
						if (button1Pressed) { // rotation
							dh = Math.PI * (x2 - x1) / 40.0 ;
							dp = Math.PI * (y1 - y2) / 40.0 ;
							dr = (dh - dp) / 2.0 ;
						}
						if (button2Pressed) { // zoom
							dz = (x1 - x2 + y2 - y1) / 40.0 ;
						}
						if (button3Pressed) { // translation dans le plan de l'écran
							dx = (x2 - x1) / 40.0 ;
							dy = (y1 - y2) / 40.0 ;
						}
						/*   replace this by clalling to the server cuz these are for local intercation */ 
                                                //translate (dx, dy, dz) ;
					        ///rotate (dh, dp, dr) ;
                                                
                                                
                                                 Vector3d deltapos =  new  Vector3d(dx,dy,dz);
                                                 Vector3d   delatrot =  new  Vector3d(dh,dp,dr);
                                                _puniv.mouse2PupdateObject(objectInInteraction.getName(),deltapos, delatrot);
                                                
                                                
                                               // _puniv.updateObject(dx,dy,dz,dh,dp,dr,objectInInteraction.getName());
                                                
                                                 
                                               /* System.out.println("__________________________________________________________");
                                                System.out.println("Interaction on the object : "+objectInInteraction.getName());
                                                System.out.println(" Transform value : "+ "trans: x ="+dx+" y="+dy+"  z="+dz );
                                                System.out.println(" Rotate value : "+ "trans: dh ="+dh+" dy="+dy+"  dz="+dz );
                                                System.out.println("__________________________________________________________");
                                                */
						x1 = x2 ;
						y1 = y2 ;
					}
				}
			}
		}
		wakeupOn (wEvents) ;
	}

	protected void rotate (double dh, double dp, double dr) {
		Transform3D oldT3D = new Transform3D () ;
		objectInInteraction.getTransform (oldT3D) ;
		Vector3d rotate = new Vector3d () ;
		rotate.set (dh, dp, dr) ;
		Transform3D localT3D = new Transform3D () ;
		localT3D.setEuler (rotate) ;
		Transform3D newT3D = new Transform3D () ;
		newT3D.mul (oldT3D, localT3D) ;
		objectInInteraction.setTransform (newT3D) ;
	}

	protected void translate (double dx, double dy, double dz) {
		Transform3D oldT3D = new Transform3D () ;
		objectInInteraction.getTransform (oldT3D) ;
		Vector3d translate = new Vector3d () ;
		translate.set (dx, dy, dz) ;
		Transform3D localT3D = new Transform3D () ;
		localT3D.setTranslation (translate) ;
		Transform3D newT3D = new Transform3D () ;
		// newT3D.mul (oldT3D, localT3D) ;
		newT3D.mul (localT3D, oldT3D) ;
		objectInInteraction.setTransform (newT3D) ;
	}

}