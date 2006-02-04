package ezbay.model.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import org.jboss.remoting.Client;

import ezbay.model.interfaces.CategorieFacade;
import ezbay.model.interfaces.CategorieFacadeHome;
import ezbay.model.interfaces.ClientDTO;
import ezbay.model.interfaces.ClientFacadeLocal;
import ezbay.model.interfaces.ClientFacadeLocalHome;
import ezbay.model.interfaces.ClientLocal;
import ezbay.model.interfaces.ClientLocalHome;
import ezbay.model.interfaces.MembreDTO;
import ezbay.model.interfaces.MembreLocal;
import ezbay.model.interfaces.MembreLocalHome;
import ezbay.model.interfaces.VendeurDTO;
import ezbay.model.interfaces.VendeurFacadeLocal;
import ezbay.model.interfaces.VendeurFacadeLocalHome;
import ezbay.model.interfaces.VendeurLocal;
import ezbay.model.interfaces.VendeurLocalHome;
import ezbay.utils.ServiceLocator;
import ezbay.utils.ServiceLocatorException;

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
 * @ejb.bean name="MembreFacade"
 *           display-name="Name for MembreFacade"
 *           description="Description for MembreFacade"
 *           jndi-name="ejb/MembreFacade"
 *           type="Stateless"
 *           view-type="both"
 */
public class MembreFacadeBean implements SessionBean {

	/** The session context */
	private SessionContext context;

	public MembreFacadeBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param membreDTO
	 */
	public MembreDTO createMembre(MembreDTO membreDTO) throws Exception {
		try {
			MembreLocalHome membreHome = getEntityHome();
			MembreLocal membre = membreHome.create(membreDTO);
			this.createClient(membreDTO);
			return membre.getMembreDTO();
		} catch (Exception e) {
			throw new Exception("Cannot create membre", e);
		}
	}
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param 
	 */
    public void updateMembre(MembreDTO membreDTO) throws Exception {
    	// 	A FAIRE
    	/*try {
		}catch (Exception e) {
			throw new Exception("Cannot remove vendeur", e);
		}*/
    }	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param 
	 */
    public void removeMembre(MembreDTO membreDTO) throws Exception {
    	// 	A FAIRE
    	/*try {
		}catch (Exception e) {
			throw new Exception("Cannot remove vendeur", e);
		}*/
    }		
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param membreDTO
	 */
	public void createVendeur(MembreDTO membreDTO) throws Exception {
		try {
	        ServiceLocator locator = ServiceLocator.getInstance();
	        //creation du client
	        VendeurFacadeLocalHome vendeurFacadeHome = (VendeurFacadeLocalHome) locator.getLocalHome(ClientFacadeLocalHome.JNDI_NAME);
	        VendeurFacadeLocal vendeurFacade = (VendeurFacadeLocal) vendeurFacadeHome.create();
	        VendeurDTO vendeurDTO = (VendeurDTO) vendeurFacade.createVendeur();
	        VendeurLocal vendeur = VendeurFacadeBean.getEntity(vendeurDTO.getId());
	        // association vendeur-membre
			MembreLocal membre = getEntity(membreDTO.getId());
			membre.setVendeurLocal(vendeur);
		} catch (Exception e) {
			throw new Exception("Cannot create vendeur", e);
		}
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param membreDTO
	 */
	public void createClient(MembreDTO membreDTO) throws Exception {
		try {
	        ServiceLocator locator = ServiceLocator.getInstance();
	        //creation du client
	        ClientFacadeLocalHome clientFacadeHome = (ClientFacadeLocalHome) locator.getLocalHome(ClientFacadeLocalHome.JNDI_NAME);
	        ClientFacadeLocal clientFacade = (ClientFacadeLocal) clientFacadeHome.create();
	        ClientDTO clientDTO = (ClientDTO) clientFacade.createClient();
	        ClientLocal client = ClientFacadeBean.getEntity(clientDTO.getId());
	        // association client-membre
			MembreLocal membre = getEntity(membreDTO.getId());
			membre.setClientLocal(client);
		} catch (Exception e) {
			throw new Exception("Cannot create client", e);
		}
	}		
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param 
	 */
    public void removeVendeur(MembreDTO membreDTO) throws Exception {
    	// 	A FAIRE
    	/*try {
		}catch (Exception e) {
			throw new Exception("Cannot remove vendeur", e);
		}*/
    }
    
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param 
	 */
    public void removeClient(MembreDTO membreDTO) throws Exception {
    	// 	A FAIRE
    	/*try {
		}catch (Exception e) {
			throw new Exception("Cannot remove vendeur", e);
		}*/
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
	public static MembreLocal getEntity(String id) throws Exception{
        try {
        	MembreLocalHome home = getEntityHome();
            return home.findByPrimaryKey(id);
        } catch (Exception e) {
            throw new Exception("Cannot locate Article", e);
        }
    }
    
     /** Retrieves the local home interface of the Customer intity bean. */
    public static MembreLocalHome getEntityHome(){
    	MembreLocalHome home = null;
    	try {
	        ServiceLocator locator = ServiceLocator.getInstance();
			home = (MembreLocalHome) locator.getLocalHome(MembreLocalHome.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
        return home;
    }	

}
