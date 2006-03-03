package axlomoso.ezbay.test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import axlomoso.ezbay.exceptions.ArticleEnEnchereException;
import axlomoso.ezbay.exceptions.ArticleEnVenteException;
import axlomoso.ezbay.exceptions.ArticlePasEnVenteException;
import axlomoso.ezbay.exceptions.ArticleProprietaireException;
import axlomoso.ezbay.exceptions.ArticleVenduException;
import axlomoso.ezbay.exceptions.VendeurInconnuException;
import axlomoso.ezbay.model.ejb.ArticleFacadeBean;
import axlomoso.ezbay.model.ejb.CategorieFacadeBean;
import axlomoso.ezbay.model.ejb.MembreFacadeBean;
import axlomoso.ezbay.model.ejb.VendeurFacadeBean;
import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.ActionTransactionDTO;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleFacade;
import axlomoso.ezbay.model.interfaces.ArticleFacadeHome;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ArticleLocalHome;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieFacade;
import axlomoso.ezbay.model.interfaces.CategorieFacadeHome;
import axlomoso.ezbay.model.interfaces.CategorieLocalHome;
import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreFacade;
import axlomoso.ezbay.model.interfaces.MembreFacadeHome;
import axlomoso.ezbay.model.interfaces.MembreLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurFacade;
import axlomoso.ezbay.model.interfaces.VendeurFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurLocal;
import axlomoso.ezbay.model.interfaces.VendeurLocalHome;
import axlomoso.ezbay.struts.views.ArticleView;
import axlomoso.ezbay.utils.ServiceLocator;
import axlomoso.ezbay.utils.ServiceLocatorException;

import junit.framework.TestCase;

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
	MembreDTO membreDTO;
	ArticleFacade articleFacade;
	VendeurFacade vendeurFacade;
	MembreFacade membreFacade;
	CategorieFacade categorieFacade;	

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
			
			ArticleDTO articleDTO1=new ArticleDTO();
			ArticleDTO articleDTO2=new ArticleDTO();
			VendeurDTO vendeurDTO1=new VendeurDTO();
			CategorieDTO categorieDTO1=new CategorieDTO();
			membreDTO=new MembreDTO();
			
			//creation d un membre
			membreDTO.setNom("membre");
			membreDTO=membreFacade.saveMembre(membreDTO);
			//creation d un vendeur
			vendeurDTO1.setNomProprioCB("vendeur");			
			vendeurDTO=membreFacade.saveVendeur(membreDTO,vendeurDTO1);
			
			//creation d une categorie
			categorieDTO1.setLibelle("Categorie1");
			categorieDTO=categorieFacade.createCategorie(categorieDTO1);
			
			//creation de deux articles 			
			articleDTO1.setLibelle("article 1");
			articleDTO1.setMarque("marque 1");
			articleDTO1.setModele("model 1");
			articleDTO1.setPrixVente(new Double(25.500));
			articleDTO1.setAnneeFabrication(new Integer(1983));
			articleDTO1.setDateLimite(new Date(127));
			articleDTO1.setDescription("Le premier article");	
			
			articleDTOcree1=vendeurFacade.saveArticle(vendeurDTO.getId(),articleDTO1,categorieDTO.getId());

			
			articleDTO2.setLibelle("article 2");
			articleDTO2.setMarque("marque 2");
			articleDTO2.setModele("model 2");
			articleDTO2.setPrixVente(new Double(25.500));
			articleDTO2.setAnneeFabrication(new Integer(1983));
			articleDTO2.setDateLimite(new Date(127));
			articleDTO2.setDescription("Le deuxieme article");	
			
			articleDTOcree2=vendeurFacade.saveArticle(vendeurDTO.getId(),articleDTO2,categorieDTO.getId());
					
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}	
		
		
	}

	protected void tearDown() throws Exception {
		//suppression des articles crees		
		vendeurFacade.removeArticle(vendeurDTO.getId(),articleDTOcree1.getId());
		vendeurFacade.removeArticle(vendeurDTO.getId(),articleDTOcree2.getId());
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
			assertTrue(VendeurFacadeTest.equalsDTO(vendeurDTOrecup,vendeurDTO));
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
	
	
	
	
	
	//methode a deplacer dans vendeurfacadetest
	public void testRemoveArticle(){
		try {
			
			ArticleDTO tArticleDTO = new ArticleDTO();
			tArticleDTO.setLibelle("article 1");
			tArticleDTO.setMarque("marque 1");
			tArticleDTO.setModele("model 1");
			tArticleDTO.setPrixVente(new Double(25.500));
			tArticleDTO.setAnneeFabrication(new Integer(1983));
			tArticleDTO.setDateLimite(new Date(127));
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
			e.printStackTrace();
		} catch (ArticleProprietaireException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	// ne marche pas
	public Collection getArticlesEnVenteByVendeur(String vendeurId) throws RemoteException, Exception {
		return this.getArticlesDtoToView(articleFacade.getArticlesEnVenteByVendeur(vendeurId));
	}
	*/
	
	/*public void testGetArticlesEnVenteByVendeur() throws RemoteException {
	 *
		Collection articlesDTO = articleFacade.getArticlesByVendeur(articleLocal.getVendeurLocal().getId());
		for (Iterator it = articlesDTO.iterator(); it.hasNext(); ) {
			ArticleDTO articleDTO = (ArticleDTO) it.next();
			assertEquals(articleDTOTemoin.getLibelle(),articleDTO.getLibelle());
		}
	}*/
	
	// ne marche pas
	/*
	public Collection getArticlesEnVenteByCategorie(String categorieId) throws RemoteException, Exception {
		return this.getArticlesDtoToView(articleFacade.getArticlesEnVenteByCategorie(categorieId));
	}
	public void testGetArticlesByCategorie() throws RemoteException {
		Collection articlesDTO = articleFacade.getArticlesByCategorie(articleLocal.getCategorieLocal().getId());
		for (Iterator it = articlesDTO.iterator(); it.hasNext(); ) {
			ArticleDTO articleDTO = (ArticleDTO) it.next();
			assertEquals(articleDTOTemoin.getLibelle(),articleDTO.getLibelle());
		}
	}*/
	
	
	
	
	
	
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
	
	
	
	
/*
		public void terminerVente(String articleId){
		public void deposerTransactionAvis(String articleId, String avis){
		
	
	
		
		public void retirerArticle(String articleId) throws Exception {
		
	public void mettreEnVenteArticle(String articleId) throws Exception {	
	
	
		public Collection rechercherArticlesEnVente(String libcategorie, String libelle, String marque, String modele, Double prixVenteMin,
			Double prixVenteMax, Integer anneeFabrication, Date dateLimite) {
		
	public Collection getArticlesEnVenteByVendeur(String vendeurId) {
		
	public Collection getArticlesEnAttenteByVendeur(String vendeurId) {
		
	public Collection getArticlesVendusByVendeur(String vendeurId) {
		
	public Collection getArticlesEnVenteByCategorie(String categorieId) {
	
	
	
	
	public ActionEnchereDTO getDerniereEnchere(String articleId){
		
	public Integer getNbEncheres(String articleId){
	
	public ClientDTO getAcquereur(String articleId) {
		
	public ActionTransactionDTO getTransaction(String articleId) {
	
	public ClientDTO getDernierEncherisseur(String articleId){
		
		public ActionEnchereDTO encherir(ActionEnchereDTO enchereDTO, String articleId, String clientId) throws ArticlePasEnVenteException{
	
	*/

	
	
}


