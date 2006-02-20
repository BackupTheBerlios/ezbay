package axlomoso.ezbay.model.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ArticleLocalHome;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieFacadeLocal;
import axlomoso.ezbay.model.interfaces.CategorieFacadeLocalHome;
import axlomoso.ezbay.model.interfaces.CategorieLocal;
import axlomoso.ezbay.model.interfaces.CategorieLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurFacadeLocal;
import axlomoso.ezbay.model.interfaces.VendeurFacadeLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurLocal;
import axlomoso.ezbay.model.interfaces.VendeurLocalHome;
import axlomoso.ezbay.utils.ServiceLocator;
import axlomoso.ezbay.utils.ServiceLocatorException;


/**
 * XDoclet-based session bean. The class must be declared public according to
 * the EJB specification.
 * 
 * To generate the EJB related files to this EJB: - Add Standard EJB module to
 * XDoclet project properties - Customize XDoclet configuration for your
 * appserver - Run XDoclet
 * 
 * Below are the xdoclet-related tags needed for this EJB.
 * 
 * @ejb.bean name="ArticleFacade" display-name="Name for ArticleFacade"
 *           description="Description for ArticleFacade"
 *           jndi-name="ejb/ArticleFacade" type="Stateless" view-type="both"
 */
public class ArticleFacadeBean implements SessionBean {

	/** The session context */
	private SessionContext context;

	public ArticleFacadeBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Set the associated session context. The container calls this method after
	 * the instance creation.
	 * 
	 * The enterprise bean instance should store the reference to the context
	 * object in an instance variable.
	 * 
	 * This method is called with no transaction context.
	 * 
	 * @throws EJBException
	 *             Thrown if method fails due to system-level error.
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
	 * @param vendeurDTO
	 * @throws Exception 
	 */
	public ArticleDTO saveArticle(String vendeurId, ArticleDTO articleDTO, String categorieId) throws Exception{
		ArticleDTO tRes = null;
		boolean exists = false;
		try {
			if(articleDTO.getId() == null){
				exists = false;
			}
			else{
				ArticleLocal articleLocal = getEntity(articleDTO.getId()); //test de l'existence de l'article
				exists = true;
			}
		} catch (FinderException e) {
			exists = false;
		}
		if(exists){
			// l'article existe : mise à jour.
			tRes = this.updateArticle(articleDTO, categorieId);
		}
		else{
			//l'article n'existe pas: création.
			tRes = this.createArticle(vendeurId, articleDTO, categorieId);
		}
		return tRes;
	}	
	
	
	private ArticleDTO createArticle(String vendeurId, ArticleDTO articleDTO, String categorieId) throws Exception {
		ArticleDTO tRes = null;
		try {
			ArticleLocalHome home = getEntityHome();
			VendeurLocal vendeurLocal = VendeurFacadeBean.getEntity(vendeurId);
			ArticleLocal articleLocal = home.create(articleDTO, vendeurLocal);
			//categorie
	        CategorieLocal categorieLocal = CategorieFacadeBean.getEntity(categorieId);
	        articleLocal.setCategorieLocal(categorieLocal);
	        // return
			tRes = articleLocal.getArticleDTO();
		} catch (Exception e) {
			throw new Exception("Cannot create article", e);
		}
		return tRes;
	}

