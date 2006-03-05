package axlomoso.ezbay.test;



import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import axlomoso.ezbay.model.ejb.CategorieFacadeBean;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieFacade;
import axlomoso.ezbay.model.interfaces.CategorieFacadeHome;
import axlomoso.ezbay.model.interfaces.CategorieLocal;
import axlomoso.ezbay.model.interfaces.CategorieLocalHome;
import axlomoso.ezbay.utils.ServiceLocator;
import axlomoso.ezbay.utils.ServiceLocatorException;


import junit.framework.TestCase;

public class CategorieFacadeTest extends TestCase {
	/**
	 * The fixture
	 */
	
	CategorieFacade categorieFacade;	
	CategorieDTO categorieDTO;	
	CategorieDTO categorieDTOcree=null;
	

	protected void setUp() throws Exception {
		try{
			//creation de la facade de la categorie
			ServiceLocator locator = ServiceLocator.getInstance();
			CategorieFacadeHome categorieFacadeHome;
			categorieFacadeHome = (CategorieFacadeHome) locator.getRemoteHome(CategorieFacadeHome.JNDI_NAME, CategorieFacadeHome.class);
			this.categorieFacade = categorieFacadeHome.create();	
			
			//creation de la categorieDTO
			categorieDTO =new CategorieDTO();
			categorieDTO.setLibelle("categorie 1");
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
	}
	
	protected void tearDown() throws Exception {
		//suppression de la categorie cree
		CategorieLocalHome categorieLocalHome=CategorieFacadeBean.getEntityHome();	
		if (categorieDTOcree!=null){
		categorieLocalHome.findByPrimaryKey(categorieDTOcree.getId()).remove();
		}
		
	}
	
	
	public void testCreateCategorie(){
		
		try {
			//creation de la categorie
			categorieDTOcree=categorieFacade.createCategorie(categorieDTO);
			//verification que la categorie est cree avec les bons parametres 
			assertNotNull(categorieDTOcree.getId());
			assertEquals(categorieDTOcree.getLibelle(), categorieDTO.getLibelle());
		} catch (RemoteException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}
	
	
	public void testGetCategorie(){		
		try {
			//creation d'une categorie
			categorieDTOcree=categorieFacade.createCategorie(categorieDTO);
			//on test la methode en question
			CategorieDTO catDTO = categorieFacade.getCategorie(categorieDTOcree.getId());
			//vérification qu on aretourné le bonne categorie
			assertEquals(categorieDTOcree.getId(), catDTO.getId());
			assertEquals(categorieDTOcree.getLibelle(), catDTO.getLibelle());	
		} catch (RemoteException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}
	
	public void testRemoveCategorie(){
		try {	
			//creation d'une categorie
			categorieDTOcree=categorieFacade.createCategorie(categorieDTO);
			//suppression de la categorie creee
			categorieFacade.removeCategorie(categorieDTOcree);
			categorieDTOcree=null;
			//vérification qu ona bien supprimé la categorie
			CategorieLocalHome categorieLocalHome=CategorieFacadeBean.getEntityHome();
			Collection categories=categorieLocalHome.findAll();
			for (Iterator it = categories.iterator(); it.hasNext(); ) {
				CategorieDTO catDTO = (CategorieDTO) it.next();
				assertFalse(equalsDTO(catDTO,categorieDTOcree));//echec de l suppression categorie existe toujours
				}
			assertTrue(true);//test reussi la categorie n existe pas
		} catch (RemoteException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
		}
	}
	
	
	public void testGetCategories() throws RemoteException {
		try {
			//creation d'une categorie
			categorieDTOcree=categorieFacade.createCategorie(categorieDTO);
			//on teste la methode en question
			Collection categories = categorieFacade.getCategories();
			assertTrue(categories.size()!=0);
		} catch (RemoteException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	public static boolean equalsDTO(CategorieDTO categorieDTO1, CategorieDTO categorieDTO2){
		boolean tRes = true;		
			tRes = tRes && ( (categorieDTO1.getId()).equals(categorieDTO2.getId()) );		
			tRes = tRes && ( (categorieDTO1.getLibelle()).equals(categorieDTO2.getLibelle()) );
		
		return tRes;
	}
	

}
