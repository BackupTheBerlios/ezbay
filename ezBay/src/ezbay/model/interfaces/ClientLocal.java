/*
 * Generated by XDoclet - Do not edit!
 */
package ezbay.model.interfaces;

/**
 * Local interface for Client.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ClientLocal
   extends javax.ejb.EJBLocalObject
{

   public java.lang.String getId(  ) ;

   public void setId( java.lang.String id ) ;

   public ezbay.model.interfaces.MembreLocal getMembreLocal(  ) ;

   public void setMembreLocal( ezbay.model.interfaces.MembreLocal membreLocal ) ;

   public ezbay.model.interfaces.ClientDTO getClientDTO(  ) ;

}
