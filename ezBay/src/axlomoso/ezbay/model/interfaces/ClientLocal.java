/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.interfaces;

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

   public axlomoso.ezbay.model.interfaces.MembreLocal getMembreLocal(  ) ;

   public void setMembreLocal( axlomoso.ezbay.model.interfaces.MembreLocal membreLocal ) ;

   public axlomoso.ezbay.model.interfaces.ClientDTO getClientDTO(  ) ;

}
