/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.net;

import evc.client.control.CObject;
import evc.message.OpType;
import evc.server.C2SInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author eagle
 */
public class RmiClient {
    
    
        private C2SInterface rmiServer;
	private Registry registry;
	private String serverAddress;
	private int serverPort;//default 3232
        
        C2SInterface rmiServerInstance;
    
    public RmiClient(){
         // C2SInterface rmiServer;
	Registry registry;
        serverAddress= ConfigReseau.RMI_IP_ADR;
	serverPort=ConfigReseau.RMI_PORT_NUMBER;//default 3232
        
        try{
		// get the “registry”
		registry=LocateRegistry.getRegistry(serverAddress,(new Integer(serverPort)).intValue());
		// look up the remote object
		rmiServerInstance=(C2SInterface)(registry.lookup(ConfigReseau.BINDNAME));
		// call the remote method
               
	}
	catch(RemoteException e){
		e.printStackTrace();
	}
	catch(NotBoundException e){
		e.printStackTrace();
	}
    }
    
    
    public C2SInterface getInstance(){
      return rmiServerInstance;
    }
    
}
