/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.interfaces;

/**
 * Local home interface for ActionTransaction.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ActionTransactionLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/ActionTransactionLocal";
   public static final String JNDI_NAME="ActionTransactionLocal";

   public axlomoso.ezbay.model.interfaces.ActionTransactionLocal create(axlomoso.ezbay.model.interfaces.ActionTransactionDTO transactionDTO , axlomoso.ezbay.model.interfaces.ArticleLocal articleLocal , axlomoso.ezbay.model.interfaces.ClientLocal clientLocal)
      throws javax.ejb.CreateException;

   public axlomoso.ezbay.model.interfaces.ActionTransactionLocal findByPrimaryKey(java.lang.String pk)
      throws javax.ejb.FinderException;

}
