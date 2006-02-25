package axlomoso.ezbay.model.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.ClientLocal;
import axlomoso.ezbay.model.interfaces.ClientLocalHome;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.utils.ServiceLocator;
import axlomoso.ezbay.utils.ServiceLocatorException;


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
			ClientLocalHome home = getEntityHome();
			ClientLocal clientLocal = home.create();
			return clientLocal.getClientDTO();
		} catch (Exception e) {
			throw new Exception("Cannot create client", e);
		}
	}	
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param clientId
	 */
	public MembreDTO getMembre(String clientId) throws Exception {
		MembreDTO tRes = null;
		try {
			ClientLocal clientLocal = getEntity(clientId);
			tRes = clientLocal.getMembreLocal().getMembreDTO();
		} catch (Exception e) {
			throw new Exception("Cannot get Membre From client id=" + clientId, e);
		}
		return tRes;
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
	

    /** Retrieves the local interface of the Customer entity bean. 
     * @throws Exception */
	public static ClientLocal getEntity(String id) throws Exception{
        try {
        	ClientLocalHome home = getEntityHome();
            return home.findByPrimaryKey(id);
        } catch (Exception e) {
            throw new Exception("Cannot locate Article", e);
        }
    }
    
     /** Retrieves the local home interface of the Customer intity bean. */
    public static ClientLocalHome getEntityHome(){
    	ClientLocalHome home = null;
    	try {
	        ServiceLocator locator = ServiceLocator.getInstance();
			home = (ClientLocalHome) locator.getLocalHome(ClientLocalHome.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
        return home;
    }	

}
