package axlomoso.ezbay.test;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

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
import axlomoso.ezbay.model.interfaces.ArticleFacade;
import axlomoso.ezbay.model.interfaces.ArticleFacadeHome;
import axlomoso.ezbay.model.interfaces.ArticleFacadeLocalHome;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ArticleLocalHome;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieFacade;
import axlomoso.ezbay.model.interfaces.CategorieFacadeHome;
import axlomoso.ezbay.model.interfaces.CategorieLocalHome;
import axlomoso.ezbay.model.interfaces.ClientLocal;
import axlomoso.ezbay.model.interfaces.ClientLocalHome;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreFacade;
import axlomoso.ezbay.model.interfaces.MembreFacadeHome;
import axlomoso.ezbay.model.interfaces.MembreLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurFacade;
import axlomoso.ezbay.model.interfaces.VendeurFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurLocalHome;
import axlomoso.ezbay.utils.ServiceLocator;
import axlomoso.ezbay.utils.ServiceLocatorException;

/**
 */
public class ArticleFacadeTest extends TestCase {
	/**
	 * The fixture
	 */
	ArticleDTO articleDTOcree1;
	ArticleDTO articleDTOcree2;
	VendeurDTO vendeurDTO;	
	CategorieDTO categorieDTO;
	MembreDTO membreDTO = new MembreDTO();
	ArticleFacade articleFacade;
	VendeurFacade vendeurFacade;
	MembreFacade membreFacade;
	CategorieFacade categorieFacade;	
	
	String defaultNomCategorie = new String("Categorie ");
	String defaultLibelleArticle = new String("article ");
	String defaultModeleArticle = new String("marque ");
	String defaultMarqueArticle = new String("modèle ");
	Double defaultPrixVenteArticle = new Double(100);
	Integer defaultAnneeFabricationArticle = new Integer(1900);	
	String defaultDescriptionArticle = new String("description ");
	Date defaultDateLimiteArticle = new Date(System.currentTimeMillis()); // date courante

	/**
	 */
	public ArticleFacadeTest() {
		super();
	}

