/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.interfaces;

/**
 * Local home interface for ArticleFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ArticleFacadeLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/ArticleFacadeLocal";
   public static final String JNDI_NAME="ArticleFacadeLocal";

   public axlomoso.ezbay.model.interfaces.ArticleFacadeLocal create()
      throws javax.ejb.CreateException;

}
