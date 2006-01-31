package ezbay.model.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;

import ezbay.model.interfaces.ClientDTO;
import ezbay.model.interfaces.ClientLocal;
import ezbay.model.interfaces.ClientLocalHome;
import ezbay.model.interfaces.MembreDTO;
import ezbay.model.interfaces.MembreLocal;
import ezbay.model.interfaces.MembreLocalHome;
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
			InitialContext initialContext = new InitialContext();
			MembreLocalHome membreHome = (MembreLocalHome) initialContext.lookup(MembreLocalHome.JNDI_NAME);
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
	public void createClient(MembreDTO membreDTO) throws Exception {
		try {
			InitialContext initialContext = new InitialContext();
			MembreLocalHome membreHome = (MembreLocalHome) initialContext.lookup(MembreLocalHome.JNDI_NAME);
			MembreLocal membre = membreHome.findByPrimaryKey(membreDTO.getId());
			// Association d'un Vendeur
			VendeurLocalHome vendeurHome = (VendeurLocalHome) initialContext.lookup(VendeurLocalHome.JNDI_NAME);
			VendeurDTO vendeurDTO = (VendeurDTO) vendeurHome.create();
			VendeurLocal vendeur = (VendeurLocal) membreHome.findByPrimaryKey(vendeurDTO.getId());
			membre.setVendeurLocal(vendeur);
		} catch (Exception e) {
			throw new Exception("Cannot create client", e);
		}
	}	
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param membreDTO
	 */
	public void createVendeur(MembreDTO membreDTO) throws Exception {
		try {
			InitialContext initialContext = new InitialContext();
			MembreLocalHome membreHome = (MembreLocalHome) initialContext.lookup(MembreLocalHome.JNDI_NAME);
			MembreLocal membre = membreHome.findByPrimaryKey(membreDTO.getId());
			// Association d'un Vendeur
			VendeurLocalHome vendeurHome = (VendeurLocalHome) initialContext.lookup(VendeurLocalHome.JNDI_NAME);
			VendeurDTO vendeurDTO = (VendeurDTO) vendeurHome.create();
			VendeurLocal vendeur = (VendeurLocal) membreHome.findByPrimaryKey(vendeurDTO.getId());
			membre.setVendeurLocal(vendeur);
		} catch (Exception e) {
			throw new Exception("Cannot create vendeur", e);
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

}
