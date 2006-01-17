import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.rmi.PortableRemoteObject;

import test.helloWorld.interfaces.HelloWorld;
import test.helloWorld.interfaces.HelloWorldHome;

public class ClientHelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
		    Properties properties = new Properties();
		    properties.put("java.naming.factory.initial",
		        "org.jnp.interfaces.NamingContextFactory");
		    properties.put("java.naming.factory.url.pkgs",
		        "org.jboss.naming:org.jnp.interfaces");
		    properties.put("java.naming.provider.url", "jnp://localhost:1099");
		    properties.put("jnp.disableDiscovery", "true");
		    
		    javax.naming.Context jndiCtx = new javax.naming.InitialContext(properties);
		    Object ref = jndiCtx.lookup("ejb/HelloWorld");
		    HelloWorldHome home = (HelloWorldHome)PortableRemoteObject.narrow(ref, HelloWorldHome.class);
		    HelloWorld helloWorld = home.create();
		    System.out.println(helloWorld.sayHello());	    
		}catch(RemoteException e){e.printStackTrace();}
		catch(CreateException e){e.printStackTrace();}
		catch(Exception e){e.printStackTrace();}

	}

}
