/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.interfaces;

/**
 * Remote interface for ClientFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ClientFacade
   extends javax.ejb.EJBObject
{

   public axlomoso.ezbay.model.interfaces.ClientDTO createClient(  )
      throws java.lang.Exception, java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.MembreDTO getMembre( java.lang.String clientId )
      throws java.lang.Exception, java.rmi.RemoteException;

}
