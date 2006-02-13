/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.ejb;

/**
 * CMP layer for Vendeur.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public abstract class VendeurCMP
   extends axlomoso.ezbay.model.ejb.VendeurBean
   implements javax.ejb.EntityBean
{

   public void ejbLoad() throws javax.ejb.EJBException, java.rmi.RemoteException
   {
      super.ejbLoad();
   }

   public void ejbStore() throws javax.ejb.EJBException, java.rmi.RemoteException
   {
         super.ejbStore();
   }

   public void ejbActivate() throws javax.ejb.EJBException, java.rmi.RemoteException
   {
      super.ejbActivate();
   }

   public void ejbPassivate() throws javax.ejb.EJBException, java.rmi.RemoteException
   {
      super.ejbPassivate();

      VendeurDTO = null;
   }

   public void setEntityContext(javax.ejb.EntityContext ctx) throws javax.ejb.EJBException
   {
      super.setEntityContext(ctx);
   }

   public void unsetEntityContext() throws javax.ejb.EJBException
   {
      super.unsetEntityContext();
   }

   public void ejbRemove() throws javax.ejb.EJBException, java.rmi.RemoteException, javax.ejb.RemoveException
   {
      super.ejbRemove();

   }

 /* Value Objects BEGIN */

   private axlomoso.ezbay.model.interfaces.VendeurDTO VendeurDTO = null;

   public axlomoso.ezbay.model.interfaces.VendeurDTO getVendeurDTO()
   {
      VendeurDTO = new axlomoso.ezbay.model.interfaces.VendeurDTO();
      try
         {
            VendeurDTO.setId( getId() );

         }
         catch (Exception e)
         {
            throw new javax.ejb.EJBException(e);
         }

	  return VendeurDTO;
   }

/* Value Objects END */

   public abstract java.lang.String getId() ;

   public abstract void setId( java.lang.String id ) ;

}
