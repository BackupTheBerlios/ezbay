package ezbay.model.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;

import ezbay.model.interfaces.ClientDTO;
import ezbay.model.interfaces.ClientLocal;
import ezbay.model.interfaces.ClientLocalHome;
import ezbay.model.interfaces.VendeurDTO;
import ezbay.model.interfaces.VendeurLocal;
import ezbay.model.interfaces.VendeurLocalHome;

/**
 * XDoclet-based session bean.  The class must be declared
 * public according to the EJB specification.
 *
 * To generate the EJB related files to this EJB:
 *		- Add Standard EJB module to XDoclet project properties
 *		- Customize XDoclet configuration for your appserver
 *		- Run XDoclet
 *
 * Below are the xdoclet-related tags needed for this EJB.
 * 
 * @ejb.bean name="ClientFacade"
 *           display-name="Name for ClientFacade"
 *           description="Description for ClientFacade"
 *           jndi-name="ejb/ClientFacade"
 *           type="Stateless"
 *           view-type="both"
 */
public class ClientFacadeBean implements SessionBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6764821663424470511L;
	/** The session context */
	private SessionContext context;

	public ClientFacadeBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param vendeurDTO
	 */
	public ClientDTO createClient() throws Exception {
		try {
			InitialContext initialContext = new InitialContext();
			ClientLocalHome home = (ClientLocalHome) initialContext.lookup(ClientLocalHome.JNDI_NAME);
			ClientLocal clientLocal = home.create();
			return clientLocal.getClientDTO();
		} catch (Exception e) {
			throw new Exception("Cannot create client", e);
		}
	}	
	
	/**
	 * Set the associated session context. The container calls this method 
	 * after the instance creation.
	 * 
	 * The enterprise bean instance should store the reference to the context 
	 * object in an instance variable.
	 * 
	 * This method is called with no transaction context. 
	 * 
	 * @throws EJBException Thrown if method fails due to system-level error.
	 */
	public void setSessionContext(SessionContext newContext)
		throws EJBException {
		context = newContext;
	}

	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	/**
	 * An example business method
	 *
	 * @ejb.interface-method view-type = "remote"
	 * 
	 * @throws EJBException Thrown if method fails due to system-level error.
	 */
	public void replaceWithRealBusinessMethod() throws EJBException {
		// rename and start putting your business logic here
	}

}
