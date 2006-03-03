/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.interfaces;

/**
 * Remote interface for VendeurFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface VendeurFacade
   extends javax.ejb.EJBObject
{

   public axlomoso.ezbay.model.interfaces.VendeurDTO saveVendeur( axlomoso.ezbay.model.interfaces.VendeurDTO vendeurDTO )
      throws java.lang.Exception, java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.VendeurDTO getVendeur( java.lang.String vendeurId )
      throws java.lang.Exception, java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.MembreDTO getMembre( java.lang.String vendeurId )
      throws java.lang.Exception, java.rmi.RemoteException;

   public java.util.Collection getVendeurs(  )
      throws java.rmi.RemoteException;

   public java.util.Collection getArticlesEnAttente( java.lang.String vendeurId )
      throws java.rmi.RemoteException;

   public java.util.Collection getArticlesEnVente( java.lang.String vendeurId )
      throws java.rmi.RemoteException;

   public java.util.Collection getArticlesVendus( java.lang.String vendeurId )
      throws java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.ArticleDTO saveArticle( java.lang.String vendeurId,axlomoso.ezbay.model.interfaces.ArticleDTO articleDTO,java.lang.String categorieId )
      throws axlomoso.ezbay.exceptions.VendeurInconnuException, java.lang.Exception, java.rmi.RemoteException;

   public void removeArticle( java.lang.String vendeurId,java.lang.String articleId )
      throws axlomoso.ezbay.exceptions.ArticleProprietaireException, java.rmi.RemoteException;

   public void retirerArticle( java.lang.String vendeurId,java.lang.String articleId )
      throws axlomoso.ezbay.exceptions.ArticleEnEnchereException, axlomoso.ezbay.exceptions.ArticleVenduException, axlomoso.ezbay.exceptions.ArticleProprietaireException, java.rmi.RemoteException;

   public void mettreEnVenteArticle( java.lang.String vendeurId,java.lang.String articleId )
      throws axlomoso.ezbay.exceptions.ArticleProprietaireException, java.rmi.RemoteException;

}
