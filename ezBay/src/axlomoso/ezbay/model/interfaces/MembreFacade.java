/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.interfaces;

/**
 * Remote interface for MembreFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface MembreFacade
   extends javax.ejb.EJBObject
{

   public axlomoso.ezbay.model.interfaces.MembreDTO saveMembre( axlomoso.ezbay.model.interfaces.MembreDTO membreDTO )
      throws java.lang.Exception, axlomoso.ezbay.exceptions.PseudoDejaUtiliseException, java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.MembreDTO getMembre( java.lang.String membreId )
      throws java.lang.Exception, java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.MembreDTO getMembre( java.lang.String pseudo,java.lang.String password )
      throws java.rmi.RemoteException;

   public boolean membreExists( java.lang.String pseudo )
      throws java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.VendeurDTO getVendeurDTO( java.lang.String membreId )
      throws java.rmi.RemoteException;

   public void removeMembre( axlomoso.ezbay.model.interfaces.MembreDTO membreDTO )
      throws java.lang.Exception, java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.VendeurDTO saveVendeur( axlomoso.ezbay.model.interfaces.MembreDTO membreDTO,axlomoso.ezbay.model.interfaces.VendeurDTO vendeurDTO )
      throws java.lang.Exception, java.rmi.RemoteException;

   public void createClient( axlomoso.ezbay.model.interfaces.MembreDTO membreDTO )
      throws java.lang.Exception, java.rmi.RemoteException;

   public void removeVendeur( axlomoso.ezbay.model.interfaces.MembreDTO membreDTO )
      throws java.lang.Exception, java.rmi.RemoteException;

   public void removeClient( axlomoso.ezbay.model.interfaces.MembreDTO membreDTO )
      throws java.lang.Exception, java.rmi.RemoteException;

}
