
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

import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ArticleLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurFacade;
import axlomoso.ezbay.model.interfaces.VendeurFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurLocal;
import axlomoso.ezbay.model.interfaces.VendeurLocalHome;

import junit.framework.TestCase;

/**
 */
public class VendeurFacadeTest extends TestCase {
	/**
	 * The fixture
	 */
	Context jndiContext;
	VendeurFacade vendeurFacade;
	VendeurDTO vendeurDTOTemoin = null; //DTO t�moin
	VendeurDTO vendeurDTOCreated = null; //DTO cr�� via la session facade

	/**
	 */
	public VendeurFacadeTest() {
		super();
	}

	protected void setUp() throws Exception {
		jndiContext = new InitialContext();
		Object ref = jndiContext.lookup(VendeurFacadeHome.JNDI_NAME);
		VendeurFacadeHome facadeHome = (VendeurFacadeHome) PortableRemoteObject.narrow(ref, VendeurFacadeHome.class);
		this.vendeurFacade = facadeHome.create();
		
		vendeurDTOTemoin = new VendeurDTO();	
		//cr�ation d'un vendeur par le sessionFacade
		vendeurDTOCreated = vendeurFacade.saveVendeur(vendeurDTOTemoin);
	}

	/**
	 */
	protected void tearDown() throws Exception {
		//r�cup�ration de l'vendeur cr�� via facade
		VendeurLocal vendeurLocal;
		VendeurLocalHome vendeurLocalHome;
		vendeurLocalHome = (VendeurLocalHome) jndiContext.lookup(VendeurLocalHome.JNDI_NAME);
		vendeurLocal = vendeurLocalHome.findByPrimaryKey(vendeurDTOCreated.getId());
		vendeurLocal.remove();
		this.vendeurFacade = null;
	}
	
	public void testGetVendeur() throws RemoteException {
		try {
			//r�cup�ration de l'vendeur cr�� via facade
			VendeurDTO vendeurDTO = vendeurFacade.getVendeur(vendeurDTOCreated.getId());
			assertTrue(this.equalsDTO(vendeurDTO, vendeurDTOCreated));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void testGetArticles() throws RemoteException {
		try {
			ArticleLocal articleLocal;
			ArticleLocalHome articleLocalHome;
			articleLocalHome = (ArticleLocalHome) jndiContext.lookup(ArticleLocalHome.JNDI_NAME);
			VendeurLocalHome vendeurLocalHome = (VendeurLocalHome) jndiContext.lookup(VendeurLocalHome.JNDI_NAME);
			VendeurLocal vendeurLocal = vendeurLocalHome.findByPrimaryKey(vendeurDTOCreated.getId());
			//cr�ation d'une liste d'articles pour le vendeur
			ArrayList newArticles = new ArrayList();
			int i=0;
			for(i=0 ; i<5 ; i++){
				ArticleDTO art = new ArticleDTO();
				art.setLibelle("Simpson N�" + i);
				newArticles.add(articleLocalHome.create(art, vendeurLocal).getArticleDTO());
			}
			//r�cup�ration des articles du vendeur
			Collection articlesVendeur = vendeurFacade.getArticles(vendeurDTOCreated.getId());
			//Est-ce que le vendeur poss�de bien (et uniquement) les articles que l'on vient de cr�er ?
			assertEquals(newArticles.size(), articlesVendeur.size()); // meme nombre
			//Est-ce que tous les articles que l'on vient de cr�er se trouvent bien dans la liste du vendeur ?
			 for (Iterator it = newArticles.iterator(); it.hasNext(); ) {
				assertTrue(articlesVendeur.contains(it.next()));
			}
			// Est-ce que tous les articles appartiennent bien au vendeur ?
			System.out.println("Articles du vendeur : ");
			i=0;
			for (Iterator it = articlesVendeur.iterator(); it.hasNext(); i++) {
				ArticleDTO articleDTO = (ArticleDTO) it.next();
				articleLocal = articleLocalHome.findByPrimaryKey(articleDTO.getId());
				System.out.println("article N�"+ i + " : " + articleDTO.getLibelle());
				assertEquals(vendeurDTOCreated.getId(),articleLocal.getVendeurLocal().getVendeurDTO().getId());
			}
			//suppression des articles			
			for (Iterator it = newArticles.iterator(); it.hasNext();) {
				ArticleDTO articleDTO = (ArticleDTO) it.next();
				articleLocal = articleLocalHome.findByPrimaryKey(articleDTO.getId());
				articleLocal.remove();
			}
			assertTrue(vendeurFacade.getArticles(vendeurDTOCreated.getId()).size() == 0);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EJBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean equalsDTO(VendeurDTO vendeurDTO1, VendeurDTO vendeurDTO2){
		boolean tRes = true;
		tRes = tRes && ( (vendeurDTO1.getId()).equals(vendeurDTO2.getId()) );
		return tRes;
	}
	
	
}
