package de.laliluna.tutorial.simpleBean.client;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import de.laliluna.tutorial.simpleBean.interfaces.SimpleBean;
import de.laliluna.tutorial.simpleBean.interfaces.SimpleBeanHome;

/**
 * @author HS
 *
 * 
 */
public class SimpleBeanClient {

  Properties properties;

  public SimpleBeanClient() {
    properties = new Properties();
    properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
    properties.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
    properties.put("java.naming.provider.url", "jnp://localhost:1099");
    properties.put("jnp.disableDiscovery", "true");
  }

  public static void main(String[] args) {
    SimpleBeanClient beanClient = new SimpleBeanClient();
    beanClient.createBean();
  }

  public void createBean() throws EJBException {
    try {
      // [laliluna] create a context to look up the beans in the JNDI
      InitialContext context = new InitialContext(properties);
      /*
       * [laliluna] 
       * we have to look up the remote interfaces as we are not in the same environment as the EJB.
       * Therefore we will have to use the PortableRemote class to convert our object
       */
      Object object = context.lookup(SimpleBeanHome.JNDI_NAME);
      SimpleBeanHome simpleHome = (SimpleBeanHome) PortableRemoteObject.narrow(object, SimpleBeanHome.class);

      SimpleBean simpleBean = simpleHome.create();
      simpleBean.setName("Gunter");
      System.out.println(simpleBean.getId());
      System.out.println(simpleBean.getName());

    } catch (NamingException e) {
      throw new EJBException(e);
    } catch (RemoteException e) {
      throw new EJBException(e);
    } catch (CreateException e) {
      throw new EJBException(e);
    }

  }
}