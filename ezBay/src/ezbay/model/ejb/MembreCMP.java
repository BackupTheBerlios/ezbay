/*
 * Generated by XDoclet - Do not edit!
 */
package ezbay.model.ejb;

/**
 * CMP layer for Membre.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public abstract class MembreCMP
   extends ezbay.model.ejb.MembreBean
   implements javax.ejb.EntityBean
{

   /**
    * Generated ejbPostCreate for corresponding ejbCreate method.
    *
    * @see #ejbCreate(ezbay.model.interfaces.MembreDTO membreDTO)
    */
   public void ejbPostCreate(ezbay.model.interfaces.MembreDTO membreDTO)
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

      MembreDTO = null;
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

   private ezbay.model.interfaces.MembreDTO MembreDTO = null;

   public ezbay.model.interfaces.MembreDTO getMembreDTO()
   {
      MembreDTO = new ezbay.model.interfaces.MembreDTO();
      try
         {
            MembreDTO.setId( getId() );
            MembreDTO.setNom( getNom() );

         }
         catch (Exception e)
         {
            throw new javax.ejb.EJBException(e);
         }

	  return MembreDTO;
   }

/* Value Objects END */

   public abstract java.lang.String getId() ;

   public abstract void setId( java.lang.String id ) ;

   public abstract java.lang.String getNom() ;

   public abstract void setNom( java.lang.String nom ) ;

}
