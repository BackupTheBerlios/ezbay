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

import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.ActionEnchereLocal;
import axlomoso.ezbay.model.interfaces.ActionEnchereLocalHome;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ArticleLocalHome;
import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.ClientLocal;
import axlomoso.ezbay.model.interfaces.ClientLocalHome;
import axlomoso.ezbay.model.interfaces.MembreDTO;
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
	 * @ejb.transaction type="Required" 
	 * cette methode permet de creer une enchere en passant en parametre l article a encherir et le client qui effectue l enchere
	 * @param enchereDTO
	 * @param articleLocal
	 * @param clientLocal
	 * @return ActionEnchereDTO
	 * @throws CreateException
	 */
	 
	
	public ActionEnchereDTO createActionEnchere(ActionEnchereDTO enchereDTO, ArticleLocal articleLocal, ClientLocal clientLocal) throws CreateException{
		ActionEnchereDTO tRes = null;
		try {
			ActionEnchereLocalHome home = (ActionEnchereLocalHome) getEntityHome();
			ActionEnchereLocal enchereLocal;
			enchereLocal = home.create(enchereDTO, articleLocal, clientLocal);//creation de l'enchere
			tRes = enchereLocal.getActionEnchereDTO();
		} catch (CreateException e) {
			System.out.println(e.getMessage()); 
			throw e;
		}
		return tRes;
	}
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * cette methode permet d'enlever une enchere à la fin de la vente
	 * @param enchereId
	 */ 
	
	public void removeActionEnchere(String enchereId){
		try {
			ActionEnchereLocal enchereLocal = getEntity(enchereId);
			enchereLocal.remove();//suppression de l enchere
		} catch (FinderException e) {
			System.out.println(e.getMessage()); 
		} catch (EJBException e) {
			System.out.println(e.getMessage()); 
		} catch (RemoveException e) {
			System.out.println(e.getMessage()); 
		}
	}	

	/**
	 * @ejb.interface-method view-type = "both"
	 * cette methode permet de retourner une liste des enchers pour un articles
	 * @param articleId
	 * @return Collection
	 */	 
	
	public Collection getActionEncheresByArticle(String articleId){
		Collection encheres = null;
		ArrayList tRes = new ArrayList();
		try {
			ActionEnchereLocalHome home = getEntityHome();
			encheres = home.findByArticleId(articleId);//on utilise un finder qui retourne la liste des encheres pour un article 
			for (Iterator it = encheres.iterator(); it.hasNext();) {
				ActionEnchereLocal actionEnchereLocal = (ActionEnchereLocal) it.next();
				tRes.add(actionEnchereLocal.getActionEnchereDTO());//on ajout dans la collection des articleDTOs
			}
		} catch (FinderException e) {
			//System.out.println(e.getMessage()); 
		}
		return tRes;//on retourne une collection d articleDTOs
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * Cette methode permet de retourner le client qui a effectuer une enchere en passant en parametre l enchereId
	 * @param enchereId
	 * @return ClientDTO
	 */
	 
	
	public ClientDTO getEncherisseur(String enchereId){
		ClientDTO tRes = null;
		try {
			ActionEnchereLocal enchereLocal = getEntity(enchereId);
			tRes = enchereLocal.getClientLocal().getClientDTO();//on retourne l objet DTO du client Local
		} catch (FinderException e) {			
			System.out.println(e.getMessage()); 
		}
		return tRes;
	}	
	

	/**cette methode retourne une instance de l'interface local du ActionEnchere entity bean 
	 * @param id
	 * @return ActionEnchereLocal
	 * @throws FinderException
	 */
	public static ActionEnchereLocal getEntity(String id) throws FinderException {
		try {
			ActionEnchereLocalHome home = getEntityHome();
			return home.findByPrimaryKey(id);
		} catch (FinderException e) {
			throw new FinderException("Cannot locate ActionEnchere with id=" + id + " "+ e.getMessage());
		}
	}

	/** cette methode retourne une instance de l'interface local Home du ActionEnchere entity bean  */
	public static ActionEnchereLocalHome getEntityHome() {
		ActionEnchereLocalHome home = null;
		try {
			ServiceLocator locator = ServiceLocator.getInstance();
			home = (ActionEnchereLocalHome) locator
					.getLocalHome(ActionEnchereLocalHome.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			System.out.println(e.getMessage()); 
		}
		return home;
	}


}
