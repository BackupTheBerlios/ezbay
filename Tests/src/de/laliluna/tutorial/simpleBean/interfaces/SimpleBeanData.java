/*
 * Generated by XDoclet - Do not edit!
 */
package de.laliluna.tutorial.simpleBean.interfaces;

/**
 * Data object for SimpleBean.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public class SimpleBeanData
   extends java.lang.Object
   implements java.io.Serializable
{
   private java.lang.String id;
   private java.lang.String name;

  /* begin value object */

  /* end value object */

   public SimpleBeanData()
   {
   }

   public SimpleBeanData( java.lang.String id,java.lang.String name )
   {
      setId(id);
      setName(name);
   }

   public SimpleBeanData( SimpleBeanData otherData )
   {
      setId(otherData.getId());
      setName(otherData.getName());

   }

   public java.lang.String getPrimaryKey() {
     return  getId();
   }

   public java.lang.String getId()
   {
      return this.id;
   }
   public void setId( java.lang.String id )
   {
      this.id = id;
   }

   public java.lang.String getName()
   {
      return this.name;
   }
   public void setName( java.lang.String name )
   {
      this.name = name;
   }

   public String toString()
   {
      StringBuffer str = new StringBuffer("{");

      str.append("id=" + getId() + " " + "name=" + getName());
      str.append('}');

      return(str.toString());
   }

   public boolean equals( Object pOther )
   {
      if( pOther instanceof SimpleBeanData )
      {
         SimpleBeanData lTest = (SimpleBeanData) pOther;
         boolean lEquals = true;

         if( this.id == null )
         {
            lEquals = lEquals && ( lTest.id == null );
         }
         else
         {
            lEquals = lEquals && this.id.equals( lTest.id );
         }
         if( this.name == null )
         {
            lEquals = lEquals && ( lTest.name == null );
         }
         else
         {
            lEquals = lEquals && this.name.equals( lTest.name );
         }

         return lEquals;
      }
      else
      {
         return false;
      }
   }

   public int hashCode()
   {
      int result = 17;

      result = 37*result + ((this.id != null) ? this.id.hashCode() : 0);

      result = 37*result + ((this.name != null) ? this.name.hashCode() : 0);

      return result;
   }

}
