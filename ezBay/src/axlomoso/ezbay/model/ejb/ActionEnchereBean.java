package axlomoso.ezbay.model.ejb;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

import javax.ejb.CreateException;

import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.ActionEnchereUtil;
import axlomoso.ezbay.model.interfaces.ClientLocal;
import axlomoso.ezbay.model.interfaces.VendeurLocal;

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
 * @ejb.bean name="ActionEnchere"
 *           display-name="Name for Enchere"
 *           description="Description for Enchere"
 *           jndi-name="ejb/ActionEnchere"
 *           schema="actionenchere"
 *           type="CMP"
 *           cmp-version="2.x"
 *           view-type="local"
 *           primkey-field = "id"
 *           
 * @ejb.persistence 
 * 			table-name = "actionenchere"
 * 
 * @jboss.persistence table-name = "actionenchere" 
 * 			    create-table = "true" 
 *				remove-table = "true"
 *
 * @ejb:util generate="physical"
 * 
 * @ejb.value-object match = "*"
 * 
 * @ejb.finder
* 		description="findByArticleId"
* 		signature="java.util.Collection findByArticleId(java.lang.String articleId)" 
* 		query="
* 		SELECT DISTINCT OBJECT(ae) 
* 		FROM actionenchere AS ae,
* 			article AS a 
* 		WHERE 
* 			a.id = ?1 
* 			AND ae.articleLocal = a
* 		ORDER BY ae.date DESC"
 */
public abstract class ActionEnchereBean implements EntityBean {

	/** The entity context */
	private EntityContext context;

	public ActionEnchereBean() {
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
	 * @ejb.persistence column-name = "date"
	 * @return
	 */
	public abstract Date getDate();

	/**
	 * @ejb.interface-method view-type = "local"
	 * @param date
	 */
	public abstract void setDate(Date date);
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * @ejb.persistence column-name = "montant"
	 * @return
	 */
	public abstract Double getMontant();
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * @param montant
	 */
	public abstract void setMontant(Double montant);
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * @ejb.persistence column-name = "datelimite"
	 * @return
	 */
	public abstract Date getDateLimite();

	/**
	 * @ejb.interface-method view-type = "local"
	 * @param datelimite
	 */
	public abstract void setDateLimite(Date datelimite);
	
	/**
	   * @ejb.interface-method view-type = "local"
	   * @ejb.relation name = "enchere-article" role-name = "une enchere est posee sur un Article"
	   * @jboss.relation related-pk-field = "id" fk-column = "article_id" 
	   *                 fk-constraint = "true"
	   * @return ArticleLocal
	   */
	  public abstract ArticleLocal getArticleLocal();

	  /**
	   * @ejb.interface-method view-type = "local"
	   * @param articleLocal
	   */
	  public abstract void setArticleLocal(ArticleLocal articleLocal);		
	
	  /**
	   * @ejb.interface-method view-type = "local"
	   * @param
	   */
	  public abstract ActionEnchereDTO getActionEnchereDTO();

		/**
	   * @ejb.interface-method view-type = "local"
	   * @ejb.relation name = "client-actionenchere" role-name = "ActionEnchere est effectuée par un Client"
	   * @jboss.relation related-pk-field = "id" fk-column = "client_id" 
	   *                 fk-constraint = "true"
	   * @return
	   */
	  public abstract ClientLocal getClientLocal();

	  /**
	   * @ejb.interface-method view-type = "local"
	   * @param clientLocal
	   */
	  public abstract void setClientLocal(ClientLocal clientLocal);	
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
	public String ejbCreate(ActionEnchereDTO enchereDTO, ArticleLocal articleLocal, ClientLocal clientLocal) throws CreateException {
		String tId = ActionEnchereUtil.generateGUID(this);
		this.setId(tId);
		Calendar calendar = Calendar.getInstance();
		this.setDate(calendar.getTime());
		this.setMontant(enchereDTO.getMontant());
		this.setDateLimite(enchereDTO.getDateLimite());
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
	public void ejbPostCreate(ActionEnchereDTO enchereDTO, ArticleLocal articleLocal, ClientLocal clientLocal) throws CreateException {
		this.setArticleLocal(articleLocal);
		this.setClientLocal(clientLocal);
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
