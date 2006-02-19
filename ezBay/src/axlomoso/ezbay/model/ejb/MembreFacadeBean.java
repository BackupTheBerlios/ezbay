package axlomoso.ezbay.model.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import org.jboss.remoting.Client;

import axlomoso.ezbay.exceptions.PseudoDejaUtiliseException;
import axlomoso.ezbay.model.interfaces.CategorieFacade;
import axlomoso.ezbay.model.interfaces.CategorieFacadeHome;
import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.ClientFacadeLocal;
import axlomoso.ezbay.model.interfaces.ClientFacadeLocalHome;
import axlomoso.ezbay.model.interfaces.ClientLocal;
import axlomoso.ezbay.model.interfaces.ClientLocalHome;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreLocal;
import axlomoso.ezbay.model.interfaces.MembreLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurFacadeLocal;
import axlomoso.ezbay.model.interfaces.VendeurFacadeLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurLocal;
import axlomoso.ezbay.model.interfaces.VendeurLocalHome;
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
	 * @throws Exception 
	 */
	public MembreDTO saveMembre(MembreDTO membreDTO) throws Exception, PseudoDejaUtiliseException{
		MembreDTO tRes = null;
		boolean exists = false;
		try {
			if(membreDTO.getId() == null)
				exists = false;
			else{
				MembreLocal membreLocal = getEntity(membreDTO.getId()); //test de l'existence du membre
				exists = true;
			}
		} catch (FinderException e) {
			exists = false;
		}
		if(exists){
			// le membre existe : mise à jour.
			tRes = this.updateMembre(membreDTO);
		}
		else{
			if( this.membreExists(membreDTO.getPseudo()) ){
				throw new PseudoDejaUtiliseException("Le pseudo " + membreDTO.getPseudo() + " est déjà utilisé !");
			}
			else{
				//le membre n'existe pas: création.
				tRes = this.createMembre(membreDTO);
			}
		}
		return tRes;
	}

	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param membreDTO
	 * @throws Exception 
	 */
	public MembreDTO createMembre(MembreDTO membreDTO) throws Exception{
		MembreDTO tRes = null;
		try {
			MembreLocalHome membreHome = getEntityHome();
			MembreLocal membre = membreHome.create(membreDTO);
			this.createClient(membre.getMembreDTO());
			tRes = membre.getMembreDTO();
		} catch (CreateException e) {
			throw new CreateException("Cannot create membre" + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Cannot create membre", e);
		}
		return tRes;
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param 
	 * @throws Exception 
	 */
    public MembreDTO updateMembre(MembreDTO membreDTO) throws Exception{
    	MembreDTO tRes = null;
		MembreLocal membreLocal;
		membreLocal = getEntity(membreDTO.getId());
		String id = membreLocal.updateMembre(membreDTO);
		MembreLocal membreLocalModified = getEntity(id);
		tRes = membreLocalModified.getMembreDTO();
		return tRes;
    }		
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param membreId
	 */
	public MembreDTO getMembre(String membreId) throws Exception{
		try {
			return getEntity(membreId).getMembreDTO();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Cannot get membre", e);
		}
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param membreId
	 * @throws FinderException 
	 */
	public MembreDTO getMembre(String pseudo, String password){
		MembreDTO tRes = null;
		try {
				MembreLocalHome membreHome = getEntityHome();
				ArrayList membres;
				membres = (ArrayList) membreHome.findByPseudo(pseudo);
				Iterator it = membres.iterator();
				if(it.hasNext()){
					MembreLocal membre = (MembreLocal)it.next();
					if( membre.getPassword().equals(password) ){
						tRes = membre.getMembreDTO();
					}
					//else
						//throw new FinderException("Mauvais mot de passe !! ");
				}
			} catch (FinderException e) {
				e.printStackTrace();
				//throw new FinderException("Membre n'existe pas " + e.getMessage());
			}
			return tRes;
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param membreId
	 * @throws FinderException 
	 */
	public boolean membreExists(String pseudo){
		boolean tRes = true;
		try {
				MembreLocalHome membreHome = getEntityHome();
				Collection membres = (Collection) membreHome.findByPseudo(pseudo);
				if( membres.size() == 0 ){
					tRes = false;
				}
			} catch (FinderException e) {
				tRes = false;
			}
			return tRes;
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param membreId
	 */
	public VendeurDTO getVendeurDTO(String membreId){
		VendeurDTO tRes = null;
		try {
			MembreLocal membre = getEntity(membreId);;
			VendeurLocal vendeur = membre.getVendeurLocal();
			if( vendeur != null)
				tRes = vendeur.getVendeurDTO();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tRes;
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
	public VendeurDTO saveVendeur(MembreDTO membreDTO, VendeurDTO vendeurDTO) throws Exception {
		VendeurDTO tRes = null;
		try {
	        ServiceLocator locator = ServiceLocator.getInstance();
	        MembreLocal membre = getEntity(membreDTO.getId());
	        //creation du vendeur
	        VendeurFacadeLocalHome vendeurFacadeHome = (VendeurFacadeLocalHome) locator.getLocalHome(VendeurFacadeLocalHome.JNDI_NAME);
	        VendeurFacadeLocal vendeurFacade = (VendeurFacadeLocal) vendeurFacadeHome.create();
	        tRes = (VendeurDTO) vendeurFacade.saveVendeur(vendeurDTO);
	        VendeurLocal vendeurLocal = VendeurFacadeBean.getEntity(tRes.getId());
	        // association vendeur-membre			
			membre.setVendeurLocal(vendeurLocal);
		} catch (Exception e) {
			throw new Exception("Cannot save vendeur", e);
		}
		return tRes;
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
	public static MembreLocal getEntity(String id)  throws FinderException{
        try {
        	MembreLocalHome home = getEntityHome();
            return home.findByPrimaryKey(id);
        } catch (FinderException e) {
            throw new FinderException("Cannot locate Membre" + e.getMessage());
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
