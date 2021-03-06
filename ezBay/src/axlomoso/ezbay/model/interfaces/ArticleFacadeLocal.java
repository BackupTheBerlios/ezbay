/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.interfaces;

/**
 * Local interface for ArticleFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ArticleFacadeLocal
   extends javax.ejb.EJBLocalObject
{

   public void terminerVente( java.lang.String articleId ) ;

   public void deposerTransactionAvis( java.lang.String articleId,java.lang.String avis ) ;

   public axlomoso.ezbay.model.interfaces.ArticleDTO saveArticle( java.lang.String vendeurId,axlomoso.ezbay.model.interfaces.ArticleDTO articleDTO,java.lang.String categorieId ) throws java.lang.Exception;

   public void removeArticle( java.lang.String articleId ) throws axlomoso.ezbay.exceptions.ArticleEnVenteException, axlomoso.ezbay.exceptions.ArticleVenduException, java.lang.Exception;

   public void retirerArticle( java.lang.String articleId ) throws java.lang.Exception;

   public void mettreEnVenteArticle( java.lang.String articleId ) throws axlomoso.ezbay.exceptions.ArticleVenduException, java.lang.Exception;

   public java.util.Collection getArticlesEnVenteByVendeur( java.lang.String vendeurId ) ;

   public java.util.Collection getArticlesEnAttenteByVendeur( java.lang.String vendeurId ) ;

   public java.util.Collection getArticlesVendusByVendeur( java.lang.String vendeurId ) ;

   public java.util.Collection getArticlesEnVenteByCategorie( java.lang.String categorieId ) ;

   public axlomoso.ezbay.model.interfaces.ActionEnchereDTO getDerniereEnchere( java.lang.String articleId ) ;

   public java.lang.Integer getNbEncheres( java.lang.String articleId ) ;

   public axlomoso.ezbay.model.interfaces.ClientDTO getAcquereur( java.lang.String articleId ) ;

   public axlomoso.ezbay.model.interfaces.ActionTransactionDTO getTransaction( java.lang.String articleId ) ;

   public axlomoso.ezbay.model.interfaces.ClientDTO getDernierEncherisseur( java.lang.String articleId ) ;

   public boolean isArticleEnEnchere( java.lang.String articleId ) ;

   public boolean isArticleVendu( java.lang.String articleId ) ;

   public axlomoso.ezbay.model.interfaces.ActionEnchereDTO encherir( axlomoso.ezbay.model.interfaces.ActionEnchereDTO enchereDTO,java.lang.String articleId,java.lang.String clientId ) throws axlomoso.ezbay.exceptions.ArticlePasEnVenteException, axlomoso.ezbay.exceptions.EnchereInsuffisanteException, java.lang.Exception;

}
