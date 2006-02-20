package axlomoso.ezbay.model.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import axlomoso.ezbay.exceptions.VendeurInconnuException;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleFacadeLocal;
import axlomoso.ezbay.model.interfaces.ArticleFacadeLocalHome;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
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
 * @ejb.bean name="VendeurFacade"
 *           display-name="Name for VendeurFacade"
 *           description="Description for VendeurFacade"
 *           jndi-name="ejb/VendeurFacade"
 *           type="Stateless"
 *           view-type="both"
 */
public class VendeurFacadeBean implements SessionBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8719723927459392718L;
	/** The session context */
	private SessionContext context;

	public VendeurFacadeBean() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @ejb.interface-method view-type = "both"
	 * @param vendeurDTO
	 * @throws Exception 
	 */
	public VendeurDTO saveVendeur(VendeurDTO vendeurDTO) throws Exception{
		VendeurDTO tRes = null;
		boolean exists = false;
		try {
			if(vendeurDTO.getId() == null){
				exists = false;
			}
			else{
				VendeurLocal vendeurLocal = getEntity(vendeurDTO.getId()); //test de l'existence du vendeur
				exists = true;
			}
		} catch (FinderException e) {
			exists = false;
		}
		if(exists){
			// le vendeur existe : mise à jour.
			tRes = this.updateVendeur(vendeurDTO);
		}
		else{
			//le vendeur n'existe pas: création.
			tRes = this.createVendeur(vendeurDTO);
		}
		return tRes;
	}

	private VendeurDTO createVendeur(VendeurDTO vendeurDTO) throws CreateException{
    	VendeurLocal vendeur = null;
		try {
			VendeurLocalHome vendeurHome = getEntityHome();
			vendeur = vendeurHome.create(vendeurDTO);
		} catch (CreateException e) {
			throw new CreateException("cannot create vendeur " + e.getMessage());
		}
		return vendeur.getVendeurDTO();
    }
	
	private VendeurDTO updateVendeur(VendeurDTO vendeurDTO) throws Exception{
		VendeurDTO tRes = null;
		VendeurLocal vendeurLocal = getEntity(vendeurDTO.getId());
		String id = vendeurLocal.updateVendeur(vendeurDTO);
		VendeurLocal vendeurLocalModified = getEntity(id);
		tRes = vendeurLocalModified.getVendeurDTO();
		return tRes;
    }	
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param 
	 */
    public void removeVendeur(VendeurDTO vendeurDTO) throws Exception {
		/*try {
			VendeurLocal vendeurLocal = getEntity(vendeurDTO.getId());
			vendeurLocal.remove();
		}catch (Exception e) {
			throw new Exception("Cannot remove vendeur", e);
		}*/
    }	
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param vendeurId
	 */
	public VendeurDTO getVendeur(String vendeurId) throws Exception {
		try {
			VendeurLocal vendeurLocal = getEntity(vendeurId);
			return vendeurLocal.getVendeurDTO();
		} catch (Exception e) {
			throw new Exception("Cannot get vendeur", e);
		}
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param vendeurId
	 */
	public MembreDTO getMembre(String vendeurId) throws Exception {
		try {
			VendeurLocal vendeurLocal = getEntity(vendeurId);
			return vendeurLocal.getMembreLocal().getMembreDTO();
		} catch (Exception e) {
			throw new Exception("Cannot get membre", e);
		}
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param
	 */
	public Collection getVendeurs() {
		Collection vendeurs = null;
		ArrayList tRes = new ArrayList();
		try {
			VendeurLocalHome home = getEntityHome();
			vendeurs = home.findAll();
			for (Iterator it = vendeurs.iterator(); it.hasNext(); ) {
				VendeurLocal vendeurLocal = (VendeurLocal) it.next();
				tRes.add(vendeurLocal.getVendeurDTO());
		    }			
		}catch (FinderException e) {
			e.printStackTrace();
		}
		return tRes;
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param vendeurDTO
	 */
	public Collection getArticles(String vendeurId) {
		Collection tRes = new ArrayList();
		try {
			VendeurLocal vendeur = getEntity(vendeurId);
			Collection articles = vendeur.getArticle();
			for (Iterator it = articles.iterator(); it.hasNext(); ) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				tRes.add(articleLocal.getArticleDTO());
		    }		
		} catch (FinderException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tRes;		
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param vendeurDTO
	 */
	public Collection getArticlesEnAttente(String vendeurId) {
		return this.getArticles(vendeurId);
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param vendeurDTO
	 */
	public Collection getArticlesEnVente(String vendeurId) {
		Collection tRes = new ArrayList(); 
		return tRes;	
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param vendeurDTO
	 */
	public Collection getArticlesVendus(String vendeurId) {
		Collection tRes = new ArrayList(); 
		return tRes;	
	}

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param vendeurId, ArticleDTO
	 * @throws VendeurInconnuException 
	 * @throws Exception 
	 */
	public ArticleDTO saveArticle(String vendeurId, ArticleDTO articleDTO) throws VendeurInconnuException, Exception{
		ArticleDTO tRes = null;
		try {
			VendeurLocal vendeur = getEntity(vendeurId);
			ServiceLocator locator = ServiceLocator.getInstance();
			ArticleFacadeLocalHome articleFacadeLocalHome = (ArticleFacadeLocalHome) locator.getLocalHome(ArticleFacadeLocalHome.JNDI_NAME);
			ArticleFacadeLocal articleFacade = (ArticleFacadeLocal) articleFacadeLocalHome.create();
			tRes = (ArticleDTO) articleFacade.saveArticle(vendeurId, articleDTO);
			articleFacade.remove();
			articleFacadeLocalHome = null;
		} catch (FinderException e) {
			throw new VendeurInconnuException("Le vendeur " + vendeurId + " n'existe pas !");
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (EJBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (RemoveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return tRes;		
	}

   /** Retrieves the local interface of the Customer entity bean. 
  * @throws Exception */
	public static VendeurLocal getEntity(String id) throws FinderException{
     try {
     	VendeurLocalHome home = getEntityHome();
         return home.findByPrimaryKey(id);
     } catch (FinderException e) {
         throw new FinderException("Cannot locate Vendeur" + e.getMessage());
     }
 }
 
  /** Retrieves the local home interface of the Customer intity bean. */
 public static VendeurLocalHome getEntityHome(){
 	VendeurLocalHome home = null;
 	try {
	        ServiceLocator locator = ServiceLocator.getInstance();
			home = (VendeurLocalHome) locator.getLocalHome(VendeurLocalHome.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
     return home;
 }		
	
}
