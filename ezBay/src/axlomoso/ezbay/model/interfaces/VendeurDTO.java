/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.interfaces;

/**
 * Value object for Vendeur.
 *
 * Notice, this object is used to represent the state of an 
 * Vendeur object. This value object
 * Is not connected to the database in any way, it is just a normal object used 
 * as a container for data from an EJB. 
 *
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public class VendeurDTO
   extends java.lang.Object
   implements java.io.Serializable, java.lang.Cloneable 
{

   private java.lang.String id;
   private boolean idHasBeenSet = false;

   private java.lang.String nomProprioCB;
   private boolean nomProprioCBHasBeenSet = false;

   private java.lang.String numCB;
   private boolean numCBHasBeenSet = false;

   private java.util.Date dateExpirCB;
   private boolean dateExpirCBHasBeenSet = false;

   private java.lang.String codeSecuCB;
   private boolean codeSecuCBHasBeenSet = false;

   private java.lang.String primaryKey;

   public VendeurDTO()
   {
   }

   public VendeurDTO( java.lang.String id,java.lang.String nomProprioCB,java.lang.String numCB,java.util.Date dateExpirCB,java.lang.String codeSecuCB )
   {
       setId(id);
       setNomProprioCB(nomProprioCB);
       setNumCB(numCB);
       setDateExpirCB(dateExpirCB);
       setCodeSecuCB(codeSecuCB);
	   primaryKey = this.getId();
   }

   /**
    * @deprecated use {@link #clone}
    */
   public VendeurDTO( VendeurDTO otherValue )
   {
	  this.id = otherValue.id;
	  idHasBeenSet = true;
	  this.nomProprioCB = otherValue.nomProprioCB;
	  nomProprioCBHasBeenSet = true;
	  this.numCB = otherValue.numCB;
	  numCBHasBeenSet = true;
	  this.dateExpirCB = otherValue.dateExpirCB;
	  dateExpirCBHasBeenSet = true;
	  this.codeSecuCB = otherValue.codeSecuCB;
	  codeSecuCBHasBeenSet = true;

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
   public java.lang.String getNomProprioCB()
   {
	  return this.nomProprioCB;
   }

   public void setNomProprioCB( java.lang.String nomProprioCB )
   {
	  this.nomProprioCB = nomProprioCB;
	  nomProprioCBHasBeenSet = true;

   }

   public boolean nomProprioCBHasBeenSet(){
	  return nomProprioCBHasBeenSet;
   }
   public java.lang.String getNumCB()
   {
	  return this.numCB;
   }

   public void setNumCB( java.lang.String numCB )
   {
	  this.numCB = numCB;
	  numCBHasBeenSet = true;

   }

   public boolean numCBHasBeenSet(){
	  return numCBHasBeenSet;
   }
   public java.util.Date getDateExpirCB()
   {
	  return this.dateExpirCB;
   }

   public void setDateExpirCB( java.util.Date dateExpirCB )
   {
	  this.dateExpirCB = dateExpirCB;
	  dateExpirCBHasBeenSet = true;

   }

   public boolean dateExpirCBHasBeenSet(){
	  return dateExpirCBHasBeenSet;
   }
   public java.lang.String getCodeSecuCB()
   {
	  return this.codeSecuCB;
   }

   public void setCodeSecuCB( java.lang.String codeSecuCB )
   {
	  this.codeSecuCB = codeSecuCB;
	  codeSecuCBHasBeenSet = true;

   }

   public boolean codeSecuCBHasBeenSet(){
	  return codeSecuCBHasBeenSet;
   }

   public String toString()
   {
	  StringBuffer str = new StringBuffer("{");

	  str.append("id=" + getId() + " " + "nomProprioCB=" + getNomProprioCB() + " " + "numCB=" + getNumCB() + " " + "dateExpirCB=" + getDateExpirCB() + " " + "codeSecuCB=" + getCodeSecuCB());
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
          if (other instanceof VendeurDTO)
          {
                 VendeurDTO that = (VendeurDTO) other;
                 boolean lEquals = true;
                 if( this.id == null )
                 {
                        lEquals = lEquals && ( that.id == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.id.equals( that.id );
                 }
                 if( this.nomProprioCB == null )
                 {
                        lEquals = lEquals && ( that.nomProprioCB == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.nomProprioCB.equals( that.nomProprioCB );
                 }
                 if( this.numCB == null )
                 {
                        lEquals = lEquals && ( that.numCB == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.numCB.equals( that.numCB );
                 }
                 if( this.dateExpirCB == null )
                 {
                        lEquals = lEquals && ( that.dateExpirCB == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.dateExpirCB.equals( that.dateExpirCB );
                 }
                 if( this.codeSecuCB == null )
                 {
                        lEquals = lEquals && ( that.codeSecuCB == null );
                 }
                 else
                 {
                        lEquals = lEquals && this.codeSecuCB.equals( that.codeSecuCB );
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
        if (!(other instanceof VendeurDTO)) { 
            return false;
        }

        return equals((VendeurDTO) other);
    }

    /**
     * This class is not using strict ordering. This means that the object is not Comparable, and
     * each check for equality will test all members for equality. We do not check collections for
     * equality however, so you would be wise to not use this if you have collection typed EJB References.
     */
    public boolean equals(VendeurDTO that) {

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

        if(this.nomProprioCB != that.nomProprioCB) {

            if( this.nomProprioCB == null || that.nomProprioCB == null ) {
                return false;
            }

            if(!this.nomProprioCB.equals(that.nomProprioCB)) {
                return false;
            }

        }

        if(this.numCB != that.numCB) {

            if( this.numCB == null || that.numCB == null ) {
                return false;
            }

            if(!this.numCB.equals(that.numCB)) {
                return false;
            }

        }

        if(this.dateExpirCB != that.dateExpirCB) {

            if( this.dateExpirCB == null || that.dateExpirCB == null ) {
                return false;
            }

            if(!this.dateExpirCB.equals(that.dateExpirCB)) {
                return false;
            }

        }

        if(this.codeSecuCB != that.codeSecuCB) {

            if( this.codeSecuCB == null || that.codeSecuCB == null ) {
                return false;
            }

            if(!this.codeSecuCB.equals(that.codeSecuCB)) {
                return false;
            }

        }

        return true;

    }

    public Object clone() throws java.lang.CloneNotSupportedException {
        VendeurDTO other = (VendeurDTO) super.clone();

        return other;
    }

    public ReadOnlyVendeurDTO getReadOnlyVendeurDTO() {
        return new ReadOnlyVendeurDTO();
    }

    public int hashCode(){
	  int result = 17;
      result = 37*result + ((this.id != null) ? this.id.hashCode() : 0);

      result = 37*result + ((this.nomProprioCB != null) ? this.nomProprioCB.hashCode() : 0);

      result = 37*result + ((this.numCB != null) ? this.numCB.hashCode() : 0);

      result = 37*result + ((this.dateExpirCB != null) ? this.dateExpirCB.hashCode() : 0);

      result = 37*result + ((this.codeSecuCB != null) ? this.codeSecuCB.hashCode() : 0);

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

    private final class ReadOnlyVendeurDTO 
    implements java.lang.Cloneable, java.io.Serializable 
    {
        private VendeurDTO underlying() {
            return VendeurDTO.this;
        }

       public java.lang.String getId() {
              return underlying().id;
       }

       public java.lang.String getNomProprioCB() {
              return underlying().nomProprioCB;
       }

       public java.lang.String getNumCB() {
              return underlying().numCB;
       }

       public java.util.Date getDateExpirCB() {
              return underlying().dateExpirCB;
       }

       public java.lang.String getCodeSecuCB() {
              return underlying().codeSecuCB;
       }

        public int hashCode() {
            return 101 * underlying().hashCode();
        }

        public boolean equals(Object o) {
            if(o instanceof ReadOnlyVendeurDTO) {
                return this.equals((ReadOnlyVendeurDTO) o);
            }
            return false;
        }

        public boolean equals(ReadOnlyVendeurDTO that) {
            if(null == that) {
                return false;
            }

            return this.underlying().equals(that.underlying());
        }

    }

}
