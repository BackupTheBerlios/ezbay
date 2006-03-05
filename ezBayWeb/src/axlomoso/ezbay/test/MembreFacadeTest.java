package axlomoso.ezbay.test;

import java.rmi.RemoteException;

import axlomoso.ezbay.exceptions.PseudoDejaUtiliseException;
import axlomoso.ezbay.model.ejb.CategorieFacadeBean;
import axlomoso.ezbay.model.ejb.MembreFacadeBean;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieFacadeHome;
import axlomoso.ezbay.model.interfaces.CategorieLocalHome;
import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreFacade;
import axlomoso.ezbay.model.interfaces.MembreFacadeHome;
import axlomoso.ezbay.model.interfaces.MembreLocal;
import axlomoso.ezbay.model.interfaces.MembreLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.utils.ServiceLocator;



import junit.framework.TestCase;

public class MembreFacadeTest extends TestCase {
	/**
	 * The fixture
	 */
	
	MembreFacade membreFacade;
	MembreDTO membreDTO;
	VendeurDTO vendeurDTO;
	ClientDTO clientDTO ;
	MembreDTO membreDTOcree=null;
	
	/**
	 */
	public MembreFacadeTest() {
		super();
	}
	
	protected void setUp() throws Exception {
		
		//creation de la facade du membre
		ServiceLocator locator = ServiceLocator.getInstance();
		MembreFacadeHome membreFacadeHome;
		membreFacadeHome = (MembreFacadeHome) locator.getRemoteHome(MembreFacadeHome.JNDI_NAME, MembreFacadeHome.class);
		this.membreFacade = membreFacadeHome.create();	
		
		//creation du membreDTO
		membreDTO =new MembreDTO();
		membreDTO.setNom("nom 1");
		membreDTO.setPrenom("prenom 1");
		membreDTO.setPseudo("pseudo 1");
		membreDTO.setPassword("password");
		membreDTO.setEmail("test@test.fr");		
		membreDTO.setAdresse("adresse 1");
		membreDTO.setCodePostal("code 1");
		membreDTO.setVille("ville 1");
		membreDTO.setPays("pays 1");
		membreDTO.setTelephoneFixe("00000000");
		
		//creation du vendeurDTO
		vendeurDTO=new VendeurDTO();
		vendeurDTO.setNomProprioCB("vendeur1");
		vendeurDTO.setCodeSecuCB("111");
		vendeurDTO.setNumCB("1111111111111111");		
		
		
	}

	/**
	 */
	protected void tearDown() throws Exception {
		//suppression de la categorie cree
		MembreLocalHome membreLocalHome=MembreFacadeBean.getEntityHome();	
		if (membreDTOcree!=null){
			membreLocalHome.findByPrimaryKey(membreDTOcree.getId()).remove();
		}
		
	}
		
	public void testSaveMembre(){
		try {
			//creation du membre			
			this.membreDTOcree = membreFacade.saveMembre(this.membreDTO);
			boolean testerId=false;			
			assertTrue(equalsDTO(membreDTO, membreDTOcree,testerId));//le membre est bien ajouté
			
			//tester si on ajoute un membre avec un pseudo qui existe deja
			MembreDTO membreDTOcree2 = membreFacade.saveMembre(this.membreDTO);
			assertTrue(false);//echec du test le membre est ajouté avec un pseudo qui existe déja
		} catch (RemoteException e) {			
			e.printStackTrace();
		} catch (PseudoDejaUtiliseException e) {
			assertTrue(true);//le test a reussi une exception est levé le membre n est pas sauvegardé 
		} catch (Exception e) {
			
		}
	}
	
	public void testGetMembre(){
		try {
			//Creation d un membre
			 membreDTOcree = membreFacade.saveMembre(this.membreDTO);
			MembreDTO membreDTOrecup = membreFacade.getMembre(membreDTOcree.getId());
			boolean testerId=true;
			assertTrue(equalsDTO(membreDTOrecup, membreDTOcree,testerId));
		} catch (RemoteException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
		
	public void testGetMembreByPseudo(){
		try {
			//Creation d un membre
			 membreDTOcree = membreFacade.saveMembre(this.membreDTO);
			MembreDTO membreDTOrecup = membreFacade.getMembre(membreDTOcree.getPseudo(),membreDTOcree.getPassword());
			boolean testerId=true;
			assertTrue(equalsDTO(membreDTOrecup, membreDTOcree,testerId));
		} catch (RemoteException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	public void testSaveVendeur(){
		try {
			//Creation d un membre
			 membreDTOcree = membreFacade.saveMembre(this.membreDTO);			 
			 //creation du vendeur
			 membreFacade.saveVendeur(membreDTOcree,this.vendeurDTO);
			 //verification si le vendeur est bien ajouté
			 MembreLocalHome membreLocalHome=MembreFacadeBean.getEntityHome();
			 MembreLocal membreLocal =membreLocalHome.findByPrimaryKey(membreDTOcree.getId());
			 VendeurDTO vendeurDTOrecup=membreLocal.getVendeurLocal().getVendeurDTO();
			 boolean testerId=false;
			 assertTrue(VendeurFacadeTest.equalsDTO(vendeurDTOrecup,this.vendeurDTO,testerId));
			 
		} catch (RemoteException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}	
		
	public static boolean equalsDTO(MembreDTO membreDTO1, MembreDTO membreDTO2,boolean testerId){
		boolean tRes = true;
		if(testerId){
		tRes = tRes && ( (membreDTO1.getId()).equals(membreDTO2.getId()) );
		}
		tRes = tRes && ( (membreDTO1.getAdresse()).equals(membreDTO2.getAdresse()) );
		tRes = tRes && ( (membreDTO1.getCodePostal()).equals(membreDTO2.getCodePostal()) );
		tRes = tRes && ( (membreDTO1.getEmail()).equals(membreDTO2.getEmail()) );
		tRes = tRes && ( (membreDTO1.getNom()).equals(membreDTO2.getNom()) );
		tRes = tRes && ( (membreDTO1.getPassword()).equals(membreDTO2.getPassword()) );
		tRes = tRes && ( (membreDTO1.getPays()).equals(membreDTO2.getPays()) );
		tRes = tRes && ( (membreDTO1.getPrenom()).equals(membreDTO2.getPrenom()) );
		tRes = tRes && ( (membreDTO1.getPseudo()).equals(membreDTO2.getPseudo()) );
		tRes = tRes && ( (membreDTO1.getTelephoneFixe()).equals(membreDTO2.getTelephoneFixe()) );		
		tRes = tRes && ( (membreDTO1.getVille()).equals(membreDTO2.getVille()) );	
		
		return tRes;
	}
	
	
}
