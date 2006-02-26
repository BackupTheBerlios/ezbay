package axlomoso.ezbay.test;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import axlomoso.ezbay.delegate.MembreFacadeDelegate;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.CategorieLocal;
import axlomoso.ezbay.model.interfaces.CategorieLocalHome;
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
	MembreFacadeDelegate membreFacadeDelegate=null;
	
	/**
	 */
	public MembreFacadeTest() {
		super();
	}
	
	protected void setUp() throws Exception {
		membreFacadeDelegate=MembreFacadeDelegate.getInstance();
		MembreFacade membreFacade = membreFacadeDelegate.getMembreFacade();
		membreDTOTemoin = new MembreDTO();	
		membreDTOCreated = membreFacade.saveMembre(membreDTOTemoin);
	}

	/**
	 */
	protected void tearDown() throws Exception {
		MembreLocal membreLocal;
		MembreLocalHome membreLocalHome;
		membreLocalHome = (MembreLocalHome) jndiContext.lookup(MembreLocalHome.JNDI_NAME);
		membreLocal = membreLocalHome.findByPrimaryKey(membreDTOCreated.getId());
		membreLocal.remove();
		this.membreFacade = null;
		
	}
	
	public void testSaveMembre()throws RemoteException{
		try {
			//récupération de l'article créé via facade
			MembreDTO membreDTO = membreFacadeDelegate.getMembreById(membreDTOCreated.getId());
			assertTrue(this.equalsDTO(membreDTO, membreDTOCreated));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testGetMembre()throws RemoteException{
		try {
			//récupération du vendeur créé via facade
			MembreDTO membreDTO = membreFacade.getMembre(membreDTOCreated.getId());
			assertTrue(this.equalsDTO(membreDTO, membreDTOCreated));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testGetMembreBis()throws RemoteException{
		
	}
	
	public void testMembreExists()throws RemoteException{
		
	}
	
	public void testGetVendeurDTO()throws RemoteException{
		
	}
	
	public void testSaveVendeur()throws RemoteException{
		
	}
	
	public boolean equalsDTO(MembreDTO membreDTO1, MembreDTO membreDTO2){
		boolean tRes = true;
		tRes = tRes && ( (membreDTO1.getId()).equals(membreDTO2.getId()) );
		tRes = tRes && ( (membreDTO1.getAdresse()).equals(membreDTO2.getAdresse()) );
		tRes = tRes && ( (membreDTO1.getCodePostal()).equals(membreDTO2.getCodePostal()) );
		tRes = tRes && ( (membreDTO1.getEmail()).equals(membreDTO2.getEmail()) );
		tRes = tRes && ( (membreDTO1.getNom()).equals(membreDTO2.getNom()) );
		tRes = tRes && ( (membreDTO1.getPassword()).equals(membreDTO2.getPassword()) );
		tRes = tRes && ( (membreDTO1.getPays()).equals(membreDTO2.getPays()) );
		tRes = tRes && ( (membreDTO1.getPrenom()).equals(membreDTO2.getPrenom()) );
		tRes = tRes && ( (membreDTO1.getPseudo()).equals(membreDTO2.getPseudo()) );
		tRes = tRes && ( (membreDTO1.getTelephoneFixe()).equals(membreDTO2.getTelephonePortable()) );
		tRes = tRes && ( (membreDTO1.getTelephonePortable()).equals(membreDTO2.getTelephonePortable()) );
		tRes = tRes && ( (membreDTO1.getVille()).equals(membreDTO2.getVille()) );
		tRes = tRes && ( (membreDTO1.getDateNaissance()).equals(membreDTO2.getDateNaissance()) );
		
		return tRes;
	}
}
