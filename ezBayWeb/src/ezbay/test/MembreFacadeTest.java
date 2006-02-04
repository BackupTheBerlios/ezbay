package ezbay.test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import ezbay.model.interfaces.MembreDTO;
import ezbay.model.interfaces.MembreFacade;
import ezbay.model.interfaces.MembreFacadeHome;
import ezbay.model.interfaces.MembreLocal;
import ezbay.model.interfaces.MembreLocalHome;
import ezbay.model.interfaces.VendeurDTO;
import ezbay.model.interfaces.VendeurFacade;
import ezbay.model.interfaces.VendeurFacadeHome;
import ezbay.model.interfaces.VendeurLocal;
import ezbay.model.interfaces.VendeurLocalHome;
import junit.framework.TestCase;

public class MembreFacadeTest extends TestCase {
	/**
	 * The fixture
	 */
	Context jndiContext;
	MembreFacade membreFacade;
	MembreDTO membreDTOTemoin = null; //DTO t�moin
	MembreDTO membreDTOCreated = null; //DTO cr�� via la session facade

	/**
	 */
	public MembreFacadeTest() {
		super();
	}
	
	protected void setUp() throws Exception {
		jndiContext = new InitialContext();
		Object ref = jndiContext.lookup(MembreFacadeHome.JNDI_NAME);
		MembreFacadeHome facadeHome = (MembreFacadeHome) PortableRemoteObject.narrow(ref, MembreFacadeHome.class);
		this.membreFacade = facadeHome.create();
		
		membreDTOTemoin = new MembreDTO();	
		//cr�ation d'un vendeur par le sessionFacade
		membreDTOTemoin.setNom("Homer");
		membreDTOCreated = membreFacade.createMembre(membreDTOTemoin);
	}

	/**
	 */
	protected void tearDown() throws Exception {
		//r�cup�ration de l'vendeur cr�� via facade
		MembreLocal membreLocal;
		MembreLocalHome membreLocalHome = (MembreLocalHome) jndiContext.lookup(MembreLocalHome.JNDI_NAME);
		MembreLocal membre = membreLocalHome.findByPrimaryKey(membreDTOCreated.getId());
		membre.remove();
		this.membreFacade = null;
	}
	
	public void testCreateMembre(){
	}
	
	public void testCreateVendeur(){
	}
	
	
}
