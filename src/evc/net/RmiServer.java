/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evc.net;

import evc.server.C2SImple;
import evc.server.ServerProxy;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * 
 * @author me
 * 
 * Classe RMI coté serveur 
 */
public  class RmiServer {

    int thisPort;
    String thisAddress;
    Registry registry; // rmi registry for lookup the remote objects.
    
       public RmiServer(ServerProxy prx) throws RemoteException {
            C2SImple obj = new C2SImple(prx);
        try {
            // get the address of this host.
            thisAddress = (InetAddress.getByName(ConfigReseau.RMI_IP_ADR)).toString();
        } catch (Exception e) {
           throw new RemoteException("can't get inet address.");
        }
        thisPort = ConfigReseau.RMI_PORT_NUMBER; // this port(registry’s port)
        System.out.println("this address=" + thisAddress + ",port=" + thisPort);
       try {
            // create the registry and bind the name and object.
            registry = LocateRegistry.createRegistry(thisPort);
            registry.rebind(ConfigReseau.BINDNAME, obj);
        } catch (RemoteException e) {
            throw e;
        }
        }
  }
