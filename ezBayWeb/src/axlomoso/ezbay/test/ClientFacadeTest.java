package axlomoso.ezbay.test;

import java.util.Collection;
import java.util.Iterator;

import axlomoso.ezbay.model.ejb.ActionEnchereFacadeBean;
import axlomoso.ezbay.model.ejb.ActionTransactionFacadeBean;
import axlomoso.ezbay.model.ejb.ArticleFacadeBean;
import axlomoso.ezbay.model.ejb.CategorieFacadeBean;
import axlomoso.ezbay.model.ejb.ClientFacadeBean;
import axlomoso.ezbay.model.ejb.MembreFacadeBean;
import axlomoso.ezbay.model.ejb.VendeurFacadeBean;
import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.ActionEnchereLocal;
import axlomoso.ezbay.model.interfaces.ActionEnchereLocalHome;
import axlomoso.ezbay.model.interfaces.ActionTransactionDTO;
import axlomoso.ezbay.model.interfaces.ActionTransactionLocal;
import axlomoso.ezbay.model.interfaces.ActionTransactionLocalHome;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ArticleLocalHome;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieLocal;
import axlomoso.ezbay.model.interfaces.CategorieLocalHome;
import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.ClientFacade;
import axlomoso.ezbay.model.interfaces.ClientFacadeHome;
import axlomoso.ezbay.model.interfaces.ClientLocal;
import axlomoso.ezbay.model.interfaces.ClientLocalHome;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreLocal;
import axlomoso.ezbay.model.interfaces.MembreLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurLocal;
import axlomoso.ezbay.model.interfaces.VendeurLocalHome;
import axlomoso.ezbay.utils.ServiceLocator;
import axlomoso.ezbay.utils.ServiceLocatorException;


import junit.framework.TestCase;

public class ClientFacadeTest extends TestCase {
	/**
	 * The fixture
	 */
	
	ClientFacade clientFacade;
	MembreDTO membreDTOcree;
	ClientDTO clientDTOcree;
	VendeurDTO vendeurDTOcree;
	CategorieDTO categorieDTO;
	ArticleDTO articleDTOcree1;
	ArticleDTO articleDTOcree2;
	ActionEnchereDTO actionEnchereDTO;
	ActionTransactionDTO actionTransactionDTO;
	
	

