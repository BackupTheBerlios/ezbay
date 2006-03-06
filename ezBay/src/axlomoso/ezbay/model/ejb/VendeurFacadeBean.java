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

import axlomoso.ezbay.exceptions.ArticleEnEnchereException;
import axlomoso.ezbay.exceptions.ArticleEnVenteException;
import axlomoso.ezbay.exceptions.ArticleProprietaireException;
import axlomoso.ezbay.exceptions.ArticleVenduException;
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
	ServiceLocator locator;
	ArticleFacadeLocalHome articleFacadeLocalHome;
	ArticleFacadeLocal articleFacade;
	private VendeurLocalHome home;

	public VendeurFacadeBean() {
		super();
		try {
			locator = ServiceLocator.getInstance();
			articleFacadeLocalHome = (ArticleFacadeLocalHome) locator.getLocalHome(ArticleFacadeLocalHome.JNDI_NAME);
			articleFacade = (ArticleFacadeLocal) articleFacadeLocalHome.create();
			home = getEntityHome();
		} catch (ServiceLocatorException e) {
			System.out.println(e.getMessage()); 
		} catch (CreateException e) {
			System.out.println(e.getMessage()); 
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
	 * @ejb.interface-method view-type = "local"
	 * cette methode permet de creer ou de mettre a jour les informations concernant le vendeur
	 * @param vendeurDTO
	 * @return VendeurDTO
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
	 * cette methode permet de retourner le vendeur en passant en parametre son identifiant
	 * @param vendeurId
	 * @return VendeurDTO
	 * @throws Exception
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
	 * cette methode permet de retourner le membre en passant le vendeurId
	 * @param vendeurId
	 * @return
	 * @throws Exception
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
	 */
	public Collection getArticlesEnAttente(String vendeurId) {
		return articleFacade.getArticlesEnAttenteByVendeur(vendeurId);
	}
	
	/**
	 * @ejb.interface-method view-type = "both"	
	 */
	public Collection getArticlesEnVente(String vendeurId) {
		return articleFacade.getArticlesEnVenteByVendeur(vendeurId);
	}
	
	/**
	 * @ejb.interface-method view-type = "both"	
	 */
	public Collection getArticlesVendus(String vendeurId) {
		return articleFacade.getArticlesVendusByVendeur(vendeurId);
	}

	/**
	 * @ejb.interface-method view-type = "both"
	 * cette methode permet de creer un article ou le mettre a jour 
	 * @param vendeurId
	 * @param articleDTO
	 * @param categorieId
	 * @return ArticleDTO
	 * @throws VendeurInconnuException
	 * @throws Exception
	 */
	public ArticleDTO saveArticle(String vendeurId, ArticleDTO articleDTO, String categorieId) throws VendeurInconnuException, Exception{
		ArticleDTO tRes = null;
		try {
			VendeurLocal vendeur = getEntity(vendeurId); // vérification de l'existence du vendeur
			tRes = (ArticleDTO) articleFacade.saveArticle(vendeurId, articleDTO, categorieId);
		} catch (FinderException e) {
			throw new VendeurInconnuException("Le vendeur " + vendeurId + " n'existe pas !");
		} catch (CreateException e) {
			System.out.println(e.getMessage()); 
			throw e;
		} catch (EJBException e) {
			System.out.println(e.getMessage()); 
			throw e;
		}
		return tRes;		
	}
	
	/**
	 * @ejb.interface-method view-type = "both"	 
	 * cette methode permet de supprimer un article
	 * @param vendeurId
	 * @param articleId
	 * @throws ArticleEnVenteException
	 * @throws ArticleVenduException
	 * @throws Exception
	 */
	
	public void removeArticle(String vendeurId, String articleId) throws ArticleEnVenteException, ArticleVenduException, Exception {
		if( !this.possedeArticle(vendeurId, articleId) ) {
			throw new ArticleProprietaireException();
		}		
		else{
				articleFacade.removeArticle(articleId);			
		}
	}
	
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * cette methode permet de retirer un article de la vente si il y n'est pas encheri 
	 * @param vendeurId, ArticleDTO
	 * @throws ArticleEnEnchereException 
	 * @throws ArticleVenduException 
	 * @throws VendeurInconnuException 
	 * @throws Exception 
	 */
	public void retirerArticle(String vendeurId, String articleId) throws ArticleEnEnchereException, ArticleVenduException,ArticleProprietaireException{
		if( !this.possedeArticle(vendeurId, articleId) ) {
			throw new ArticleProprietaireException();
		}
		else if( articleFacade.isArticleEnEnchere(articleId)){
			throw new ArticleEnEnchereException();
		}
		else if( articleFacade.isArticleVendu(articleId)){
			throw new ArticleVenduException();
		}
		else{
			try {
				articleFacade.retirerArticle(articleId);
			} catch (Exception e) {				
				System.out.println(e.getMessage()); 
			}
		}
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * cette methode permet de mettre un article en vente
	 * @param vendeurId
	 * @param articleId
	 * @throws ArticleProprietaireException
	 */
	
	public void mettreEnVenteArticle(String vendeurId, String articleId) throws ArticleProprietaireException{
		if( !this.possedeArticle(vendeurId, articleId) ) {
			throw new ArticleProprietaireException();
		}		
		else{
			try {
				articleFacade.mettreEnVenteArticle(articleId);
			} catch (Exception e) {				
				System.out.println(e.getMessage()); 
			}
		}
	}
	
	/**cette methode retourne une instance de l'interface local du Vendeur entity bean 
	 * @param id
	 * @return ActionEnchereLocal
	 * @throws FinderException
	 */
	public static VendeurLocal getEntity(String id) throws FinderException{
     try {
     	VendeurLocalHome home = getEntityHome();
         return home.findByPrimaryKey(id);
     } catch (FinderException e) {
         throw new FinderException("Cannot locate Vendeur" + e.getMessage());
     }
 }
 
	/** cette methode retourne une instance de l'interface local Home du Vendeur entity bean  */
	public static VendeurLocalHome getEntityHome(){
 	VendeurLocalHome home = null;
 	try {
	        ServiceLocator locator = ServiceLocator.getInstance();
			home = (VendeurLocalHome) locator.getLocalHome(VendeurLocalHome.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			System.out.println(e.getMessage()); 
		}
     return home;
 }		
 
 
	private boolean possedeArticle(String vendeurId, String articleId) {
		boolean tRes = false;
		try {
			VendeurLocal vendeur = getEntity(vendeurId);
			Collection articles = vendeur.getArticle();
			for (Iterator it = articles.iterator(); it.hasNext(); ) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				if( articleLocal.getId().equals(articleId) ){
					return true;
				}
		    }		
		} catch (FinderException e) {
			System.out.println(e.getMessage()); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage()); 
		}
		return tRes;		
	}
	
}