	/**
	 */
	protected void setUp() throws Exception {
		
		try {			
			//creation de la facade de l'article
			ServiceLocator locator = ServiceLocator.getInstance();
			ArticleFacadeHome articleFacadeHome;
			articleFacadeHome = (ArticleFacadeHome) locator.getRemoteHome(ArticleFacadeHome.JNDI_NAME, ArticleFacadeHome.class);
			this.articleFacade = articleFacadeHome.create();					
			//creation de la facade du membre
			MembreFacadeHome membreFacadeHome;
			membreFacadeHome = (MembreFacadeHome) locator.getRemoteHome(MembreFacadeHome.JNDI_NAME, MembreFacadeHome.class);
			this.membreFacade = membreFacadeHome.create();			
			//creation de la facade du vendeur
			VendeurFacadeHome vendeurFacadeHome;
			vendeurFacadeHome = (VendeurFacadeHome) locator.getRemoteHome(VendeurFacadeHome.JNDI_NAME, VendeurFacadeHome.class);
			this.vendeurFacade = vendeurFacadeHome.create();			
			//creation de la facade de la categorie
			CategorieFacadeHome categorieFacadeHome;
			categorieFacadeHome = (CategorieFacadeHome) locator.getRemoteHome(CategorieFacadeHome.JNDI_NAME, CategorieFacadeHome.class);
			this.categorieFacade = categorieFacadeHome.create();

			//creation d un membre
			membreDTO.setNom("membre");
			membreDTO=membreFacade.saveMembre(membreDTO);
			//creation d un vendeur
			VendeurDTO vendeurDTO1=new VendeurDTO();
			vendeurDTO1.setNomProprioCB("vendeur");			
			vendeurDTO=membreFacade.saveVendeur(membreDTO,vendeurDTO1);
			//creation d une categorie
			CategorieDTO categorieDTO1=new CategorieDTO();
			categorieDTO1.setLibelle(defaultNomCategorie + "1");
			categorieDTO=categorieFacade.createCategorie(categorieDTO1);
			
			//creation de deux articles
			ArticleDTO articleDTO1=new ArticleDTO();
			articleDTO1.setLibelle(defaultLibelleArticle + "1");
			articleDTO1.setMarque(defaultMarqueArticle + "1");
			articleDTO1.setModele(defaultModeleArticle + "1");
			articleDTO1.setPrixVente(new Double(defaultPrixVenteArticle.doubleValue() + 1));
			articleDTO1.setAnneeFabrication(new Integer(defaultAnneeFabricationArticle.intValue() + 1));
			articleDTO1.setDateLimite(new Date(defaultDateLimiteArticle.getTime() + 1 * 60 * 1000)); // date courant + 1 min
			articleDTO1.setDescription(defaultDescriptionArticle + "1");	
			articleDTOcree1 = vendeurFacade.saveArticle(vendeurDTO.getId(), articleDTO1, categorieDTO.getId());

			ArticleDTO articleDTO2 = new ArticleDTO();
			articleDTO2.setLibelle(defaultLibelleArticle + "2");
			articleDTO2.setMarque(defaultMarqueArticle + "2");
			articleDTO2.setModele(defaultModeleArticle + "2");
			articleDTO2.setPrixVente(new Double(defaultPrixVenteArticle.doubleValue() + 2));
			articleDTO2.setAnneeFabrication(new Integer(defaultAnneeFabricationArticle.intValue() + 2));
			articleDTO2.setDateLimite(new Date(defaultDateLimiteArticle.getTime() + 2 * 60 * 1000)); // date courant + 1 min
			articleDTO2.setDescription(defaultDescriptionArticle + "1");	
			articleDTOcree2 = vendeurFacade.saveArticle(vendeurDTO.getId(), articleDTO2, categorieDTO.getId());
			
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
		VendeurLocalHome  vendeurLocalHome=VendeurFacadeBean.getEntityHome();
		vendeurLocalHome.findByPrimaryKey(vendeurDTO.getId()).remove();
		//suppression de la categorie creee
		CategorieLocalHome  categorieLocalHome=CategorieFacadeBean.getEntityHome();
		categorieLocalHome.findByPrimaryKey(categorieDTO.getId()).remove();
		//suppression du membre cree
		MembreLocalHome  membreLocalHome=MembreFacadeBean.getEntityHome();		
		membreLocalHome.findByPrimaryKey(membreDTO.getId()).remove();
	}
	
	

	public void testGetArticle() throws RemoteException {
		try {
			//récupération de l'article créé via facade			
			ArticleDTO articleDTOrecup = articleFacade.getArticle(articleDTOcree1.getId());
			boolean testerId=false;
			assertTrue(this.equalsDTO(articleDTOrecup, articleDTOcree1,testerId));
		} catch (RemoteException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	
	public void testGetVendeurDTO() throws RemoteException {
		try {
			//récupération de id de l'article créé 
			String idArticle=articleDTOcree1.getId();
			VendeurDTO vendeurDTOrecup=articleFacade.getVendeurDTO(idArticle);			
			assertTrue(VendeurFacadeTest.equalsDTO(vendeurDTOrecup,vendeurDTO, true));
		} catch (RemoteException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	public void testGetCategorieDTO() throws RemoteException {
		try {
			//récupération de id de l'article créé 
			String idArticle=articleDTOcree1.getId();
			CategorieDTO categorieDTOrecup=articleFacade.getCategorieDTO(idArticle);			
			assertTrue(CategorieFacadeTest.equalsDTO(categorieDTOrecup,categorieDTO));
		} catch (RemoteException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	public void testGetArticlesEnVenteByCategorie() {
		//prerequis : articles en vente.
		try {
			vendeurFacade.mettreEnVenteArticle(vendeurDTO.getId(),articleDTOcree1.getId());
			vendeurFacade.mettreEnVenteArticle(vendeurDTO.getId(),articleDTOcree2.getId());
		} catch (RemoteException e1) {
			assertTrue(false);
		} catch (ArticleProprietaireException e1) {
			assertTrue(false);
		}
		
		try {
			//récupération de id de la categorie creee 
			String idCategorie=categorieDTO.getId();
			Collection articles=articleFacade.getArticlesEnVenteByCategorie(idCategorie);
			//la collection doit contenir 2 articles
			assertTrue( articles.size() == 2);
		} catch (RemoteException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		//retrait de la vente des articles
		try {
			vendeurFacade.retirerArticle(vendeurDTO.getId(), articleDTOcree1.getId());
			vendeurFacade.retirerArticle(vendeurDTO.getId(), articleDTOcree2.getId());
		} catch (RemoteException e) {
			assertTrue(false);
		} catch (ArticleEnEnchereException e) {
			assertTrue(false);
		} catch (ArticleVenduException e) {
			assertTrue(false);
		} catch (ArticleProprietaireException e) {
			assertTrue(false);
		}
		
	}
	
	public void testDeposerTransactionAvis(){
		//prerequis : article vendu
		ArticleLocalHome articleHome = (ArticleLocalHome) ArticleFacadeBean.getEntityHome();
		ActionTransactionLocalHome transactionHome = (ActionTransactionLocalHome) ActionTransactionFacadeBean.getEntityHome();
		ArticleLocal articleLocal = null;
		ActionTransactionLocal transactionLocal = null;
		try {
			articleLocal = articleHome.findByPrimaryKey(articleDTOcree1.getId());
		} catch (FinderException e) {
		}
		ActionTransactionDTO transactionDTO = new ActionTransactionDTO();
		transactionDTO.setDate(new Date(System.currentTimeMillis()));
		transactionDTO.setMontant(articleLocal.getPrixVente());
		try {
			transactionLocal = transactionHome.create(transactionDTO);
		} catch (CreateException e) {
		}
		transactionLocal.setArticleLocal(articleLocal);
		
		//Debut du test
		String avis = "très bien";
		try {
			articleFacade.deposerTransactionAvis(articleDTOcree1.getId(), avis);
		} catch (RemoteException e) {
		}		
		assertEquals(articleLocal.getActionTransactionLocal().getAvis(), avis); //avis dans ActionTransactionBean
		assertEquals(articleLocal.getTransactionAvis(), avis); //info redondante : transactionAvis dans ArticleBean
		//Test fini
		
		//suppression de la Transaction
		try {
			transactionLocal.remove();
		} catch (EJBException e) {
		} catch (RemoveException e) {
		}
	}	


	
	
	
	
	
	//methode a deplacer dans vendeurfacadetest
	public void testRemoveArticle(){
		try {
			
			ArticleDTO tArticleDTO = new ArticleDTO();
			tArticleDTO.setLibelle("article 1");
			tArticleDTO.setMarque("marque 1");
			tArticleDTO.setModele("model 1");
			tArticleDTO.setPrixVente(new Double(25.500));
			tArticleDTO.setAnneeFabrication(new Integer(1983));
			tArticleDTO.setDateLimite(new Date(System.currentTimeMillis() + 1 * 60 * 1000)); // date courant + 1min
			tArticleDTO.setDescription("Le premier article");	
			
			try {
				tArticleDTO=vendeurFacade.saveArticle(vendeurDTO.getId(),tArticleDTO,categorieDTO.getId());
			} catch (VendeurInconnuException e2) {
			} catch (Exception e2) {
			}
			
			//récupération de id de l'article créé 
			String idArticle=tArticleDTO.getId();
			//mise en vente de l article
			vendeurFacade.mettreEnVenteArticle(vendeurDTO.getId(),idArticle);
			//un article ne doit pouvoir etre supprimer si il est en vente
			try{
				vendeurFacade.removeArticle(vendeurDTO.getId(),idArticle);
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
			try {
				vendeurFacade.retirerArticle(vendeurDTO.getId(),idArticle);
			} catch (ArticleEnEnchereException e1) {
				//ne peut pas se produire
				assertTrue(false);
			} catch (ArticleVenduException e1) {
				//ne peut pas se produire
				assertTrue(false);
			} catch (ArticleProprietaireException e) {
				//ne peut pas se produire
				assertTrue(false);				
			}
			
			
			
			//test : un vendeur supprime un article qui ne lui appartient pas 
			try{
				vendeurFacade.removeArticle("zdbzdb",idArticle);
				assertTrue(false);//echec : la suppression n aurais pas du marcher --> raison le vendeur passer en parametre n est pas le proprietaire
			}
			catch(ArticleEnVenteException e){
				//ne peut pas se produire
				assertTrue(false);
			} catch (ArticleVenduException e) {
				//ne peut pas se produire
				assertTrue(false);
			} catch (ArticleProprietaireException e) {
				assertTrue(true);//succes : la suppression n a pas marcher --> raison le vendeur passer en parametre n est pas le proprietaire
			} catch (Exception e) {
			} 
			
			//a partie d ici la suppression doit etre possible
			try {
				vendeurFacade.removeArticle(vendeurDTO.getId(),idArticle);
				assertTrue(true);//succes suppression n a pas levé d'exception
			} catch (Exception e) {				
				assertTrue(false);
			}
			
			// on test si la suppression a bien fonctionné

				try {
					articleFacade.getArticle(idArticle);
					assertTrue(false);//echec l article ne devrais pas exister
				} catch (FinderException e) {
					assertTrue(true);//succes l article n existe plus
				}
	
			
		} catch (RemoteException e) {			
			assertTrue(false);
			e.printStackTrace();
		} catch (ArticleProprietaireException e) {
			assertTrue(false);
			e.printStackTrace();
		}
		
	}
	
	
	// A Revoir
	public void testRechercherArticlesEnVente(){
		// prérequis : articles en vente
		Collection articles = null;
		try {
			articles = articleFacade.rechercherArticlesEnVente(
					categorieDTO.getLibelle(), 
					articleDTOcree1.getLibelle(), 
					articleDTOcree1.getMarque(), 
					articleDTOcree1.getModele(), 
					new Double(articleDTOcree1.getPrixVente().doubleValue() - 1) , 
					new Double(articleDTOcree1.getPrixVente().doubleValue() + 1), 
					articleDTOcree1.getAnneeFabrication(), 
					articleDTOcree1.getDateLimite());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertTrue( articles.size() == 0 ); // Aucun article n'a du être trouvé car ceux-ci ne sont pas en vente.
		
		// mise en vente
		System.out.println("1");
		ArticleLocalHome articleLocalHome = ArticleFacadeBean.getEntityHome();		
		try {
			articleLocalHome.findByPrimaryKey(articleDTOcree1.getId()).setEnVente(new Boolean(true));
			articleLocalHome.findByPrimaryKey(articleDTOcree2.getId()).setEnVente(new Boolean(true));
		} catch (FinderException e) {
			e.printStackTrace();
		}
		System.out.println("2");
		//recherche article1
		try {
			articles = articleFacade.rechercherArticlesEnVente(
					categorieDTO.getLibelle(), 
					articleDTOcree1.getLibelle(), 
					articleDTOcree1.getMarque(), 
					articleDTOcree1.getModele(), 
					new Double(articleDTOcree1.getPrixVente().doubleValue() - 1) , 
					new Double(articleDTOcree1.getPrixVente().doubleValue() + 1), 
					new Integer(articleDTOcree1.getAnneeFabrication().intValue() - 10),
					new Date(articleDTOcree1.getDateLimite().getTime() - 100000));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println("articles.size() : " + articles.size());
		assertTrue( articles.size() == 1 ); // Cette fois-ci l'article est en vente.
		//recherche article2
		try {
			articles = articleFacade.rechercherArticlesEnVente(
					categorieDTO.getLibelle(), 
					articleDTOcree2.getLibelle(), 
					articleDTOcree2.getMarque(), 
					articleDTOcree2.getModele(), 
					new Double(articleDTOcree2.getPrixVente().doubleValue() - 1) , 
					new Double(articleDTOcree2.getPrixVente().doubleValue() + 1), 
					new Integer(articleDTOcree2.getAnneeFabrication().intValue() - 10),
					new Date(articleDTOcree2.getDateLimite().getTime() - 100000));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertTrue( articles.size() == 1 ); // Cette fois-ci l'article est en vente.
		
		try {
			articles = articleFacade.rechercherArticlesEnVente(
					defaultNomCategorie, 
					defaultLibelleArticle, 
					defaultMarqueArticle, 
					defaultModeleArticle, 
					new Double(defaultPrixVenteArticle.doubleValue() - 10) , 
					new Double(defaultPrixVenteArticle.doubleValue() + 10), 
					new Integer(defaultAnneeFabricationArticle.intValue() - 10), 
					new Date(defaultDateLimiteArticle.getTime() - 100000));
		} catch (RemoteException e) {
		}
		assertTrue( articles.size() == 2 ); // Cette fois-ci les 2 articles sont en vente et correspondent à la requete.
	
	}
	
	public void testGetDerniereEnchere() throws FinderException, CreateException{
		// prerequis : enchères sur l'article
		ArticleLocal articleLocal = ArticleFacadeBean.getEntity(articleDTOcree1.getId());
		ActionEnchereDTO enchereDTO1 = new ActionEnchereDTO();
		enchereDTO1.setDate(new Date(System.currentTimeMillis() + 10000));
		enchereDTO1.setDateLimite(articleLocal.getDateLimite());
		enchereDTO1.setMontant(new Double(articleLocal.getPrixVente().doubleValue()));
		ActionEnchereDTO enchereDTO2 = new ActionEnchereDTO();
		enchereDTO2.setDate(new Date(System.currentTimeMillis()));
		enchereDTO2.setDateLimite(new Date(articleLocal.getDateLimite().getTime() + 1000 ));
		enchereDTO2.setMontant(new Double(articleLocal.getPrixVente().doubleValue()));
		ClientLocalHome clientLocalHome = ClientFacadeBean.getEntityHome();
		ClientLocal clientLocal = clientLocalHome.create();
		ActionEnchereLocalHome actionEnchereLocalHome = ActionEnchereFacadeBean.getEntityHome();
		ActionEnchereLocal enchereLocal1 = actionEnchereLocalHome.create(enchereDTO1, articleLocal, clientLocal);
		ActionEnchereLocal enchereLocal2 = actionEnchereLocalHome.create(enchereDTO2, articleLocal, clientLocal);
		
		enchereDTO1 = enchereLocal1.getActionEnchereDTO();
		enchereDTO2 = enchereLocal2.getActionEnchereDTO();
		
		ActionEnchereDTO enchereDTO = null;
		try {
			enchereDTO = articleFacade.getDerniereEnchere(articleDTOcree1.getId());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertTrue(enchereDTO.getId().equals(enchereDTO1.getId()));
		assertFalse(enchereDTO.getId().equals(enchereDTO2.getId()));
	}
	
	public void testGetNbEncheres() throws FinderException, CreateException{
		// prerequis : enchères sur l'article
		ArticleLocal articleLocal = ArticleFacadeBean.getEntity(articleDTOcree1.getId());
		ActionEnchereDTO enchereDTO1 = new ActionEnchereDTO();
		enchereDTO1.setDate(new Date(System.currentTimeMillis() + 10000));
		enchereDTO1.setDateLimite(articleLocal.getDateLimite());
		enchereDTO1.setMontant(new Double(articleLocal.getPrixVente().doubleValue()));
		ActionEnchereDTO enchereDTO2 = new ActionEnchereDTO();
		enchereDTO2.setDate(new Date(System.currentTimeMillis()));
		enchereDTO2.setDateLimite(new Date(articleLocal.getDateLimite().getTime() + 1000 ));
		enchereDTO2.setMontant(new Double(articleLocal.getPrixVente().doubleValue()));
		ClientLocalHome clientLocalHome = ClientFacadeBean.getEntityHome();
		ClientLocal clientLocal = clientLocalHome.create();
		ActionEnchereLocalHome actionEnchereLocalHome = ActionEnchereFacadeBean.getEntityHome();
		ActionEnchereLocal enchereLocal1 = actionEnchereLocalHome.create(enchereDTO1, articleLocal, clientLocal);
		ActionEnchereLocal enchereLocal2 = actionEnchereLocalHome.create(enchereDTO2, articleLocal, clientLocal);
		
		enchereDTO1 = enchereLocal1.getActionEnchereDTO();
		enchereDTO2 = enchereLocal2.getActionEnchereDTO();

		try {
			Integer nbEncheres = articleFacade.getNbEncheres(articleDTOcree1.getId());
			assertEquals(nbEncheres.intValue(), 2);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void testGetAcquereur() throws CreateException{
		//prerequis : article vendu
		ArticleLocalHome articleHome = (ArticleLocalHome) ArticleFacadeBean.getEntityHome();
		ActionTransactionLocalHome transactionHome = (ActionTransactionLocalHome) ActionTransactionFacadeBean.getEntityHome();
		ArticleLocal articleLocal = null;
		ActionTransactionLocal transactionLocal = null;
		try {
			articleLocal = articleHome.findByPrimaryKey(articleDTOcree1.getId());
		} catch (FinderException e) {
		}
		ActionTransactionDTO transactionDTO = new ActionTransactionDTO();
		transactionDTO.setDate(new Date(System.currentTimeMillis()));
		transactionDTO.setMontant(articleLocal.getPrixVente());
		ClientLocalHome clientLocalHome = ClientFacadeBean.getEntityHome();
		ClientLocal clientLocal = clientLocalHome.create();
		try {
			transactionLocal = transactionHome.create(transactionDTO);
			transactionLocal.setArticleLocal(articleLocal);
			transactionLocal.setClientLocal(clientLocal);
		} catch (CreateException e) {
		}
		
		//ICI
		
		
		
	}
	
	public boolean equalsDTO(ArticleDTO articleDTO1, ArticleDTO articleDTO2,boolean testerId){
		boolean tRes = true;
		if (testerId){
			tRes = tRes && ( (articleDTO1.getId()).equals(articleDTO2.getId()) );
		}
		tRes = tRes && ( (articleDTO1.getLibelle()).equals(articleDTO2.getLibelle()) );
		tRes = tRes && ( (articleDTO1.getMarque()).equals(articleDTO2.getMarque()) );
		tRes = tRes && ( (articleDTO1.getModele()).equals(articleDTO2.getModele()) );
		tRes = tRes && ( (articleDTO1.getPrixVente()).equals(articleDTO2.getPrixVente()) );
		tRes = tRes && ( (articleDTO1.getAnneeFabrication()).equals(articleDTO2.getAnneeFabrication()) );
		/* Remarque : Il y a un petit soucis avec la comparaison de dates. A éclaicir... */
		//tRes = tRes && ( (articleDTO1.getDateLimite()).equals(articleDTO2.getDateLimite()) ); // erreur ici, il faudra verifier
		tRes = tRes && ( (articleDTO1.getDescription()).equals(articleDTO2.getDescription()) );
		return tRes;
	}
	
	private void poserEncheres(){
	
	}
	
	
	
	
/*
 	public void terminerVente(String articleId) //local : pas à tester ?
		
	public ClientDTO getAcquereur(String articleId) 
		
	public ActionTransactionDTO getTransaction(String articleId) 
	
	public ClientDTO getDernierEncherisseur(String articleId)
		
	public ActionEnchereDTO encherir(ActionEnchereDTO enchereDTO, String articleId, String clientId) throws ArticlePasEnVenteException
	
*/

	
	
}


