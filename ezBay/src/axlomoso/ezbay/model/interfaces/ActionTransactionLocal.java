/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.interfaces;

/**
 * Local interface for ActionTransaction.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ActionTransactionLocal
   extends javax.ejb.EJBLocalObject
{

   public java.lang.String getId(  ) ;

   public void setId( java.lang.String id ) ;

   public java.util.Date getDate(  ) ;

   public void setDate( java.util.Date date ) ;

   public java.lang.Double getMontant(  ) ;

   public void setMontant( java.lang.Double montant ) ;

   public java.lang.String getAvis(  ) ;

   public void setAvis( java.lang.String avis ) ;

   public axlomoso.ezbay.model.interfaces.ArticleLocal getArticleLocal(  ) ;

   public void setArticleLocal( axlomoso.ezbay.model.interfaces.ArticleLocal articleLocal ) ;

   public axlomoso.ezbay.model.interfaces.ClientLocal getClientLocal(  ) ;

   public void setClientLocal( axlomoso.ezbay.model.interfaces.ClientLocal clientLocal ) ;

   public axlomoso.ezbay.model.interfaces.ActionTransactionDTO getActionTransactionDTO(  ) ;

}
