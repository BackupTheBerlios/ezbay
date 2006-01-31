package ezbay.model.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ezbay.model.interfaces.ArticleDTO;
import ezbay.model.interfaces.ArticleLocal;
import ezbay.model.interfaces.ArticleLocalHome;
import ezbay.model.interfaces.VendeurDTO;
import ezbay.model.interfaces.VendeurLocal;
import ezbay.model.interfaces.VendeurLocalHome;

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
	 * @ejb.interface-method view-type = "remote"
	 * @param articleDTO
	 */
	public ArticleDTO createArticle(ArticleDTO articleDTO, VendeurDTO vendeurDTO) throws Exception {
		try {
			InitialContext initialContext = new InitialContext();
			ArticleLocalHome home = (ArticleLocalHome) initialContext.lookup(ArticleLocalHome.JNDI_NAME);
			VendeurLocalHome vendeurHome = (VendeurLocalHome) initialContext.lookup(VendeurLocalHome.JNDI_NAME);
			VendeurLocal vendeurLocal = vendeurHome.findByPrimaryKey(vendeurDTO.getId());

			ArticleLocal articleLocal = home.create(articleDTO, vendeurLocal);			
			return articleLocal.getArticleDTO();
		} catch (Exception e) {
			throw new Exception("Cannot create article", e);
		}
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param articleDTO
	 */
	public void updateArticle(ArticleDTO articleDTO) throws Exception {
		try {
			InitialContext initialContext = new InitialContext();
			ArticleLocalHome home = (ArticleLocalHome) initialContext.lookup(ArticleLocalHome.JNDI_NAME);
			ArticleLocal articleLocal = home.findByPrimaryKey(articleDTO.getId());
			articleLocal.updateArticle(articleDTO);
		} catch (Exception e) {
			throw new Exception("Cannot create customer", e);
		}
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param
	 */
    public void removeArticle(ArticleDTO articleDTO) throws Exception {
		try {
			InitialContext initialContext = new InitialContext();
			ArticleLocalHome home = (ArticleLocalHome) initialContext.lookup(ArticleLocalHome.JNDI_NAME);
			ArticleLocal articleLocal = home.findByPrimaryKey(articleDTO.getId());
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
			InitialContext initialContext = new InitialContext();
			ArticleLocalHome home = (ArticleLocalHome) initialContext.lookup(ArticleLocalHome.JNDI_NAME);
			ArticleLocal articleLocal = home.findByPrimaryKey(articleId);
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
			InitialContext initialContext = new InitialContext();
			ArticleLocalHome home = (ArticleLocalHome) initialContext.lookup(ArticleLocalHome.JNDI_NAME);
			ArticleLocal articleLocal = home.findByPrimaryKey(articleId);
			return articleLocal.getVendeurLocal().getVendeurDTO();
		} catch (Exception e) {
			throw new Exception("Cannot get article", e);
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
			InitialContext initialContext;
			initialContext = new InitialContext();
			ArticleLocalHome home = (ArticleLocalHome) initialContext.lookup(ArticleLocalHome.JNDI_NAME);
			articles = home.findAll();
			for (Iterator it = articles.iterator(); it.hasNext(); ) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				tRes.add(articleLocal.getArticleDTO());
		    }			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FinderException e) {
			e.printStackTrace();
		}
		return tRes;
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param
	 */
	public Collection getArticlesByLibelle(String libelle) {
		Collection articles = null;
		ArrayList tRes = new ArrayList();
		try {
			InitialContext initialContext;
			initialContext = new InitialContext();
			ArticleLocalHome home = (ArticleLocalHome) initialContext.lookup(ArticleLocalHome.JNDI_NAME);
			articles = home.findByLibelle(libelle);
			for (Iterator it = articles.iterator(); it.hasNext(); ) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				tRes.add(articleLocal.getArticleDTO());
		    }			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FinderException e) {
			e.printStackTrace();
		}
		return tRes;
	}
}