	protected void setUp() throws Exception {
		try{			
			ServiceLocator locator = ServiceLocator.getInstance();
						
			//creation de la facade du client
			ClientFacadeHome clientFacadeHome;
			clientFacadeHome = (ClientFacadeHome) locator.getRemoteHome(ClientFacadeHome.JNDI_NAME, ClientFacadeHome.class);
			this.clientFacade = clientFacadeHome.create();

			//creation d un membre
			MembreDTO tMembreDTO = new MembreDTO();
			MembreLocalHome membreLocalHome = MembreFacadeBean.getEntityHome();
			tMembreDTO.setNom("membre 1");
			MembreLocal membreLocal1 = membreLocalHome.create(tMembreDTO);
			membreDTOcree = membreLocal1.getMembreDTO();			
			
			
			//creation d un client
			ClientLocalHome clientLocalHome = ClientFacadeBean.getEntityHome();
			ClientLocal clientLocal1 = clientLocalHome.create();			
			clientDTOcree = clientLocal1.getClientDTO();
			

			//creation de 1 vendeur
			VendeurLocalHome vendeurLocalHome = VendeurFacadeBean.getEntityHome();
			VendeurDTO tvendeurDTO;
			tvendeurDTO = new VendeurDTO();
			tvendeurDTO.setNomProprioCB("vendeur 1");		
			VendeurLocal vendeurLocal1 = vendeurLocalHome.create(tvendeurDTO);
			vendeurDTOcree = vendeurLocal1.getVendeurDTO();
			
			
			//relations :
			membreLocal1.setClientLocal(clientLocal1);
			membreLocal1.setVendeurLocal(vendeurLocal1);
			
			//creation d une categorie
			CategorieLocalHome categorieLocalHome = CategorieFacadeBean.getEntityHome();
			CategorieDTO categorieDTO1=new CategorieDTO();
			categorieDTO1.setLibelle("categorie 1");
			CategorieLocal categorieLocal = categorieLocalHome.create(categorieDTO1);
			categorieDTO = categorieLocal.getCategorieDTO();
			
			
			//creation de deux articles
			ArticleLocalHome articleLocalHome = ArticleFacadeBean.getEntityHome();
			ArticleDTO articleDTO1=new ArticleDTO();
			articleDTO1.setLibelle("article 1");
			articleDTO1.setMarque("marque 1");
			articleDTO1.setModele("model 1");
			articleDTO1.setPrixVente(new Double(200));
			articleDTO1.setAnneeFabrication(new Integer(2005));			
			articleDTO1.setDescription("description 1");	
			ArticleLocal articleLocal1 = articleLocalHome.create(articleDTO1, vendeurLocal1);
			articleLocal1.setCategorieLocal(categorieLocal);
			articleDTOcree1 = articleLocal1.getArticleDTO();

			ArticleDTO articleDTO2 = new ArticleDTO();
			articleDTO2.setLibelle("article 2");
			articleDTO2.setMarque("marque 2");
			articleDTO2.setModele("model 2");
			articleDTO2.setPrixVente(new Double(201));
			articleDTO2.setAnneeFabrication(new Integer(2005));				
			articleDTO2.setDescription("description 2");	
			ArticleLocal articleLocal2 = articleLocalHome.create(articleDTO2, vendeurLocal1);
			articleLocal2.setCategorieLocal(categorieLocal);
			articleDTOcree2 = articleLocal2.getArticleDTO();
			
			//creation d une enchere pour l article 1 faite par le client cree
			ActionEnchereLocalHome actionEnchereLocalHome = ActionEnchereFacadeBean.getEntityHome();
			ActionEnchereDTO actionEnchereDTO1=new ActionEnchereDTO();
			actionEnchereDTO1.setMontant(new Double(202));			
			ActionEnchereLocal actionEnchereLocal = actionEnchereLocalHome.create(actionEnchereDTO1,articleLocal1,clientLocal1);
			actionEnchereDTO = actionEnchereLocal.getActionEnchereDTO();
			
			//creation d une transaction pour l article2 faite par le client cree 
			ActionTransactionLocalHome actionTransactionLocalHome = ActionTransactionFacadeBean.getEntityHome();
			ActionTransactionDTO actionTransactionDTO1=new ActionTransactionDTO();
			actionTransactionDTO1.setMontant(new Double(202));			
			ActionTransactionLocal actionTransactionLocal = actionTransactionLocalHome.create(actionTransactionDTO1);
			actionTransactionLocal.setArticleLocal(articleLocal2);
			actionTransactionLocal.setClientLocal(clientLocal1);
			actionTransactionDTO = actionTransactionLocal.getActionTransactionDTO();
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
	}
	
	protected void tearDown() throws Exception {
		//suppression des articles crees
		ArticleLocalHome articleLocalHome = ArticleFacadeBean.getEntityHome(); 
		articleLocalHome.findByPrimaryKey(articleDTOcree1.getId()).remove();
		articleLocalHome.findByPrimaryKey(articleDTOcree2.getId()).remove();
		//suppression du vendeur cree
		VendeurLocalHome vendeurLocalHome=VendeurFacadeBean.getEntityHome();
		vendeurLocalHome.findByPrimaryKey(vendeurDTOcree.getId()).remove();
		
		//suppression du client cree
		ClientLocalHome clientLocalHome = ClientFacadeBean.getEntityHome();
		clientLocalHome.findByPrimaryKey(clientDTOcree.getId()).remove();
		
		//suppression du membre
		MembreLocalHome membreLocalHome=MembreFacadeBean.getEntityHome();		
		membreLocalHome.findByPrimaryKey(membreDTOcree.getId()).remove();
				
		//suppression de la categorie creee
		CategorieLocalHome  categorieLocalHome=CategorieFacadeBean.getEntityHome();
		categorieLocalHome.findByPrimaryKey(categorieDTO.getId()).remove();
		
		//suppression de l enchere creee
		ActionEnchereLocalHome actionEnchereLocalHome = ActionEnchereFacadeBean.getEntityHome();
		actionEnchereLocalHome.findByPrimaryKey(actionEnchereDTO.getId()).remove();
		
		//suppression de la transaction creee
		ActionTransactionLocalHome actionTransactionLocalHome = ActionTransactionFacadeBean.getEntityHome();
		actionTransactionLocalHome.findByPrimaryKey(actionTransactionDTO.getId()).remove();
		
	}
	
		 
	public void testGetMembre(){		
		try {
			MembreDTO membreDTO =clientFacade.getMembre(clientDTOcree.getId());
			assertNotNull(membreDTO.getId());
			assertTrue(membreDTO.getNom().equals(membreDTOcree.getNom()));		
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}
	
	
	public void testGetArticlesEnEncheres(){		
		try {
			//on recupere la liste des articles encheris pour le client cree
			Collection articlesEnEnchere =clientFacade.getArticlesEnEncheres(clientDTOcree.getId());
			assertFalse(articlesEnEnchere.size()==0);//echec la liste doit contenir un article
			for (Iterator it = articlesEnEnchere.iterator(); it.hasNext(); ) {
				ArticleDTO artDTO = (ArticleDTO) it.next();
				assertTrue(ArticleFacadeTest.equalsDTO(artDTO,articleDTOcree1,true));//succes l article existe bien dans la liste des articles encheris
				}			
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}
	
	public void testGetArticlesAchetes(){		
		try {
			//on recupere la liste des articles achetes pour le client cree
			Collection articlesAchetes =clientFacade.getArticlesAchetes(clientDTOcree.getId());
			assertFalse(articlesAchetes.size()==0);//echec la liste doit contenir un article
			for (Iterator it = articlesAchetes.iterator(); it.hasNext(); ) {
				ArticleDTO artDTO = (ArticleDTO) it.next();
				assertTrue(ArticleFacadeTest.equalsDTO(artDTO,articleDTOcree2,true));//succes l article existe bien dans la liste des articles encheris
				}			
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}
	
	


	
}
