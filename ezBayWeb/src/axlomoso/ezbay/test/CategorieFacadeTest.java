package axlomoso.ezbay.test;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieFacade;
import axlomoso.ezbay.model.interfaces.CategorieFacadeHome;
import axlomoso.ezbay.model.interfaces.CategorieLocal;
import axlomoso.ezbay.model.interfaces.CategorieLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurLocal;
import axlomoso.ezbay.model.interfaces.VendeurLocalHome;

import junit.framework.TestCase;

public class CategorieFacadeTest extends TestCase {
	/**
	 * The fixture
	 */
	Context jndiContext;
	CategorieFacade categorieFacade;
	CategorieDTO categorieDTOTemoin = null; //DTO témoin
	CategorieDTO categorieDTOCreated = null; //DTO créé via la session facade

	protected void setUp() throws Exception {
		jndiContext = new InitialContext();
		Object ref = jndiContext.lookup(CategorieFacadeHome.JNDI_NAME);
		CategorieFacadeHome facadeHome = (CategorieFacadeHome) PortableRemoteObject.narrow(ref, CategorieFacadeHome.class);
		this.categorieFacade = facadeHome.create();
		
		categorieDTOTemoin = new CategorieDTO();
		categorieDTOTemoin.setLibelle("Categorie 1");
		//création d'une catégorie par le sessionFacade
		categorieDTOCreated = categorieFacade.createCategorie(categorieDTOTemoin);
	}
	
	protected void tearDown() throws Exception {
		CategorieLocal categorieLocal;
		CategorieLocalHome categorieLocalHome;
		categorieLocalHome = (CategorieLocalHome) jndiContext.lookup(CategorieLocalHome.JNDI_NAME);
		categorieLocal = categorieLocalHome.findByPrimaryKey(categorieDTOCreated.getId());
		categorieLocal.remove();
		this.categorieFacade = null;
	}
	
	public void testCreateCategorie(){
		assertNotNull(categorieDTOCreated.getId());
		assertEquals(categorieDTOTemoin.getLibelle(), categorieDTOCreated.getLibelle());
	}
	
	public void testGetCategorie() throws RemoteException {
		try {
			CategorieDTO categorieDTO = categorieFacade.getCategorie(categorieDTOCreated.getId());
			assertEquals(categorieDTOCreated.getId(), categorieDTO.getId());
			assertEquals(categorieDTOTemoin.getLibelle(), categorieDTO.getLibelle());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testRemoveCategorie(){
		try {
			CategorieDTO catDTO = categorieFacade.createCategorie(categorieDTOTemoin);
			categorieFacade.removeCategorie(catDTO);
			CategorieLocalHome categorieLocalHome;
			categorieLocalHome = (CategorieLocalHome) jndiContext.lookup(CategorieLocalHome.JNDI_NAME);
			Collection categories = categorieLocalHome.findAll();
			boolean tPresent = false;
			for (Iterator it = categories.iterator(); it.hasNext(); ) {
				CategorieLocal categorieLocal = (CategorieLocal) it.next();
				tPresent = tPresent && (categorieLocal.getId().equals(catDTO.getId()));
		    }	
			assertFalse(tPresent);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testGetArticlesDTO(){
		try {
			Collection articles = categorieFacade.getArticlesDTO(categorieDTOCreated.getId());
			boolean tDiff = false;
			for (Iterator it = articles.iterator(); it.hasNext(); ) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				tDiff = tDiff && (articleLocal.getCategorieLocal().getId().equals(categorieDTOCreated.getId()));
		    }	
			assertFalse(tDiff);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testGetCategories() throws RemoteException {
		try {
			Collection categories = categorieFacade.getCategories();
			for (Iterator it = categories.iterator(); it.hasNext(); ) {
				CategorieLocal categorieLocal = (CategorieLocal) it.next();
				categorieLocal.getCategorieDTO().getId().equals(categorieDTOCreated.getId());
				assertEquals(categorieDTOCreated.getId(), categorieLocal.getId());
				assertEquals(categorieDTOTemoin.getLibelle(), categorieLocal.getLibelle());
			}	
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
