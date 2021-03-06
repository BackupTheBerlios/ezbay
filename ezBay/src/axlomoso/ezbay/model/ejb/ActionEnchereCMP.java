/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.ejb;

/**
 * CMP layer for ActionEnchere.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public abstract class ActionEnchereCMP
   extends axlomoso.ezbay.model.ejb.ActionEnchereBean
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

      ActionEnchereDTO = null;
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

   private axlomoso.ezbay.model.interfaces.ActionEnchereDTO ActionEnchereDTO = null;

   public axlomoso.ezbay.model.interfaces.ActionEnchereDTO getActionEnchereDTO()
   {
      ActionEnchereDTO = new axlomoso.ezbay.model.interfaces.ActionEnchereDTO();
      try
         {
            ActionEnchereDTO.setId( getId() );
            ActionEnchereDTO.setDate( getDate() );
            ActionEnchereDTO.setMontant( getMontant() );
            ActionEnchereDTO.setDateLimite( getDateLimite() );

         }
         catch (Exception e)
         {
            throw new javax.ejb.EJBException(e);
         }

	  return ActionEnchereDTO;
   }

/* Value Objects END */

   public abstract java.lang.String getId() ;

   public abstract void setId( java.lang.String id ) ;

   public abstract java.util.Date getDate() ;

   public abstract void setDate( java.util.Date date ) ;

   public abstract java.lang.Double getMontant() ;

   public abstract void setMontant( java.lang.Double montant ) ;

   public abstract java.util.Date getDateLimite() ;

   public abstract void setDateLimite( java.util.Date dateLimite ) ;

}
