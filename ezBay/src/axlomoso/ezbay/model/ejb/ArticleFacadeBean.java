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
import javax.naming.NamingException;

import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ArticleLocalHome;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieLocal;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
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
	 * @param vendeurId,
	 *            articleId
	 * @throws Exception
	 */
	public void mettreEnVente(String vendeurId, String articleId) {
		// A faire.
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 * @param vendeurDTO
	 * @throws Exception
	 */
	public ArticleDTO saveArticle(String vendeurId, ArticleDTO articleDTO,
			String categorieId) throws Exception {
		ArticleDTO tRes = null;
		boolean exists = false;
		try {
			if (articleDTO.getId() == null) {
				exists = false;
			} else {
				ArticleLocal articleLocal = getEntity(articleDTO.getId()); // test de l'existence de l'article
				exists = true;
			}
		} catch (FinderException e) {
			exists = false;
		}
		if (exists) {
			// l'article existe : mise à jour.
			tRes = this.updateArticle(articleDTO, categorieId);
		} else {
			// l'article n'existe pas: création.
			tRes = this.createArticle(vendeurId, articleDTO, categorieId);
		}
		return tRes;
	}

	private ArticleDTO createArticle(String vendeurId, ArticleDTO articleDTO,
			String categorieId) throws Exception {
		ArticleDTO tRes = null;
		try {
			ArticleLocalHome home = getEntityHome();
			VendeurLocal vendeurLocal = VendeurFacadeBean.getEntity(vendeurId);
			ArticleLocal articleLocal = home.create(articleDTO, vendeurLocal);
			// categorie
			CategorieLocal categorieLocal = CategorieFacadeBean
					.getEntity(categorieId);
			articleLocal.setCategorieLocal(categorieLocal);
			// return
			tRes = articleLocal.getArticleDTO();
		} catch (Exception e) {
			throw new Exception("Cannot create article", e);
		}
		return tRes;
	}

	private ArticleDTO updateArticle(ArticleDTO articleDTO, String categorieId)
			throws Exception {
		ArticleDTO tRes = null;
		try {
			ArticleLocal articleLocal = getEntity(articleDTO.getId());
			String id = articleLocal.updateArticle(articleDTO);
			// categorie
			CategorieLocal categorieLocal = CategorieFacadeBean
					.getEntity(categorieId);
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
		} catch (Exception e) {
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
	public Collection getAllArticles() {
		Collection articles = null;
		ArrayList tRes = new ArrayList();
		try {
			ArticleLocalHome home = getEntityHome();
			articles = home.findAll();
			for (Iterator it = articles.iterator(); it.hasNext();) {
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
	public Collection rechercherArticlesEnVente(String libcategorie, String libelle, String marque, String modele, Double prixVenteMin,
			Double prixVenteMax, Integer anneeFabrication, Date dateLimite) {
		//retourne une collection d'ArticleDTO
		Collection tTemp = rechercherArticles(libcategorie, libelle, marque, modele, prixVenteMin, prixVenteMax, anneeFabrication, dateLimite);
		return this.getOnlyArticlesEnVente(tTemp);
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param vendeurId
	 */
	public Collection getArticlesEnVenteByVendeur(String vendeurId) {
		//	retourne une collection d'ArticleDTO
		return this.getOnlyArticlesEnVente(this.getArticlesByVendeur(vendeurId));
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param vendeurId
	 */
	public Collection getArticlesEnAttenteByVendeur(String vendeurId) {
		//	retourne une collection d'ArticleDTO
		return this.getOnlyArticlesEnAttente(this.getArticlesByVendeur(vendeurId));
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param vendeurId
	 */
	public Collection getArticlesVendusByVendeur(String vendeurId) {
		//	retourne une collection d'ArticleDTO
		return this.getOnlyArticlesVendus(this.getArticlesByVendeur(vendeurId));
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param categorieId
	 */
	public Collection getArticlesEnVenteByCategorie(String categorieId) {
		//	retourne une collection d'ArticleDTO
		return this.getOnlyArticlesEnVente(this.getArticlesByCategorie(categorieId));
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param
	 */
	public Collection rechercherArticles(String libcategorie, String libelle, String marque, String modele, Double prixVenteMin,
			Double prixVenteMax, Integer anneeFabrication, Date dateLimite) {
		Collection articles = null;
		Collection tRes = new ArrayList();
		try {
			ArticleLocalHome home = getEntityHome();
			articles = home.findByFields(libcategorie, "%" + libelle + "%", "%" + marque + "%", "%" + modele + "%", prixVenteMin,					prixVenteMax, anneeFabrication, dateLimite);
			for (Iterator it = articles.iterator(); it.hasNext();) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				ArticleDTO articleDTO = articleLocal.getArticleDTO();
				tRes.add(articleDTO);
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
		Collection tRes = new ArrayList();
		try {
			VendeurLocalHome vendeurHome = VendeurFacadeBean.getEntityHome();
			VendeurLocal vendeur = vendeurHome.findByPrimaryKey(vendeurId);
			articles = vendeur.getArticle();
			for (Iterator it = articles.iterator(); it.hasNext();) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				ArticleDTO articleDTO = articleLocal.getArticleDTO();
				tRes.add(articleDTO);
			}
		} catch (FinderException e) {
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
		Collection tRes = new ArrayList();
		try {
			CategorieLocal categorie = CategorieFacadeBean.getEntity(categorieId);
			articles = categorie.getArticleLocal();
			for (Iterator it = articles.iterator(); it.hasNext();) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				ArticleDTO articleDTO = articleLocal.getArticleDTO();
				tRes.add(articleDTO);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FinderException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tRes;
	}

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param articleDTO
	 */
	public boolean isArticleEnAttente(String articleId) {
		return !(this.isArticleEnVente(articleId) || this.isArticleVendu(articleId));
	}

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param articleDTO
	 */
	public boolean isArticleEnVente(String articleId) {
		boolean tRes = false;
		try{
			ArticleDTO articleDTO = getEntity(articleId).getArticleDTO();
			return articleDTO.getEnVente().booleanValue();
		}
		catch (FinderException e) {
		e.printStackTrace();
		}
		return tRes;		
	}

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param articleDTO
	 */
	public boolean isArticleVendu(String articleId) {
		boolean tRes = false;
		try {
			ArticleDTO articleDTO = getEntity(articleId).getArticleDTO();
			if (this.isArticleEnVente(articleDTO)) {
				tRes = false;
				return tRes;
			}
			ArticleLocal articleLocal = ArticleFacadeBean.getEntity(articleDTO.getId());
			tRes = (articleLocal.getActionTransactionLocal() != null);
		} catch (FinderException e) {
			e.printStackTrace();
		}
		return tRes;
	}

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param articleDTO
	 */
	public boolean isArticleEnAttente(ArticleDTO articleDTO) {
		return !(this.isArticleEnVente(articleDTO) || this.isArticleVendu(articleDTO));
	}

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param articleDTO
	 */
	public boolean isArticleEnVente(ArticleDTO articleDTO) {
		return articleDTO.getEnVente().booleanValue();
	}

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param articleDTO
	 */
	public boolean isArticleVendu(ArticleDTO articleDTO) {
		boolean tRes = false;
		if (this.isArticleEnVente(articleDTO)) {
			tRes = false;
			return tRes;
		}
		try {
			ArticleLocal articleLocal = ArticleFacadeBean.getEntity(articleDTO
					.getId());
			tRes = (articleLocal.getActionTransactionLocal() != null);
		} catch (FinderException e) {
			e.printStackTrace();
		}
		return tRes;
	}

	private Collection getOnlyArticlesEnVente(Collection allArticlesLocal){
		// retourne une Collection d'ArticlesDTO
		Collection tRes = new ArrayList();
		for (Iterator it = allArticlesLocal.iterator(); it.hasNext();) {
			ArticleDTO articleDTO = (ArticleDTO) it.next();
			if(this.isArticleEnVente(articleDTO)){
				tRes.add(articleDTO);
			}
		}
		return tRes;
	}
	
	private Collection getOnlyArticlesVendus(Collection allArticlesLocal){
//		 retourne une Collection d'ArticlesDTO
		Collection tRes = new ArrayList();
		for (Iterator it = allArticlesLocal.iterator(); it.hasNext();) {
			ArticleDTO articleDTO = (ArticleDTO) it.next();
			if(this.isArticleVendu(articleDTO)){
				tRes.add(articleDTO);
			}
		}
		return tRes;
	}
	
	private Collection getOnlyArticlesEnAttente(Collection allArticlesLocal){
//		 retourne une Collection d'ArticlesDTO
		Collection tRes = new ArrayList();
		for (Iterator it = allArticlesLocal.iterator(); it.hasNext();) {
			ArticleDTO articleDTO = (ArticleDTO) it.next();
			if(this.isArticleEnAttente(articleDTO)){
				tRes.add(articleDTO);
			}
		}
		return tRes;
	}
	
	/**
	 * Retrieves the local interface of the Customer entity bean.
	 * 
	 * @throws FinderException
	 * @throws Exception
	 */
	public static ArticleLocal getEntity(String id) throws FinderException {
		try {
			ArticleLocalHome home = getEntityHome();
			return home.findByPrimaryKey(id);
		} catch (FinderException e) {
			throw new FinderException("Cannot locate Article" + e.getMessage());
		}
	}

	/** Retrieves the local home interface of the Customer intity bean. */
	public static ArticleLocalHome getEntityHome() {
		ArticleLocalHome home = null;
		try {
			ServiceLocator locator = ServiceLocator.getInstance();
			home = (ArticleLocalHome) locator
					.getLocalHome(ArticleLocalHome.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return home;
	}

}

	