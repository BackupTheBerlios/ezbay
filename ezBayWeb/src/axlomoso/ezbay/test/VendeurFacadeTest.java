
package axlomoso.ezbay.test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import axlomoso.ezbay.delegate.ArticleFacadeDelegate;
import axlomoso.ezbay.delegate.MembreFacadeDelegate;
import axlomoso.ezbay.delegate.VendeurFacadeDelegate;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ArticleLocalHome;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreLocal;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurFacade;
import axlomoso.ezbay.model.interfaces.VendeurFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurLocal;
import axlomoso.ezbay.model.interfaces.VendeurLocalHome;
import axlomoso.ezbay.utils.ServiceLocator;

import junit.framework.TestCase;

/**
 */
public class VendeurFacadeTest extends TestCase {
	/**
	 * The fixture
	 */
	//Context jndiContext;
	VendeurFacade vendeurFacade;
	VendeurDTO vendeurDTOTemoin = null; //DTO témoin
	VendeurDTO vendeurDTOCreated = null; //DTO créé via la session facade
	//MembreLocal membreLocal=null;
	MembreDTO membreDTOTemoin = null; //DTO témoin
	
	/**
	 */
	public VendeurFacadeTest() {
		super();
	}

	protected void setUp() throws Exception {
		//création d'un vendeur ==> init + test de vendeurFacade.saveVendeur
		VendeurFacadeDelegate vendeurFacadeDelegate = VendeurFacadeDelegate.getInstance();
		VendeurFacade vendeurFacade = vendeurFacadeDelegate.getVendeurFacade();
		vendeurDTOTemoin = new VendeurDTO();
		//vendeurDTOTemoin.setCodeSecuCB()
		//...
		vendeurDTOCreated = vendeurFacade.saveVendeur(vendeurDTOTemoin);
		vendeurDTOTemoin.setId(vendeurDTOCreated.getId());
	}

	/**
	 */
	protected void tearDown() throws Exception {
		// suppression du vendeur créé
		VendeurLocal vendeurLocal;
		ServiceLocator locator = ServiceLocator.getInstance();
		VendeurLocalHome home = (VendeurLocalHome) locator.getLocalHome(VendeurLocalHome.JNDI_NAME);
		vendeurLocal = home.findByPrimaryKey(vendeurDTOCreated.getId());
		vendeurLocal.remove();
	}
	
