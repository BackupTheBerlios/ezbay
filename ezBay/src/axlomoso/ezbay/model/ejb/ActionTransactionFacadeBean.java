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
	 * @ejb.interface-method view-type = "local"
	 * @ejb.transaction type="Required" 	
	 * cette methode permet de creer une transaction a la fin de la vente en passant en parametre un article et un client 
	 * @param transactionDTO
	 * @param articleLocal
	 * @param clientLocal
	 * @return ActionTransactionDTO
	 * @throws CreateException
	 */ 	
	
	public ActionTransactionDTO createActionTransaction(ActionTransactionDTO transactionDTO, ArticleLocal articleLocal, ClientLocal clientLocal) throws CreateException{
		ActionTransactionDTO tRes = null;
		try {
			ActionTransactionLocalHome home = (ActionTransactionLocalHome) getEntityHome();
			ActionTransactionLocal transactionLocal;
			transactionLocal = home.create(transactionDTO);//creation de la transaction
			transactionLocal.setArticleLocal(articleLocal);//pour les relation cmr on ajoute l article local 
			transactionLocal.setClientLocal(clientLocal);//pour les relation cmr on ajoute le client local 
			tRes = transactionLocal.getActionTransactionDTO();
		} catch (CreateException e) {
			System.out.println(e.getMessage()); 
			throw e;
		}
		return tRes;
	}
	
	/**
	 * @ejb.interface-method view-type = "local"	
	 * cette methode permet de retourner une transaction en passant en parametre l'identifiant de l'article
	 * @param articleId
	 * @return ActionTransactionDTO
	 */ 	 
	
	public ActionTransactionDTO getActionTransactionByArticle(String articleId){
		ActionTransactionDTO tRes = null;
		try {
			ActionTransactionLocalHome home = getEntityHome();
			ActionTransactionLocal transactionLocal = home.findByArticleLocal(articleId);
			tRes = transactionLocal.getActionTransactionDTO(); 
		} catch (FinderException e) {
			System.out.println("cannot get ActionTransaction for article id=" + articleId);
		}
		return tRes;
	}
	
	/**
	 * @ejb.interface-method view-type = "local"
	 /**cette methode permet de retourner le client qui a gagn� l'enchere
	 * @param articleId
	 * @return ClientDTO
	 */	 
	
	public ClientDTO getAcquereur(String articleId){
		ClientDTO tRes = null;
		try {
			ActionTransactionLocalHome home = getEntityHome();
			ActionTransactionLocal transactionLocal = home.findByArticleLocal(articleId);
			ClientLocal clientLocal = transactionLocal.getClientLocal();//on recuper le client local
			tRes = clientLocal.getClientDTO();//on retourne le clientDTO
		} catch (FinderException e) {
			System.out.println("cannot get ClientLocal Acqu�reur for article id=" + articleId);		
		}
		return tRes;
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * cette methode permet au client de donner un avis sur la transaction 
	 * @param transactionId
	 * @param avis
	 */
	
	public void setAvis(String transactionId, String avis){
		try {
			ActionTransactionLocal transactionLocal = getEntity(transactionId);
			transactionLocal.setAvis(avis);
		} catch (FinderException e) {
			System.out.println(e.getMessage()); 
		}
	}
	
	
	/**cette methode retourne une instance de l'interface local du ActionTransaction entity bean 
	 * @param id
	 * @return ActionEnchereLocal
	 * @throws FinderException
	 */
	public static ActionTransactionLocal getEntity(String id) throws FinderException {
		try {
			ActionTransactionLocalHome home = getEntityHome();
			return home.findByPrimaryKey(id);
		} catch (FinderException e) {
			throw new FinderException("Cannot locate ActionTransaction with id=" + id + " "+ e.getMessage());
		}
	}

	/** cette methode retourne une instance de l'interface local Home du ActionTransaction entity bean  */
	public static ActionTransactionLocalHome getEntityHome() {
		ActionTransactionLocalHome home = null;
		try {
			ServiceLocator locator = ServiceLocator.getInstance();
			home = (ActionTransactionLocalHome) locator
					.getLocalHome(ActionTransactionLocalHome.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			System.out.println(e.getMessage()); 
		}
		return home;
	}

}
