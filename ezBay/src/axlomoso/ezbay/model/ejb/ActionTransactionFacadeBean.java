package axlomoso.ezbay.model.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.ActionEnchereLocal;
import axlomoso.ezbay.model.interfaces.ActionEnchereLocalHome;
import axlomoso.ezbay.model.interfaces.ActionTransactionDTO;
import axlomoso.ezbay.model.interfaces.ActionTransactionLocal;
import axlomoso.ezbay.model.interfaces.ActionTransactionLocalHome;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.ClientLocal;
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
 * @ejb.bean name="ActionTransactionFacade"
 *           display-name="Name for ActionTransactionFacade"
 *           description="Description for ActionTransactionFacade"
 *           jndi-name="ejb/ActionTransactioneFacade"
 *           type="Stateless"
 *           view-type="both"
 */
public class ActionTransactionFacadeBean implements SessionBean {

	/** The session context */
	private SessionContext context;

	public ActionTransactionFacadeBean() {
		super();
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
	 * @throws CreateException 
	 */
	public ActionTransactionDTO createActionTransaction(ActionTransactionDTO transactionDTO, ArticleLocal articleLocal, ClientLocal clientLocal) throws CreateException{
		ActionTransactionDTO tRes = null;
		try {
			ActionTransactionLocalHome home = (ActionTransactionLocalHome) getEntityHome();
			ActionTransactionLocal transactionLocal;
			transactionLocal = home.create(transactionDTO, articleLocal, clientLocal);
			transactionLocal.setArticleLocal(articleLocal);
			transactionLocal.setClientLocal(clientLocal);
			tRes = transactionLocal.getActionTransactionDTO();
		} catch (CreateException e) {
			e.printStackTrace();
			throw e;
		}
		return tRes;
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param vendeurDTO
	 * @throws FinderException 
	 * @throws CreateException 
	 */
	public ActionTransactionDTO getActionTransactionByArticle(String articleId){
		ActionTransactionDTO tRes = null;
		try {
			System.out.println("getActionTransactionByArticle. articleId = " + articleId);
			System.out.println("getActionTransactionByArticle 1");
			ActionTransactionLocalHome home = getEntityHome();
			System.out.println("getActionTransactionByArticle 2");
			ActionTransactionLocal transactionLocal = home.findByArticleLocal(articleId);
			System.out.println("getActionTransactionByArticle 3");
			tRes = transactionLocal.getActionTransactionDTO();
			System.out.println("getActionTransactionByArticle 4");
		} catch (FinderException e) {
			System.out.println("cannot get ActionTransaction for article id=" + articleId);
		}
		return tRes;
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param vendeurDTO
	 * @throws FinderException 
	 * @throws CreateException 
	 */
	public ClientDTO getAcquereur(String articleId){
		ClientDTO tRes = null;
		try {
			System.out.println("getActionTransactionByArticle. articleId = " + articleId);
			ActionTransactionLocalHome home = getEntityHome();
			ActionTransactionLocal transactionLocal = home.findByArticleLocal(articleId);
			ClientLocal clientLocal = transactionLocal.getClientLocal();
			tRes = clientLocal.getClientDTO();
		} catch (FinderException e) {
			System.out.println("cannot get ClientLocal Acquéreur for article id=" + articleId);		
		}
		return tRes;
	}
	
	/**
	 * Retrieves the local interface of the Customer entity bean.
	 * 
	 * @throws FinderException
	 * @throws Exception
	 */
	public static ActionTransactionLocal getEntity(String id) throws FinderException {
		try {
			ActionTransactionLocalHome home = getEntityHome();
			return home.findByPrimaryKey(id);
		} catch (FinderException e) {
			throw new FinderException("Cannot locate ActionTransaction with id=" + id + " "+ e.getMessage());
		}
	}

	/** Retrieves the local home interface of the Customer intity bean. */
	public static ActionTransactionLocalHome getEntityHome() {
		ActionTransactionLocalHome home = null;
		try {
			ServiceLocator locator = ServiceLocator.getInstance();
			home = (ActionTransactionLocalHome) locator
					.getLocalHome(ActionTransactionLocalHome.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return home;
	}

}
