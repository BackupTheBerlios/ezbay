package ezbay.model.ejb;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ezbay.model.interfaces.ArticleLocal;
import ezbay.model.interfaces.ArticleLocalHome;
import ezbay.model.interfaces.VendeurDTO;
import ezbay.model.interfaces.VendeurLocal;
import ezbay.model.interfaces.VendeurLocalHome;
import ezbay.model.interfaces.VendeurUtil;

/**
 * XDoclet-based CMP 2.x entity bean.  This class must be declared
 * public abstract because the concrete class will
 * be implemented by the CMP providers tooling.
 * 
 * To generate EJB related classes using XDoclet:
 *
 *		- Add Standard EJB module to XDoclet project properties
 *		- Customize XDoclet configuration
 *		- Run XDoclet
 * 
 * Below are the xdoclet-related tags needed for this EJB.
 *
 * @ejb.bean name="Vendeur"
 *           display-name="Name for Vendeur"
 *           description="Description for Vendeur"
 *           jndi-name="ejb/Vendeur"
 *           schema="vendeur"
 *           type="CMP"
 *           cmp-version="2.x"
 *           view-type="local"
 *           primkey-field = "id"
 * @ejb.persistence 
 * 			table-name = "vendeur"
 * @jboss.persistence table-name = "vendeur" 
 * 			    create-table = "true" 
 *				remove-table = "true"
 * @ejb:util generate="physical"
 * @ejb.value-object match = "*"
 *       
 * @ejb.finder
* 		signature="java.util.Collection findAll()" 
* 		query="SELECT Object(a) FROM vendeur AS a"
* @ejb.finder
* 		description="findByNom"
* 		signature="java.util.Collection findByNom(java.lang.String nom)" 
* 		query="SELECT DISTINCT OBJECT(p) FROM vendeur p WHERE p.nom = ?1"
* 
* 
**/
public abstract class VendeurBean implements EntityBean {

	/** The entity context */
	private EntityContext context;

	public VendeurBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 * @ejb.persistence column-name = "id"
	 * @return
	 */
	public abstract String getId();

	/**
	 * @ejb.interface-method view-type = "local"
	 * @param id
	 */
	public abstract void setId(String id);

	/**
	 * @ejb.interface-method view-type = "local"
	 * @ejb.persistence column-name = "nom"
	 * @return
	 */
	public abstract String getNom();

	/**
	 * @ejb.interface-method view-type = "local"
	 * @param nom
	 */
	public abstract void setNom(String nom);

	/**
	 * @ejb.interface-method
	 *   view-type="local"
	 * @ejb.relation
	 *   name = "article-vendeur"
	 *   role-name = "vendeur vend article"
	 *   @return a Collection of Article
	 */ 
	public abstract Collection getArticle();

	/**
	 * @ejb.interface-method view-type = "local"
	 * @param Collection of Article
	 */
	public abstract void setArticle(Collection article);	
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * @param
	 */
    public abstract VendeurDTO getVendeurDTO();
    
	/**
	 * @ejb.interface-method view-type = "local"
	 * @param
	 */
    public void updateVendeur(VendeurDTO vendeurDTO) throws Exception {
    	this.setNom(vendeurDTO.getNom());
    }
    
    
 	/**
	 * There are zero or more ejbCreate<METHOD>(...) methods, whose signatures match
	 * the signatures of the create<METHOD>(...) methods of the entity bean?s home interface.
	 * The container invokes an ejbCreate<METHOD>(...) method on an entity bean instance
	 * when a client invokes a matching create<METHOD>(...) method on the entity bean?s
	 * home interface.<br>
	 * 
	 * The entity bean provider?s responsibility is to initialize the instance in the ejbCreate<
	 * METHOD>(...) methods from the input arguments, using the get and set accessor
	 * methods, such that when the ejbCreate<METHOD>(...) method returns, the persistent
	 * representation of the instance can be created. <br>
	 * 
	 * The entity bean provider must not attempt to modify the values of cmr-fields in an ejbCreate<
	 * METHOD(...) method; this should be done in the ejbPostCreate<METHOD(...) method instead.<br>
	 * 
	 * The entity object created by the ejbCreate<METHOD> method must have a unique primary
	 * key. This means that the primary key must be different from the primary keys of all the existing
	 * entity objects within the same home. However, it is legal to reuse the primary key of a previously
	 * removed entity object. The implementation of the bean provider?s ejbCreate<
	 * METHOD>(...) methods should be coded to return a null.<br>
	 * 
	 * An ejbCreate<METHOD>(...) method executes in the transaction context determined by
	 * the transaction attribute of the matching create<METHOD>(...) method. 
	 * The database insert operations are performed by the container within the same
	 * transaction context after the Bean Provider?s ejbCreate<METHOD>(...) method completes.
	 *
	 * @throws CreateException Thrown if method fails due to system-level error.
	 * 
	 * @throws CreateException
	 *
	 * @ejb.create-method
	 */
	public String ejbCreate(VendeurDTO vendeurTO) throws CreateException {
		String tId = VendeurUtil.generateGUID(this);
		this.setId(tId);
		this.setNom(vendeurTO.getNom());
		return tId;
	}

	/**
	 * For each ejbCreate<METHOD>(...) method, there is a matching ejbPostCreate<
	 * METHOD>(...) method that has the same input parameters but whose return type is
	 * void. The container invokes the matching ejbPostCreate<METHOD>(...) method on
	 * an instance after it invokes the ejbCreate<METHOD>(...) method with the same arguments.
	 * The instance can discover the primary key by calling getPrimaryKey() on its
	 * entity context object. <br>
	 * 
	 * The entity object identity is available during the ejbPostCreate<METHOD>(...)
	 * method. The instance may, for example, obtain the component interface of the associated entity
	 * object and pass it to another enterprise bean as a method argument.<br>
	 * 
	 * The entity Bean Provider may use the ejbPostCreate<METHOD>(...) to set the values
	 * of cmr-fields to complete the initialization of the entity bean instance.
	 * An ejbPostCreate<METHOD>(...) method executes in the same transaction context as
	 * the previous ejbCreate<METHOD>(...) method.
	 * 
	 * @throws CreateException Thrown if method fails due to system-level error.
	 */
    public void ejbPostCreate(VendeurDTO vendeurDTO) throws javax.ejb.CreateException {
    }

	/**
	 * Set the associated entity context. The container calls this method 
	 * after the instance creation. The entity bean must not attempt to 
	 * access its persistent state and relationships using the accessor 
	 * methods during this method. <br>
	 *
	 * The enterprise bean instance should store the reference to the context 
	 * object in an instance variable. <br>
	 * 
	 * This method is called with no transaction context. 
	 * 
	 * @throws EJBException Thrown if method fails due to system-level error.
	 */
	public void setEntityContext(EntityContext newContext) throws EJBException {
		context = newContext;
	}

	/**
	 * Unset the associated entity context. A container invokes this method 
	 * before terminating the life of the instance. The entity bean must not 
	 * attempt to access its persistent state and relationships using the 
	 * accessor methods during this method. <br>
	 * 
	 * This method is called with no transaction context. 
	 * 
	 * @throws EJBException Thrown if method fails due to system-level error.
	 */
	public void unsetEntityContext() throws EJBException {
		context = null;
	}

	public void ejbRemove()
		throws RemoveException,
		EJBException,
		RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbLoad() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbStore() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}
	
}
