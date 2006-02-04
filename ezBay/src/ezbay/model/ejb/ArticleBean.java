package ezbay.model.ejb;

import java.rmi.RemoteException;
import java.util.Date;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

import javax.ejb.CreateException;

import ezbay.model.interfaces.ArticleDTO;
import ezbay.model.interfaces.ArticleUtil;
import ezbay.model.interfaces.CategorieLocal;
import ezbay.model.interfaces.VendeurLocal;

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
 * @ejb.bean name="Article"
 *           display-name="Name for Article"
 *           description="Description for Article"
 *           jndi-name="ejb/Article"
 *           schema="article"
 *           type="CMP"
 *           cmp-version="2.x"
 *           view-type="local"
 *           primkey-field = "id"
 * @ejb.persistence 
 * 			table-name = "article"
 * @jboss.persistence table-name = "article" 
 * 			    create-table = "true" 
 *				remove-table = "true"
 * @ejb:util generate="physical"
 * @ejb.value-object match = "*"
 *       
 * @ejb.finder
* 		signature="java.util.Collection findAll()" 
* 		query="SELECT Object(a) FROM article AS a"
* @ejb.finder
* 		description="findByLibelle"
* 		signature="java.util.Collection findByLibelle(java.lang.String libelle)" 
* 		query="SELECT DISTINCT OBJECT(p) FROM article p WHERE p.libelle = ?1"
* @ejb.finder
* 		description="findByMarque"
* 		signature="java.util.Collection findByMarque(java.lang.String marque)" 
* 		query="SELECT DISTINCT OBJECT(p) FROM article p WHERE p.marque = ?1"
* @ejb.finder
* 		description="findByModele"
* 		signature="java.util.Collection findByModele(java.lang.String modele)" 
* 		query="SELECT DISTINCT OBJECT(p) FROM article p WHERE p.modele = ?1"
* @ejb.finder
* 		description="findByPrixVente"
* 		signature="java.util.Collection findByPrixVente(java.lang.Double prixVente)" 
* 		query="SELECT DISTINCT OBJECT(p) FROM article p WHERE p.prixVente <= ?1"
* @ejb.finder
* 		description="findByAnneeFabrication"
* 		signature="java.util.Collection findByAnneeFabrication(java.lang.Integer anneeFabrication)" 
* 		query="SELECT DISTINCT OBJECT(p) FROM article p WHERE p.anneeFabrication >= ?1"
* @ejb.finder
* 		description="findByDateLimite"
* 		signature="java.util.Collection findByDateLimite(java.util.Date dateLimite)" 
* 		query="SELECT DISTINCT OBJECT(p) FROM article p WHERE p.dateLimite >= ?1"
* @ejb.finder
* 		description="findByDescription"
* 		signature="java.util.Collection findByDescription(java.lang.String description)" 
* 		query="SELECT DISTINCT OBJECT(p) FROM article p WHERE p.description LIKE '%?1%'"
*/
public abstract class ArticleBean implements EntityBean {

	/** The entity context */
	private EntityContext context;

	public ArticleBean() {
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
	 * @ejb.persistence column-name = "libelle"
	 * @return
	 */
	public abstract String getLibelle();

	/**
	 * @ejb.interface-method view-type = "local"
	 * @param libelle
	 */
	public abstract void setLibelle(String libelle);
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * @ejb.persistence column-name = "marque"
	 * @return
	 */
	public abstract String getMarque();

	/**
	 * @ejb.interface-method view-type = "local"
	 * @param marque
	 */
	public abstract void setMarque(String marque);
	
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * @ejb.persistence column-name = "modele"
	 * @return
	 */
	public abstract String getModele();

	/**
	 * @ejb.interface-method view-type = "local"
	 * @param modele
	 */
	public abstract void setModele(String modele);
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * @ejb.persistence column-name = "prixvente"
	 * @return
	 */
	public abstract Double getPrixVente();

	/**
	 * @ejb.interface-method view-type = "local"
	 * @param prixvente 
	 */
	public abstract void setPrixVente (Double prixVente);
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * @ejb.persistence column-name = "anneefabrication"
	 * @return
	 */
	public abstract Integer getAnneeFabrication();

	/**
	 * @ejb.interface-method view-type = "local"
	 * @param anneefabrication
	 */
	public abstract void setAnneeFabrication(Integer anneeFabrication);
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * @ejb.persistence column-name = "description"
	 * @return
	 */
	public abstract String getDescription();

	/**
	 * @ejb.interface-method view-type = "local"
	 * @param description
	 */
	public abstract void setDescription(String description);
	
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
	public abstract void setDateLimite(Date dateLimite);
    
	/**
	   * @ejb.interface-method view-type = "local"
	   * @ejb.relation name = "article-vendeur" role-name = "article est vendu par vendeur"
	   * @jboss.relation related-pk-field = "id" fk-column = "vendeur_id" 
	   *                 fk-constraint = "true"
	   * @return
	   */
	  public abstract VendeurLocal getVendeurLocal();

	  /**
	   * @ejb.interface-method view-type = "local"
	   * @param vendeurLocal
	   */
	  public abstract void setVendeurLocal(VendeurLocal vendeurLocal);	
	
	
		/**
	   * @ejb.interface-method view-type = "local"
	   * @ejb.relation name = "categorie-article" role-name = "Article fait partie d'une Categorie"
	   * @jboss.relation related-pk-field = "id" fk-column = "categorie_id" 
	   *                 fk-constraint = "true"
	   * @return
	   */
	  public abstract CategorieLocal getCategorieLocal();

	  /**
	   * @ejb.interface-method view-type = "local"
	   * @param categorieLocal
	   */
	  public abstract void setCategorieLocal(CategorieLocal categorieLocal);	
	
	  
	  
	/**
	 * @ejb.interface-method view-type = "local"
	 * @param
	 */
    public abstract ArticleDTO getArticleDTO();

	/**
	 * @ejb.interface-method view-type = "local"
	 * @param
	 */
    public void updateArticle(ArticleDTO articleDTO) throws Exception {
    	this.setAnneeFabrication(articleDTO.getAnneeFabrication());
    	this.setDateLimite(articleDTO.getDateLimite());
    	this.setDescription(articleDTO.getDescription());
    	this.setLibelle(articleDTO.getLibelle());
    	this.setMarque(articleDTO.getMarque());
    	this.setModele(articleDTO.getModele());
    	this.setPrixVente(articleDTO.getPrixVente());
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
	public String ejbCreate(ArticleDTO articleTO, VendeurLocal vendeur) throws CreateException {
		String tId = ArticleUtil.generateGUID(this);
		this.setId(tId);
		this.setLibelle(articleTO.getLibelle());
		this.setMarque(articleTO.getMarque());
		this.setModele(articleTO.getModele());
		this.setPrixVente(articleTO.getPrixVente());
		this.setAnneeFabrication(articleTO.getAnneeFabrication());
		this.setDateLimite(articleTO.getDateLimite());
		this.setDescription(articleTO.getDescription());
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
    public void ejbPostCreate(ArticleDTO articleDTO, VendeurLocal vendeur) throws javax.ejb.CreateException {
		this.setVendeurLocal(vendeur);
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
