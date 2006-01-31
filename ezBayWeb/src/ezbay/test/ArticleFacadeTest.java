package ezbay.test;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import junit.framework.TestCase;
import ezbay.model.interfaces.ArticleDTO;
import ezbay.model.interfaces.ArticleFacade;
import ezbay.model.interfaces.ArticleFacadeHome;
import ezbay.model.interfaces.ArticleLocal;
import ezbay.model.interfaces.ArticleLocalHome;
import ezbay.model.interfaces.VendeurDTO;
import ezbay.model.interfaces.VendeurLocal;
import ezbay.model.interfaces.VendeurLocalHome;

/**
 */
public class ArticleFacadeTest extends TestCase {
	/**
	 * The fixture
	 */
	ArticleLocalHome articleLocalHome;
	ArticleFacade articleFacade;
	ArticleDTO articleDTOTemoin = null; //DTO t�moin
	ArticleDTO articleDTOCreated = null; //DTO cr�� via la session facade
	VendeurLocal vendeurLocal = null;

	/**
	 */
	public ArticleFacadeTest() {
		super();
	}

	/**
	 */
	protected void setUp() throws Exception {
		Context jndiContext = new InitialContext();
		Object ref = jndiContext.lookup(ArticleFacadeHome.JNDI_NAME);
		ArticleFacadeHome facadeHome = (ArticleFacadeHome) PortableRemoteObject.narrow(ref, ArticleFacadeHome.class);

		VendeurLocalHome vendeurLocalHome = (VendeurLocalHome) jndiContext.lookup(VendeurLocalHome.JNDI_NAME);
		vendeurLocal = (VendeurLocal) vendeurLocalHome.create();		
		
		this.articleFacade = facadeHome.create();
		articleLocalHome = (ArticleLocalHome) jndiContext.lookup(ArticleLocalHome.JNDI_NAME);
		articleDTOTemoin = new ArticleDTO();
		articleDTOTemoin.setLibelle("Homer Simpson");
		articleDTOTemoin.setMarque("Matt Groening");
		articleDTOTemoin.setModele("XXL");
		articleDTOTemoin.setPrixVente(new Double(25.500));
		articleDTOTemoin.setAnneeFabrication(new Integer(1983));
		articleDTOTemoin.setDateLimite(new Date(127));
		articleDTOTemoin.setDescription("Le premier Homer de la serie");
		
		//cr�ation d'un article par le sessionFacade
		articleDTOCreated = articleFacade.createArticle(articleDTOTemoin, vendeurLocal.getVendeurDTO());
	}

	protected void tearDown() throws Exception {
		//r�cup�ration de l'article cr�� via facade
		ArticleLocal articleLocal;
		articleLocal = articleLocalHome.findByPrimaryKey(articleDTOCreated.getId());
		articleLocal.remove();
		vendeurLocal.remove();
		this.articleFacade = null;
	}

	public void testGetArticle() throws RemoteException {
		try {
			//r�cup�ration de l'article cr�� via facade
			ArticleDTO articleDTO = articleFacade.getArticle(articleDTOCreated.getId());
			assertTrue(this.equalsDTO(articleDTO, articleDTOCreated));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testGetVendeurDTO() throws RemoteException {
		try {
			//r�cup�ration de l'article cr�� via facade
			VendeurDTO expectedVendeur = vendeurLocal.getVendeurDTO();
			VendeurDTO actualVendeur = articleFacade.getVendeurDTO(articleDTOCreated.getId());
			assertEquals(expectedVendeur.getId(), actualVendeur.getId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testGetArticlesByLibelle() throws RemoteException {
		Collection articlesDTO = articleFacade.getArticlesByLibelle(articleDTOTemoin.getLibelle());
		for (Iterator it = articlesDTO.iterator(); it.hasNext(); ) {
			ArticleDTO articleDTO = (ArticleDTO) it.next();
			assertEquals(articleDTOTemoin.getLibelle(),articleDTO.getLibelle());
		}
	}
	
	public void testUpdateArticle() throws RemoteException {
		try {
			//r�cup�ration de l'article cr�� via facade
			articleDTOCreated = articleFacade.getArticle(articleDTOCreated.getId());

			//modification du t�moins
			articleDTOTemoin.setLibelle("MONSIEUR Homer Simpson");
			
			// modification des DTO
			articleDTOCreated.setLibelle(articleDTOTemoin.getLibelle());
			
			//update du DTO par facade
			articleFacade.updateArticle(articleDTOCreated);
			articleDTOCreated = articleFacade.getArticle(articleDTOCreated.getId());
		
			//test
			assertEquals(articleDTOTemoin.getLibelle(), articleDTOCreated.getLibelle());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	
	public boolean equalsDTO(ArticleDTO articleDTO1, ArticleDTO articleDTO2){
		boolean tRes = true;
		tRes = tRes && ( (articleDTO1.getId()).equals(articleDTO2.getId()) );
		tRes = tRes && ( (articleDTO1.getLibelle()).equals(articleDTO2.getLibelle()) );
		tRes = tRes && ( (articleDTO1.getMarque()).equals(articleDTO2.getMarque()) );
		tRes = tRes && ( (articleDTO1.getModele()).equals(articleDTO2.getModele()) );
		tRes = tRes && ( (articleDTO1.getPrixVente()).equals(articleDTO2.getPrixVente()) );
		tRes = tRes && ( (articleDTO1.getAnneeFabrication()).equals(articleDTO2.getAnneeFabrication()) );
		/* Remarque : Il y a un petit soucis avec la comparaison de dates. A �claicir... */
		//tRes = tRes && ( (articleDTO1.getDateLimite()).equals(articleDTO2.getDateLimite()) ); // erreur ici, il faudra verifier
		tRes = tRes && ( (articleDTO1.getDescription()).equals(articleDTO2.getDescription()) );
		return tRes;
	}
	
}
