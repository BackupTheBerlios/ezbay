/*
 *
 * Copyright 2003 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * The code in this example is offered under the license at: 
 * http://wireless.java.sun.com/berkeley_license.html
 *
 */


package ezbay.utils;


public class ServiceLocatorException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2723272459676361039L;


	/** Creates an empty ServiceLocatorException. */
    public ServiceLocatorException() {
    }

    /**
     * Constructs an <code>ServiceLocatorException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ServiceLocatorException(String msg) {
        super(msg);
    }

    /**
     * Constructs an <code>ServiceLocatorException</code> with the specified detail message
     * and cause message.
     *
     * @param msg the detail message.
     * @param causeMsg the cause of the exception
     */
    public ServiceLocatorException(String msg, String causeMsg) {
        super(msg + ": " + causeMsg);
    }

 
    /**
     * Constructs an <code>ServiceLocatorException</code> without a detail message and using
     * the specified Throwable as the root cause for this Exception.
     *
     * @param th the Throwable which caused this ServiceLocatorException to be created
     */
    public ServiceLocatorException(Throwable th) {
        super(th);
    }
}
