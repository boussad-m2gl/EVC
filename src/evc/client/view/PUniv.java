/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package evc.client.view;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.universe.SimpleUniverse;
import evc.client.control.CObject;
import evc.client.control.CUniv;
import evc.message.ObjType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;
/**
 *
 * @author anthony
 */
public class PUniv extends javax.swing.JFrame  implements MouseListener, KeyListener{
    
 
    //  
    
    private CUniv _cuniv; 
  
    private Transform3D rotate; 
    private BranchGroup scene;
   // private int currentObjIndex=0;
    private String currentObjName=""; 
    
    private  static final float Translation_Value = 0.05f;
    private  static final double Rotation_Value = 0.1d;
    private SimpleUniverse u = null ;
    
    // Piker
    Canvas3D c ;
    private PickCanvas pickCanvas;

    
    /**
     * Creates new form Viewer3D
     */
    public PUniv(CUniv cuniv) {
        
        _cuniv = cuniv;
        
        this.getContentPane().setMinimumSize(new Dimension(900, 600));
        this.getContentPane().setPreferredSize(new Dimension(900, 600));
        
        initComponents();                                                       // creer jpanel avec boutons
        init();                                                                 // initialiser univers avec code de navigational simple universe

        this.setVisible(true); 
        // Peaker
        
        // To DELETE :  
        
   }

   /* private PUnivers() {
        throw new UnsupportedOperationException("Not yet implemented");
    }*/

