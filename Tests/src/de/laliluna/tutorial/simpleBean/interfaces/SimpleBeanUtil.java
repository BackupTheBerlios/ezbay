/*
 * Generated file - Do not edit!
 */
package de.laliluna.tutorial.simpleBean.interfaces;

/**
 * Utility class for SimpleBean.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public class SimpleBeanUtil
{
   /** Cached remote home (EJBHome). Uses lazy loading to obtain its value (loaded by getHome() methods). */
   private static de.laliluna.tutorial.simpleBean.interfaces.SimpleBeanHome cachedRemoteHome = null;
   /** Cached local home (EJBLocalHome). Uses lazy loading to obtain its value (loaded by getLocalHome() methods). */
   private static de.laliluna.tutorial.simpleBean.interfaces.SimpleBeanLocalHome cachedLocalHome = null;

   private static Object lookupHome(java.util.Hashtable environment, String jndiName, Class narrowTo) throws javax.naming.NamingException {
      // Obtain initial context
      javax.naming.InitialContext initialContext = new javax.naming.InitialContext(environment);
      try {
         Object objRef = initialContext.lookup(jndiName);
         // only narrow if necessary
         if (java.rmi.Remote.class.isAssignableFrom(narrowTo))
            return javax.rmi.PortableRemoteObject.narrow(objRef, narrowTo);
         else
            return objRef;
      } finally {
         initialContext.close();
      }
   }

   // Home interface lookup methods

   /**
    * Obtain remote home interface from default initial context
    * @return Home interface for SimpleBean. Lookup using JNDI_NAME
    */
   public static de.laliluna.tutorial.simpleBean.interfaces.SimpleBeanHome getHome() throws javax.naming.NamingException
   {
      if (cachedRemoteHome == null) {
            cachedRemoteHome = (de.laliluna.tutorial.simpleBean.interfaces.SimpleBeanHome) lookupHome(null, de.laliluna.tutorial.simpleBean.interfaces.SimpleBeanHome.JNDI_NAME, de.laliluna.tutorial.simpleBean.interfaces.SimpleBeanHome.class);
      }
      return cachedRemoteHome;
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for SimpleBean. Lookup using JNDI_NAME
    */
   public static de.laliluna.tutorial.simpleBean.interfaces.SimpleBeanHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (de.laliluna.tutorial.simpleBean.interfaces.SimpleBeanHome) lookupHome(environment, de.laliluna.tutorial.simpleBean.interfaces.SimpleBeanHome.JNDI_NAME, de.laliluna.tutorial.simpleBean.interfaces.SimpleBeanHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for SimpleBean. Lookup using JNDI_NAME
    */
   public static de.laliluna.tutorial.simpleBean.interfaces.SimpleBeanLocalHome getLocalHome() throws javax.naming.NamingException
   {
      if (cachedLocalHome == null) {
            cachedLocalHome = (de.laliluna.tutorial.simpleBean.interfaces.SimpleBeanLocalHome) lookupHome(null, de.laliluna.tutorial.simpleBean.interfaces.SimpleBeanLocalHome.JNDI_NAME, de.laliluna.tutorial.simpleBean.interfaces.SimpleBeanLocalHome.class);
      }
      return cachedLocalHome;
   }

   /** Cached per JVM server IP. */
   private static String hexServerIP = null;

   // initialise the secure random instance
   private static final java.security.SecureRandom seeder = new java.security.SecureRandom();

   /**
    * A 32 byte GUID generator (Globally Unique ID). These artificial keys SHOULD <strong>NOT </strong> be seen by the user,
    * not even touched by the DBA but with very rare exceptions, just manipulated by the database and the programs.
    *
    * Usage: Add an id field (type java.lang.String) to your EJB, and add setId(XXXUtil.generateGUID(this)); to the ejbCreate method.
    */
   public static final String generateGUID(Object o) {
       StringBuffer tmpBuffer = new StringBuffer(16);
       if (hexServerIP == null) {
           java.net.InetAddress localInetAddress = null;
           try {
               // get the inet address

               localInetAddress = java.net.InetAddress.getLocalHost();
           }
           catch (java.net.UnknownHostException uhe) {
               System.err.println("SimpleBeanUtil: Could not get the local IP address using InetAddress.getLocalHost()!");
               // todo: find better way to get around this...
               uhe.printStackTrace();
               return null;
           }
           byte serverIP[] = localInetAddress.getAddress();
           hexServerIP = hexFormat(getInt(serverIP), 8);
       }

       String hashcode = hexFormat(System.identityHashCode(o), 8);
       tmpBuffer.append(hexServerIP);
       tmpBuffer.append(hashcode);

       long timeNow      = System.currentTimeMillis();
       int timeLow       = (int)timeNow & 0xFFFFFFFF;
       int node          = seeder.nextInt();

       StringBuffer guid = new StringBuffer(32);
       guid.append(hexFormat(timeLow, 8));
       guid.append(tmpBuffer.toString());
       guid.append(hexFormat(node, 8));
       return guid.toString();
   }

   private static int getInt(byte bytes[]) {
       int i = 0;
       int j = 24;
       for (int k = 0; j >= 0; k++) {
           int l = bytes[k] & 0xff;
           i += l << j;
           j -= 8;
       }
       return i;
   }

   private static String hexFormat(int i, int j) {
       String s = Integer.toHexString(i);
       return padHex(s, j) + s;
   }

   private static String padHex(String s, int i) {
       StringBuffer tmpBuffer = new StringBuffer();
       if (s.length() < i) {
           for (int j = 0; j < i - s.length(); j++) {
               tmpBuffer.append('0');
           }
       }
       return tmpBuffer.toString();
   }

}

