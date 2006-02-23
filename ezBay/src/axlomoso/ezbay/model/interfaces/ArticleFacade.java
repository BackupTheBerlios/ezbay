/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.interfaces;

/**
 * Remote interface for ArticleFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ArticleFacade
   extends javax.ejb.EJBObject
{

   public axlomoso.ezbay.model.interfaces.ArticleDTO getArticle( java.lang.String articleId )
      throws java.lang.Exception, java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.VendeurDTO getVendeurDTO( java.lang.String articleId )
      throws java.lang.Exception, java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.CategorieDTO getCategorieDTO( java.lang.String articleId )
      throws java.lang.Exception, java.rmi.RemoteException;

   public java.util.Collection getAllArticles(  )
      throws java.rmi.RemoteException;

   public java.util.Collection rechercherArticlesEnVente( java.lang.String libcategorie,java.lang.String libelle,java.lang.String marque,java.lang.String modele,java.lang.Double prixVenteMin,java.lang.Double prixVenteMax,java.lang.Integer anneeFabrication,java.util.Date dateLimite )
      throws java.rmi.RemoteException;

   public java.util.Collection getArticlesEnVenteByVendeur( java.lang.String vendeurId )
      throws java.rmi.RemoteException;

   public java.util.Collection getArticlesEnAttenteByVendeur( java.lang.String vendeurId )
      throws java.rmi.RemoteException;

   public java.util.Collection getArticlesVendusByVendeur( java.lang.String vendeurId )
      throws java.rmi.RemoteException;

   public java.util.Collection getArticlesEnVenteByCategorie( java.lang.String categorieId )
      throws java.rmi.RemoteException;

   public java.util.Collection rechercherArticles( java.lang.String libcategorie,java.lang.String libelle,java.lang.String marque,java.lang.String modele,java.lang.Double prixVenteMin,java.lang.Double prixVenteMax,java.lang.Integer anneeFabrication,java.util.Date dateLimite )
      throws java.rmi.RemoteException;

   public java.util.Collection getArticlesByVendeur( java.lang.String vendeurId )
      throws java.rmi.RemoteException;

   public java.util.Collection getArticlesByCategorie( java.lang.String categorieId )
      throws java.rmi.RemoteException;

   public boolean isArticleEnAttente( java.lang.String articleId )
      throws java.rmi.RemoteException;

   public boolean isArticleEnVente( java.lang.String articleId )
      throws java.rmi.RemoteException;

   public boolean isArticleVendu( java.lang.String articleId )
      throws java.rmi.RemoteException;

   public boolean isArticleEnAttente( axlomoso.ezbay.model.interfaces.ArticleDTO articleDTO )
      throws java.rmi.RemoteException;

   public boolean isArticleEnVente( axlomoso.ezbay.model.interfaces.ArticleDTO articleDTO )
      throws java.rmi.RemoteException;

   public boolean isArticleVendu( axlomoso.ezbay.model.interfaces.ArticleDTO articleDTO )
      throws java.rmi.RemoteException;

}
