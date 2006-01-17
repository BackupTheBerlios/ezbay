package de.laliluna.tutorial.simpleBean.client;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.rmi.PortableRemoteObject;

import de.laliluna.tutorial.simpleBean.interfaces.SimpleBean;
import de.laliluna.tutorial.simpleBean.interfaces.SimpleBeanHome;

public class SimpleBeanLocalClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
		    Properties properties = new Properties();
		    properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		    properties.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
		    properties.put("java.naming.provider.url", "jnp://localhost:1099");
		    properties.put("jnp.disableDiscovery", "true");

		    javax.naming.Context jndiCtx = new javax.naming.InitialContext(properties);
		    Object ref = jndiCtx.lookup(SimpleBeanHome.JNDI_NAME);
		    SimpleBeanHome simpleHome = (SimpleBeanHome) PortableRemoteObject.narrow(ref, SimpleBeanHome.class);
		    SimpleBean simpleBean = simpleHome.create();
		    simpleBean.setName("Gunter");
		    System.out.println(simpleBean.getId());
		    System.out.println(simpleBean.getName());
		    
		}catch(RemoteException e){e.printStackTrace();}
		catch(CreateException e){e.printStackTrace();}
		catch(Exception e){e.printStackTrace();}

	}

}
