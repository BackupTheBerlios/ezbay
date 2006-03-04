package axlomoso.ezbay.delegate;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;

import axlomoso.ezbay.exceptions.ArticlePasEnVenteException;
import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.ActionTransactionDTO;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleFacade;
import axlomoso.ezbay.model.interfaces.ArticleFacadeHome;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.struts.views.ActionEnchereView;
import axlomoso.ezbay.struts.views.ActionTransactionView;
import axlomoso.ezbay.struts.views.ArticleView;
import axlomoso.ezbay.utils.ServiceLocator;
import axlomoso.ezbay.utils.ServiceLocatorException;

public class ArticleFacadeDelegate {
	private ArticleFacade articleFacade = null;
	private static ArticleFacadeDelegate instance = null;

	public static ArticleFacadeDelegate getInstance(){
		if( instance == null ) instance = new ArticleFacadeDelegate();
		return instance;
	}
	
	private ArticleFacadeDelegate(){
		try {
			ServiceLocator locator = ServiceLocator.getInstance();
			ArticleFacadeHome home;
			home = (ArticleFacadeHome) locator.getRemoteHome(ArticleFacadeHome.JNDI_NAME, ArticleFacadeHome.class);
			this.articleFacade = home.create();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		}

	}

	public ArticleDTO getArticle(String articleId) throws Exception, RemoteException {
		return articleFacade.getArticle(articleId);
	}

	public Collection getArticlesEnVenteByCategorie(String categorieId) throws RemoteException, Exception {
		return this.getArticlesDtoToView(articleFacade.getArticlesEnVenteByCategorie(categorieId));
	}

	public CategorieDTO getCategorieDTO(String articleId) throws Exception, RemoteException {
		return articleFacade.getCategorieDTO(articleId);
	}

	public VendeurDTO getVendeurDTO(String articleId) throws Exception, RemoteException {
		return articleFacade.getVendeurDTO(articleId);
	}

	public Collection rechercherArticlesEnVente(String libcategorie, String libelle, String marque, String modele, Double prixVenteMin,Double prixVenteMax, Integer anneeFabrication, Date dateLimite) throws RemoteException, Exception {
		return this.getArticlesDtoToView(articleFacade.rechercherArticlesEnVente(libcategorie, libelle, marque, modele, prixVenteMin,prixVenteMax, anneeFabrication, dateLimite));
	}

	public ActionEnchereDTO getDerniereEnchere(String articleId) throws RemoteException {
		return articleFacade.getDerniereEnchere(articleId);
	}
	
	public ClientDTO getDernierEncherisseur(String articleId) throws RemoteException {
		return articleFacade.getDernierEncherisseur(articleId);
	}
	
	public Integer getNbEncheres(String articleId) throws RemoteException {
		return articleFacade.getNbEncheres(articleId);
	}
	
	public ActionEnchereDTO encherir(ActionEnchereDTO enchereDTO, String articleId, String clientId) throws RemoteException, ArticlePasEnVenteException{
		return articleFacade.encherir(enchereDTO, articleId, clientId);
	}
	
	public ClientDTO getAcquereur(String artilcleId) throws RemoteException {
		return articleFacade.getAcquereur(artilcleId);
	}

	public ActionTransactionDTO getTransaction(String articleId) throws RemoteException {
		return articleFacade.getTransaction(articleId);
	}
	
	public void deposerTransactionAvis(String articleId, String avis) throws RemoteException {
		articleFacade.deposerTransactionAvis(articleId, avis);		
	}	
	
	
	/* transforme une liste d'ArticleDTO en une liste d'articleView */
    public Collection getArticlesDtoToView(Collection articles) throws Exception {
    	Collection tRes = new ArrayList();
		for (Iterator it = articles.iterator(); it.hasNext(); ) {
			ArticleDTO articleDTO = (ArticleDTO) it.next();
			ArticleView articleView = getArticleViewFromArticleDTO(articleDTO);
			tRes.add(articleView);
	    }
		return tRes;
	}

	/**
	 * @param clientDelegate
	 * @param articleDTO
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArticleView getArticleViewFromArticleDTO(ArticleDTO articleDTO) throws RemoteException, Exception {
		ArticleView articleView = new ArticleView();
		CategorieDTO categorieDTO = new CategorieDTO();
		articleView.setArticleDTO(articleDTO);
		if(articleDTO.getId() != null){
			categorieDTO = this.getCategorieDTO(articleDTO.getId());
		}
		articleView.setCategorieDTO(categorieDTO);
		return articleView;
	}
 
}
