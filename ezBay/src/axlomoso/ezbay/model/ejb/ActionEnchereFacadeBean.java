package axlomoso.ezbay.model.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import axlomoso.ezbay.model.interfaces.ActionEnchereLocal;
import axlomoso.ezbay.model.interfaces.ActionEnchereLocalHome;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ArticleLocalHome;
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
 * @ejb.bean name="ActionEnchereFacade"
 *           display-name="Name for ActionEnchereFacade"
 *           description="Description for ActionEnchereFacade"
 *           jndi-name="ejb/ActionEnchereFacade"
 *           type="Stateless"
 *           view-type="both"
 */
public class ActionEnchereFacadeBean implements SessionBean {

	/** The session context */
	private SessionContext context;

	public ActionEnchereFacadeBean() {
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
	 * @param articleId
	 * @throws Exception
	 */
	public Collection getActionEncheresByArticle(String articleId){
		Collection encheres = null;
		ArrayList tRes = new ArrayList();
		try {
			ActionEnchereLocalHome home = getEntityHome();
			encheres = home.findByArticleId(articleId);
			for (Iterator it = encheres.iterator(); it.hasNext();) {
				ActionEnchereLocal actionEnchereLocal = (ActionEnchereLocal) it.next();
				tRes.add(actionEnchereLocal.getActionEnchereDTO());
			}
		} catch (FinderException e) {
			e.printStackTrace();
		}
		return tRes;
	}
	
	/**
	 * Retrieves the local interface of the Customer entity bean.
	 * 
	 * @throws FinderException
	 * @throws Exception
	 */
	public static ActionEnchereLocal getEntity(String id) throws FinderException {
		try {
			ActionEnchereLocalHome home = getEntityHome();
			return home.findByPrimaryKey(id);
		} catch (FinderException e) {
			throw new FinderException("Cannot locate ActionEnchere" + e.getMessage());
		}
	}

	/** Retrieves the local home interface of the Customer intity bean. */
	public static ActionEnchereLocalHome getEntityHome() {
		ActionEnchereLocalHome home = null;
		try {
			ServiceLocator locator = ServiceLocator.getInstance();
			home = (ActionEnchereLocalHome) locator
					.getLocalHome(ActionEnchereLocalHome.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return home;
	}


}
