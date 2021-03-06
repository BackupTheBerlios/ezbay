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

   public void deposerTransactionAvis( java.lang.String articleId,java.lang.String avis )
      throws java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.ArticleDTO getArticle( java.lang.String articleId )
      throws javax.ejb.FinderException, java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.VendeurDTO getVendeurDTO( java.lang.String articleId )
      throws java.lang.Exception, java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.CategorieDTO getCategorieDTO( java.lang.String articleId )
      throws java.lang.Exception, java.rmi.RemoteException;

   public java.util.Collection rechercherArticlesEnVente( java.lang.String libcategorie,java.lang.String libelle,java.lang.String marque,java.lang.String modele,java.lang.Double prixVenteMin,java.lang.Double prixVenteMax,java.lang.Integer anneeFabrication,java.util.Date dateLimite )
      throws java.rmi.RemoteException;

   public java.util.Collection getArticlesEnVenteByCategorie( java.lang.String categorieId )
      throws java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.ActionEnchereDTO getDerniereEnchere( java.lang.String articleId )
      throws java.rmi.RemoteException;

   public java.lang.Integer getNbEncheres( java.lang.String articleId )
      throws java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.ClientDTO getAcquereur( java.lang.String articleId )
      throws java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.ActionTransactionDTO getTransaction( java.lang.String articleId )
      throws java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.ClientDTO getDernierEncherisseur( java.lang.String articleId )
      throws java.rmi.RemoteException;

   public axlomoso.ezbay.model.interfaces.ActionEnchereDTO encherir( axlomoso.ezbay.model.interfaces.ActionEnchereDTO enchereDTO,java.lang.String articleId,java.lang.String clientId )
      throws axlomoso.ezbay.exceptions.ArticlePasEnVenteException, axlomoso.ezbay.exceptions.EnchereInsuffisanteException, java.lang.Exception, java.rmi.RemoteException;

}
