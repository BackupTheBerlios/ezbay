package axlomoso.ezbay.delegate;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

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

public class VendeurFacadeDelegate {
	private VendeurFacade vendeurFacade = null;
	
	public VendeurFacadeDelegate() throws Exception {
		try {
			Context jndiContext;
			jndiContext = new InitialContext();
			Object ref = jndiContext.lookup(VendeurFacadeHome.JNDI_NAME);
			VendeurFacadeHome facadeHome = (VendeurFacadeHome) PortableRemoteObject.narrow(ref, VendeurFacadeHome.class);
			this.vendeurFacade = facadeHome.create();
		} catch (Exception e) {
			throw new Exception("Cannot locate MembreFacadeHome", e);
		}
	}
	
	public VendeurFacade getVendeurFacade() {
		return vendeurFacade;
	}

	public void setVendeurFacade(VendeurFacade vendeurFacade) {
		this.vendeurFacade = vendeurFacade;
	}

	public Collection getArticles(String vendeurId) throws RemoteException {
		return vendeurFacade.getArticles(vendeurId);
	}

	public VendeurDTO getVendeur(String vendeurId) throws Exception, RemoteException {
		return vendeurFacade.getVendeur(vendeurId);
	}

	public MembreDTO getMembre(String vendeurId) throws Exception, RemoteException {
		return vendeurFacade.getMembre(vendeurId);
	}
	
	public Collection getVendeurs() throws RemoteException {
		return vendeurFacade.getVendeurs();
	}

	public Collection getArticlesEnAttente(String vendeurId) throws RemoteException {
		Collection articles = vendeurFacade.getArticlesEnAttente(vendeurId);
		return getArticlesDtoToView(articles);
	}

	public Collection getArticlesEnVente(String vendeurId) throws RemoteException {
		Collection articles = vendeurFacade.getArticlesEnAttente(vendeurId);
		return getArticlesDtoToView(articles);
	}

	public Collection getArticlesVendus(String vendeurId) throws RemoteException {
		Collection articles = vendeurFacade.getArticlesEnAttente(vendeurId);
		return getArticlesDtoToView(articles);
	}

	public ArticleDTO saveArticle(String vendeurId, ArticleDTO articleDTO, String categorieId) throws VendeurInconnuException, Exception, RemoteException {
		return vendeurFacade.saveArticle(vendeurId, articleDTO, categorieId);
	}
	
	public void removeArticle(String vendeurId, String articleId) throws ArticleProprietaireException, ArticleEnVenteException, ArticleVenduException, Exception{
		vendeurFacade.removeArticle(vendeurId, articleId);
	}
	
	/* transforme une liste d'ArticleDTO en une liste d'articleView */
	private Collection getArticlesDtoToView(Collection articles) {
		Collection tRes = new ArrayList();
		for (Iterator it = articles.iterator(); it.hasNext(); ) {
			ArticleDTO articleDTO = (ArticleDTO) it.next();
			ArticleView articleView = new ArticleView();
			articleView.setArticleDTO(articleDTO);
			tRes.add(articleView);
	    }		
		return tRes;
	}
	
	
}
