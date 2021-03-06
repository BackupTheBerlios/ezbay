/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.interfaces;

/**
 * Local interface for Membre.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface MembreLocal
   extends javax.ejb.EJBLocalObject
{

   public java.lang.String getId(  ) ;

   public void setId( java.lang.String id ) ;

   public java.lang.String getNom(  ) ;

   public void setNom( java.lang.String nom ) ;

   public java.lang.String getPrenom(  ) ;

   public void setPrenom( java.lang.String prenom ) ;

   public java.lang.String getPseudo(  ) ;

   public void setPseudo( java.lang.String pseudo ) ;

   public java.lang.String getPassword(  ) ;

   public void setPassword( java.lang.String password ) ;

   public java.lang.String getEmail(  ) ;

   public void setEmail( java.lang.String email ) ;

   public java.lang.String getAdresse(  ) ;

   public void setAdresse( java.lang.String adresse ) ;

   public java.lang.String getCodePostal(  ) ;

   public void setCodePostal( java.lang.String codePostal ) ;

   public java.lang.String getVille(  ) ;

   public void setVille( java.lang.String ville ) ;

   public java.lang.String getPays(  ) ;

   public void setPays( java.lang.String pays ) ;

   public java.lang.String getTelephoneFixe(  ) ;

   public void setTelephoneFixe( java.lang.String telephoneFixe ) ;

   public java.lang.String getTelephonePortable(  ) ;

   public void setTelephonePortable( java.lang.String telephonePortable ) ;

   public java.util.Date getDateNaissance(  ) ;

   public void setDateNaissance( java.util.Date dateNaissance ) ;

   public axlomoso.ezbay.model.interfaces.VendeurLocal getVendeurLocal(  ) ;

   public void setVendeurLocal( axlomoso.ezbay.model.interfaces.VendeurLocal vendeurLocal ) ;

   public axlomoso.ezbay.model.interfaces.ClientLocal getClientLocal(  ) ;

   public void setClientLocal( axlomoso.ezbay.model.interfaces.ClientLocal clientLocal ) ;

   public axlomoso.ezbay.model.interfaces.MembreDTO getMembreDTO(  ) ;

   public java.lang.String updateMembre( axlomoso.ezbay.model.interfaces.MembreDTO membreDTO ) throws java.lang.Exception;

}
