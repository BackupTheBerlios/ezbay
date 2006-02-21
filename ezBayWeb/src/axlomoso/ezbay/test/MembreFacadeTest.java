package axlomoso.ezbay.test;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreFacade;
import axlomoso.ezbay.model.interfaces.MembreFacadeHome;
import axlomoso.ezbay.model.interfaces.MembreLocal;
import axlomoso.ezbay.model.interfaces.MembreLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurFacade;
import axlomoso.ezbay.model.interfaces.VendeurFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurLocal;
import axlomoso.ezbay.model.interfaces.VendeurLocalHome;

import junit.framework.TestCase;

public class MembreFacadeTest extends TestCase {
	/**
	 * The fixture
	 */
	Context jndiContext;
	MembreFacade membreFacade;
	MembreDTO membreDTOTemoin = null; //DTO témoin
	MembreDTO membreDTOCreated = null; //DTO créé via la session facade

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
		//création d'un vendeur par le sessionFacade
		membreDTOTemoin.setNom("Homer");
		//membreDTOCreated = membreFacade.createMembre(membreDTOTemoin);
	}

	/**
	 */
	protected void tearDown() throws Exception {
		//récupération de l'vendeur créé via facade
		MembreLocal membreLocal;
		MembreLocalHome membreLocalHome = (MembreLocalHome) jndiContext.lookup(MembreLocalHome.JNDI_NAME);
		MembreLocal membre = membreLocalHome.findByPrimaryKey(membreDTOCreated.getId());
		membre.remove();
		this.membreFacade = null;
	}
	
	public void testSaveMembre()throws RemoteException{
		
	}
	
	public void testGetMembre()throws RemoteException{
		
	}
	
	public void testGetMembreBis()throws RemoteException{
		
	}
	
	public void testMembreExists()throws RemoteException{
		
	}
	
	public void testGetVendeurDTO()throws RemoteException{
		
	}
	
	public void testSaveVendeur()throws RemoteException{
		
	}
}
