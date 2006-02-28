package axlomoso.ezbay.delegate;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import axlomoso.ezbay.exceptions.ArticleEnEnchereException;
import axlomoso.ezbay.exceptions.ArticleEnVenteException;
import axlomoso.ezbay.exceptions.ArticleProprietaireException;
import axlomoso.ezbay.exceptions.ArticleVenduException;
import axlomoso.ezbay.exceptions.VendeurInconnuException;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreFacade;
import axlomoso.ezbay.model.interfaces.MembreFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurFacade;
import axlomoso.ezbay.model.interfaces.VendeurFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurLocal;
import axlomoso.ezbay.struts.views.ArticleView;
import axlomoso.ezbay.utils.ServiceLocator;
import axlomoso.ezbay.utils.ServiceLocatorException;
import axlomoso.ezbay.utils.Util;

public class VendeurFacadeDelegate {

	private VendeurFacade vendeurFacade = null;
	private static VendeurFacadeDelegate instance = null;
	private Util util = new Util();

	public static VendeurFacadeDelegate getInstance(){
		if( instance == null ) instance = new VendeurFacadeDelegate();
		return instance;
	}
	
	private VendeurFacadeDelegate(){
		try {
			ServiceLocator locator = ServiceLocator.getInstance();
			VendeurFacadeHome home = (VendeurFacadeHome) locator.getRemoteHome(VendeurFacadeHome.JNDI_NAME, VendeurFacadeHome.class);
			this.vendeurFacade = home.create();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		}
	}

	
	public VendeurFacade getVendeurFacade() {
		return vendeurFacade;
	}

	public void setVendeurFacade(VendeurFacade vendeurFacade) {
		this.vendeurFacade = vendeurFacade;
	}

	public VendeurDTO getVendeurById(String vendeurId) throws Exception, RemoteException {
		return vendeurFacade.getVendeur(vendeurId);
	}

	public MembreDTO getMembreByVendeurId(String vendeurId) throws Exception, RemoteException {
		return vendeurFacade.getMembre(vendeurId);
	}
	
	public Collection getVendeurs() throws RemoteException {
		return vendeurFacade.getVendeurs();
	}

	public Collection getArticlesEnAttente(String vendeurId) throws RemoteException, Exception {
		Collection tRes;
		long d = System.currentTimeMillis();
		Collection articles = vendeurFacade.getArticlesEnAttente(vendeurId);
		ArticleFacadeDelegate articleFacade = ArticleFacadeDelegate.getInstance();
		tRes = articleFacade.getArticlesDtoToView(articles);
		System.out.println("getArticlesEnAttente" + (System.currentTimeMillis() - d));
		return tRes;
	}

	
	
	public Collection getArticlesEnVente(String vendeurId) throws RemoteException, Exception {
		Collection tRes;
		long d = System.currentTimeMillis();
		Collection articles = vendeurFacade.getArticlesEnVente(vendeurId);
		ArticleFacadeDelegate articleFacade = ArticleFacadeDelegate.getInstance();
		tRes = articleFacade.getArticlesDtoToView(articles);
		System.out.println("getArticlesEnAttente" + (System.currentTimeMillis() - d));
		return tRes;
	}

	public Collection getArticlesVendus(String vendeurId) throws RemoteException, Exception {
		Collection tRes;
		long d = System.currentTimeMillis();
		Collection articles = vendeurFacade.getArticlesVendus(vendeurId);
		ArticleFacadeDelegate articleFacade = ArticleFacadeDelegate.getInstance();
		tRes = articleFacade.getArticlesDtoToView(articles);
		System.out.println("getArticlesEnAttente" + (System.currentTimeMillis() - d));
		return tRes;
	}

	public ArticleDTO saveArticle(String vendeurId, ArticleDTO articleDTO, String categorieId) throws VendeurInconnuException, Exception, RemoteException {
		return vendeurFacade.saveArticle(vendeurId, articleDTO, categorieId);
	}
	
	public void removeArticle(String vendeurId, String articleId) throws ArticleProprietaireException, ArticleEnVenteException, ArticleVenduException, Exception{
		vendeurFacade.removeArticle(vendeurId, articleId);
	}
	
	public void retirerArticle(String vendeurId, String articleId) throws ArticleProprietaireException, ArticleEnEnchereException, ArticleVenduException, Exception{
		vendeurFacade.retirerArticle(vendeurId, articleId);
	}
	
	public void mettreEnVenteArticle(String vendeurId, String articleId) throws ArticleProprietaireException, ArticleVenduException, Exception{
		vendeurFacade.mettreEnVenteArticle(vendeurId, articleId);
	}
	
}