	public void testSaveVendeur() throws Exception {
			try {
				//	update d'un vendeur Existant
				vendeurDTOTemoin.setCodeSecuCB("123");
				vendeurDTOTemoin.setNomProprioCB("axloso");
				vendeurDTOTemoin.setNumCB("1212121212121212");
				
				vendeurDTOCreated = vendeurFacade.saveVendeur(vendeurDTOTemoin);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void testGetVendeur() throws RemoteException {
		try {
			//récupération du vendeur créé via facade
			VendeurDTO vendeurDTO = vendeurFacade.getVendeur(vendeurDTOCreated.getId());
			assertTrue(this.equalsDTO(vendeurDTO, vendeurDTOCreated));
			
			assertTrue(equalsDTO(vendeurDTOTemoin, vendeurDTOCreated));

			// création d'un nouveau vendeur
			VendeurDTO vendeurDTOTemoin2 = new VendeurDTO();
			VendeurDTO vendeurDTOCreated2 = new VendeurDTO();
			VendeurDTO vendeurDTOCreated3 = new VendeurDTO();
			vendeurDTOTemoin.setCodeSecuCB("123");
			vendeurDTOTemoin.setNomProprioCB("axloso");
			vendeurDTOTemoin.setNumCB("1212121212121212");
			vendeurDTOCreated2 = vendeurFacade.saveVendeur(vendeurDTOTemoin2);
			vendeurDTOTemoin2.setId(vendeurDTOCreated2.getId());
			assertTrue(equalsDTO(vendeurDTOTemoin2, vendeurDTOCreated2));
			
			//test de création d'un doublon
			//vendeurDTOCreated3 = vendeurFacade.saveVendeur(vendeurDTOTemoin2);
			// tester qu'il ya bien 1 erreur : Pas 2 vendeur avec mêmepseudo
			
			//effacement
			//vendeurDTOCreated2
			//vendeurDTOCreated3
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void testGetMembre() throws RemoteException {
		try{
			MembreFacadeDelegate membreFacadeDelegate = MembreFacadeDelegate.getInstance();
			// récupération du membre via la facade
			MembreDTO membreDTO = vendeurFacade.getMembre(vendeurDTOCreated.getId());
			assertEquals(membreDTO.getId(), membreFacadeDelegate.getMembreById(membreDTOTemoin.getId()));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testGetVendeurs() throws RemoteException {
		try {
			//récupération du vendeur créé via facade
			Collection vendeursDTO = vendeurFacade.getVendeurs();
			for (Iterator it = vendeursDTO.iterator(); it.hasNext(); ) {
				VendeurDTO vendeurDTO = (VendeurDTO) it.next();
				assertEquals(vendeurDTOTemoin.getNomProprioCB(),vendeurDTO.getNomProprioCB());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void testGetArticlesEnAttente() throws RemoteException {
		try{
			//ArticleFacadeDelegate articleFacadeDelegate = ArticleFacadeDelegate.getInstance();
			ArticleLocal articleLocal;
			ServiceLocator locator = ServiceLocator.getInstance();
			ArticleLocalHome home = (ArticleLocalHome) locator.getLocalHome(ArticleLocalHome.JNDI_NAME);
			Collection articlesDTO = vendeurFacade.getArticlesEnAttente(vendeurDTOCreated.getId());
			// récupération du membre via la facade
			for (Iterator it = articlesDTO.iterator(); it.hasNext(); ) {
				ArticleDTO articleDTO = (ArticleDTO) it.next();
				//assertEquals(articleDTO.getId());
				}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testGetArticlesEnVente() throws RemoteException {
		try{
			ArticleFacadeDelegate articleFacadeDelegate = ArticleFacadeDelegate.getInstance();
			Collection articlesDTO = vendeurFacade.getArticlesEnVente(vendeurDTOCreated.getId());
			// récupération du membre via la facade
			for (Iterator it = articlesDTO.iterator(); it.hasNext(); ) {
				ArticleDTO articleDTO = (ArticleDTO) it.next();
				assertEquals(articleDTO.getId(),articleFacadeDelegate.getArticlesEnVenteByVendeur(vendeurDTOTemoin.getId()));
				}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testGetArticlesVendus() throws RemoteException {
		try{
			ArticleFacadeDelegate articleFacadeDelegate = ArticleFacadeDelegate.getInstance();
			Collection articlesDTO = vendeurFacade.getArticlesVendus(vendeurDTOCreated.getId());
			// récupération du membre via la facade
			for (Iterator it = articlesDTO.iterator(); it.hasNext(); ) {
				ArticleDTO articleDTO = (ArticleDTO) it.next();
				assertEquals(articleDTO.getId(),articleFacadeDelegate.getArticlesEnVenteByVendeur(vendeurDTOTemoin.getId()));
				}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testSaveArticle() throws RemoteException {
		
		
	}
	
	public void testRemoveArticle() throws RemoteException {
		
	}
	
	
	public static boolean equalsDTO(VendeurDTO vendeurDTO1, VendeurDTO vendeurDTO2){
		boolean tRes = true;
		tRes = tRes && ( (vendeurDTO1.getId()).equals(vendeurDTO2.getId()) );
		tRes = tRes && ( (vendeurDTO1.getCodeSecuCB()).equals(vendeurDTO2.getCodeSecuCB()) );
		tRes = tRes && ( (vendeurDTO1.getNomProprioCB()).equals(vendeurDTO2.getNomProprioCB()) );
		tRes = tRes && ( (vendeurDTO1.getNumCB()).equals(vendeurDTO2.getNumCB()) );		
		return tRes;
	}
	
/*
	public VendeurDTO getVendeur(String vendeurId) throws Exception 
		
	public MembreDTO getMembre(String vendeurId) throws Exception 
		
	public Collection getVendeurs() 
	
	public Collection getArticlesEnAttente(String vendeurId) 
		
	public Collection getArticlesEnVente(String vendeurId) 
		
	public Collection getArticlesVendus(String vendeurId) 
	
	public ArticleDTO saveArticle(String vendeurId, ArticleDTO articleDTO, String categorieId) throws VendeurInconnuException, Exception
	
	public void removeArticle(String vendeurId, String articleId) throws ArticleEnVenteException, ArticleVenduException, Exception 
	
	public void retirerArticle(String vendeurId, String articleId) throws ArticleEnEnchereException, ArticleVenduException,ArticleProprietaireException
	
	public void mettreEnVenteArticle(String vendeurId, String articleId) throws ArticleProprietaireException{
			 
*/
	
	
}
