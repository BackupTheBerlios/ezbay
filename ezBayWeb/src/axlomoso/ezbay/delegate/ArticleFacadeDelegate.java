package axlomoso.ezbay.delegate;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleFacade;
import axlomoso.ezbay.model.interfaces.ArticleFacadeHome;
import axlomoso.ezbay.model.interfaces.ArticleFacadeUtil;
import axlomoso.ezbay.model.interfaces.ArticleLocalHome;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.utils.ServiceLocator;
import axlomoso.ezbay.utils.ServiceLocatorException;
import axlomoso.ezbay.utils.Util;

public class ArticleFacadeDelegate {
	private Util util = new Util();
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

	/*public Collection getArticles() throws RemoteException {
		return util.getArticlesDtoToView(articleFacade.getAllArticles());
	}*/

	public Collection getArticlesEnVenteByCategorie(String categorieId) throws RemoteException {
		return util.getArticlesDtoToView(articleFacade.getArticlesEnVenteByCategorie(categorieId));
	}

	/*
	public Collection getArticlesByLibelle(String libelle) throws RemoteException {
		return util.getArticlesDtoToView(articleFacade.getArticlesEnVenteByLibelle(libelle));
	}*/

	public Collection getArticlesEnVenteByVendeur(String vendeurId) throws RemoteException {
		return util.getArticlesDtoToView(articleFacade.getArticlesEnVenteByVendeur(vendeurId));
	}

	public CategorieDTO getCategorieDTO(String articleId) throws Exception, RemoteException {
		return articleFacade.getCategorieDTO(articleId);
	}

	public VendeurDTO getVendeurDTO(String articleId) throws Exception, RemoteException {
		return articleFacade.getVendeurDTO(articleId);
	}

	public Collection rechercherArticlesEnVente(String libcategorie, String libelle, String marque, String modele, Double prixVenteMin,Double prixVenteMax, Integer anneeFabrication, Date dateLimite) throws RemoteException {
		return util.getArticlesDtoToView(articleFacade.rechercherArticlesEnVente(libcategorie, libelle, marque, modele, prixVenteMin,prixVenteMax, anneeFabrication, dateLimite));
	}
	
}
