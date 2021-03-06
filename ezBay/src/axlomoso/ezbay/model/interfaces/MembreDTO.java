/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.interfaces;

/**
 * Value object for Membre.
 *
 * Notice, this object is used to represent the state of an 
 * Membre object. This value object
 * Is not connected to the database in any way, it is just a normal object used 
 * as a container for data from an EJB. 
 *
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public class MembreDTO
   extends java.lang.Object
   implements java.io.Serializable, java.lang.Cloneable 
{

   private java.lang.String id;
   private boolean idHasBeenSet = false;

   private java.lang.String nom;
   private boolean nomHasBeenSet = false;

   private java.lang.String prenom;
   private boolean prenomHasBeenSet = false;

   private java.lang.String pseudo;
   private boolean pseudoHasBeenSet = false;

   private java.lang.String password;
   private boolean passwordHasBeenSet = false;

   private java.lang.String email;
   private boolean emailHasBeenSet = false;

   private java.lang.String adresse;
   private boolean adresseHasBeenSet = false;

   private java.lang.String codePostal;
   private boolean codePostalHasBeenSet = false;

   private java.lang.String ville;
   private boolean villeHasBeenSet = false;

   private java.lang.String pays;
   private boolean paysHasBeenSet = false;

   private java.lang.String telephoneFixe;
   private boolean telephoneFixeHasBeenSet = false;

   private java.lang.String telephonePortable;
   private boolean telephonePortableHasBeenSet = false;

   private java.util.Date dateNaissance;
   private boolean dateNaissanceHasBeenSet = false;

   private java.lang.String primaryKey;

   public MembreDTO()
   {
   }

   public MembreDTO( java.lang.String id,java.lang.String nom,java.lang.String prenom,java.lang.String pseudo,java.lang.String password,java.lang.String email,java.lang.String adresse,java.lang.String codePostal,java.lang.String ville,java.lang.String pays,java.lang.String telephoneFixe,java.lang.String telephonePortable,java.util.Date dateNaissance )
   {
       setId(id);
       setNom(nom);
       setPrenom(prenom);
       setPseudo(pseudo);
       setPassword(password);
       setEmail(email);
       setAdresse(adresse);
       setCodePostal(codePostal);
       setVille(ville);
       setPays(pays);
       setTelephoneFixe(telephoneFixe);
       setTelephonePortable(telephonePortable);
       setDateNaissance(dateNaissance);
	   primaryKey = this.getId();
   }

   /**
    * @deprecated use {@link #clone}
    */
   public MembreDTO( MembreDTO otherValue )
   {
	  this.id = otherValue.id;
	  idHasBeenSet = true;
	  this.nom = otherValue.nom;
	  nomHasBeenSet = true;
	  this.prenom = otherValue.prenom;
	  prenomHasBeenSet = true;
	  this.pseudo = otherValue.pseudo;
	  pseudoHasBeenSet = true;
	  this.password = otherValue.password;
	  passwordHasBeenSet = true;
	  this.email = otherValue.email;
	  emailHasBeenSet = true;
	  this.adresse = otherValue.adresse;
	  adresseHasBeenSet = true;
	  this.codePostal = otherValue.codePostal;
	  codePostalHasBeenSet = true;
	  this.ville = otherValue.ville;
	  villeHasBeenSet = true;
	  this.pays = otherValue.pays;
	  paysHasBeenSet = true;
	  this.telephoneFixe = otherValue.telephoneFixe;
	  telephoneFixeHasBeenSet = true;
	  this.telephonePortable = otherValue.telephonePortable;
	  telephonePortableHasBeenSet = true;
	  this.dateNaissance = otherValue.dateNaissance;
	  dateNaissanceHasBeenSet = true;

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
   public java.lang.String getNom()
   {
	  return this.nom;
   }

   public void setNom( java.lang.String nom )
   {
	  this.nom = nom;
	  nomHasBeenSet = true;

   }

   public boolean nomHasBeenSet(){
	  return nomHasBeenSet;
   }
   public java.lang.String getPrenom()
   {
	  return this.prenom;
   }

   public void setPrenom( java.lang.String prenom )
   {
	  this.prenom = prenom;
	  prenomHasBeenSet = true;

   }

   public boolean prenomHasBeenSet(){
	  return prenomHasBeenSet;
   }
   public java.lang.String getPseudo()
   {
	  return this.pseudo;
   }

   public void setPseudo( java.lang.String pseudo )
   {
	  this.pseudo = pseudo;
	  pseudoHasBeenSet = true;

   }

   public boolean pseudoHasBeenSet(){
	  return pseudoHasBeenSet;
   }
   public java.lang.String getPassword()
   {
	  return this.password;
   }

   public void setPassword( java.lang.String password )
   {
	  this.password = password;
	  passwordHasBeenSet = true;

   }

   public boolean passwordHasBeenSet(){
	  return passwordHasBeenSet;
   }
   public java.lang.String getEmail()
   {
	  return this.email;
   }

   public void setEmail( java.lang.String email )
   {
	  this.email = email;
	  emailHasBeenSet = true;

   }

   public boolean emailHasBeenSet(){
	  return emailHasBeenSet;
   }
   public java.lang.String getAdresse()
   {
	  return this.adresse;
   }

   public void setAdresse( java.lang.String adresse )
   {
	  this.adresse = adresse;
	  adresseHasBeenSet = true;

   }

   public boolean adresseHasBeenSet(){
	  return adresseHasBeenSet;
   }
   public java.lang.String getCodePostal()
   {
	  return this.codePostal;
   }

   public void setCodePostal( java.lang.String codePostal )
   {
	  this.codePostal = codePostal;
	  codePostalHasBeenSet = true;

   }

   public boolean codePostalHasBeenSet(){
	  return codePostalHasBeenSet;
   }
   public java.lang.String getVille()
   {
	  return this.ville;
   }

   public void setVille( java.lang.String ville )
   {
	  this.ville = ville;
	  villeHasBeenSet = true;

   }

   public boolean villeHasBeenSet(){
	  return villeHasBeenSet;
   }
   public java.lang.String getPays()
   {
	  return this.pays;
   }

   public void setPays( java.lang.String pays )
   {
	  this.pays = pays;
	  paysHasBeenSet = true;

   }

   public boolean paysHasBeenSet(){
	  return paysHasBeenSet;
   }
   public java.lang.String getTelephoneFixe()
   {
	  return this.telephoneFixe;
   }

   public void setTelephoneFixe( java.lang.String telephoneFixe )
   {
	  this.telephoneFixe = telephoneFixe;
	  telephoneFixeHasBeenSet = true;

   }

   public boolean telephoneFixeHasBeenSet(){
	  return telephoneFixeHasBeenSet;
   }
   public java.lang.String getTelephonePortable()
   {
	  return this.telephonePortable;
   }

   public void setTelephonePortable( java.lang.String telephonePortable )
   {
	  this.telephonePortable = telephonePortable;
	  telephonePortableHasBeenSet = true;

   }

   public boolean telephonePortableHasBeenSet(){
	  return telephonePortableHasBeenSet;
   }
   public java.util.Date getDateNaissance()
   {
	  return this.dateNaissance;
   }

   public void setDateNaissance( java.util.Date dateNaissance )
   {
	  this.dateNaissance = dateNaissance;
	  dateNaissanceHasBeenSet = true;

   }

   public boolean dateNaissanceHasBeenSet(){
	  return dateNaissanceHasBeenSet;
   }

   public String toString()
   {
	  StringBuffer str = new StringBuffer("{");

	  str.append("id=" + getId() + " " + "nom=" + getNom() + " " + "prenom=" + getPrenom() + " " + "pseudo=" + getPseudo() + " " + "password=" + getPassword() + " " + "email=" + getEmail() + " " + "adresse=" + getAdresse() + " " + "codePostal=" + getCodePostal() + " " + "ville=" + getVille() + " " + "pays=" + getPays() + " " + "telephoneFixe=" + getTelephoneFixe() + " " + "telephonePortable=" + getTelephonePortable() + " " + "dateNaissance=" + getDateNaissance());
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
          if (other instanceof MembreDTO)
          {
                 MembreDTO that = (MembreDTO) other;
                 boolean lEquals = true;
                 if( this.id == null )
                 {
                        lEquals = lEquals && ( that.id == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.id.equals( that.id );
                 }
                 if( this.nom == null )
                 {
                        lEquals = lEquals && ( that.nom == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.nom.equals( that.nom );
                 }
                 if( this.prenom == null )
                 {
                        lEquals = lEquals && ( that.prenom == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.prenom.equals( that.prenom );
                 }
                 if( this.pseudo == null )
                 {
                        lEquals = lEquals && ( that.pseudo == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.pseudo.equals( that.pseudo );
                 }
                 if( this.password == null )
                 {
                        lEquals = lEquals && ( that.password == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.password.equals( that.password );
                 }
                 if( this.email == null )
                 {
                        lEquals = lEquals && ( that.email == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.email.equals( that.email );
                 }
                 if( this.adresse == null )
                 {
                        lEquals = lEquals && ( that.adresse == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.adresse.equals( that.adresse );
                 }
                 if( this.codePostal == null )
                 {
                        lEquals = lEquals && ( that.codePostal == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.codePostal.equals( that.codePostal );
                 }
                 if( this.ville == null )
                 {
                        lEquals = lEquals && ( that.ville == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.ville.equals( that.ville );
                 }
                 if( this.pays == null )
                 {
                        lEquals = lEquals && ( that.pays == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.pays.equals( that.pays );
                 }
                 if( this.telephoneFixe == null )
                 {
                        lEquals = lEquals && ( that.telephoneFixe == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.telephoneFixe.equals( that.telephoneFixe );
                 }
                 if( this.telephonePortable == null )
                 {
                        lEquals = lEquals && ( that.telephonePortable == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.telephonePortable.equals( that.telephonePortable );
                 }
                 if( this.dateNaissance == null )
                 {
                        lEquals = lEquals && ( that.dateNaissance == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.dateNaissance.equals( that.dateNaissance );
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
        if (!(other instanceof MembreDTO)) { 
            return false;
        }

        return equals((MembreDTO) other);
    }

    /**
     * This class is not using strict ordering. This means that the object is not Comparable, and
     * each check for equality will test all members for equality. We do not check collections for
     * equality however, so you would be wise to not use this if you have collection typed EJB References.
     */
    public boolean equals(MembreDTO that) {

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

        if(this.nom != that.nom) {

            if( this.nom == null || that.nom == null ) {
                return false;
            }

            if(!this.nom.equals(that.nom)) {
                return false;
            }

        }

        if(this.prenom != that.prenom) {

            if( this.prenom == null || that.prenom == null ) {
                return false;
            }

            if(!this.prenom.equals(that.prenom)) {
                return false;
            }

        }

        if(this.pseudo != that.pseudo) {

            if( this.pseudo == null || that.pseudo == null ) {
                return false;
            }

            if(!this.pseudo.equals(that.pseudo)) {
                return false;
            }

        }

        if(this.password != that.password) {

            if( this.password == null || that.password == null ) {
                return false;
            }

            if(!this.password.equals(that.password)) {
                return false;
            }

        }

        if(this.email != that.email) {

            if( this.email == null || that.email == null ) {
                return false;
            }

            if(!this.email.equals(that.email)) {
                return false;
            }

        }

        if(this.adresse != that.adresse) {

            if( this.adresse == null || that.adresse == null ) {
                return false;
            }

            if(!this.adresse.equals(that.adresse)) {
                return false;
            }

        }

        if(this.codePostal != that.codePostal) {

            if( this.codePostal == null || that.codePostal == null ) {
                return false;
            }

            if(!this.codePostal.equals(that.codePostal)) {
                return false;
            }

        }

        if(this.ville != that.ville) {

            if( this.ville == null || that.ville == null ) {
                return false;
            }

            if(!this.ville.equals(that.ville)) {
                return false;
            }

        }

        if(this.pays != that.pays) {

            if( this.pays == null || that.pays == null ) {
                return false;
            }

            if(!this.pays.equals(that.pays)) {
                return false;
            }

        }

        if(this.telephoneFixe != that.telephoneFixe) {

            if( this.telephoneFixe == null || that.telephoneFixe == null ) {
                return false;
            }

            if(!this.telephoneFixe.equals(that.telephoneFixe)) {
                return false;
            }

        }

        if(this.telephonePortable != that.telephonePortable) {

            if( this.telephonePortable == null || that.telephonePortable == null ) {
                return false;
            }

            if(!this.telephonePortable.equals(that.telephonePortable)) {
                return false;
            }

        }

        if(this.dateNaissance != that.dateNaissance) {

            if( this.dateNaissance == null || that.dateNaissance == null ) {
                return false;
            }

            if(!this.dateNaissance.equals(that.dateNaissance)) {
                return false;
            }

        }

        return true;

    }

    public Object clone() throws java.lang.CloneNotSupportedException {
        MembreDTO other = (MembreDTO) super.clone();

        return other;
    }

    public ReadOnlyMembreDTO getReadOnlyMembreDTO() {
        return new ReadOnlyMembreDTO();
    }

    public int hashCode(){
	  int result = 17;
      result = 37*result + ((this.id != null) ? this.id.hashCode() : 0);

      result = 37*result + ((this.nom != null) ? this.nom.hashCode() : 0);

      result = 37*result + ((this.prenom != null) ? this.prenom.hashCode() : 0);

      result = 37*result + ((this.pseudo != null) ? this.pseudo.hashCode() : 0);

      result = 37*result + ((this.password != null) ? this.password.hashCode() : 0);

      result = 37*result + ((this.email != null) ? this.email.hashCode() : 0);

      result = 37*result + ((this.adresse != null) ? this.adresse.hashCode() : 0);

      result = 37*result + ((this.codePostal != null) ? this.codePostal.hashCode() : 0);

      result = 37*result + ((this.ville != null) ? this.ville.hashCode() : 0);

      result = 37*result + ((this.pays != null) ? this.pays.hashCode() : 0);

      result = 37*result + ((this.telephoneFixe != null) ? this.telephoneFixe.hashCode() : 0);

      result = 37*result + ((this.telephonePortable != null) ? this.telephonePortable.hashCode() : 0);

      result = 37*result + ((this.dateNaissance != null) ? this.dateNaissance.hashCode() : 0);

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

    private final class ReadOnlyMembreDTO 
    implements java.lang.Cloneable, java.io.Serializable 
    {
        private MembreDTO underlying() {
            return MembreDTO.this;
        }

       public java.lang.String getId() {
              return underlying().id;
       }

       public java.lang.String getNom() {
              return underlying().nom;
       }

       public java.lang.String getPrenom() {
              return underlying().prenom;
       }

       public java.lang.String getPseudo() {
              return underlying().pseudo;
       }

       public java.lang.String getPassword() {
              return underlying().password;
       }

       public java.lang.String getEmail() {
              return underlying().email;
       }

       public java.lang.String getAdresse() {
              return underlying().adresse;
       }

       public java.lang.String getCodePostal() {
              return underlying().codePostal;
       }

       public java.lang.String getVille() {
              return underlying().ville;
       }

       public java.lang.String getPays() {
              return underlying().pays;
       }

       public java.lang.String getTelephoneFixe() {
              return underlying().telephoneFixe;
       }

       public java.lang.String getTelephonePortable() {
              return underlying().telephonePortable;
       }

       public java.util.Date getDateNaissance() {
              return underlying().dateNaissance;
       }

        public int hashCode() {
            return 101 * underlying().hashCode();
        }

        public boolean equals(Object o) {
            if(o instanceof ReadOnlyMembreDTO) {
                return this.equals((ReadOnlyMembreDTO) o);
            }
            return false;
        }

        public boolean equals(ReadOnlyMembreDTO that) {
            if(null == that) {
                return false;
            }

            return this.underlying().equals(that.underlying());
        }

    }

}
