package axlomoso.ezbay.model.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieLocal;
import axlomoso.ezbay.model.interfaces.CategorieLocalHome;
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
	 * cette methode permet de creer une categorie
	 * @param categorieDTO
	 * @return CategorieDTO
	 * @throws Exception
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
	 * cette methode permet de supprimer une categorie
     * @param categorieDTO
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
	 * cette methode permet de retourner une categorie en passant en parametre
	 * l'identifiant de la categorie
	 * @param categorieId
	 * @return CategorieDTO
	 * @throws Exception
	 */	
	
	public CategorieDTO getCategorie(String categorieId) throws Exception {
		try {
			CategorieLocal categorieLocal = getEntity(categorieId);
			return categorieLocal.getCategorieDTO();
		} catch (Exception e) {
			throw new Exception("Cannot get categorie", e);
		}
	}
		
		
    
   
	/**cette methode retourne une instance de l'interface local du categorie entity bean 
	 * @param id
	 * @return CategorieLocal
	 * @throws Exception
	 */
	public static CategorieLocal getEntity(String id) throws Exception{
        try {
            CategorieLocalHome home = getEntityHome();
            return home.findByPrimaryKey(id);
        } catch (Exception e) {
            throw new Exception("Cannot locate CustomerHome", e);
        }
    }
    
     /** cette methode retourne une instance de l'interface local Home du categorie entity bean  */
	public static CategorieLocalHome getEntityHome(){
    	CategorieLocalHome home = null;
    	try {
	        ServiceLocator locator = ServiceLocator.getInstance();
			home = (CategorieLocalHome) locator.getLocalHome(CategorieLocalHome.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			System.out.println(e.getMessage()); 
		}
        return home;
    }
	
	/**
	 * @ejb.interface-method view-type = "remote" 
	 * cette methode permet de retourner la liste des categories existantes
	 * @return Collection
	 */	 
	
	public Collection getCategories() {
		Collection categories = null;
		ArrayList tRes = new ArrayList();
		try {
			CategorieLocalHome home = getEntityHome();
			categories = home.findAll();//retourne une liste de categories locales
			for (Iterator it = categories.iterator(); it.hasNext(); ) {
				CategorieLocal categorieLocal = (CategorieLocal) it.next();
				tRes.add(categorieLocal.getCategorieDTO());
		    }			
		} catch (FinderException e) {
			System.out.println(e.getMessage()); 
		}
		return tRes;//on retourne une liste de categorieDTO
	}
	
}
