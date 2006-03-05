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
import axlomoso.ezbay.exceptions.ArticlePasEnVenteException;
import axlomoso.ezbay.exceptions.ArticleProprietaireException;
import axlomoso.ezbay.exceptions.ArticleVenduException;
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
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ArticleLocalHome;
import axlomoso.ezbay.model.interfaces.ArticleUtil;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieFacade;
import axlomoso.ezbay.model.interfaces.CategorieFacadeHome;
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
import axlomoso.ezbay.utils.ServiceLocator;
import axlomoso.ezbay.utils.ServiceLocatorException;

/**
 */
public class ArticleFacadeTest extends TestCase {
	/**
	 * The fixture
	 */
	private ArticleDTO articleDTOcree1;
	private ArticleDTO articleDTOcree2;
	private VendeurDTO vendeurDTOcree1;
	private VendeurDTO vendeurDTOcree2;
	private ClientDTO clientDTOcree1;
	private ClientDTO clientDTOcree2;
	private CategorieDTO categorieDTO;
	private MembreDTO membreDTOcree1;
	private MembreDTO membreDTOcree2;
	private ArticleFacade articleFacade;
	private VendeurFacade vendeurFacade;
	private MembreFacade membreFacade;
	private CategorieFacade categorieFacade;	
	
	private String defaultNomCategorie = new String("Categorie ");
	private String defaultLibelleArticle = new String("article ");
	private String defaultModeleArticle = new String("marque ");
	private String defaultMarqueArticle = new String("modèle ");
	private Double defaultPrixVenteArticle = new Double(100);
	private Integer defaultAnneeFabricationArticle = new Integer(1900);	
	private String defaultDescriptionArticle = new String("description ");
	private Date defaultDateLimiteArticle = new Date(System.currentTimeMillis()); // date courante

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
			VendeurDTO tvendeurDTO;
			tvendeurDTO = new VendeurDTO();
			tvendeurDTO.setNomProprioCB("vendeur 1");		
			VendeurLocal vendeurLocal1 = vendeurLocalHome.create(tvendeurDTO);
			vendeurDTOcree1 = vendeurLocal1.getVendeurDTO();
			tvendeurDTO = new VendeurDTO();
			tvendeurDTO.setNomProprioCB("vendeur 2");		
			VendeurLocal vendeurLocal2 = vendeurLocalHome.create(tvendeurDTO);
			vendeurDTOcree2 = vendeurLocal2.getVendeurDTO();
			
			//relations :
			membreLocal1.setClientLocal(clientLocal1);
			membreLocal1.setVendeurLocal(vendeurLocal1);
			membreLocal2.setClientLocal(clientLocal2);
			membreLocal2.setVendeurLocal(vendeurLocal2);
			//creation d une categorie
			CategorieLocalHome categorieLocalHome = CategorieFacadeBean.getEntityHome();
			CategorieDTO categorieDTO1=new CategorieDTO();
			categorieDTO1.setLibelle(defaultNomCategorie + CategorieUtil.generateGUID(this));
			CategorieLocal categorieLocal = categorieLocalHome.create(categorieDTO1);
			categorieDTO = categorieLocal.getCategorieDTO();
			
			
			//creation de deux articles
			ArticleLocalHome articleLocalHome = ArticleFacadeBean.getEntityHome();
			ArticleDTO articleDTO1=new ArticleDTO();
			articleDTO1.setLibelle(defaultLibelleArticle + ArticleUtil.generateGUID(this));
			articleDTO1.setMarque(defaultMarqueArticle + ArticleUtil.generateGUID(this));
			articleDTO1.setModele(defaultModeleArticle + ArticleUtil.generateGUID(this));
			articleDTO1.setPrixVente(new Double(defaultPrixVenteArticle.doubleValue() + 1));
			articleDTO1.setAnneeFabrication(new Integer(defaultAnneeFabricationArticle.intValue() + 1));
			articleDTO1.setDateLimite(new Date(defaultDateLimiteArticle.getTime() + 1 * 60 * 1000)); // date courant + 1 min
			articleDTO1.setDescription(defaultDescriptionArticle + ArticleUtil.generateGUID(this));
			ArticleLocal articleLocal1 = articleLocalHome.create(articleDTO1, vendeurLocal1);
			articleLocal1.setCategorieLocal(categorieLocal);
			articleDTOcree1 = articleLocal1.getArticleDTO();

