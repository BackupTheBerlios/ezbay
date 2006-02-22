/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.interfaces;

/**
 * Local interface for Article.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ArticleLocal
   extends javax.ejb.EJBLocalObject
{

   public java.lang.String getId(  ) ;

   public void setId( java.lang.String id ) ;

   public java.lang.Boolean getEnVente(  ) ;

   public void setEnVente( java.lang.Boolean envente ) ;

   public java.lang.String getLibelle(  ) ;

   public void setLibelle( java.lang.String libelle ) ;

   public java.lang.String getMarque(  ) ;

   public void setMarque( java.lang.String marque ) ;

   public java.lang.String getModele(  ) ;

   public void setModele( java.lang.String modele ) ;

   public java.lang.Double getPrixVente(  ) ;

   public void setPrixVente( java.lang.Double prixVente ) ;

   public java.lang.Integer getAnneeFabrication(  ) ;

   public void setAnneeFabrication( java.lang.Integer anneeFabrication ) ;

   public java.lang.String getDescription(  ) ;

   public void setDescription( java.lang.String description ) ;

   public java.util.Date getDateLimite(  ) ;

   public void setDateLimite( java.util.Date dateLimite ) ;

   public axlomoso.ezbay.model.interfaces.VendeurLocal getVendeurLocal(  ) ;

   public void setVendeurLocal( axlomoso.ezbay.model.interfaces.VendeurLocal vendeurLocal ) ;

   public axlomoso.ezbay.model.interfaces.CategorieLocal getCategorieLocal(  ) ;

   public void setCategorieLocal( axlomoso.ezbay.model.interfaces.CategorieLocal categorieLocal ) ;

   public java.util.Collection getEnchereLocal(  ) ;

   public void setEnchereLocal( java.util.Collection enchereLocal ) ;

   public axlomoso.ezbay.model.interfaces.ActionTransactionLocal getActionTransactionLocal(  ) ;

   public void setActionTransactionLocal( axlomoso.ezbay.model.interfaces.ActionTransactionLocal actionTransactionLocal ) ;

   public axlomoso.ezbay.model.interfaces.ArticleDTO getArticleDTO(  ) ;

   public java.lang.String updateArticle( axlomoso.ezbay.model.interfaces.ArticleDTO articleDTO ) throws java.lang.Exception;

}
