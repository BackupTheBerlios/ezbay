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

   /**
    * Generated ejbPostCreate for corresponding ejbCreate method.
    *
    * @see #ejbCreate(axlomoso.ezbay.model.interfaces.VendeurDTO vendeurDTO)
    */
   public void ejbPostCreate(axlomoso.ezbay.model.interfaces.VendeurDTO vendeurDTO)
   {
   }

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
            VendeurDTO.setNomProprioCB( getNomProprioCB() );
            VendeurDTO.setNumCB( getNumCB() );
            VendeurDTO.setDateExpirCB( getDateExpirCB() );
            VendeurDTO.setCodeSecuCB( getCodeSecuCB() );

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

   public abstract java.lang.String getNomProprioCB() ;

   public abstract void setNomProprioCB( java.lang.String nomProprioCB ) ;

   public abstract java.lang.String getNumCB() ;

   public abstract void setNumCB( java.lang.String numCB ) ;

   public abstract java.util.Date getDateExpirCB() ;

   public abstract void setDateExpirCB( java.util.Date dateExpirCB ) ;

   public abstract java.lang.String getCodeSecuCB() ;

   public abstract void setCodeSecuCB( java.lang.String codeSecuCB ) ;

}
