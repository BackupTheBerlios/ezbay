package axlomoso.ezbay.test;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;

import axlomoso.ezbay.model.ejb.ActionTransactionFacadeBean;
import axlomoso.ezbay.model.ejb.ArticleFacadeBean;
import axlomoso.ezbay.model.ejb.CategorieFacadeBean;
import axlomoso.ezbay.model.ejb.ClientFacadeBean;
import axlomoso.ezbay.model.ejb.MembreFacadeBean;
import axlomoso.ezbay.model.ejb.VendeurFacadeBean;
import axlomoso.ezbay.model.interfaces.ActionTransactionDTO;
import axlomoso.ezbay.model.interfaces.ActionTransactionLocal;
import axlomoso.ezbay.model.interfaces.ActionTransactionLocalHome;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ArticleLocalHome;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieLocal;
import axlomoso.ezbay.model.interfaces.CategorieLocalHome;
import axlomoso.ezbay.model.interfaces.CategorieUtil;
import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.ClientLocal;
import axlomoso.ezbay.model.interfaces.ClientLocalHome;
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
import axlomoso.ezbay.model.interfaces.VendeurUtil;
import axlomoso.ezbay.utils.ServiceLocator;
import junit.framework.TestCase;

public class VendeurFacadeTest extends TestCase {
	//facades remote
	VendeurFacade vendeurFacade;
	MembreFacade membreFacade;
	//DTOs
	MembreDTO membreDTOcree1;
	MembreDTO membreDTOcree2;
	ClientDTO clientDTOcree1;
	ClientDTO clientDTOcree2;	
	VendeurDTO vendeurDTOcree1;
	VendeurDTO vendeurDTOcree2;
	
	public VendeurFacadeTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		ServiceLocator locator = ServiceLocator.getInstance();
		//creation de la facade du vendeur
		VendeurFacadeHome vendeurFacadeHome;
		vendeurFacadeHome = (VendeurFacadeHome) locator.getRemoteHome(VendeurFacadeHome.JNDI_NAME, VendeurFacadeHome.class);
		this.vendeurFacade = vendeurFacadeHome.create();
		//creation de la facade du membre
		MembreFacadeHome membreFacadeHome;
		membreFacadeHome = (MembreFacadeHome) locator.getRemoteHome(MembreFacadeHome.JNDI_NAME, MembreFacadeHome.class);
		this.membreFacade = membreFacadeHome.create();
		
		//creation de 2 membres
		MembreDTO tMembreDTO = new MembreDTO();
		MembreLocalHome membreLocalHome = MembreFacadeBean.getEntityHome();
		tMembreDTO.setNom("membre 1");
		MembreLocal membreLocal1 = membreLocalHome.create(tMembreDTO);
		membreDTOcree1 = membreLocal1.getMembreDTO();
		tMembreDTO.setNom("membre 2");
		MembreLocal membreLocal2 = membreLocalHome.create(tMembreDTO);
		membreDTOcree2 = membreLocal2.getMembreDTO();
		
		//creation de 2 clients
		ClientLocalHome clientLocalHome = ClientFacadeBean.getEntityHome();
		ClientLocal clientLocal1 = clientLocalHome.create();
		ClientLocal clientLocal2 = clientLocalHome.create();
		clientDTOcree1 = clientLocal1.getClientDTO();
		clientDTOcree2 = clientLocal2.getClientDTO();

		//creation de 2 vendeurs
		VendeurLocalHome vendeurLocalHome = VendeurFacadeBean.getEntityHome();
		VendeurDTO tVendeurDTO;
		tVendeurDTO = new VendeurDTO();
		tVendeurDTO.setCodeSecuCB(VendeurUtil.generateGUID(this));
		tVendeurDTO.setDateExpirCB(new Date(System.currentTimeMillis()));
		tVendeurDTO.setNumCB(VendeurUtil.generateGUID(this));
		tVendeurDTO.setNomProprioCB("vendeur 1");
		VendeurLocal vendeurLocal1 = vendeurLocalHome.create(tVendeurDTO);
		vendeurDTOcree1 = vendeurLocal1.getVendeurDTO();
		tVendeurDTO = new VendeurDTO();
		tVendeurDTO.setCodeSecuCB(VendeurUtil.generateGUID(this));
		tVendeurDTO.setDateExpirCB(new Date(System.currentTimeMillis()));
		tVendeurDTO.setNumCB(VendeurUtil.generateGUID(this));
		tVendeurDTO.setNomProprioCB("vendeur 2");		
		VendeurLocal vendeurLocal2 = vendeurLocalHome.create(tVendeurDTO);
		vendeurDTOcree2 = vendeurLocal2.getVendeurDTO();
		
