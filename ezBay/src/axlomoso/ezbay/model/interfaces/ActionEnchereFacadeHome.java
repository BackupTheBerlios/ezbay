/*
 * Generated by XDoclet - Do not edit!
 */
package axlomoso.ezbay.model.interfaces;

/**
 * Home interface for ActionEnchereFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ActionEnchereFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/ActionEnchereFacade";
   public static final String JNDI_NAME="ejb/ActionEnchereFacade";

   public axlomoso.ezbay.model.interfaces.ActionEnchereFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