    public void init () {
        GraphicsConfiguration config =
	    SimpleUniverse.getPreferredConfiguration () ;

	 c = new Canvas3D (config) ;
	jPanel2.add(c);

        // To delete
            c.addMouseListener(this);
            c.addKeyListener(this);
        // end of to de
        
	// Create a simple scene and attach it to the virtual universe
	u = new SimpleUniverse (c) ;
	 scene = createSceneGraph () ;

 	// création d'un navigateur
	TransformGroup vpTrans = u.getViewingPlatform ().getViewPlatformTransform () ;
	KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior (vpTrans) ;
	keyNavBeh.setSchedulingBounds (new BoundingSphere (new Point3d (), 1000.0)) ;  
	scene.addChild (keyNavBeh) ;

        //  Adding lights 
        
        BoundingSphere worldBounds = new BoundingSphere(new Point3d(0.0, 0.0,
        0.0), // Center
        1000.0); // Extent

    // Set the light color and its influencing bounds
     /*   AmbientLight light = new AmbientLight();
        light.setEnable(true);
        Color3f c3f = new Color3f(Color.BLUE); 
        light.setColor(c3f);
        light.setCapability(AmbientLight.ALLOW_STATE_WRITE);
        light.setCapability(AmbientLight.ALLOW_COLOR_WRITE);
        light.setInfluencingBounds(worldBounds);
       
        scene.addChild(light);*/
        
        
        
         // To delete
          scene.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
          scene.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
          
          scene.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
          /*scene.setCapability(BranchGroup.ALLOW_DETACH);*/
        //
        
        
        // Have Java 3D perform optimizations on this scene graph.
        scene.compile () ;
	u.addBranchGraph (scene) ;

        // PEAKER
         pickCanvas = new PickCanvas(c,  scene);
         pickCanvas.setMode(PickCanvas.BOUNDS);
        
        // This will move the ViewPlatform back a bit so the
        // objects in the scene can be viewed.
        u.getViewingPlatform ().setNominalViewingTransform () ;
        
        
        //  Set the backgound image :
        
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("backImage/space3.jpeg"));
        } catch (IOException ex) {
            Logger.getLogger(PUniv.class.getName()).log(Level.SEVERE, null, ex);
        }
       /* ImageComponent2D imageComponent2D = new ImageComponent2D(ImageComponent2D.FORMAT_RGB, image);
        
        Background background = new Background();
        
        BoundingSphere sphere = new BoundingSphere(new Point3d(0,0,0), 100000);
        background.setApplicationBounds(sphere);
        
        background.setImage(imageComponent2D);
        
        BranchGroup brback = new BranchGroup();
        brback.addChild(background);
        u.getViewingPlatform ().addChild(brback);*/
        
        
   }
    
    public BranchGroup createSceneGraph(){
		      // on creer le BG principale
		
                 BranchGroup objroot = new BranchGroup();
		      //on crée une matrice de transformation pour faire tourner le cub
		 rotate = new Transform3D();
		 rotate.rotY(Math.PI/3.0d); // rotation d'angle Pi/3
		      // on creer un groupe de tranformation  rotate suivant la matrice de tranformation rotate
		//  objTourne = new TransformGroup(rotate);
                 
               // _cuniv.initObjectList();
                Collection<CObject> liobj =  _cuniv.getListObject();
                for(CObject cobj : liobj){
                       // objroot.addChild(cobj.getPrentation().get3DPresentation());
                         objectSelector.addItem(cobj.getName());
                }
   
                  // ---> To delate :  added to  enable the ineraction 
               // 
                MouseInteractor mi = new MouseInteractor (objroot,this) ;
		BoundingSphere bounds = new BoundingSphere (new Point3d (0.0, 0.0, 0.0),
		      1.0) ;
		mi.setSchedulingBounds (bounds) ;
		// Add the behavior to the scene graph
		objroot.addChild (mi) ;
                
                //  ---> end of to delate
                return objroot;
	}
    
  
    
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void butRotXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butRotXActionPerformed
              // _cuniv.rotateObjectX(currentObjName, Rotation_Value);
               _cuniv.p2cUpdateObject( currentObjName,new Vector3d(0,0,0), new Vector3d(Rotation_Value,0,0));
    }//GEN-LAST:event_butRotXActionPerformed

    private void butRotYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butRotYActionPerformed
                   //_cuniv.rotateObjectY(currentObjName, Rotation_Value);
                 //_cuniv.p2cRotateY(currentObjName, (float)Rotation_Value);
        _cuniv.p2cUpdateObject(currentObjName, new Vector3d(0,0,0), new Vector3d(0,Rotation_Value,0));
    }//GEN-LAST:event_butRotYActionPerformed

    private void butRotZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butRotZActionPerformed
               //_cuniv.rotateObjectZ(currentObjName, Rotation_Value);
                // _cuniv.p2cRotateZ(currentObjName, (float)Rotation_Value);
           _cuniv.p2cUpdateObject(currentObjName, new Vector3d(0,0,0), new Vector3d(0,0,Rotation_Value));
    }//GEN-LAST:event_butRotZActionPerformed

    private void bRiseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRiseActionPerformed
           
          _cuniv.p2cUpdateObject(currentObjName, new Vector3d(0,Translation_Value,0), new Vector3d(0,0,0));
           //_cuniv.p2cTranslateUp(currentObjName, Translation_Value);
    }//GEN-LAST:event_bRiseActionPerformed

    private void bLowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLowerActionPerformed
            
            _cuniv.p2cUpdateObject(currentObjName, new Vector3d(0,-Translation_Value,0), new Vector3d(0,0,0));
           //_cuniv.p2cTranslateDown(currentObjName, Translation_Value);
    }//GEN-LAST:event_bLowerActionPerformed

    private void bMoveForwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMoveForwardActionPerformed
             
            _cuniv.p2cUpdateObject(currentObjName, new Vector3d(0,0,Translation_Value), new Vector3d(0,0,0));
             // _cuniv.p2cTranslateForward(currentObjName, Translation_Value);
    }//GEN-LAST:event_bMoveForwardActionPerformed

    private void bslideRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bslideRightActionPerformed
         
               _cuniv.p2cUpdateObject(currentObjName, new Vector3d(Translation_Value,0,0), new Vector3d(0,0,0)); 
               System.out.println(" Client wants to move right ");
               // _cuniv.p2cTranslateRight(currentObjName, Translation_Value);
    }//GEN-LAST:event_bslideRightActionPerformed

    private void bMoveBackwardsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMoveBackwardsActionPerformed
             
             _cuniv.p2cUpdateObject(currentObjName, new Vector3d(0,0,-Translation_Value), new Vector3d(0,0,0));
            // _cuniv.p2cTranslateBackward(currentObjName, Translation_Value);
    }//GEN-LAST:event_bMoveBackwardsActionPerformed

    private void bSlideLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSlideLeftActionPerformed
             
             _cuniv.p2cUpdateObject(currentObjName, new Vector3d(-Translation_Value,0,0), new Vector3d(0,0,0));  
            System.out.println(" Client wants to move left ");
            // _cuniv.p2cTranslateLeft(currentObjName, Translation_Value);
    }//GEN-LAST:event_bSlideLeftActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    /**
     * @param args the command line arguments
     */
  //  public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
    /*    try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PUnivers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PUnivers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PUnivers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PUnivers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
   */
        /* Create and display the form */
    /*    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PUnivers();
            }
        });
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bSlideLeft = new javax.swing.JButton();
        bMoveBackwards = new javax.swing.JButton();
        bslideRight = new javax.swing.JButton();
        bMoveForward = new javax.swing.JButton();
        bLower = new javax.swing.JButton();
        bRise = new javax.swing.JButton();
        butRotZ = new javax.swing.JButton();
        butRotY = new javax.swing.JButton();
        butRotX = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        selectedObject = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        objectSelector = new javax.swing.JComboBox();
        vrmlFileLoader = new javax.swing.JButton();
        butNewObj = new javax.swing.JButton();
        JBtnDelete = new javax.swing.JButton();
        jBtnCone = new javax.swing.JButton();
        jbtnClientPov = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Viewe3D");
        setMinimumSize(new java.awt.Dimension(910, 640));

        jPanel1.setBackground(new java.awt.Color(0, 204, 102));
        jPanel1.setMaximumSize(new java.awt.Dimension(900, 90));
        jPanel1.setMinimumSize(new java.awt.Dimension(900, 90));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 90));

        bSlideLeft.setText("slide left");
        bSlideLeft.setName("butSlideLeft"); // NOI18N
        bSlideLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSlideLeftActionPerformed(evt);
            }
        });

        bMoveBackwards.setText("move backwards");
        bMoveBackwards.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMoveBackwardsActionPerformed(evt);
            }
        });

        bslideRight.setText("slide right");
        bslideRight.setName("butSlideRight"); // NOI18N
        bslideRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bslideRightActionPerformed(evt);
            }
        });

        bMoveForward.setText("move forward");
        bMoveForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMoveForwardActionPerformed(evt);
            }
        });

        bLower.setText("Lower");
        bLower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLowerActionPerformed(evt);
            }
        });

        bRise.setText("Rise");
        bRise.setName("butRise"); // NOI18N
        bRise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRiseActionPerformed(evt);
            }
        });

        butRotZ.setText("Rot Z");
        butRotZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butRotZActionPerformed(evt);
            }
        });

        butRotY.setText("Rot Y ");
        butRotY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butRotYActionPerformed(evt);
            }
        });

        butRotX.setText("Rot X ");
        butRotX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butRotXActionPerformed(evt);
            }
        });

        jButton12.setText("....");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel1.setText("Selected : ");

        jButton17.setText("....");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("....");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        objectSelector.setName("objectSelector"); // NOI18N
        objectSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                objectSelectorActionPerformed(evt);
            }
        });

        vrmlFileLoader.setText("vrml loader");
        vrmlFileLoader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vrmlFileLoaderActionPerformed(evt);
            }
        });

        butNewObj.setText("Cube");
        butNewObj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butNewObjActionPerformed(evt);
            }
        });

        JBtnDelete.setText("Delete");
        JBtnDelete.setName("deleteBtn"); // NOI18N
        JBtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBtnDeleteActionPerformed(evt);
            }
        });

        jBtnCone.setText("Cone");
        jBtnCone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnConeActionPerformed(evt);
            }
        });

        jbtnClientPov.setText("pov");
        jbtnClientPov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClientPovActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(butNewObj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnCone))
                    .addComponent(bSlideLeft, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bMoveForward, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bMoveBackwards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(JBtnDelete)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bslideRight)
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bRise, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bLower, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(objectSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                                .addComponent(jButton12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(butRotX)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnClientPov)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(butRotY)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jButton18)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jButton17))
                                    .addComponent(butRotZ))))
                        .addGap(55, 55, 55))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(vrmlFileLoader)
                        .addGap(102, 102, 102)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectedObject)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(selectedObject)
                            .addComponent(vrmlFileLoader)
                            .addComponent(JBtnDelete)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(butNewObj)
                        .addComponent(jBtnCone))
                    .addComponent(jbtnClientPov))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bMoveForward)
                    .addComponent(bRise)
                    .addComponent(butRotZ)
                    .addComponent(butRotY)
                    .addComponent(butRotX))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bSlideLeft)
                        .addComponent(bMoveBackwards)
                        .addComponent(bslideRight)
                        .addComponent(bLower)
                        .addComponent(jButton17)
                        .addComponent(objectSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton18)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton12.getAccessibleContext().setAccessibleName("to come");

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 300));
        jPanel2.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void objectSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_objectSelectorActionPerformed
          String selectObject= (String)objectSelector.getSelectedItem();
          String indexstr = selectObject.substring(selectObject.length()-1, selectObject.length());
         // currentObjIndex= Integer.valueOf(indexstr)-1;
          selectedObject.setText(indexstr );
          
          currentObjName = selectObject;
    }//GEN-LAST:event_objectSelectorActionPerformed
    public void addObject(TransformGroup transGObj, String idobject) {

       // To delete  :  
       // System.out.println(" PUniv: set the name ob the trans obj to :"+idobject);
        transGObj.setName(idobject);
      //  System.out.println("PUniv : adding object name :" + idobject);
        BranchGroup newbranch = new BranchGroup();
        newbranch.setCapability(BranchGroup.ALLOW_DETACH);
        newbranch.addChild(transGObj);
        scene.addChild(newbranch);


        // selector update

        objectSelector.addItem(idobject);
        if (selectedObject.getText().equals("")) {
            selectedObject.setText(idobject);
            currentObjName = idobject;
        }


    }
    
    
    public void addObjectToSelect(String idobject){
    
       objectSelector.addItem(idobject);
        if (selectedObject.getText().equals("")) {
            selectedObject.setText(idobject);
            currentObjName = idobject;
        }

    }
    public void removeObject(TransformGroup transGObj, String ObjId){
     
        System.out.println("PUniv : removing object name :"+ObjId);
        //System.out.println("PUniv : removing object index :"+objectSelector.getSelectedIndex());
        // BranchGroup branchToremove= new BranchGroup();
        // branchToremove.addChild(transGObj);
        scene.removeChild(transGObj.getParent());
       // objectSelector.remove(objectSelector.getItemAt(WIDTH));
        /*   int indexToDelete=-1;
             for(int i=0; i< objectSelector.getItemCount();i++){
                if (objectSelector.getItemAt(i).equals(ObjId)) {indexToDelete=i; break;} 
             }
             if(indexToDelete != -1) objectSelector.remove(indexToDelete);
        */
    }
    public void mouse2PupdateObject(String objname, Vector3d deltapos, Vector3d deltarot){
              _cuniv.p2cUpdateObject(objname, deltapos, deltarot);
    }
    
    
    private void savedVrmlLoadedActionPerformed(){
        JFileChooser jfc;
        jfc = new JFileChooser();     
        File f = new File(System.getProperty("user.dir"));
        jfc.setCurrentDirectory(f);
       // jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        javax.swing.filechooser.FileFilter filterVrml = new FileTypeFilter(".wrl", "vrml files");
        jfc.setFileFilter(filterVrml);
        jfc.showOpenDialog(this);
        File selFile = jfc.getSelectedFile();
        
        
        VrmlLoader loader = new VrmlLoader () ;
        try {
            Scene scenevrml = loader.load (selFile.getAbsolutePath()) ;

            Transform3D translation = new Transform3D();
            translation.setTranslation(new Vector3d(-1,1,-3));
            TransformGroup objTrans = new TransformGroup(translation);
           // objTrans.setName("Object"+myListOfObjects.size());
            objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    objTrans.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
            objTrans.addChild (scenevrml.getSceneGroup ()) ;
            // add it to the list
            //TODO : CompletER CES LIGNE DE loader VRML 
             //   myListOfObjects.add(objTrans);
             //   objectSelector.addItem("Object"+myListOfObjects.size()+1);
            // add it to the scene
            BranchGroup newbranch = new BranchGroup();
            newbranch.addChild(objTrans);
            //_cuniv.p2cCreateVRMLObject();
            //scene.addChild(newbranch);

        }catch (Exception e){
            
            e.printStackTrace();
        }
    
    }
    private void vrmlFileLoaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vrmlFileLoaderActionPerformed
        JFileChooser jfc;
        jfc = new JFileChooser();     
        File f = new File(System.getProperty("user.dir"));
        jfc.setCurrentDirectory(f);
       // jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        javax.swing.filechooser.FileFilter filterVrml = new FileTypeFilter(".wrl", "vrml files");
        jfc.setFileFilter(filterVrml);
        jfc.showOpenDialog(this);
        File selFile = jfc.getSelectedFile();
        
        // at this point make arequest 
        
        _cuniv.p2cCreateObject(new Vector3d(0,0,0),new Vector3d(0,0,0),-1,true,("vrmlFiles/"+selFile.getName()));
        
       
      /*  VrmlLoader loader = new VrmlLoader () ;
        try {
            Scene scenevrml = loader.load ("vrmlFiles/"+selFile.getName()) ; //selFile.getAbsolutePath(

            Transform3D translation = new Transform3D();
            translation.setTranslation(new Vector3d(-1,1,-3));
            TransformGroup objTrans = new TransformGroup(translation);
           // objTrans.setName("Object"+myListOfObjects.size());
            objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    objTrans.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
            objTrans.addChild (scenevrml.getSceneGroup ()) ;
            // add it to the list
            //TODO : CompletER CES LIGNE DE loader VRML 
             //   myListOfObjects.add(objTrans);
             //   objectSelector.addItem("Object"+myListOfObjects.size()+1);
            // add it to the scene
            BranchGroup newbranch = new BranchGroup();
            newbranch.addChild(objTrans);
            scene.addChild(newbranch);

        }catch (Exception e){
            
            e.printStackTrace();
        }*/
        
    }//GEN-LAST:event_vrmlFileLoaderActionPerformed

    private void butNewObjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butNewObjActionPerformed
         
        _cuniv.p2cCreateObject(new Vector3d(0,0,0),new Vector3d(0,0,0),ObjType.CUBE,false,"");
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        pickCanvas.setShapeLocation(e);
        PickResult result = pickCanvas.pickClosest();

           if (result == null) {

              System.out.println("Nothing picked");

           } else {

                       Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);

                       Shape3D s = (Shape3D)result.getNode(PickResult.SHAPE3D);

                       if (p != null) {

                              System.out.println("p!=null : "+p.getName());
                              System.out.println(" selected objet is : "+p.getName());
                                    String selectObject= p.getName();
                                    currentObjName = selectObject;
                                    selectedObject.setText(currentObjName ); 
                                  
                       } else if (s != null) {
                                    System.out.println(" selected objet is : "+s.getName());
                                    String selectObject= s.getName();
                                    currentObjName = selectObject;
                                  
                                    String indexstr = selectObject.substring(selectObject.length()-1, selectObject.length());
                                    int currentObjIndex= Integer.valueOf(indexstr)-1;
                                    selectedObject.setText(indexstr ); 
                                    objectSelector.setSelectedIndex(currentObjIndex);

                               } else{
                                        System.out.println("null");
                                      }
                  }  // end else

    }
    
    //  The coordinate about witch to move the camera
     public void c2pMoveCamera(double x, double y, double z){
    
        
         Vector3d vectTrans= new Vector3d(x,y,z);
         Transform3D oldT3D = new Transform3D();
         TransformGroup camera = u.getViewingPlatform().getViewPlatformTransform();
         camera.getTransform(oldT3D);  
         Transform3D  localT3D = new Transform3D();
         localT3D.setTranslation(vectTrans);
         Transform3D newT3D = new Transform3D();
         newT3D.mul(oldT3D,localT3D);          
         camera.setTransform(newT3D);
         
        // myPOV.setTransform(newT3D);
        // System.out.println(" New myPOV caracteristics are :  ");
        // System.out.println(" Position  x: " +myPOV.getPosition().x+" y :"+myPOV.getPosition().y+" z:"+myPOV.getPosition().z);
       
    }
     
     
     public void allumerLumiere(){
         TransformGroup camera = u.getViewingPlatform().getViewPlatformTransform();
            
     }

     public void c2pRotateCamera(double r, double y, double z){
         
         
                TransformGroup transCam = u.getViewingPlatform().getViewPlatformTransform();
                Transform3D oldT3D = new Transform3D();
                transCam.getTransform(oldT3D);
                Vector3d rotate = new Vector3d();
                rotate.set(r, y, z);
                Transform3D localT3D = new Transform3D();
                localT3D.setEuler(rotate);
                Transform3D newT3D = new Transform3D();
                newT3D.mul(oldT3D, localT3D);
                transCam.setTransform(newT3D);
     }
     
    @Override
    public void mousePressed(MouseEvent e) {
         //System.out.println("  Mouse pressed ");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("  Mouse relesased ");
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("  Mouse entered ");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("  Mouse exited ");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
      //  System.out.println("  Key taped ....  PUniv");
       if(e.getKeyChar()=='U'  || e.getKeyChar()=='u' ){
             //c2pMoveCamera(0,0.1,0);
            _cuniv.p2cUpdateObject(currentObjName, new Vector3d(0,0.1,0), new Vector3d(0,0,0));
         }
         
         if(e.getKeyChar()=='D'  || e.getKeyChar()=='d' ){
                /// c2pMoveCamera(0,-0.1,0);
               _cuniv.p2cUpdateObject(currentObjName, new Vector3d(0,-0.1,0), new Vector3d(0,0,0));
         }
         
         if(e.getKeyChar()=='L'  || e.getKeyChar()=='l' ){
             // c2pMoveCamera(-0.1,0,0);
               _cuniv.p2cUpdateObject(currentObjName, new Vector3d(-0.1,0,0), new Vector3d(0,0,0));
         }
         
         if(e.getKeyChar()=='R'  || e.getKeyChar()=='r' ){
              //c2pMoveCamera(0.1,0,0);
             _cuniv.p2cUpdateObject(currentObjName, new Vector3d(0.1,0,0), new Vector3d(0,0,0));
         }
         
         if(e.getKeyChar()=='F'  || e.getKeyChar()=='f' ){
              //c2pMoveCamera(0.1,0,0);
             _cuniv.p2cUpdateObject(currentObjName, new Vector3d(0,0,0.1), new Vector3d(0,0,0));
         }
         if(e.getKeyChar()=='B'  || e.getKeyChar()=='b' ){
              //c2pMoveCamera(0.1,0,0);
             _cuniv.p2cUpdateObject(currentObjName, new Vector3d(0,0,-0.1), new Vector3d(0,0,0));
         }
    }

    @Override
    public void keyPressed(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }//GEN-LAST:event_butNewObjActionPerformed

    private void JBtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnDeleteActionPerformed
        System.out.println(" ****   Delete  button clicked ! ");

        if (!currentObjName.contains("object")) {
            JOptionPane.showMessageDialog(this,
                    "Please choose the object to delete.",
                    "Error ",
                    JOptionPane.ERROR_MESSAGE);


        } else {
            System.out.println(" Request to delete the object: " + currentObjName);
            _cuniv.p2cDeleteObject(currentObjName);
        }
    }//GEN-LAST:event_JBtnDeleteActionPerformed

    private void jBtnConeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConeActionPerformed
       
        _cuniv.p2cCreateObject(new Vector3d(0,0,0),new Vector3d(0,0,0),ObjType.CONE,false,"");
    }//GEN-LAST:event_jBtnConeActionPerformed

    private void jbtnClientPovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClientPovActionPerformed
         //  Register POV inside the server  :
         // Récupérere les coordonnées actuelle de la  caméra. 
        
         Vector3d vectTransActuel= new Vector3d(0,0,0);
         Transform3D oldT3D = new Transform3D();
         TransformGroup camera = u.getViewingPlatform().getViewPlatformTransform();
         camera.getTransform(oldT3D);
         oldT3D.get(vectTransActuel);
         // TODO enter rotation mettre dans methode init
        _cuniv.initializePOV(vectTransActuel);
    }//GEN-LAST:event_jbtnClientPovActionPerformed
    
    /**
     *
     *   VRML File filter class
     */
   public class FileTypeFilter  extends FileFilter {
    
    private String extension;
    private String description;
 
        public FileTypeFilter(String extension, String description) {
           this.extension = extension;
            this.description = description;
        }
        @Override
        public boolean accept(File file) {
             if (file.isDirectory()) {
            return true;
        }
        return file.getName().endsWith(extension);
        }

        @Override
        public String getDescription() {
              return description + String.format(" (*%s)", extension);
        }
   }
    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBtnDelete;
    private javax.swing.JButton bLower;
    private javax.swing.JButton bMoveBackwards;
    private javax.swing.JButton bMoveForward;
    private javax.swing.JButton bRise;
    private javax.swing.JButton bSlideLeft;
    private javax.swing.JButton bslideRight;
    private javax.swing.JButton butNewObj;
    private javax.swing.JButton butRotX;
    private javax.swing.JButton butRotY;
    private javax.swing.JButton butRotZ;
    private javax.swing.JButton jBtnCone;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtnClientPov;
    private javax.swing.JComboBox objectSelector;
    private javax.swing.JLabel selectedObject;
    private javax.swing.JButton vrmlFileLoader;
    // End of variables declaration//GEN-END:variables
}

