/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.interfaces;

/**
 * Local interface for VendeurFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface VendeurFacadeLocal
   extends javax.ejb.EJBLocalObject
{

   public axlomoso.ezbay.model.interfaces.VendeurDTO saveVendeur( axlomoso.ezbay.model.interfaces.VendeurDTO vendeurDTO ) throws java.lang.Exception;

   public axlomoso.ezbay.model.interfaces.VendeurDTO getVendeur( java.lang.String vendeurId ) throws java.lang.Exception;

   public axlomoso.ezbay.model.interfaces.MembreDTO getMembre( java.lang.String vendeurId ) throws java.lang.Exception;

   public java.util.Collection getArticlesEnAttente( java.lang.String vendeurId ) ;

   public java.util.Collection getArticlesEnVente( java.lang.String vendeurId ) ;

   public java.util.Collection getArticlesVendus( java.lang.String vendeurId ) ;

   public axlomoso.ezbay.model.interfaces.ArticleDTO saveArticle( java.lang.String vendeurId,axlomoso.ezbay.model.interfaces.ArticleDTO articleDTO,java.lang.String categorieId ) throws axlomoso.ezbay.exceptions.VendeurInconnuException, java.lang.Exception;

   public void removeArticle( java.lang.String vendeurId,java.lang.String articleId ) throws axlomoso.ezbay.exceptions.ArticleEnVenteException, axlomoso.ezbay.exceptions.ArticleVenduException, java.lang.Exception;

   public void retirerArticle( java.lang.String vendeurId,java.lang.String articleId ) throws axlomoso.ezbay.exceptions.ArticleEnEnchereException, axlomoso.ezbay.exceptions.ArticleVenduException, axlomoso.ezbay.exceptions.ArticleProprietaireException;

   public void mettreEnVenteArticle( java.lang.String vendeurId,java.lang.String articleId ) throws axlomoso.ezbay.exceptions.ArticleProprietaireException;

}
