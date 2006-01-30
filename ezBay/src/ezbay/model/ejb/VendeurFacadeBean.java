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

import ezbay.model.interfaces.ArticleLocal;
import ezbay.model.interfaces.VendeurDTO;
import ezbay.model.interfaces.VendeurLocal;
import ezbay.model.interfaces.VendeurLocalHome;

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
 * @ejb.bean name="VendeurFacade"
 *           display-name="Name for VendeurFacade"
 *           description="Description for VendeurFacade"
 *           jndi-name="ejb/VendeurFacade"
 *           type="Stateless"
 *           view-type="remote"
 */
public class VendeurFacadeBean implements SessionBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8719723927459392718L;
	/** The session context */
	private SessionContext context;

	public VendeurFacadeBean() {
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
	 * @param vendeurDTO
	 */
	public VendeurDTO createVendeur(VendeurDTO vendeurDTO) throws Exception {
		try {
			InitialContext initialContext = new InitialContext();
			VendeurLocalHome home = (VendeurLocalHome) initialContext.lookup(VendeurLocalHome.JNDI_NAME);
			VendeurLocal vendeurLocal = home.create(vendeurDTO);
			return vendeurLocal.getVendeurDTO();
		} catch (Exception e) {
			throw new Exception("Cannot create vendeur", e);
		}
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param vendeurDTO
	 */
	public void updateVendeur(VendeurDTO vendeurDTO) throws Exception {
		try {
			InitialContext initialContext = new InitialContext();
			VendeurLocalHome home = (VendeurLocalHome) initialContext
					.lookup(VendeurLocalHome.JNDI_NAME);
			VendeurLocal vendeurLocal = home.findByPrimaryKey(vendeurDTO.getId());
			vendeurLocal.updateVendeur(vendeurDTO);
		} catch (Exception e) {
			throw new Exception("Cannot create vendeur", e);
		}
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param 
	 */
    public void removeVendeur(VendeurDTO vendeurDTO) throws Exception {
		try {
			InitialContext initialContext = new InitialContext();
			VendeurLocalHome home = (VendeurLocalHome) initialContext.lookup(VendeurLocalHome.JNDI_NAME);
			VendeurLocal vendeurLocal = home.findByPrimaryKey(vendeurDTO.getId());
			vendeurLocal.remove();
		}catch (Exception e) {
			throw new Exception("Cannot remove vendeur", e);
		}
    }	
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param vendeurId
	 */
	public VendeurDTO getVendeur(String vendeurId) throws Exception {
		try {
			InitialContext initialContext = new InitialContext();
			VendeurLocalHome home = (VendeurLocalHome) initialContext.lookup(VendeurLocalHome.JNDI_NAME);
			VendeurLocal vendeurLocal = home.findByPrimaryKey(vendeurId);
			return vendeurLocal.getVendeurDTO();
		} catch (Exception e) {
			throw new Exception("Cannot get vendeur", e);
		}
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param
	 */
	public Collection getVendeurs() {
		Collection vendeurs = null;
		ArrayList tRes = new ArrayList();
		try {
			InitialContext initialContext;
			initialContext = new InitialContext();
			VendeurLocalHome home = (VendeurLocalHome) initialContext.lookup(VendeurLocalHome.JNDI_NAME);
			vendeurs = home.findAll();
			for (Iterator it = vendeurs.iterator(); it.hasNext(); ) {
				VendeurLocal vendeurLocal = (VendeurLocal) it.next();
				tRes.add(vendeurLocal.getVendeurDTO());
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
	 * @param nom
	 */
	public Collection getVendeursByNom(String nom) {
		Collection vendeurs = null;
		ArrayList tRes = new ArrayList();
		try {
			InitialContext initialContext;
			initialContext = new InitialContext();
			VendeurLocalHome home = (VendeurLocalHome) initialContext.lookup(VendeurLocalHome.JNDI_NAME);
			vendeurs = home.findByNom(nom);
			for (Iterator it = vendeurs.iterator(); it.hasNext(); ) {
				VendeurLocal vendeurLocal = (VendeurLocal) it.next();
				tRes.add(vendeurLocal.getVendeurDTO());
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
	public Collection getArticles(VendeurDTO vendeurDTO) {
		Collection tRes = new ArrayList();
		try {
			InitialContext initialContext;
			initialContext = new InitialContext();
			VendeurLocalHome home = (VendeurLocalHome) initialContext.lookup(VendeurLocalHome.JNDI_NAME);
			VendeurLocal vendeur = home.findByPrimaryKey(vendeurDTO.getId());
			Collection articles = vendeur.getArticle();
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
