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

import axlomoso.ezbay.exceptions.PseudoDejaUtiliseException;
import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.ClientFacadeLocal;
import axlomoso.ezbay.model.interfaces.ClientFacadeLocalHome;
import axlomoso.ezbay.model.interfaces.ClientLocal;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreLocal;
import axlomoso.ezbay.model.interfaces.MembreLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurFacadeLocal;
import axlomoso.ezbay.model.interfaces.VendeurFacadeLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurLocal;
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
	 * cette methode permet de creer ou de mettre a jour un membre
	 * @param membreDTO
	 * @return MembreDTO
	 * @throws Exception
	 * @throws PseudoDejaUtiliseException
	 */
	
	public MembreDTO saveMembre(MembreDTO membreDTO) throws Exception, PseudoDejaUtiliseException{
		MembreDTO tRes = null;
		boolean exists = false;
		try {
			if(membreDTO.getId() == null)
				exists = false;//membre n'existe pas
			else{
				MembreLocal membreLocal = getEntity(membreDTO.getId()); //test de l'existence du membre
				exists = true;//membre existant
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

	
	/**cette methode permet de creer un membre
	 * @param membreDTO
	 * @return MembreDTO
	 * @throws Exception
	 */
	private MembreDTO createMembre(MembreDTO membreDTO) throws Exception{
		MembreDTO tRes = null;
		try {
			MembreLocalHome membreHome = getEntityHome();
			MembreLocal membre = membreHome.create(membreDTO);//creation du membre
			this.createClient(membre.getMembreDTO());//creation du client au meme temps que le membre
			tRes = membre.getMembreDTO();
		} catch (CreateException e) {
			throw new CreateException("Cannot create membre" + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Cannot create membre", e);
		}
		return tRes;
	}

    /**cette methode permet de mettre a jour le membre
     * @param membreDTO
     * @return MembreDTO
     * @throws Exception
     */
    private MembreDTO updateMembre(MembreDTO membreDTO) throws Exception{
    	MembreDTO tRes = null;
		MembreLocal membreLocal;
		membreLocal = getEntity(membreDTO.getId());//on recupere le membre local 
		String id = membreLocal.updateMembre(membreDTO);//on le met a jour
		MembreLocal membreLocalModified = getEntity(id);
		tRes = membreLocalModified.getMembreDTO();
		return tRes;
    }		
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * cette methode permet de retourner un membre en passant en parametre son identifiant
	 * @param membreId
	 * @return MembreDTO
	 * @throws Exception
	 */
	
	public MembreDTO getMembre(String membreId) throws Exception{
		try {
			return getEntity(membreId).getMembreDTO();
		} catch (Exception e) {
			System.out.println(e.getMessage()); 
			throw new Exception("Cannot get membre", e);
		}
	}
	
	/**
	 * @ejb.interface-method view-type = "both"	 
	 * cette methode permet de retourner un membre en passant en parametre son pseudo et son mot de passe
	 * @param pseudo
	 * @param password
	 * @return MembreDTO
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
				}
			} catch (FinderException e) {
				System.out.println(e.getMessage()); 				
			}
			return tRes;
	}
	
	
	/**cette methode retourne vrai si le membre existe sinon elle retourne faux
	 * @param pseudo
	 * @return Boolean
	 */
	private boolean membreExists(String pseudo){
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
	 * cette methode permet de chercher un vendeur en passant en parametre l'identifiant du membre qu'il est
	 * @param membreId
	 * @return VendeurDTO
	 */ 
	
	public VendeurDTO getVendeurDTO(String membreId){
		VendeurDTO tRes = null;
		try {
			MembreLocal membre = getEntity(membreId);
			VendeurLocal vendeur = membre.getVendeurLocal();//on recupere le vendeur local 
			if( vendeur != null)
				tRes = vendeur.getVendeurDTO();//on recupere le vendeurDTO
		} catch (Exception e) {
			System.out.println(e.getMessage()); 
		}	
		return tRes;
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"	
	 * cette methode permet de chercher un client en passant en parametre l'identifiant du membre qu'il est
	 * @param membreId
	 * @return ClientDTO
	 */ 	
	public ClientDTO getClientDTO(String membreId){
		ClientDTO tRes = null;
		try {
			MembreLocal membre = getEntity(membreId);;
			ClientLocal clientLocal = membre.getClientLocal();//on recupere le client local
			if( clientLocal != null)
				tRes = clientLocal.getClientDTO();//on recupere le clientDTO
		} catch (Exception e) {
			System.out.println(e.getMessage()); 
		}	
		return tRes;
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"	
	 * cette methode permet de creer ou de mettre a jour un vendeur
	 * @param membreDTO
	 * @param vendeurDTO
	 * @return VendeurDTO
	 * @throws Exception
	 */	
	
	public VendeurDTO saveVendeur(MembreDTO membreDTO, VendeurDTO vendeurDTO) throws Exception {
		VendeurDTO tRes = null;
		try {
	        ServiceLocator locator = ServiceLocator.getInstance();
	        MembreLocal membre = getEntity(membreDTO.getId());
	        //creation du vendeur
	        VendeurFacadeLocalHome vendeurFacadeHome = (VendeurFacadeLocalHome) locator.getLocalHome(VendeurFacadeLocalHome.JNDI_NAME);
	        VendeurFacadeLocal vendeurFacade = (VendeurFacadeLocal) vendeurFacadeHome.create();
	        tRes = (VendeurDTO) vendeurFacade.saveVendeur(vendeurDTO);//on apelle une methode local du VendeurFacadeBean pour la sauvegarde du vendeur
	        VendeurLocal vendeurLocal = VendeurFacadeBean.getEntity(tRes.getId());
	        // association vendeur-membre			
			membre.setVendeurLocal(vendeurLocal);//on met a jour le vendeur local
		} catch (Exception e) {
			throw new Exception("Cannot save vendeur", e);
		}
		return tRes;
	}

	/**cette memthode privée permet de creer un client 
	 * @param membreDTO
	 * @throws Exception
	 */
	private void createClient(MembreDTO membreDTO) throws Exception {
		try {
	        ServiceLocator locator = ServiceLocator.getInstance();
	        //creation du client
	        ClientFacadeLocalHome clientFacadeHome = (ClientFacadeLocalHome) locator.getLocalHome(ClientFacadeLocalHome.JNDI_NAME);
	        ClientFacadeLocal clientFacade = (ClientFacadeLocal) clientFacadeHome.create();
	        ClientDTO clientDTO = (ClientDTO) clientFacade.createClient();//creation du client en local
	        ClientLocal client = ClientFacadeBean.getEntity(clientDTO.getId());
	        // association client-membre
			MembreLocal membre = getEntity(membreDTO.getId());
			membre.setClientLocal(client);//mise a jour du client local pour le bean membre
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
	
  
     /** cette methode retourne une instance de l'interface local du membre entity bean
	 * @param id
	 * @return MembreLocal
	 * @throws FinderException
	 */
	public static MembreLocal getEntity(String id)  throws FinderException{
        try {
        	MembreLocalHome home = getEntityHome();
            return home.findByPrimaryKey(id);
        } catch (FinderException e) {
            throw new FinderException("Cannot locate Membre" + e.getMessage());
        }
    }
    
	/** cette methode retourne une instance de l'interface local Home du membre entity bean  */
    public static MembreLocalHome getEntityHome(){
    	MembreLocalHome home = null;
    	try {
	        ServiceLocator locator = ServiceLocator.getInstance();
			home = (MembreLocalHome) locator.getLocalHome(MembreLocalHome.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			System.out.println(e.getMessage()); 
		}
        return home;
    }	

}