		//relations :
		membreLocal1.setClientLocal(clientLocal1);
		membreLocal1.setVendeurLocal(vendeurLocal1);
		membreLocal2.setClientLocal(clientLocal2);
		membreLocal2.setVendeurLocal(vendeurLocal2);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		//suppression des vendeurs crees
		VendeurLocalHome vendeurLocalHome=VendeurFacadeBean.getEntityHome();
		vendeurLocalHome.findByPrimaryKey(vendeurDTOcree1.getId()).remove();
		vendeurLocalHome.findByPrimaryKey(vendeurDTOcree2.getId()).remove();
		//suppression des clients crees
		ClientLocalHome clientLocalHome = ClientFacadeBean.getEntityHome();
		clientLocalHome.findByPrimaryKey(clientDTOcree1.getId()).remove();
		clientLocalHome.findByPrimaryKey(clientDTOcree2.getId()).remove();
		//suppression des membres crees
		MembreLocalHome membreLocalHome=MembreFacadeBean.getEntityHome();		
		membreLocalHome.findByPrimaryKey(membreDTOcree1.getId()).remove();
		membreLocalHome.findByPrimaryKey(membreDTOcree2.getId()).remove();		
	}

	public void testGetMembre() throws RemoteException, Exception{
		MembreDTO membreDTORecup ;
		membreDTORecup = vendeurFacade.getMembre(vendeurDTOcree1.getId());
		assertTrue( membreDTORecup.getId().equals(membreDTOcree1.getId()) );
		membreDTORecup = vendeurFacade.getMembre(vendeurDTOcree2.getId());
		assertTrue( membreDTORecup.getId().equals(membreDTOcree2.getId()) );
	}
	
	public void testGetVendeur() throws RemoteException, Exception{
		VendeurDTO vendeurDTORecup = null;
		vendeurDTORecup = vendeurFacade.getVendeur(vendeurDTOcree1.getId());
		assertTrue( equalsDTO(vendeurDTORecup, vendeurDTOcree1, true) );
		vendeurDTORecup = vendeurFacade.getVendeur(vendeurDTOcree2.getId());
		assertTrue( equalsDTO(vendeurDTORecup, vendeurDTOcree2, true) );		
	}
	
	//méthode de test de vendeurFacade.getArticlesEnAttente() et vendeurFacade.getArticlesEnVentee() t vendeurFacade.getArticlesVendus()
	public void testGetArticlesEnAttenteOuEnVenteOuVendus() throws Exception{
		//prerequis : articles créés
		VendeurLocal vendeurLocal1 = VendeurFacadeBean.getEntity(vendeurDTOcree1.getId());
		VendeurLocal vendeurLocal2 = VendeurFacadeBean.getEntity(vendeurDTOcree2.getId());
		
		CategorieLocalHome categorieLocalHome = CategorieFacadeBean.getEntityHome();
		CategorieDTO categorieDTO = new CategorieDTO();
		categorieDTO.setLibelle("cat" + CategorieUtil.generateGUID(this));
		CategorieLocal categorieLocal = categorieLocalHome.create(categorieDTO);
		categorieDTO = categorieLocal.getCategorieDTO();
		
		Random ran = new Random();
		ArticleDTO articleDTO = null;
		ArticleLocalHome articleLocalHome = ArticleFacadeBean.getEntityHome();
		
		articleDTO = new ArticleDTO();
		articleDTO.setAnneeFabrication(new Integer(1900 + ran.nextInt()));
		articleDTO.setDateLimite(new Date(System.currentTimeMillis() + ran.nextInt()));
		articleDTO.setDescription("description " + VendeurUtil.generateGUID(this));
		articleDTO.setLibelle("description " + VendeurUtil.generateGUID(this));
		articleDTO.setMarque("marque " + VendeurUtil.generateGUID(this));
		articleDTO.setModele("modèle " + VendeurUtil.generateGUID(this));
		articleDTO.setPrixVente(new Double(ran.nextDouble()));
		ArticleLocal articleLocal1 = articleLocalHome.create(articleDTO, vendeurLocal1);
		articleLocal1.setCategorieLocal(categorieLocal);
		
		articleDTO = new ArticleDTO();
		articleDTO.setAnneeFabrication(new Integer(1900 + ran.nextInt()));
		articleDTO.setDateLimite(new Date(System.currentTimeMillis() + ran.nextInt()));
		articleDTO.setDescription("description " + VendeurUtil.generateGUID(this));
		articleDTO.setLibelle("description " + VendeurUtil.generateGUID(this));
		articleDTO.setMarque("marque " + VendeurUtil.generateGUID(this));
		articleDTO.setModele("modèle " + VendeurUtil.generateGUID(this));
		articleDTO.setPrixVente(new Double(ran.nextDouble()));
		ArticleLocal articleLocal2 = articleLocalHome.create(articleDTO, vendeurLocal1);
		articleLocal2.setCategorieLocal(categorieLocal);
		
		articleDTO = new ArticleDTO();
		articleDTO.setAnneeFabrication(new Integer(1900 + ran.nextInt()));
		articleDTO.setDateLimite(new Date(System.currentTimeMillis() + ran.nextInt()));
		articleDTO.setDescription("description " + VendeurUtil.generateGUID(this));
		articleDTO.setLibelle("description " + VendeurUtil.generateGUID(this));
		articleDTO.setMarque("marque " + VendeurUtil.generateGUID(this));
		articleDTO.setModele("modèle " + VendeurUtil.generateGUID(this));
		articleDTO.setPrixVente(new Double(ran.nextDouble()));
		ArticleLocal articleLocal3 = articleLocalHome.create(articleDTO, vendeurLocal2);
		articleLocal3.setCategorieLocal(categorieLocal);
		
		Collection articlesEnAttente = null;
		Collection articlesEnVente = null;
		Collection articlesVendus = null;
		articlesEnAttente = vendeurFacade.getArticlesEnAttente(vendeurDTOcree1.getId());
		articlesEnVente = vendeurFacade.getArticlesEnVente(vendeurDTOcree1.getId());
		assertEquals(articlesEnAttente.size(), 2); //vendeur1 a 2 article en attente
		assertEquals(articlesEnVente.size(), 0);//vendeur1 a 0 article en vente
		articlesEnAttente = vendeurFacade.getArticlesEnAttente(vendeurDTOcree2.getId());
		articlesEnVente = vendeurFacade.getArticlesEnVente(vendeurDTOcree2.getId());
		assertEquals(articlesEnAttente.size(), 1); //vendeur2 a 1 article en attente
		assertEquals(articlesEnVente.size(), 0);//vendeur2 a 0 article en vente
		
		//mise en vente
		articleLocal1.setEnVente(new Boolean(true)); //article de vendeur 1
		articleLocal3.setEnVente(new Boolean(true)); //article de vendeur 2
		articlesEnAttente = vendeurFacade.getArticlesEnAttente(vendeurDTOcree1.getId());
		articlesEnVente = vendeurFacade.getArticlesEnVente(vendeurDTOcree1.getId());
		assertEquals(articlesEnAttente.size(), 1); //vendeur1 n'a plus qu'1 article en attente
		assertEquals(articlesEnVente.size(), 1);//vendeur1 a 1 article en vente
		articlesEnAttente = vendeurFacade.getArticlesEnAttente(vendeurDTOcree2.getId());
		articlesEnVente = vendeurFacade.getArticlesEnVente(vendeurDTOcree2.getId());
		assertEquals(articlesEnAttente.size(), 0); //vendeur2 n'a plus d'article en attente
		assertEquals(articlesEnVente.size(), 1);//vendeur2 a 1 article en vente
				
		//retrait de la vente
		articleLocal1.setEnVente(new Boolean(false)); //article de vendeur 1
		articleLocal3.setEnVente(new Boolean(false)); //article de vendeur 2
		articlesEnAttente = vendeurFacade.getArticlesEnAttente(vendeurDTOcree1.getId());
		articlesEnVente = vendeurFacade.getArticlesEnVente(vendeurDTOcree1.getId());
		assertEquals(articlesEnAttente.size(), 2); //vendeur1 a 2 article en attente
		assertEquals(articlesEnVente.size(), 0);//vendeur1 a 0 article en vente
		articlesEnAttente = vendeurFacade.getArticlesEnAttente(vendeurDTOcree2.getId());
		articlesEnVente = vendeurFacade.getArticlesEnVente(vendeurDTOcree2.getId());
		assertEquals(articlesEnAttente.size(), 1); //vendeur2 a 1 article en attente
		assertEquals(articlesEnVente.size(), 0);//vendeur2 a 0 article en vente
		
		articlesVendus = vendeurFacade.getArticlesVendus(vendeurDTOcree1.getId());
		assertEquals(articlesVendus.size(), 0);//vendeur1 n'a rien vendu
		articlesVendus = vendeurFacade.getArticlesVendus(vendeurDTOcree2.getId());
		assertEquals(articlesVendus.size(), 0);//vendeur2 n'a rien vendu

		//Vente : ActionTransaction
		ActionTransactionLocalHome actionTransactionLocalHome = ActionTransactionFacadeBean.getEntityHome();
		//client1 achète article1
		ActionTransactionDTO transactionDTO1 = new ActionTransactionDTO();
		transactionDTO1.setDate(new Date(System.currentTimeMillis()));
		transactionDTO1.setMontant(new Double(articleLocal1.getPrixVente().doubleValue() + ran.nextDouble() ));
		ActionTransactionLocal transactionLocal1 = actionTransactionLocalHome.create(transactionDTO1);
		transactionLocal1.setArticleLocal(articleLocal1);
		transactionLocal1.setClientLocal(ClientFacadeBean.getEntity(clientDTOcree1.getId()));
		//test
		articlesVendus = vendeurFacade.getArticlesVendus(vendeurDTOcree1.getId());
		assertEquals(articlesVendus.size(), 1);//vendeur1 a vendu 1 article
		//client1 achète article2
		ActionTransactionDTO transactionDTO2 = new ActionTransactionDTO();
		transactionDTO2.setDate(new Date(System.currentTimeMillis()));
		transactionDTO2.setMontant(new Double(articleLocal2.getPrixVente().doubleValue() + ran.nextDouble() ));
		ActionTransactionLocal transactionLocal2 = actionTransactionLocalHome.create(transactionDTO1);
		transactionLocal2.setArticleLocal(articleLocal2);
		transactionLocal2.setClientLocal(ClientFacadeBean.getEntity(clientDTOcree1.getId()));
		//test
		articlesVendus = vendeurFacade.getArticlesVendus(vendeurDTOcree1.getId());
		assertEquals(articlesVendus.size(), 2);//vendeur1 a vendu 2 articles
		//client2 achète article3
		ActionTransactionDTO transactionDTO3 = new ActionTransactionDTO();
		transactionDTO3.setDate(new Date(System.currentTimeMillis()));
		transactionDTO3.setMontant(new Double(articleLocal2.getPrixVente().doubleValue() + ran.nextDouble() ));
		ActionTransactionLocal transactionLocal3 = actionTransactionLocalHome.create(transactionDTO1);
		transactionLocal3.setArticleLocal(articleLocal3);
		transactionLocal3.setClientLocal(ClientFacadeBean.getEntity(clientDTOcree2.getId()));
		//	test
		articlesVendus = vendeurFacade.getArticlesVendus(vendeurDTOcree2.getId());
		assertEquals(articlesVendus.size(), 1);//vendeur2 a vendu 1 articles
		
		//suppression des transactions
		transactionLocal1.remove();
		transactionLocal2.remove();
		transactionLocal3.remove();
		//test
		articlesVendus = vendeurFacade.getArticlesVendus(vendeurDTOcree1.getId());
		assertEquals(articlesVendus.size(), 0);//vendeur1 a vendu 0 article
		articlesVendus = vendeurFacade.getArticlesVendus(vendeurDTOcree2.getId());
		assertEquals(articlesVendus.size(), 0);//vendeur2 a vendu 0 articles		
		
		// Nettoyage...
		articleLocal1.remove();
		articleLocal2.remove();
		articleLocal3.remove();
		categorieLocal.remove();
		
		articlesEnAttente = vendeurFacade.getArticlesEnAttente(vendeurDTOcree1.getId());
		assertEquals(articlesEnAttente.size(), 0); //vendeur1 n'a plus d'article
		articlesEnAttente = vendeurFacade.getArticlesEnAttente(vendeurDTOcree2.getId());
		assertEquals(articlesEnAttente.size(), 0); //vendeur2 n'a plus d'article
	}


	
	public static boolean equalsDTO(VendeurDTO vendeurDTO1, VendeurDTO vendeurDTO2, boolean testerId){
		boolean tRes = true;
		if(testerId){
			tRes = tRes && ( 
					( ( vendeurDTO1.getId() == null ) && ( vendeurDTO2.getId() == null ) )
					|| ( (vendeurDTO1.getId()).equals(vendeurDTO2.getId()) )
					);			
		}
		tRes = tRes && ( 
				( ( vendeurDTO1.getCodeSecuCB() == null ) && ( vendeurDTO2.getCodeSecuCB() == null ) )
				|| ( (vendeurDTO1.getCodeSecuCB()).equals(vendeurDTO2.getCodeSecuCB()) )
				);
		tRes = tRes && (
				( vendeurDTO1.getNomProprioCB() == null ) && ( vendeurDTO2.getNomProprioCB() == null )
				|| ( (vendeurDTO1.getNomProprioCB()).equals(vendeurDTO2.getNomProprioCB()) )
				);
		tRes = tRes && ( 
				( vendeurDTO1.getNumCB() == null ) && ( vendeurDTO2.getNumCB() == null )
				|| ( (vendeurDTO1.getNumCB()).equals(vendeurDTO2.getNumCB()) )
				);		
		return tRes;
	}
	
/*	
*/
	
}