	private ArticleDTO updateArticle(ArticleDTO articleDTO, String categorieId) throws Exception {
		ArticleDTO tRes = null;
		try {
			ArticleLocal articleLocal = getEntity(articleDTO.getId());
			String id = articleLocal.updateArticle(articleDTO);
			//categorie
	        CategorieLocal categorieLocal = CategorieFacadeBean.getEntity(categorieId);
	        articleLocal.setCategorieLocal(categorieLocal);
	        // return
			tRes = getEntity(id).getArticleDTO();
		} catch (Exception e) {
			throw new Exception("Cannot update article", e);
		}
		return tRes;
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 * @param
	 */
    public void removeArticle(String articleId) throws Exception {
		try {
			ArticleLocal articleLocal = getEntity(articleId);
			articleLocal.remove();
		}catch (Exception e) {
			throw new Exception("Cannot remove article", e);
		}
    }	
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param articleId
	 */
	public ArticleDTO getArticle(String articleId) throws Exception {
		try {
			ArticleLocal articleLocal = getEntity(articleId);
			return articleLocal.getArticleDTO();
		} catch (Exception e) {
			throw new Exception("Cannot get article", e);
		}
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param articleId
	 */
	public VendeurDTO getVendeurDTO(String articleId) throws Exception {
		try {
			ArticleLocal articleLocal = getEntity(articleId);
			return articleLocal.getVendeurLocal().getVendeurDTO();
		} catch (Exception e) {
			throw new Exception("Cannot get article", e);
		}
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param articleId
	 */
	public CategorieDTO getCategorieDTO(String articleId) throws Exception {
		try {
			ArticleLocal articleLocal = getEntity(articleId);
			return articleLocal.getCategorieLocal().getCategorieDTO();
		} catch (Exception e) {
			throw new Exception("Cannot get categorie", e);
		}
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param
	 */
	public Collection getArticles() {
		Collection articles = null;
		ArrayList tRes = new ArrayList();
		try {
			ArticleLocalHome home = getEntityHome();
			articles = home.findAll();
			for (Iterator it = articles.iterator(); it.hasNext(); ) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				tRes.add(articleLocal.getArticleDTO());
		    }			
		} catch (FinderException e) {
			e.printStackTrace();
		}
		return tRes;
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param
	 */
	public Collection getArticles(String libcategorie, String libelle, String marque, String modele, Double prixVente, Integer anneeFabrication, Date dateLimite) {
		Collection articles = null;
		ArrayList tRes = new ArrayList();
		try {
			ArticleLocalHome home = getEntityHome();
			articles = home.findByFields(libcategorie,libelle,marque,modele,prixVente,anneeFabrication,dateLimite);
			for (Iterator it = articles.iterator(); it.hasNext(); ) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				tRes.add(articleLocal.getArticleDTO());
		    }			
		} catch (FinderException e) {
			e.printStackTrace();
		}
		return tRes;
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param libelle
	 */
	public Collection getArticlesByLibelle(String libelle) {
		Collection articles = null;
		ArrayList tRes = new ArrayList();
		try {
			ArticleLocalHome home = getEntityHome();
			articles = home.findByLibelle(libelle);
			for (Iterator it = articles.iterator(); it.hasNext(); ) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				tRes.add(articleLocal.getArticleDTO());
		    }			
		} catch (FinderException e) {
			e.printStackTrace();
		}
		return tRes;
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param vendeurId
	 */
	public Collection getArticlesByVendeur(String vendeurId) {
		Collection articles = null;
		ArrayList tRes = new ArrayList();
		try {
			VendeurLocalHome vendeurHome = VendeurFacadeBean.getEntityHome();
			VendeurLocal vendeur = vendeurHome.findByPrimaryKey(vendeurId);
			articles = vendeur.getArticle() ;
			for (Iterator it = articles.iterator(); it.hasNext(); ) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				tRes.add(articleLocal.getArticleDTO());
		    }			
		} catch (FinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return tRes;
	}	
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param categorieId
	 */
	public Collection getArticlesByCategorie(String categorieId) {
		Collection articles = null;
		ArrayList tRes = new ArrayList();
		try {
			CategorieLocal categorie = CategorieFacadeBean.getEntity(categorieId);
			articles = categorie.getArticleLocal();
			for (Iterator it = articles.iterator(); it.hasNext(); ) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				tRes.add(articleLocal.getArticleDTO());
		    }			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return tRes;
	}

    /** Retrieves the local interface of the Customer entity bean. 
     * @throws FinderException 
     * @throws Exception */
	public static ArticleLocal getEntity(String id) throws FinderException{
        try {
        	ArticleLocalHome home = getEntityHome();
            return home.findByPrimaryKey(id);
        } catch (FinderException e) {
            throw new FinderException("Cannot locate Article" + e.getMessage());
        }
    }
    
     /** Retrieves the local home interface of the Customer intity bean. */
    public static ArticleLocalHome getEntityHome(){
    	ArticleLocalHome home = null;
    	try {
	        ServiceLocator locator = ServiceLocator.getInstance();
			home = (ArticleLocalHome) locator.getLocalHome(ArticleLocalHome.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
        return home;
    }	
	
}
