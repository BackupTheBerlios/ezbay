package axlomoso.ezbay.model.ejb;

import java.rmi.RemoteException;
import java.util.Date;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

import javax.ejb.CreateException;

import axlomoso.ezbay.model.interfaces.ClientLocal;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreLocal;
import axlomoso.ezbay.model.interfaces.MembreLocalHome;
import axlomoso.ezbay.model.interfaces.MembreUtil;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurLocal;
import axlomoso.ezbay.model.interfaces.VendeurUtil;
import axlomoso.ezbay.utils.ServiceLocator;
import axlomoso.ezbay.utils.ServiceLocatorException;


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
 * @ejb.bean name="Membre"
 *           display-name="Name for Membre"
 *           description="Description for Membre"
 *           jndi-name="ejb/Membre"
 *           schema="membre"
 *           type="CMP"
 *           cmp-version="2.x"
 *           view-type="local"
 *           primkey-field = "id"
 *           
 *         
 * @ejb.persistence 
 * 			table-name = "membre"
 * @jboss.persistence table-name = "membre" 
 * 			    create-table = "true" 
 *				remove-table = "true"
 * @ejb:util generate="physical"
 * @ejb.value-object match = "*"
 * @ejb.finder
* 		description="findByPseudo"
* 		signature="java.util.Collection findByPseudo(java.lang.String pseudo)" 
* 		query="SELECT DISTINCT OBJECT(p) FROM membre p WHERE p.pseudo = ?1"      
 */
public abstract class MembreBean implements EntityBean {

	/** The entity context */
	private EntityContext context;

	public MembreBean() {
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
	 * @ejb.interface-method view-type = "both"
	 * @ejb.persistence column-name = "prenom"
	 * @return
	 */
	public abstract String getPrenom();

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param prenom
	 */
	public abstract void setPrenom(String prenom);
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @ejb.persistence column-name = "pseudo"
	 * @return
	 */
	public abstract String getPseudo();

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param pseudo
	 */
	public abstract void setPseudo(String pseudo);
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @ejb.persistence column-name = "password"
	 * @return
	 */
	public abstract String getPassword();

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param password
	 */
	public abstract void setPassword(String password);
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @ejb.persistence column-name = "email"
	 * @return
	 */
	public abstract String getEmail();

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param email
	 */
	public abstract void setEmail(String email);
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @ejb.persistence column-name = "adresse"
	 * @return
	 */
	public abstract String getAdresse();

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param adresse
	 */
	public abstract void setAdresse(String adresse);
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @ejb.persistence column-name = "codePostal"
	 * @return
	 */
	public abstract String getCodePostal();

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param codePostal
	 */
	public abstract void setCodePostal(String codePostal);
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @ejb.persistence column-name = "ville"
	 * @return
	 */
	public abstract String getVille();

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param ville
	 */
	public abstract void setVille(String ville);
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @ejb.persistence column-name = "pays"
	 * @return
	 */
	public abstract String getPays();

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param pays
	 */
	public abstract void setPays(String pays);
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @ejb.persistence column-name = "telephoneFixe"
	 * @return
	 */
	public abstract String getTelephoneFixe();

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param telephoneFixe
	 */
	public abstract void setTelephoneFixe(String telephoneFixe);
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @ejb.persistence column-name = "telephonePortable"
	 * @return
	 */
	public abstract String getTelephonePortable();

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param telephonePortable
	 */
	public abstract void setTelephonePortable(String telephonePortable);
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @ejb.persistence column-name = "dateNaissance"
	 * @return
	 */
	public abstract Date getDateNaissance();

	/**
	 * @ejb.interface-method view-type = "both"
	 * @param dateNaissance
	 */
	public abstract void setDateNaissance(Date dateNaissance);
	
	
	/**
	   * @ejb.interface-method view-type = "local"
	   * @ejb.relation name = "membre-vendeur" role-name = "A un Membre est associe un Vendeur"
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
	   * @ejb.relation name = "membre-client" role-name = "A un Membre est associe un Client"
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
		 * @ejb.interface-method view-type = "local"
		 * @param
		 */
	    public abstract MembreDTO getMembreDTO();
	  
		/**
		 * @ejb.interface-method view-type = "local"
		 * @param
		 */
	    public void updateMembre(MembreDTO membreDTO) throws Exception {
	    	this.setNom(membreDTO.getNom());
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
	public String ejbCreate(MembreDTO membreDTO) throws CreateException {
		String tId = MembreUtil.generateGUID(this);
		this.setId(tId);
		this.setNom(membreDTO.getNom());
		this.setPrenom(membreDTO.getPrenom());
		this.setPseudo(membreDTO.getPseudo());
		this.setPassword(membreDTO.getPassword());
		this.setEmail(membreDTO.getEmail());
		this.setAdresse(membreDTO.getAdresse());
		this.setCodePostal(membreDTO.getCodePostal());
		this.setVille(membreDTO.getVille());
		this.setPays(membreDTO.getPays());
		this.setTelephoneFixe(membreDTO.getTelephoneFixe());
		this.setTelephonePortable(membreDTO.getTelephonePortable());
		this.setDateNaissance(membreDTO.getDateNaissance());
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
	public void ejbPostCreate() throws CreateException {
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
