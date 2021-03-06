package axlomoso.ezbay.model.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import axlomoso.ezbay.model.interfaces.ActionEnchereLocal;
import axlomoso.ezbay.model.interfaces.ActionTransactionLocal;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleFacadeLocal;
import axlomoso.ezbay.model.interfaces.ArticleFacadeLocalHome;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
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
 * @ejb.bean name="ClientFacade"
 *           display-name="Name for ClientFacade"
 *           description="Description for ClientFacade"
 *           jndi-name="ejb/ClientFacade"
 *           type="Stateless"
 *           view-type="both"
 */
public class ClientFacadeBean implements SessionBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6764821663424470511L;
	/** The session context */
	private SessionContext context;
	ServiceLocator locator;
	ArticleFacadeLocalHome articleFacadeLocalHome;
	ArticleFacadeLocal articleFacade;
	
	public ClientFacadeBean() {
		super();
		try {
			locator = ServiceLocator.getInstance();
			articleFacadeLocalHome = (ArticleFacadeLocalHome) locator.getLocalHome(ArticleFacadeLocalHome.JNDI_NAME);
			articleFacade = (ArticleFacadeLocal) articleFacadeLocalHome.create();
		} catch (ServiceLocatorException e) {
			System.out.println(e.getMessage()); 
		} catch (CreateException e) {
			System.out.println(e.getMessage()); 
		}
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 * cette methode permet de creer un client
	 * @return ClientDTO
	 * @throws Exception
	 */	 
	
	public ClientDTO createClient() throws Exception {
		try {
			ClientLocalHome home = getEntityHome();
			ClientLocal clientLocal = home.create();
			return clientLocal.getClientDTO();
		} catch (Exception e) {
			throw new Exception("Cannot create client", e);
		}
	}	
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * cette methode retourne un membre en passant en parametre l'identifiant du client
	 * @param clientId
	 * @return MembreDTO
	 * @throws Exception
	 */	 
	
	public MembreDTO getMembre(String clientId) throws Exception {
		MembreDTO tRes = null;
		try {
			ClientLocal clientLocal = getEntity(clientId);
			tRes = clientLocal.getMembreLocal().getMembreDTO();
		} catch (Exception e) {
			throw new Exception("Cannot get Membre From client id=" + clientId, e);
		}
		return tRes;
	}	
	
	/**
	 * @ejb.interface-method view-type = "both" 
	 * cette methode permet de retourner la liste des articles en encheres pour une client
	 * @param clientId
	 * @return Collection
	 */	 
	
	public Collection getArticlesEnEncheres(String clientId) {
		Collection tRes = new ArrayList();
		Collection encheres = null;
		try {
			ClientLocal clientLocal = getEntity(clientId);
			encheres = clientLocal.getActionEnchereLocal();//liste des encheres locales pour le client
			for (Iterator it = encheres.iterator(); it.hasNext();) {
				ActionEnchereLocal enchere = (ActionEnchereLocal) it.next();
				ArticleLocal articleLocal = enchere.getArticleLocal();//on recupere l article lie a l'enchere
				ArticleDTO tArticleDTO = articleLocal.getArticleDTO();
				if(!tRes.contains(tArticleDTO)){
					tRes.add(tArticleDTO);//on ajoute l article recupere dans la liste
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()); 
		}
		return tRes;//on retourne une collection d'articles encheris par le client 
	}	
	
	/**
	 * @ejb.interface-method view-type = "both"	 
	 * cette methode permet de retourner la liste des articles achetes pour une client
	 * @param clientId
	 * @return Collection
	 */
	 
	public Collection getArticlesAchetes(String clientId) {
		Collection tRes = new ArrayList();
		Collection transactions = null;
		try {
			ClientLocal clientLocal = getEntity(clientId);
			transactions = clientLocal.getActionTransactionLocal();//liste des transactions locales pour le client
			for (Iterator it = transactions.iterator(); it.hasNext();) {
				ActionTransactionLocal transaction = (ActionTransactionLocal) it.next();
				ArticleLocal articleLocal = transaction.getArticleLocal();//on ajoute l article recupere dans la liste
				tRes.add(articleLocal.getArticleDTO());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()); 
		}
		return tRes;//on retourne une collection d'articles achetes par le client 
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
	
    
     /** cette methode retourne une instance de l'interface local du client entity bean 
	 * @param id
	 * @return ClientLocal
	 * @throws Exception
	 */
	public static ClientLocal getEntity(String id) throws Exception{
        try {
        	ClientLocalHome home = getEntityHome();
            return home.findByPrimaryKey(id);
        } catch (Exception e) {
            throw new Exception("Cannot locate Article", e);
        }
    }
    
	/** cette methode retourne une instance de l'interface local Home du client entity bean  */
    public static ClientLocalHome getEntityHome(){
    	ClientLocalHome home = null;
    	try {
	        ServiceLocator locator = ServiceLocator.getInstance();
			home = (ClientLocalHome) locator.getLocalHome(ClientLocalHome.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			System.out.println(e.getMessage()); 
		}
        return home;
    }	

}
