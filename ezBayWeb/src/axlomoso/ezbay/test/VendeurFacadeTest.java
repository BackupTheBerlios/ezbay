package axlomoso.ezbay.test;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;

import junit.framework.TestCase;
import axlomoso.ezbay.exceptions.ArticleEnEnchereException;
import axlomoso.ezbay.exceptions.ArticleEnVenteException;
import axlomoso.ezbay.exceptions.ArticleProprietaireException;
import axlomoso.ezbay.exceptions.ArticleVenduException;
import axlomoso.ezbay.exceptions.VendeurInconnuException;
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

public class VendeurFacadeTest extends TestCase {
	//facades remote
	private VendeurFacade vendeurFacade;
	private MembreFacade membreFacade;
	//DTOs
	private MembreDTO membreDTOcree1;
	private MembreDTO membreDTOcree2;
	private ClientDTO clientDTOcree1;
	private ClientDTO clientDTOcree2;	
	private VendeurDTO vendeurDTOcree1;
	private VendeurDTO vendeurDTOcree2;
	
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
	
	//méthode de test de vendeurFacade.getArticlesEnAttente() et vendeurFacade.getArticlesEnVentee() et vendeurFacade.getArticlesVendus()
	public void testGetArticlesEnAttenteOuEnVenteOuVendus() throws Exception{
		//prerequis : articles créés
		//Création des objets
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
		
		//DEBUT DU TEST
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

	//méthode de test de vendeurFacade.mettreEnVenteArticle() et vendeurFacade.retirerArticle()
	public void testMettreEnVenteArticle() throws FinderException, CreateException, EJBException, RemoveException{
		//prerequis : articles créés
		//Création des objets
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

		
		//DEBUT du test mettreEnVente
		try {
			vendeurFacade.mettreEnVenteArticle(vendeurDTOcree2.getId(), articleLocal1.getId());
			assertTrue(false); //ECHEC : le vendeur n'aurait pas du povoir,  les articles ne lui appartiennent pas
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ArticleProprietaireException e) {
			assertTrue(true); //SUCCES : le vendeur n'a pas pu, les articles ne lui appartiennent pas
		}
		
		try {
			vendeurFacade.mettreEnVenteArticle(vendeurDTOcree2.getId(), articleLocal2.getId());
			assertTrue(false); //ECHEC : le vendeur n'aurait pas du povoir,  les articles ne lui appartiennent pas
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ArticleProprietaireException e) {
			assertTrue(true); //SUCCES : le vendeur n'a pas pu, les articles ne lui appartiennent pas
		}
		try {
			vendeurFacade.mettreEnVenteArticle(vendeurDTOcree1.getId(), articleLocal3.getId());
			assertTrue(false); //ECHEC : le vendeur n'aurait pas du povoir,  les articles ne lui appartiennent pas
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ArticleProprietaireException e) {
			assertTrue(true); //SUCCES : le vendeur n'a pas pu, les articles ne lui appartiennent pas
		}
		
		try {
			vendeurFacade.mettreEnVenteArticle(vendeurDTOcree1.getId(), articleLocal1.getId());
			vendeurFacade.mettreEnVenteArticle(vendeurDTOcree1.getId(), articleLocal2.getId());
			vendeurFacade.mettreEnVenteArticle(vendeurDTOcree2.getId(), articleLocal3.getId());
			assertTrue(true); //SUCCES : le vendeur a pu, les articles lui appartiennent
			assertTrue(articleLocal1.getEnVente().booleanValue() == true);
			assertTrue(articleLocal2.getEnVente().booleanValue() == true);
			assertTrue(articleLocal3.getEnVente().booleanValue() == true);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ArticleProprietaireException e) {
			assertTrue(false); //ECHEC : e vendeur aurait du pouvoir, les articles lui appartiennent
		}
		
		// Nettoyage...
		articleLocal1.remove();
		articleLocal2.remove();
		articleLocal3.remove();
		categorieLocal.remove();
	}
	
	
	public void testRetirerArticle() throws CreateException, FinderException, Exception{
		//prerequis : articles créés
		//Création des objets
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

		//prerequis : articles en vente
		articleLocal1.setEnVente(new Boolean(true));
		articleLocal2.setEnVente(new Boolean(true));
		articleLocal3.setEnVente(new Boolean(true));
		
		//vente de article1
		ActionTransactionLocalHome actionTransactionLocalHome = ActionTransactionFacadeBean.getEntityHome();
		ActionTransactionDTO transactionDTO = new ActionTransactionDTO();
		transactionDTO.setDate(new Date(System.currentTimeMillis()));
		transactionDTO.setMontant(articleLocal1.getPrixVente());
		ActionTransactionLocal transactionLocal = actionTransactionLocalHome.create(transactionDTO);
		transactionLocal.setArticleLocal(articleLocal1);
		transactionLocal.setClientLocal(ClientFacadeBean.getEntity(clientDTOcree1.getId()));
		//enchere sur article2
		ActionEnchereLocalHome actionEnchereLocalHome = ActionEnchereFacadeBean.getEntityHome();
		ActionEnchereDTO enchereDTO = new ActionEnchereDTO();
		enchereDTO.setDate(new Date(System.currentTimeMillis()));
		enchereDTO.setMontant(articleLocal1.getPrixVente());
		ActionEnchereLocal enchereLocal = actionEnchereLocalHome.create(enchereDTO, articleLocal2, ClientFacadeBean.getEntity(clientDTOcree1.getId()));
		
		try {
			vendeurFacade.retirerArticle(vendeurDTOcree1.getId(), articleLocal1.getId());
			assertTrue(false); // ECHEC : n'aurait pas du focntionner ==> l'article est vendu
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ArticleEnEnchereException e) {
			e.printStackTrace();
		} catch (ArticleVenduException e) {
			assertTrue(true);// SUCCES : n'a pas focntionné ==> l'article est vendu
		} catch (ArticleProprietaireException e) {
			e.printStackTrace();
		}
		
		try {
			vendeurFacade.retirerArticle(vendeurDTOcree1.getId(), articleLocal2.getId());
			assertTrue(false); // ECHEC : n'aurait pas du focntionner ==> l'article est enchéri
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ArticleEnEnchereException e) {
			assertTrue(true);// SUCCES : n'a pas focntionné ==> l'article est enchéri
		} catch (ArticleVenduException e) {
			e.printStackTrace();
		} catch (ArticleProprietaireException e) {
			e.printStackTrace();
		}
		//suppression des transactions et encheres
		transactionLocal.remove();
		enchereLocal.remove();
		try {
			vendeurFacade.retirerArticle(vendeurDTOcree1.getId(), articleLocal1.getId());
			assertTrue(true); // SUCCES : a focntionné ==> l'article n'est plus vendu
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ArticleEnEnchereException e) {
			e.printStackTrace();
		} catch (ArticleVenduException e) {
			assertTrue(false);// ECHEC : aurait du focntionner ==> l'article n'est plus vendu
		} catch (ArticleProprietaireException e) {
			e.printStackTrace();
		}
		try {
			vendeurFacade.retirerArticle(vendeurDTOcree1.getId(), articleLocal2.getId());
			assertTrue(true); // SUCCES : a focntionné ==> l'article n'est plus enchéri
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ArticleEnEnchereException e) {
			assertTrue(false);// ECHEC : aurait du focntionner ==> l'article n'est plus enchéri
		} catch (ArticleVenduException e) {
			e.printStackTrace();
		} catch (ArticleProprietaireException e) {
			e.printStackTrace();
		}
		try {
			vendeurFacade.retirerArticle(vendeurDTOcree1.getId(), articleLocal3.getId());
			assertTrue(false); // ECHEC : n'aurait pas du focntionner ==> l'article ne lui appartient pas
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ArticleEnEnchereException e) {
			e.printStackTrace();
		} catch (ArticleVenduException e) {
			e.printStackTrace();
		} catch (ArticleProprietaireException e) {
			assertTrue(true);// SUCCES : n'a pas focntionné ==> l'article ne lui appartient pas
		}
		try {
			vendeurFacade.retirerArticle(vendeurDTOcree2.getId(), articleLocal3.getId());
			assertTrue(true); // SUCCES : a focntionné ==> l'article lui appartient
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ArticleEnEnchereException e) {
			e.printStackTrace();
		} catch (ArticleVenduException e) {
			e.printStackTrace();
		} catch (ArticleProprietaireException e) {
			assertTrue(false);// ECHEC : aurait du focntionner ==> l'article lui appartient
		}
		
		
		// Nettoyage...
		articleLocal1.remove();
		articleLocal2.remove();
		articleLocal3.remove();
		categorieLocal.remove();
	}
	
	
	public void testSaveArticle() throws FinderException, CreateException, EJBException, RemoveException {
		CategorieLocalHome categorieLocalHome = CategorieFacadeBean.getEntityHome();
		CategorieDTO categorieDTO1 = new CategorieDTO();
		categorieDTO1.setLibelle("reererere");
		CategorieLocal categorieLocal1 = categorieLocalHome.create(categorieDTO1);
		categorieDTO1 = categorieLocal1.getCategorieDTO();
		CategorieDTO categorieDTO2 = new CategorieDTO();
		categorieDTO2.setLibelle("reererfgfgere");
		CategorieLocal categorieLocal2 = categorieLocalHome.create(categorieDTO2);
		categorieDTO2 = categorieLocal2.getCategorieDTO();
		
		ArticleDTO tArticleDTO = new ArticleDTO();
		tArticleDTO.setLibelle("article 1");
		tArticleDTO.setMarque("marque 1");
		tArticleDTO.setModele("model 1");
		tArticleDTO.setPrixVente(new Double(25.500));
		tArticleDTO.setAnneeFabrication(new Integer(1983));
		tArticleDTO.setDateLimite(new Date(System.currentTimeMillis() + 1 * 60 * 1000)); // date courant + 1min
		tArticleDTO.setDescription("Le premier article");
		
		ArticleDTO articleDTOCreated = null;
		//test avec un vendeur inconnu
		try {
			articleDTOCreated = vendeurFacade.saveArticle("dfdfdf", tArticleDTO, categorieDTO1.getId());
			assertTrue(false); // ECHEC : n'aurait pas du fonctionner ==> le vendeur n'existe pas
		} catch (RemoteException e) {
		} catch (VendeurInconnuException e) {
			assertTrue(true); // SUCCES : n'a pas fonctionné ==> le vendeur n'exxiste pas
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			articleDTOCreated = vendeurFacade.saveArticle(vendeurDTOcree1.getId(), tArticleDTO, categorieDTO1.getId());
			assertTrue(true); // SUCCES : a fonctionné ==> le vendeur exxiste
		} catch (RemoteException e) {
		} catch (VendeurInconnuException e) {
			assertTrue(false); // ECHEC : aurait du fonctionner ==> le vendeur existe
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// on vérifie que la création a bien fonctionné
		assertNotNull(articleDTOCreated);
		assertNotNull(articleDTOCreated.getId());
		try {
			ArticleFacadeBean.getEntity(articleDTOCreated.getId()).getArticleDTO();
			assertTrue(true); // l'article existe bien : il a été créé
		} catch (FinderException e) {
			assertTrue(false); // pb : l'article n' pas été créé.
		}
				
		//TEST DE LA MODIFICATION D'ARTICLE
		ArticleDTO tArticleDTO2 = new ArticleDTO();
		tArticleDTO2.setId(articleDTOCreated.getId());
		tArticleDTO2.setLibelle(tArticleDTO.getLibelle() + "changed");
		tArticleDTO2.setMarque(tArticleDTO.getMarque() + "changed");
		tArticleDTO2.setModele(tArticleDTO.getModele() + "changed");
		tArticleDTO2.setPrixVente( new Double(tArticleDTO.getPrixVente().doubleValue() + 100.0 ) );
		tArticleDTO2.setAnneeFabrication( new Integer(tArticleDTO.getAnneeFabrication().intValue() + 10) );
		tArticleDTO2.setDateLimite(new Date(System.currentTimeMillis() + 1 * 60 * 1000)); // date courant + 1min
		tArticleDTO2.setDescription(tArticleDTO.getDescription() + "changed");
		
		//on modifie
		ArticleDTO articleDTOCreated2 = null;
		try {
			articleDTOCreated2 = vendeurFacade.saveArticle(vendeurDTOcree1.getId(), tArticleDTO2, categorieDTO2.getId());
			
		} catch (RemoteException e) {
		} catch (VendeurInconnuException e) {
			
		} catch (Exception e) {
		}
		//on vérifie que la mise à jour a bien fonctionné
		assertNotNull(articleDTOCreated2);
		assertNotNull(articleDTOCreated2.getId());
		assertTrue( ArticleFacadeTest.equalsDTO(articleDTOCreated2, tArticleDTO2, false) ); // les champs ont-ils bien été modifiés ?
		String id1 = articleDTOCreated.getId();
		String id2 = articleDTOCreated2.getId();
		assertTrue( id1.equals(id2) ); // on vérifie que l'Id n'a pas changé
		String idCat1 = categorieDTO2.getId();
		String idCat2 = (ArticleFacadeBean.getEntity(id2)).getCategorieLocal().getId();
		System.out.println("idCat1 :" + idCat1);
		System.out.println("idCat2 :" + idCat2);
		assertTrue( idCat1.equals(idCat2) ); // test si la catégorie a bien été modifiée
		
		//nettoyage...
		ArticleFacadeBean.getEntity(articleDTOCreated2.getId()).remove();
		categorieLocal1.remove();
		categorieLocal2.remove();		
	}
	
	public void testRemoveArticle() throws EJBException, RemoveException, CreateException, FinderException, Exception{
		CategorieLocalHome categorieLocalHome = CategorieFacadeBean.getEntityHome();
		CategorieDTO categorieDTO = new CategorieDTO();
		categorieDTO.setLibelle("reererere");
		CategorieLocal categorieLocal = categorieLocalHome.create(categorieDTO);
		
		ArticleDTO tArticleDTO = new ArticleDTO();
		tArticleDTO.setLibelle("article 1");
		tArticleDTO.setMarque("marque 1");
		tArticleDTO.setModele("model 1");
		tArticleDTO.setPrixVente(new Double(25.500));
		tArticleDTO.setAnneeFabrication(new Integer(1983));
		tArticleDTO.setDateLimite(new Date(System.currentTimeMillis() + 1 * 60 * 1000)); // date courant + 1min
		tArticleDTO.setDescription("Le premier article");
		
		VendeurLocal vendeurLocal = VendeurFacadeBean.getEntity(vendeurDTOcree1.getId());
		ArticleLocalHome articleLocalHome = ArticleFacadeBean.getEntityHome();
		ArticleLocal articleLocal = articleLocalHome.create(tArticleDTO, vendeurLocal);
		ArticleDTO articleDTOcree = articleLocal.getArticleDTO();
		//récupération de id de l'article créé 
		String idArticle = articleDTOcree.getId();
		//mise en vente de l article
		articleLocal.setEnVente(new Boolean(true));
		//un article ne doit pouvoir etre supprimer si il est en vente
		try{
			vendeurFacade.removeArticle(vendeurDTOcree1.getId(),idArticle);
			assertTrue(false);//echec : la suppression n aurais pas du marcher --> raison l article est en vente 
		}
		catch(ArticleEnVenteException e){
			assertTrue(true);//succees : la suppression n a pas fonctionné --> raison l article est en vente
		} catch (ArticleVenduException e) {
			//ne peut pas se produire
			assertTrue(false);
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		//on retire l article de la vente pour puvoir le supprimer
		articleLocal.setEnVente(new Boolean(false));
		
		//vente de l'article
		ActionTransactionDTO transactionDTO = new ActionTransactionDTO();
		transactionDTO.setDate(new Date(System.currentTimeMillis()));
		transactionDTO.setMontant(articleLocal.getPrixVente());
		ActionTransactionLocalHome transactionLocalHome = ActionTransactionFacadeBean.getEntityHome();
		ActionTransactionLocal transactionLocal = transactionLocalHome.create(transactionDTO);
		transactionLocal.setArticleLocal(articleLocal);
		transactionLocal.setClientLocal(ClientFacadeBean.getEntity(clientDTOcree1.getId()));
		
		//un article ne doit pouvoir etre supprimer si il est vendu
		try{
			vendeurFacade.removeArticle(vendeurDTOcree1.getId(),idArticle);
			assertTrue(false);//echec : la suppression n aurais pas du marcher --> raison l article est vendu 
		}
		catch(ArticleEnVenteException e){
			//ne peut pas se produire
			assertTrue(false);
		} catch (ArticleVenduException e) {
			assertTrue(true);//succees : la suppression n a pas fonctionné --> raison l article est vendu
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		//suppression de la transaction : l'article n'est plus vendu
		transactionLocal.remove();
		
		//test : un vendeur supprime un article qui ne lui appartient pas 
		try{
			vendeurFacade.removeArticle(vendeurDTOcree2.getId(),idArticle);
			assertTrue(false);//echec : la suppression n aurais pas du marcher --> raison le vendeur passer en parametre n est pas le proprietaire
		}
		catch(ArticleEnVenteException e){
			//ne peut pas se produire
			assertTrue(false);
		} catch (ArticleVenduException e) {
			//ne peut pas se produire
			assertTrue(false);
		} catch (ArticleProprietaireException e) {
			assertTrue(true);//succes : la suppression n a pas marcher --> raison le vendeur passé en parametre n est pas le proprietaire
		} catch (Exception e) {
		} 
		
		//a partie d ici la suppression doit etre possible
		try {
			vendeurFacade.removeArticle(vendeurDTOcree1.getId(),idArticle);
			assertTrue(true);//succes suppression n a pas levé d'exception
		} catch (Exception e) {				
			assertTrue(false);
		}
		
		// on teste si la suppression a bien fonctionné

			try {
				articleLocalHome.findByPrimaryKey(idArticle);
				articleLocal.remove();
				assertTrue(false);//echec l article ne devrait pas exister
			} catch (FinderException e) {
				assertTrue(true);//succes l article n existe plus
			}
			
			//Nettoyage
			categorieLocal.remove();
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
	
}
