/*
 * Generated by XDoclet - Do not edit!
 */
package ezbay.model.interfaces;

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

   public ezbay.model.interfaces.ClientDTO createClient(  )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * An example business method
    * @throws EJBException Thrown if method fails due to system-level error.    */
   public void replaceWithRealBusinessMethod(  )
      throws java.rmi.RemoteException;

}
