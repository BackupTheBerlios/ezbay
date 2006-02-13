package axlomoso.ezbay.model.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ArticleLocalHome;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieLocal;
import axlomoso.ezbay.model.interfaces.CategorieLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
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
 * @ejb.bean name="CategorieFacade"
 *           display-name="Name for CategorieFacade"
 *           description="Description for CategorieFacade"
 *           jndi-name="ejb/CategorieFacade"
 *           type="Stateless"
 *           view-type="both"
 */
public class CategorieFacadeBean implements SessionBean {

	/** The session context */
	private SessionContext context;

	public CategorieFacadeBean() {
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
	 * @ejb.interface-method view-type = "remote"
	 * @param categorieDTO
	 */
	public CategorieDTO createCategorie(CategorieDTO categorieDTO) throws Exception {
		try {
			CategorieLocalHome home = getEntityHome();
			CategorieLocal categorieLocal = home.create(categorieDTO);
			return categorieLocal.getCategorieDTO();
		} catch (Exception e) {
			throw new Exception("Cannot create categorie", e);
		}
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param
	 * @throws Exception 
	 */
    public void removeCategorie(CategorieDTO categorieDTO) throws Exception {
		try {
			CategorieLocal categorieLocal = getEntity(categorieDTO.getId());
			categorieLocal.remove();
		}catch (Exception e) {
			throw new Exception("Cannot remove categorie", e);
		}
    }	

    /**
	 * @ejb.interface-method view-type = "remote"
	 * @param categorieId
	 */
	public CategorieDTO getCategorie(String categorieId) throws Exception {
		try {
			CategorieLocal categorieLocal = getEntity(categorieId);
			return categorieLocal.getCategorieDTO();
		} catch (Exception e) {
			throw new Exception("Cannot get categorie", e);
		}
	}
		
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param categorieId
	 */
	public Collection getArticlesDTO(String categorieId) throws Exception {
		ArrayList tRes = new ArrayList();
		try {
			CategorieLocal categorieLocal = getEntity(categorieId);
			Collection articles = categorieLocal.getArticleLocal();
			for (Iterator it = articles.iterator(); it.hasNext(); ) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				tRes.add(articleLocal.getArticleDTO());
		    }	
			return tRes;
		} catch (Exception e) {
			throw new Exception("Cannot get article", e);
		}
	}
	
	
    /** Retrieves the local interface of the Customer entity bean. 
     * @throws Exception */
	public static CategorieLocal getEntity(String id) throws Exception{
        try {
            CategorieLocalHome home = getEntityHome();
            return home.findByPrimaryKey(id);
        } catch (Exception e) {
            throw new Exception("Cannot locate CustomerHome", e);
        }
    }
    
     /** Retrieves the local home interface of the Customer intity bean. */
	public static CategorieLocalHome getEntityHome(){
    	CategorieLocalHome home = null;
    	try {
	        ServiceLocator locator = ServiceLocator.getInstance();
			home = (CategorieLocalHome) locator.getLocalHome(CategorieLocalHome.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
        return home;
    }
	
	
	
}
