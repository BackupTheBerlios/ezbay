/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.interfaces;

/**
 * Value object for Article.
 *
 * Notice, this object is used to represent the state of an 
 * Article object. This value object
 * Is not connected to the database in any way, it is just a normal object used 
 * as a container for data from an EJB. 
 *
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public class ArticleDTO
   extends java.lang.Object
   implements java.io.Serializable, java.lang.Cloneable 
{

   private java.lang.String id;
   private boolean idHasBeenSet = false;

   private java.lang.String libelle;
   private boolean libelleHasBeenSet = false;

   private java.lang.String marque;
   private boolean marqueHasBeenSet = false;

   private java.lang.String modele;
   private boolean modeleHasBeenSet = false;

   private java.lang.Double prixVente;
   private boolean prixVenteHasBeenSet = false;

   private java.lang.Integer anneeFabrication;
   private boolean anneeFabricationHasBeenSet = false;

   private java.lang.String description;
   private boolean descriptionHasBeenSet = false;

   private java.util.Date dateLimite;
   private boolean dateLimiteHasBeenSet = false;

   private java.lang.String primaryKey;

   public ArticleDTO()
   {
   }

   public ArticleDTO( java.lang.String id,java.lang.String libelle,java.lang.String marque,java.lang.String modele,java.lang.Double prixVente,java.lang.Integer anneeFabrication,java.lang.String description,java.util.Date dateLimite )
   {
       setId(id);
       setLibelle(libelle);
       setMarque(marque);
       setModele(modele);
       setPrixVente(prixVente);
       setAnneeFabrication(anneeFabrication);
       setDescription(description);
       setDateLimite(dateLimite);
	   primaryKey = this.getId();
   }

   /**
    * @deprecated use {@link #clone}
    */
   public ArticleDTO( ArticleDTO otherValue )
   {
	  this.id = otherValue.id;
	  idHasBeenSet = true;
	  this.libelle = otherValue.libelle;
	  libelleHasBeenSet = true;
	  this.marque = otherValue.marque;
	  marqueHasBeenSet = true;
	  this.modele = otherValue.modele;
	  modeleHasBeenSet = true;
	  this.prixVente = otherValue.prixVente;
	  prixVenteHasBeenSet = true;
	  this.anneeFabrication = otherValue.anneeFabrication;
	  anneeFabricationHasBeenSet = true;
	  this.description = otherValue.description;
	  descriptionHasBeenSet = true;
	  this.dateLimite = otherValue.dateLimite;
	  dateLimiteHasBeenSet = true;

	  primaryKey = this.getId();
   }

   public java.lang.String getPrimaryKey()
   {
	  return primaryKey;
   }

   public void setPrimaryKey( java.lang.String primaryKey)
   {
      // it's also nice to update PK object - just in case
      // somebody would ask for it later...
      this.primaryKey = primaryKey;
	  setId( primaryKey );
   }

   public java.lang.String getId()
   {
	  return this.id;
   }

   public void setId( java.lang.String id )
   {
	  this.id = id;
	  idHasBeenSet = true;

      primaryKey = id;
   }

   public boolean idHasBeenSet(){
	  return idHasBeenSet;
   }
   public java.lang.String getLibelle()
   {
	  return this.libelle;
   }

   public void setLibelle( java.lang.String libelle )
   {
	  this.libelle = libelle;
	  libelleHasBeenSet = true;

   }

   public boolean libelleHasBeenSet(){
	  return libelleHasBeenSet;
   }
   public java.lang.String getMarque()
   {
	  return this.marque;
   }

   public void setMarque( java.lang.String marque )
   {
	  this.marque = marque;
	  marqueHasBeenSet = true;

   }

   public boolean marqueHasBeenSet(){
	  return marqueHasBeenSet;
   }
   public java.lang.String getModele()
   {
	  return this.modele;
   }

   public void setModele( java.lang.String modele )
   {
	  this.modele = modele;
	  modeleHasBeenSet = true;

   }

   public boolean modeleHasBeenSet(){
	  return modeleHasBeenSet;
   }
   public java.lang.Double getPrixVente()
   {
	  return this.prixVente;
   }

   public void setPrixVente( java.lang.Double prixVente )
   {
	  this.prixVente = prixVente;
	  prixVenteHasBeenSet = true;

   }

   public boolean prixVenteHasBeenSet(){
	  return prixVenteHasBeenSet;
   }
   public java.lang.Integer getAnneeFabrication()
   {
	  return this.anneeFabrication;
   }

   public void setAnneeFabrication( java.lang.Integer anneeFabrication )
   {
	  this.anneeFabrication = anneeFabrication;
	  anneeFabricationHasBeenSet = true;

   }

   public boolean anneeFabricationHasBeenSet(){
	  return anneeFabricationHasBeenSet;
   }
   public java.lang.String getDescription()
   {
	  return this.description;
   }

   public void setDescription( java.lang.String description )
   {
	  this.description = description;
	  descriptionHasBeenSet = true;

   }

   public boolean descriptionHasBeenSet(){
	  return descriptionHasBeenSet;
   }
   public java.util.Date getDateLimite()
   {
	  return this.dateLimite;
   }

   public void setDateLimite( java.util.Date dateLimite )
   {
	  this.dateLimite = dateLimite;
	  dateLimiteHasBeenSet = true;

   }

   public boolean dateLimiteHasBeenSet(){
	  return dateLimiteHasBeenSet;
   }

   public String toString()
   {
	  StringBuffer str = new StringBuffer("{");

	  str.append("id=" + getId() + " " + "libelle=" + getLibelle() + " " + "marque=" + getMarque() + " " + "modele=" + getModele() + " " + "prixVente=" + getPrixVente() + " " + "anneeFabrication=" + getAnneeFabrication() + " " + "description=" + getDescription() + " " + "dateLimite=" + getDateLimite());
	  str.append('}');

	  return(str.toString());
   }

   /**
    * A Value Object has an identity if the attributes making its Primary Key have all been set. An object without identity is never equal to any other object.
    *
    * @return true if this instance has an identity.
    */
   protected boolean hasIdentity()
   {
	  return idHasBeenSet;
   }

   /**
    *
    * @deprecated use {@link #equals}
    */
   public boolean isIdentical(Object other)
   {
          if (other instanceof ArticleDTO)
          {
                 ArticleDTO that = (ArticleDTO) other;
                 boolean lEquals = true;
                 if( this.id == null )
                 {
                        lEquals = lEquals && ( that.id == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.id.equals( that.id );
                 }
                 if( this.libelle == null )
                 {
                        lEquals = lEquals && ( that.libelle == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.libelle.equals( that.libelle );
                 }
                 if( this.marque == null )
                 {
                        lEquals = lEquals && ( that.marque == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.marque.equals( that.marque );
                 }
                 if( this.modele == null )
                 {
                        lEquals = lEquals && ( that.modele == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.modele.equals( that.modele );
                 }
                 if( this.prixVente == null )
                 {
                        lEquals = lEquals && ( that.prixVente == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.prixVente.equals( that.prixVente );
                 }
                 if( this.anneeFabrication == null )
                 {
                        lEquals = lEquals && ( that.anneeFabrication == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.anneeFabrication.equals( that.anneeFabrication );
                 }
                 if( this.description == null )
                 {
                        lEquals = lEquals && ( that.description == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.description.equals( that.description );
                 }
                 if( this.dateLimite == null )
                 {
                        lEquals = lEquals && ( that.dateLimite == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.dateLimite.equals( that.dateLimite );
                 }

                 return lEquals;
          }
          else
          {
                 return false;
          }
   }

    public boolean equals(Object other) {

        //If it's not the correct type, clearly it isn't equal to this.
        if (!(other instanceof ArticleDTO)) { 
            return false;
        }

        return equals((ArticleDTO) other);
    }

    /**
     * This class is not using strict ordering. This means that the object is not Comparable, and
     * each check for equality will test all members for equality. We do not check collections for
     * equality however, so you would be wise to not use this if you have collection typed EJB References.
     */
    public boolean equals(ArticleDTO that) {

        //try to get lucky.
        if (this == that) {
            return true;
        }
        //this clearly isn't null.
        if(null == that) {
            return false;
        }

        if(this.id != that.id) {

            if( this.id == null || that.id == null ) {
                return false;
            }

            if(!this.id.equals(that.id)) {
                return false;
            }

        }

        if(this.libelle != that.libelle) {

            if( this.libelle == null || that.libelle == null ) {
                return false;
            }

            if(!this.libelle.equals(that.libelle)) {
                return false;
            }

        }

        if(this.marque != that.marque) {

            if( this.marque == null || that.marque == null ) {
                return false;
            }

            if(!this.marque.equals(that.marque)) {
                return false;
            }

        }

        if(this.modele != that.modele) {

            if( this.modele == null || that.modele == null ) {
                return false;
            }

            if(!this.modele.equals(that.modele)) {
                return false;
            }

        }

        if(this.prixVente != that.prixVente) {

            if( this.prixVente == null || that.prixVente == null ) {
                return false;
            }

            if(!this.prixVente.equals(that.prixVente)) {
                return false;
            }

        }

        if(this.anneeFabrication != that.anneeFabrication) {

            if( this.anneeFabrication == null || that.anneeFabrication == null ) {
                return false;
            }

            if(!this.anneeFabrication.equals(that.anneeFabrication)) {
                return false;
            }

        }

        if(this.description != that.description) {

            if( this.description == null || that.description == null ) {
                return false;
            }

            if(!this.description.equals(that.description)) {
                return false;
            }

        }

        if(this.dateLimite != that.dateLimite) {

            if( this.dateLimite == null || that.dateLimite == null ) {
                return false;
            }

            if(!this.dateLimite.equals(that.dateLimite)) {
                return false;
            }

        }

        return true;

    }

    public Object clone() throws java.lang.CloneNotSupportedException {
        ArticleDTO other = (ArticleDTO) super.clone();

        return other;
    }

    public ReadOnlyArticleDTO getReadOnlyArticleDTO() {
        return new ReadOnlyArticleDTO();
    }

    public int hashCode(){
	  int result = 17;
      result = 37*result + ((this.id != null) ? this.id.hashCode() : 0);

      result = 37*result + ((this.libelle != null) ? this.libelle.hashCode() : 0);

      result = 37*result + ((this.marque != null) ? this.marque.hashCode() : 0);

      result = 37*result + ((this.modele != null) ? this.modele.hashCode() : 0);

      result = 37*result + ((this.prixVente != null) ? this.prixVente.hashCode() : 0);

      result = 37*result + ((this.anneeFabrication != null) ? this.anneeFabrication.hashCode() : 0);

      result = 37*result + ((this.description != null) ? this.description.hashCode() : 0);

      result = 37*result + ((this.dateLimite != null) ? this.dateLimite.hashCode() : 0);

	  return result;
    }

    /**
     * Covariant function so the compiler can choose the proper one at compile time,
     * eliminates the need for XDoclet to really understand compiletime typing.
     *
     * Read only collections need to be synchronized. Once we start giving out handles
     * to these collections, they'll be used in other threads sooner or later. 
     */
    private static java.util.Collection wrapCollection(java.util.Collection input) {
        return java.util.Collections.synchronizedCollection(input);
    }
    /**
     * Covariant function so the compiler can choose the proper one at compile time,
     * eliminates the need for XDoclet to really understand compiletime typing.
     *
     * Read only collections need to be synchronized. Once we start giving out handles
     * to these collections, they'll be used in other threads sooner or later. 
     */
    private static java.util.Set wrapCollection(java.util.Set input) {
        return java.util.Collections.synchronizedSet(input);
    }
    /**
     * Covariant function. This is used in covariant form so that the compiler
     * can do some of our conditional branches for us. If I made these functions
     * have different names, then XDoclet would have to choose between them based on 
     * compiletime types, that wouldn't be easy. 
     */
    private static java.util.Collection wrapReadOnly(java.util.Collection input) {
        return java.util.Collections.unmodifiableCollection(input);
    }
    /**
     * Covariant function. This is used in covariant form so that the compiler
     * can do some of our conditional branches for us. If I made these functions
     * have different names, then XDoclet would have to choose between them based on 
     * compiletime types, that wouldn't be easy. 
     */
    private static java.util.Set wrapReadOnly(java.util.Set input) {
        return java.util.Collections.unmodifiableSet(input);
    }

    private final class ReadOnlyArticleDTO 
    implements java.lang.Cloneable, java.io.Serializable 
    {
        private ArticleDTO underlying() {
            return ArticleDTO.this;
        }

       public java.lang.String getId() {
              return underlying().id;
       }

       public java.lang.String getLibelle() {
              return underlying().libelle;
       }

       public java.lang.String getMarque() {
              return underlying().marque;
       }

       public java.lang.String getModele() {
              return underlying().modele;
       }

       public java.lang.Double getPrixVente() {
              return underlying().prixVente;
       }

       public java.lang.Integer getAnneeFabrication() {
              return underlying().anneeFabrication;
       }

       public java.lang.String getDescription() {
              return underlying().description;
       }

       public java.util.Date getDateLimite() {
              return underlying().dateLimite;
       }

        public int hashCode() {
            return 101 * underlying().hashCode();
        }

        public boolean equals(Object o) {
            if(o instanceof ReadOnlyArticleDTO) {
                return this.equals((ReadOnlyArticleDTO) o);
            }
            return false;
        }

        public boolean equals(ReadOnlyArticleDTO that) {
            if(null == that) {
                return false;
            }

            return this.underlying().equals(that.underlying());
        }

    }

}