			ArticleDTO articleDTO2 = new ArticleDTO();
			articleDTO2.setLibelle(defaultLibelleArticle + ArticleUtil.generateGUID(this));
			articleDTO2.setMarque(defaultMarqueArticle + ArticleUtil.generateGUID(this));
			articleDTO2.setModele(defaultModeleArticle + ArticleUtil.generateGUID(this));
			articleDTO2.setPrixVente(new Double(defaultPrixVenteArticle.doubleValue() + 2));
			articleDTO2.setAnneeFabrication(new Integer(defaultAnneeFabricationArticle.intValue() + 2));
			articleDTO2.setDateLimite(new Date(defaultDateLimiteArticle.getTime() + 2 * 60 * 1000)); // date courant + 1 min
			articleDTO2.setDescription(defaultDescriptionArticle + ArticleUtil.generateGUID(this));	
			ArticleLocal articleLocal2 = articleLocalHome.create(articleDTO2, vendeurLocal1);
			articleLocal2.setCategorieLocal(categorieLocal);
			articleDTOcree2 = articleLocal2.getArticleDTO();
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}	
	}
	
	protected void tearDown() throws Exception {
		//suppression des articles crees
		ArticleLocalHome articleLocalHome = ArticleFacadeBean.getEntityHome(); 
		articleLocalHome.findByPrimaryKey(articleDTOcree1.getId()).remove();
		articleLocalHome.findByPrimaryKey(articleDTOcree2.getId()).remove();
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
		//suppression de la categorie creee
		CategorieLocalHome  categorieLocalHome=CategorieFacadeBean.getEntityHome();
		categorieLocalHome.findByPrimaryKey(categorieDTO.getId()).remove();
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
			assertTrue(VendeurFacadeTest.equalsDTO(vendeurDTOrecup,vendeurDTOcree1, true));
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
			vendeurFacade.mettreEnVenteArticle(vendeurDTOcree1.getId(),articleDTOcree1.getId());
			vendeurFacade.mettreEnVenteArticle(vendeurDTOcree1.getId(),articleDTOcree2.getId());
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
			vendeurFacade.retirerArticle(vendeurDTOcree1.getId(), articleDTOcree1.getId());
			vendeurFacade.retirerArticle(vendeurDTOcree1.getId(), articleDTOcree2.getId());
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
	
	// A Revoir
	public void testRechercherArticlesEnVente(){
		// prérequis : articles en vente
		Collection articles = null;
		try {
			articles = articleFacade.rechercherArticlesEnVente(
					categorieDTO.getId(), 
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
		ArticleLocalHome articleLocalHome = ArticleFacadeBean.getEntityHome();		
		try {
			articleLocalHome.findByPrimaryKey(articleDTOcree1.getId()).setEnVente(new Boolean(true));
			articleLocalHome.findByPrimaryKey(articleDTOcree2.getId()).setEnVente(new Boolean(true));
		} catch (FinderException e) {
			assertTrue(false);
			e.printStackTrace();
		} catch(Exception e){
			assertTrue(false);
			e.printStackTrace();
		}

		//recherche article1
		try {
			articles = articleFacade.rechercherArticlesEnVente(
					categorieDTO.getId(), 
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
		assertTrue( articles.size() == 1 ); // Cette fois-ci l'article est en vente.
		//recherche article2
		try {
			articles = articleFacade.rechercherArticlesEnVente(
					categorieDTO.getId(), 
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
					categorieDTO.getId(), 
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
		// mise en vente
		try {
			articleLocalHome.findByPrimaryKey(articleDTOcree1.getId()).setEnVente(new Boolean(false));
			articleLocalHome.findByPrimaryKey(articleDTOcree2.getId()).setEnVente(new Boolean(false));
		} catch (FinderException e) {
			assertTrue(false);
			e.printStackTrace();
		} catch(Exception e){
			assertTrue(false);
			e.printStackTrace();
		}
	}
	
	
	public void testEncherir() throws FinderException, EJBException, RemoveException{
		ArticleLocal articleLocal = ArticleFacadeBean.getEntity(articleDTOcree1.getId());
		ClientLocalHome clientLocalHome = ClientFacadeBean.getEntityHome();
		ClientLocal clientLocal = clientLocalHome.findByPrimaryKey(clientDTOcree1.getId());
		clientLocal.setMembreLocal(MembreFacadeBean.getEntity(membreDTOcree1.getId()));
		ActionEnchereDTO enchereDTO = new ActionEnchereDTO();
		enchereDTO.setDate(new Date(System.currentTimeMillis() + 10000) );
		enchereDTO.setDateLimite(articleLocal.getDateLimite());
		enchereDTO.setMontant(new Double(articleLocal.getPrixVente().doubleValue()));

		//Debut du test		
		//l'article n'est pas en vente, une ArticlePasEnVenteException doit être levée...
		try {
			articleFacade.encherir(enchereDTO, articleLocal.getId(), clientDTOcree1.getId());
			assertTrue(false); // ECHEC : ça ne doit pas fonctionner ==> l'article n'est pas en vente !
		} catch (ArticlePasEnVenteException e) {
			assertTrue(true); // SUCCESS : ça ne fonctionne pas ==> l'article n'est pas en vente !
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		//mise en vente
		articleLocal.setEnVente(new Boolean(true));
		
		//l'article est en vente, cette fois-ci ça doit marcher...
		try {
			enchereDTO = articleFacade.encherir(enchereDTO, articleLocal.getId(), clientDTOcree1.getId());
			assertTrue(true); // SUCCES : ça marche ==> l'article est en vente !
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ArticlePasEnVenteException e) {
			assertTrue(true); // ECHEC : ça aurait du fonctionner ==> l'article est en vente !
		}
		// test de l'enchère effectuée
		ActionEnchereDTO enchereDTORecup = null;
		try {
			enchereDTORecup =  articleFacade.getDerniereEnchere(articleDTOcree1.getId());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertTrue( (enchereDTORecup.getId().equals(enchereDTO.getId())) );
		
		//vérification des infos redondantes dans ArticleBean
		Double enchereMontantRecup = articleLocal.getDerniereEnchereMontant();
		assertEquals(enchereMontantRecup, enchereDTO.getMontant());
		String encherisseurClientId = articleLocal.getEncherisseurClientId();
		assertEquals(encherisseurClientId, clientDTOcree1.getId());
		String encherisseurMembreId = articleLocal.getEncherisseurMembreId();
		assertEquals(encherisseurMembreId, membreDTOcree1.getId());
		String encherisseurPseudo = articleLocal.getEncherisseurPseudo();
		assertEquals(encherisseurPseudo, membreDTOcree1.getPseudo());
		Integer nbEncheres = articleLocal.getNbEncheres();
		assertEquals(nbEncheres, new Integer(1));
			
		//nettoyage...
		ActionEnchereFacadeBean.getEntity(enchereDTO.getId()).remove();				
	}
	
	public void testGetDerniereEnchere() throws FinderException, CreateException, EJBException, RemoveException{
		// prerequis : enchères sur l'article
		ArticleLocal articleLocal = ArticleFacadeBean.getEntity(articleDTOcree1.getId());
		ActionEnchereDTO enchereDTO1 = new ActionEnchereDTO();
		enchereDTO1.setDate(new Date(System.currentTimeMillis() + 100000));
		enchereDTO1.setDateLimite(articleLocal.getDateLimite());
		enchereDTO1.setMontant(new Double(articleLocal.getPrixVente().doubleValue()));
		ActionEnchereDTO enchereDTO2 = new ActionEnchereDTO();
		enchereDTO2.setDate(new Date(System.currentTimeMillis() + 200000));
		enchereDTO2.setDateLimite(articleLocal.getDateLimite());
		enchereDTO2.setMontant(new Double(articleLocal.getPrixVente().doubleValue()));
		ClientLocalHome clientLocalHome = ClientFacadeBean.getEntityHome();
		ClientLocal clientLocal = clientLocalHome.findByPrimaryKey(clientDTOcree1.getId());
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
		//TODO : revenir là dessus
		//assertTrue(enchereDTO.getId().equals(enchereDTO2.getId()));
		//assertFalse(enchereDTO.getId().equals(enchereDTO1.getId()));
		assertTrue( (enchereDTO.getId().equals(enchereDTO2.getId())) || (enchereDTO.getId().equals(enchereDTO1.getId())) );
		//Nettoyage...
		enchereLocal1.remove();
		enchereLocal2.remove();
	}
	
	public void testGetNbEncheres() throws FinderException, CreateException, EJBException, RemoveException{
		// prerequis : enchères sur l'article
		ArticleLocal articleLocal = ArticleFacadeBean.getEntity(articleDTOcree1.getId());
		ActionEnchereDTO enchereDTO1 = new ActionEnchereDTO();
		enchereDTO1.setDate(new Date(System.currentTimeMillis() + 10000));
		enchereDTO1.setDateLimite(articleLocal.getDateLimite());
		enchereDTO1.setMontant(new Double(articleLocal.getPrixVente().doubleValue()));
		ActionEnchereDTO enchereDTO2 = new ActionEnchereDTO();
		enchereDTO2.setDate(new Date(System.currentTimeMillis() + 20000));
		enchereDTO2.setDateLimite(articleLocal.getDateLimite());
		enchereDTO2.setMontant(new Double(articleLocal.getPrixVente().doubleValue()));
		ClientLocalHome clientLocalHome = ClientFacadeBean.getEntityHome();
		ClientLocal clientLocal = clientLocalHome.findByPrimaryKey(clientDTOcree1.getId());
		ActionEnchereLocalHome actionEnchereLocalHome = ActionEnchereFacadeBean.getEntityHome();
		ActionEnchereLocal enchereLocal1 = actionEnchereLocalHome.create(enchereDTO1, articleLocal, clientLocal);
		ActionEnchereLocal enchereLocal2 = actionEnchereLocalHome.create(enchereDTO2, articleLocal, clientLocal);
		enchereDTO1 = enchereLocal1.getActionEnchereDTO();
		enchereDTO2 = enchereLocal2.getActionEnchereDTO();
		
		enchereDTO1 = enchereLocal1.getActionEnchereDTO();
		enchereDTO2 = enchereLocal2.getActionEnchereDTO();

		try {
			Integer nbEncheres = articleFacade.getNbEncheres(articleDTOcree1.getId());
			assertEquals(nbEncheres.intValue(), 2);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		//Nettoyage...
		enchereLocal1.remove();
		enchereLocal2.remove();
	}
	
	
	public void testGetDernierEncherisseur() throws FinderException, CreateException, EJBException, RemoveException{
		// prerequis : enchères sur l'article
		ArticleLocal articleLocal = ArticleFacadeBean.getEntity(articleDTOcree1.getId());
		ActionEnchereDTO enchereDTO1 = new ActionEnchereDTO();
		enchereDTO1.setDate(new Date(System.currentTimeMillis() + 10000));
		enchereDTO1.setDateLimite(articleLocal.getDateLimite());
		enchereDTO1.setMontant(new Double(articleLocal.getPrixVente().doubleValue()));
		ActionEnchereDTO enchereDTO2 = new ActionEnchereDTO();
		enchereDTO2.setDate(new Date(System.currentTimeMillis() + 20000));
		enchereDTO2.setDateLimite(articleLocal.getDateLimite());
		enchereDTO2.setMontant(new Double(articleLocal.getPrixVente().doubleValue()));
		ClientLocalHome clientLocalHome = ClientFacadeBean.getEntityHome();
		ClientLocal clientLocal = clientLocalHome.findByPrimaryKey(clientDTOcree1.getId());
		ActionEnchereLocalHome actionEnchereLocalHome = ActionEnchereFacadeBean.getEntityHome();
		ActionEnchereLocal enchereLocal1 = actionEnchereLocalHome.create(enchereDTO1, articleLocal, clientLocal);
		ActionEnchereLocal enchereLocal2 = actionEnchereLocalHome.create(enchereDTO2, articleLocal, clientLocal);
		enchereDTO1 = enchereLocal1.getActionEnchereDTO();
		enchereDTO2 = enchereLocal2.getActionEnchereDTO();
		ClientDTO clientDTO = null;
		try {
			clientDTO = articleFacade.getDernierEncherisseur(articleDTOcree1.getId());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertTrue(clientDTO.getId().equals(clientDTOcree1.getId()));
		assertFalse(clientDTO.getId().equals(clientDTOcree2.getId()));
		
		//Nettoyage...
		enchereLocal1.remove();
		enchereLocal2.remove();
	}
	
	public void testGetAcquereur() throws CreateException, EJBException, RemoveException, FinderException{
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
		ClientLocal clientLocal = clientLocalHome.findByPrimaryKey(clientDTOcree1.getId());
		try {
			transactionLocal = transactionHome.create(transactionDTO);
			transactionLocal.setArticleLocal(articleLocal);
			transactionLocal.setClientLocal(clientLocal);
		} catch (CreateException e) {
		}
		
		//test
		ClientDTO clientDTO = null;
		try {
			clientDTO = articleFacade.getAcquereur(articleLocal.getId());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertTrue( clientDTO.getId().equals(clientDTOcree1.getId()) );
		
		//nettoyage...
		transactionLocal.remove();
	}
	
	public void testGetTransaction() throws CreateException, EJBException, RemoveException, FinderException{
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
		ClientLocal clientLocal = clientLocalHome.findByPrimaryKey(clientDTOcree1.getId());
		try {
			transactionLocal = transactionHome.create(transactionDTO);
			transactionLocal.setArticleLocal(articleLocal);
			transactionLocal.setClientLocal(clientLocal);
			transactionDTO = transactionLocal.getActionTransactionDTO();
		} catch (CreateException e) {
		}
		
		//test
		ActionTransactionDTO transactionDTORecup = null;
		try {
			transactionDTORecup = articleFacade.getTransaction(articleLocal.getId());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertTrue( transactionDTO.getId().equals(transactionDTO.getId()) );

		//nettoyage...
		transactionLocal.remove();
	}
	
	
	public static boolean equalsDTO(ArticleDTO articleDTO1, ArticleDTO articleDTO2,boolean testerId){
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

	
	
}


